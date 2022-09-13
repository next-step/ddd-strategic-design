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

| 한글명         | 영문명                   | 설명                                                         | 예시             |
|-------------|-----------------------|------------------------------------------------------------|----------------|
| 상품          | product               | 손님에게 제공되는 음식을 뜻한다.                                         | 후라이드 치킨, 양념 치킨 |
| 비속어         | profanity             | 욕설 등, 상품 이름에 들어가기 적절하지 않은 단어를 뜻한다.                         |                |
| 상품 가격 변경 정책 | changing price policy | 변경된 가격의 상품을 구성하는 메뉴 중에서, 메뉴의 가격이 구성된 상품의 가격보다 크면 메뉴가 가려진다. |                |

### 메뉴 그룹

| 한글명   | 영문명        | 설명            | 예시           |
|-------|------------|---------------|--------------|
| 메뉴 그룹 | menu group | 메뉴들의 집합을 뜻한다. | 추천 메뉴, 인기 메뉴 |

### 메뉴

| 한글명      | 영문명               | 설명                                            | 예시           |
|----------|-------------------|-----------------------------------------------|--------------|
| 메뉴       | menu              | 손님이 주문 할 수 있는 메뉴 상품의 집합을 뜻한다.                 | 양념 치킨 두마리 세트 |
| 메뉴 상품    | menu product      | 메뉴의 구성 요소로, 상품과 상품의 수량으로 구성된다.                |              |
| 비속어      | profanity         | 욕설 등, 메뉴 이름에 들어가기 적절하지 않은 단어를 뜻한다.            |              |
| 메뉴 노출    | display menu      | 손님이 메뉴를 주문 할 수 있음을 뜻한다.                       |              |
| 메뉴 숨김    | hide menu         | 손님이 메뉴를 주문 할 수 없음을 뜻한다.                       |              |
| 메뉴 가격 정책 | menu price policy | 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우 메뉴를 노출할 수 없다. |              |

### 주문 테이블

| 한글명    | 영문명               | 설명                                          | 예시  |
|--------|-------------------|---------------------------------------------|-----|
| 주문 테이블 | order table       | 손님이 매장 주문 시 사용하는 테이블을 뜻한다.                  |     |
| 빈 테이블  | empty order table | 손님이 사용하지 않는 테이블을 뜻한다. 손님은 빈 테이블에 착석 할 수 있다. |     |
| 방문한 손님 | guest             | 주문 테이블을 사용중인 손님을 뜻한다.                       |     |

### 주문

| 한글명           | 영문명                   | 설명                                    | 예시  |
|---------------|-----------------------|---------------------------------------|-----|
| 주문            | order                 | 손님이 요청한 주문 항목의 집합을 뜻한다.               |     |
| 주문 항목         | order line item       | 주문의 구성 요소로, 메뉴와 수량으로 구성된다.            |     |
| 주문 유형 - 배달 주문 | delivery order type   | 배달 대행사를 통해 상품을 전달받는 주문 유형을 뜻한다.       |     |
| 주문 유형 - 포장 주문 | takeout order type    | 손님이 매장을 방문해 상품을 수령하는 주문 유형을 뜻한다.      |     |
| 주문 유형 - 매장 주문 | eat in order type     | 손님이 매장을 방문해 주문 테이블에서 주문하는 주문 유형을 뜻한다. |     |
| 배달 주소         | delivery address      | 배달 주문 시, 손님이 상품을 전달받을 주소를 뜻한다.        |     |
| 배달 대행사        | kitchen riders client | 배달 주문 상품을 손님에게 전달하는 배달 중개 서비스를 뜻한다.   |     |
| 주문 접수 대기      | waiting order         | 손님이 주문 후, 주문이 수락되기 전 상태를 뜻한다.         |     |
| 주문 접수         | accept order          | 접수 대기 중인 주문을 수락하고 상품 조리를 시작한다.        |     |
| 주문 서빙         | served order          | 접수 된 주문의 상품 조리가 완료되었음을 뜻한다.           |     |
| 주문 배달 시작      | start delivery order  | 서빙된 배달 주문을 배달 대행사를 통해 배달을 시작했음을 뜻한다.  |     |
| 주문 배달 완료      | delivered order       | 손님에게 배달 주문이 배달 완료되었음을 뜻한다.            |     |
| 주문 완료         | complete order        | 주문이 정상적으로 완료되었음을 뜻한다.                 |     |

## 모델링