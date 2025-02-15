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
---
## 상품
| 한글명 |     영문명     | 설명                                           |
|:----|:-----------:|:---------------------------------------------|
| 상품  |   product   | 키친포스에서 손님에게 제공되는 개별적인 상품. ex) 기본짬뽕/해물짬뽕/차돌짬뽕 |
| 가격  |    price    | 손님이 해당 상품을 주문하게 되었을때 1개의 상품에 대한 가격.          |
| 이름  | productName | 손님에게 제공되는 음식의 이름.                            |
## 메뉴
| 한글명       |      영문명      | 설명                                |
|:----------|:-------------:|:----------------------------------|
| 금액        |    amount     | 가격 * 수량                           |
| 메뉴        |     menu      | 손님이 주문할 수 있는 상품들의 조합. 주문의 단위.     |
| 메뉴 그룹     |   menuGroup   | 메뉴가 분리되어 묶여지는 그룹 ex) 메인메뉴/세트메뉴/음료 |
| 메뉴에 속한 상품 |  menuProduct  | 특정 메뉴에 포함된 상품 목록. 상품과 수량을 갖는다.    |
| 메뉴 표시 여부  |   displayed   | 손님이 해당 메뉴를 선택할수 있는지 없는지에 대한 구분값.  |
| 메뉴 이름     |   menuName    | 손님이 선택할수 있는 메뉴의 이름.               |
| 메뉴그룹 이름   | menuGroupName | 메뉴가 분류되는 그룹의 이름.  |
## 매장 주문
| 한글명        |        영문명         | 설명                                                 |
|:-----------|:------------------:|:---------------------------------------------------|
| 매장 주문      |     eatInOrder     | 매장에서 상품을 테이블에서 먹고 가는 형태의 주문                        |
| 손님수        |   numberOfGuest    | 식사를 하려는 손님의 수. 최소 0명이상                             |
| 빈 테이블      |  emptyOrderTable   | 주문을 등록할수 없는 테이블.                                   |
| 주문 상태      |    orderStatus     | 주문이 현재 어떤 단계에 있는지를 나타내는 상태 값.                      |
| 접수 대기 중 상태 |      waiting       | 주문이 생성되어 매장에 주문이 들어온 단계.                           |
| 접수된 상태     |      accepted      | 주문을 수락하여 상품을 준비하는 단계.                              |
| 서빙된 상태     |       served       | 매장손님에게 음식을 제공하여 손님이 음식을 먹을수 있는 단계.                 |
| 완료된 상태     |     completed      | 고객이 식사를 마치고 주문이 완료된 단계.                            |
| 주문 항목      |   orderLineItem    | 하나의 주문에 포함된 개별 메뉴 목록. 각 항목은 메뉴와 수량을 포함한다.          |
| 주문 테이블     |     orderTable     | 매장에서 식사를 위해 사용되는 테이블. 매장 내 주문 시 반드시 테이블이 지정되어야 한다. |
| 주문 테이블 이름  |   orderTableName   | 주문 테이블의 이름.                                   |
## 배달 주문
| 한글명        |      영문명      | 설명                                      |
|:-----------|:-------------:|:----------------------------------------|
| 배달 주문      | deliveryOrder | 손님이 배달대행사를 통해 상품을 전달 받는 형태의 주문.         |
| 배달 대행사     |  riderClient  | 배달 주문 시 상품을 손님에게 전달하는 업체 또는 배달 서비스 제공자. |
| 주문 상태      |  orderStatus  | 주문이 현재 어떤 단계에 있는지를 나타내는 상태 값.           |
| 접수 대기 중 상태 |    waiting    | 주문이 생성되어 매장에 주문이 들어온 단계.                |
| 접수된 상태     |   accepted    | 주문을 수락하여 상품을 준비하는 단계.                   |
| 서빙된 상태     |    served     | 음식이 완성되어 배달부가 가져갈수 있는 단계.               |
| 배달중 상태     |  delivering   | 배달부가 음식을 갖고 손님에게 가져다 주고 있는 단계.          |
| 배달 완료된 상태  |   delivered   | 배달 주문이 손님에게 도착하여 수령된 단계.                |
| 완료된 상태     |   completed   | 배달이 완료된 단계.                             |
## 포장 주문
| 한글명        |     영문명      | 설명                                      |
|:-----------|:------------:|:----------------------------------------|
| 포장주문       | takeoutOrder | 손님이 상품을 매장에서 받지만 받은 상품을 외부로 가져가는 형태의 주문 |
| 주문 상태      | orderStatus  | 주문이 현재 어떤 단계에 있는지를 나타내는 상태 값.           |
| 접수 대기 중 상태 |   waiting    | 주문이 생성되어 매장에 주문이 들어온 단계.                |
| 접수된 상태     |   accepted   | 주문을 수락하여 상품을 준비하는 단계.                   |
| 서빙된 상태     |    served    | 포장이 완료되어 손님이 가져갈수 있는 단계.                |
| 완료된 상태     |  completed   | 포장주문이 완료된 단계.                           |

## 모델링

### 상품

- `product`는 등록할 수 있다.
- `product`의 `price`는 올바르지 않으면 등록할 수 없다.
  - `product`의 `price` 0원 이상이어야 한다.
- `product`의 `productName`이 올바르지 않으면 등록할 수 없다.
  - `product`의 `productName`에는 비속어가 포함될 수 없다.
- `product`의 `price`을 변경할 수 있다.
- `product`의 `price`이 올바르지 않으면 변경할 수 없다.
  - `product`의 `price` 0원 이상이어야 한다.
