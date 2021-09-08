# 키친포스

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

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | Product | 키친포스에서 판매하는 제품(음식)을 뜻한다. |
| 상품명 | Product Name | 상품의 이름 |
| 상품 가격 | Product Price | 상품의 가격 |
| 비속어 | Profanity | 욕설등을 포함한 비속어 |
| 메뉴에 속한 상품 | MenuProduct | 메뉴등록시 포함된 1개이상의 상품들 |
| 메뉴 | Menu | 1개 이상의 상품을 묶은 단위로 상품과 별도로 가격을 가진다. |
| 메뉴를 등록 | Menu Create | 새로운 메뉴를 생성한다. |
| 메뉴 가격 | Menu Price | 메뉴의 가격 |
| 메뉴 상품 수량 | Menu Price Quantity | 메뉴에 포함된 상품의 수량 |
| 메뉴에 속한 상품 금액의 합 | Menu Product Price Sum | 메뉴에 포함된 상품들의 가격 * 수량의 합 |
| 메뉴를 노출 | Display | 메뉴가 사용자에게 보여진다. |
| 메뉴를 숨김 | Hide | 메뉴가 사용자에게 보여지지 않는다. |
| 메뉴그룹 | MenuGroup | 1개 이상의 메뉴를 묶은 단위로 이름을 가진다. |
| 메뉴그룹명 | MenuGroup Name | 메뉴그룹의 이름 |
| 메뉴그룹을 등록 | MenuGroup Create | 새로운 메뉴그룹을 생성한다. |
| 주문 테이블 | OrderTable | 매장에서 사용하는 테이블로 매장에 방문한 손님들이 사용한다. |
| 주문 테이블을 등록 | OrderTable Create | 새로운 주문 테이블을 생성한다. |
| 주문 테이블명 | OrderTable Name | 주문 테이블의 이름 |
| 빈 테이블 | Empty Table | 사용하는 손님이 없는 손님을 받을수 있는 테이블 |
| 빈 테이블을 해지 | Clear | 방문한 손님이 테이블을 사용하여 테이블의 상태를 변경한다. |
| 빈 테이블로 설정 | Empty | 방문한 손님의 테이블 사용이 끝나고 다른 손님이 사용할수 있도록 상태를 변경한다. |
| 방문한 손님 수 | NumberOfGuests | 하나의 주문 테이블에 앉은 손님의 수 |
| 완료되지 않은 주문이 있는 주문 테이블 | Not Order Complete Order Table | 해당 테이블의 주문이 완료상태가 아닌 주문테이블 |
| 주문 | Order | 매장/배달/포장 주문으로 구분할 수 있고 주문유형, 주문항목등의 정보를 가지고 있다. |
| 주문을 등록 | Order Create | 새로운 주문을 생성한다. |
| 주문유형 | OrderType | 매장/배달/포장 유형이 존재하고 주문형태에 대한 구분값이다. |
| 주문한 메뉴 | Order Menu | 주문에 포함된 메뉴 |
| 주문항목 | Order Line Item | 주문에 포함된 메뉴, 수량, 가격에 대한 내용 |
| 배달 주문 | Delivery Order | 주문유형이 배달(Delivery)인 주문. 배달주소가 필요하고 고객이 주문한 메뉴가 배달된다. |
| 배달 주소 | Delivery Address | 배달주문시 해당 주문이 배달될 주소 |
| 매장 주문 | Eat in Order | 주문유형이 매장(Eat_in)인 주문. 주문테이블 정보가 필요하고 주문한 메뉴가 고객이 사용중인 테이블에 제공된다. |
| 포장 주문 | Takeout Order | 주문유형이 포장(Takeout)인 주문. 매장주문과 동일하지만 고객이 테이블을 사용하지 않기 때문에 주문테이블 정보가 필요없다. |
| 접수 대기 | Order Wait | 주문이 됐지만 아직 확인되지 않은 상태 |
| 주문 접수 | Order Accepted | 고객이 주문한 내용에 대해서 주문이 확인 된 상태 |
| 주문을 서빙 | Order Served | 손님의 주문이 접수된 이후 주문된 메뉴를 제공한 상태 |
| 주문을 배달시작 | Start Delivery | 배달주문의 배달을 시작 |
| 주문을 배달 | Order Delivery | 배달주문이 주문한 고객에서 배달되는 상태 |
| 배달 완료 | Delivery Complete | 배달주문이 주문한 고객에게 배달이 완료된 상태 |
| 주문 완료 | Order Complete | 주문에 대한 처리가 완료된 상태(매장주문 - 고객의 주문에 대해서 계산까지 완료되고 빈 테이블로 설정, 배달주문 - 배달 완료 이후 주문완료 처리) |
| 배달 대행사 | Kitchen Riders | 배달주문에 대해 배달을 대행해주는 업체 |
| 배달 대행사 호출 | Request Delivery | 배달주문에 대해 배달대행사에 배달을 요청한다. |


