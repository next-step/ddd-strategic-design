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

### 메뉴

| 한글명       | 영문명          | 설명                                  |
|-----------|--------------|-------------------------------------|
| 메뉴        | menu         | 하나 이상의 상품으로 구성된 집합, 구매는 메뉴 단위로 이루어짐 |
| 메뉴 노출     | menu display | 메뉴를 노출시킨다.                          |
| 메뉴 숨김     | menu hide    | 메뉴를 숨긴다.                            |
| 메뉴 그룹     | menu group   | 메뉴를 유사한 유형에 따라 분류한 집합 예) 세트메뉴       |

### 상품

| 한글명       | 영문명          | 설명                    |
|-----------|--------------|-----------------------|
| 상품        | product      | 메뉴의 구성요소 예) 후라이드      |
| 메뉴에 속한 상품 | menu product | 메뉴에 소속된 상품들           |
| 비속어     | profanity    | 이름으로 사용할 수 없는 부적절한 용어 |


### 주문 - 공통

| 한글명   | 영문명          | 설명                                                                   |
|-------|--------------|----------------------------------------------------------------------|
| 주문    | order        | 손님이 원하는 상품을 구매하는 행위                                                  |
| 주문 유형 | order type   | 손님이 주문 시 상품을 수령하는 방법의 종류. 매장 주문, 배달 주문, 포장 주문이 있다.                   |
| 주문 상태 | order status | 주문이 처리되고 있는 상황을 표현한 상태. 주문 상태에는 대기 중, 접수됨, 서빙됨, 배달 중, 배달 완료, 완료가 있다. |
| 주문 항목 | order line item | 주문한 메뉴들의 정보                                                          |
| 대기    | waiting      | 손님에게 주문 받기 전 상태                                                      |
| 접수    | accepted     | 손님에게 주문을 받은 상태                                                       |
| 완료    | completed    | 모든 주문 과정이 완료된 상태                                                     |

#### 주문 유형 별 상태 흐름
- 매장 주문
  - 대기 -> 접수 -> 서빙됨 -> 완료
- 배달 주문
  - 대기 -> 접수 -> 배달 시작 -> 배달 완료 -> 완료
- 포장 주문
  - 대기 -> 접수 -> 서빙됨 -> 완료

#### 매장 주문

| 한글명       | 영문명              | 설명                                                                      |
|-----------|------------------|-------------------------------------------------------------------------|
| 매장 주문     | eat in           | 주문을 받아 주문 테이블에 착석한 손님에게 상품을 서빙한다                                           |
| 주문 테이블    | order table      | 매장 주문한 손님이 착석할 수 있는 테이블.                       |
| 테이블 착석    | sit              | 주문 테이블을 특정 손님이 착석 한다.  |
| 테이블 정리    | clear            | 주문 테이블을 치운다.          |
| 빈 테이블     | empty table      | 손님이 착석하지 않은 상태의 주문 테이블. 새 손님이 왔을 때 착석할 수 있다.    |
| 앉은 테이블    | occupied table   | 손님이 착석한 상태의 주문 테이블. 매장 주문이 완료될 때까지 착석 상태가 유지된다. |
| 서빙됨       | served           | 주문 테이블에 착석한 손님에게 상품을 전달한 상태                                         |

#### 배달 주문

| 한글명    | 영문명              | 설명                                                                      |
|--------|------------------|-------------------------------------------------------------------------|
| 배달 주문  | eat in           | 주문을 받아 배달 대행사를 통해 손님에게 상품을 서빙한다                                            |
| 배달 중   | delivering       | 배달 대행사에서 손님에게 상품을 서빙 중인 상태                                                             |
| 배달 완료  | delivered        | 배달 대행사에서 손님에게 상품을 서빙 완료한 상태                                                         |
| 배달 주소  | delivery address | 배달 대행사를 통해 손님에게 상품을 서빙할 주소                                                       |
| 배달 대행사 | rider            | 배달을 대행해주는 곳                                                             |

#### 포장 주문
| 한글명    | 영문명              | 설명                                                                      |
|--------|------------------|-------------------------------------------------------------------------|
| 포장 주문  | takeOut          | 주문을 받아 매장으로 온 손님에게 상품을 서빙한다                                                   |
| 서빙됨  | served           | 매장으로 온 손님에게 상품을 전달한 상태                                         |



## 모델링

### 상품 컨텍스트
#### 상품 (Product)
- 상품(`Product`)은 이름(`name`), 가격(`price`)을 가진다

- 상품(`Product`)을 등록(create)한다
  - 상품(`Product`)의 이름(`name`)에는 비속어(Profanity)가 포함될 수 없다.
  - 상품(`Product`)의 가격(`price`)은 0원 이상이어야 한다
- 상품(`Product`)의 가격(`price`)을 변경(update)한다
  - 상품(`Product`)의 가격(`price`)은 0원 이상이어야 한다

