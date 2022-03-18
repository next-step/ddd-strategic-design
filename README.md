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
| 상품 | Product | 이름과 가격을 갖고 있으며 메뉴에 포함될 수 있는 제품을 뜻함 |
| 상품 이름 | Product Name | 상품의 이름 |
| 상품 가격 | Product Price | 상품의 값(가격) |
| 상품 등록 | Register Product | 상품을 등록 |
| 상품 가격 변경 | Change Product Price | 상품의 가격을 변경 |

### 메뉴 그룹

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 그룹 | Menu Group | 메뉴의 묶음으로 하나 이상의 메뉴를 포함 |
| 메뉴 그룹 이름 | Menu Group Name | 메뉴 그룹 이름 |
| 메뉴 그룹 등록 | Register Menu Group | 메뉴 그룹 등록 |

### 메뉴

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | Menu | 여러 상품의 묶음으로, 손님이 주문하는 대상이며 특정 메뉴 그룹에 속함 |
| 메뉴 상품 | Menu Product | 메뉴에 속한 상품이며, 수량이 0개 이상이고 메뉴 가격의 합이 메뉴의 가격보다 크거나 같아야함 |
| 메뉴 가격 | Menu Price | 메뉴의 값이며 0원 이상 |
| 메뉴 이름 | Menu Name | 메뉴의 이름이며 비속어가 포함될 수 없음 |
| 메뉴 노출 | Display Menu | 메뉴를 노출하며 메뉴의 가격이 메뉴 상품의 수량 * 가격 보다 작거나 같아야 함 |
| 메뉴 숨김 | Hide Menu | 메뉴를 숨김 |

### 주문 테이블

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 테이블 | Order Table | 매장식사 주문일 경우, 손님들이 식사를 하고 가는 테이블 |
| 주문 테이블 이름 | Order Table Name | 주문 테이블 이름 |
| 주문 테이블 방문 손님 수 | Order Table Number Of Guests | 주문 테이블을 방문한 손님 수이며 0명 이상 |
| 주문 테이블 등록 | Register Order Table | 주문 테이블 등록 |
| 주문 테이블 찬 테이블로 설정 | Fill Order Table | 주문 테이블이 가득찬 테이블로 설정 |
| 주문 테이블 빈 테이블로 설정 | Clear Order Table | 주문 테이블을 빈 테이블로 설정하며 완료되지 않은 주문이 있으면 불가능 |
| 주문 테이블 방문 손님 수 변경 | Change Order Table Number Of Guests | 주문 테이블을 방문한 손님 수를 변경하며, 빈 테이블은 불가능 |

### 주문

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 | Order | 1개 이상의 메뉴를 손님에게 제공하는 것을 뜻하며 없는 메뉴나 숨겨진 메뉴는 주문할 수 없음 |
| 주문 항목 | Order Line Item | 주문의 내용을 뜻하며 메뉴, 가격, 수량을 담고 있으며, 배달이나 포장시 수량은 0개 이상이어야 함. 각 주문 항목의 가격은 메뉴 가격과 일치해야 함 |
| 주문 유형 | Order Type | 주문의 유형으로, 배달, 포장, 매장식사 3가지가 있음 |
| 배달 주문 | Delivery Order | 배달 주소까지 배달해주는 주문이며 배달 주소가 있어야 주문이 가능 |
| 포장 주문 | Takeout Order | 포장해서 가져갈 수 있는 주문 |
| 매장식사 주문 | Eat In Order | 매장 내 주문 테이블에서 식사하는 주문이며 찬 테이블에 주문이 가능 |
| 배달 주소 | Deliver Address | 배달 주문 시, 배달하는 주소 |
| 주문 등록 | Register Order | 손님의 주문을 등록 |
| 주문 접수 | Accept Order | 접수 대기중인 주문을 접수하며 배달 주문인 경우, 배달 대행사를 호출. 주문 대기 상태여야 함 |
| 주문 서빙 | Serve Order | 사장님이 접수된 주문을 서빙하며 주문 접수됨 상태여야 함 |
| 주문 배달 시작 | Start Delivery Order | 배달 대행사가 서빙된 배달 주문을 배달 시작하며 배달 주문이어야 하고 서빙됨 상태여야 함 |
| 주문 배달 완료 | Complete Delivery Order | 배달 대행사가 배달중인 배달 주문을 배달 완료하며 배달 중 상태여야 함 |
| 주문 완료 | Complete Order | 주문을 완료한다. 배달 주문이라면 배달됨 상태여야 하고 포장이나 매장식사 주문인 경우 서빙됨 상태여야 함 |

