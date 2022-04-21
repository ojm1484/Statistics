package hello.mysql.domain;

public class Fees {
	private String price;
	private String discount;
	private String lastpay;
	private String fees;
	
	public Fees(String price, String discount, String lastpay, String fees) {
		super();
		this.price = price;
		this.discount = discount;
		this.lastpay = lastpay;
		this.fees = fees;
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
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
}
