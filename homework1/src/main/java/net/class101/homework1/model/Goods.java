package net.class101.homework1.model;

public class Goods {
	private String goodsCode  = "";
	private String goodsKinds = "";
	private String ClassName  = "";
	private int    salePrice  = 0;
	private int    goodsStock = 0;
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsKinds() {
		return goodsKinds;
	}
	public void setGoodsKinds(String goodsKinds) {
		this.goodsKinds = goodsKinds;
	}
	public String getClassName() {
		return ClassName;
	}
	public void setClassName(String ClassName) {
		this.ClassName = ClassName;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}
	
}
