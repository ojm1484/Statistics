package hello.mysql.domain;

public class Order {
	private String orddt;
	private String price;
	private String discount;
	private String lastpay;
	
	public Order(String orddt, String price, String discount, String lastpay) {
		super();
		this.orddt = orddt;
		this.price = price;
		this.discount = discount;
		this.lastpay = lastpay;
	}
	public String getOrddt() {
		return orddt;
	}
	public void setOrddt(String orddt) {
		this.orddt = orddt;
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
