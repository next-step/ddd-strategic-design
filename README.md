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

| 한글명   | 영문명           | 설명                        |
|-------|---------------|---------------------------|
| 상품    | Product       | 판매할 상품의 이름과 가격이 포함한 구성 요소 |
| 상품 가격 | Product Price | 판매할 상품의 가격                |
| 상품 이름 | Product Name  | 판매할 상품의 이름                |
| 비속어   | Profanity     | 부적절한 단어 또는 비속어            |

### 메뉴

| 한글명      | 영문명                   | 설명                     |
|----------|-----------------------|------------------------|
| 메뉴       | Menu                  | 판매할 수 있는 최소 단위, 상품의 묶음 |
| 메뉴 가격    | Menu Price            | 메뉴에 표시될 가격             |
| 메뉴 이름    | Menu Name             | 메뉴에 표시될 이름             |
| 전시 메뉴    | Displayed Menu        | 판매 가능한 메뉴              |
| 숨김 메뉴    | Hidden Menu           | 판매 불가능한 메뉴             |
| 메뉴 상품    | Menu Product          | 메뉴에 등록된 상품             |
| 메뉴 상품 수량 | Menu Product Quantity | 메뉴에 등록된 상품의 수량         |

### 메뉴 그룹

| 한글명      | 영문명             | 설명            |
|----------|-----------------|---------------|
| 메뉴 그룹    | Menu Group      | 메뉴들의 묶음       |
| 메뉴 그룹 이름 | Menu Group Name | 메뉴 그룹에 표시될 이름 |

### 주문 테이블

| 한글명    | 영문명             | 설명                             |
|--------|-----------------|--------------------------------|
| 테이블    | Order Table     | 매장에 배치된 테이블                    |
| 빈 테이블  | Empty Table     | 손님이 없는 테이블                     |
| 완료 테이블 | Completed Table | 주문이 완료된 테이블, 빈 테이블로 지정 가능한 테이블 |
| 손님     | Table Guest     | 테이블에 앉은 손님                     |

### 주문

| 한글명      | 영문명                 | 설명                             |
|----------|---------------------|--------------------------------|
| 주문 메뉴    | Order Menu          | 주문할 수 있는 최소 단위, (메뉴, 수량, 금액)   |
| 주문 메뉴 수량 | Order Menu Quantity | 주문할 메뉴의 수량                     |
| 주문       | Order               | 하나 이상의 주문 항목으로 구성된 단위          |
| 주문 가격 | Order Price         | 주문 가격                          |
| 배달 주소 | Delivery Address    | 고객 주소                    |
| 주문 유형    | Order Type          | 주문의 유형 대분류, 참고 [주문 유형](#주문-유형) |
| 주문 상태    | Order Status        | 주문 상태 대분류, 참고 [주문 상태](#주문-상태)  |
| 배달 대행사   | Delivery Agency     | 배달 주문을 수행할 업체                  |

### 주문 유형

| 한글명   | 영문명          | 설명             |
|-------|--------------|----------------|
| 배달 주문 | Delivery     | 배달로 요청한 주문 유형  |
| 포장 주문 | Takeout      | 포장으로 요청한 주문 유형 |
| 매장 주문 | Eat in       | 매장에서 요청한 주문 유형 |

### 주문 상태

| 한글명   | 영문명          | 설명                  |
|-------|--------------|---------------------|
| 대기 | Waiting      | 주문 접수 상태            |
| 접수 | Accepted     | 주문을 수락한 상태          |
| 서빙 | Served       | 매장에서 주문한 상품을 전달한 상태 |
| 배달 중  | Delivering   | 주문한 상품을 배달하고 있는 상태  |
| 배달 완료 | Delivered    | 배달 완료 상태            |
| 주문 완료 | Completed    | 주문 완료 상태            |

## 모델링

## 모델링
