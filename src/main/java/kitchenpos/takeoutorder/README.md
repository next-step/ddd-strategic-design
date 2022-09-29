## 용어 사전

### 포장 주문 (Bounded Context)

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 포장 주문 | takeoutOrder | 포장하기 위해 한 종류 이상의 메뉴를 구매하는 것을 의미한다. 고객이 요청한 주문상품 정보들을 말한다. |
| 메뉴 | menu | 주문할 수 있는 최소 단위 |
| 전시된 메뉴 | displayedMenu | 주문할 수 있는 메뉴 |
| 숨겨진 메뉴 | hiddenMenu | 주문할 수 없는 메뉴 |
| 주문 상품 | orderLineItem | 주문하려는 메뉴의 종류와 개수 정보를 포함하는 정보다. |
| 주문 상태 | orderStatus | 주문의 진행 상황을 의미한다.<br/>대기, 수락, 서빙완료, 주문 처리 완료가 있다. |
| 대기 | waiting | 손님이 주문을 하고 사장님이 수락하지 않은 상태 |
| 수락 | accepted | 손님의 주문을 사장님이 수락한 상태 |
| 서빙 완료 | served | 대기하는 손님에게 음식이 전달된 상태 |
| 주문 처리 완료 | completed | 음식이 손님에게 전달되어 사장님이 주문을 완료 처리한 상태 |
| 주문 시간 | orderDateTime | 주문을 등록한 시간이다. |
| 수락한다 | accept | 고객의 주문을 수락한다. |
| 서빙한다 | serve | 대기중인 고객에게 음식을 서빙한다. |
| 완료한다 | complete | 주문을 완료한다. |

## 모델링

### 포장 주문

- `포장 주문(takeoutOrder)`은 식별자, 주문 상태, 주문 시간, 주문 상품 목록을 가진다.
- `포장 주문(takeoutOrder)`은 `대기(waiting)` → `수락(accepted)` → `서빙 완료(served)` → `주문 처리 완료(completed)` 순서로 진행된다.<br/><br/>
- `포장 주문(takeoutOrder)`을 생성한다.
  - 존재하지 않는 `메뉴(menu)`나 `숨겨진 메뉴(hiddenMenu)`는 주문할 수 없다.
  - `주문 상품(orderLineItem)`이 하나 이상이어야 한다.
  - 각 `주문 상품(orderLineItem)`의 개수는 0개 이상이어야 한다.
  - `주문상품(orderLineItem)`의 가격은 실제 `메뉴 가격(menuPrice)`과 일치해야 한다.
- `포장 주문(takeoutOrder)`을 `수락한다(accept).`
  - `대기(waiting)` 상태의 `포장 주문(takeoutOrder)`만 `수락할 수 있다(accept).`
- `포장 주문(takeoutOrder)`을 `서빙한다(serve).`
  - `수락(accped)` 상태의 `포장 주문(takeoutOrder)`만 `서빙할 수 있다(serve).`
- `포장 주문(takeoutOrder)`을 `완료한다(complete).`
  - `서빙 완료(served)` 된 `포장 주문(takeoutOrder)`만 `완료할 수 있다(complete).`