## 모델링
### 상품(Product)

#### 속성

- 상품은 이름(`name`)과 가격(`price`)을 가진다.

#### 기능/조건

- 상품을 등록할 수 있다(`create`).
  - `price`는 0원 이상이어야 한다.
  - `name`은 비워 둘 수 없다.
  - `name`은 `PurgoMalumClient`를 통해 비속어(`profanity`)를 필터링 한다.
- 상품의 가격을 변경할 수 있다(`changePrice`).
  - 변경되는 `price`는 0원 이상이어야 한다.
  - 변경 후 메뉴에 속한 상품(`menuProduct`)금액의 합보다 메뉴(`menu`)의 가격이 크면 메뉴가 숨겨진다(`hide`).
- 상품의 목록을 조회할 수 있다.(`findAll`)

### 메뉴 그룹(MenuGroup)

#### 속성

- `name`을 가진다.

#### 기능/조건

- 메뉴 그룹을 등록할 수 있다(`create`).
  - `name`은 비워 둘 수 없다.
  - `name`은 `PurgoMalumClient`를 통해 비속어(`profanity`)를 필터링 한다.
- 메뉴 그룹의 목록을 조회할 수 있다(`findAll`).

### 메뉴(Menu)

#### 속성

- 메뉴는 이름(`name`)과 가격(`price`)을 가진다.
- 메뉴는 하나의 `menuGroup`에 속한.
  - 속한 메뉴 그룹을 식별하기 위한 `menuGroupId`를 가진다.
- 메뉴는 노출 여부를 나타내는 `displayed`를 가진다.
- 메뉴는 하나 이상의 MenuProduct를 가진다.
  - `menuProduct`는 `product`를 가진다.
  - `menuProduct`는 상품을 식별하기 위한 `productId`를 가진다.
  - `menuProduct`는 상품의 갯수(`quantity`)를 가진다.

#### 기능/조건

- 메뉴를 등록할 수 있다(`create`).
  - 메뉴를 등록하려면 등록된 `product`가 1개 이상이어야 한다.
  - `menuProduct`의 `quantity`는 0 이상이어야 한다.
  - 메뉴의 `price`는 0원 이상이어야 한다.
  - `menuProduct`금액의 총합은 메뉴의 `price`보다 크거나 같아야 한다.
  - 메뉴는 특정 `menuGroup`에 속해야 한다.
  - 메뉴의 `name`은 비워둘 수 없다.
  - 메뉴의 `name`은 `PurgoMalumClient`를 통해 비속어(`profanity`)를 필터링 한다.
- 메뉴의 가격을 변경할 수 있다(`changePrice`).
  - 변경 후 메뉴의 `price`는 0원 이상이어야 한다.
  - 변경 후 `menuProduct`금액의 총합은 메뉴의 `price`보다 크거나 같아야 한다.
- 메뉴를 노출할 수 있다(`display`).
  - 메뉴의 `price`가 `menuProduct`금액의 총합 보다 높을 경우 노출할 수 없다.
- 메뉴를 숨길 수 있다(`hide`).
- 메뉴의 목록을 조회할 수 있다(`findAll`).

### 주문 테이블(OrderTable)

#### 속성

- `name`을 가진다.
- `numberOfGuests`를 가진다.
- 주문 테이블은 테이블이 비워져있는지를 나타내는 상태인 `empty`를 가진다.

#### 기능/조건

- 주문 테이블을 등록할 수 있다(`create`).
  - `name`은 비워 둘 수 없다.
  - `name`은 `profanity`가 포함될 수 없다.
    - `PurgoMalumClient`가 비속어를 필터링 한다.
- 빈 테이블을 해지할 수 있다(`sit`).
- 빈 테이블로 설정할 수 있다(`clear`).
  - 주문 테이블의 `orderStatus`가 `COMPLETED`가 아니라면 설정할 수 없다.
