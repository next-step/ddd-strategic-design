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
### 음식(Product)
| 한글명       | 영문명                  | 설명                                                                                                                        |
|-----------|----------------------|---------------------------------------------------------------------------------------------------------------------------|
| 음식        | product              | 음식 ex) 양념치킨, 간장치킨                                                                                                         |
| 음식 가격     | productPrice        | 음식 가격. 음식의 가격은 필수이며 0원 이상이어야 한다.                                                                              |
| 음식 이름     | productName         | 음식의 이름. 음식 이름은 필수이며 비속어가 포함될 수 없다.                                                                            |
| 비속어       | profanity        | 비속어                                                                                                              |


### 메뉴 그룹(MenuGroup)
| 한글명      | 영문명             | 설명                       |
|----------|-----------------|--------------------------|
| 메뉴 그룹    | menuGroup      | 메뉴를 묶은 것 ex) 추천메뉴, 두마리메뉴 |
| 메뉴 그룹 이름 | menuGroupName | 메뉴 그룹 이름. 이름이 없을 수 없다.              |
|          |                 |                          |

### 메뉴(Menu)
| 한글명      | 영문명            | 설명                                          |
|----------|----------------|---------------------------------------------|
| 메뉴       | menu           | 메뉴 그룹에 속하며 1개 이상의 메뉴구성음식으로 구성된 것. ex) 후라이드+후라이드 2마리세트 |
| 메뉴 이름    | menuName      | 메뉴의 이름. 메뉴 이름은 필수이며 비속어가 포함될 수 없다                     |
| 메뉴 가격    | menuPrice     | 메뉴의 가격. 가격은 필수이고 0원 이상이다. |
| 메뉴 구성 음식 | menuProduct   | 메뉴를 구성하는 음식. 음식과 수량을 가지고 있다. 메뉴에는 1개 이상의 메뉴구성 음식이 포함된다.|
| 메뉴 노출 상태 | displayed | 메뉴의 노출 상태                                   |
| 노출하기     | display        | 메뉴를 노출하는 행위.                                |
| 숨기기      | hide           | 메뉴를 숨기는 행위.                                 |
| 비속어      | profanity        | 비속어                               |



### 매장테이블(RestaurantTable)
| 한글명       | 영문명                         | 설명                     |
|-----------|-----------------------------|------------------------|
| 매장 테이블    | restaurantTable            | 매장 내 식사하는 테이블.         |
| 빈 테이블     | unoccupied | 사용 중이 아닌 상태           |
| 사용중 테이블   | occupied   | 사용 중인 상태              |
| 치우기       | clear                       | 매장 테이블을 빈 테이블로 만드는 행위. |
| 손님 수      | numberOfGuests            | 테이블에 착석한 손님 수          |
| 매장 테이블 이름 | restaurantTableName       | 매장 테이블의 이름. 이름은 없을 수 없다.          |



### 주문금액(OrderLineAmount)
| 한글명    | 영문명              | 설명            |
|--------|------------------|------------------|
| 주문금액  | OrderLineAmount | 주문한 메뉴의 금액. 모든 유형의 주문은 주문한 금액을 갖는다. |


### 주문상품들(OrderLineItems)
| 한글명    | 영문명              | 설명            |
|--------|------------------|------------------|
| 주문상품들  | OrderLineItems | 주문한 상품들. 모든 유형의 주문은 주문한 상품들을 갖는다. |

### 주문상품(OrderLineItem)
| 한글명    | 영문명              | 설명            |
|--------|------------------|------------------|
| 주문상품  | OrderLineItem | 주문한 상품. 주문한 상품에는 메뉴와 수량이 포함된다. |


### 배달주문(DeliveryOrder)
| 한글명    | 영문명              | 설명               |
|--------|------------------|------------------|
| 배달 주문  | deliveryOrder     | 배달하는 주문 유형.      |
| 주문 상태  | orderStatus     | 주문의 진행 상태.       |
| 접수중    | waiting          | 주문이 접수되기 전 상태.   |
| 접수완료   | accepted         | 주문이 접수된 상태.      |
| 픽업완료   | picked up        | 배달 기사가 주문을 픽업한 상태 |
| 배달중    | delivering       | 배달 주문이 배달중인 상태.  |
| 배달완료   | delivered        | 배달 주문이 배달완료인 상태. |
| 배달주문완료 | completed       | 주문이 완료 상태.       |
| 라이더    | deliveryRider    | 배달 기사.            |
| 배달요청    | requestDelivery   | 배달 기사에게 배달을 요청하는 행위. 주문번호와 주문금액과 배달주소를 전달해야 한다. |
| 배달정보    | requestDeliveryInfo   | 배달 기사에게 전달할 주문정보. 주문번호와 주문금액과 배달주소가 포함된다 |
| 배달 주소  | deliveryAddress | 배달 주문이 배달될 주소.   |

