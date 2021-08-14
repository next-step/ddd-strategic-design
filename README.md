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
| 상품 이름 | Product Name | 상품의 이름이다. 비속어가 포함될 수 없다. |
| 상품 가격 | Product Price | 상품의 값이다. 0원 이상이다. |
| 상품 등록 | Register Product | 상품을 등록한다. |
| 상품 가격 변경 | Change Product Price | 상품의 가격을 변경한다. |

### 메뉴 그룹

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 그룹 | Menu Group | 메뉴들을 하나로 묶어서 보여주는 그룹이다. |
| 메뉴 그룹 이름 | Menu Group Name | 메뉴 그룹의 이름이다. |
| 메뉴 그룹 등록 | Register Menu Group | 메뉴 그룹을 등록한다. |

### 메뉴

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | Menu | 여러 개의 상품의 묶음으로, 손님이 주문하는 대상이다. 또한 특정 메뉴 그룹에 속한다. |
| 메뉴 상품 | Menu Product | 메뉴에 속한 상품이며, 수량이 0개 이상이고 메뉴 가격의 합이 메뉴의 가격보다 크거나 같아야 한다. |
| 메뉴 가격 | Menu Price | 메뉴의 값이다. 0원 이상이다. |
| 메뉴 이름 | Menu Name | 메뉴의 이름이다. 비속어가 포함될 수 없다. |
| 메뉴 등록 | Register Menu | 메뉴를 등록한다. |
| 메뉴 가격 변경 | Change Menu Price | 메뉴의 가격을 변경한다. |
| 메뉴 노출 | Display Menu | 메뉴를 노출한다. |
| 메뉴 숨김 | Hide Menu | 메뉴를 숨긴다. |

### 주문 테이블

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 테이블 | Order Table | 매장식사 주문일 경우, 손님들이 식사를 하고 가는 테이블이다. |
| 주문 테이블 이름 | Order Table Name | 주문 테이블의 이름이다. |
| 주문 테이블 방문 손님 수 | Order Table Number Of Guests | 주문 테이블을 방문한 손님 수이다. 0명 이상이다. |
| 주문 테이블 등록 | Register Order Table | 주문 테이블을 등록한다. |
| 주문 테이블 찬 테이블로 설정 | Fill Order Table | 주문 테이블을 찬 테이블로 설정한다. |
| 주문 테이블 빈 테이블로 설정 | Clear Order Table | 주문 테이블을 빈 테이블로 설정한다. 완료되지 않은 주문이 있으면 불가능하다. |
| 주문 테이블 방문 손님 수 변경 | Change Order Table Number Of Guests | 주문 테이블을 방문한 손님 수를 변경한다. 빈 테이블은 불가능하다. |

### 주문

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 | Order | 1개 이상의 메뉴를 손님에게 제공하는 것을 뜻한다. 없는 메뉴나 숨겨진 메뉴는 주문할 수 없다. |
| 주문 항목 | Order Line Item | 주문의 내용을 뜻하며 메뉴, 가격, 수량을 담고 있다. 배달이나 포장시 수량은 0개 이상이어야 한다. 각 주문 항목의 가격은 메뉴 가격과 일치해야 한다. |
| 주문 유형 | Order Type | 주문의 유형으로, 배달, 포장, 매장식사 3가지의 경우가 있다. |
| 배달 주문 | Delivery Order | 배달 주소까지 배달해주는 주문이다. 배달 주소가 있어야 주문이 가능하다. |
| 포장 주문 | Takeout Order | 포장해서 가져갈 수 있는 주문이다. |
| 매장식사 주문 | Eat In Order | 매장 내 주문 테이블에서 식사하는 주문이다. 찬 테이블에 주문이 가능하다. |
| 배달 주소 | Deliver Address | 배달 주문 시, 배달하는 주소다. |
| 주문 등록 | Register Order | 손님의 주문을 등록한다. |
| 주문 접수 | Accept Order | 사장님이 접수 대기중인 주문을 접수한다. 배달 주문인 경우, 배달 대행사를 호출한다. |
| 주문 서빙 | Serve Order | 사장님이 접수된 주문을 서빙한다. |
| 주문 배달 시작 | Start Delivery Order | 배달 대행사가 서빙된 배달 주문을 배달 시작한다. |
| 주문 배달 완료 | Complete Delivery Order | 배달 대행사가 배달중인 배달 주문을 배달 완료한다. |
| 주문 완료 | Complete Order | 주문을 완료한다. |

## 모델링
