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
| 한글명 | 영문명           | 설명                                          |
|-----|---------------|---------------------------------------------|
| 상품  | product       | 매장에서 등록된 음식을 뜻한다.                           |
| 상품 가격 | product price | 한 상품의 가격을 의미한다.                             |
| 상품 이름 | product name  | 한 상품의 상품명을 의미한다.                            |
| 비속어 | profanity     | 비격식적이거나 바람직하지 않은 단어들을 의미힌다. 상품명에 포함되면 안 된다. |

### 메뉴
| 한글명      | 영문명             | 설명                                         |
|----------|-----------------|--------------------------------------------|
| 메뉴       | menu            | 여러 개의 상품으로 구성되어 있어 손님이 주문할 수 있다.           |
| 메뉴 그룹    | menu group      | 메뉴를 일정 기준에 따라 분류하여 묶어둔 그룹이다.               |
| 메뉴 그룹 이름 | menu group name | 메뉴 그룹의 그룹명을 의미한다.                          |
| 노출된 메뉴   | visible menu     | 매장에 등록된 메뉴로 포스기 화면에서 볼 수 있다.               |
| 숨겨진 메뉴   | hidden menu     | 매장에 등록된 메뉴지만 포스기 화면에서 볼 수 없는 메뉴다.          |
| 비속어 | profanity     | 비격식적이거나 바람직하지 않은 단어들을 의미힌다. 메뉴명에 포함되면 안 된다. |

### 주문 - 공용
| 한글명   | 영문명             | 설명                                                                        |
|-------|-----------------|---------------------------------------------------------------------------|
| 주문    | order           | 손님이 원하는 음식을 정해서 매장에 시킨다.                                                  |
| 주문 라인 | order line item | 손님이 주문한 메뉴. 메뉴는 1개 이상이 될 수 있다.                                            |
| 주문 유형 | order type      | 손님이 주문하면 완성된 음식을 전달 받는 방식이다. <br/>종류로는 배달 주문, 포장 주문, 매장 주문이 있다.           |
| 주문 상태 | order status    | 주문에서 배달까지의 현 상황을 표현한다. <br/>종류로는 대기 중, 주문 받음, 배달 중, 배달 완료, 서빙 완료, 완료가 있다. |
| 대기 중  | waiting         | 주문을 받기 전 초기 상태다.                                                          |
| 주문 받음 | accepted        | 손님에게 주문을 받은 상태다.                                                          |
| 서빙 완료    | served           | 주문 받은 요리를 완료한 상태이다.              |
| 완료    | completed       | 모든 주문 과정이 완료된 상태다.                                                        |

### 배달 주문
| 한글명 | 영문명 | 설명                                        |
| -- | --- |-------------------------------------------|
| 배달 주문 | delivery | 주문을 받으면 라이더를 통해 손님에게 완성된 음식을 전달한다.        |
| 배달 주소 | delivery address | 배달로 오는 음식을 받을 손님의 주소다.                    |
| 배달 중 | delivering | 배달로 주문한 손님에게 완성된 음식을 라이더에게 전달해 이동 중인 상태다. |
| 배달 완료 | delivered | 배달로 주문한 손님에게 음식을 전달한 상태다.                 |

### 포장 주문
| 한글명 | 영문명 | 설명                                |
| -- | --- |-----------------------------------|
| 포장 주문 | takeout | 주문을 받으면 매장으로 온 손님에게 완성된 음식을 서빙한다. |

### 매장 주문
| 한글명      | 영문명              | 설명                                         |
|----------|------------------|--------------------------------------------|
| 매장 주문    | eat in           | 주문을 받으면 매장의 주문 테이블에 앉은 손님에게 음식을 서빙한다.      |
| 주문 테이블   | order table      | 매장으로 방문한 손님이 앉을 수 있는 자리다.                  |
| 주문 테이블 이름 | order table name | 주문 테이블의 테이블명이다.                            |
| 테이블에 앉은 고객 수 | numberOfGuests   | 주문 테이블 하나에 앉아 있는 고객의 수. 빈 테이블이면 0명으로 처리된다. |
| 테이블 사용 여부 | occupied         | 주문 테이블에 고객이 앉아 있는지 아닌지 체크한다.               |
| 빈 테이블    | empty table      | 손님이 앉아 있지 않고 새 손님이 왔을 때 앉을 수 있는 테이블이다.     |
| 앉은 테이블   | occupied table   | 손님이 앉아있고, 주문이 완료될 때까지 차있는 상태로 유지되는 테이블이다.  |

