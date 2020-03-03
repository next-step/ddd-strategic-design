# 키친포스

## 요구 사항

### 상품

* 상품을 등록할 수 있다.
* 상품의 가격이 올바르지 않으면 등록할 수 없다.
    * 상품의 가격은 0 원 이상이어야 한다.
* 상품의 목록을 조회할 수 있다.

### 메뉴 그룹 (카테고)

* 메뉴 그룹을 등록할 수 있다.
* 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴

* 1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.
* 메뉴의 가격이 올바르지 않으면 등록할 수 없다.
    * 메뉴의 가격은 0 원 이상이어야 한다.
    * 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
* 메뉴는 특정 메뉴 그룹에 속해야 한다.
* 메뉴의 목록을 조회할 수 있다.

### 주문 테이블

* 주문 테이블을 등록할 수 있다.
* 주문 테이블의 목록을 조회할 수 있다.
* 빈 주문 테이블 설정 또는 해지할 수 있다.
* 단체 지정된 주문 테이블은 빈 주문 테이블 설정 또는 해지할 수 없다.
* 주문 상태가 조리 또는 식사인 주문 테이블은 빈 테이블 설정 또는 해지할 수 없다.
* 방문한 손님 수를 입력할 수 있다.
* 방문한 손님 수가 올바르지 않으면 입력할 수 없다.
    * 방문한 손님 수는 0 명 이상이어야 한다.
* 빈 주문 테이블은 방문한 손님 수를 입력할 수 없다.

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
| 상품  | product | 매장에서 판매 취급을 하는 상품 |
| 메뉴 | menu | 1개 이상의 상품으로 고객에게 노출되는 상품들 |
| 메뉴 그룹 | menu group | 고객이 메뉴를 보기 쉽게 특징으로 구분지어 놓은 카테고리 |
| 주문 테이블 | order table | 메뉴를 시킬 수 있는 자리, 자리에 앉아서 메뉴를 시킬 수 있다. |
| 빈 주문 테이블 | order table empty | 고객이 없는 테이블, 고객을 받아 들일 수 있는 자리 |
| 주문 | order | 메뉴를 시킬 수가 있다. |
| 주문 상태 | order status | 메뉴를 시켜 고객이 가질 수 있는 상태 (요리중, 식사, 완료) |
| 주문 상태 요리 | COOKING | 테이블에 앉아 메뉴에 대한 고객의 상태 , 요리중 |
| 주문 상태 식사 | MEAL | 테이블에 앉아 메뉴에 대한 고객의 상태 , 식사 |
| 주문 상태 완료 | COMPLETION | 테이블에 앉아 메뉴에 대한 고객의 상태 , 완료 |
| 단체 지정 | table group | 고객 수가 많아 2 개 이상의 주문 테이블에 앉을 수 있을 때 단체 지정이라고 한다. |

## 모델링

Products 에서 판매할 Product 를 만든다.  
Products 에서 Product 를 조회한다.  

MenuGroups 를 통해 MenuGroup 을 만든다.
MenuGroups 은 Menu 로 정보를 제공한다.

MenuProducts 는 MenuProduct 를 만든다.
MenuProducts 는 Product 의 정보를 제공한다.
MenuProducts 의 Price 는 MenuProduct 의 Product 가격의 합보다 작은 값을 가진다.

Menus 는 Menu 를 만든다.
Menus 는 MenuGroup 의 정보를 받는다(가져온다[Down Stream]).
Menus 는 상품의 정보를 표현하기 위해 MenuProducts 들을 가진다.
Menu 의 가격은 MenuProducts 의 가격보다 작은 값을 가진다.

OrderTables 는 OrderTable 을 만든다.
OrderTables 은 OrderTable 자리의 유무를 가진다.
OrderTables 은 TableGroup 의 정보를 받는다.

TableGroups 는 OrderTables 의 OrderTable 을 수정할 수 있다.
TableGroup 은 여러개의 테이블의 정보를 알 수 있도록 OrderTables 가진다.

Orders 에서 Order 를 만든다.
Orders 는 손님이 앉을 수 있는 OrderTable 정보를 받는다.
Orders 는 주문한 상품 정보를 알기위해 OrderLineItems 를 가진다.
Orders 는 현재 주문 상태를 구별할 수 있는 OrderStatus 를 가진다.

OrderLineItems 는 주문을 구별할 수 있는 OrderLineItem 을 만든다.
OrderLineItems 은 Menu 의 정보를 받는다.
OrderLineItems 은 Order 의 정보를 받는다.
