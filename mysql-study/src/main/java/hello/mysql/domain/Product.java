package hello.mysql.domain;

public class Product {
	
	private int goodsno;
	private String price;
	private String discount;
	private String lastpay;
	
	public Product(int goodsno, String price, String discount, String lastpay) {
		super();
		this.goodsno = goodsno;
		this.price = price;
		this.discount = discount;
		this.lastpay = lastpay;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
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
