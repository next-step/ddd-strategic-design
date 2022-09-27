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
- 상품의 가격이 변경될 때 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 크면 메뉴가 비공개된다.
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
- 메뉴를 공개할 수 있다.
- 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우 메뉴를 공개할 수 없다.
- 메뉴를 비공개 수 있다.
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
- 비공개된 메뉴는 주문할 수 없다.
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

| 한글명               | 영문명                        | 설명                                                                                                   |
|-------------------|----------------------------|------------------------------------------------------------------------------------------------------|
| 비속어검사기            | Profanity checker          | 주어진 단어에 비속어가 포함되어 있는지를 알려주는 역할을 한다.                                                                  |


### 상품

| 한글명               | 영문명                        | 설명                                                                                                   |
|-------------------|----------------------------|------------------------------------------------------------------------------------------------------|
| 상품                | Product                    | 매장에서 팔기 위한 물품                                                                                        |
| 상품명               | Product name               | 상품명은 비속어가 포함되지 않아야 한다                                                                                |

### 메뉴

| 한글명          | 영문명                   | 설명                                                               |
|--------------|-----------------------|------------------------------------------------------------------|
| 메뉴 그룹        | Menu group            | 메뉴를 묶는 카테고리. 메뉴는 반드시 하나의 메뉴 그룹에 속해 있어야 한다                        |
| 메뉴           | Menu                  | 판매하기 위한 상품과 개수, 가격을 미리 정하여 묶어놓은 주문 단위. 실질적으로 주문은 메뉴를 대상으로 이루어진다. |
| 메뉴 이름        | Menu name             | 메뉴를 추가할 때 정한 메뉴의 이름. 상품의 이름과 별도로 필요하다. 메뉴명은 비속어가 포함되지 않아야 한다.    |
| 메뉴에 속한 상품    | Menu product          | 메뉴를 추가할 때 메뉴에 포함된 상품                                             |
| 메뉴에 속한 상품 수량 | Menu product quantity | 메뉴가 주문될 때 메뉴에 속한 상품을 몇 개 팔지, 메뉴를 등록할 때 정한다.                      |
| 공개된 (메뉴)     | Displayed (menu)      | 메뉴가 공개되어야 주문할 수 있으며, 비공개되면 주문할 수 없다.                             |
| (메뉴를) 비공개하다  | Hide (menu)           | 메뉴를 비공개 상태로 만든다. (반의어: displayed)                                |
| 메뉴 가격을 변경하다  | Change menu price     | 변경될 메뉴 가격이 메뉴에 속한 상품 및 수량의 가격의 합보다 작거나 같아야 한다.                   |

### 주문테이블

| 한글명               | 영문명                        | 설명                                                                                                   |
|-------------------|----------------------------|------------------------------------------------------------------------------------------------------|
| 주문 테이블(= 테이블)     | Order table(a.k.a. Table)  | 매장 내에서 식사를 할 때 차지하는 공간. (이름, 손님 수, 비어 있는 지 여부)                                                       |
| 빈 테이블             | Empty table                | 빈 테이블은 새로운 손님을 받을 준비가 된 상태이다.                                                                        |
| 차지된 테이블           | Occupied table             | 직접 채우거나, 주문을 통해 손님을 받아 테이블이 사용 중인 상태. 차지한 테이블은 새로운 손님을 받을 수 없다.                                      |
| 테이블을 비우다          | Clear table                | 차지된 테이블을 비워 새로운 손님을 받을 준비를 하는 것을 의미한다.                                                               |
| 테이블을 채우다          | Occupy table               | 빈 테이블을 채운다.                                                                                          |
| 손님 수              | Number of guests           | 차지된 테이블에 손님이 몇명이 왔는지를 의미하며, 처음 테이블이 등록될 때는 0이다.                                                      |


### 주문

