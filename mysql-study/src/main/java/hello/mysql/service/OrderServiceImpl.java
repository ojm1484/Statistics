package hello.mysql.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.mysql.domain.Company;
import hello.mysql.domain.CompanyTable;
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

	@Override
	public String selectDate() {
		return orderRepository.selectDate();
	}

	@Override
	public List<CompanyTable> selectListCompany() {
		List<CompanyTable> companyTables = orderRepository.selectListCompany();
		return companyTables;
	}
	//사이트 등록
	@Override
	public int companyInsert(CompanyTable companyTable) {
		return orderRepository.companyInsert(companyTable);
	}
	//수정
	@Override
	public int companyUpdate(CompanyTable companyTable) {
		return orderRepository.companyUpdate(companyTable);
		
	}
	//상세보기
	@Override
	public CompanyTable selectOne(int siteId) {
		return orderRepository.selectOne(siteId);
	}
	//삭제하기
	@Override
	public int companyDelete(int siteId) {
		return orderRepository.companyDelete(siteId);
	}

	//신규제휴사 등록
	@Override
	public int companyRegist(@Param(value="companynm")String companynm, @Param(value="feerate") String feerate) {
		return orderRepository.companyRegist(companynm,feerate);
	}
	//검색해서 리스트 찾기
	@Override
	public List<CompanyTable> selectSearchCompany(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return orderRepository.selectSearchCompany(param);
	}

	@Override
	public int selectCompanynm(String companynm) {
		// TODO Auto-generated method stub
		return orderRepository.selectCompanynm(companynm);
	}
}
