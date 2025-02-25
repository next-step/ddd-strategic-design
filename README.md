# 키친포스

## 퀵 스타트

```sh
cd docker
docker compose -p kitchenpos up -d
```

## 요구 사항

- `상품`을 등록할 수 있다.
- `상품 가격`이 올바르지 않으면 등록할 수 없다.
  - `상품의가격`은 0원 이상이어야 한다.
- `상품 이름`이 올바르지 않으면 등록할 수 없다.
  - `상품 이름`에는 `비속어`가 포함될 수 없다.
- `상품 가격`을 변경할 수 있다.
- `상품 가격`이 올바르지 않으면 변경할 수 없다.
  - `상품 가격`은 0원 이상이어야 한다.
- `상품 가격`이 변경될 때 `메뉴 가격`이 메뉴에 속한 상품 금액의 합보다 크면 메뉴가 숨겨진다.
- `상품`의 목록을 조회할 수 있다.

### 메뉴 그룹

- `메뉴 그룹`을 등록할 수 있다.
- `메뉴 그룹 이름`이 올바르지 않으면 등록할 수 없다.
  - `메뉴 그룹 이름`은 비워 둘 수 없다.
- `메뉴 그룹`의 목록을 조회할 수 있다.

### 메뉴

- 1 개 이상의 등록된 `상품`으로 `메뉴`를 등록할 수 있다.
- `상품`이 없으면 등록할 수 없다.
- `메뉴`에 속한 `상품`의 수량은 0 이상이어야 한다.
- `메뉴 가격`이 올바르지 않으면 등록할 수 없다.
  - `메뉴 가격`은 0원 이상이어야 한다.
- `메뉴`에 속한 `상품` 금액의 합은 `메뉴의 가격`보다 크거나 같아야 한다.
- `메뉴`는 특정 `메뉴 그룹`에 속해야 한다.
- `메뉴 이름`이 올바르지 않으면 등록할 수 없다.
  - `메뉴 이름`에는 `비속어`가 포함될 수 없다.
- `메뉴 가격`을 변경할 수 있다.
- `메뉴 가격`이 올바르지 않으면 변경할 수 없다.
  - `메뉴 가격`은 0원 이상이어야 한다.
- `메뉴`에 속한 `상품` 금액의 합은 `메뉴의 가격`보다 크거나 같아야 한다.
- `메뉴를 노출`할 수 있다.
  - `메뉴 가격`이 메뉴에 속한 `상품` 금액의 합보다 높을 경우 `메뉴를 노출`할 수 없다.
- `메뉴`를 숨길 수 있다.
- `메뉴`의 목록을 조회할 수 있다.

### 주문 테이블

- `주문 테이블`을 등록할 수 있다.
- `주문 테이블 이름`이 올바르지 않으면 등록할 수 없다.
  - `주문 테이블 이름`은 비워 둘 수 없다.
- `빈 테이블`을 해지할 수 있다.
- `빈 테이블`로 설정할 수 있다.
- 완료되지 않은 주문이 있는 `주문 테이블`은 `빈 테이블`로 설정할 수 없다.
- `방문 손님 수`를 변경할 수 있다.
- `방문 손님 수`가 올바르지 않으면 변경할 수 없다.
  - `방문 손님 수`는 0 이상이어야 한다.
- `빈 주문 테이블`은 `방문한 손님 수`를 변경할 수 없다.
- `주문 테이블 목록`을 조회할 수 있다.

### 주문

- 1개 이상의 등록된 `메뉴`로 `배달 주문`을 등록할 수 있다.
- 1개 이상의 등록된 `메뉴`로 `포장 주문`을 등록할 수 있다.
- 1개 이상의 등록된 `메뉴`로 `매장 주문`을 등록할 수 있다.
- `주문 유형`이 올바르지 않으면 등록할 수 없다.
- `메뉴`가 없으면 등록할 수 없다.
- `주문`은 `주문 항목`의 수량이 0 미만일 수 있다.
- `주문`의 경우 `주문 항목`의 수량은 0 이상이어야 한다.
- `배달 주소`가 올바르지 않으면 `배달 주문`을 등록할 수 없다.
  - `배달 주소`는 비워 둘 수 없다.
