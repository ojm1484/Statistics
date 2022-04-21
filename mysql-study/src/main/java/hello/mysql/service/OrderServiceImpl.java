package hello.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.mysql.domain.Company;
import hello.mysql.domain.Fees;
import hello.mysql.domain.Month;
import hello.mysql.domain.Order;
import hello.mysql.domain.Product;
import hello.mysql.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderRepository orderRepository; 
	
	@Autowired
	OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<Order> selectAll(String start, String end, String companyno) {
		List<Order> list = orderRepository.selectAll(start, end, companyno);
		return list;
	}
	@Override
	public List<Month> selectMonth(String year, String month) {
		List<Month> monthList = orderRepository.selectMonth(year, month);
		return monthList;
	}
	@Override
	public List<Fees> selectCompany(String year, String month, String companynm) {
		List<Fees> FeesList = orderRepository.selectCompany(year, month, companynm);
		return FeesList;
	}
	@Override
	public List<Product> selectProduct(String year, String month, String companynm) {
		List<Product> productList = orderRepository.selectProduct(year, month, companynm);
		return productList;
	}

	@Override
	public List<Company> selectAllCompany() {
		return orderRepository.selectAllCompany();
	}


}
