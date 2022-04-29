package hello.mysql.domain;

public class Company {//aton_order_goods에서 companyno를 groupby하면 되나?
	private int companyno;
	private String companynm;
	private String feerate;
	public Company() {}
	public Company(int companyno, String companynm, String feerate) {
		super();
		this.companyno = companyno;
		this.companynm = companynm;
		this.feerate = feerate;
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
	public String getFeerate() {
		return feerate;
	}
	public void setFeerate(String feerate) {
		this.feerate = feerate;
	}
	@Override
	public String toString() {
		return "Company [companyno=" + companyno + ", companynm=" + companynm + ", feerate=" + feerate + "]";
	}
	
	
	
}
