package hello.mysql.domain;

public class Company {//aton_order_goods���� companyno�� groupby�ϸ� �ǳ�?
	private int companyno;
	private String companynm;
	
	public Company(int companyno, String companynm) {
		super();
		this.companyno = companyno;
		this.companynm = companynm;
	}
	public int getCompanyno() {
		return companyno;
	}
	public void setCompanyno(int companyno) {
		this.companyno = companyno;
	}
	public String getCompanynm() {
		return companynm;
	}
	public void setCompanynm(String companynm) {
		this.companynm = companynm;
	}
	
	
}
