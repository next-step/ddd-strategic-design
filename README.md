# 키친포스

## 퀵 스타트

```sh
cd docker
docker compose -p kitchenpos up -d
```

## 요구 사항

### 상품
#### 속성
- `Product`는 `price` / `name` 을 가진다
#### 기능
- 상품을 등록할 수 있다.
- 상품을 조회할 수 있다.
- 상품가격을 변경할 수 있다.
#### 정책
- 공통 정책
  - `상품가격`은 0원 이상이어야 한다.
  - `상품이름`은 필수값이고, 비속어가 포함될 수 없다.
- 상품가격 변경 정책
  - `상품가격`을 변경할 때 `메뉴가격`이 `메뉴상품가격 합`초과인 `메뉴`는 `비노출` 된다.

### 메뉴 그룹
#### 속성
- `MenuGroup`은 `name`을 가진다.
#### 기능
- `메뉴 그룹`을 등록할 수 있다.
- `메뉴 그룹`을 조회할 수 있다.
#### 정책
- 공통 정책
  - `메뉴 그룹`의 이름은 필수값이다.

### 메뉴
#### 속성
- `Menu`는 1개의 `MenuGroup` / `price` / `name` / 노출 여부를 결정하는 `displayed` / 메뉴에 속한 상품들인 `MenuProduct`를 가진다.
#### 기능
- 메뉴를 등록할 수 있다.
- 메뉴를 조회할 수 있다.
- 메뉴가격을 변경할 수 있다.
- 메뉴를 노출/비노출 할 수 있다.
#### 정책
- 공통 정책
  - `메뉴가격`는 0원 이상이어야 한다.
  - `메뉴이름`은 필수값이고, 비속어가 포함될 수 없다.
- 메뉴 생성 정책
  - `메뉴상품`의 `수량`은 0개 이상이어야 한다.
  - `메뉴가격`은 `메뉴상품가격 합`이하여야 한다.
  - `메뉴`는 1개의 `메뉴 그룹`에 속한다.
- 메뉴가격 변경 정책
  - `메뉴가격`은 `메뉴상품가격 합`이하여야 한다.
- 메뉴노출 변경 정책
  - `메뉴`를 `비노출`하는데는 아무런 제약이 없다
  - `메뉴`를 `노출`하려면 `메뉴가격`이 `메뉴상품가격 합`이하여야 한다


### 주문 테이블
#### 속성
- `OrderTable`은 `name` / 손님 수를 의미하는 `number of guests` / 점유 여부를 의미하는 `occupied`를 가진다.
#### 기능
- 주문 테이블을 등록할 수 있다.
- 주문 테이블을 점유/비점유 할 수 있다.
- 주문 테이블의 손님 수를 변경할 수 있다.
#### 정책
- 공통 정책
  - `주문 테이블`의 이름은 필수값이다.
- 주문 테이블 등록 정책
  - 등록 시 초기값은 `손님 수`는 0명, `점유 여부`는 `비점유`이다.
- 주문 테이블 점유 정책
  - `주문 테이블`을 `점유`하는데는 아무런 제약이 없다
  - `주문 테이블`의 `주문`이 `완료`되지 않은 경우 `비점유`할 수 없다
  - `주문 테이블`이 `비점유`되면 `손님 수`를 0명으로 설정한다.
- 주문 테이블 손님 수 변경 정책
  - `주문 테이블`이 `점유`되었을 때만 `손님 수`를 변경할 수 있다.

### 주문
#### 속성
- `Order`는 주문유형을 의미하는 `order type` / 주문상태를 의미하는 `order status` / 주문 테이블을 의미하는 `order table`/ 주문메뉴를 의미하는 `order menu` / 배달 주소를 의미하는 `delivery address`를 가진다.
#### 기능
- 주문을 조회한다
- 주문을 등록한다
- 주문을 접수한다
- 주문을 서빙한다
- 배달주문이 배달중이다
- 배달주문을 배달완료한다
- 주문을 완료한다
#### 정책
- 공통 정책
  - 1개 이상의 `주문 메뉴`를 가진다.
  - `비노출 메뉴`는 주문할 수 없다.
  - `메뉴가격`은 실제 `메뉴가격`과 일치해야 한다.