- `product`의 `price`이 변경될 때 `menu`의 `amount`는 `menuProduct`의 `price`의 합보다 크면 `menu`의 `displayed` 가 `false` 가 된다.
- `product`의 목록을 조회할 수 있다.

### 메뉴 그룹

- `menuGroup`을 등록할 수 있다.
- `menuGroup`의 `menuGroupName`이 올바르지 않으면 등록할 수 없다.
  - `menuGroup`의 `menuGroupName`은 비워 둘 수 없다.
- `menuGroup`의 목록을 조회할 수 있다.

### 메뉴

- 1 개 이상의 등록된 `product`으로 `menu`를 등록할 수 있다.
- `product`이 없으면 등록할 수 없다.
- `menuProduct`의 수량은 0 이상이어야 한다.
- `menu`의 `amount`가 올바르지 않으면 등록할 수 없다.
  - `menu`의 `amount`는 0원 이상이어야 한다.
- `menuProduct`의 `price`의 합은 `menu`의 `amount`보다 크거나 같아야 한다.
- `menu`는 특정 `menuGroup`에 속해야 한다.
- `menu`의 `menuName`이 올바르지 않으면 등록할 수 없다.
  - `menu`의 `menuName`에는 비속어가 포함될 수 없다.
- `menu`의 `amount`는 변경할 수 있다.
- `menu`의 `amount`가 올바르지 않으면 변경할 수 없다.
  - `menu`의 `amount`는은 0원 이상이어야 한다.
- `menu`의 `displayed`를 `true` 로 변경할 수 있다.
- `menu`의 `amount`가이 `menuProduct`의 `price`의 합보다 높을 경우 `displayed`를 `true` 로 변경할 수 없다.
- `menu`의 `displayed`를 `false` 로 변경할 수 있다.
- `menu`의 목록을 조회할 수 있다.

### 주문 테이블

- `orderTable`을 등록할 수 있다. 
  - `orderTable`의 `orderTableName`이 올바르지 않으면 등록할 수 없다.
- `orderTable.orderTableName`은 비워 둘 수 없다.
- `orderTable`을 `emptyOrderTable`에서 해지할 수 있다.
- `orderTable`을 `emptyOrderTable`로 설정할 수 있다.
- 완료되지 않은 `order`가 있는 `orderTable`은 `emptyOrderTable`로 설정할 수 없다.
- `orderTable.numberOfGuest`를 변경할 수 있다.
- `orderTable.numberOfGuest`가 올바르지 않으면 변경할 수 없다.
  - `orderTable.numberOfGuest`는 0 이상이어야 한다.
- `emptyOrderTable`은 `orderTable.numberOfGuest`를 변경할 수 없다.
- `orderTable`의 목록을 조회할 수 있다.

### 주문

- 1개 이상의 등록된 `menu`로 `deliveryOrder`를 등록할 수 있다.
- 1개 이상의 등록된 `menu`로 `takeoutOrder`를 등록할 수 있다.
- 1개 이상의 등록된 `menu`로 `eatInOrder`를 등록할 수 있다.
- `order.orderType`이 올바르지 않으면 등록할 수 없다.
- `order.orderLineItem`이 없으면 등록할 수 없다.
- `eatInOrder`의 경우 `order.orderLineItem.amount`는 0 미만일 수 있다.
- `eatInOrder`을 제외한 `order의` 경우 `order.orderLineItem.amount`는 0 이상이어야 한다.
- `deliveryOrder`의 경우 `order.deliveryAddress`가 올바르지 않으면 등록할 수 없다.
  - `order.deliveryAddress`는 비워 둘 수 없다.
- `emptyOrderTable`에는 `eatInOrder`를 등록할 수 없다.
- `displayed == false`인 `menu`는 `order`할 수 없다.
- `order.orderLineItem.price`는 `menu.price`와 일치해야 한다.
- `order`를 `accepted`로 변경할 수 있다.
- `orderStatus == waiting`인 `order`만 `accepted`로 변경할 수 있다.
- `deliveryOrder`가 `accepted` 로 변경되면 `riderClient` 를 호출한다.
- `order` 를 `served` 로 변경할 수 있다.
- `orderStatus == accepted` 인 `order`만  `served` 로 변경할 수 있다.
- `order` 를 `delivering` 로 변경할 수 있다.
- `orderType == deliveryOrder` 인 `order`만  `delivering` 으로 변경할 수 있다.
- `orderStatus == served` 인 `order` 만 `delivering` 으로 변경할 수 있다.
- `order` 를 `delivered` 로 변경할 수 있다.
- `orderStatus == delivering` 인 `order` 만 `delivered` 로 변경할 수 있다.
- `order` 를 `completed` 로 변경할 수 있다.
- `orderType == deliveryOrder` 인 경우 `orderStatus == delivered` 인 `order` 만 `completed` 로 변경할 수 있다.
- `orderType == takeoutOrder` 또는 `orderType == eatInOrder` 인  경우 `orderStatus == served` 인 `order` 만 `completed` 로 변경할 수 있다.
- `orderTable` 에 속한 모든 `eatInOrder` 가 `completed` 되면 `orderTable` 을 `emptyOrderTable` 로 설정한다.
- `completed` 되지 않은 `eatInOrder` 가 있는 경우 `orderTable` 을 `emptyOrderTabl` e로 설정할 수 없다.
- `order` 목록을 조회할 수 있다.
