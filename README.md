# 키친포스

---

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

---

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
|  |  |  |
| 키친 포스 | kitchen pos | 본 프로젝트, 주방 포스, 상품, 메뉴, 주문등을 관리한다. |
| 상품 | product | 메뉴로 등록할 수 있는 상품 |
| 상품명 | product name | 상품의 이름, 필수 입력값이다, 비속어가 포함될 수 없다. |
| 상품 가격 | product price | 상품의 가격, 필수 입력값 이다, 0원 이상이여야 한다. |
| 상품 등록 | create product | 상품을 등록하는 행위 |
| 상품 가격 수정 | change product price | 상품의 가격을 수정하는 행위, 0원 이상이여야 한다, 해당 상품을 메뉴로 등록된 경우 상품가격이 메뉴가격보다 높으면 해당 메뉴는 hide 상태가 된다. |
| 상품 목록 조회 | find all product | 모든 상품 목록을 조회하는 행위 |
|  |  |  |
| 메뉴 그룹 | menu group | 메뉴를 분류할수 있는 그룹 |
| 메뉴 그룹명 | menu group name | 메뉴 그룹 이름, 필수 입력값이다. |
| 메뉴 그룹 등록 | create menu group | 메뉴 그룹을 등록하는 행위 |
| 메뉴 그룹 목록 조회 | find all menu group | 모든 메뉴 그룹을 조회하는 행위 |
|  |  |  |
| 메뉴 | menu | 상품을 조합한 판매 묶음 |
| 메뉴명 | menu name | 메뉴의 이름, 필수 입력값이다, 비속어가 포함될 수 없다. |
| 메뉴 가격 | menu price | 메뉴의 가격, 필수 입력값 이다, 0원 이상이여야 한다, 상품의 기본 가격의 총합보다 크면 안된다. |
| 메뉴 등록 | create menu | 메뉴를 등록하는 행위 |
| 메뉴 가격 수정 | change menu price | 메뉴의 가격을 수정하는 행위, 0원 이상이여야 한다, 메뉴에 포함된 상품의 가격보다 크면 안된다. |
| 메뉴 목록 조회 | find all menu | 모든 메뉴를 조회하는 행위 |
| 메뉴 노출 | displayed menu | 판매 가능한 메뉴, 노출 당시의 메뉴의 가격이 상품 가격의 총합보다 크면 안된다. |
| 메뉴 숨김 | hide menu | 판매 불가능한 메뉴 |
|  |  |  |
| 메뉴 상품 | menu product | 메뉴에 등록하기위한 상품 정보, 메뉴 등록시 필수 입력값 이다. |
| 메뉴 상품 번호 | menu product seq | 메뉴 상품의 고유 번호, 자동으로 증가하여 생성된다 |
| 메뉴 상품 수량 | menu product quantity | 메뉴 상품에 등록된 상품의 수량, 1개 이상이여야 한다. |
|  |  |  |
| 주문 테이블 | order table | 매장의 주문 가능한 테이블 |
| 주문 테이블명 | order table name | 주문 테이블의 이름 |
| 주문 테이블 고객 수 | order table number of guests | 주문 테이블에 자리한 고객 수 |
| 빈 주문 테이블 | empty order table | 새 고객이 착석 가능한 주문 테이블 |
| 주문 테이블 등록 | create order table | 주문 테이블을 등록하는 행위 |
| 주문 테이블 목록 조회 | find all order table | 모든 주문 테이블을 조회하는 행위 |
|  |  |  |
| 주문 | order | 고객이 요청한 주문 항목들의 발생 단위 |
| 주문 종류 | order type | 발생할수 있는 여러 주문 방식 |
| 매장 주문 | eat in order | 매장의 주문 테이블에서 발생한 주문 |
| 배달 주문 | delivery order | 배달을 요청한 주문 |
| 포장 주문 | takeout order | 포장을 요청한 주문 |
| 주문 상태 | order status | 주문 진행 상태 |
| 접수 대기 상태 | waiting status | 주문이 발생후 아직 접수가 안 된 상태 |
| 접수 완료 상태 | accepted status | 주문이 주방에 접수된 상태, 접수 대기 상태에서만 가능하다. |
| 서빙 완료 상태 | served status | 주문의 조리후 고객에게 서빙되는 상태, 주문 접수 상태에서만 가능하다. |
| 배달중 상태 | delivering status | 배달 주문 시작 후 상태 |
| 배달 완료 상태 | delivered status | 배달 주문일 경우 주문이 고객이 요청한 주소로 배달이 완료된 상태, 주문 종류가 배달 주문일 경우만 가능하다, 주문 배달중 상태에서만 가능하다. |
| 완료 상태 | completed status | 주문이 고객에게 정상적으로 전달된 상태, 매장/포장 주문일경우 주문 서빙 상태에서만 가능하다, 배달 주문일 경우 주문 배달 완료 상태에서만 가능하다. |
| 주문 발생 시간 | order date time | 주문이 발생한 시간 |
| 배달 주문 주소 | order delivery address | 배달 주문일 경우 고객이 요청한 배송지, 배달 주문일경우 필수 입력값 이다. |
| 주문 등록 | create order | 주문 등록하는 행위 |
| 주문 접수 | accept order | 주문이 주방에 접수하는 행위 |
| 주문 서빙 | serve order | 주문의 조리후 고객에게 서빙하는 행위 |
| 주문 배달 시작 | start delivery | 배달 주문일 경우 배달이 사작하는 행위 |
| 주문 배달 완료 | complete delivery | 배달 주문일 경우 주문이 고객이 요청한 주소로 배달이 완료하는 행위 |
| 주문 완료 | complete order | 주문이 고객에게 정상적으로 전달된 후 주문을 완료하는 행위 |
| 주문 목록 조회 | find all order | 모든 주문 목록을 조회하는 행위 |
|  |  |  |
| 주문 항목 | order line item | 고객이 요청한 메뉴 정보 |
| 주문 항목 번호 | order line item seq | 주문 항목의 고유 번호 |
| 주문 항목 수량 | order line item quantity | 주문 항목의 수량 |
| 주문 항목 가격 | order line item price | 주문 항목의 가격 |
|  |  |  |
| 배달 대행사 | delivery agency | 배달원을 보유한 배달 서비스사 |
| 대행사 호출 | request delivery | 배달주문 라이더 호출 |