- 주문 등록 정책
  - `주문`을 등록하면 `주문대기` 상태이다 
  - 홀주문 등록 정책 
    - `홀주문`은 `주문메뉴 수량`제약이 없다.
    - `홀주문`은 `점유`된 `주문테이블`에 등록해야 한다.
  - 포장주문 등록 정책
    - `포장주문`은 `주문메뉴 수량`이 0개 이상이다.
  - 배달주문 등록 정책
    - `배달주문`은 `주문메뉴 수량`이 0개 이상이다.
    - `배달주소`는 비워 둘 수 없다.

- 주문 접수 정책
  - `주문대기`인 주문만 `접수`할 수 있다.
  - `배달주문`을 `접수`하면 `배달 대행사`를 호출한다.

- 주문 서빙 정책
  - `주문접수`인 주문만 `서빙`할 수 있다. 

- 주문 배달중 정책
  - `서빙`인 `배달주문`만 배달할 수 있다. 

- 주문 배달완료 정책
  - `배달중`인 `배달주문`만 배달완료할 수 있다.

- 주문 `완료` 정책
  - `배달완료`인 `배달주문`을 `완료`할 수 있다.
  - `서빙`인 `홀주문`을 `완료`할 수 있다.
  - `서빙`인 `포장주문`을 `완료`할 수 있다.
  - `주문 테이블`의 모든 `홀주문`이 완료되면 `비점유`하고 `손님 수`를 0명으로 설정한다.

## 용어 사전

| 한글명     | 영문명                     | 설명                                                                                                                                                                             |
|---------|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 상품      | Product                 | 메뉴에 속해있는 판매단위  ex)양념치킨                                                                                                                                                         |
| 메뉴 그룹   | Menu Group              | 메뉴의 상위개념으로 비슷한 메뉴들끼리 묶여있다 ex)추천메뉴                                                                                                                                              |
| 메뉴      | Menu                    | 손님이 주문하는 단위 ex)치킨 두마리 세트                                                                                                                                                       |
| 메뉴 상품   | Menu Product            | 메뉴에 속한 상품                                                                                                                                                                      |
| 메뉴상품가격 합 | Total Menu Product Price | 메뉴에 속한 상품들의 가격과 수량을 곱한 값 (메뉴에 속한 상품의 (가격 * 수량))                                                                                                                                |
| 메뉴 가격   | Menu Price              | 메뉴를 판매하는 가격                                                                                                                                                                    |
| 노출 메뉴   | Displayed Menu          | 손님에게 보이는 메뉴                                                                                                                                                                    |
| 비노출 메뉴  | Not Displayed Menu      | 손님에게 보이지 않는 메뉴                                                                                                                                                                 |
| 주문 테이블  | Order Table             | 주문을 입력하는 테이블 (현재는 홀주문만 제공)                                                                                                                                                     |
| 비점유 테이블 | Not Occupied Table      | 손님이 매장의 테이블에서 나갔다                                                                                                                                                              |
| 점유 테이블  | Occupied Table          | 손님이 매장의 테이블에 앉았다                                                                                                                                                               |
| 손님      | Guest                   | 주문을 하는 사람                                                                                                                                                                      |
| 손님 수    | Number Of Guest         | 테이블에 앉은 사람의 수                                                                                                                                                                  |
| 배달주문    | Delivery Order          | 배달로 주문한다                                                                                                                                                                       |
| 포장주문    | TakeOut Order           | 방문해서 포장한다                                                                                                                                                                      |
| 홀주문     | EatIn Order             | 매장 내부에서 먹고간다                                                                                                                                                                   |
| 주문 유형   | Order Type              | 주문방식의 종류들 (배달/포장/홀)                                                                                                                                                            |
| 배달 대행사  | Delivery Agency         | 배달을 대행해주는 업체                                                                                                                                                                   |
| 주문 상태   | Order Status            | 주문의 진행 현황 (주문대기/주문접수/주문서빙/배달중/배달완료/완료)<br/><br/>  배달주문 (주문대기 -> 주문접수 -> 주문서빙 -> 배달중 -> 배달완료 -> 완료) <br/>홀주문 (주문대기 -> 주문접수 -> 주문서빙 -> 완료) <br/>포장주문 (주문대기 -> 주문접수 -> 주문서빙 -> 완료) |
| 주문대기    | Waiting                 | 고객이 주문을 생성한 상태                                                                                                                                                                 |
| 주문접수    | Accepted                | 매장이 주문을 접수한 상태                                                                                                                                                                 |
| 주문서빙    | Served                  | 매장이 주문을 완성한 상태                                                                                                                                                                 |
| 배달중     | Delivering              | 주문에 대한 배달을 진행중인 상태                                                                                                                                                             |
| 배달완료    | Delivered               | 주문에 대한 배달을 완료한 상태                                                                                                                                                              |
| 완료      | Complete                | 주문의 진행이 끝난 최종상태                                                                                                                                                                |