- `빈 주문 테이블`에는 `매장 주문`을 등록할 수 없다.
- `숨겨진 메뉴`는 주문할 수 없다.
- 주문한 `메뉴의 가격`은 실제 `메뉴 가격`과 일치해야 한다.
- `주문`을 접수한다.
- 접수 대기 중인 `주문만 접수`할 수 있다.
- 배달 `주문을 접수`되면 `배달 대행사`를 호출한다.
- `주문`을 `서빙`한다.
- 접수된 `주문`만 `서빙`할 수 있다.
- `주문`을 `배달`한다.
- `배달 주문`만 `배달`할 수 있다.
- `서빙된 주문`만 `배달`할 수 있다.
- `주문`을 `배달 완료`한다.
- 배달 중인 `주문`만 `배달 완료`할 수 있다.
- `주문`을 `완료`한다.
- 배달 `주문`의 경우 `배달 완료된 주문`만 `완료`할 수 있다.
- `포장 및 매장 주문`의 경우 `서빙된 주문`만 `완료`할 수 있다.
- `주문 테이블`의 모든 매장 `주문`이 `완료`되면 `빈 테이블`로 설정한다.
- 완료되지 않은 `매장 주문`이 있는 `주문 테이블`은 `빈 테이블`로 설정하지 않는다.
- `주문 목록`을 조회할 수 있다.

## 용어 사전

### 상품

| 한글명   | 영문명          | 설명                                        |
|-------|--------------|-------------------------------------------|
| 상품    | Product      | 메뉴를 구성하는 요소로, 판매되는 개별적인 품목.               |
| 상품 가격 | Product Price | 상품의 가격으로, 0원 이상이어야 한다.                    |
| 상품 이름 | Product Name | 판매되는 상품을 구별하기 위해 사용하는 단어로, 비속어가 포함될 수 없다. |


### 메뉴 그룹

| 한글명 | 영문명             | 설명                                       |
| ---- |-----------------|------------------------------------------|
| 메뉴 그룹 | Menu Group      | 여러 메뉴를 묶어 분류화한 그룹 단위. (예: 세트 메뉴, 사이드 메뉴) |
| 메뉴 그룹 이룸 | Menu Group Name | 메뉴 그룹을 구분하기 위해 사용하는 단어.                  |

### 메뉴

| 한글명   | 영문명       | 설명                                   |
|-------|-----------|--------------------------------------|
| 메뉴    | Menu      | 손님이 주문할 수 있는 다양한 상품을 모아 구성한 목록.      |
| 메뉴 이름 | Menu Name | 메뉴를 구분하기 위해 사용하는 단어로, 비속어가 포함될 수 없다. |
| 비속어   | Profanity | 불쾌감을 주는 단어나 문구.                      |
| 메뉴 상품 | Menu Product | 메뉴에 포함된 개별적인 상품.                     |
| 메뉴 가격 | Menu Price | 메뉴의 판매 가격으로, 0원 이상이어야 한다.            |
| 메뉴 목록 | Menu List | 등록된 모든 메뉴를 나열한 목록.                   |
| 메뉴 노출 | Display Menu | 메뉴를 화면이나 목록에 노출하는 것.                 |
| 메뉴 숨김 | Not Display Menu | 메뉴를 화면에서 숨김 처리하는 것.                  |

### 매장 주문

| 한글명     | 영문명               | 설명                                                                                                                                |
|---------|-------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| 주문      | Order             | 손님이 요청한 메뉴들의 모음으로, 주문 유형과 상태를 가진다.                                                                                                |
| 주문 가격   | Order Price       | 주문에 포함된 하나 이상의 주문 항목의 총합.                                                                                                         |
| 주문 유형   | Order Type        | 홀 주문, 포장, 배달 중에서 선택할 수 있는 주문 방식을 구분하는 체계.                                                                                         |
| 주문 상태   | Order Status      | 각 주문 유형이 진행되는 단계. <br/> 배달 주문 : 주문 대기 -> 주문 접수 -> 주문 서빙 -> 배달 중 -> 배달 완료 -> 완료 <br/> 홀 주문 및 포장 주문 : 주문 대기 -> 주문 접수 -> 주문 서빙 -> 완료 |
| 주문 항목   | Order LineItem    | 주문에 포함된 개별 메뉴와 해당 수량 및 가격 정보.                                                                                                     |
| 홀 주문    | EatIn Order       | 매장에서 주문하고 식사하는 방식.                                                                                                                |
| 포장 주문   | Takeout Order     | 주문한 음식을 포장하여 매장에서 직접 수령하는 방식.                                                                                                     |

