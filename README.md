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

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 손님 | guest | 식당에서 테이블에 앉아있는 사람 |
| 상품 | product | 요리되어 제공되는 단위 |
| 메뉴그룹 | menuGroup | 메뉴의 카테고리 (상위범위) |
| 메뉴항목 | menuProduct | 메뉴에 해당하는 상품과 그 상품의 개수로 이루어 진 단위 |
| 메뉴 | menu | 주문할 수 있는 최소단위, 이름과 가격으로 이루워 져 있다. |
| 테이블 | orderTable | 손님이 주문을 하고 식사를 하는 장소, 몇명의 손님이 사용하고 있는지를 포함한다. |
| 빈 테이블 |  | 어느 손님이 사용하고 있지 않은 테이블, 이 상태의 테이블만 단체지정, 주문할 수 있다. |
| 단체지정 | tableGroup | 여러개의 테이블을 묶어 놓은 단위 |
| 주문항목 | orderLineItem | 주문에 하당하는 메뉴와 그 메뉴의 개수로 이루어 진 단위 |
| 주문 | order | 손님이 주문할 수 있는 단위로, 1개 이상의 주문항목들과 해당하는 테이블을 포함한다. |
| 주문상태 | orderStatus | 테이블의 주문이 요리인지, 식사인지, 계산완료 상태인지를 표현 |
| 요리 | cooking | 주문이 들어오고 아직 손님이 식사를 하고 있지 않은 상태, 주문이 생성되면 이 주문은 요리중 상태를 갖는다. |
| 식사 | meal | 손님이 식사중인 상태  |
| 계산완료 | completion | 계산이 완료된 상태, 이 때 더 이상 주문의 상태는 변경할 수 없고, 테이블을 빈 상태로 변경할 수 있다.  |

## 모델링

### 상품
- 상품은 이름이 있다.
- 상품은 가격이 있다.
    - 상품의 가격은 0원 이상이다.

### 메뉴
- 메뉴 그룹을 추가했다.
- 메뉴 그룹은 이름이 있다.
- 메뉴 그룹은 메뉴를 포함한다.

- 메뉴를 메뉴그룹에 추가했다. 
    - 메뉴는 이름이 있다.
    - 메뉴는 1개 이상의 상품과 각 상품의 개수로 메뉴를 추가했다.
    - 메뉴는 가격이 있다.
        - 메뉴의 가격은 0원 이상이다.
        - 메뉴의 가격은 상품들의 가격의 합보다 작거나 같다.
    
### 테이블
- 테이블을 추가했다.

- 2개 이상의 빈 테이블을 합쳐 단체석을 만들었다.
    - 단체석이 생성된 시간을 기록했다.
    - 단체석으로 생성된 테이블은 빈 테이블이 아니다.

- 1명 이상의 손님이 비어있지 않은 테이블에 앉았다.
    - 몇명의 손님이 앉았는지 기록했다.
    
- 계산이 완료된 테이블을 비웠다.
    - 단체석은 분리한 후에 비웠다.

### 주문
- 테이블에 앉은 손님이 주문을 했다.
    - 손님이 1개 이상의 메뉴와 각 메뉴의 개수로 주문을 했다.
    - 손님이 주문을 해서 주문이 요리중 상태로 변경되었다.
        - 계산이 완료되지 않은 주문의 상태가 변경되었다.

 
