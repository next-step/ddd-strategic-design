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

- 1개 이상의 등록된 메뉴로 배달주문을 등록할 수 있다.
- 1개 이상의 등록된 메뉴로 포장주문을 등록할 수 있다.
- 1개 이상의 등록된 메뉴로 매장주문을 등록할 수 있다.
- 주문 유형이 올바르지 않으면 등록할 수 없다.
- 메뉴가 없으면 등록할 수 없다.
- 매장주문은 주문 항목의 수량이 0 미만일 수 있다.
- 매장주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.
- 배달주소가 올바르지 않으면 배달주문을 등록할 수 없다.
    - 배달주소는 비워 둘 수 없다.
- 빈 테이블에는 매장주문을 등록할 수 없다.
- 숨겨진 메뉴는 주문할 수 없다.
- 주문한 메뉴의 가격은 실제 메뉴가격과 일치해야 한다.
- 주문을 접수한다.
- 접수 대기 중인 주문만 접수할 수 있다.
- 배달주문을 접수되면 배달 대행사를 호출한다.
- 주문을 서빙한다.
- 접수된 주문만 서빙할 수 있다.
- 주문을 배달한다.
- 배달주문만 배달할 수 있다.
- 서빙된 주문만 배달할 수 있다.
- 주문을 배달 완료한다.
- 배달 중인 주문만 배달 완료할 수 있다.
- 주문을 완료한다.
- 배달주문의 경우 배달 완료된 주문만 완료할 수 있다.
- 포장 및 매장주문의 경우 서빙된 주문만 완료할 수 있다.
- 주문 테이블의 모든 매장주문이 완료되면 빈 테이블로 설정한다.
- 완료되지 않은 매장주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.
- 주문 목록을 조회할 수 있다.

## 용어 사전

### 음식(Product)

| 한글명   | 영문명           | 설명                         |
|-------|---------------|----------------------------|
| 음식    | product       | 음식 ex) 양념치킨, 간장치킨          |
| 음식 가격 | product price | 음식 가격. 음식 가격은 0원 이상이어야 한다. |
| 음식 이름 | product name  | 음식의 이름. 비속어가 포함될 수 없다.     |
| 비속어   | profanity     | 비속한, 불경스러운 말               |
| 음식 목록 | product list  | 등록된 음식 전체 목록               |

### 메뉴 그룹(Menu Group)

| 한글명      | 영문명             | 설명                                   |
|----------|-----------------|--------------------------------------|
| 메뉴 그룹    | menu group      | 메뉴를 성격에 따라 분류하여 묶은 것 ex) 추천메뉴, 두마리메뉴 |
| 메뉴 그룹 이름 | menu group name | 메뉴 그룹 이름. 공백이면 안 된다.                 |
|          |                 |                                      |

### 메뉴(Menu)

| 한글명      | 영문명            | 설명                                                                 |
|----------|----------------|--------------------------------------------------------------------|
| 메뉴       | menu           | 하나 이상의 음식을 묶는 단위. 메뉴는 메뉴그룹에 속해야 한다. 구매는 메뉴 단위로 해야한다. ex) 후라이드+후라이드 |
| 메뉴 이름    | menu name      | 메뉴의 이름. 비속어가 포함될 수 없다.                                             |
| 메뉴 가격    | menu price     | 메뉴의 가격. 0원 이상이어야 한다. <br/>메뉴에 속한 음식 가격의 합보다 작거나 같아야 한다.            |
| 메뉴 구성 음식 | menu product   | 메뉴를 구성하는 음식.                                                       |
| 메뉴 노출 상태 | menu displayed | 메뉴의 노출 상태                                                          |
| 노출하기     | display        | 메뉴를 노출하는 행위.                                                       |
| 숨기기      | hide           | 메뉴를 숨기는 행위.                                                        |
| 비속어      | profanity      | 비속한, 불경스러운 말                                                       |
|          |                |                                                                    |

### 매장테이블(Restaurant Table)