## 모델링
- 엔티티나 밸류 타입인 경우 영문 표기에서 대문자로 시작한다
- 그 외 다른 단어는 영문 표기에서 소문자로 시작한다
- 엔티티, 밸류 타입, 필드는 `backtick`으로 감싸서 표현하고, 그 외 단어는 감싸지 않는다
- 이넘 클래스 내 타입은 전체 대문자로 표현한다

###  상품(`Product`)
- 상품(`Product`)은 이름(`name`), 가격(`price`)을 가진다

- 상품(`Product`)을 등록(create)한다
  - 상품(`Product`)의 가격(`price`)은 0원 이상이어야 한다
  - 상품(`Product`)의 이름(`name`)에는 비속어(profanity)가 포함될 수 없다

- 상품(`Product`)의 가격(`price`)을 변경(changePrice)한다
  - 상품(`Product`)의 가격(`price`)은 0원 이상이어야 한다
  - 변경하려는 상품(`Product`)이 포함된 모든 메뉴(`Menu`)와 비교한다
    - 기존 메뉴(`Menu`)의 총합보다 변경된 메뉴(`Menu`)의 금액의 합이 크면 메뉴(`Menu`)를 화면(`displayed`)에서 숨긴다

- 모든 상품(`Product`)을 가져온다(findAll)

###  메뉴(`Menu`)
- 메뉴(`Menu`)는 이름(`name`), 가격(`price`), 화면 노출 여부(`displayed`)를 가진다

- 메뉴(`Menu`)를 등록(create)한다
  - 메뉴(`Menu`)의 가격(`price`)은 0원 이상이어야 한다
  - 메뉴(`Menu`)는 특정 메뉴 그룹(`MenuGroup`)에 속해 있어야 한다
  - 메뉴(`Menu`)에 1개 이상의 등록된 상품(`Product`)이 있어야 한다
    - 각 상품(`Product`)의 개수는 1개 이상이어야 한다
    - 메뉴(`Menu`)의 가격(`price`)은 메뉴(`Menu`)가 속한 상품 금액의 합보다 작거나 같아야 한다
  - 메뉴(`Menu`)의 이름(`name`)에는 비속어(profanity)가 포함될 수 없다

- 메뉴(`Menu`)의 가격(`price`)을 변경(changePrice)한다
  - 상품(`Product`)의 가격(`price`)은 0원 이상이어야 한다
  - 기존 메뉴(`Menu`)의 가격(`price`)은 변경된 메뉴(`Menu`)가 속한 상품 금액의 합보다 작거나 같아야 한다

- 메뉴(`Menu`)를 화면(`displayed`)에 전시(display)한다
  - 메뉴(`Menu`)의 가격(`price`)은 메뉴(`Menu`)가 속한 상품 금액의 합보다 작거나 같아야 한다
  - 메뉴(`Menu`)의 화면(`displayed`)에서 보이게 한다.

- 메뉴(`Menu`)를 화면(`displayed`)에서 감춘다(hide)
  - 메뉴(`Menu`)의 화면(`displayed`)에서 숨긴다.

- 모든 메뉴(`Menu`)를 가져온다(findAll)

### 메뉴 그룹(`MenuGroup`)
- 메뉴 그룹(`MenuGroup`)은 이름(`name`)을 가진다.

- 메뉴 그룹(`MenuGroup`)을 등록(create)한다.
  - 메뉴 그룹(`MenuGroup`)의 이름(`name`)은 비어있으면 안 된다

- 모든 메뉴 그룹(`MenuGroup`)을 가져온다(findAll)

### 주문 테이블(`OrderTable`)
- 주문 테이블(`OrderTable`)은 이름(`name`), 테이블에 앉은 고객 수(`numberOfGuests`), 테이블 사용 여부(`occupied`)를 가진다

- 주문 테이블(`OrderTable`)을 등록(create)한다
  - 주문 테이블(`OrderTable`)은 이름(`name`)은 비어있으면 안 된다

- 고객을 빈 주문 테이블(`OrderTable`)에 앉힌다(sit)
  - 지정된 주문 테이블(`OrderTable`)에 고객이 사용`occupied`)할 수 있게 앉힌다.

