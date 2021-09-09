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
| 상품 | Product | 키친 포스 식당에서 만들어내는 음식 |
| 상품 식별자 | id | 상품의 식별자 |
| 상품명 | name | 상품의 이름. 비속어는 포함하지 않는다. |
| 상품 가격 | price | 상품의 가격. 0원 이상이다. |

### 메뉴 그룹

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 그룹 | MenuGroup | 메뉴를 분류하는 단위 |
| 메뉴 그룹 식별자 | id | 메뉴 그룹의 식별자 |
| 메뉴 그룹명 | name | 메뉴 그룹의 이름 |


### 메뉴

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | Menu | 키친 포스 식당에서 판매하는 음식의 종류 |
| 메뉴 식별자 | id | 메뉴의 식별자 |
| 메뉴명 | name | 메뉴의 이름. 비속어는 포함하지 않는다. |
| 메뉴 가격 | price | 메뉴의 가격. 0원 이상이다. |
| 메뉴 노출 | displayed | 메뉴의 노출 여부. 노출하거나 숨길 수 있다. 메뉴 상품의 가격 합이 메뉴 가격보다 클 경우 노출할 수 없다. |
| 메뉴 그룹 | MenuGroup | 메뉴가 속한 메뉴 그룹. 하나의 메뉴 그룹에 속해있다. |
| 메뉴 상품 | MenuProduct | 메뉴를 구성하는 상품. 한 가지 이상을 필수로 등록해야하고, 메뉴 상품의 가격 합은 메뉴 가격보다 크거나 같아야한다. |


### 메뉴 상품

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 상품 | MenuProduct | 메뉴를 구성하는 상품. 메뉴 상품의 가격은 (수량 * 상품 가격)이다. |
| 상품 수량 | quantity | 메뉴 상품의 수량. 0개 이상이다. |
| 상품 | Product | 메뉴에 속한 상품. 메뉴보다 먼저 등록되어 있다. |


### 주문 테이블

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 테이블 | OrderTable | 매장에서 사용하는 테이블 |
| 주문 테이블 식별자 | id | 주문 테이블의 식별자 |
| 주문 테이블명 | name | 주문 테이블 이름 |
| 방문한 손님 수 | numberOfGuests | 테이블에 앉으려는 손님 수. 비어있지 않은 테이블에 0명 이상으로 설정할 수 있다. |
| 빈 테이블 | empty | 테이블이 비어있는 상태. 해당 테이블의 주문이 완료된 상태면 비울 수 있다. |


### 주문

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 | Order | 키친 포스 식당에 음식을 주문 |
| 주문 식별자 | id | 주문의 식별자 |
| 주문 날짜 | orderDateTime | 주문한 날짜 |
| 배달 주소 | deliveryAddress | 배달하려는 주소. 배달 주문을 접수하려면 필수 기재. |
| 주문 종류 | OrderType | 주문의 종류. 매장, 포장, 배달 중 한 가지의 종류를 필수로 선택한다. |
| 주문 상태 | OrderStatus | 주문의 진행 상태. 접수, 서빙, 완료할 수 있다. 배달 주문은 접수 시에 배달 대행사를 호출하고, 서빙 이후 배달 시작, 배달 완료 상태를 거쳐 주문이 완료된다. |
| 주문 항목 | OrderLineItem | 주문에 속한 세부 항목. 매장주문 외에는 항목의 수량이 0개 이상이다. |
| 주문 테이블 | OrderTable | 식사할 매장 테이블. 매장 주문시엔 비어있지 않은 테이블이 필요하다. |
| 주문 테이블 식별자 | orderTableId | 주문 테이블의 식별자 (OrderTable - id) |


### 주문 종류

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 종류 | OrderType | 주문의 종류 |
| 배달 주문 | DELIVERY | 배달 주문 |
| 포장 주문 | TAKEOUT | 포장 주문 |
| 매장 주문 | EAT_IN | 매장 주문 |

### 주문 상태

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 상태 | OrderStatus | 주문의 현재 진행 상태 |
| 주문 대기 | WAITING | 주문이 등록되었으나 수락되지 않은 상태 |
| 주문 수락 | ACCEPTED | 주문이 수락된 상태 |
| 서빙 | SERVED | 주문한 음식이 준비된 상태 |
| 배달 중 | DELIVERING | 배달을 진행하는 상태 |
| 배달 완료 | DELIVERED | 배달이 완료된 상태 |
| 주문 완료 | COMPLETED | 주문이 완료된 상태 |


### 주문 항목

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 항목 | OrderLineItem | 주문할 세부 항목 |
| 주문 수량 | quantity | 주문할 항목의 수량 |
| 주문 가격 | price | 주문할 항목의 가격. 실제 메뉴의 가격과 동일해야한다. |
| 메뉴 | Menu | 주문할 항목의 선택 메뉴. 숨겨진 메뉴일 경우 주문할 수 없다. |



## 모델링

**속성**: 필드에 대한 정의, 포괄적이면서 명확한 의미  
**기능**: 행위나 상태를 나타내는 것

### 상품(Product)
**속성**
- 상품은 식별자(`id`)를 가진다.
- 비속어를 포함하지 않는 상품명(`name`)을 가진다.
- 0원 이상의 상품 가격(`price`)을 가진다.

**기능**
- 상품을 등록한다. (``create``)
- 등록된 상품 가격을 0원 이상으로 변경한다.(``changePrice``)
  - 변경할 가격이 메뉴 상품의 가격 합보다 크면 메뉴가 숨겨진다.