---

## 모델링

### 상품(`Product`)
- 상품(`Product`)는 이름(`name`)을 가진다.
    - 이름(`name`)은 필수 입력값이다.
    - 이름(`name`)은 비속어(`profanity`)를 포함할 수 없다.
- 상품(`Product`)는 가격(`price`)을 가진다.
    - 가격(`price`)은 0원 이상이어야 한다.
- 상품 가격(`price`)을 변경(`changePrice`)할 수 있다.
    - 상품 가격 변경(`changePrice`)할 때 메뉴(`Menu`)의 가격이 메뉴에 속한 상품 금액의 합보다 크면 메뉴가 숨겨진다(`hide`).
- 상품 목록을 조회(`findAllProduct`)할 수 있다.

### 메뉴 그룹(`MenuGroup`)
- 메뉴 그룹(`MenuGroup`)는 이름(`name`)을 가진다.
    - 이름(`name`)은 필수 입력값이다.
- 메뉴 그룹 목록을 조회(`findAllMenuGroup`)할 수 있다.

### 메뉴(`Menu`)
- 메뉴(`Menu`)는 이름(`name`)을 가진다.
    - 이름(`name`)은 필수 입력값이다.
    - 이름(`name`)은 비속어(`profanity`)를 포함할 수 없다.
- 메뉴(`Menu`)는 가격(`price`)을 가진다.
    - 가격(`price`)은 0원 이상이어야 한다.
- 메뉴 가격(`price`)을 변경 할 수 있다.
    - 가격(`price`)은 메뉴 상품(`MenuProduct`)의 총 가격보다 크면 안된다.
- 메뉴(`Menu`)는 메뉴 그룹(`MenuGroup`)에 속한다.
- 메뉴(`Menu`)는 판매 가능한 메뉴 여부를 나타내는 노출(`displayed`)을 가진다.
    - 메뉴 노출(`displayed`)은 메뉴의 가격(`price`)이 상품의 가격보다 작거나 같아야 한다.
