package net.class101.homework1;

import java.util.ArrayList;
import java.util.Scanner;

import net.class101.homework1.model.Goods;
import net.class101.homework1.model.GoodsVO;

public class OrderMain {

	static Object[][] examGoodsList= new Object[][] {
		{"16374", "KLASS", "����Ʈ������ �� 100���� �����, ����� ����� ���� ����� ���", 151950, 99999},
		{"26825", "KLASS", "�ر�, Ư���ϰ� �Ƹ��ٿ� ������ �ݷ��Ǳ�", 114500, 99999},
		{"65625", "KLASS", "�ϻ� ������ ������ �Ҿ�־��, �������� �Բ��ϴ� �����е� �����", 174500, 99999},
		{"91008", "KIT", "�۰� ���� �׷��� - �δ���� �����ϴ� ��äȭ �̴� ŰƮ", 28000, 10},
		{"9236", "KIT", "�Ϸ��� ���۰� ��, ����� �������� �����ϴ� õ�� ��", 9900, 22},
		{"55527", "KLASS", "�ڹٴ÷� ������ ������! �������� ������ �۾���", 299500, 99999},
		{"2344", "KLASS", "�ϻ� ��Ʃ�� ������ ������ �״��. ��ȭ���� ���̷α� ���۹�", 184500, 99999},
		{"60538", "KIT", "���ۿ� ���� �δ��� ����. ���� ���� �����е� Ư����", 135800, 7},
		{"78156", "KIT", "�ϻ��� �����ϰ� ä��� ��ĳ�Ŀ� �����", 45000, 16},
		{"53144", "KLASS", "���� �����, �޸� ���� ������. �ɵ��Ʈ�� ����, �׸���", 249500, 99999},
		{"78311", "KLASS", "�簢�簢 �ձ۾��� ����, ��ũ����Ʈ�� �ѱ� ����ü ��۾�", 209500, 99999},
		{"97166", "KIT", "�̷��� ���� ��äȭ ŰƮ,���? Ŭ����101�� �����ȭ���� ��ȹ�� 3���� ��äȭ ŰƮ", 96900, 5},
		{"83791", "KLASS", "�������� �������� ���ϴ� �е��� ���� �Ƹ���/�̺��� �Թ�", 178500, 99999},
		{"58395", "KIT", "���� �������� ä��� 2020�� Ķ������ ����", 18620, 31},
		{"39712", "KIT", "������ ������ �ܿ��� ������ ������, �÷��׸��� ������", 17600, 8},
		{"96588", "KLASS", "���� �Թ��ڸ� ���� ���� ���� ���� ��Դ� ������ ����Ʈ��", 234500, 99999},
		{"42031", "KIT", "������ ������ ����� ���� ��� ��Ű��", 209000, 2},
		{"45947", "KLASS", "�Ϸ���Ʈ������ ������ �ŷ����� �� �׸���", 249500, 99999},
		{"74218", "KLASS", "������ ���汸�� ������! �׸������� Ÿ������ ���� �����", 191600, 99999},
		{"28448", "KLASS", "��ŵ� �� �� �ִ�! ���׶� �ǹ��ڰ� �˷��ִ� ��Ǳ׷����� ��� ��", 52200, 99999}
	};
	
	public static void main(String[] args) {
		
		OrderSub orderSub = new OrderSub();
		Goods[] goodsList = orderSub.listCreate(examGoodsList);
		
		String inputVal = "O";
		
		Scanner sc = new Scanner(System.in);
		
		while("O".equals(inputVal)) {
			System.out.print("�Է�(o[order]: �ֹ�, q[quit]: ����): ");
			inputVal = sc.nextLine().toUpperCase();
			ArrayList<GoodsVO> orderGoodsList = orderSub.orderGoodsList(inputVal, goodsList);
			
			String returnStr = orderSub.calOrderAmt(orderGoodsList);
			if("O".equals(returnStr)) {
				goodsList = orderSub.calGoodsStock(orderGoodsList, goodsList);
			}
			if((0 == orderGoodsList.size()) && !(inputVal.equals("Q")) ) {
				inputVal = "O";
			} else {
				inputVal = returnStr;
			}
		}
		
		sc.close();
	}

}
