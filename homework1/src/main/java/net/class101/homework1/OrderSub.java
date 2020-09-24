package net.class101.homework1;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

import net.class101.homework1.model.Goods;
import net.class101.homework1.model.GoodsVO;

public class OrderSub implements OrderSubIf{
		
		@Override
		public Goods[] listCreate(Object[][] valGoodsList) {
			Goods[] returnList = new Goods[valGoodsList.length];
			Goods valGoods;
			for(int i=0; i<valGoodsList.length; i++) {
				valGoods = new Goods();
				valGoods.setGoodsCode((String)valGoodsList[i][0]);
				valGoods.setGoodsKinds((String)valGoodsList[i][1]);
				valGoods.setClassName((String)valGoodsList[i][2]);
				valGoods.setSalePrice((int)valGoodsList[i][3]);
				valGoods.setGoodsStock((int)valGoodsList[i][4]);
				
				returnList[i] = valGoods;
			}
			
			return returnList;
		}
		
		Goods[] goodsList = null;
		ArrayList<GoodsVO> returnList = new ArrayList<GoodsVO>();
		@Override
		public ArrayList<GoodsVO> goodsList(String inputVal, Goods[] goodsListVal) {
			if("O".equals(inputVal.toUpperCase())) {
				this.goodsList = goodsListVal;
				System.out.println("상품번호\t상품명\t판매가\t재고");
				for(int i=0; i<goodsList.length; i++) {
					System.out.println(goodsList[i].getGoodsCode() + " / " +goodsList[i].getClassName() + 
							" / "+goodsList[i].getSalePrice() + " / "+goodsList[i].getGoodsStock());
				}
				orderGoods(inputVal);
			} else if ("Q".equals(inputVal)) {
				System.out.println("--주문정보--");
			} else {
				System.out.println("올바른 값을 입력해주세요.(o-주문, q-종료)");
			}
			
			return returnList;
		}
		
		Scanner sc = null;
		@Override
		public String orderGoods(String inputVal) {
			Goods goods = null;
			GoodsVO returnGoods = null;
			String goodsCode = "";
			String	goodsCount = "";
			boolean existGoods = false;
			sc = new Scanner(System.in);
			
			if("O".equals(inputVal)) {
				
				System.out.print("\n상품번호 : ");
				goodsCode = sc.nextLine().toUpperCase();
				
				for(int i=0; i<returnList.size(); i++) {
					if(returnList.get(i).getGoodsCode().equals(goodsCode)) {
						goodsCode = "overLap";
					}
				}
				
				if(" ".equals(goodsCode)) {
					inputVal = "Q";
				} else if("overLap".equals(goodsCode)){
					 System.out.println("중복되는 상품번호입니다. 다른상품을 입력해주세요.");
				} else if("Q".equals(goodsCode.toUpperCase())){
					 System.out.println("사용자 주문종료");
					 inputVal = "Q";
				} else {
					for(Goods goodsVal : goodsList) {
						if(goodsVal.getGoodsCode().equals(goodsCode)) {
							goods = goodsVal;
							existGoods = true;
							break;
						}
					}
					
					if(existGoods) {
						existGoods = false;

						System.out.print("\n수량 : ");
						goodsCount = sc.nextLine();
						if("Q".equals(goodsCount.toUpperCase())){
							System.out.println("사용자 주문종료");
							inputVal = "Q";
						} else {
							if (isStringInteager(goodsCount)) {
								returnGoods = new GoodsVO();
								returnGoods.setGoodsCode(goods.getGoodsCode());
								returnGoods.setGoodsKinds(goods.getGoodsKinds());
								returnGoods.setClassName(goods.getClassName());
								returnGoods.setSalePrice(goods.getSalePrice());
								returnGoods.setGoodsStock(goods.getGoodsStock());
								returnGoods.setGoodsCount(Integer.parseInt(goodsCount));
								
								returnList.add(returnGoods);
							} else {
								System.out.println("올바른 수량을 입력해주세요.");
							}
						}
						
					} else {
						System.out.println("상품번호를 확인해주세요.");
					}
				}
			} else {
				return "";
			}
			return orderGoods(inputVal);
		}
		
		public boolean isStringInteager(String countVal) {
			try {
				Integer.parseInt(countVal);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		@Override
		public String calOrderAmt(ArrayList<GoodsVO> orderListVal) {
			
			if(0 == orderListVal.size()) {
				return "";
			}
			
			DecimalFormat df = new DecimalFormat("###,###");
			
			int kitAmt = 0;
			int klassAmt = 0;
			int orderAmt = 0;
			int delyAmt = 0;
			String errGoodsCode = "";
			String returnStr = "";
			
			System.out.println("\n주문내역 : ");
			System.out.println("------------------------------------------");
			for(GoodsVO goodsVal : orderListVal) {
				
				if("KIT".equals(goodsVal.getGoodsKinds())) {
					kitAmt += goodsVal.getSalePrice() * goodsVal.getGoodsCount();
				} else if ("KLASS".equals(goodsVal.getGoodsKinds())) {
					klassAmt += goodsVal.getSalePrice() * goodsVal.getGoodsCount();
				} else {
					errGoodsCode += goodsVal.getGoodsCode()+" ";
				}
				
				System.out.println(goodsVal.getClassName() + " - "+goodsVal.getGoodsCount()+"개");
			}
			System.out.println("------------------------------------------");
			orderAmt = kitAmt + klassAmt;
			if( (kitAmt > 0) && (klassAmt > 0) ) {
				delyAmt = 0;
			} else if ( (kitAmt == 0) && (klassAmt > 0) ) {
				delyAmt = 0;
			} else if ( (kitAmt > 0) && (klassAmt == 0) && (50000 > kitAmt) ) {
				delyAmt = 5000;
			}
			
			if(errGoodsCode.length() > 0) {
				returnStr = "주문취소(에러발생)";
			} else {
				System.out.println("주문금액 : " + df.format(orderAmt));
				if(delyAmt > 0) {
					System.out.println("배송비	: " + df.format(delyAmt));
				}
				System.out.println("------------------------------------------");
				System.out.println("지불금액 : " + df.format(orderAmt+delyAmt));
				System.out.println("------------------------------------------");
				returnStr = "주문완료";
			}
			
			return returnStr;
		}
	}
