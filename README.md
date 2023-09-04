# 키친포스

## 퀵 스타트

```sh
cd docker
docker compose -p kitchenpos up -d
```

## 요구 사항

### 상품

- 상품을 등록할 수 있다.
- 상품의 가격이 올바르지 않으면 등록할 수 없다.
    - 상품의 가격은 0원 이상이어야 한다.
- 상품의 이름이 올바르지 않으면 등록할 수 없다.
    - 상품의 이름에는 비속어가 포함될 수 없다.
- 상품의 가격을 변경할 수 있다.
- 상품의 가격이 올바르지 않으면 변경할 수 없다.
    - 상품의 가격은 0원 이상이어야 한다.
- 상품의 가격이 변경될 때 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 크면 메뉴가 숨겨진다.
- 상품의 목록을 조회할 수 있다.

### 메뉴 그룹

- 메뉴 그룹을 등록할 수 있다.
- 메뉴 그룹의 이름이 올바르지 않으면 등록할 수 없다.
    - 메뉴 그룹의 이름은 비워 둘 수 없다.
- 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴

- 1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.
- 상품이 없으면 등록할 수 없다.
- 메뉴에 속한 상품의 수량은 0 이상이어야 한다.
- 메뉴의 가격이 올바르지 않으면 등록할 수 없다.
    - 메뉴의 가격은 0원 이상이어야 한다.
- 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
- 메뉴는 특정 메뉴 그룹에 속해야 한다.
- 메뉴의 이름이 올바르지 않으면 등록할 수 없다.
    - 메뉴의 이름에는 비속어가 포함될 수 없다.
- 메뉴의 가격을 변경할 수 있다.
- 메뉴의 가격이 올바르지 않으면 변경할 수 없다.
    - 메뉴의 가격은 0원 이상이어야 한다.
- 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
- 메뉴를 노출할 수 있다.
- 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우 메뉴를 노출할 수 없다.
- 메뉴를 숨길 수 있다.
- 메뉴의 목록을 조회할 수 있다.

### 주문 테이블

- 주문 테이블을 등록할 수 있다.
- 주문 테이블의 이름이 올바르지 않으면 등록할 수 없다.
    - 주문 테이블의 이름은 비워 둘 수 없다.
- 빈 테이블을 해지할 수 있다.
- 빈 테이블로 설정할 수 있다.
- 완료되지 않은 주문이 있는 주문 테이블은 빈 테이블로 설정할 수 없다.
- 방문한 손님 수를 변경할 수 있다.
- 방문한 손님 수가 올바르지 않으면 변경할 수 없다.
    - 방문한 손님 수는 0 이상이어야 한다.
- 빈 테이블은 방문한 손님 수를 변경할 수 없다.
- 주문 테이블의 목록을 조회할 수 있다.

### 주문

- 1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다.
- 1개 이상의 등록된 메뉴로 포장 주문을 등록할 수 있다.
- 1개 이상의 등록된 메뉴로 매장 주문을 등록할 수 있다.
- 주문 유형이 올바르지 않으면 등록할 수 없다.
- 메뉴가 없으면 등록할 수 없다.
- 매장 주문은 주문 항목의 수량이 0 미만일 수 있다.
- 매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.
- 배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.
    - 배달 주소는 비워 둘 수 없다.
- 빈 테이블에는 매장 주문을 등록할 수 없다.
- 숨겨진 메뉴는 주문할 수 없다.
- 주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.
- 주문을 접수한다.
- 접수 대기 중인 주문만 접수할 수 있다.
- 배달 주문을 접수되면 배달 대행사를 호출한다.
- 주문을 서빙한다.
- 접수된 주문만 서빙할 수 있다.
- 주문을 배달한다.
- 배달 주문만 배달할 수 있다.
- 서빙된 주문만 배달할 수 있다.
- 주문을 배달 완료한다.
- 배달 중인 주문만 배달 완료할 수 있다.
- 주문을 완료한다.
- 배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.
- 포장 및 매장 주문의 경우 서빙된 주문만 완료할 수 있다.
- 주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.
- 완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.
- 주문 목록을 조회할 수 있다.

## 용어 사전

### 공통