### 메뉴 컨텍스트
#### 메뉴 (Menu)
- 메뉴(`Menu`)는 이름(`name`), 그룹(`group`), 상품 목록(`productList`), 가격(`price`), 노출 여부(`displayed`)를 가진다
- 메뉴(`Menu`)을 등록(create)한다
  - 메뉴(`Menu`)의 이름(`name`)에는 비속어(Profanity)가 포함될 수 없다
  - 메뉴(`Menu`)의 상품 목록(`productList`)에는 1개 이상의 등록된 상품(`Product`)이 있어야 한다.
  - 메뉴(`Menu`)의 상품 목록(`productList`)의 상품(`Product`) 수량은 0 이상이어야 한다.
  - 메뉴(`Menu`)의 가격(`price`)은 0원 이상이어야 한다
  - 메뉴 가격(`price`)은 메뉴 상품 목록(`productList`) 내 상품(`Product`) 금액의 합보다 작거나 같아야 한다
- 메뉴(`Menu`)의 가격(`price`)을 변경(update)한다
  - 메뉴(`Menu`)의 가격(`price`)은 0원 이상이어야 한다
  - 메뉴 가격(`price`)은 메뉴 상품 목록(`productList`) 내 상품(`Product`) 금액의 합보다 작거나 같아야 한다
  - 메뉴 가격(`price`)이 메뉴 상품 목록(`productList`) 내 상품(`Product`) 금액의 합보다 크면 메뉴를 숨긴다(hide)
- 메뉴(`Menu`)를 노출(display)할 수 있다.

#### 메뉴 그룹 (Menu Group)
- 메뉴 그룹(`MenuGroup`)은 이름(`name`)을 가진다
- 메뉴 그룹(`MenuGroup`)을 등록(create)한다
  - 메뉴 그룹(`MenuGroup`)의 이름(`name`)은 비워둘 수 없다


### 매장 주문 컨텍스트
#### 매장 주문 (Eat In)
- 매장 주문(`EatInOrder`)은 상태(`status`), 주문 항목(`orderLineItems`), 주문 테이블(`orderTable`), 유형(`type`)을 가진다.
- 매장 주문(`EatInOrder`)의 상태(`status`)는 대기(`WAITING`) -> 접수(`ACCEPTED`) -> 서빙됨(`SERVED`) -> 완료(`COMPLETED`) 순이다.

- 매장 주문(`EatInOrder`)을 등록(create)한다
  - 매장 주문(`EatInOrder`)의 주문 항목(`orderLineItems`)은 수량(`quantity`)이 0 미만일 수 있다
  - 매장 주문(`EatInOrder`)의 주문 항목(`orderLineItems`)의 메뉴 가격(`price`)은 실제 메뉴 가격(`price`)과 일치해야 한다
  - 매장 주문(`EatInOrder`)의 주문 항목(`orderLineItems`)의 메뉴(`menu`)가 숨겨져있다면(`displayed`) 등록(create)할 수 없다
  - 매장 주문(`EatInOrder`)의 유형(`type`)이 올바르지 않으면 등록(create)할 수 없다
  - 매장 주문(`EatInOrder`)의 주문 항목(`orderLineItems`)의 메뉴(`menu`)가 없으면 등록(create)할 수 없다
  - 1개 이상의 등록된 메뉴(`menu`)로만 매장 주문(`EatInOrder`)을 등록(create)할 수 있다
  - 매장 주문(`EatInOrder`)의 빈 테이블(`orderTable`)에는 매장 주문(`EatInOrder`)을 등록(create)할 수 없다
- 매장 주문(`EatInOrder`)을 접수(accept)한다
  - 대기 중(`WAITING`)인 매장 주문(`EatInOrder`)만 접수(accept)할 수 있다
- 매장 주문(`EatInOrder`)을 서빙(serve)한다
  - 접수된(`ACCEPTED`) 매장 주문(`EatInOrder`)만 서빙(serve)할 수 있다
- 매장 주문(`EatInOrder`)을 완료(complete)한다
  - 서빙된(`SERVED`) 매장 주문(`EatInOrder`)만 완료(complete)할 수 있다
- 테이블 착석(sit)한다
- 테이블 정리(clear)한다
  - 매장 주문(`EatInOrder`)의 주문 테이블(`orderTable`)의 모든 매장 주문(`EatInOrder`)이 완료(complete)되면 테이블 정리(clear)한다
  - 완료(`COMPLETED`)가 아닌 매장 주문(`EatInOrder`)이 있는 주문 테이블(`orderTable`)은 테이블 정리(clear)할 수 없다

#### 주문 테이블 (Order Table)
- 주문 테이블(`orderTable`)은 이름(`name`), 손님 수(`numberOfGuests`), 빈 테이블 여부(`occupied`)를 가진다
- 주문 테이블(`orderTable`)을 등록(create)한다
  - 주문 테이블(`orderTable`)의 이름(`name`)은 비워둘 수 없다
  - 주문 테이블(`orderTable`)의 이름(`name`)에는 비속어(Profanity)가 포함될 수 없다
  - 주문 테이블(`orderTable`)의 손님 수(`numberOfGuests`)는 0 이상이어야 한다
- 주문 테이블(`orderTable`)의 손님 수(`numberOfGuests`)를 변경(update)한다
  - 주문 테이블(`orderTable`)의 손님 수(`numberOfGuests`)를 음수로 변경(update)할 수 없다

  
