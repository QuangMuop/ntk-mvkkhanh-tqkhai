package dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import pojos.QuangCao;
import util.HibernateUtil;

public class QuangCaoDAO {
	/*
	public static List<QuangCaoOld> layDanhSachQuangCao(InputStream mappingFilePath) throws JDOMException, IOException {
		List<QuangCaoOld> listQuangCao = new ArrayList<QuangCaoOld>();
    	SAXBuilder builder = new SAXBuilder();
		
		Document document = (Document) builder.build(mappingFilePath);
		Element rootNode = document.getRootElement();
		List<Element> listElement = rootNode.getChildren("advertisement");
		for(Element adNode: listElement){
			String adPosition = adNode.getChildText("position");
			String adLink = adNode.getChildText("link");
			String adExpiredDate = adNode.getChildText("expired-date");
			String adShow = adNode.getChildText("show");
			QuangCaoOld quangCao = new QuangCaoOld();
			quangCao.setPosition(adPosition);
			quangCao.setLink(adLink);
			quangCao.setExpiredDate(DateUtil.convertStringToDate(adExpiredDate, "yyyy-MM-dd"));
			quangCao.setIsShow(Boolean.parseBoolean(adShow));		
			listQuangCao.add(quangCao);
		}
    	
    	return listQuangCao;
    }
	
	public static QuangCaoOld layThongTinQuangCao(InputStream mappingFilePath, String position) throws JDOMException, IOException
	{
		QuangCaoOld quangCao = new QuangCaoOld();
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
	
	public static Boolean capNhatQuangCao(InputStream mappingFilePath, String outputURL, String position, QuangCaoOld quangCao) throws JDOMException, IOException
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
	*/
	@SuppressWarnings("unchecked")
	public static List<QuangCao> layDanhSachQuangCao() {
        List<QuangCao> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql="select qc from QuangCao qc order by qc.maQuangCao asc";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
	
	public static QuangCao layThongTinQuangCao(Long maQuangCao) {
        QuangCao quangCao = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	quangCao = (QuangCao) session.get(QuangCao.class, maQuangCao);
        } catch (HibernateException ex) {
        //Log the exception
        System.err.println(ex);
        } finally {
        session.close();
        }
        return quangCao;
    }
	
	public static boolean capNhatQuangCao(QuangCao qc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(qc);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
