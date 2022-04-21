package hello.mysql.domain;

public class Month {
	private String companynm;
	private String price;
	private String discount;
	private String lastpay;
	
	public Month(String companynm, String price, String discount, String lastpay) {
		super();
		this.companynm = companynm;
		this.price = price;
		this.discount = discount;
		this.lastpay = lastpay;
	}
	public String getCompanynm() {
		return companynm;
	}
	public void setCompanynm(String companynm) {
		this.companynm = companynm;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getLastpay() {
		return lastpay;
	}
	public void setLastpay(String lastpay) {
		this.lastpay = lastpay;
	}
	
	

}
