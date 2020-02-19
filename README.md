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
| 관리자 | Manager | 매장을 관리하는 주체이다. |
| 상품 | Product | 매장에서 판매하는 음식. |
| 상품 이름 | Product Name | 상품의 이름. |
| 상품 가격 | Product Price | 상품의 가격. |
| 상품 가격 정책 | Product Price Policy | 상품의 가격은 0 원 이상이어야 한다. |
| 메뉴 | Menu | 상품과 개수를 포함한다. |
| 메뉴 이름 | Menu Name | 메뉴의 이름. |
| 메뉴 가격 | Menu Price | 메뉴의 가격이고, 메뉴 가격 정책에 따라 결정된다. |
| 메뉴 가격 정책 | Menu Price Policy | 메뉴의 가격은 포함된 (상품 가격 * 상품 수량)의 합보다 작아야 한다. |
| 메뉴 그룹 | Menu Group | 메뉴들을 적절히 그룹 지을 수 있다. |
| 주문 | Order | 손님이 요청한 메뉴를 뜻한다. |
| 주문 정책 | Order Policy | 비어 있지 않은 테이블이고, 존재하는 메뉴여야 한다. |
| 주문 상태 | Order Status | 주문은 조리 중, 식사 중, 계산 완료 상태가 있다. |
| 주문 상태 변경 조건 | Order Status Change Condition | 조리 중, 식사 중 상태의 주문이어야 한다. |
| 조리 중 | Cooking | 주문을 만드는 중이다. |
| 식사 중 | Meal | 주문된 메뉴가 주문 테이블로 나왔다. |
| 계산 완료 | Completion | 손님이 계산을 완료했다. |
| 주문 테이블 | Order Table | 손님이 앉아서 주문을 하고, 식사를 하는 공간이다. |
| 주문 테이블 상태 | Order Table Status | 비어 있는 상태, 비어있지 않은 상태가 있다. 상태를 변경할 시에는 주문 테이블 상태 변경 조건을 따른다. |
| 주문 테이블 그룹 | Order Table Group | 단체 손님을 위해서 주문 테이블을 그룹 설정, 해지 할 수 있다. 이때 주문 테이블 상태 변경 조건을 따른다. |
| 주문 테이블 상태 변경 조건 | Order Table Status Change Condition | 주문 테이블의 주문이 없거나, 주문 상태가 완료거나, 주문 테이블 그룹 설정이 되어 있지 않아야 한다. |
| 주문 테이블 그룹 설정 조건 | Order Table Group Set Condition | 주문 테이블 그룹이 중복되지 않고, 주문 테이블들의 주문 상태가 계산 완료여야 한다. |
| 주문 테이블 그룹 해지 조건 | Order Table Group Unset Condition | 주문 테이블들의 주문 상태가 계산 완료여야 한다. |
| 방문 손님 | Visit Customer | 주문 테이블에 앉은 손님이다. |
| 방문 손님 수 변경 조건 | Count of Visit Customer Change Condition | 방문 손님은 0명 이상이어야 하고, 주문 테이블이 빈 상태가 아니여야 한다. |

## 모델링

- 상품
  - 상품은 상품 이름과 상품 가격을 가진다.
  - 상품을 등록한다.
    - 상품 가격은 0원 이상이어야 한다.
  - 상품 목록을 조회한다.
- 메뉴
  - 메뉴는 메뉴 이름, 메뉴 가격, 메뉴 그룹 ID, 상품 리스트를 가진다.
    - 메뉴는 하나의 메뉴 그룹에 속해야 한다.
    - 상품 리스트는 상품 ID, 상품 수량으로 구성된다.
  - 메뉴를 등록한다.
    - 메뉴 가격은 상품 리스트의 총 가격보다 작아야 한다.
  - 메뉴 목록을 조회한다.
  - 메뉴 그룹은 메뉴 그룹 이름을 가진다.
  - 메뉴 그룹을 등록한다.
    - 메뉴는 등록된 메뉴 그룹 ID를 포함한다.
  - 메뉴 그룹 목록을 조회한다.
- 주문 테이블
 - 주문 테이블은 주문 테이블 그룹 ID, 방문 손님 수, 주문 테이블 상태를 가진다.
   - 주문 테이블 상태는 비어 있는 상태, 비어있지 않은 상태를 가진다.
 - 주문 테이블을 등록한다.
   - 주문 테이블 그룹 ID는 주문 테이블 그룹에 속할때 수정된다.
 - 주문 테이블 목록을 조회한다.
 - 주문 테이블의 상태를 변경한다.
   - 주문 테이블의 주문이 없거나, 주문 상태가 완료거나, 주문 테이블 그룹 설정이 되어 있지 않아야 한다.
 - 주문 테이블의 방문 손님 수를 변경한다.
   - 방문 손님 수는 0명 이상이어야 한다.
- 주문 테이블 그룹
  - 주문 테이블 그룹은 생성 시각, 주문 테이블 목록을 가진다.
  - 주문 테이블 그룹을 등록한다.
    - 주문 테이블들을 주문 테이블 그룹 설정 조건에 따라서 주문 테이블 그룹으로 설정한다.
    - 포함되는 주문 테이블들의 주문 테이블 그룹이 중복되지 않고, 주문 상태가 계산 완료여야 한다.
  - 주문 테이블 그룹을 해지한다.
    - 주문 테이블들의 주문 상태가 계산 완료여야 한다.
- 주문
  - 주문은 메뉴 목록과 수량, 주문 상태, 주문 테이블 ID를 가진다.
    - 조리 중, 식사 중, 계산 완료 상태가 있다.
  - 주문을 등록한다.
    - 비어 있지 않은 테이블이고, 존재하는 메뉴여야 한다.
  - 주문 목록을 조회한다.
  - 주문 상태를 변경한다.
    - 조리 중, 식사 중 상태의 주문만 변경 가능하다.