### 주문 테이블 (매장 주문 전용)

| 한글명       | 영문명               | 설명                        |
|-----------|-------------------|---------------------------|
| 주문 테이블    | Order Table       | 매장 내에서 손님에게 음식을 제공하는 테이블. |
| 주문 테이블 이름 | Order Table Name  | 매장 내 테이블을 구분하기 위해 사용하는 단어. |
| 빈 주문 테이블  | Empty Order Table | 현재 사용 중이지 않은 주문 테이블.      |
| 방문 손님 수   | Number Of Guest   | 해당 주문 테이블에 앉아 있는 손님 수.    |

### 배달 주문
| 한글명       | 영문명      | 설명        |
|-----------|----------|-----------|
| 주문 종류(배달) | DELIVERY | 배달 주문 종류. |
| 주문 대기     | WAITING | 주문이 시스템에 접수되어, 아직 매장에서 확인 및 처리가 시작되지 않은 초기 상태.  |
| 주문 접수     | ACCEPTED | 매장이 주문을 확인하고 처리 절차를 개시한 상태.   |
| 주문 서빙     | Served | 음식이 완전히 준비되어 고객에게 제공되거나 픽업 준비가 완료된 상태 |
| 배달 중      | Delivering | 음식이 배달원에게 인계되어 고객에게 전달 중인 상태 |
| 배달 완료     | Delivered | 음식이 고객에게 성공적으로 전달되어 배송 과정이 종료된 상태. |
| 완료된 주문    | Completed Order | 주문의 모든 처리가 종료되고 결제 및 후속 작업이 마무리된 최종 상태 |
| 미완료된 주문   | Uncompleted Order | 주문 처리 과정 중 일부 단계가 완료되지 않은 상태 |
| 배달 대행사  | Delivery Agency   | 외부 음식 배달 서비스 제공 파트너.      |

### 공통
| 한글명       | 영문명      | 설명        |
|-----------|----------|-----------|
| 비속어   | Profanity | 모욕적이거나 불쾌감을 주는 단어나 문구.                    |

## 모델링
### Product
속성
- `Product`는 `name`과 `price`를 가지고 있다.

행위
- `Product`를 `Product 등록 정책`에 따라 등록할 수 있다. 
  - `name`과 `price`를 입력하여 등록 가능하다.
  - `Product 등록 정책`
    - `name`과 `price`은 반드시 입력되어야 한다.
    - `name`은 공백만 입력할 수 없다.
    - `name`은 `Profanity`가 포함될 수 없다.
    - `price`은 0원 이상이어야 한다.
- `Product`의 `price`를 `Product 변경 정책`에 따라 변경할 수 있다.
  - `Product 변경 정책`
    - `price`은 0원 이상이어야 한다.
    - `Menu`의 `price`이 속한 `product`의 가격 총합을 초과하면, `Not Display Menu`가 된다.
- `Product` 목록을 조회할 수 있다.

### MenuGroup
속성
- `MenuGroup`은 `name`을 가지고 있다.

행위
- `MenuGroup`를 `MenuGroup 등록 정책`에 따라 등록할 수 있다.
  - `name`을 입력하여 등록 가능하다.
  - `MenuGroup 등록 정책`
    - `name`은 반드시 입력되어야 한다.
    - `name`은 공백만 입력할 수 없다.
- `MenuGroup` 목록을 조회할 수 있다.

### Menu
속성
- `Menu`는 `name`, `price`, `menuGroup`, `displayed`, `menuProduct`를 가지고 있다.

