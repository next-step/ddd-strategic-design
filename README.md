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

### 상품

| 한글명     | 영문명       | 설명                         |
|---------|-----------|----------------------------|
| 상품      | Product   | 가격과 이름을 가지며, <br> 이름에는 비속어가 포함되지 말아야 한다.|

### 메뉴 그룹

| 한글명    | 영문명         | 설명            |
|--------|-------------|---------------|
| 메뉴 그룹  | Menu Group  | 메뉴를 묶을수 있는 단위 |

### 메뉴

| 한글명   | 영문명          | 설명                 |
|-------|--------------|--------------------|
| 메뉴    | Menu         | 주문을 하는 대상 <br> 가격,이름,메뉴 상품,메뉴그룹,표시 여부를 가진다 <br> 이름에는 비속어가 포함 되지 않는다.| 
| 메뉴상품  | Menu Product | 메뉴에 속한 상품, 상품과 상품의 수량을 가진다.|
| 표시 여부 | Displayed    | 메뉴가 노출되어있는지에 대한 여부 |

### 주문 테이블

| 한글명    | 영문명              | 설명                 |
|--------|------------------|--------------------|
| 주문 테이블 | Order Table      | 매장 주문시 사용하게 되는 테이블 |
| 손님수    | Number Of Guests | 주문테이블을 사용하게될 손님수   |
| 빈 테이블  | Empty Table      | 주문테이블이 비어있는 경우     |


### 주문

| 한글명    | 영문명              | 설명                        |
|--------|------------------|---------------------------|
| 주문     | Order            | 메뉴에 등록된 상품을 구매하는 행위, <br> 주문항목, 주문유형, 주문상태를 가진다.|
| 주문 항목  | Order Line Item  | 주문시 선택한 메뉴와 수량을 나타낸다. |
| 주문 유형  | Order Type       | 주문시 반드시 선택해야 하는 유형, <br>  배달주문, 포장주문, 매장 주문이 있다.|
| 주문 상태  | Order Status     | 현재 주문의 진행 상태, <br> 접수대기, 주문접수, 주문서빙, 배달중, 배달 완료, 주문 완료가 있다.|
| 배달 주소  | Delivery Address | 배달을 받는 장소를 뜻한다.           |
| 배달 대행사 | Kitchen Riders   | 배달 주문에 대해서 배달을 하는 대행사        |


### 주문 유형
| 한글명    | 영문명              | 설명                        |
|--------|------------------|---------------------------|
| 배달 주문  | Delivery Order   | 배달 주소로 주문 항목들을 배달하는 주문 유형 |
| 포장 주문  | Takeout Order    | 주문을 직접 가져가는 주문 유형         |
| 매장 주문  | Eat In Order     | 매장에서 주문하는 주문 유형           |

### 주문 상태 (매장 주문, 포장 주문)
| 한글명    | 영문명              | 설명                        |
|--------|------------------|---------------------------|
| 접수 대기  | Waiting          | 주문의 생성시 초기 상태             |
| 주문 접수  | Accepted   | 주문이 수락된 상태                |
| 주문 서빙  | Served     | 주문에 대해서 서빙이 완료된 상태        |
| 주문 완료  | Completed  | 주문의 모든 과정이 완료된 상태         |

### 주문 상태 (배달 주문)
| 한글명    | 영문명              | 설명                        |
|--------|------------------|---------------------------|
| 접수 대기  | Waiting          | 주문의 생성시 초기 상태             |
| 주문 접수  | Accepted   | 주문이 수락된 상태                |
| 주문 서빙  | Served     | 주문에 대해서 서빙이 완료된 상태        |
| 배달중    | Delivering       | 주문이 배달중인 상태               |
| 배달 완료  | Delivered        | 주문이 배달 완료된 상태             |
| 주문 완료  | Completed  | 주문의 모든 과정이 완료된 상태         |


### 공통
| 한글명 | 영문명       | 설명                    |
|-----|-----------|-----------------------|
| 비속어 | Profanity | 상품, 메뉴 이름에는 사용할 수 없다. |

## 모델링
