package net.class101.homework1;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

import net.class101.homework1.exception.SoldOutException;
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
		ArrayList<GoodsVO> returnList = null;
		@Override
		public ArrayList<GoodsVO> orderGoodsList(String inputVal, Goods[] goodsListVal) {
			returnList = new ArrayList<GoodsVO>();
			
			if("O".equals(inputVal.toUpperCase())) {
				this.goodsList = goodsListVal;
				System.out.println("��ǰ��ȣ\t��ǰ��\t�ǸŰ�\t���");
				for(int i=0; i<goodsList.length; i++) {
					System.out.println(goodsList[i].getGoodsCode() + " / " +goodsList[i].getClassName() + 
							" / "+goodsList[i].getSalePrice() + " / "+goodsList[i].getGoodsStock());
				}
				try {
					orderGoods(inputVal);
				} catch (SoldOutException e) {
					e.printStackTrace();
				}
			} else if ("Q".equals(inputVal)) {
				System.out.println("������ �ֹ� �����մϴ�.");
			} else {
				System.out.println("�ùٸ� ���� �Է����ּ���.(o-�ֹ�, q-����)");
			}
			
			return returnList;
		}
		
		Scanner sc = null;
		@Override
		public String orderGoods(String inputVal) throws SoldOutException{
			Goods goods = null;
			GoodsVO returnGoods = null;
			String goodsCode = "";
			String	goodsCount = "";
			boolean existGoods = false;
			sc = new Scanner(System.in);
			
			if("O".equals(inputVal)) {
				
				System.out.print("\n��ǰ��ȣ : ");
				goodsCode = sc.nextLine().toUpperCase();
				
				for(int i=0; i<returnList.size(); i++) {
					if(returnList.get(i).getGoodsCode().equals(goodsCode)) {
						goodsCode = "overLap";
					}
				}
				
				if(" ".equals(goodsCode)) {
					inputVal = "Q";
				} else if("overLap".equals(goodsCode)){
					 System.out.println("�ߺ��Ǵ� ��ǰ��ȣ�Դϴ�. �ٸ���ǰ�� �Է����ּ���.");
				} else if("Q".equals(goodsCode.toUpperCase())){
					 System.out.println("����� �ֹ�����");
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

						System.out.print("\n���� : ");
						goodsCount = sc.nextLine();
						if("Q".equals(goodsCount.toUpperCase())){
							System.out.println("����� �ֹ�����");
							inputVal = "Q";
						} else {
							if (isStringInteager(goodsCount)) {
								
								if(0 > (goods.getGoodsStock()-Integer.parseInt(goodsCount))) {
									throw new SoldOutException("�ش��ǰ�� ��� �����ϴ�.(��ǰ��ȣ : "+goods.getGoodsCode()+")");
								}
								
								returnGoods = new GoodsVO();
								returnGoods.setGoodsCode(goods.getGoodsCode());
								returnGoods.setGoodsKinds(goods.getGoodsKinds());
								returnGoods.setClassName(goods.getClassName());
								returnGoods.setSalePrice(goods.getSalePrice());
								returnGoods.setGoodsStock(goods.getGoodsStock());
								returnGoods.setGoodsCount(Integer.parseInt(goodsCount));
								
								returnList.add(returnGoods);
							} else {
								System.out.println("�ùٸ� ������ �Է����ּ���.");
							}
						}
						
					} else {
						System.out.println("��ǰ��ȣ�� Ȯ�����ּ���.");
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
				return "QUIT";
			}
			
			DecimalFormat df = new DecimalFormat("###,###");
			
			int kitAmt = 0;
			int klassAmt = 0;
			int orderAmt = 0;
			int delyAmt = 0;
			String errGoodsCode = "";
			String returnStr = "";
			
			System.out.println("\n�ֹ����� : ");
			System.out.println("------------------------------------------");
			for(GoodsVO goodsVal : orderListVal) {
				
				if("KIT".equals(goodsVal.getGoodsKinds())) {
					kitAmt += goodsVal.getSalePrice() * goodsVal.getGoodsCount();
				} else if ("KLASS".equals(goodsVal.getGoodsKinds())) {
					klassAmt += goodsVal.getSalePrice() * goodsVal.getGoodsCount();
				} else {
					errGoodsCode += goodsVal.getGoodsCode()+" ";
				}
				
				System.out.println(goodsVal.getClassName() + " - "+goodsVal.getGoodsCount()+"��");
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
				System.out.println("�ֹ����(�����߻�)");
				returnStr = "QUIT";
			} else {
				System.out.println("�ֹ��ݾ� : " + df.format(orderAmt));
				if(delyAmt > 0) {
					System.out.println("��ۺ�	: " + df.format(delyAmt));
				}
				System.out.println("------------------------------------------");
				System.out.println("���ұݾ� : " + df.format(orderAmt+delyAmt));
				System.out.println("------------------------------------------\n");
				returnStr = "O";
			}
			
			return returnStr;
		}

		public Goods[] calGoodsStock(ArrayList<GoodsVO> orderGoodsListVal, Goods[] goodsListVal) {
			
			for(int i=0; i<goodsListVal.length; i++) {
				for(GoodsVO minusStock : orderGoodsListVal) {
					if(goodsListVal[i].getGoodsCode().equals(minusStock.getGoodsCode())) {
						goodsListVal[i].setGoodsStock(goodsListVal[i].getGoodsStock() - minusStock.getGoodsCount());
					}
				}
			}
			
			return goodsListVal;
		}
	}