- 주문 테이블(`OrderTable`)을 정리(clear)한다
  - 주문 테이블(`OrderTable`)에서 모든 주문(`Order`)이 완료(completed)된 상태(`status`)면 치운다
  - 주문 테이블(`OrderTable`)에 앉은 고객 수(`numberOfGuests`)를 0으로 변경한다
  - 주문 테이블(`OrderTable`)에 고객이 사용(`occupied`)하지 않는 걸로 변경한다

- 주문 테이블(`OrderTable`)에 앉은 고객 수를 변경(changeNumberOfGuests)한다
  - 주문 테이블(`OrderTable`)에 앉은 고객 수(`numberOfGuests`)는 0명 이상이어야 한다
  - 주문 테이블(`OrderTable`)에 다른 고객이 사용(`occupied`)하고 있으면 안 된다

- 모든 주문 테이블(`OrderTable`)을 가져온다(findAll)

### 주문(`Order`)
- 주문(`Order`)은 이름(`name`), 주문 라인(`orderLineItem`), 주문 종류(`type`), 주문 상태(`status`), 배달 주소(`deliveryAddress`)를 가진다
- 주문 종류(`type`)는 배달 주문(DELIVERY), 매장 주문(EAT_IN), 포장 주문(TAKEOUT)을 가진다
- 주문 상태(`status`)는 대기 중(WAITING), 주문 받음(ACCEPTED), 서빙 완료(SERVED), 배달 중(DELIVERING), 배달 완료(DELIVERED), 완료(COMPLETED)를 가진다

- 주문(`Order`)을 등록(create)한다
  - 주문(`Order`)의 종류(`type`)는 배달(DELIVERY), 매장(EAT_IN), 포장(TAKEOUT) 중 하나여야 한다
  - 주문(`Order`)에서 주문 라인(`orderLineItem`)이 1개 이상이어야 한다
  - 화면(`displayed`)에서 보이는 메뉴(`Menu`)만 주문(`Order`)이 가능하다
  - 주문(`Order`)의 종류(`type`)가 배달(DELIVERY)이면 배달 주소(`deliveryAddress`)가 필요하다
  - 주문(`Order`)의 종류(`type`)가 매장 주문(EAT_IN)이면 고객을 1개의 주문 테이블(`OrderTable`)에 지정한다
    - 해당 주문 테이블(`OrderTable`)은 사용(`occupied`)하고 있어야 한다

- 주문(`Order`)을 접수(accept)한다
  - 주문(`Order`)의 상태(`status`)가 대기 중(WAITING)이면 안 된다
  - 주문(`Order`)의 종류(`type`)가 배달(DELIVERY)이면 배달 기사를 지정한다
  - 끝나면 주문(`Order`)의 상태(`status`)를 주문 받음(ACCEPTED)으로 변경한다

- 주문(`Order`)을 서빙(serve)한다
  - 주문(`Order`)의 현재 상태(`status`)가 주문 받음(ACCEPTED)이어야 한다
  - 끝나면 주문(`Order`)의 상태(`status`)를 서빙 완료(SERVED)으로 변경한다

- 배달 주문(DELIVERY)을 시작(startDelivery)한다
  - 주문(`Order`)의 종류(`type`)는 배달(DELIVERY)여야 한다
  - 주문(`Order`)의 현재 상태(`status`)가 서빙 완료(SERVED)이어야 한다
  - 끝나면 주문(`Order`)의 상태(`status`)를 배달 중(DELIVERING)으로 변경한다

- 배달 주문(DELIVERY)을 완료(completeDelivery)한다
  - 주문(`Order`)의 현재 상태(`status`)가 배달 중(DELIVERING)이어야 한다
  - 끝나면 주문(`Order`)의 상태(`status`)를 배달 완료(DELIVERED)로 변경한다

- 주문(`Order`)을 완료(complete)한다
  - 주문(`Order`)의 종류(`type`)가 배달(DELIVERY)인 경우 주문(`Order`)의 상태(`status`)가 배달 완료(DELIVERED)여야 한다
  - 주문(`Order`)의 종류(`type`)가 매장(EAT_IN)이나 포장(TAKEOUT)인 경우 주문(`Order`)의 상태(`status`)가 서빙 완료(SERVED)여야 한다
  - 주문(`Order`)의 상태(`status`)를 완료(COMPLETED)로 변경한다
  - 주문(`Order`)의 종류(`type`)가 매장(EAT_IN)이면 고객이 앉았던 주문 테이블(`OrderTable`)을 치운다.

- 모든 주문(`Order`)을 가져온다(findAll)
