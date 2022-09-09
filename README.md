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

### 상품

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 판매할 음식의 종류를 의미한다. 메뉴는 하나 이상의 상품으로 구성된다. |
| 가격 | price | 상품의 가격을 의미한다. |
| 이름 | name | 상품의 이름을 의미한다. |
| 등록한다 | create | 새로운 상품을 등록한다. |
| 가격을 변경한다 | changePrice | 상품의 가격을 변경한다. |

### 메뉴

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | menu | 손님이 주문할 수 있는 최소단위이다. 한 개 이상의 메뉴 상품으로 구성된다.<br/>ex) 후라이드 두마리 세트 : 후라이드 치킨 2마리(메뉴상품) + 콜라 1.25L(메뉴상품) |
| 메뉴 상품 | menuProduct | 어떤 종류의 상품을 몇개를 포함하는지를 의미한다. 메뉴 상품은 하나 이상의 상품을 포함한다.<br/>ex) 후라이드 치킨 2마리 : 후라이드 치킨(상품) x 2(수량) |
| 이름 | name | 메뉴의 이름을 의미한다. |
| 가격 | price | 메뉴의 가격을 의미한다. |
| 등록한다 | create | 새로운 메뉴를 등록한다. |
| 전시한다 | display | 메뉴를 주문할 수 있도록 정보를 보여준다. |
| 숨긴다 | hide | 메뉴를 주문할 수 없도록 정보를 숨긴다. |
| 가격을 변경한다. | changePrice | 메뉴의 가격을 변경한다. |

### 메뉴그룹

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴그룹 | menuGroup | 메뉴들을 분류하기 위한 그룹으로, 여러 메뉴들을 포함하고 있다.<br/>ex) 추천메뉴 그룹:<br/> - 후라이드 두마리 메뉴<br/> - 마늘, 간장 메뉴 등 |
| 이름 | name | 메뉴 그룹의 이름을 의미한다. |
| 등록한다. | create | 새로운 메뉴그룹을 등록한다. |

### 주문

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 | order | 한 종류 이상의 메뉴를 구매하는 것을 의미한다. 고객이 요청한 주문상품 정보들을 말한다. |
| 주문 상품 | orderLineItem | 주문하려는 메뉴의 종류와 개수 정보를 포함하는 정보다. |
| 주문 유형 | orderType | 주문 유형이다. 배달, 포장, 매장 식사가 있다. |
| 주문 상태 | orderStatus | 주문의 진행 상황을 의미한다.<br/>대기, 수락, 서빙완료, 배달중, 배달완료, 주문 처리 완료가 있다. |
| 주문 시간 | orderDateTime | 주문을 등록한 시간이다. |
| 배달 주소 | deliveryAddress | 배달 주문에서 배달을 할 주소다. |
| 등록한다 | create | 고객이 식당에 주문을 한다. |
| 수락한다 | accept | 고객의 주문을 수락한다. |
| 서빙한다 | serve | 고객이 주문한 음식을 서빙한다.<br/> - 매장: 주문 테이블에 음식을 서빙한다.<br/> - 포장: 대기중인 고객에게 음식을 서빙한다.<br/> - 배달: 배달기사님에게 음식을 전달한다. |
| 배달을 시작한다 | startDelivery | 배달을 시작한다. |
| 배달을 완료한다 | completeDelivery | 배달을 완료한다. |
| 완료한다 | complete | 주문을 완료한다. |

### 주문테이블

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 테이블 | orderTable | 손님들이 매장 식사를 하기 위해 앉을 테이블을 의미한다. |
| 이름 | name | 주문 테이블의 이름이다. |
| 손님 수 | numberOfGuests | 테이블에 앉은 손님의 수다. |
| 사용 여부 | ocuppied | 테이블이 사용중인지 여부를 나타낸다.<br/>ex) `사용중` : 손님들에게 배정이 된 테이블로, 손님에게 배정을 할 수 없음을 의미한다.<br/>`사용 가능` : 비어있는 테이블로 손님에게 배정을 할 수 있음을 의미한다. |
| 등록한다 | create | 주문 테이블을 등록한다. |
| 사용한다 | use | 주문 테이블을 `사용 중` 으로 바꾼다. |
| 치운다 | clear | 주문 테이블을 `사용 가능` 으로 바꾼다. |
| 손님 수를 변경한다 | changeNumberOfGuests | 주문 테이블에 앉아있는 손님의 수를 변경한다. |

## 모델링
