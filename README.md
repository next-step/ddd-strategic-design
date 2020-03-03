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
| 상품 | Product | 메뉴를 구성하는 1개 이상의 요소를 의미하고, 각각의 가격을 가지고 있다. |
| 메뉴 그룹 | Menu Group | 여러 메뉴들을 구분짓는 범주를 의미한다. 하나의 메뉴 그룹에는 복수의 메뉴가 존재할 수 있다. |
| 메뉴 | Menu | 주문의 대상이 되는 요소를 의미한다. 메뉴는 복수의 상품으로 구성될 수 있고, 상품 가격과는 별개로 메뉴의 가격을 가지고 있다. |
| 테이블 | Order Table | 주문 등록을 요청하는 주체. 빈 테이블 여부, 방문한 손님 수, 단체 지정 정보로 구성되어 있다. |
| 단체 | Table Group | 두개 이상의 테이블을 묶을 수 있다. |
| 주문 | Order | 빈 테이블이 방문한 손님들로 채워진 이후 등록될 수 있는 요소를 의미. 주문 상태, 복수의 메뉴, 테이블 정보를 가진다 |
| 주문 상태 | Order Status | 주문이 등록되고부터 일련의 절차를 수행할 때마다 가지게 되는 상태를 의미한다. '조리', '식사', '계산 완료' 상태로 표기할 수 있다. |
| '조리' 상태 | Order Status 'Cooking' | 주문이 등록된 직후의 상태를 의미 |
| '식사' 상태 | Order Status 'Meal' | '조리' 상태의 주문이 처리되어 메뉴가 제공된 상태를 의미 |
| '계산 완료' 상태 | Order Status 'Completion' | '식사' 상태가 완료된 후의 상태를 의미, '계산 완료' 상태에서 테이블, 단체 지정 정보를 모두 초기화 할 수 있다. |
| 방문한 손님 수 | Number Of Guest | 테이블에 착석한 손님의 수를 의미 |

## 모델링

### Product
* 사용자는 Product 를 등록한다.
    * Product 는 price 정보를 필수로 가지며, price 는 0원 이상이 입력되어야 한다.
* 사용자는 Product 의 목록을 조회한다.
    * Product 목록의 각 요소는 name 과 price 를 포함한다.

### Menu Group
* 사용자는 MenuGroup 을 등록한다.
    * MenuGroup 등록 시, name 이 입력되어야 한다.
* 사용자는 MenuGroup 의 목록을 조회한다.
    * MenuGroup 의 각 요소는 name 을 포함한다.

### Menu
* 사용자는 Menu 를 등록한다.
    * Menu 를 등록할 때는 적어도 1개 이상의 Product 가 포함되어야 한다.
    * Menu 는 price 정보를 필수로 가지며, price 는 0원 이상이 입력되어야 한다.
    * Menu 의 price 는 Menu 를 구성하는 모든 Product 의 price 를 합한 것보다 높지 않게 입력되어야 한다.
    * Menu 를 등록할 때, MenuGroup 이 함께 선택되어야 한다.
* 사용자는 Menu 목록을 조회한다.
    * Menu 목록의 각 요소는 name, price, MenuGroup 의 ID 정보를 포함한다.
    * Menu 목록의 각 요소는 Menu 를 구성하는 Product 의 ID와 개수(quantity) 정보를 포함한다.

### Table Group
* 사용자는 TableGroup 을 등록한다.
    * TableGroup은 2개 이상의 empty 상태의 OrderTable이 포함되어야 한다.
        * 다른 TableGroup에 포함된 OrderTable을 지정할 수 없어야 한다.
        * OrderTable에서 주문한 Order의 OrderStatus가 COMPLETION 인 경우에만 TableGroup으로 지정할 수 있다.
* 사용자는 TableGroup 을 해지한다.

### Table 
* 사용자는 OrderTable 을 등록한다.
* 사용자는 OrderTable 의 목록을 조회한다.
    * OrderTable 목록의 각 요소는 empty 여부, number of guest, table group ID 정보를 가진다.
* 사용자는 OrderTable 의 상태를 변경한다.
    * OrderTable 의 empty 여부를 변경할 수 있다.
    * OrderTable 의 number of guest 를 변경할 수 있다.
        * number of guest 는 0 명 이상 입력되어야 한다.
    * OrderTable 이 empty 상태인 경우 number of guest 는 입력할 수 없다.

### Order
* 사용자는 Order 를 등록한다.
    * 1개 이상의 Menu, empty 상태가 아닌 OrderTable 의 정보가 포함된다.
* 사용자는 Order 목록을 조회한다.
* 사용자는 Order 의 OrderStatus 를 변경한다.
    * OrderStatus 의 종류는 COOKING, MEAL, COMPLETION 으로 구분된다.
    * OrderStatus 가 COMPLETION 인 경우는 OrderStatus 를 변경할 수 없어야 한다.