| 한글명      | 영문명                         | 설명                                                           |
|----------|-----------------------------|--------------------------------------------------------------|
| 매장테이블    | restaurant table            | 매장 내 식사하는 테이블.                                               |
| 빈 테이블    | unoccupied restaurant table | 사용 중이 아닌 테이블                                                 |
| 사용중 테이블  | occupied restaurant table   | 사용 중인 테이블                                                    |
| 치우기      | clear                       | 매장테이블을 빈 테이블로 만드는 행위. <br/> 완료되지 않은 매장주문이 있는 매장테이블은 치울 수 없다. |
| 손님 수     | number of guests            | 테이블에 방문한 손님 수                                                |
| 매장테이블 이름 | restaurant table name       | 매장테이블의 이름.                                                   |

### 주문(Order)

| 한글명   | 영문명                 | 설명                                                       |
|-------|---------------------|----------------------------------------------------------|
| 주문 메뉴 | order line          | 주문한 메뉴.  1개 이상의 등록된 메뉴만 주문할 수 있다.                        |
| 주문 상태 | order status        | 주문의 진행 상태.                                               |
| 주문 금액 | order line amount   | 주문 메뉴의 금액.주문 금액은 메뉴가격과 같아야 한다.                           |
| 주문 수량 | order line quantity | 주문 메뉴의 수량. 배달주문, 포장주문일 땐 0보다 커야 한다. 매장주문일 땐 0보다 작을 수 있다. |

#### 배달주문(Delivery Order)

| 한글명       | 영문명              | 설명                                      |
|-----------|------------------|-----------------------------------------|
| 배달주문      | delivery order   | 배달하는 주문 유형.                             |
| 배달주문 접수중  | waiting          | 배달주문이 접수되기 전 상태.                        |
| 배달주문 접수완료 | accepted         | 배달주문이 접수된 상태. 접수중만 접수완료로 바꿀 수 있다.       |
| 픽업 완료     | picked up        | 배달 기사가 주문을 픽업한 상태. 접수완료만 픽업완료로 바꿀 수 있다. |
| 배달중       | delivering       | 배달주문이 배달중인 상태. 픽업완료만 배달중으로 바꿀 수 있다.     |
| 배달완료      | delivered        | 배달주문이 배달완료인 상태. 배달중만 배달완료로 바꿀 수 있다.     |
| 배달주문완료    | completed        | 배달주문이 완료된 상태. 배달완료만 배달주문완료로 바꿀 수 있다.    |
| 라이더       | rider            | 배달 기사                                   |
| 배달주소      | delivery address | 배달주문이 배달될 주소.                           |
| 배달금액      | delivery amount  | 배달주문의 총 금액.                             |
| 배달을 요청한다  | request delivery | 라이더에게 배달을 요청한다.                         |
| 배달정보      | delivery info    | 배달주소, 배달금액, 주문 Id로 이뤄진 배달에 필요한 정보.      |

#### 포장주문(Take Out Order)

| 한글명       | 영문명            | 설명                                         |
|-----------|----------------|--------------------------------------------|
| 포장주문      | take out order | 포장하는 주문 유형.                                |
| 포장주문 접수중  | waiting        | 포장주문이 접수되기 전 상태.                           |
| 포장주문 접수완료 | accepted       | 포장주문이 접수된 상태.  점수중만 접수완료로 바꿀 수 있다.         |
| 픽업완료      | picked up      | 포장주문이 고객에게 전달 완료된 상태. 접수완료만 픽업완료로 바꿀 수 있다. |
| 포장주문완료    | completed      | 포장주문이 완료 상태. 픽업완료만 포장주문완료로 바꿀 수 있다.        |

#### 매장주문(Eat In Order)

| 한글명       | 영문명          | 설명                                          |
|-----------|--------------|---------------------------------------------|
| 매장주문      | eat in order | 매장에서 먹는 주문 유형.                              |
| 매장주문 접수중  | waiting      | 매장주문이 접수되기 전 상태.                            |
| 매장주문 접수완료 | accepted     | 매장주문이 접수된 상태. 접수중만 접수완료로 바꿀 수 있다.           |
| 서빙완료      | served       | 매장주문이 매장테이블에 서빙완료된 상태. 접수완료만 서빙완료로 바꿀 수 있다. |
| 매장주문완료    | completed    | 매장주문이 완료 상태. 서빙완료만 매장주문완료로 바꿀 수 있다.         |

