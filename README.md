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

용어 사전은 아래와 같이 구성되어 있습니다.

- 상품
- 메뉴 그룹
- 메뉴
- 주문 테이블
- 주문
- 주문 품목
- 기타

### 상품 용어 사전

| 한글명 | 영문명     | 설명             |
|-----|---------|----------------|
| 상품  | product | 키친포스에서 판매되는 물건 |
| 이름  | name    | 상품의 이름         |
| 가격  | price   | 상품이 판매되고 있는 금액 |            |

### 메뉴 그룹 용어 사전

| 한글명   | 영문명        | 설명                               |
|-------|------------|----------------------------------|
| 메뉴 그룹 | menu group | 유사한 특징을 가져서 동일하게 분류될 수 있는 메뉴의 묶음 |
| 이름    | name       | 메뉴 그룹의 이름                        |
| 등록    | create     | 새로운 메뉴 그룹을 추가하는 것                |

### 메뉴 용어 사전

| 한글명      | 영문명          | 설명                                                       |
|----------|--------------|----------------------------------------------------------|
| 메뉴       | menu         | 키친포스에서 판매되는 메뉴로 하나의 메뉴 그룹에 속하고 한 개 이상의 메뉴 구성 상품으로 구성됩니다. |
| 이름    | name    | 메뉴의 이름                                                   |
| 가격    | price   | 메뉴가 판매되고 있는 금액                                           |
| 노출 여부    | displayed    | 메뉴가 고객에게 공개되어 조회 및 주문 할 수 있는지 여부.                        |
| 노출       | display      | 메뉴가 고객에게 공개되어 조회 및 주문 할 수 있게 하는 것.                       |
| 숨김       | hide         | 메뉴가 고객에게 비공개되어 조회 및 주문 할 수 없게 하는 것.                      |
| 등록       | create       | 새로운 데이터를 추가하는 것.                                         |
| 가격 변경    | change price | 메뉴가 고객에게 판매되는 가격을 변경하는 것.                                |

#### 메뉴 - 메뉴 구성 상품 용어 사전

| 한글명      | 영문명          | 설명                       |
|----------|--------------|--------------------------|
| 메뉴 구성 상품 | menu product | 메뉴를 구성하는 상품              |
| 개수       | quantity     | 메뉴 구성 상품가 포함하는 특정 상품의 개수 |

### 주문 테이블 용어사전

| 한글명    | 영문명                     | 설명                                 |
|--------|-------------------------|------------------------------------|
| 주문 테이블 | order table             | 주문 유형이 매장식사일 때, 고객분이 식사하는 테이블  |
| 이름     | name                    | 주문 테이블의 이름                         |
| 인원수    | number of guests        | 주문 테이블에 앉아있는 고객 수                  |
| 착석 여부  | occupied                | 주문 테이블이 현재 사용 중인지 여부.              |
| 빈 테이블  | empty table             | 테이블에 착석한 고객이 없는 상태                 |
| 착석     | sit                     | 매장 식사를 원하는 고객이 앉을 수 있게 한다.         |
| 정리     | clear                   | 매장 식사가 끝나서 주문 테이블을 정리해서 빈 테이블로 만든다. |
| 인원수 변경 | change number of guests | 주문 테이블에 앉아있는 고객 수를 변경하는 것.         |          
| 등록     | create                  | 새로운 주문 테이블을 추가하는 것.                |

### 주문 용어 사전

| 한글명      | 영문명              | 설명                                                   |
|----------|------------------|------------------------------------------------------|
| 주문       | order            | 노출 중인 메뉴에 대해 판매가 발생한 것. 한 개 이상의 주문 품목으로 구성됩니다.       |
| 주문 유형    | order type       | 고객에 주문된 메뉴를 얻는 방법                                    |
| 주문 발생 시점 | order date time  | 주문이 발생한 시각                                           |
| 매장식사     | eat in           | 주문 유형의 하나로, 주문된 메뉴를 매장의 주문 테이블에서 먹고 가는 것             |
| 포장       | takeout          | 주문 유형의 하나로, 주문된 메뉴를 포장해서 가져가는 것                      |
| 배달       | delivery         | 주문 유형의 하나로, 주문된 메뉴를 배달주소로 배달 하는 것                    |
| 배달 주소    | delivery address | 고객에 배달 주문에 대해 배달을 요청한 주소                             |
| 주문 상태    | order status     | 주문이 처리되는 과정에서의 상태를 나타내는 것                            |
| 키친라이더 | kitchen rider | 키친 포스의 배달을 담당하는 업체                                   |
| 라이더   | rider         | 주문을 배달하는 대상                                          |