| 한글명               | 영문명                        | 설명                                                                                                   |
|-------------------|----------------------------|------------------------------------------------------------------------------------------------------|
| 주문                | Order                      | 손님이 사고 싶은 메뉴 목록이 담겨 있다. 손님이 요청하여 상품을 받아 종료될 때까지 일련의 과정을 상태로 관리할 수 있다.                                |
| 주문 유형             | Order type                 | 배달, 포장, 매장 주문이 있다.                                                                                   |
| 주문 상태             | Order status               | 주문이 진행되는 단계.  `대기중`, `접수됨`, `제공됨`, `배달중`, `배달완료`, `주문완료` (배달중, 배달완료는 배달 유형에만 있는 단계)                  |
| 주문 항목             | Order line item            | 한 주문에 선택한 메뉴 및 수량. 주문은 여러 주문 항목을 가질 수 있다.                                                            |
| 배달                | Delivery                   | 주문 유형 중 하나. 배달 과정이 추가로 존재하기에 주문할 때 배달 주소를 필수로 받아야 하며, 배달 관련  단계를 거친다.                                |
| 포장                | Takeout                    | 주문 유형 중 하나. 음식이 준비되면 수령하는 과정만이 존재한다.                                                                 |
| 매장                | Eat-in                     | 주문 유형 중 하나. 매장에서 식사하는 과정이 필요하므로 주문 테이블 하나를 지정해야 한다.                                                  |
| 대기중               | Waiting                    | 주문 상태. 주문을 생성하면 매장에서 접수를 기다리는 상태이다.                                                                  |
| 접수됨               | Accepted                   | 주문 상태. 주문을 접수받아 매장에서 상품을 준비하는 중인 상태이다.                                                               |
| 제공됨               | Served                     | 주문 상태. 상품이 제공된 상태이다.                                                                                 |
| 배달중               | Delivering                 | 배달 유형만의 주문 상태. 준비된 상품이 주문한 손님에게 배달기사가 전달하러 가는 중인 상태이다.                                               |
| 배달완료              | Delivered                  | 배달 유형만의 주문 상태. 준비된 상품이 주문한 손님에게 전달된 상태이다.                                                            |
| 주문완료              | Completed                  | 주문 상태. 정상적으로 주문이 완료된 상태이다.                                                                           |
| 주문을 생성하다          | Create order               | 손님이 사고 싶은 메뉴와 수량을 받는다. 배달 유형은 배달 주소를 받아야 한다. 매장 유형은 빈 테이블을 하나 선택하여 채운다.                              |
| 접수하다              | Accept                     | 손님의 '주문'을 수락하여 상품 준비를 시작한다. 배달 유형은 배달기사를 요청한다.                                                       |
| 제공하다              | Serve                      | '주문'에 담긴 상품을 제공한다. 배달 유형에서는 배달 기사에게 상품이 전달되며, 그 외 유형에서는 손님에게 상품이 전달된다.                               |
| 배달을 시작하다          | Start delivery             | 배달기사가 매장에서 제공받은 상품을 손님에게 전달하기 위해 출발한다는 의미이다. 배달 유형에서만 가능하다.                                          |
| 배달을 완료하다          | Complete delivery          | 배달기사가 손님에게 상품을 전달했다는 의미이다. 배달 유형에서만 가능하다.                                                            |
| 주문을 완료하다          | Complete order             | 손님에게 상품이 제공된 후 '주문'이 정상적으로 끝났다고 표시한다. 매장 유형은, 주문 생성 시 정한 주문테이블에 다른 주문이 없다면 테이블을 비우고, 손님 수를 0으로 변경한다. |
| 배달기사              | Rider                      | 배달 주문의 상품을 손님에게 전달하는 역할을 하는 사람들이다.                                                                   |
| 배달대행사             | Rider agency               | 배달기사를 매장에 배정해주는 역할을 한다.                                                                              |
| 배달기사를 요청하다        | Request rider              | 배달대행사에게 배달기사를 매장에 배정해주기를 요청하는 행위.                                                                    |                                             |

## 모델링

### 상품(`Product`)
- 상품은 이름(`name`)을 가진다.
  - 비속어검사기(`ProfanityChecker`)를 통과한 이름만을 가질 수 있다.
  - 이름은 비울 수 없다.