| 한글명 | 영문명    | 설명   |
| ------ | --------- | ------ |
| 비속어 | profanity | 비속어 |

### 상품


| 한글명      | 영문명        | 설명                     |
| ----------- | ------------- | ------------------------ |
| 상품        | product       | 메뉴에 등록할 상품       |
| 상품 식별자 | product id    | 상품 식별자              |
| 상품 가격   | product price | 상품의 가격 (0원 이상)   |
| 상품 이름   | product name  | 상품의 이름(비속어 불가) |
| 상품 목록   | product list  | 상품의 목록              |

### 메뉴 그룹


| 한글명           | 영문명          | 설명             |
| ---------------- | --------------- | ---------------- |
| 메뉴 그룹        | menu group      | 등록된 메뉴 모음 |
| 메뉴 그룹 식별자 | menu group id   | 메뉴 그룹 식별자 |
| 메뉴 그룹 이름   | menu group name | 메뉴 그룹의 이름 |
| 메뉴 그룹 목록   | menu group list | 메뉴 그룹의 목록 |

### 메뉴


| 한글명         | 영문명                | 설명                                             |
| -------------- | --------------------- | ------------------------------------------------ |
| 메뉴           | menu                  | 메뉴 그룹에 속하고 상품을 1개 이상 포함하는 메뉴 |
| 메뉴 식별자    | menu id               | 메뉴 식별자                                      |
| 메뉴 가격      | menu price            | 메뉴의 가격 (0원 이상)                           |
| 메뉴 구성 상품 | menu product          | 메뉴를 구성하는 상품                             |
| 메뉴 상품 수량 | menu product quantity | 메뉴에 등록된 상품의 수량                        |
| 전시 여부      | displayed             | 메뉴의 전시 여부                                 |
| 전시           | display               | 고객이 메뉴를 볼 수 있게 전시 여부를 변경        |
| 숨김           | hide                  | 고객이 메뉴를 볼 수 없게 전시 여부를 변경        |
| 메뉴 이름      | menu name             | 메뉴의 이름(비속어 불가)                         |
| 메뉴 목록      | menu list             | 메뉴의 목록                                      |

### 주문 테이블


| 한글명             | 영문명                 | 설명                                |
| ------------------ | ---------------------- | ----------------------------------- |
| 주문 테이블        | order table            | 매장 주문을 할 고객이 사용할 테이블 |
| 주문 테이블 식별자 | order table id         | 주문 테이블 식별자                  |
| 주문 테이블 이름   | order table name       | 주문 테이블 이름                    |
| 테이블 사용 여부   | occupied               | 고객의 테이블 사용 여부             |
| 사용 테이블        | occupied order table   | 사용 테이블                         |
| 미사용 테이블      | unoccupied order table | 미사용 테이블                       |
| 앉음               | sit                    | 고객이 테이블에 앉음                |
| 비움               | clear                  | 고객이 테이블을 비움                |
| 고객수             | number of guests       | 테이블을 사용중인 고객수            |
| 주문 테이블 목록   | order table list       | 주문 테이블의 목록                  |

### 주문

### 주문 공통


| 한글명      | 영문명           | 설명                           |
| ----------- | ---------------- | ------------------------------ |
| 주문 식별자 | order id         | 주문 식별자                    |
| 주문 타입   | order type       | 주문 타입(배달,포장,매장 주문) |
| 주문 상태   | order status     | 주문 진행 상태                 |
| 주문 메뉴   | order line       | 주문한 메뉴                    |
| 주문 가격   | order line price | 주문한 메뉴 가격                |
| 주문 메뉴 수량   | order line quantity | 주문한 메뉴 수량                |
| 주문 목록   | order list       | 주문 목록                      |

### 배달 주문


