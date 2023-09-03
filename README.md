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
### 예시

치킨 메뉴판
1. 매운 치킨 (메뉴그룹)
- 메뉴1 세트(메뉴): 청양치킨 16000원 (상품/가격) + 음료 2000원(상품/가격) = 18000원  (메뉴상품: 청양치킨, 음료 / 가격: 18000원)
- 메뉴2(메뉴):땡초치킨 16000원(상품/가격)  (메뉴상품: 땡초치킨 / 가격: 16000원)

2. 달콤한 치킨 (메뉴그룹)
- 메뉴3(메뉴):뿌링클치킨 16000원 (상품/가격) + 음료 2000원(상품/가격) = 18000원 (메뉴상품: 뿌링클, 음료 / 가격: 18000원)
- 메뉴4(메뉴):간장치킨  15000원 (메뉴상품 / 가격)



### 메뉴그룹
| 한글명    | 영문명       | 설명                |
|--------|-----------|-------------------|
| 메뉴그룹   | menuGroup | 메뉴를 기준에 따라 분류한 묶음 |
| 메뉴그룹생성 | create    | 메뉴그룹을 새롭게 생성함     |
| 메뉴그룹명  | name      | 메뉴그룹의 이름          |

### 메뉴
| 한글명      | 영문명         | 설명                         |
|----------|-------------|----------------------------|
| 메뉴       | menu        | 고객의 구매 단위이며 하나 이상의 상품으로 구성 |
| 메뉴생성     | create      | 메뉴를 새롭게 생성함                |
| 가격       | price       | 메뉴에 표기된 상품의 판매 금액          |
| 메뉴그룹     | menuGroup   | 메뉴를 기준에 따라 분류한 묶음          |
| 메뉴 상품    | menuProduct | 메뉴에 포함된 상품                 |
| 상품       | product     | kitchenPos가 판매하는 물건        |
| 수량       | quantity    | 구매한 상품의 개수                 |
| 상품명      | name        | 상품의 이름                     |
| 가격변경     | changePrice | 메뉴에 적힌 상품의 가격을 변경함         |
| 메뉴 표기    | displayed   | 메뉴상에 상품의 노출 여부             |
| 메뉴노출     | display     | 메뉴상에 상품을 노출함               |
| 메뉴숨김     | hide        | 메뉴상에 상품을 숨김                |

### 주문
| 한글명      | 영문명                 | 설명                                                           |
|----------|---------------------|--------------------------------------------------------------|
| 주문       | order               | 고객이 구매를 희망할 때 필요한 정보                                         |
| 주문 생성    | create              | 주문을 새롭게 생성함                                                  |
| 주문 유형    | orderType           | 주문 방법 ('배달', '포장', '매장식사' 로 구성됨)                             |
| 배달       | DELIVERY            | 주문 방법 중 음식을 가져다주도록 요청함                                       |
| 포장       | TAKEOUT             | 주문 방법 중 음식을 가지고 나가도록 요청함                                     |
| 매장식사     | EAT_IN              | 주문 방법 중 음식을 매장에서 먹도록 요청함                                     |
| 주문 상태    | orderStatus         | 주문의 진행상황을 나타냄 ('대기중', '승인', '제공됨', '배달중', '배달됨', '완료' 로 구성됨) |
| 대기중      | WAITING             | 주문 접수 후 주문을 승인하기 전                                           |
| 승인       | ACCEPTED            | 주문 접수 후 주문을 확인함                                              |
| 제공됨      | SERVED              | 주문 접수 메뉴를 고객에게 전달함                                           |
| 배달중      | DELIVERING          | 상품을 배달하고 있음                                                  |
| 배달됨      | DELIVERED           | 상품이 배송지에 배달 완료됨                                              |
| 완료       | COMPLETED           | 상품이 고객에게 제공됨                                                 |
| 주문시간     | orderDateTime       | 주문을 한 날짜, 시간                                                 |
| 주문항목     | orderLineItems      | 주문한 메뉴                                                       |
| 메뉴       | menu                | 고객이 구매 가능한 상품                                                |
| 배달주소     | deliveryAddress     | 주문을 배달할 장소                                                   |
| 배달원      | KitchenridersClient | 배달하는 내용에 대한 설명                                               |
| 승인상태확인   | accept              | 주문 확인 상태 확인                                                  |
| 제공상태확인   | serve               | 상품 준비 상태 확인                                                  |
| 배달출발상태확인 | startDelivery               | 배달중 상태 확인                                                    |
| 배달도착상태확인 | completeDelivery    | 배달완료 상태 확인                                                   |
| 완료상태확인   | complete            | 상품이 고객에게 제공된 상태 확인                                           |

### 주문 테이블
| 한글명       | 영문명                  | 설명                 |
|-----------|----------------------|--------------------|
| 주문 테이블    | orderTable           | 매장에 구비되어있는 테이블     |
| 주문 테이블 생성 | create               | 주문 테이블을 새롭게 생성함    |
| 테이블명      | name                 | 테이블의 이름            |
| 테이블 손님수   | numberOfGuests       | 테이블에 앉아있는 손님수      |
| 테이블 사용여부  | Occupied             | 현재 테이블의 사용 여부      |
| 테이블 사용    | sit                  | 손님이 테이블에 앉음        |
| 테이블 정리    | clear                | 손님이 테이블에서 일어남      |
| 손님수 변경    | changeNumberOfGuests | 테이블에 앉아있는 손님수를 변경함 |

### 상품
| 한글명       | 영문명                  | 설명                 |
|-----------|----------------------|--------------------|
| 상품       | product             | kitchenPos가 판매하는 물건      |
| 상품 가격    | price               | 상품의 판매 금액                |
| 상품생성 | create    | 상품을 새롭게 생성함     |
| 가격변경     | changePrice         | 상품의 가격을 변경함       |
| 상품명      | name                | 상품의 이름                   |
| 메뉴       | menu                | 고객이 구매 가능한 상품            |



## 모델링
