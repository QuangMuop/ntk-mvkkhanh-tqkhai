package controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojos.DoChoi;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DoChoiDAO;


@Controller
public class DashboardController {
	@RequestMapping(method = GET) 
    protected ModelAndView welcome( HttpServletRequest arg0,
    		HttpServletResponse arg1) throws Exception
    {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
        modelAndView.addObject("currentMenu", "dashboard");
        
        return modelAndView;
    }
	
	@RequestMapping(method = RequestMethod.POST, params = { "chartName"}, value="/chart", produces = "application/json") 
    protected void chart(
    		@RequestParam(value = "chartName", required = true) String chartName,
    		HttpServletRequest arg0,
    		HttpServletResponse arg1) throws JsonParseException, IOException
    {
		//Top 10 đồ chơi bán chạy nhất
		if(chartName.equalsIgnoreCase("TopTenSellingToys"))
		{
			ObjectMapper mapper = new ObjectMapper();
	        List<DoChoi> top10BestSellToys= DoChoiDAO.topNDoChoiBanChayNhat(10);
	        List<Map<String, Object>> listChartData = new ArrayList<Map<String, Object>>();
	        for(int i = 0; i < top10BestSellToys.size(); i++)
	        {
	        	DoChoi doChoi = top10BestSellToys.get(i);
	        	Map<String,Object> chartData = new HashMap<String,Object>(); 
	        	chartData.put("toyName", doChoi.getTenDoChoi());
	        	chartData.put("quantity", doChoi.getSoLuongDaBan());
	        	listChartData.add(chartData);        
	        }
	        //Chuyển Json thành chuỗi
	        String returnString = mapper.writeValueAsString(listChartData);
	        //Dùng HttpServletResponse để write String đó ra để được chuỗi trả về có dấu
	        arg1.getWriter().write(returnString);
		}
		else if (chartName.equalsIgnoreCase("ToysSoldPerDay"))
		{
			ObjectMapper mapper = new ObjectMapper();
			List<Date> listNgay = new ArrayList<Date>();
			List<Long> listSoLuong = new ArrayList<Long>();
			DoChoiDAO.demSoLuongDoChoiBanRaTrongNNgay(listNgay, listSoLuong, 10);
			List<Map<String, Object>> listChartData = new ArrayList<Map<String, Object>>();
	        for(int i = 0; i < listNgay.size(); i++)
	        {
	        	Date ngay = listNgay.get(i);
	        	Long soLuong = listSoLuong.get(i);
	        	Map<String,Object> chartData = new HashMap<String,Object>(); 
	        	chartData.put("date", ngay);
	        	chartData.put("quantity", soLuong);
	        	listChartData.add(chartData);        
	        }
	      //Chuyển Json thành chuỗi
	        String returnString = mapper.writeValueAsString(listChartData);
	        //Dùng HttpServletResponse để write String đó ra để được chuỗi trả về có dấu
	        arg1.getWriter().write(returnString);
		}
    }
	
}