행위
- `Menu`를 `Menu 등록 정책`에 따라 등록할 수 있다.
  - `name`, `price`, `menuGroup`, `displayed`를 입력하여 등록 가능하다.
  - `Menu 등록 정책`
    - 1개 이상의 `MenuProduct`가 있어야 한다.
    - `MenuProduct`의 `quantity`는 `0`이상 이어야한다.
    - `name`, `price`, `menuGroup`은 반드시 입력되어야 한다.
    - `name`은 공백만 입력할 수 없다.
    - `name`은 `profanity`가 포함될 수 없다.
      - `PurgomalumClient`을 사용하여 올바른 `name`인지 확인한다.
    - `price`는 0이상 이어야한다.
    - `Menu`의 `price`는 해당 `Menu`에 속한 `Product`들의 총 가격을 초과할 수 없다.
- `Menu`의 `price`은 `Menu 가격 정책`에 따라 변경할 수 있다.
  - `Menu 가격 정책`
    - `price`은 반드시 입력되어야 한다. 
    - `price`은 0원 이상 이어야한다.
    - `Menu`의 `price`는 해당 `Menu`에 속한 `Product`들의 총 가격을 초과할 수 없다.
- `Menu 노출 정책`에 따라 `Menu`의 `displayed`를 변경할 수 있다.
  - `displayed`를 `false`로 변경하여 `Not Display Menu`로 변경한다.
  - `displayed`를 `true`로 변경하여 `Display Menu`로 변경한다.
  - `Menu 노출 정책`
    - `Menu`의 `price`는 해당 `Menu`가 속한 `Product`들의 총 가격을 초과할 수 없다.
- `Menu`의 목록을 조회할 수 있다.

### OrderTable
속성
- `OrderTable`은 `name`, `numberOfCustomer`, `occupied`를 가지고 있다.

행위
- `OrderTable`를 `OrderTable 등록 정책`에 따라 등록할 수 있다.
  - `name`, `numberOfCustomer`, `occupied`를 입력하여 등록 가능하다.
  - `OrderTable 등록 정책`
    - `name`은 반드시 입력되어야 한다.
    - `name`은 공백만 입력할 수 없다.
    - `numberOfCustomer`은 반드시 입력되어야 한다.
    - `numberOfCusomer`은 0명 이상 이어야한다.
    - `occupied`은 반드시 입력되어야 한다.
- `OrderTable`의 `occupied`를 `OrderTable Occupy 변경 정책`에 따라 변경할 수 있다.
  - `occupied`를 `true`로 변경하여 `Occupied Order Table`로 변경한다.
  - `occupied`를 `false`로 변경하여 `Empty Order Table`로 변경한다.
  - `OrderTable Occupy 변경 정책`
    - `Order Table`의 `occupied`가 `true`인 경우 `Empty Order Table`로 변경할 수 없다.
- `OrderTable`의 `numberOfGuests`를 `OrderTable Guests 변경 정책`에 따라 변경할 수 있다.
  - `OrderTable Guests 변경 정책`
    - `numberOfGuests`는 `음수`로 변경할 수 없다.
    - `occupied`가 `false`인 경우 변경할 수 없다.
- `OrderTable` 목록을 조회할 수 있다.

### Order
속성
- `Order`는 `orderType`, `orderStatus`, `orderDateTime`, `OrderLineItem`, `deliveryAddress`, `OrderTable`을 가진다.

행위
- `Order` 목록을 조회할 수 있다.

#### OrderType에 따른 OrderStatus 변화

