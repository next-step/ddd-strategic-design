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
- 빈 테이블을 점유할 수 있다.
- 테이블을 비울 수 있다.
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

| 한글명     | 영문명            | 설명                       |
|---------|------------------|--------------------------|
| 상품      | product          | 키친포스에서 판매하는 음식           |
| 가격(금액)  | price            | 키친포스에서 판매하는 상품의 가격       |
| 수량      | quantity         | 상품의 개수                   |
| 비속어 검증기 | Purgomalum   | 상품 이름에 포함된 비속어를 검증하는 시스템 |

### 메뉴

| 한글명        | 영문명          | 설명                       |
|------------|--------------|--------------------------|
| 메뉴 그룹      | menu group   | 관련된 메뉴를 묶은 카테고리          |
| 메뉴         | menu         | 손님이 구매 가능한 상품 묶음         |
| 가격(금액)     | price        | 키친포스에서 판매하는 메뉴의 가격       |
| 수량         | quantity     | 메뉴의 개수                   |
| 가격(금액)     | price        | 키친포스에서 판매하는 메뉴, 상품의 가격   |
| 수량         | quantity     | 상품 또는 메뉴의 개수             |
| 비속어 검증기 | Purgomalum   | 상품 이름에 포함된 비속어를 검증하는 시스템 |
| 메뉴 구성 상품   | menu product | 메뉴에 구성되는 여러 상품           |
| 메뉴 노출      | display      | 키친포스에서 노출되는 메뉴           |
| 메뉴 숨김      | hide         | 키친포스에서 숨겨진 메뉴            |

### 주문

| 한글명     | 영문명              | 설명                                         |
|---------|------------------|--------------------------------------------|
| 주문 유형   | order type       | 손님이 주문할 수 있는 방법                    |
| 주문한 메뉴  | order line item  | 주문에 포함된 메뉴                           |
| 주문 상태   | order status     | 손님의 주문 진행 상태                        |

#### 배달 주문

| 한글명    | 영문명              | 설명                           |
|--------|------------------|------------------------------|
| 배달 주문  | delivery         | 정해진 위치로 배달해주는 주문             |
| 배달 주소  | delivery address | 배달 주문의 목적지                   |
| 배달 대행사 | kitchen rider    | 배달 주소로 상품을 배달하는 위탁 업체        |
| 접수 대기 중 | waiting          | 매장에서 주문을 접수하기 전의 상태          |
| 접수     | accepted         | 매장에서 주문을 확인한 상태              |
| 서빙     | served           | 매장에서 조리가 완료되어 배달 대행사에게 전달된 상태 |
| 배달 중    | delivering       | 손님에게 배달이 진행 중인 상태                |
| 배달 완료   | delivered        | 손님에게 상품이 전달된 상태                   |
| 주문 완료  | completed        | 주문의 모든 절차가 끝난 상태             |

#### 포장 주문

| 한글명          | 영문명            | 설명                            |
|----------------|------------------|-------------------------------|
| 포장 주문       | take out         | 손님이 매장에 방문해 상품을 가져가야 하는 주문    |
| 접수 대기 중 | waiting          | 매장에서 주문을 접수하기 전의 상태           |
| 접수     | accepted         | 매장에서 주문을 확인한 상태               |
| 서빙      | served           | 매장에서 조리가 완료되어 방문한 손님에게 전달된 상태 |
| 주문 완료  | completed        | 주문의 모든 절차가 끝난 상태              |

#### 매장 주문

| 한글명          | 영문명            | 설명                             |
|----------------|------------------|--------------------------------|
| 매장 주문       | eat in           | 손님이 주문 테이블에서 식사하는 주문           |
| 접수 대기 중 | waiting          | 매장에서 주문을 접수하기 전의 상태            |
| 접수     | accepted         | 매장에서 주문을 확인한 상태                |
| 서빙      | served           | 매장에서 조리가 완료되어 테이블로 전달된 상태 |
| 주문 완료  | completed        | 주문의 모든 절차가 끝난 상태               |

##### 주문 테이블

| 한글명         | 영문명            | 설명                                         |
|-------------|------------------|--------------------------------------------|
| 주문 테이블(테이블) | order table      | 매장에서 식사가 가능한 테이블                |
| 점유          | occupy           | 주문 테이블을 점유                           |
| 비움          | clear            | 주문 테이블을 비움                           |
| 빈 테이블       | cleared table    | 손님이 사용하지 않는 빈 테이블                |
| 손님 수        | number of guests | 주문 테이블에서 식사하는 손님의 수             |

## 모델링