- 상품은 가격(`price`)을 가진다.
  - 가격은 0 이상이어야 한다.

- 상품을 등록할 수 있다.
  - 이름과 가격을 받는다.
- 상품의 가격을 변경할 수 있다.
  - 가격은 0 이상이어야 한다.
- 상품의 목록을 조회할 수 있다.

### 메뉴 그룹(`MenuGroup`)
- 메뉴 그룹은 이름(`name`)을 가진다.
  - 이름은 비울 수 없다.

- 메뉴 그룹을 등록할 수 있다.
  - 이름을 받는다.
- 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴(`Menu`)
- 메뉴는 메뉴에 속한 상품(`MenuProduct`) 목록을 가진다.
  - 메뉴는 메뉴에 속한 상품을 하나 이상 가지고 있어야 한다.
  - 메뉴에 속한 상품은 상품(`Product`)을 가진다.
  - 메뉴에 속한 상품은 수량(`quantity`)을 가진다
    - 수량은 0 이상이어야 한다.
- 메뉴는 가격(`price`)을 가진다.
  - 가격은 0 이상이어야 한다.
  - 메뉴에 속한 상품의 (상품 가격 * 수량)의 총합보다 크거나 같아야 한다.
- 메뉴는 이름(`name`)을 가진다.
  - 비속어검사기(`ProfanityChecker`)를 통과한 이름만을 가져야 한다.
  - 이름은 비울 수 없다.
- 메뉴는 해당 메뉴가 속한 메뉴 그룹(`MenuGroup`)을 가진다.
  - 메뉴는 특정 메뉴 그룹에 속해야 한다.
  - 여러 메뉴 그룹에 동시에 속할 수 없다.
- 메뉴는 공개 여부(`displayed`)를 정할 수 있다.

- 1개 이상의 상품으로 메뉴를 등록할 수 있다.
  - 메뉴에 속한 상품 목록, 가격, 이름, 속할 메뉴 그룹, 공개 여부를 받는다.
- 메뉴의 가격을 변경할 수 있다.
- 메뉴는 공개할 수 있다.
  - 메뉴에 속한 상품의 (상품 가격 * 수량)의 총합보다 크거나 같지 않으면 공개할 수 없다.
- 메뉴는 비공개할 수 있다.
- 메뉴의 목록을 조회할 수 있다.

### 주문테이블(`OrderTable`)
- 주문테이블을 등록할 수 있다.
  - 주문테이블은 이름을 가진다.
    - 이름은 비울 수 없다.
  - 주문테이블은 차지 여부(`occupied`)를 가진다.
  - 주문테이블은 손님 수(`numberOfGuests`)를 가진다.
- 주문테이블은 비울 수 있다.
  - 주문테이블에 완료되지 않은 주문(`Order`)이 하나라도 있다면 비울 수 없다.
  - 주문테이블을 비우면 손님 수가 0이 된다.
- 주문테이블은 채울 수 있다.
- 주문테이블의 손님 수를 변경할 수 있다.
  - 손님 수는 0 이상이어야 한다.
  - 차지되지 않은 주문테이블은 손님 수를 변경할 수 없다.
- 주문테이블의 목록을 조회할 수 있다.


### 주문(`Order`)

#### 배달 주문(`DeliveryOrder`)
- 1개 이상의 메뉴(`Menu`)로 배달 주문을 생성할 수 있다.
  - 하나 이상의 주문 항목(`OrderLineItem`) 이 있어야 한다.
    - 주문 항목은 선택된 메뉴 하나를 가진다.
      - 비공개된 메뉴가 아니어야 한다.
    - 주문 항목은 수량을 가진다.
      - 수량은 0 이상이어야 한다.
  - 배달주소(`deliveryAddress`)를 가진다.
    - 배달주소는 비어 있지 않아야 한다.
  - 주문일시(`orderDateTime`)를 가진다.
  - 생성된 배달 주문의 주문상태(`status`)는 대기중(`WAITING`)이다.