## 모델링

### Product
- Product는 식별자와 price, name을 가진다.
- Product의 name은 필수값이고, PurgomalumClient를 통해 비속어가 포함되어 있지 않은지 확인한다.
- Product의 price를 변경
  - Product를 포함한 Menu들 중  `MenuPrice <= ProductPrice * MenuProductQuantity`를 만족하지 못하는 Menu는 `Not Displayed`된다
### MenuGroup
- MenuGroup는 식별자와 name을 가진다.
- MenuGroup의 name은 필수값이다.
### Menu
- Menu는 식별자와 MenuGroup, price, name, displayed, MenuProducts를 가진다.
- Menu의 name은 필수값이고, PurgomalumClient를 통해 비속어가 포함되어 있지 않은지 확인한다.
- Menu는 1개의 MenuProduct를 반드시 가진다
- MenuProduct의 quantity는 0개 이상이어야 한다.
- Menu 생성
  - `MenuPrice <= ProductPrice * MenuProductQuantity`를 만족하지 못하면 Menu는 생성되지 못한다
- Price 변경
  - `MenuPrice <= ProductPrice * MenuProductQuantity`를 만족하지 못하면 price는 변경되지 못한다
### OrderTable
- OrderTable은 식별자와 name, numberOfGuests, occupied를 가진다.
- OrderTable의 초기값은 numberOfGuests는 0명, occupied는 Not Occupied이다.
- OrderTable의 name은 필수값이다.
- OrderTable Occupied 변경
  - OrderTable을 Occupied할 때 제약은 없다
  - OrderTable에 완료되지 않은 Order가 존재하면 NotOccupied 할 수 없다
- OrderTable의 numberOfGuests 변경
  - OrderTable이 Occupied일 때만 변경할 수 있다

### Order
- Order는 식별자와 orderType, orderStatus, orderMenus를 가진다. 
- EatInOrder는 OrderTable를 가진다
- DeliveryOrder는 deliveryAddress를 가진다
- TakeOutOrder는 존재한다

- Order를 생성하면 Waiting가 된다
  - EatInOrder 생성
    - EatInOrder는 Occupied인 OrderTable에 등록해야한다
    - EatInOrder는 OrderMenus의 quantity 제약이 없다
  - DeliveryOrder 생성
    - DeliveryOrder는 OrderMenus의 quantity가 0개 이상이어야 한다
    - DeliveryOrder는 deliveryAddress가 필수값이다
  - TakeOutOrder 생성
    - TakeOutOrder는 OrderMenus의 quantity가 0개 이상이어야 한다

- Order를 접수하면 Accepted로 변경한다
  - DeliveryOrder는 `DeliveryAgency`를 호출한다
- Order를 서빙하면 Served로 변경한다
- DeliveryOrder의 배달이 시작되면 Delivering로 변경한다
- DeliveryOrder의 배달이 완료되면 Delivered로 변경한다
- Order를 완료하면 Complete로 변경한다
  - EatInOrder는 등록된 OrderTable의 모든 Order가 완료된 상태면, OrderTable을 NotOccupied로 변경하고 numberOfGuests를 0명으로 변경한다
