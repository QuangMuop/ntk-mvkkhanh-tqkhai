package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date getCurrentDate(String datePattern)
	{
		Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DATE);
        String today = year + "-" + month + "-" + day;
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date currentDate = new Date();
		try {
			currentDate = dateFormat.parse(today);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return currentDate;
	}
	
	public static Date convertStringToDate(String strDate, String datePattern)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		Date date = new Date();
		try {
			date = dateFormat.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String convertDateToString(Date date, String datePattern) {
			SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(datePattern);
			return mySimpleDateFormat.format(date);
	}
}
