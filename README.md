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

### 배달 주문 (Bounded Context)

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 배달 주문 | deliveryOrder | 배달받기 위해 한 종류 이상의 메뉴를 구매하는 것을 의미한다. 고객이 요청한 주문상품 정보들을 말한다. |
| 메뉴 | menu | 주문할 수 있는 최소 단위 |
| 전시된 메뉴 | displayedMenu | 주문할 수 있는 메뉴 |
| 숨겨진 메뉴 | hiddenMenu | 주문할 수 없는 메뉴 |
| 주문 상품 | orderLineItem | 주문하려는 메뉴의 종류와 개수 정보를 포함하는 정보다. |
| 주문 상태 | orderStatus | 주문의 진행 상황을 의미한다.<br/>대기, 수락, 서빙완료, 배달중, 배달완료, 주문 처리 완료가 있다. |
| 대기 | waiting | 손님이 주문을 하고 사장님이 수락하지 않은 상태 |
| 수락 | accepted | 손님의 주문을 사장님이 수락한 상태 |
| 서빙 완료 | served | 배달 대행사 기사님에게 음식이 전달된 상태 |
| 배달 중 | delivering | 음식이 배달 대행사 기사님에게 전달되어 배달이 진행 중인 상태 |
| 배달 완료 | delivered | 배달 대행사 기사님이 주문한 손님에게 음식을 전달한 상태 |
| 주문 처리 완료 | completed | 음식이 손님에게 전달되어 사장님이 주문을 완료 처리한 상태 |
| 배달 대행사 | deliveryAgency | 배달 기사님들을 관리해주는 곳으로, 배달 주문에 대한 배송을 책임지는 곳을 의미한다. |
| 주문 시간 | orderDateTime | 주문을 등록한 시간이다. |
| 배달 주소 | deliveryAddress | 배달 주문에서 배달을 할 주소다. |
| 수락한다 | accept | 고객의 주문을 수락한다. |
| 서빙한다 | serve | 배달기사님에게 음식을 전달한다. |
| 배달을 시작한다 | startDelivery | 배달을 시작한다. |
| 배달을 완료한다 | completeDelivery | 배달을 완료한다. |
| 완료한다 | complete | 주문을 완료한다. |

## 모델링

### 배달 주문

- `배달 주문(deliveryOrder)`은 식별자, 주문 상태, 주문 시간, 주문 상품 목록, 배달 주소를 가진다.
- `배달 주문(deliveryOrder)`은 `대기(waiting)` → `수락(accepted)` → `서빙 완료(served)` → `배달 중(delivering)` → `배달 완료(delivered)` → `주문 처리 완료(completed)` 순서로 진행된다.<br/><br/>
- `배달 주문(deliveryOrder)`를 생성한다.
  - 존재하지 않는 `메뉴(menu)`나 `숨겨진 메뉴(hiddenMenu)`는 주문할 수 없다.
  - `주문 상품(orderLineItem)`이 하나 이상이어야 한다.
  - 각 `주문 상품(orderLineItem)`의 개수는 0개 이상이어야 한다.
  - `주문상품(orderLineItem)`의 가격은 실제 `메뉴 가격(menuPrice)`과 일치해야 한다.
  - `배달 주소(deliveryAddress)`가 있어야 한다.
- `배달 주문(deliveryOrder)`를 `수락한다(accept).`
  - `대기(waiting)` 상태의 `배달 주문(deliveryOrder)`만 `수락할 수 있다(accept).`
  - `배달 대행사(deliveryAgency)`에 배달을 요청한다.
- `배달 주문(deliveryOrder)`를 `서빙한다(serve).`
  - `수락(accped)` 상태의 `배달 주문(deliveryOrder)`만 `서빙할 수 있다(serve).`
- `배달 주문(deliveryOrder)`를 `배달을 시작한다(startDelivery).`
  - `서빙완료(served)` 된 `배달 주문(deliveryOrder)`만 `배달을 시작할 수 있다(startDelivery).`
- `배달 주문(deliveryOrder)`를 `배달을 완료한다(completeDelivery).`
  - `배달 중(delivering)`인 `배달 주문(deliveryOrder)`만 `배달을 완료할 수 있다(completeDelivery).`
- `배달 주문(deliveryOrder)`를 `완료한다(complete).`
  - `배달 완료(delivered)`된 `배달 주문(deliveryOrder)`만 `완료할 수 있다(complete).`
