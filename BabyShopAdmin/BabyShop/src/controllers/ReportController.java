package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import pojos.Report;
import util.ReportProvider;

@Controller
public class ReportController {
	ReportProvider _reportProvider = null;
	List<Report> _listReport = null;
	Report _report = null;
	
	@RequestMapping(method = GET) 
    protected ModelAndView list( HttpServletRequest arg0,
    		HttpServletResponse arg1) throws Exception
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("report");
        modelAndView.addObject("currentMenu", "reportList");
        
        
        InputStream reportMapping = arg0.getServletContext().getResourceAsStream("/resources/reportsMapping.xml");
		InputStream reportConfig = arg0.getServletContext().getResourceAsStream("/WEB-INF/config/reportServerConfig.properties");
		_reportProvider = new ReportProvider();
		_reportProvider.loadConfig(reportConfig);
		
		_listReport = _reportProvider.getReportList(reportMapping);
		modelAndView.addObject("listReport", _listReport);
		
        
        return modelAndView;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{reportName}", method = GET, params={"type"})
	public void getReport(HttpServletRequest arg0, HttpServletResponse response,
			@PathVariable("reportName") String reportName,
			@RequestParam(value="type", required = true) String type) 
			throws Exception{
		InputStream reportMapping = arg0.getServletContext().getResourceAsStream("/resources/reportsMapping.xml");
		HashMap<String, String> params = new HashMap();
        params.put("page", "1");
        params.put("soluong", "10");
        
		_report = new Report();		
		_report = _reportProvider.getReportByName(reportName, reportMapping);
		_reportProvider.getReport(_report, type, params, response);
	}
	
}