| 한글명         | 영문명           | 설명                                                                                 |
| -------------- | ---------------- | ------------------------------------------------------------------------------------ |
| 배달 주문      | delivery order   | 배달 주문(주문 타입)                                                                 |
| 배달 기사      | delivery rider   | 주문 배달 기사                                                                       |
| 배달 주소      | delivery address | 주문 배달 주소                                                                       |
| 접수 중        | waiting          | 배달 주문 접수를 기다리는 상태(주문 진행 상태)                                       |
| 접수 승인      | accept           | 배달 주문 접수 승인 완료 된 상태(주문 진행 상태)                                     |
| 메뉴 제공      | serve            | 매장에서 배달 기사에게 배달 주문 메뉴 제공 완료 상태(주문 진행 상태)                 |
| 배달중         | delivering       | 배달 주문 메뉴 제공이 완료 되고 배달기사 배치 후 주문 배달 중인 상태(주문 진행 상태) |
| 배달 완료      | delivered        | 배달이 완료 된 상태(주문 진행 상태)                                                  |
| 배달 주문 완료 | complete         | 배달 주문 완료 상태(주문 진행 상태)                                                  |

### 포장 주문

| 한글명         | 영문명   | 설명                                                            |
| -------------- | -------- | --------------------------------------------------------------- |
| 포장 주문      | takeout order  | 포장 주문(주문 타입)                                            |
| 접수 중        | waiting  | 포장 주문 접수를 기다리는 상태(주문 진행 상태)                  |
| 접수 승인      | accept   | 포장 주문 접수 승인 완료 된 상태(주문 진행 상태)                |
| 메뉴 제공      | serve    | 매장에서 고객에게 포장 주문 메뉴 제공 완료 상태(주문 진행 상태) |
| 포장 주문 완료 | complete | 포장 주문 완료 상태(주문 진행 상태)                             |

### 매장 주문

| 한글명         | 영문명   | 설명                                                            |
| -------------- | -------- | --------------------------------------------------------------- |
| 매장 주문      | eat in order   | 매장 주문(주문 타입)                                            |
| 접수 중        | waiting  | 매장 주문 접수를 기다리는 상태(주문 진행 상태)                  |
| 접수 승인      | accept   | 매장 주문 접수 승인 완료 된 상태(주문 진행 상태)                |
| 메뉴 제공      | serve    | 매장에서 고객에게 매장 주문 메뉴 제공 완료 상태(주문 진행 상태) |
| 매장 주문 완료 | complete | 매장 주문 완료 상태(주문 진행 상태)                             |

## 모델링

### 상품(product)

#### 속성

- product 는 product id, product name, product price 를 가진다.
- 상품 정책
  - product name 은 profanity 를 포함할 수 없다.
  - product price 는 0원 이상이어야 한다.

#### 행위

- product 는 상품 정책에 따라 등록할 수 있다.
- product price 는 변경할 수 있다.
  - product price 를 변경할 때, 메뉴 가격 정책에 따라 menu 를 숨길 수 있다.
- product list 를 조회할 수 있다.

### 메뉴 그룹(menu group)

#### 속성

- menu group 은 menu group id, menu group name 을 가진다.
- 메뉴 그룹 정책
  - menu group name 은 비워둘 수 없다.

#### 행위

- menu group 은 메뉴 그룹 정책에 따라 등록할 수 있다.
- menu group list 를 조회할 수 있다.

### 메뉴(menu)

#### 속성

- menu 는 menu id menu name, menu price, menu product, menu product quantity, displayed 를 가진다.
- 메뉴 정책
  - menu name 은 profanity 를 포함할 수 없다.
  - menu price 는 0원 이상이어야 한다.
  - menu product 는 1개 이상 등록해야 한다.
    - menu product 가 없으면 menu 를 등록할 수 없다.
  - menu 는 특정 menu group 에 속해야 한다.
- 메뉴 가격 정책
  - menu 가격이 menu product 의 총합보다 클 수 없다.

#### 행위

- menu 는 메뉴 정책에 따라 등록할 수 있다.
- menu 는 메뉴 가격 정책에 따라 가격을 변경할 수 있다.
- menu 는 메뉴 가격 정책을 만족할 때, display 할 수 있다.
- menu 를 hide 할 수 있다.
- menu list 를 조회할 수 있다.

### 주문 테이블(order table)

#### 속성

- order table 은 order table id, order table name, occupied, number of guests 를 가진다.
- 주문 테이블 정책
  - order table name 은 비워둘 수 없다.
  - order table 을 clear 하기 위해서, order status 가 complete 이여야 한다.
  - order table 의 number of guests 는 0명 이상 이여야 한다.
  - order table 의 number of guests 를 변경하기 위해서, unoccupied order table 이여야 한다.