#### 주문 - 주문 유형 별 주문 상태 용어 사전

주문 유형에 따라 주문 상태가 가지는 의미가 달라 별도의 용어 사전으로 정리합니다.

- 매장 식사

| 한글명 | 영문명       | 설명                                      |
|-----|-----------|-----------------------------------------|
| 대기  | waiting   | 주문을 요청 받아 접수되기 전 대기 중인 상태               |
| 접수  | accepted  | 주문이 접수되어 조리가 시작된 상태                     |
| 서빙  | served    | 접수된 주문이 조리가 완료되어 고객의 주문 테이블에 서빙된 상태     |
| 완료  | completed | 고객의 식사가 끝나 주문 테이블이 빈 테이블이 되어 주문이 완료된 상태 |

- 포장

| 한글명 | 영문명       | 설명                              |
|-----|-----------|---------------------------------|
| 대기  | waiting   | 주문을 요청 받아 접수되기 전 대기 중인 상태       |
| 접수  | accepted  | 주문이 접수되어 조리가 시작된 상태             |
| 서빙  | served    | 접수된 주문이 조리가 완료되어 포장이 완료된 상태     |
| 완료  | completed | 포장된 주문이 고객에게 전달되어 주문이 모두 완료된 상태 |

- 배달

| 한글명   | 영문명        | 설명                                          |
|-------|------------|---------------------------------------------|
| 대기    | waiting    | 주문을 요청 받아 접수되기 전 대기 중인 상태                   |
| 접수    | accepted   | 주문이 접수되어 조리가 시작된 상태. 키친라이더에게 라이더 배정도 요청합니다. |
| 서빙    | served     | 접수된 주문이 조리가 완료된 상태.                         |
| 배달 중  | delivering | 조리가 완료된 주문이 라이더에게 전달되어 배달 주소로 배달 중인 상태      |
| 배달 완료 | delivered  | 라이더가 배달 주소로 배달을 완료한 상태.                     |
| 완료    | completed  | 배달 완료 이후에 주문이 모두 완료된 상태                     |

#### 주문 - 주문 품목 용어 사전

| 한글명   | 영문명           | 설명                 |
|-------|---------------|--------------------|
| 주문 품목 | orderLineItem | 주문을 구성하는 메뉴와 그 개수  |
| 가격    | price         | 주문이 발생한 시점에 메뉴의 가격 |
| 개수    | quantity      | 주문된 메뉴의 개수         |


### 기타 용어 사전

| 한글명   | 영문명           | 설명                 |
|-------|---------------|--------------------|
| 비속어   | profanity     | 상스럽고 거친 용어         |

## 모델링

### 상품 (`Product`)

#### 상품 (`Product`) - 속성
- `Product` 는 `name`과 `price`를 가진다.
    - `name`은 반드시 필요하고 비속어가 포함될 수 없다.
    - `price`는 반드시 필요하고 0보다 커야 한다.

#### 상품 (`Product`) - 유스케이스
- 관리자는 `Product`를 생성할 수 있다.
    - 비속어 검증기로 `name`에 비속어 포함 여부를 확인해야 한다.
- 관리자는 `Product`의 가격을 수정할 수 있다.
    - 해당 `Product`가 `MenuProduct`로 포함된 모든 `Menu`에 대해 아래 검증을 진행한다.
        - `Menu`의 `price`보다 `MenuProduct`의 `Product.price`와 `quantity`의 곱에 총합보다 더 크면 비노출한다.
- 관리자는 `Product`를 조회할 수 있다.

### 메뉴 (`Menu`)

#### 메뉴 구성 상품 (`MenuProduct`)

##### 메뉴 구성 상품 (`MenuProduct`) - 속성
- `MenuProduct`는 `Product`와 `quantity`를 가진다.
    - `Product`는 반드시 필요하다.
    - `quantity`는 반드시 필요하고 0보다 커야 한다.

