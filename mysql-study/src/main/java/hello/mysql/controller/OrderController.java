package hello.mysql.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.mysql.domain.Company;
import hello.mysql.domain.Fees;
import hello.mysql.domain.Month;
import hello.mysql.domain.Order;
import hello.mysql.domain.Product;
import hello.mysql.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderservice;

	@GetMapping("/")
	public String getCompany(Model model) {
		List<Company> companies = new ArrayList<Company>();
		companies = orderservice.selectAllCompany();
		model.addAttribute("company", companies); 

		return "home";
	}
	
	// home에서 ajax로 넘어오는 컨트롤러
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getOrder(@RequestParam(value = "start") String start,
														@RequestParam(value = "end") String end, 
														@RequestParam(value = "companyno") String companyno, Model model) {

		end = end.concat(" 23:59:59");
		System.out.println(end);
		try {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			List<Order> orderlist = orderservice.selectAll(start, end, companyno);
			int priceSum = 0;
			int discountSum = 0;
			int lastpaySum = 0;
			for (Order order : orderlist) {
				priceSum += Integer.parseInt(order.getPrice().replace(",", ""));
				discountSum += Integer.parseInt(order.getDiscount().replace(",", ""));
				lastpaySum += Integer.parseInt(order.getLastpay().replace(",", ""));
			}
			resultMap.put("order", orderlist);
			resultMap.put("priceSum", priceSum);
			resultMap.put("discountSum", discountSum);
			resultMap.put("lastpaySum", lastpaySum);
			
			return resultMap;
			
		} catch (Exception e) {
			System.out.println("getOrder : " + e);
		}

		return null;
	}
	
	@GetMapping("/two/home")
	public String getMonth(Model model) {

		return "two/home";
	}
	
	//two/home에서 ajax로 넘어오는 컨트롤러
	@RequestMapping(value = "/two/home2", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getList(@RequestParam(value = "year", required = false) String year,
														@RequestParam(value = "month", required = false) String month, Model model) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		String lastday = Integer.toString(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String firstday = Integer.toString(calendar.getActualMinimum(Calendar.DATE));
		String year1 = year;
		String str = "0";
		
		firstday = str + firstday;

		if (month.length() == 1) {
			month = "-" + str + month + "-";
		} else if (month.length() != 1) {
			month = "-" + month + "-";
		}

		year = year.concat(month) + firstday;
		month = year1 + month.concat(lastday);
		month = month.concat(" 23:59:59");
		
		try {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			List<Month> monthList = orderservice.selectMonth(year, month);

			int priceSum = 0;
			int discountSum = 0;
			int lastpaySum = 0;
			for (Month mon : monthList) {
				priceSum += Integer.parseInt(mon.getPrice().replace(",", ""));
				discountSum += Integer.parseInt(mon.getDiscount().replace(",", ""));
				lastpaySum += Integer.parseInt(mon.getLastpay().replace(",", ""));
			}

			resultMap.put("monthList", monthList);
			resultMap.put("priceSum", priceSum);
			resultMap.put("discountSum", discountSum);
			resultMap.put("lastpaySum", lastpaySum);

			return resultMap;
		} catch (Exception e) {
			System.out.println("getMonth : " + e);
		}

		return null;
	}
	//팝업창 컨트롤러
	@RequestMapping(value = "/three/home3", method = RequestMethod.GET)
	public String getPop(@RequestParam(value = "year", required = false) String year,
						@RequestParam(value = "month", required = false) String month,
						@RequestParam(value = "companynm", required = false) String companynm, Model model) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);
		String lastday = Integer.toString(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String firstday = Integer.toString(calendar.getActualMinimum(Calendar.DATE));
		
		String year1 = year;
		String month1 = "";
		String str = "0";

		firstday = str + firstday;

		if (month.length() == 1) {
			month = "-" + str + month + "-";
		} else if (month.length() != 1) {
			month = "-" + month + "-";
		}

		year = year.concat(month) + firstday;
		month = year1 + month.concat(lastday);
		month = month.concat(" 23:59:59");
		month1 = year.substring(0, 7);
		System.out.println("year = " + year);
		System.out.println("month = " + month);
		System.out.println("month1 = " + month1);

		List<Fees> FeesList = orderservice.selectCompany(year, month, companynm);

		model.addAttribute("feeList", FeesList);
		model.addAttribute("yearMonth", month1);
		model.addAttribute("companynm", companynm);

		List<Product> productList = orderservice.selectProduct(year, month, companynm);

		int priceSum = 0;
		int discountSum = 0;
		int lastpaySum = 0;
		for (Product pro : productList) {
			priceSum += Integer.parseInt(pro.getPrice().replace(",", ""));
			discountSum += Integer.parseInt(pro.getDiscount().replace(",", ""));
			lastpaySum += Integer.parseInt(pro.getLastpay().replace(",", ""));
		}

		System.out.println(priceSum);
		model.addAttribute("productList", productList);
		model.addAttribute("priceSum", priceSum);
		model.addAttribute("discountSum", discountSum);
		model.addAttribute("lastpaySum", lastpaySum);

		return "three/home3";
	}
}