- 상품의 전체 목록을 조회한다.(``findAll``)

### 메뉴 그룹(MenuGroup)
**속성**
- 메뉴 그룹은 식별자(`id`)을 가진다.
- 비어있지 않은 메뉴 그룹명(`name`)을 가진다.

**기능**
- 메뉴 그룹을 등록한다. (``create``)
- 메뉴 그룹의 전체 목록을 조회한다.(``findAll``)

### 메뉴 (Menu)
**속성**
- 메뉴는 식별자(`id`)와 메뉴 노출(`displayed`) 여부를 가진다.
- 비속어를 포함하지 않는 메뉴명(`name`)을 가진다.
- 0원 이상의 메뉴 가격(`price`)을 가진다.
- 등록된 상품으로 메뉴 상품(`MenuProduct`)을 1개 이상 가진다.
- 등록된 메뉴 그룹(`MenuGroup`)에 속한다.

**기능**
- 메뉴를 등록한다. (``create``)
  - 메뉴 상품의 가격 합은 메뉴 가격보다 크거나 같다.
- 등록된 메뉴 가격을 0원 이상으로 변경한다. (``changePrice``)
  - 메뉴 상품의 가격 합은 메뉴 가격보다 크거나 같다.
- 등록된 메뉴를 노출한다. (``display``)
  - 메뉴 가격이 메뉴 상품의 가격 합보다 클 경우 노출할 수 없다.
- 등록된 메뉴를 숨긴다. (``hide``)
- 메뉴의 전체 목록을 조회한다. (``findAll``)

### 주문 테이블 (OrderTable)
**속성**
- 주문 테이블은 식별자(`id`), 방문한 손님 수(`numberOfGuests`), 빈 테이블 여부(`empty`)를 가진다.
- 비어있지 않은 주문 테이블명(`name`)을 가진다.

**기능**
- 주문 테이블을 등록한다. (``create``)
- 등록된 주문 테이블에 앉는다. (``sit``)
- 등록된 주문 테이블을 비운다. (``clear``)
  - 해당 테이블에 주문 완료(`COMPLETED`) 상태가 아닌 주문이 있으면 비울 수 없다.
- 등록된 주문 테이블의 손님 수를 0명 이상으로 변경한다. (``changeNumberOfGuests``)
  - 빈 테이블(`empty`)은 변경할 수 없다.
- 주문 테이블의 전체 목록을 조회한다. (``findAll``)

### 주문 (Order)
**속성**
- 주문은 식별자(`id`), 주문 날짜(`orderDateTime`)를 가진다.
- 주문 종류(`OrderType`)는 배달 주문(`DELIVERY`), 포장 주문(`TAKEOUT`), 매장 주문(`EAT_IN`) 3가지로 나뉜다.
- 주문 상태(`OrderStatus`)는 주문 대기(`WAITING`), 주문 수락(`ACCEPTED`), 서빙(`SERVED`), 배달 중(`DELIVERING`), 배달 완료(`DELIVERED`), 주문 완료(`COMPLETED`) 6가지 중 하나로 설정한다.
- 1개 이상의 주문 항목(`OrderLineItem`)을 가진다.
- 등록되어있고 노출(`displayed`)된 메뉴(`Menu`)를 가진다.
  
**기능**
- 주문의 전체 목록을 조회한다. (``findAll``)

- 배달 주문
  - 배달 주문을 등록(``create``)한다. 
    - 메뉴 가격과 일치하는 주문 가격(`price`)을 가진다.
    - 주문 수량(`quantity`)은 0 이상을 가진다.  
    - 비어있지 않은 배달 주소(`deliveryAddress`)를 가진다.
  - 주문 대기 상태의 배달 주문을 수락한다. (``accept``)
    - 배달 대행사(`kitchenridersClient`)를 호출한다.(`requestDelivery`)      
  - 주문 수락 상태의 배달 주문을 서빙한다. (``serve``)
  - 서빙 완료된 배달 주문을 배달 시작한다. (``startDelivery``)
  - 배달 중인 배달 주문을 배달 완료한다. (``completeDelivery``)
  - 배달 완료된 배달 주문을 완료한다.(``complete``)

- 포장 주문
  - 포장 주문을 등록(``create``)한다.
    - 메뉴 가격과 일치하는 주문 가격(`price`)을 가진다.
    - 주문 수량(`quantity`)은 0 이상을 가진다.
  - 주문 대기 상태의 포장 주문을 수락한다. (``accept``)
  - 주문 수락 상태의 포장 주문을 서빙한다. (``serve``)
  - 서빙된 포장 주문을 완료한다. (``complete``)

- 매장 주문
  - 매장 주문을 등록(``create``)한다.
    - 메뉴 가격과 일치하는 주문 가격(`price`)을 가진다.
    - 주문 수량(`quantity`)은 0 미만을 가질 수 있다.  
    - 등록된 주문 테이블 식별자(`orderTableId`)을 가진다.  
      - 주문 테이블(`OrderTable`)은 비어있지 않다.
  - 주문 대기 상태의 매장 주문을 수락한다. (``accept``)
  - 주문 수락 상태의 매장 주문을 서빙한다. (``serve``)
  - 서빙된 매장 주문을 완료한다. (``complete``)
    - 주문 테이블의 모든 매장 주문이 완료되면 빈 테이블(`empty`)로 설정한다.
