package hello.mysql.domain;

public class Company {//aton_order_goods에서 companyno를 groupby하면 되나?
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