#### 행위

- order table 은 주문 테이블 정책에 따라 등록할 수 있다.
- order table 에는 고객이 sit 할 수 있다.
- 주문 테이블 정책에 따라, order table 에 있는 고객을 clear 할 수 있다.
- 주문 테이블 정책에 따라, order table 에 있는 number of guests 를 변경할 수 있다.
- order table list 를 조회할 수 있다.

### 주문(order) 공통

#### 속성

- order 는 order id, order type, order status, order line, order line price 를 가진다.
- order 는 order type 이 올바르지 않으면 등록할 수 없다.
- order 는 order line 이 없으면 등록할 수 없다.
- 숨겨진 메뉴를 order 할 수 없다.
- order line price 는 실제 menu price 와 일치해야 한다.

#### 행위

- order list 를 조회할 수 있다.

### 배달 주문(delivery order)

#### 속성

- delivery order 는 order type 이 delivery 이다.
- delivery order 의 order status 에는 waiting, accept, serve, delivering, delivered, complete 가 있다.
- 배달 주문 정책
  - delivery address 가 올바르지 않으면 delivery order 를 등록할 수 없다.
  - delivery order 의 order line quantity 는 0 이상 이어야 한다.
  - delivery order 는 order status 가 waiting 에서 accept 로 변할 때, delivery rider 를 호출한다.

#### 행위

- delivery order 는 배달 주문 정책에 따라 접수(order status: waiting) 할 수 있다.
- delivery order 는 배달 주문 정책에 따라 접수 승인(order status: accept) 할 수 있다.
- delivery order 는 메뉴 제공(order status: serve) 할 수 있다.
- delivery order 는 배달중 처리(order status: delivering) 할 수 있다.
- delivery order 는 배달완료 처리(order status: delivering) 할 수 있다.
- delivery order 는 배달 주문 완료(order status: complete) 할 수 있다.

### 포장 주문(takeout order)

#### 속성

- takeout order 는 order type 이 takeout 이다.
- takeout order 의 order status 에는 waiting, accept, serve, complete 가 있다.
- 포장 주문 정책
  - takeout order 의 order line quantity 는 0 이상 이어야 한다.

#### 행위
- takeout order 는 포장 주문 정책에 따라 접수(order status: waiting) 할 수 있다.
- takeout order 는 접수 승인(order status: accept) 할 수 있다.
- takeout order 는 메뉴 제공(order status: serve) 할 수 있다.
- takeout order 는 포장 주문 완료(order status: complete) 할 수 있다.

### 매장 주문(eat in order)

#### 속성

- eat in order 는 order type 이 eat in 이다.
- eat in order 의 order status 에는 waiting, accept, serve, complete 가 있다.
- 매장 주문 정책
  - eat in order 의 order line quantity 는 0 미만 일 수 있다. (주문한 메뉴를 취소할 수 있다.)

#### 행위
- eat in order 는 매장 주문 정책에 따라 접수(order status: waiting) 할 수 있다.
- eat in order 는 접수 승인(order status: accept) 할 수 있다.
- eat in order 는 메뉴 제공(order status: serve) 할 수 있다.
- eat in order 는 매장 주문 완료(order status: complete) 할 수 있다.

#### 주문 테이블(order table)

##### 속성

- order table 은 order table id, order table name, occupied, number of guests 를 가진다.
- 주문 테이블 정책
  - order table name 은 비워둘 수 없다.
  - order table 을 clear 하기 위해서, order stat[](https://)us 가 complete 이여야 한다.
  - order table 의 number of guests 는 0명 이상 이여야 한다.
  - order table 의 number of guests 를 변경하기 위해서, unoccupied order table 이여야 한다.

##### 행위

- order table 은 주문 테이블 정책에 따라 등록할 수 있다.
- order table 에는 고객이 sit 할 수 있다.
- 주문 테이블 정책에 따라, order table 에 있는 고객을 clear 할 수 있다.
- 주문 테이블 정책에 따라, order table 에 있는 number of guests 를 변경할 수 있다.
- order table list 를 조회할 수 있다.