## 모델링
- 상품
  - ProductService 에서 Product를 생성한다..
  - Product는 Product Name과 Product Price를 가지고 있다.
  - Product를 생성한다.
    - Product 생성시 Product Price는 0원 이상이어야 한다.
    - Product 생성시 Product Name에 Profanity를 포함할 수 없다.
  - Product Price를 변경한다.
    - Product Price 변경시 해당 Product를 포함한 MenuProduct의 가격도 변경된다.
    - Product Price가 변경될때 Menu Price가 MenuProduct Price Sum 보다 크면 Menu가 hide 된다.

- 메뉴그룹
  - MenuGroupService 에서 MenuGroup를 생성한다.
  - MenuGroup을 생성한다.
    - MenuGroup 생성시 MenuGroup Name 이 필요하다.

- 메뉴
  - MenuService 에서 Menu를 생성한다.
  - Menu등록시 미리 등록된 Product가 필요하다.
  - Menu를 생성한다.
    - Menu는 메뉴에 속한 상품에 대한 정보인 MenuProduct를 가진다.
    - MenuProduct의 수량은 0보다 커야한다.
    - MenuPrice는 0원 이상이어야 한다.
    - MenuProduct Price Sum은 Menu Price보다 크거나 같아야 한다.
    - Menu는 MenuGroup정보를 포함한다.
  - Menu Price를 변경한다.
    - Menu Price는 0원 이상이어야 한다.
    - MenuProduct Price Sum은 Menu Price보다 크거나 같아야 한다.
  - Menu를 Display한다.
    - Menu Price가 MenuProduct Price Sum보다 크다면 Hide 한다.
  - Menu를 Hide 한다.

- 주문 테이블
  - OrderTableService 에서 OrderTable을 생성한다.
  - OrderTable을 생성한다.
    - OrderTable 은 Name을 가져야 한다.
  - OrderTable을 Clear 한다.
  - OrderTable을 Empty 한다.
    - OrderTable의 정보를 가지고 있는 Order의 상태가 Complete가 아니면 Empty할 수 없다.
  - NumberOfGuests를 변경한다.
    - NumberOfGuests는 0 이상이어야 한다.
    - OrderTable의 상태가 Empty상태가 아니어야 한다.

- 주문
  - OrderService에서 Order를 생성한다.
  - Order를 생성한다.
    - Order는 OrderType을 가진다.
    - Order는 OrderStatus를 가진다.
    - OrderType은 IN_EAT, DELIVERY, TAKEOUT 중 하나여야 한다.
    - Order는 Menu정보를 포함하고 있는 OrderLineItem 정보를 가진다.
    - OrderLineItem에 포함된 Menu가 존재해야 한다.
    - OrderLineItem에 포함된 Menu는 Display 상태여야 한다.
    - OrderLineItem의 Price는 (해당 Menu의 Menu Price) * (OrderLineItem의 수량)과 같아야 한다.
    - OrderType이 IN_EAT인 Order를 생성한다.
      - OrderType이 IN_EAT일때 OrderLineItem의 수량은 0미만일수 있다.
      - OrderTable 정보를 가진다.
      - OrderTable의 상태는 Clear여야 한다.
    - OrderType이 DELIVERY인 Order를 생성한다.
      - OrderLineItem의 수량은 0 이상이어야 한다.
      - DeliveryAddress 값이 존재해야 한다.
    - OrderType이 TAKEOUT인 Order를 생성한다.
      - OrderLineItem의 수량은 0 이상이어야 한다.
  - Order를 Accept한다.
    - Order가 존재하지 않으면 예외처리한다.
    - OrderStatus가 WAITING 상태여야 한다.
      - OrderStatus가 WAITING 상태가 아니면 예외처리 한다.
    - OrderType이 DELIVERY일때 OrderLineItem의 Price는 0일수 없다.
    - OrderType이 DELIVERY일때 Kitchen Riders를 호출한다.
    - OrderStatus를 ACCEPTED로 변경한다.
  - Order를 serve한다.
    - Order가 존재하지 않으면 예외처리한다.
    - OrderStatus가 ACCEPTED 상태여야 한다.
      - OrderStatus가 ACCEPTED가 아니면 예외처리한다.
    - OrderStatus를 SERVED 로 변경한다.
  - Order를 startDelivery 한다.
    - Order가 존재하지 않으면 예외처리한다.
    - OrderType이 DELIVERY가 아니면 예외처리한다.
    - OrderStatus가 SERVED 상태여야 한다
      - OrderStatus가 SERVED가 아니면 예외처리한다.
    - OrderStatus를 DELIVERING으로 변경한다.
  - Order를 completeDelivery 한다.
    - Order가 존재하지 않으면 예외처리한다.
    - OrderStatus가 DELIVERING 상태여야 한다
      - OrderStatus가 DELIVERING이 아니면 예외처리한다.
    - OrderStatus를 DELIVERED로 변경한다.
  - Order를 Complete 한다.
    - Order가 존재하지 않으면 예외처리한다.
    - OrderType이 DELIVERY 이면 OrderStatus가 DELIVERED 상태여야 한다.
      - OrderStatus가 DELIVERED가 아니면 예외처리한다.
    - OrderType이 TAKEOUT이나 EAT_IN이면 SERVED 상태여야 한다.
      - OrderStatus가 SERVED가 아니면 예외처리힌다.
    - OrderStatus를 COMPLETED로 변경한다.
    - OrderType이 EAT_IN이면 OrderTable을 Empty 한다.
