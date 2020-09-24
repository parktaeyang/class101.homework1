package net.class101.homework1;

import java.util.ArrayList;

import net.class101.homework1.model.Goods;
import net.class101.homework1.model.GoodsVO;

public interface OrderSubIf {
	
	public Goods[] listCreate(Object[][] valGoodsList);
	
	public ArrayList<GoodsVO> goodsList(String inputVal, Goods[] goodsListVal);
	
	public String orderGoods(String inputVal);
	
	public String calOrderAmt(ArrayList<GoodsVO> orderListVal);
	
}
