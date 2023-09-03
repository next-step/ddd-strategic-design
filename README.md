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

### 상품 

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 판매할 수 있는 단일 식품 |
| 이름 | name | 상품의 이름 |
| 가격 | price | 상품의 가격(₩) |
| 등록 | create | 포스기에 신규로 상품을 등록한다 |
| 가격 변경 | change price | 등록된 상품의 가격을 변경한다  |

### 메뉴 그룹

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 그룹 |  menu group | 메뉴들을 종류에 따라 모아둔 그룹 |
| 이름 | name | 메뉴 그룹의 이름 |
| 등록 | create | 포스기에 신규로 메뉴 그룹을 등록한다 |

### 메뉴

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | menu | 고객들에게 제공되는 구성품들과 가격의 정보 |
| 이름 | name | 메뉴의 이름 |
| 가격 | price | 메뉴의 가격(₩) |
| 구성품 | menu product | 메뉴를 구성하고 있는 상품들 |
| 등록 | create | 포스기에 신규로 메뉴를 등록한다 |
| 활성화 | display | 메뉴를 고객이 구매할수 있게 한다 |
| 비활성화 | hide | 메뉴를 고객이 구매할수 없게 한다 |
| 가격 변경 | change price | 등록된 메뉴의 가격을 변경한다 |

### 매장 테이블

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 매장 테이블 |  order table | 매장 내 고객이 이용할 수 있는 테이블 |
| 이름 | name | 매장 테이블을 지칭하는 이름 |
| 고객 수 | number of guest | 매장 테이블을 이용 중인 인원 수 |
| 이용 중 | occupied | 매장 테이블이 이용 중인 상태 |
| 등록 | create | 포스기에 신규로 매장 테이블을 등록한다 |
| 이용 | mark occupied | 매장테이블의 상태를 이용 중으로 변경한다. |  
| 정리 | clear | 매장 테이블을 비운다 |
| 고객 수 변경 | change number of guests | 매장 테이블을 이용중인 인원 수를 변경한다 |

### 주문

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 |  order | 고객이 선택한 메뉴들 |
| 배달 주문        | delivery order    | 매장이 음식을 배달하여 고객이 집에서 먹는 형태의 주문                 |
| 매장 식사 주문    | eat-in order      | 매장 내에서 식사하는 형태의 주문                                    |
| 포장 주문         | takeout order     | 매장에서 포장하여 고객이 외부에서 먹는 형태의 주문                   |
| 접수 대기 중 | WAITING        | 주문이 접수되어 매장의 수락 대기중            |
| 수락됨       | ACCEPTED       | 매장이 주문 수락              |
| 전달됨       | SERVED         | 매장에서 주문한 구성품이 전달됨               |
| 배달 중      | DELIVERING     | 주문이 배달 중                    |
| 배달 완료    | DELIVERED      | 배달 완료           |
| 완료됨       | COMPLETED      | 주문 완료                       |
| 주문 일자 | order datetime | 고객이 주문한 날짜와 시간 |
| 주문 내역 | order line items | 고객이 주문한 메뉴들 |
| 라이더 요청 | request delivery | 배달주문이 수락될때 라이더를 요청한다 |
| 등록 | create | 고객이 매장에 주문을 한다 | 
| 수락 | accept | 매장이 고객의 주문을 수락한다 | 
| 전달 | serve | 매장에서 고객이 주문한 구성품을 전달한다 |
| 배달 시작 | start delivery | 라이더가 배달을 시작한다 |
| 배달 완료 | complete delivery | 라이더가 고객에게 주문을 전달하고 배달을 완료한다 |
| 완료 | complete | 매장이 주문을 완료한다 |

### 공통

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 비속어 |  profanity | 욕설이나 부적절한 언어 |
| 라이더 |  kitchen rider | 주문을 배달하는 사람 |
| 라이더 부서 |  kitchen riders | 배달을 총괄하는 외부 부서 |


## 모델링

### 상품

- `Product`은 0원 이상의 `price`을 가진다.
- `Product`은 `name`은 `profanity`를 포함할 수 없다.
- `Product`은 `name`은 필수 값이다.
- `Product`은 0원 이상의 `price`을 가진다.


- `Product Service`에서 `Product`를 등록한다.
  - `Product Service`에서 `name`과 `price`를 검증 한다.
- `Product Service`에서 `Product`의 `price`를 변경한다.

###  메뉴 그룹

- `MenuGroup`은 `name`은 필수 값이다.


- `MenuGroup Service`에서 `MenuGroup`를 등록한다.

### 메뉴

