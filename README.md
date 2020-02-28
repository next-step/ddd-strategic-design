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
| 상품 | Product | name과 금액을 가진다. |
| 메뉴 | Menu | 이름과 금액을 가지며, 손님이 주문할수 단위이다.  |
| 메뉴 그룹 | MenuGroup | 이름을 가지며, 메뉴의 묶음이다. |
| 메뉴 상품 | MenuProduct | 메뉴의 상품과 수량 정보를 가지고 있다. |
| 주문 테이블 | OrderTable | 손님이 주문하는 테이블이며, NumberOfGuest 와 테이블에 손님이 있는지에 대한 여부(IsOrderTableEmpty)를 확인할 수 있다.|
| 주문 테이블 손님 유무 확인 | IsOrderTableEmpty| 주문 테이블에 손님이 존재하는지에 대한 여부를 확인한다.|
| 단체 지정 | TableGroup | 2개 이상의 테이블을 묶어 단체 지정할수 있다.  |
| 주문 | Order | 손님이 주문한 정보로 주문 테이블, 주문상태, 주문시간 그리고 주문 상품을 알수 있다. |
| 주문 상품 | OrderLineItem | 손님이 주문한 상품 정보이며, 메뉴와 수량을 알수있다. |
| 주문 상태 | OrderStatus | 주문의 상태이며, 주문이 완료 되면 COOKING 상태,  식사 중 상태(MEAL), 계산 완료 상태(COMPLETION) 를 가진다. |
| 손님 | Guest | 주문 테이블에서 메뉴를 주문하는 주최자.  |
| 손님 수 | NumberOfGuest | 주문 테이블에 있는 손님들의 수|

## 모델링

- 상품(Product)은 이름과 가격을 가진다.
    - 상품 가격은 0원 이상이다.

- Menu는 MenuGroup과, MenuProduct의 목록, Name, Price를 가진다.
    - Price 는 0원 이상이다.
    - MenuProduct의 목록의 상품금액의 합이 Price 보다 크거나 같다.
- MenuGroup은 이름과 Menu의 목록을 가진다.
- MenuProduct는 Product과 수량을 가진다.

- 단체 지정은 OrderTable 목록을 가진다.
    - OrderTable의 수가 2개 이상이다.
- 단체 지정을 해지할수 있다.
    - OrderTable의 OrderStatus가 COOKING 또는 MEAL 상태이면 해지할수 없다.

- Order는 주문 테이블 (OrderTable), 주문 상태(OrderStauts), 주문시간, 주문 상품(OrderLineItem) 을 가진다.
    - OrderLineItem 1개 이상이다.
- Order는 주문의 상태를 변경할수 있다.
    - 주문 상태가 계산 완료 상태(COMPLETION) 일땐 변경 불가하다.
- OrderTable은 손님수(NumberOfGuest)와 손님 유무(IsOrderTableEmpty)를 가진다.