![orderStatus drawio](https://github.com/user-attachments/assets/fcb519d1-ff3c-4c7e-8c0c-2bd21588648e)

#### 1) DeliveryOrder 과정

- ① `deliveryOrder`는 `DeliveryOrder 등록 정책`에 따라 등록할 수 있다.
  - `DeliveryOrder 등록 정책`
    - `orderType`이 `DELIVERY`인 경우만 등록 가능하다. 
    - 반드시 1개 이상의 `OrderLineItem`이 있어야 한다.
    - `Not Display Menu`는 등록할 수 없다.
    - `OrderLineItem`의 `price`는 `Menu`의 `price`와 동일해야 한다.
    - `deliveryAddress`는 반드시 입력되어야 한다.
    - `deliveryAddress`는 공백만 입력할 수 없다.
  - `Order`가 정상적으로 등록되면 `waitingOrder`가 된다

- ② `accetedOrder`는 `AcceptedOrder 정책`에 따라 변경할 수 있다.
  - `AcceptedOrder 정책`
    - `orderStatus`가 `WAITING`인 경우만 변경 가능하다.
  - `DeliveryAgent`에게 `Order`의 총 가격과 `deliveryAddress`를 전달한다

- ③ `servedOrder`는 `ServedOrder 정책`에 따라 변경할 수 있다.
  - `ServedOrder 정책`
    - `orderStatus`가 `ACCEPTED`인 경우만 변경 가능하다.

- ④ `deliveringOrder`는 `DeliveringOrder 정책`에 따라 변경할 수 있다.
  - `DeliveringOrder 정책`
    - `orderStatus`가 `SERVED`인 경우만 변경 가능하다.

- ⑤ `deliveredOrder`는 `DeliveredOrder 정책`에 따라 변경할 수 있다.
  - `DeliveredOrder 정책`
    - `orderStatus`가 `DELIVERING`인 경우만 변경 가능하다.

- ⑥ `completedOrder`는 `CompletedOrder 정책`에 따라 변경할 수 있다.
  - `CompletedOrder 정책`
    - `orderStatus`가 `DELIVERED`인 경우만 변경 가능하다.

#### 2) TakeOutOrder 과정

- ① `takeOutOrder`는 `TakeOutOrder 등록 정책`에 따라 등록할 수 있다.
  - `TakeOutOrder 등록 정책`
    - `orderType`이 `TAKEOUT`인 경우만 등록 가능하다.
    - 반드시 1개 이상의 `OrderLineItem`이 있어야 한다.
    - `Not Display Menu`는 등록할 수 없다.
    - `OrderLineItem`의 `price`는 `Menu`의 `price`와 동일해야 한다.
  - `Order`가 정상적으로 등록되면 `waitingOrder`가 된다

- ② `accetedOrder`는 `AcceptedOrder 정책`에 따라 변경할 수 있다.
  - `AcceptedOrder 정책`
    - `orderStatus`가 `WAITING`인 경우만 변경 가능하다.

- ③ `servedOrder`는 `ServedOrder 정책`에 따라 변경할 수 있다.
  - `ServedOrder 정책`
    - `orderStatus`가 `ACCEPTED`인 경우만 변경 가능하다.

- ④ `completedOrder`는 `CompletedOrder 정책`에 따라 변경할 수 있다.
  - `CompletedOrder 정책`
    - `orderStatus`가 `SERVED`인 경우만 변경 가능하다.

#### 3) EatIntOrder 과정

- ① `eatIntOrder`는 `EatIntOrder 등록 정책`에 따라 등록할 수 있다.
  - `EatIntOrder 등록 정책`
    - `orderType`이 `EAT_IN`인 경우만 등록 가능하다.
    - 반드시 1개 이상의 `OrderLineItem`이 있어야 한다.
    - `Not Display Menu`는 등록할 수 없다.
    - `OrderLineItem`의 `price`는 `Menu`의 `price`와 동일해야 한다.
  - `Order`가 정상적으로 등록되면 `waitingOrder`가 된다

- ② `accetedOrder`는 `AcceptedOrder 정책`에 따라 변경할 수 있다.
  - `AcceptedOrder 정책`
    - `orderStatus`가 `WAITING`인 경우만 변경 가능하다.

- ③ `servedOrder`는 `ServedOrder 정책`에 따라 변경할 수 있다.
  - `ServedOrder 정책`
    - `orderStatus`가 `ACCEPTED`인 경우만 변경 가능하다.

- ④ `completedOrder`는 `CompletedOrder 정책`에 따라 변경할 수 있다.
  - `CompletedOrder 정책`
    - `orderStatus`가 `SERVED`인 경우만 변경 가능하다.
  - `pendingOrderTable`이 아닌 경우, `clearedTable`로 만든다.
    - `numberOfCustomer`을 `0`으로 변경한다.
    - `occupied`를 `false`로 변경한다.
