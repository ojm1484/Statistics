package hello.mysql.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hello.mysql.domain.Company;
import hello.mysql.domain.CompanyTable;
import hello.mysql.domain.Fees;
import hello.mysql.domain.Month;
import hello.mysql.domain.Order;
import hello.mysql.domain.Product;
import hello.mysql.service.OrderService;


@Controller
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class); //이거 선언해주면 @slf4j를 안해도 되는 것, @slf4j는 lombok이 설치되어있어야 한다.
	
	@Autowired
	private OrderService orderservice;

	@GetMapping("/")
	public String getCompany(Model model) {
		
		String resultCode = "0000";
		
		List<Company> companies = new ArrayList<Company>();
		companies = orderservice.selectAllCompany();
		
		if(companies != null && companies.size()>=0) {
			model.addAttribute("company", companies); 
		}else{
			resultCode = "9999";
		}
		model.addAttribute("resultCode", resultCode);
		
		return "home";
	}
	
	// home에서 ajax로 넘어오는 컨트롤러
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getOrder(@RequestParam(value = "start") String start,
														@RequestParam(value = "end") String end, 
														@RequestParam(value = "companyno") String companyno, Model model) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String resultCode = "0000";
		end = end.concat(" 23:59:59");
		logger.debug("end = {}", end);

		try {
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
			
			if(orderlist != null && orderlist.size()>=0) {
				resultMap.put("order", orderlist);
			}else {
				resultCode = "9999";
			}
			
			model.addAttribute("resultCode", resultCode);
			
			
			
		} catch (Exception e) {
			logger.debug("getOrder = {}",e);
			resultCode = "9999";
			model.addAttribute("resultCode", resultCode);
			return resultMap;
			
		}

		return resultMap;
	}
	
	@GetMapping("/two/home")
	public String getMonth(Model model) {
		String date = orderservice.selectDate();
		logger.debug("date = {}",date);
		
		Map<String, Object> dbDate = new HashMap<String, Object>();
			String year = date.substring(0, 4);
			String month = date.substring(5, 7);
			dbDate.put("year", year);
			dbDate.put("month", month);
		
		model.addAttribute("date", date);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
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
		logger.debug("year = {}",year);
		logger.debug("month = {}",month);
		logger.debug("month1 = {}",month1);

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

		logger.debug("priceSum = {}",priceSum);
		model.addAttribute("productList", productList);
		model.addAttribute("priceSum", priceSum);
		model.addAttribute("discountSum", discountSum);
		model.addAttribute("lastpaySum", lastpaySum);

		return "three/home3";
	}
	
	//권순규프로님 부분 첫 페이지
	@GetMapping("/companyHome")
	public String getCompanyTable(Model model) {
		
		return "insert/companyHome";
	}
	//리스트 보여지는 페이지
	@GetMapping("/companyList")
	public String companyList(Model model) {
		List<CompanyTable> companyTables = orderservice.selectListCompany();
		
		model.addAttribute("companyTables", companyTables);
		return "insert/companyList";
	}
	
	// ajax를 위해 만든 searchList.html
	@GetMapping("/searchList")
	public String companySearchList(Model model) {
		List<CompanyTable> companyTables = orderservice.selectListCompany();
		
		model.addAttribute("companyTables", companyTables);
		return "insert/searchList";
	}
	
	//검색 리스트보여지는 페이지
	@GetMapping("/companySearch")
	public String companySearch(Model model) {
		List<Company> companies = new ArrayList<Company>();
		companies = orderservice.selectAllCompany();
		
		model.addAttribute("company", companies);
		return "insert/companySearch";
	}
	
	//검색 리스트 결과가 ajax로보여지는 페이지
	@PostMapping("/companySearchList")
	public ModelAndView companySearchList(@RequestParam(value = "startday") String start,
										 @RequestParam(value = "endday") String end, 
										 @RequestParam(value="siteName") String siteName,
										 @RequestParam(value="companynm") String companynm,
										 @RequestParam(value="siteStatus") String siteStatus,
										 @RequestParam(value="siteCalculate") String siteCalculate,
										 @RequestParam(value="siteProtocol") String siteProtocol,
										 @RequestParam(value="siteUrlDetail") String siteUrlDetail,
										 Model model) {
		
		ModelAndView mav= new ModelAndView("insert/companySearch");
		String resultCode = "0000";
		
		try {
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("start", start);
			param.put("end", end);
			param.put("siteName", siteName);
			param.put("companynm", companynm);
			param.put("siteStatus", siteStatus);
			param.put("siteCalculate", siteCalculate);
			param.put("siteProtocol", siteProtocol);
			param.put("siteUrlDetail", siteUrlDetail);
			
			List<CompanyTable> companyTables= orderservice.selectSearchCompany(param);
			logger.debug("companyTable = {}",companyTables);
			
			if(companyTables.size()<1) {
				mav.setViewName("insert/nosearch");
			}else {
				mav.addObject("companyTables", companyTables);
				mav.setViewName("insert/searchList");
			}
			
			if(companyTables != null && companyTables.size()>=0) {
				resultCode="0000";
				mav.addObject("companyTables", companyTables);
			}else {
				resultCode = "9999";
			}
			model.addAttribute("resultCode", resultCode);
			
		} catch (Exception e) {
			
			logger.debug("companySearchList = {}",resultCode);
			mav.setViewName("insert/searchList");
			resultCode = "9999";
			model.addAttribute("resultCode", resultCode);
			return mav;
			
		}
		return mav;
	}
	
	//등록폼 연결되는 페이지
	@GetMapping("/registForm")
	public String registForm(Model model) {
		List<Company> companies = new ArrayList<Company>();
		companies = orderservice.selectAllCompany();
		
		model.addAttribute("company", companies);
		return "insert/registForm";
	}
	
	//수정폼 연결되는 페이지
	@GetMapping("/detail")
	public String updateForm(Model model,int siteId) {
		CompanyTable companyTable = orderservice.selectOne(siteId);
		List<Company> companies = new ArrayList<Company>();
		logger.debug("companyTable = {}",companyTable.toString());
		model.addAttribute("companyTable", companyTable);
		model.addAttribute("company", companies);
		return "insert/updateForm";
	}
	
	//신규제휴사 등록, 중복값 검증
	@PostMapping("/regCompany")
	@ResponseBody
	public Map<String, Object> companyRegist(@Param(value="companynm")String companynm, @Param(value="feerate") String feerate,Model model) {
		String resultCode="0000";
		HashMap<String, Object> result2 = new HashMap<String, Object>();
		
		/**
		 * 값이 이미 존재하면 1 / 없으면 0
		 */
		int selectcompanynm = orderservice.selectCompanynm(companynm);
		
		if (selectcompanynm < 0) {		
			int result = orderservice.companyRegist(companynm, feerate);
		
			if(result > 0) {
				logger.debug("resultCode = {}",resultCode);
			}else {
				resultCode = "9999";
				logger.debug("resultCode = {}",resultCode);
			}

		} else {
			resultCode = "5555";
			logger.debug("resultCode = {}",resultCode);
		}
		
		result2.put("resultCode", resultCode);
		
		return result2;
	}
	
	//등록
	@PostMapping("/callRegist")
	@ResponseBody
	public String regist(CompanyTable companyTable, Model model) {
		String resultCode="0000";
		int result = orderservice.companyInsert(companyTable);
		if(result>0) {
			logger.debug("resultCode = {}",resultCode);
		}else {
			resultCode = "9999";
			logger.debug("resultCode = {}",resultCode);
		}
		model.addAttribute("resultCode", resultCode);
		return resultCode;
	}
	
	//수정
	@PostMapping("/updateForm")
	@ResponseBody
	public String update(CompanyTable companyTable,Model model) {
		String resultCode="0000";
		int result = orderservice.companyUpdate(companyTable);
		if(result>0) {
			logger.debug("resultCode = {}",resultCode);
		}else {
			resultCode = "9999";
			logger.debug("resultCode = {}",resultCode);
		}
		return resultCode;
	}
	
	//삭제
	@PostMapping("/delete")
	@ResponseBody
	public String delete(int siteId,Model model) {
		String resultCode="0000";
		int result =orderservice.companyDelete(siteId);
		logger.debug("siteId = {}",siteId);
		if(result>0) {
			logger.debug("resultCode = {}",resultCode);
		}else {
			
			resultCode = "9999";
			logger.debug("resultCode = {}",resultCode);
		}
		return resultCode;
	}
}