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
| 상품 | Product | 메뉴에 들어갈 상품을 나타낸다. | 
| 메뉴 그룹 | MenuGroup | 메뉴들을 묶어서 표현한다. ex. 추천메뉴, 점심메뉴  | 
| 메뉴 | Menu | 메뉴를 나타낸다. 이름과 가격으로 구성되고, 메뉴그룹, 상품들로 연관관계를 갖는다. | 
| 메뉴 상품 | MenuProduct | 메뉴와 상품의 관계를 나타낸다. | 
| 테이블 | Table | 오프라인 매장에서 운영중인 테이블을 나타낸다. 테이블의 인원수와 빈 자리인지 여부를 나타 낸다. | 
| 단체 지정 | TableGroup | 2개 이상의 빈 테이블을 묶는 것을 나타낸다. | 
| 주문 | Order | 상품으로 등록된 메뉴에서 주문을 나타낸다. 주문 상태 값이 존재한다. |
| 주문테이블 | OrderTable | 주문 테이블에 대한 인원수, 비어있음, 단체지정과 같은 정보를 담고있다.  |
| 주문 아이템 | OrderLineItem | 주문에 따른 아이템 정보를 나타낸다. |
| 주문상태 | OrderStatus | 주문상태는 COOKING, MEAL, COMPLETION 으로 구성된다. |

## 모델링

### Product 
- Product는 매장에서 판매하는 상품을 나타낸다.
- Product는 이름과 가격을 나타낸다.
- Product를 등록한다.
    - Product의 가격이 0원 이상이어야 한다.
- Product의 목록을 조회한다.

### MenuGroup 
- MenuGroup은 Menu들을 묶을 수 있다.
- MenuGroup은 이름을 가진다.
- MenuGroup을 등록한다.
- MenuGroup의 목록을 조회한다.

### MenuProduct
- MenuProduct는 순차정보, 메뉴Id, 상품Id, 수량에 대한 정보를 나타낸다.

### Menu
- Menu는 이름, 가격, 메뉴그룹Id, 메뉴상품들로 나타낸다.
- Menu를 등록한다.
    - Menu의 가격은 0원 이상이어야 한다.
    - Menu는 MenuGroupId 값이 존재해야 한다.
    - Menu의 속한 상품 금액의 합은 Menu의 가격보다 크거나 같아야 한다.
- Menu의 목록을 조회한다.

### OrderTable
- Table은 매장내의 테이블을 의미한다.
- OrderTable은 테이블 그룹Id, 인원수, 비움 상태를 나타낸다.
- OrderTable을 등록한다.
- OrderTable의 목록을 조회한다.
- OrderTable의 상태를 비움/채움 으로 변경한다.
    - 변경 대상이 되는 OrderTable이 존재하지 않으면 OrderTable의 상태를 변경 할 수 없다.
    - 변경 대상이 되는 OrderTable의 TableGroup으로 매핑 되어있으면 상태 변경 할 수 없다.
    - 변경 대상이 되는 OrderTable의 OrderStatus가 COOKING 이거나 MEAL 이면 상태를 변경 할 수 없다.
- OrderTable의 인원수를 입력한다.
    - 인원수가 0명 이상이어야 한다.
    - OrderTable이 존재해야 한다.
    - OrderTable의 비어있으면 인원수를 등록할 수 없다.
    
### TableGroup
- TableGroup은 단체 손님을 지정하고 해지 한다.
- TableGroup은 주문한 테이블들, 지정한 시간으로 나타낸다.
- TableGroup은 단체지정을 한다.
    - 단체지정을 할 OrderTable이 2개 이상이어야 한다.
    - TableGroup의 OrderTable의 크기(파라미터로 넘긴 값과)와 실제 OrderTable의 크기는 같아야 한다.
    - 단체지정을 할 OrderTable이 비어있지 않거나, TableGroup으로 매핑되어 있으면 단체 지정 할 수 없다.
- TableGroup은 단체지정을 해지한다.
    - 해지 대상이 되는 OrderTable의 OrderStatus가 `COOKING` 이거나 `MEAL`이면 해지 할 수 없다.  
    
### Order
- Order는 Menu를 통해서 주문 정보를 나타낸다.
- Order는 주문테이블Id, 주문 상태, 주문 시간, 주문한 아이템 정보를 가진다.
- Order를 등록한다.
    - OrderLineItem은 1개 이상이어야 한다.
    - OrderLineItem 개수(파라미터로 넘긴 값)와 실제 Menu의 개수와 일치 해야 한다.
    - OrderTable이 존재 해야 한다.
- Order의 목록을 조회한다.
- Order의 상태를 변경한다.
    - 변경 대상이 되는 Order가 존재 해야 한다.
    - 변경 대상이 되는 Order가 `COMPLETION`이면 변경 할 수 없다.

### OrderLineItem 
- OrderLineItem은 Order와 Menu사이의 관계를 나타낸다.
- OrderLineItem은 순차, 주문Id, 메뉴Id, 수량을 나타낸다.

### OrderStatus
- OrderStatus는 `요리중(COOKING)`, `식사중(MEAL)`, `식사완료(COMPLETION)` 와 같이 구성된다.

![model](https://user-images.githubusercontent.com/28615416/74082848-761ca400-4aa1-11ea-809f-2dcbf016bbd7.png)