#### 메뉴 (`Menu`) - 속성
- `Menu` 는 `name`, `price`, `MenuGroup`, `MenuProduct`,`displayed`를 가진다.
    - `name`은 반드시 필요하고 비속어가 포함될 수 없다.
    - `price`는 반드시 필요하고 0보다 커야 한다.
    - `menuProducts`는 반드시 필요하고 1개 이상이어야 한다.
    - `menuGroup`은 반드시 필요하고 하나의 `MenuGroup`에 포함되어야 한다.
    - `displayed`는 반드시 필요하고 `true` or `false` 중 하나이다.
        - `true`는 노출(`display`), `false`는 숨김(`hide`) 상태임을 의미한다.

#### 메뉴 (`Menu`) - 유스케이스
- 관리자는 `Menu`를 생성할 수 있다.
    - 비속어 검증기로 `name`에 비속어 포함 여부를 확인해야 한다.
    - `Menu`의 `price`보다 `MenuProduct`의 `Product.price`와 `quantity`의 곱에 총합이 더 커야 한다.
- 관리자는 `Menu`의 `price`를 수정할 수 있다.
    - 변경되는 `Menu`의 `price`보다 `Menu`의 `price`보다 `MenuProduct` 의 `price`와 `quantity`의 곱에 총합이 더 커야 한다.
- 관리자는 `Menu`를 `display` 할 수 있다.
    - `Menu`의 `price`보다 `MenuProduct`의 `Product.price`와 `quantity`의 곱에 총합이 더 커야 한다.
- 관리자는 `Menu`를 `hide` 할 수 있다.
- 관리자는 `Menu`를 조회할 수 있다.

### 메뉴 그룹 (`MenuGroup`)

#### 메뉴 그룹 (`MenuGroup`) - 속성
- `MenuGroup`은 `name`을 가진다.
    - `name`은 반드시 필요하다.

#### 메뉴 그룹 (`MenuGroup`) - 유스케이스
- 관리자는 `MenuGroup`을 생성할 수 있다.
- 관리자는 `MenuGroup`을 조회할 수 있다.

### 주문 테이블(`OrderTable`)

#### 주문 테이블 (`OrderTable`) - 속성
- `OrderTable`은 `name`, `numberOfGuests`, `occupied`를 가진다.
    - `name`은 반드시 필요하다.
    - `numberOfGuests`는 반드시 필요하다.
    - `occupied`는 반드시 필요하고 `true` or `false` 중 하나이다.
        - `true`는 착석(`sit`), `false`는 정리(`clear`) 되어 비어있음을 의미한다.

#### 주문 테이블 (`OrderTable`) - 유스케이스
- 관리자는 `OrderTable`을 생성할 수 있다.
    - `numberOfGuests`는 0으로, `occupied`는 `false`로 생성된다.
- 관리자는 `OrderTable`를 `sit` 처리할 수 있다.
    - `OrderTable`의 `occupied`가 `true`로 변경된다.
- 관리자는 `OrderTable`를 `clear` 처리할 수 있다.
    - `OrderTable`를 이용 중인 `Order`가 `COMPLETED` 상태가 아니면 `clear`할 수 없다.
    - `OrderTable`의 `occupied`가 `false`로 변경된다.
    - `OrderTable`의 `numberOfGuests`가 0으로 변경된다..
- 관리자는 `OrderTable`의 `numberOfGuests`를 변경할 수 있다.
    - 변경할 `numberOfGuests`는 0 이상 이어야 한다.
    - `OrderTable`이 `sit` 상태가 아니면 `numberOfGuests`를 변경할 수 없다.
- 관리자는 `OrderTable`을 조회할 수 있다.

### 주문(`Order`)

#### 주문 품목(`OrderLineItem`)

##### 주문 품목(`OrderLineItem`) - 속성
- `OrderLineItem`은 `Menu`, `quantity`, `price`를 가진다.
    - `Menu`는 반드시 필요하다.
    - `quantity`는 반드시 필요하다.

#### 주문 (`Order`) - 속성
- `Order`는 `OrderType`, `OrderStatus`, `orderDateTime`, `OrderLineItem`, `deliveryAddress`, `OrderTable`을 가진다.
    - `OrderType`은 반드시 필요하고 `DELIVERY` or `TAKEOUT` or `EAT_IN` 중 하나이다.
    - `OrderStatus`는 반드시 필요하고 `WAITING`, `ACCEPTED`, `SERVED`, `DELIVERED`, `DELIVERING`, `COMPLETED` 중 하나이다.
    - `orderDateTime`는 주문이 들어온 시점으로 반드시 필요하다.
    - `OrderLineItem`은 반드시 필요하고 1개 이상이어야 한다.
    - `deliveryAddress`는 `OrderType`이 `DELIVERY`일 경우 반드시 필요하다.
    - `OrderTable`은 `OrderType`이 `EAT_IN`일 경우 반드시 필요하다.

