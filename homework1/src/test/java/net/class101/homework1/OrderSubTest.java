package net.class101.homework1;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderSubTest {

	Object[][] examGoodsList= new Object[][] {
		{"16374", "KLASS", "이클립스에서 생성한 프로젝트 인텔리제이에서 import했는데 자바파일이 class파일만 인식되는데 왜이러는지 아시는분?", 151950, 99999},
		{"26825", "KLASS", "해금, 특별하고 아름다운 나만의 반려악기", 114500, 99999},
		{"65625", "KLASS", "일상에 따뜻한 숨결을 불어넣어요, 반지수와 함께하는 아이패드 드로잉", 174500, 99999},
		{"91008", "KIT", "작고 쉽게 그려요 - 부담없이 시작하는 수채화 미니 키트", 28000, 10},
		{"9236", "KIT", "하루의 시작과 끝, 욕실의 포근함을 선사하는 천연 비누", 9900, 22},
		{"55527", "KLASS", "코바늘로 인형을 만들자! 시은맘의 꼼지락 작업실", 299500, 99999},
		{"2344", "KLASS", "일상 유튜버 숏뚜의 감성을 그대로. 영화같은 브이로그 제작법", 184500, 99999},
		{"60538", "KIT", "시작에 대한 부담을 덜다. 가격 절약 아이패드 특가전", 135800, 7},
		{"78156", "KIT", "일상을 따뜻하게 채우는 썬캐쳐와 무드등", 45000, 16},
		{"53144", "KLASS", "여행 드로잉, 꿈만 꾸지 마세요. 핀든아트와 여행, 그리다", 249500, 99999},
		{"78311", "KLASS", "사각사각 손글씨의 낭만, 펜크래프트의 한글 정자체 펜글씨", 209500, 99999},
		{"97166", "KIT", "이렇게 멋진 수채화 키트,어때요? 클래스101과 고넹이화방이 기획한 3가지 수채화 키트", 96900, 5},
		{"83791", "KLASS", "월급으로 만족하지 못하는 분들을 위한 아마존/이베이 입문", 178500, 99999},
		{"58395", "KIT", "선과 여백으로 채우는 2020년 캘린더와 엽서", 18620, 31},
		{"39712", "KIT", "집에서 느끼는 겨울의 묵직한 포근함, 플랜테리어 아이템", 17600, 8},
		{"96588", "KLASS", "사진 입문자를 위한 쉽게 배우고 빨리 써먹는 사진과 라이트룸", 234500, 99999},
		{"42031", "KIT", "나만의 음악을 만들기 위한 장비 패키지", 209000, 2},
		{"45947", "KLASS", "일러스트레이터 집시의 매력적인 얼굴 그리기", 249500, 99999},
		{"74218", "KLASS", "나만의 문방구를 차려요! 그리지영의 타블렛으로 굿즈 만들기", 191600, 99999},
		{"28448", "KLASS", "당신도 할 수 있다! 베테랑 실무자가 알려주는 모션그래픽의 모든 것", 52200, 99999}
	};
	
	@Test
	public void test() {
		OrderSub orderSub = new OrderSub();
		assertNotNull(orderSub.listCreate(examGoodsList));
		
		
		assertNotNull(orderSub.orderGoodsList("Q", orderSub.listCreate(examGoodsList)));
		assertNotNull(orderSub.orderGoodsList("ASD", orderSub.listCreate(examGoodsList)));
		
		assertNotNull(orderSub.orderGoodsList("O", orderSub.listCreate(examGoodsList)));
		
		assertNotNull(orderSub.calOrderAmt(orderSub.orderGoodsList("O", orderSub.listCreate(examGoodsList))));
		
	}

}
