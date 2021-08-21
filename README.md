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

### 상품

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | Product | 메뉴를 구성하는 물품이다. |
| 상품 이름 | ProductName | 상품의 이름이다. 비속어가 포함될 수 없다. |
| 상품 가격 | ProductPrice | 상품의 값이다. 0원 이상이다. |
| 상품 등록 | register | 상품을 등록한다. |
| 상품 가격 변경 | changePrice | 상품의 가격을 변경한다. |

### 메뉴 그룹

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 그룹 | MenuGroup | 메뉴들을 하나로 묶어서 보여주는 그룹이다. |
| 메뉴 그룹 이름 | MenuGroupName | 메뉴 그룹의 이름이다. |
| 메뉴 그룹 등록 | register | 메뉴 그룹을 등록한다. |

### 메뉴

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | Menu | 여러 개의 상품의 묶음으로, 손님이 주문하는 대상이다. 또한 특정 메뉴 그룹에 속한다. |
| 메뉴 상품 | MenuProduct | 메뉴에 속한 상품이며, 수량이 0개 이상이고 메뉴 가격의 합이 메뉴의 가격보다 크거나 같아야 한다. |
| 메뉴 가격 | MenuPrice | 메뉴의 값이다. 0원 이상이다. |
| 메뉴 이름 | MenuName | 메뉴의 이름이다. 비속어가 포함될 수 없다. |
| 메뉴 전시 여부 | displayed | 메뉴 전시 상태다. |
| 메뉴 등록 | register | 메뉴를 등록한다. |
| 메뉴 가격 변경 | changePrice | 메뉴의 가격을 변경한다. |
| 메뉴 전시 | display | 메뉴를 전시한다. 메뉴의 가격이 메뉴 상품의 수량 * 가격 보다 작거나 같아야 한다. |
| 메뉴 숨김 | hide | 메뉴를 숨긴다. |

### 주문 테이블

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 테이블 | OrderTable | 매장식사 주문일 경우, 손님들이 식사를 하고 가는 테이블이다. |
| 주문 테이블 이름 | OrderTableName | 주문 테이블의 이름이다. |
| 방문 손님 수 | NumberOfGuests | 주문 테이블을 방문한 손님 수이다. 0명 이상이다. |
| 비어 있음 여부 | empty | 주문 테이블의 비어 있음 여부다. |
| 주문 테이블 등록 | register | 주문 테이블을 등록한다. |
| 주문 테이블 채우기 | fill | 주문 테이블을 채운다. |
| 주문 테이블 정리 | clear | 주문 테이블을 정리한다. 완료되지 않은 주문이 있으면 불가능하다. |
| 주문 테이블 방문 손님 수 변경 | changeNumberOfGuests | 주문 테이블을 방문한 손님 수를 변경한다. 빈 테이블은 불가능하다. |

### 주문

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 | Order | 1개 이상의 메뉴를 손님에게 제공하는 것을 뜻한다. 없는 메뉴나 숨겨진 메뉴는 주문할 수 없다. |
| 주문 항목 | OrderLineItem | 주문의 내용을 뜻하며 메뉴, 가격, 수량을 담고 있다. 배달이나 포장시 수량은 0개 이상이어야 한다. 각 주문 항목의 가격은 메뉴 가격과 일치해야 한다. |
| 주문 유형 | OrderType | 주문의 유형으로, 배달, 포장, 매장식사 3가지의 경우가 있다. |
| 주문 유형 - 배달 주문 | DELIVERY | 배달 주소까지 배달해주는 주문이다. 배달 주소가 있어야 주문이 가능하다. |
| 주문 유형 - 포장 주문 | TAKEOUT | 포장해서 가져갈 수 있는 주문이다. |
| 주문 유형 - 매장식사 주문 | EAT_IN | 매장 내 주문 테이블에서 식사하는 주문이다. 찬 테이블에 주문이 가능하다. |
| 배달 주소 | DeliveryAddress | 배달 주문 시, 배달하는 주소다. |
| 주문 등록 | register | 손님의 주문을 등록한다. |
| 주문 접수 | accept | 사장님이 접수 대기중인 주문을 접수한다. 배달 주문인 경우, 배달 대행사를 호출한다. 주문 대기 상태여야 한다. |
| 주문 서빙 | serve | 사장님이 접수된 주문을 서빙한다. 주문 접수됨 상태여야 한다. |
| 주문 배달 시작 | startDelivery | 배달 대행사가 서빙된 배달 주문을 배달 시작한다. 배달 주문이어야 하고 서빙됨 상태여야 한다. |
| 주문 배달 완료 | completeDelivery | 배달 대행사가 배달중인 배달 주문을 배달 완료한다. 배달 중 상태여야 한다. |
| 주문 완료 | complete | 주문을 완료한다. 배달 주문이라면 배달됨 상태여야 하고 포장이나 매장식사 주문인 경우 서빙됨 상태여야 한다. |
| 주문 상태 | OrderStatus | 주문의 상태다. |
| 주문 상태 - 대기중 | WAITING |  |
| 주문 상태 - 접수됨 | ACCEPTED |  |
| 주문 상태 - 서빙됨 | SERVED |  |
| 주문 상태 - 배달중 | DELIVERING |  |
| 주문 상태 - 배달됨 | DELIVERED |  |
| 주문 상태 - 완료됨 | COMPLETED |  |