#### 주문 (`Order`) - 공통 - 유스케이스
- 관리자는 `Order`을 조회할 수 있다.

#### 주문 (`Order`) - EAN_IN - 유스케이스
- 관리자는 `Order`를 생성할 수 있다.
    - `OrderStatus`는 `WAITING`으로 생성된다.
    - `OrderDateTime`은 생성한 시점으로 생성된다.
    - `OrderType`은 반드시 필요하다.
    - `OrderLineItem`은 하나 이상 있어여 한다.
    - `OrderLineItem`에 해당하는 메뉴는 노출 상태로 반드시 있어야 한다.
    - `OrderLineItem`의 `price`와 `Menu`의 `price`는 같아야 한다.
    - 한 개의 `OrderTable`를 `sit` 해야 한다.
- 관리자는 `Order`를 `accept` 할 수 있다.
    - 대상 `Order`는 `OrderStatus`가 `WAITING`이어야 한다.   
    - `OrderStatus`가 `ACCEPTED`로 변경된다.
- 관리자는 `Order`를 `serve` 할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `ACCEPTED`이어야 한다.
- 관리자는 `Order`를 `complete`할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `SERVED`이어야 한다.
  - 대상 `Order`와 연관된 `OrderTable`이 존재하지 않는 경우 해당 `OrderTable`의 `numberOfGuests`를 0으로 변경하고 `clear`한다.
  - `OrderStatus`가 `COMPLETED`로 변경된다.

#### 주문 (`Order`) - TAKEOUT - 유스케이스
- 관리자는 `Order`를 생성할 수 있다.
    - `OrderStatus`는 `WAITING`으로 생성된다.
    - `OrderDateTime`은 생성한 시점으로 생성된다.
    - `OrderType`은 반드시 필요하다.
    - `OrderLineItem`은 하나 이상 있어여 한다.
    - `OrderLineItem`에 해당하는 메뉴는 노출 상태로 반드시 있어야 한다.
    - `OrderLineItem`의 개수는 한 개 이상 이어야 있다.
    - `OrderLineItem`의 `price`와 `Menu`의 `price`는 같아야 한다.
- 관리자는 `Order`를 `accept` 할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `WAITING`이어야 한다.
  - `OrderStatus`가 `ACCEPTED`로 변경된다.
- 관리자는 `Order`를 `serve` 할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `ACCEPTED`이어야 한다.
- 관리자는 `Order`를 `complete`할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `SERVED`이어야 한다.
  - `OrderStatus`가 `COMPLETED`로 변경된다.

#### 주문 (`Order`) - DELIVERY - 유스케이스
- 관리자는 `Order`를 생성할 수 있다.
    - `OrderStatus`는 `WAITING`으로 생성된다.
    - `OrderDateTime`은 생성한 시점으로 생성된다.
    - `OrderType`은 반드시 필요하다.
    - `OrderLineItem`은 하나 이상 있어여 한다.
    - `OrderLineItem`에 해당하는 메뉴는 노출 상태로 반드시 있어야 한다.
    - `OrderLineItem`의 개수는 한 개 이상 이어야 있다.
    - `OrderLineItem`의 `price`와 `Menu`의 `price`는 같아야 한다.
    - `deliveryAddress`가 반드시 필요하다.
- 관리자는 `Order`를 `accept` 할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `WAITING`이어야 한다.
  - `kitchenRiders`에 배달 요청이 된다. 
  - `OrderStatus`가 `ACCEPTED`로 변경된다.
- 관리자는 `Order`를 `serve` 할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `ACCEPTED`이어야 한다.
- 관리자는 `Order`를 `startDelivery`할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `SERVED`이어야 한다.
  - `OrderStatus`가 `DELIVERING`로 변경된다.
- 관리자는 `Order`를 `completeDelivery`할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `DELIVERING`이어야 한다.
  - `OrderStatus`가 `DELIVERED`로 변경된다.
- 관리자는 `Order`를 `complete`할 수 있다.
  - 대상 `Order`는 `OrderStatus`가 `DELIVERED`이어야 한다.
  - `OrderStatus`가 `COMPLETED`로 변경된다.
