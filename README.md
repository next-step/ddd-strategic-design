# 키친포스

## 요구 사항

### 상품

* 상품을 등록할 수 있다.
* 상품의 가격이 올바르지 않으면 등록할 수 없다.
    * 상품의 가격은 0 원 이상이어야 한다.
* 상품의 목록을 조회할 수 있다.

### 메뉴 그룹

* 메뉴 그룹을 등록할 수 있다.
* 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴

* 1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.
* 메뉴의 가격이 올바르지 않으면 등록할 수 없다.
    * 메뉴의 가격은 0 원 이상이어야 한다.
    * 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
* 메뉴는 특정 메뉴 그룹에 속해야 한다.
* 메뉴의 목록을 조회할 수 있다.

### 테이블

* 테이블을 등록할 수 있다.
* 테이블의 목록을 조회할 수 있다.
* 빈 테이블 설정 또는 해지할 수 있다.
* 단체 지정된 테이블은 빈 테이블 설정 또는 해지할 수 없다.
* 주문 상태가 조리 또는 식사인 테이블은 빈 테이블 설정 또는 해지할 수 없다.
* 방문한 손님 수를 입력할 수 있다.
* 방문한 손님 수가 올바르지 않으면 입력할 수 없다.
    * 방문한 손님 수는 0 명 이상이어야 한다.
* 빈 테이블은 방문한 손님 수를 입력할 수 없다.

### 단체 지정

* 2 개 이상의 빈 테이블을 단체로 지정할 수 있다.
* 단체 지정은 중복될 수 없다.
* 단체 지정을 해지할 수 있다.
* 단체 지정된 테이블의 주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.

### 주문

* 1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.
* 빈 테이블에는 주문을 등록할 수 없다.
* 주문의 목록을 조회할 수 있다.
* 주문 상태를 변경할 수 있다.
* 주문 상태가 계산 완료인 경우 변경할 수 없다.

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
|메뉴|Menu|'후라이드 치킨', '양념치킨' 과 같이 매장에서 판매가능한 음식.|
|메뉴그룹|MenuGroup|'신메뉴', '한마리 메뉴' 와 같이 메뉴를 카테고리화 한 것.|
|상품|Product|메뉴를 구성하는 실물로, '후라이드 치킨'과 '양념 치킨' 로 구성된 '두마리 치킨 A 세트 메뉴' 가 있다면 '후라이드 치킨'과 '양념 치킨' 각각을 상품이라 한다.|
|주문|Order|테이블에 앉은 손님들이 메뉴를 요리해 달라고 요청하는 행위를 주문이라 한다.|
|테이블|Table|식당안에 있는 손님이 앉을 수 있는 테이블을 의미한다.|
|단체|TableGroup|단체 손님이 와서 여러 테이블을 묶은 것을 단체라 한다.|
|조리|Cooking|주문을 받아 요리를 하고 있는 상태를 뜻한다.|
|식사|Meal|주문한 메뉴를 손님이 먹고 있는 상태를 뜻한다.|
|완료|Complete|손님이 주문된 모든 음식을 먹고 계산을 한 상태를 뜻한다.|
## 모델링