## 모델링

### 상품

- `Product` 를 `register` 할 수 있다.
- `Product` 는 `ProductName`, `ProductPrice` 을 가진다.
- `ProductName` 은 비속어이거나 빈 값일 수 없다.
- `ProductPrice` 은 0원 이상이다.
- `Product` 의 `ProductPrice` 을 `change` 할 수 있다.

### 메뉴 그룹

- `MenuGroup` 을 `register` 할 수 있다.
- `MenuGroup` 은 `MenuGroupName` 을 가진다.

### 메뉴

- `Menu` 를 `register` 할 수 있다.
- `Menu` 는 여러 개의 `MenuProduct` 로 구성된다.
- `Menu` 는 특정한 `MenuGroup` 에 속한다.
- `Menu` 는 `MenuName`, `MenuPrice`, `displayed` 을 가진다.
- `MenuName` 은 비속어이거나 빈 값일 수 없다.
- `MenuPrice` 은 0원 이상이다.
- `displayed` 는 `Menu`의 전시 여부를 나타낸다.
- 각 `MenuProduct` 의 수량은 0개 이상이다.
- 모든 (`MenuProduct` 의 수량 * `Product` 의 `ProductPrice`) 합이 `Menu` 의 `MenuPrice` 보다 크거나 같아야 한다.
- `Menu` 의 `MenuPrice` 을 `change` 할 수 있다.
- `Menu` 를 `display` 할 수 있다.
    - 모든 (`MenuProduct` 의 수량 * `Product` 의 `ProductPrice`) 합이 `Menu` 의 `MenuPrice` 보다 크거나 같아야 한다.
    - `display` 하면 `displayed` 가 `true` 가 된다.
- `Menu` 를 `hide` 할 수 있다
    - `hide` 하면 `displayed` 가 `false` 가 된다.

### 주문 테이블

- `OrderTable` 을 `register` 할 수 있다.
- `OrderTable` 는 `OrderTableName`, `NumberOfGuests`, `empty` 을 가진다.
- `NumberOfGuests` 는 0명 이상이다.
- `empty` 는 `OrderTable` 의 비어 있음 여부이다.
- `OrderTable` 을 `fill` 할 수 있다.
- `OrderTable` 을 `clear` 할 수 있다.
    - `OrderTable` 에 `OrderStatus` 가 `COMPLETED` 이 아닌 `Order` 이 없어야 한다.
- `OrderTable` 의 `empty` 하지 않으면
    - `OrderTable` 의 `NumberOfGuests` 를 `change` 할 수 있다.

### 주문

- `Order` 은 `OrderStatus` 를 갖는다.
- `Order` 을 `register` 할 수 있다.
    - `register` 하면 `OrderStatus` 가 `WAITING` 이 된다.
    - `OrderType` 가 `DELIVERY` 이면, `DeliveryAddress` 가 있어야 한다.
    - `OrderType` 가 `EAT_IN` 이면, 그 `OrderTable` 이 `empty` 하지 않아야 한다.
- `Order` 는 `OrderType` 을 갖는데, `DELIVERY`, `TAKEOUT`, `EAT_IN` 가 있다.
- 한 번의 `Order` 에 여러 개의 `OrderLineItem` 를 담을 수 있다.
    - `OrderType` 이 `DELIVERY`, `TAKEOUT` 일 경우, 수량은 0개 이상이어야 한다.
- `Order` 를 `accept` 할 수 있다.
    - `OrderStatus` 가 `WAITING` 이어야 한다.
    - `OrderType` 가 `DELIVERY` 이면 배달 대행사를 호출한다.
    - `accept` 하면 `OrderStatus` 가 `ACCEPTED` 이 된다.
- `Order` 를 `serve` 할 수 있다.
    - `OrderStatus` 가 `ACCEPTED` 이어야 한다.
    - `serve` 하면 `OrderStatus` 가 `SERVED` 이 된다.
- `Order` 를 `startDelivery` 할 수 있다.
    - `OrderType` 가 `DELIVERY` 이고 `OrderStatus` 가 `SERVED` 이어야 한다.
    - `startDelivery` 하면 `OrderStatus` 가 `DELIVERING` 이 된다.
- `Order` 를 `completeDelivery` 할 수 있다.
    - `OrderType` 가 `DELIVERY` 이고 `OrderStatus` 가 `DELIVERING` 이어야 한다.
    - `completeDelivery` 하면 `OrderStatus` 가 `DELIVERED` 이 된다.
- `Order` 를 `complete` 할 수 있다.
    - `OrderType` 가 `DELIVERY` 이고 `OrderStatus` 가 `DELIVERED` 인 경우이거나,
    - `OrderType` 가 `TAKEOUT` 또는 `EAT_IN` 이고 `OrderStatus` 가 `SERVED` 인 경우여야 한다.
    - `complete` 하면 `OrderStatus` 가 `COMPLETED` 이 된다.