### 포장주문
| 한글명    | 영문명              | 설명                               |
|--------|------------------|----------------------------------|
| 포장 주문  | takeoutOrder   | 포장하는 주문 유형.                      |
| 주문 상태  | orderStatus     | 주문의 진행 상태. |
| 접수중    | waiting          | 주문이 접수되기 전 상태.                   |
| 접수완료   | accepted         | 주문이 접수된 상태.                      |
| 픽업완료   | picked up        | 주문이 고객에게 전달 완료된 상태.              |
| 포장주문완료 | completed        | 주문이 완료 상태.                       |

### 매장주문
| 한글명    | 영문명              | 설명                                |
|--------|------------------|-----------------------------------|
| 매장 주문  | eatInOrder     | 매장에서 먹는 주문 유형.                    |
| 주문 상태  | order status     | 주문의 진행 상태.  |
| 접수중    | waiting          | 주문이 접수되기 전 상태.                    |
| 접수완료   | accepted         | 주문이 접수된 상태.                       |
| 서빙완료   | served           | 주문이 매장 테이블에 서빙완료된 상태.             |
| 매장주문완료 | completed        | 주문이 완료 상태.                        |


## 모델링

### Product
* `Product`는 `ProductName`과 `ProductPrice` 가진다.
* `productName`은 필수이며 `Profanity`가 포함될 수 없다.
* `ProductPrice`는 필수이며 0원 이상이어야 한다.

### MenuGroup
* `MenuGroup`은 `MenuGroupName`을 가진다.
* `MenuGroupName`은 필수이다.

## Menu
* `Menu`는 `MenuName`을 가진다.
* `menuName`은 필수이며 `profanity`가 포함될 수 없다.
* `Menu`는 `MenuPrice`을 가진다.
* `Menu`는 `MenuPrice`를 변경할 수 있다.
* `MenuPrice`는 필수이며 0원 이상이다.
* `Menu`는 `MenuProducts`를 가진다.
* `Menu`는 `MenuPricePolicy`에 의해 `Displayed`가 조정된다.
* `MenuPricePolicy`는 `MenuPrice는 MenuProductsPrice 클 수 없다`를 의미한다.
* `Menu`는 스스로 `Display`를 하거나 `Hide`를 할 수 있다.
* `Menu`는 한 개 이상의 `MenuProduct`를 가진다.

## MenuProduct
* `MenuProduct`는 `Product`와 `MenuProductQuantity`를 가진다.
* `MenuProductQuantity`는 0보다 작을 수 없다.
* `MenuProduct`는 자신이 가진 상품의 가격과 수량을 곱하여 `MenuProductPrice`를 생성한다.

## RestaurantTable
* `RestaurantTable`은 `RestaurantTableName`를 가지고 있다.
* `RestaurantTableName`은 없을 수 없다.
* `RestaurantTable`은 `Unoccupied`또는 `Occupied`상태를 가질 수 있다.
* `RestaurantTable`는 손님을 받을 수 있으며 `Occupied` 상태가 된다.
* `RestaurantTable`는 자신을 치울 수 있으며 `Unoccupied`상태가 된다.

## OrderLineAmount
* 모든 유형의 주문은 `OrderLineAmount`를 갖는다.
* `OrderLineAmount`는 `OrderLineItems` 가격의 합이다.

## OrderLineItems
* 모든 유형의 주문은 `OrderLineItems`를 갖는다.

## OrderLineItem
* `OrderLineItem`은 `Menu`와 `OrderLineQuantity`와 `OrderLinePrice`를 갖는다.
* `EatInOrder`의 `OrderLineQuantity`는 음수일 수 있다.
* `TakeOutOrder`의 `OrderLineQuantity`는 음수일 수 없다.
* `DeliveryOrder`의 `OrderLineQuantity`는 음수일 수 없다.
* `OrderLineItem`은 `Menu`의 `MenuPrice`와 자신의 `OrderLinePrice`를 비교할 수 있다.

## EatInOrder
* `EatInOrder`는 상태를 가지며 자신의 상태를 변경할 수 있다.
* `EatInOrder`의 상태는 `waiting`->`accepted`->`served`->`completed` 로 변화될 수 있다.
* `EatInOrder`는 `RestaurantTable`을 갖는다.
* `EatInOrder`가 `completed`될 때 `RestaurantTable`을 치우라고 한다.

## TakeOutOrder
* `TakeOutOrder`는 상태를 가지며 자신의 상태를 변경할 수 있다.
* `TakeOutOrder`의 상태는 `waiting`->`accepted`->`pickedUp`->`completed` 로 변화될 수 있다.

## DeliveryOrder
* `DeliveryOrder`는 상태를 가지며 자신의 상태를 변경할 수 있다.
* `DeliveryOrder`의 상태는 `waiting`->`accepted`->`pickedUp`->`delivering`->`delivered`->`completed` 로 변화될 수 있다.
* `DeliveryOrder`는 `deliveryAddress`를 가지고 있다.
* `DeliveryOrder`는 `accepted`될 때, `deliveryRider`에게 `RequestDeliveryInfo`를 가지고 배달을 요청한다.
* `RequestDeliveryInfo`에는 `OrderId`, `OrderLineAmount`, `DeliveryAddress`가 포함된다. 