### 배달 주문 컨텍스트
#### 배달 주문 (Delivery Order)
- 배달 주문(`DeliveryOrder`)은 상태(`status`), 주문 항목(`orderLineItems`), 배달 주소(`deliveryAddress`), 유형(`type`)을 가진다.
- 배달 주문(`DeliveryOrder`)의 상태(`status`)는 대기(`WAITING`) -> 접수(`ACCEPTED`) -> 서빙됨(`SERVED`) -> 배달 중(`DELIVERING`) -> 배달 완료(`DELIVERED`) -> 완료(`COMPLETED`) 순이다.

- 배달 주문(`DeliveryOrder`)을 등록(create)한다
  - 배달 주문(`DeliveryOrder`)의 주문 항목(`orderLineItems`)은 수량(`quantity`)이 0 이상이어야 한다
  - 배달 주문(`DeliveryOrder`)의 주문 항목(`orderLineItems`)의 메뉴 가격(`price`)은 실제 메뉴 가격(`price`)과 일치해야 한다
  - 배달 주문(`DeliveryOrder`)의 배달 주소(`deliveryAddress`)는 비워둘 수 없다
  - 배달 주문(`DeliveryOrder`)의 주문 항목(`orderLineItems`)의 메뉴(`menu`)가 숨겨져있다면(`displayed`) 등록(create)할 수 없다
  - 배달 주문(`DeliveryOrder`)의 유형(`type`)이 올바르지 않으면 등록(create)할 수 없다
  - 배달 주문(`DeliveryOrder`)의 배달 주소(`deliveryAddress`)가 올바르지 않으면 등록(create)할 수 없다
  - 배달 주문(`DeliveryOrder`)의 주문 항목(`orderLineItems`)의 메뉴(`menu`)가 없으면 등록(create)할 수 없다
  - 1개 이상의 등록된 메뉴(`menu`)로만 배달 주문(`DeliveryOrder`)을 등록(create)할 수 있다
- 배달 주문(`DeliveryOrder`)을 접수(accept)한다
  - 대기 중(`WAITING`)인 배달 주문(`DeliveryOrder`)만 접수(accept)할 수 있다
  - 배달 주문(`DeliveryOrder`)이 접수(accept)되면 배달 대행사를 호출(requestDelivery)한다
- 배달 대행사를 호출(requestDelivery)한다
- 배달 주문(`DeliveryOrder`)을 서빙(serve)한다
- 배달 주문(`DeliveryOrder`)을 배달(startDelivery)한다
  - 서빙된(`SERVED`) 배달 주문(`DeliveryOrder`)만 배달(startDelivery)할 수 있다
- 배달 주문(`DeliveryOrder`)을 배달 완료(completeDelivery)한다
  - 배달 중(`DELIVERING`)인 배달 주문(`DeliveryOrder`)만 배달 완료(completeDelivery)할 수 있다
- 배달 주문(`DeliveryOrder`)을 완료(complete)한다
  - 배달 완료(`DELIVERED`)된 배달 주문(`DeliveryOrder`)만 완료(complete)할 수 있다



### 포장 주문 컨텍스트
#### 포장 주문 (Take out)
- 포장 주문(`TakeOut`)은 상태(`status`), 주문 항목(`orderLineItems`), 유형(`type`)을 가진다.
- 포장 주문(`TakeOut`)의 상태(`status`)는 대기(`WAITING`) -> 접수(`ACCEPTED`) -> 서빙됨(`SERVED`) -> 완료(`COMPLETED`) 순이다.

- 포장 주문(`TakeOut`)을 등록(create)한다
  - 포장 주문(`TakeOut`)의 주문 항목(`orderLineItems`)은 수량(`quantity`)이 0 미만일 수 있다
  - 포장 주문(`TakeOut`)의 주문 항목(`orderLineItems`)의 메뉴 가격(`price`)은 실제 메뉴 가격(`price`)과 일치해야 한다
  - 포장 주문(`TakeOut`)의 주문 항목(`orderLineItems`)의 메뉴(`menu`)가 숨겨져있다면(`displayed`) 등록(create)할 수 없다
  - 포장 주문(`TakeOut`)의 유형(`type`)이 올바르지 않으면 등록(create)할 수 없다
  - 포장 주문(`TakeOut`)의 주문 항목(`orderLineItems`)의 메뉴(`menu`)가 없으면 등록(create)할 수 없다
  - 1개 이상의 등록된 메뉴(`menu`)로만 포장 주문(`TakeOut`)을 등록(create)할 수 있다
- 포장 주문(`TakeOut`)을 접수(accept)한다
  - 대기 중(`WAITING`)인 포장 주문(`TakeOut`)만 접수(accept)할 수 있다
- 포장 주문(`TakeOut`)을 서빙(serve)한다
  - 접수된(`ACCEPTED`) 포장 주문(`TakeOut`)만 서빙(serve)할 수 있다
- 포장 주문(`TakeOut`)을 완료(complete)한다
  - 서빙된(`SERVED`) 포장 주문(`TakeOut`)만 완료(complete)할 수 있다