## 모델링

### 음식(Product)

* 음식(Product)은 이름(name), 가격(price)를 갖는다.
* 음식(Product)을 등록(create)한다.
    * 음식(Product)의 가격은 0원 이상이어야 한다.
    * 음식(Product)의 이름(name)에는 비속어(profanity)가 포함될 수 없다.
* 음식을 전체조회(findAll)한다.

### 메뉴그룹(MenuGroup)

* 메뉴그룹(MenuGroup)은 이름(name)을 갖는다.
* 메뉴그룹(MenuGroup)을 등록(create)한다.
    * 메뉴그룹(MenuGroup)의 이름은 공백일 수 없다.
* 메뉴그룹(MenuGroup)을 전체조회(findAll)한다.

### 메뉴(Menu)

* 메뉴(Menu)는 가격(price), 이름(name), 메뉴구성음식(MenuProduct), 메뉴 노츌 상태(displayed), 메뉴그룹(MenuGroup)을 갖는다.
    * 메뉴구성음식(MenuProduct) 음식(Product)과 수량(quantity)을 갖는다.
* 메뉴(Menu)를 등록(create)한다.
    * 메뉴(Menu)의 이름(name)에는 비속어(Profanity)가 포함될 수 없다.
    * 등록된 음식(Product)으로만 메뉴를 등록할 수 있다.
    * 메뉴구성음식(MenuProduct)의 수량(quantity)은 0보다 커야 한다.
    * 메뉴(Menu)의 가격(price)은 0원 이상이어야 한다.
    * 메뉴(Menu)의 가격(price)은 메뉴구성음식(MenuProduct) 가격(price)의 합보다 작아야 한다.
    * 등록된 메뉴그룹(MenuGroup)으로 메뉴를 등록할 수 있다.
* 메뉴(Menu)의 가격(price)을 바꾼다(changePrice).
    * 등록된 메뉴(Menu)여야 한다.
    * 메뉴(Menu)의 가격(price)은 0원 이상이어야 한다.
* 메뉴(Menu)를 노출한다(display).
    * 등록된 메뉴(Menu)여야 한다.
    * 메뉴(Menu)의 가격(price)은 메뉴구성음식(MenuProduct) 가격(price)의 합보다 같거나 크면 노출할 수 없다.
* 메뉴(Menu)를 숨긴다(display).
    * 등록된 메뉴(Menu)여야 한다.
* 메뉴(Menu)를 전체조회(findAll)한다.

### 매장테이블(RestaurantTable)

* 매장테이블(RestaurantTable)은 이름(Name)과 손님수(numberOfGuests), 사용여부(occupied)를 갖는다.
* 매장테이블(RestaurantTable)을 등록(create)한다.
    * 매장테이블(RestaurantTable)의 이름(name)은 공백일 수 없다.
    * 빈 테이블로 등록한다.
    * 손님수는 0으로 등록한다.
* 매장테이블(RestaurantTable)을 사용중 테이블로 바꾼다(sit).
    * 등록된 매장테이블이어야 한다.
* 매장테이블(RestaurantTable)을 치운다(clear).
    * 등록된 매장테이블이어야 한다.
    * 매장테이블과 연관된 매장주문(EatInOrder)은 모두 주문완료(COMPLETED) 상태여야 한다.
    * 빈 테이블(unoccupied restaurantTable)로 수정한다.
    * 손님수는 0으로 수정한다.
* 매장테이블(RestaurantTable)의 손님수를 바꾼다(changeNumberOfGuests).
    * 등록된 매장테이블이어야 한다.
    * 손님수는 0 이상이어야 한다.
    * 사용중 테이블(occupied restaurantTable)이어야 한다.
* 매장테이블(RestaurantTable)을 전체조회(findAll)한다.

### 주문금액(OrderLineAmount)

* 주문금액은 0원 이상이어야 한다.
* 주문금액은 메뉴의 가격과 같아야 한다.

### 주문메뉴(OrderLine)

