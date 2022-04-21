package hello.mysql.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hello.mysql.domain.Company;
import hello.mysql.domain.Fees;
import hello.mysql.domain.Month;
import hello.mysql.domain.Order;
import hello.mysql.domain.Product;

public interface OrderService {
	public List<Order> selectAll(@Param("start") String start,
							@Param("end") String end,
							@Param("companyno") String companyno);
	
	public List<Month> selectMonth(@Param("year") String year,
							@Param("month") String month);
	
	public List<Fees> selectCompany(@Param("year") String year,
									@Param("month") String month,
									@Param("companynm") String companynm);
	public List<Product> selectProduct(@Param("year") String year,
									@Param("month") String month,
									@Param("companynm") String companynm);
	public List<Company> selectAllCompany();
}
