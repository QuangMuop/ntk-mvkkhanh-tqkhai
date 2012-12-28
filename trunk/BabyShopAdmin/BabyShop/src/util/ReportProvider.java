package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import pojos.Report;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class ReportProvider {
	private WebResource _webResource;
    private Client _client;
    private String _baseURL;
    private String _username;
    private String _password;
    private Properties _prop;

    /*
     * Khởi tạo với các tham số do người dùng cấu hình
     */
    public ReportProvider(String baseURL, String username, String password) {
        ClientConfig config = new DefaultClientConfig();
        _client = Client.create(config);
        _baseURL = baseURL;
        _username = username;
        _password = password;
    }
    public void loadConfig(InputStream configFile) throws Exception{
    	try {
            _prop.load(configFile);
            _baseURL = _prop.getProperty("JasperServerBaseAddress");
            _username = _prop.getProperty("JasperServerUserName");
            _password = _prop.getProperty("JasperServerPassword");
        } catch (Exception e) {
            System.err.println("Không đọc được cấu hình Report Server!");
            e.printStackTrace();
            throw e;
        }
    }
    /*
     * Khởi tạo với các tham số mặc định được load từ properties
     */
    public ReportProvider() throws Exception {
        ClientConfig config = new DefaultClientConfig();
        _client = Client.create(config);
        _prop = new Properties();
        
    }
    
    public void getReport(Report report, String fileExtension, HashMap<String, String> params, HttpServletResponse response) {
        String relativeUrl = "";
        relativeUrl = report.getLink();
        //pdf | xls | cvs
        String fileName = String.format("%s/%s.%s?", _baseURL, relativeUrl, fileExtension);
        if (params != null) {
            for (Map.Entry<String, String> item : params.entrySet()) {
                fileName += String.format("&%s=%s", item.getKey(), item.getValue());
            }
        }

        _webResource = _client.resource(fileName);
        try {
            _client.addFilter(new HTTPBasicAuthFilter(_username, _password));
            ClientResponse responseWS = _webResource.accept(MediaType.WILDCARD).get(ClientResponse.class);
            if (responseWS.getStatus() != 200) {
                System.out.println(responseWS.toString());
                throw new RuntimeException("Failed : HTTP error code : " + responseWS.getStatus());
            }
            InputStream inputStreamFromWS = responseWS.getEntity(InputStream.class);
            
            String contentType = String.format("%s/%s", "application", fileExtension);
            String filenameToUser = String.format("%s.%s", report.getName(), fileExtension);
            response.setHeader("Content-type", contentType);
            response.setHeader("Content-disposition", "attachment; filename=\"" + filenameToUser + "\"");
            OutputStream outputStream = response.getOutputStream();
            
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStreamFromWS.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            inputStreamFromWS.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Report> getReportList(InputStream mappingFilePath) throws JDOMException, IOException{
    	List<Report> listReport = new ArrayList<Report>();
    	SAXBuilder builder = new SAXBuilder();
		
		Document document = (Document) builder.build(mappingFilePath);
		Element rootNode = document.getRootElement();
		List<Element> listElement = rootNode.getChildren("report");
		for(Element reportNode: listElement){
			String reportName = reportNode.getChildText("name");
			String reportDisplayName = reportNode.getChildText("display-name");
			String reportLink = reportNode.getChildText("link");
			Report report = new Report();
			report.setName(reportName);
			report.setLink(reportLink);
			report.setDisplayName(reportDisplayName);
			listReport.add(report);
			System.out.println(reportName);
			System.out.println(reportLink);
			System.out.println(reportDisplayName);
		}
    	
    	return listReport;
    }
    public Report getReportByName(String name, InputStream mappingFilePath) 
    		throws JDOMException, IOException{
    	SAXBuilder builder = new SAXBuilder();
		Document document = (Document) builder.build(mappingFilePath);
		Element rootNode = document.getRootElement();
		List<Element> listElement = rootNode.getChildren("report");
		for(Element reportNode: listElement){
			String reportName = reportNode.getChildText("name");
			if(reportName.equals(name)){
				String reportDisplayName = reportNode.getChildText("display-name");
				String reportLink = reportNode.getChildText("link");
				Report report = new Report();
				report.setName(reportName);
				report.setLink(reportLink);
				report.setDisplayName(reportDisplayName);
				return report;
			}
		}
    	return null;
    }
    public String getBaseURL() {
        return _baseURL;
    }

    public void setBaseURL(String _baseURL) {
        this._baseURL = _baseURL;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }
}
