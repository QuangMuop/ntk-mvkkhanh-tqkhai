package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;


public class StringUtil {
	public static List<String> FindMatches(String str, String strPattern)
	{
		Pattern regex = Pattern.compile(strPattern);
		Matcher m = regex.matcher(str);
        List<String> listMatches = new ArrayList<String>();

        while(m.find())
        {
            listMatches.add(m.group(1));
        }
        
        return listMatches;
	}
	
	public static String RemoveString(String str, String strNeedToRemove)
	{
		String result = str.replace(strNeedToRemove, "");
		return result;
	}
}
