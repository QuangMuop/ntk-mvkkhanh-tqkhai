package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import pojos.QuangCao;
import util.DateUtil;

public class QuangCaoDAO {
	public static List<QuangCao> layDanhSachQuangCao(InputStream mappingFilePath) throws JDOMException, IOException {
		List<QuangCao> listQuangCao = new ArrayList<QuangCao>();
    	SAXBuilder builder = new SAXBuilder();
		
		Document document = (Document) builder.build(mappingFilePath);
		Element rootNode = document.getRootElement();
		List<Element> listElement = rootNode.getChildren("advertisement");
		for(Element adNode: listElement){
			String adPosition = adNode.getChildText("position");
			String adLink = adNode.getChildText("link");
			String adExpiredDate = adNode.getChildText("expired-date");
			String adShow = adNode.getChildText("show");
			QuangCao quangCao = new QuangCao();
			quangCao.setPosition(adPosition);
			quangCao.setLink(adLink);
			quangCao.setExpiredDate(DateUtil.convertStringToDate(adExpiredDate, "yyyy-MM-dd"));
			quangCao.setIsShow(Boolean.parseBoolean(adShow));		
			listQuangCao.add(quangCao);
		}
    	
    	return listQuangCao;
    }
	
	public static QuangCao layThongTinQuangCao(InputStream mappingFilePath, String position) throws JDOMException, IOException
	{
		QuangCao quangCao = new QuangCao();
		SAXBuilder builder = new SAXBuilder();
		
		Document document = (Document) builder.build(mappingFilePath);
		Element rootNode = document.getRootElement();
		List<Element> listElement = rootNode.getChildren("advertisement");
		for(Element adNode: listElement)
		{
			String adPosition = adNode.getChildText("position");
			if(adPosition.equals(position))
			{				
				String adLink = adNode.getChildText("link");
				String adExpiredDate = adNode.getChildText("expired-date");
				String adShow = adNode.getChildText("show");
				quangCao.setPosition(adPosition);
				quangCao.setLink(adLink);
				quangCao.setExpiredDate(DateUtil.convertStringToDate(adExpiredDate, "yyyy-MM-dd"));
				quangCao.setIsShow(Boolean.parseBoolean(adShow));
			}
		
		}
		return quangCao;
	}
	
	public static Boolean capNhatQuangCao(InputStream mappingFilePath, String outputURL, String position, QuangCao quangCao) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		
		Document document = (Document) builder.build(mappingFilePath);
		Element rootNode = document.getRootElement();
		List<Element> listElement = rootNode.getChildren("advertisement");
		for(Element adNode: listElement)
		{
			String adPosition = adNode.getChildText("position");
			if(adPosition.equals(position))
			{				
				String adLink = quangCao.getLink();
				String adExpiredDate = DateUtil.convertDateToString(quangCao.getExpiredDate(), "yyyy-MM-dd");
				String adShow = quangCao.getIsShow().toString();
				adNode.getChild("link").setText(adLink);
				adNode.getChild("expired-date").setText(adExpiredDate);
				adNode.getChild("show").setText(adShow);
				XMLOutputter output=new XMLOutputter();
				output.output(document, new FileOutputStream(outputURL));
				return true;
			}
		
		}
		return false;
	}
}