* 주문메뉴는 메뉴(Menu), 주문수량(OrderLineQuantity), 주문금액(OrderLineAmount)을 갖는다.
* 숨겨진 메뉴는 주문메뉴에 포함될 수 없다.

### 주문수량(OrderLineQuantity)

* 매장주문(EatInOrder)일 때 음수일 수 있다
* 배달주문(DeliveryOrder), 포장주문(TakeOutOrder)일 때는 0 이상이어야 한다.

### 매장주문(EatInOrder)

* 매장주문(EatInOrder)은 주문금액(OrderLineAmount), 주문메뉴(OrderLine), 주문수량(OrderLineQuantity), 매장테이블(RestaurantTable), 주문상태(
  OrderStatus)을 갖는다.
    * 주문상태는 매장주문(EatInOrder) 은 매장주문 접수중(WAITING), 매장주문 접수완료(ACCEPTED), 서빙완료(SERVED), 매장주문완료(COMPLETED)가 있다.
* 매장주문(EatInOrder)을 등록(create)한다.
    * 주문메뉴는 등록되고, 노출된 메뉴여야 한다.
    * 주문메뉴는 1개 이상이어야 한다.
    * 주문수량은 음수일 수 있다.
    * 메뉴의 가격과 주문금액은 같아야 한다.
    * 등록된 매장테이블여야 한다. 매장테이블은 사용중 테이블이어야 한다.
    * `매장주문 접수중(WAITING)` 상태로 등록된다.
* 매장주문(EatInOrder)을 접수한다(accept).
    * 등록된 매장주문이어야 한다.
    * 매장주문은 매장주문 접수중(WAITING) 상태여야 한다.
    * 매장주문의 상태를 `매장주문 접수중(WAITING)`로 바꾼다.
* 매장주문을 서빙한다(serve).
    * 등록된 매장주문이어야 한다.
    * 매장주문은 `매장주문 접수완료(ACCEPTED)` 상태여야 한다.
* 매장주문을 완료(complete)한다.
    * 등록된 매장주문이어야 한다.
    * 매장주문은 `서빙완료(ACCEPTED)` 상태여야 한다.
    * 매장주문을 `매장주문완료(ACCEPTED)` 상태로 바꾼다.
    * 매장테이블의 모든 매장주문이 `매장주문완료(ACCEPTED)` 상태라면, 매장테이블을 치운다(clear).

### 포장주문(TakeOutOrder)

* 포장주문(TakeOutOrder)은 포스기에서 등록한다.
* 포장주문(TakeOutOrder)은 주문메뉴(OrderLine) 갖는다.
* 포장주문(TakeOutOrder)은 포장주문 접수중(WAITING), 포장주문 접수완료(ACCEPTED), 픽업완료(PICKEDUP), 포장주문완료(COMPLETED) 상태를 갖는다.
* 포장주문(TakeOutOrder)은 자신의 상태를 변경할 수 있다.

### 배달주문(DeliveryOrder)

* 배달주문(DeliveryOrder)은 포스기에서 등록한다.
* 배달주문(DeliveryOrder)은 주문메뉴(OrderLine) 갖는다.
* 배달주문(DeliveryOrder)은 배달주문 접수중(WAITING), 배달주문 접수완료(ACCEPTED), 픽업완료(PICKEDUP), 배달중(DELIVERING), 배달완료(DEIEVERED), 배달주문완료(
  COMPLETED) 상태를 갖는다.
* 배달주문(DeliveryOrder)은 자신의 상태를 변경할 수 있다.
* 배달주문(DeliveryOrder)은 배달정보를 가지고 라이더(Rider)에게 배달요청을 한다.

#### 배달주소(DeliveryAddress)

* 배달주소(DeliveryAddress)은 공백일 수 없다.

#### 라이더(Rider)

* 라이더(Rider)는 배달정보와 음식를 가지고 배달한다.

#### 배달정보(DeliveryInfo)

* 배달정보(DeliveryInfo)는 주문번호, 배달금액(DeliveryAmount), 배달주소(DeliveryAddress)를 갖는다.

#### 배달금액(DeliveryAmount)

* 배달금액(Delivery)은 주문메뉴금액(OrderLineAmounts)의 합이다.
