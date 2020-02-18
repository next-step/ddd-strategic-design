# 키친포스

## 요구 사항

### 상품

* 상품을 등록할 수 있다.
* 상품의 가격이 올바르지 않으면 등록할 수 없다.
    * 상품의 가격은 0 원 이상이어야 한다.
* 상품의 목록을 조회할 수 있다.

### 메뉴 그룹

* 메뉴 그룹을 등록할 수 있다.
* 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴

* 1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.
* 메뉴의 가격이 올바르지 않으면 등록할 수 없다.
    * 메뉴의 가격은 0 원 이상이어야 한다.
    * 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
* 메뉴는 특정 메뉴 그룹에 속해야 한다.
* 메뉴의 목록을 조회할 수 있다.

### 테이블

* 테이블을 등록할 수 있다.
* 테이블의 목록을 조회할 수 있다.
* 빈 테이블 설정 또는 해지할 수 있다.
* 단체 지정된 테이블은 빈 테이블 설정 또는 해지할 수 없다.
* 주문 상태가 조리 또는 식사인 테이블은 빈 테이블 설정 또는 해지할 수 없다.
* 방문한 손님 수를 입력할 수 있다.
* 방문한 손님 수가 올바르지 않으면 입력할 수 없다.
    * 방문한 손님 수는 0 명 이상이어야 한다.
* 빈 테이블은 방문한 손님 수를 입력할 수 없다.

### 단체 지정

* 2 개 이상의 빈 테이블을 단체로 지정할 수 있다.
* 단체 지정은 중복될 수 없다.
* 단체 지정을 해지할 수 있다.
* 단체 지정된 테이블의 주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.

### 주문

* 1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.
* 빈 테이블에는 주문을 등록할 수 없다.
* 주문의 목록을 조회할 수 있다.
* 주문 상태를 변경할 수 있다.
* 주문 상태가 계산 완료인 경우 변경할 수 없다.

## 용어 사전

| 한글명 | 영문명 | 설명  |
| --- | --- | --- |
| 매장 | Hall | 손님들이 주문을 하고, 테이블에 앉아서 음식을 먹는 공간. |
| 손님 | Customer | 테이블에 앉거나, 원하는 메뉴를 주문 할 수 있다. |
| 상품 | Product | 매장에서 판매하는 음식. |
| 상품 이름 | Product Name | 상품의 이름. |
| 상품 가격 | Product Price | 상품의 가격. |
| 메뉴 | Menu | 상품과 개수를 포함한다. |
| 메뉴 이름 | Menu Name | 메뉴의 이름. |
| 메뉴 가격 | Menu Price | 메뉴의 가격이고, 메뉴 가격 정책에 따라 결정된다. |
| 메뉴 가격 정책 | Menu Price Policy | 메뉴의 가격은 포함된 (상품 가격 * 상품 수량)의 합보다 작아야 한다. |
| 메뉴 그룹 | Menu Group | 메뉴들을 적절히 그룹 지을 수 있다. |
| 주문 | Order | 손님이 요청한 메뉴를 뜻한다. |
| 주문 상태 | Order Status | 주문은 조리 중, 식사 중, 계산 완료 상태가 있다. |
| 조리 중 | Cooking | 주문을 만드는 중이다. |
| 식사 중 | Meal | 주문된 메뉴가 주문 테이블로 나왔다. |
| 계산 완료 | Completion | 손님이 계산을 완료했다. |
| 주문 테이블 | Order Table | 손님이 앉아서 주문을 하고, 식사를 하는 공간이다. |
| 주문 테이블 상태 | Order Table Status | 비어 있는 상태, 비어있지 않은 상태가 있다. 상태를 변경할 시에는 주문 테이블 상태 변경 조건을 따른다. |
| 주문 테이블 그룹 | Order Table Group | 단체 손님을 위해서 주문 테이블을 그룹 설정, 해지 할 수 있다. 이때 주문 테이블 상태 변경 조건을 따른다. |
| 주문 테이블 상태 변경 조건 | Order Table Status Change Condition | 주문 테이블의 주문 상태가 완료라면 주문 테이블의 상태를 변경할 수 있다. |
| 주문 테이블 그룹 설정 조건 | Order Table Group Set Condition | 주문 테이블의 주문 상태가 완료라면 주문 테이블 그룹을 설정 할 수 있다. |
| 주문 테이블 그룹 해지 조건 | Order Table Group Unset Condition | 주문 테이블의 주문 상태가 완료라면 주문 테이블 그룹을 해지 할 수 있다. |

## 모델링