- `Menu`의 `name`은 `profanity`를 포함할 수 없다.
- `Menu`는 `Menu Group`을 가진다.
- `Menu`는 `Product`으로 이루어진 1개 이상의 `menu products`를 가진다.
- `Menu`은 0원 이상의 `price`을 가진다.
  - `Menu`의 `price`는 `menu products`의 `price`의 총합 보다 높을 수 없다.
- `Menu`는 고객이 구매 가능한가를 구별할 수 있는 `displayed`를 가진다.


- `Menu Service`에서 `Menu`를 등록한다.
  - `Menu Service`에서 `name`과 `price` 그리고 `menu products`를 검증 한다.
- `Menu Service`에서 `Menu`의 `price`를 변경한다.
  - 변경할 `Menu`의 `price`는 `menu products`의 `price`의 총합 보다 높을 수 없다.
- `Menu Service`는 고객이 구매 가능하도록 `display`한다.
- `Menu Service`는 고객이 구매 불가능하도록 `hide`한다.

### 매장 테이블

- `OrderTable`은 `name`을 가진다.
- `OrderTable`은 고객의 인원 수를 보여주는 `number Of Guests`을 가진다.
- `OrderTable`은 고객이 이용 중 인지를 구별하는 `occupied`를 가진다.


- `OrderTable Service`에서 `OrderTable`를 등록한다.
- `OrderTable Service`에서 `OrderTable`를 이용 중으로 표시한다.
- `OrderTable Service`에서 `OrderTable`를 정리하여 이용 가능하도록 변경한다.
- `OrderTable Service`에서 `OrderTable`의 고객 수 변경를 변경한다.

### 주문

- `Order`는 주문의 상태를 보여주는 `Order Status`를 가진다.
- `Order`는 고객이 요청한 메뉴들을 확인 가능한 `Order Line Item`을 가진다.
- `Order`는 주문한 시간을 확인할 수 있는 `order Date Time`을 갖는다.
- `Order`는 주문의 종류를 구별하는 `OrderType`을 가진다.
  - `OrderType`이 `DELIVERY`인 경우 `Delivery Address`를 가진다.
  - `OrderType`이 `EAT_IN`인 경우 `Order Table`과 `orderTableId`를 갖는다.

- `Order Service`에서 `Order`를 등록한다.
  - `Order`의 `Order Line Item`의 각 `quantity`는 0개 이상이어야 한다.
- `Order Service`에서 `Order`를 수락한다.
  - `Order Status`가 `ACCEPTED`로 변경한다.
- `Order Service`에서 `Order`를 전달한다.
  - `Order Status`가 `SERVED`로 변경한다.
- `Order Service`에서 `Order`를 완료한다.
  - `Order Status`가 `COMPLETED`로 변경한다.

### 포장 주문 프로세스

1. 고객이 주문을 하면 `Order Status`는 `WAITING`으로 표시된다.
2. 매장에서 주문을 확인하고 수락하면 `Order Status`는 `ACCEPTED`로 변경한다.
3. 매장에서 고객의 주문을 포장하고 제공하면 `Order Status`는 `SERVED`로 변경한다.
4. 매장에서 고객의 주문을 모두 처리하면 `Order Status`는 `COMPLETED`로 변경한다.
  
### 배달 주문 프로세스

1. 고객이 주문을 하면 `Order Status`는 `WAITING`으로 표시된다.
2. 매장에서 주문을 확인하고 수락하면 `Order Status`는 `ACCEPTED`로 변경한다.\
  2-1. 배달 주문을 수락하면 `kitchen riders`에게 `kitchen rider`을 요청한다.
3. 매장에서 고객의 주문을 포장하면 `Order Status`는 `SERVED`로 변경한다.
4. `kitchen rider`가 배달을 시작하면 `Order Status`는 `DELIVERING`로 변경한다.
5. `kitchen rider`가 배달을 완료하면 `Order Status`는 `DELIVERED`로 변경한다.
6. 배달 주문이 완료되면 `Order Status`는 `COMPLETED`로 변경한다.
### 매장 주문 프로세스

1. 고객이 주문을 하면 `Order Status`는 `WAITING`으로 표시된다.
2. 매장에서 주문을 확인하고 수락하면 `Order Status`는 `ACCEPTED`로 변경한다.
3. 매장에서 고객의 주문을 `Order Table`에 제공하면 `Order Status`는 `SERVED`로 변경한다.
4. 매장에서 고객의 주문을 모두 처리하면 `Order Status`는 `COMPLETED`로 변경한다.\
  4-1. `Order Table`을 `clear`한다.

### 공통

- 모든 `도메인`은 식별가능한 `ID`를 갖는다.
- `kitchen riders`에서 `kitchen rider`에 배달을 할당한다.
- `PurgomalumClient`에서 `profanity`를 검사한다.