- 방문한 손님 수를 변경할 수 있다(`changeNumberOfGuests`).
  - `numberOfGuests`는 0 이상이어야 한다.
  - `empty`상태의 주문 테이블은 `numberOfGuests`를 변경할 수 없다.
- 주문 테이블의 목록을 조회할 수 있다(`findAll`).

### 주문(Order)

#### 속성

- 주문은 어떤 주문인지 타입을 나타내는 `orderType`을 가진다.
  - 배달 주문은 `DELIVERY`타입이다.
  - 포장 주문은 `TAKEOUT`타입이다.
  - 매장 주문은 `EAT_IN`타입이다.
- 주문은 현재 상태를 나타내는 `orderStatus`를 가진다.
  - 주문이 대기 중이라면 `WAITING`상태이다.
  - 주문이 승인되었다면 `ACCEPTED`상태이다.
  - 주문이 서빙되었다면 `SERVED`상태이다.
  - 주문이 배달 중이라면 `DELIVERING`상태이다.
  - 주문이 배달되었다면 `DELIVERED`상태이다.
  - 주문이 완료되었다면 `COMPLETED`상태이다.
- 주문은 주문 일시를 나타내는 `orderDateTime`을 가진다.
- 배달 주문은 배달할 주소를 나타내는 `deliveryAddress`를 가질 수 있다.
- 주문은 주문 항목들(`orderLineItems`) 를 가진다.
  - `orderLineItem`은 주문한 `menu`를 가진다.
  - `orderLineItem`은 주문한 메뉴를 식별하기 위한 `menuId`를 가진다.
  - `orderLineItem`은 주문 항목 수량(`quantity`) 를 가진다.
  - `orderLineItem`은 주문의 `price`를 가진다.

#### 기능/조건

- 주문을 등록할 수 있다(`create`).
  - 1개 이상의 등록된 `menu`로 주문을 등록할 수 있다.
  - `menu`가 없는 주문은 등록할 수 없다.
  - `orderType`이 올바르지 않으면 등록할 수 없다.
  - `orderStatus`가 올바르지 않으면 등록할 수 없다.
  - `EAT_IN`타입의 주문은 `orderLineItem`의 `quantity`가 0 미만일 수 있다.
  - `EAT_IN`을 제외한 타입의 주문은 `orderLineItem`의 `quantity`가 0 이상이어야 한다.
  - `deliveryAddress`가 비워져 있다면, `DELIVERY`주문을 등록할 수 없다.
  - `empty`테이블에는 `EAT_IN`타입의 주문을 등록할 수 없다.
  - 숨겨진 메뉴는 주문할 수 없다.
  - 주문한 메뉴의 `price`와 `orderLineItem`의 `price`는 일치해야한다.
- 주문을 승인할 수 있다(`accept`).
  - `WAITING`상태의 주문만 접수 할 수 있다.
  - `DELIVERY`타입의 주문을 접수하면, 배달기사(`kitchenRiders`) 를 호출한다.
- 주문을 서빙할 수 있다(`serve`).
  - `ACCEPTED`상태의 주문만 서빙할 수 있다.
- 주문의 배달을 시작할 수 있다(`startDelivery`).
  - `DELIVERY`타입이고, `SERVED`상태의 주문만 배달할 수 있다.
  - 배달기사(`kitchenRiders`)가 주문의 배달을 수행한다.
- 주문의 배달을 완료할 수 있다(`completeDelivery`).
  - `DELIVERING`상태의 주문만 배달 완료할 수 있다.
- 주문을 완료할 수 있다(`complete`).
  - `DELIVERY`타입의 주문일 경우, `DELIVERED`상태의 주문만 완료할 수 있다.
  - `TAKEOUT`혹은 `EAT_IN`타입 주문일 경우, `SERVED`상태의 주문만 완료할 수 있다.
  - `orderTable`의 모든 매장 주문이 완료되면 `empty`테이블로 설정한다.
  - `COMPLETED`상태가 아닌 매장 주문이 있는 주문 테이블은 `empty`테이블로 설정할 수 없다.
- 주문 목록을 조회할 수 있다(`findAll`).