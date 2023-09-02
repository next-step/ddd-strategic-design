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

### 공통

| 한글명    | 영문명       | 설명              |
|--------|-----------|-----------------|
| 비속어     | profanity   | 비속어      |

### 상품

| 한글명    | 영문명       | 설명             |
|--------|-----------|----------------|
| 상품     | product   | 메뉴에 등록할 상품     |
| 상품 가격  | price     | 상품의 가격 (0원 이상) |
| 상품 이름  | name      | 상품의 이름(비속어 불가) |
| 상품 목록	 | list      | 상품의 목록         |

### 메뉴 그룹

| 한글명      | 영문명        | 설명        |
|----------|------------|-----------|
| 메뉴 그룹    | menu group | 등록된 메뉴 모음 |
| 메뉴 그룹 이름 | name       | 메뉴 그룹의 이름 |
| 메뉴 그룹 목록 | list       | 메뉴 그룹의 목록 |

### 메뉴

| 한글명         | 영문명          | 설명                         |
|-------------|--------------|----------------------------|
| 메뉴          | menu         | 메뉴그룹에 속하고 상품을 1개 이상 포함하는 메뉴 |
| 메뉴 가격       | price        | 메뉴의 가격 (0원 이상)             |
| 메뉴 구성 상품	   | menu product | 메뉴를 구성하는 상품                |
| 메뉴 상품 수량    | quantity     | 메뉴에 등록된 상품의 수량             |
| 메뉴 주문 가능 여부 | displayed    | 고객의 메뉴 주문 가능 여부 상태         |
| 주문 가능       | display      | 주문 가능 상태                   |
| 주문 불가능      | hide         | 주문 불가능 상태                  |
| 메뉴 이름       | name         | 메뉴의 이름(비속어 불가)             |
| 메뉴 목록       | list         | 메뉴의 목록                     |

### 주문 테이블

| 한글명       | 영문명                     | 설명                   |
|-----------|-------------------------|----------------------|
| 주문 테이블    | order table             | 매장 주문을 할 고객이 사용할 테이블 |
| 주문 테이블 이름 | name                    | 주문 테이블 이름            |
| 테이블 사용 여부 | occupied                | 고객의 테이블 사용 여부        |
| 사용 테이블	   | occupied order table	   | 사용 테이블               |
| 미사용 테이블	  | unoccupied order table	 | 미사용 테이블              |
| 사용        | sit                     | 테이블의 상태를 사용으로 변경     |
| 미사용       | clear                   | 테이블의 상태를 미사용으로 변경    |
| 고객수       | number of guests        | 테이블을 사용중인 고객수        |
| 주문 테이블 목록 | list                    | 주문 테이블의 목록           |

### 주문

### 주문 공통

| 한글명    | 영문명              | 설명                 |
|--------|------------------|--------------------|
| 주문 타입  | order type       | 주문 타입(배달,포장,매장 주문) |
| 주문한 메뉴 | order line       | 주문한 메뉴             |
| 주문 상태  | order status     | 주문 진행 상태           |
| 주문 가격  | order line price | 주문한 메뉴의 총 가격       |
| 주문 목록  | list             | 주문 목록              |

### 배달 주문

| 한글명      | 영문명              | 설명                                                 |
|----------|------------------|----------------------------------------------------|
| 배달 주문    | delivery         | 배달 주문(주문 타입)                                       |
| 배달 기사    | kitchenrider     | 주문 배달 기사                                           |
| 배달 주소    | delivery address | 주문 배달 주소                                           |
| 접수 중     | waiting          | 배달 주문 접수를 기다리는 상태(주문 진행 상태)                        |
| 접수 승인    | accept           | 배달 주문 접수 승인 완료 된 상태(주문 진행 상태)                      |
| 메뉴 제공    | serve            | 배달 주문 메뉴 제공 완료 상태(주문 진행 상태)                        |
| 배달중      | delivering       | 배달 주문 메뉴 제공이 완료 되고 배달기사 배치 후 주문 배달 중인 상태(주문 진행 상태) |
| 배달 완료    | delivered        | 배달이 완료 된 상태(주문 진행 상태)                              |
| 배달 주문 완료 | complete         | 배달 주문 완료 상태(주문 진행 상태)                              |

### 포장 주문

| 한글명      | 영문명      | 설명                            |
|----------|----------|-------------------------------|
| 포장 주문    | takeout  | 포장 주문(주문 타입)                  |
| 접수 중     | waiting  | 포장 주문 접수를 기다리는 상태(주문 진행 상태)   |
| 접수 승인    | accept   | 포장 주문 접수 승인 완료 된 상태(주문 진행 상태) |
| 메뉴 제공    | serve    | 포장 주문 메뉴 제공 완료 상태(주문 진행 상태)   |
| 포장 주문 완료 | complete | 포장 주문 완료 상태(주문 진행 상태)         |

### 매장 주문

| 한글명      | 영문명      | 설명                            |
|----------|----------|-------------------------------|
| 매장 주문    | eat in   | 매장 주문(주문 타입)                  |
| 접수 중     | waiting  | 매장 주문 접수를 기다리는 상태(주문 진행 상태)   |
| 접수 승인    | accept   | 매장 주문 접수 승인 완료 된 상태(주문 진행 상태) |
| 메뉴 제공    | serve    | 매장 주문 메뉴 제공 완료 상태(주문 진행 상태)   |
| 매장 주문 완료 | complete | 매장 주문 완료 상태(주문 진행 상태)         |

## 모델링