- 메뉴(`Menu`)는 메뉴 상품(`MenuProduct`)을 이용한 메뉴 상품 목록(`MenuProducts`)을 가진다.
    - 메뉴 상품 목록(`MenuProducts`)은 필수 입력 사항이며 메뉴 상품(`MenuProduct`)을 하나이상 가져야 한다.
    - 메뉴 상품 목록(`MenuProducts`)중 하나의 상품(`Product`)는 하나의 메뉴상품(`MenuProduct`)에만 존재해야 한다.
- 메뉴 목록을 조회(`findAllMenu`)할 수 있다.

### 메뉴 상품(`MenuProduct`)
- 메뉴 상품(`MenuProduct`)은 순서(`seq`)를 가진다.
- 메뉴 상품(`MenuProduct`)은 상품(`Product`)를 가진다.
- 메뉴 상품(`MenuProduct`)은 수량(`quantity`)를 가진다.
    - 메뉴 상품의 수량(`quantity`)은 0개 이상이어야 한다.

### 주문 테이블(`OrderTable`)
- 주문 테이블(`OrderTable`)은 이름(`name`)을 가진다.
    - 이름(`name`)은 필수 입력값이다.
- 주문 테이블(`OrderTable`)은 방문한 손님 수(`numberOfGuests`)를 가진다.
    - 방문한 손님 수(`numberOfGuests`)는 0 이상이어야 한다.
- 주문 테이블(`OrderTable`)은 빈 테이블 여부(`empty`)를 가진다.
- 빈 주문 테이블(`OrderTable`)은 손님이 앉을(`sit`) 수 있다.
- 주문 테이블(`OrderTable`)에서 손님이 나갈(`clear`) 수 있다. 
    - 주문(`Order`)가 완료되지 않으면 나갈 수 없다.
- 주문 테이블(`OrderTable`)은 손님 수를 변경(`changeNumberOfGuests`)할 수 있다.
- 주문 테이블 목록을 조회(`findAllOrderTable`)할 수 있다.

### 주문(`Order`)
- 주문(`Order`)은 매장, 배달, 포장 을 나타내는 주문 종류(`OrderType`)를 가진다.
- 주문(`Order`)은 주문이 고객에 전달되기까지의 과정을 나타내는 주문 상태(`OrderStatus`)를 가진다.
    - 주문 상태(`OrderStatus`)에는 대기 -> 확인 -> 서빙 -> 완료 순서로 진행되고, 배달 주문(`DELIVERY`)일 경우 서빙 -> 배달중 -> 배달완료 -> 완료 상태가 추가로 존재한다.
    - 주문 상태(`OrderStatus`)변경은 각 이전단계 에서만 가능하다.
- 주문(`Order`)은 주문 항목들(`OrderLineItems`)을 가진다.
    - 주문 항목들(`OrderLineItems`)은 하나이상의 주문 항목(`OrderLineItem`)을 가져야 한다.
- 배달 주문(`DELIVERY`)일 경우 배송지(`deliveryAddress`)를 필수로 가져야한다.
- 매장 주문(`EAT_IN`)일 경우 주문 테이블(`OrderTable`)를 가져야한다.
- 주문 목록을 조회(`findAllOrder`)할 수 있다.

### 주문 항목(`OrderLineItem`)
- 주문 항목(`OrderLineItem`)은 메뉴(`Menu`)를 갖는다.
  - 메뉴(`Menu`)는 필수 입력값 이다.
- 주문 항목(`OrderLineItem`)은 수량(`quantity`)를 갖는다.
    - 수량(`quantity`)은 0 이상이어야 한다.
- 주문 항목(`OrderLineItem`)은 가격(`price`)를 갖는다.
    - 가격(`price`)은 메뉴(`Menu`)의 가격 * 수량(`quantity`)과 같아야한다.

### 배달 대행사(`DeliveryAgency`)
- 배달 대행사(`DeliveryAgency`)에는 라이더(`Kitchenrider`)가 있다.
- 배달 주문(`DELIVERY`)일 경우 주문확인시 라이더를 호출(`requestDelivry`)한다.
