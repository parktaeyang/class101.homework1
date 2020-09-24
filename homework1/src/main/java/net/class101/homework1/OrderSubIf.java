package net.class101.homework1;

import java.util.ArrayList;

import net.class101.homework1.exception.SoldOutException;
import net.class101.homework1.model.Goods;
import net.class101.homework1.model.GoodsVO;

public interface OrderSubIf {
	
	public Goods[] listCreate(Object[][] valGoodsList);
	
	public ArrayList<GoodsVO> orderGoodsList(String inputVal, Goods[] goodsListVal);
	
	public String orderGoods(String inputVal) throws SoldOutException;
	
	public String calOrderAmt(ArrayList<GoodsVO> orderListVal);
	
	public Goods[] calGoodsStock(ArrayList<GoodsVO> orderGoodsListVal, Goods[] goodsListVal);
}