- 대기중인 배달 주문을 접수할 수 있다.
  - 대기중(`WAITING`) 상태여야 한다.
  - 배달대행사(`RiderAgency`)에 배달기사를 요청한다.
  - 주문상태는 접수됨(`ACCEPTED`)으로 바뀐다.
- 접수된 배달 주문을 제공할수 있다.
  - 접수됨(`ACCEPTED`) 상태여야 한다.
  - 주문상태는 제공됨(`SERVED`)으로 바뀐다.
- 제공된 배달 주문을 배달 시작할 수 있다.
  - 제공된(`SERVED`) 상태여야 한다.
  - 주문상태는 배달중(`DELIVERING`)으로 바뀐다.
- 배달중인 배달 주문을 배달 완료할 수 있다.
  - 배달중(`DELIVERING`)인 상태여야 한다.
  - 주문상태는 배달완료(`DELIVERED`)로 바뀐다.
- 배달완료된 배달 주문은 주문 완료할 수 있다.
  - 배달완료(`DELIVERED`)인 상태여야 한다.
  - 주문상태는 주문완료(`COMPLETED`)로 바뀐다.

#### 포장 주문(`TakeoutOrder`)
- 1개 이상의 메뉴(`Menu`)로 포장 주문을 생성할 수 있다.
  - 하나 이상의 주문 항목(`OrderLineItem`) 이 있어야 한다.
    - 주문 항목은 선택된 메뉴 하나를 가진다.
      - 비공개된 메뉴가 아니어야 한다.
    - 주문 항목은 수량을 가진다.
      - 수량은 0 이상이어야 한다.
  - 주문일시(`orderDateTime`)를 가진다.
  - 생성된 포장 주문의 주문상태(`status`)는 대기중(`WAITING`)이다.
- 대기중인 포장 주문을 접수할 수 있다.
  - 대기중(`WAITING`) 상태여야 한다.
  - 주문상태는 접수됨(`ACCEPTED`)으로 바뀐다.
- 접수된 포장 주문을 제공할수 있다.
  - 접수됨(`ACCEPTED`) 상태여야 한다.
  - 주문상태는 제공됨(`SERVED`)으로 바뀐다.
- 제공된 포장 주문은 주문 완료할 수 있다.
  - 제공된(`DELIVERED`) 상태여야 한다.
  - 주문상태는 주문완료(`COMPLETED`)로 바뀐다.

#### 매장 주문(`EatInOrder`)
- 1개 이상의 메뉴(`Menu`)로 매장 주문을 생성할 수 있다.
  - 하나 이상의 주문 항목(`OrderLineItem`) 이 있어야 한다.
    - 주문 항목은 선택된 메뉴(`Menu`) 하나를 가진다.
      - 비공개된 메뉴가 아니어야 한다.
    - 주문 항목은 수량을 가진다.
      - 수량은 음수도 가능하다.
  - 매장테이블(`OrderTable`) 하나를 선택해야 한다.
    - 차지된(`occupied`) 상태여야 한다.
  - 주문일시(`orderDateTime`)를 가진다.
  - 생성된 매장 주문의 주문상태(`status`)는 대기중(`WAITING`)이다.
- 대기중인 매장 주문을 접수할 수 있다.
  - 대기중(`WAITING`) 상태여야 한다.
  - 주문상태는 접수됨(`ACCEPTED`)으로 바뀐다.
- 접수된 매장 주문을 제공할수 있다.
  - 접수됨(`ACCEPTED`) 상태여야 한다.
  - 주문상태는 제공됨(`SERVED`)으로 바뀐다.
- 제공된 매장 주문은 주문 완료할 수 있다.
  - 제공된(`DELIVERED`) 상태여야 한다.
  - 주문상태는 주문완료(`COMPLETED`)로 바뀐다.
  - 생성 당시 선택한 매장테이블을 비운다.
  - 생성 당시 선택한 매장테이블의 손님 수를 0으로 설정한다.
