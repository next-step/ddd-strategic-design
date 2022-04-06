# 키친포스

## 요구 사항

### 상품

- 가게 점주는 상품을 등록할 수 있다.
    - 상품의 가격이 올바르지 않으면 등록할 수 없다.
        - 상품의 가격은 0원 이상이어야 한다.
    - 상품의 이름이 올바르지 않으면 등록할 수 없다.
        - 상품의 이름에는 비속어가 포함될 수 없다.
- 가게 점주는 상품의 가격을 변경할 수 있다.
    - 상품의 가격이 올바르지 않으면 변경할 수 없다.
        - 상품의 가격은 0원 이상이어야 한다.
    - 상품의 가격이 변경될 때 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 크면 메뉴가 숨겨진다.
- 가게 점주와 손님은 상품의 목록을 조회할 수 있다.

### 메뉴 그룹

- 가게 점주는 메뉴 그룹을 등록할 수 있다.
    - 메뉴 그룹의 이름이 올바르지 않으면 등록할 수 없다.
        - 메뉴 그룹의 이름은 비워 둘 수 없다.
- 가게 점주와 손님은 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴

- 가게 점주는 1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.
    - 상품이 없으면 등록할 수 없다.
    - 메뉴에 속한 상품의 수량은 0 이상이어야 한다.
    - 메뉴의 가격이 올바르지 않으면 등록할 수 없다.
        - 메뉴의 가격은 0원 이상이어야 한다.
    - 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
    - 메뉴는 특정 메뉴 그룹에 속해야 한다.
    - 메뉴의 이름이 올바르지 않으면 등록할 수 없다.
        - 메뉴의 이름에는 비속어가 포함될 수 없다.
- 가게 점주는 메뉴의 가격을 변경할 수 있다.
    - 메뉴의 가격이 올바르지 않으면 변경할 수 없다.
        - 메뉴의 가격은 0원 이상이어야 한다.
    - 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
- 가게 점주는 손님이 주문할 수 있도록 메뉴를 노출할 수 있다.
    - 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우 메뉴를 노출할 수 없다.
- 가게 점주는 손님이 주문할 수 없도록 메뉴를 숨길 수 있다.
    - 메뉴의 목록을 조회할 수 있다.

### 주문 테이블

- 가게 점주는 주문 테이블을 등록할 수 있다.
    - 주문 테이블의 이름이 올바르지 않으면 등록할 수 없다.
        - 주문 테이블의 이름은 비워 둘 수 없다.
- 가게 점주는 손님의 예약을 받아 주문 테이블을 예약할 수 있습니다.
    - 빈 테이블인지 확인하지 않고, 예약을 받을 수 있다.
- 손님이 예약한 식사를 마치면, 가게 점주는 주문 테이블을 비어있도록 설정할 수 있다.
    - 예약한 식사를 마치지 않은 주문 테이블은 빈 테이블로 설정할 수 없다.
- 가게 점주는 주문 변동에 따라 방문한 손님 수를 변경할 수 있다.
    - 매장 식사를 예약한 손님의 수가 올바르지 않으면 변경할 수 없다.
        - 매장 식사를 예약한 손님의 수는 0 이상이어야 한다.
    - 빈 테이블은 매장 식사를 예약한 손님의 수를 변경할 수 없다.
- 가게 점주와 손님은 주문 테이블의 목록을 조회할 수 있다.

### 주문

- 손님은 가게에 주문할 수 있다.
    - 손님은 주문할 때, 배달, 포장, 매장 중 하나를 선택해야 한다.
        - 1개 이상의 등록된 메뉴로 주문을 등록할 수 있다.
    - 주문 유형이 올바르지 않으면 등록할 수 없다.
    - 메뉴가 없으면 등록할 수 없다.
    - 매장 주문은 주문 항목의 수량이 없을 수 있다.
    - 매장 주문을 제외한 주문의 경우 주문 항목의 수량은 0 이상이어야 한다.
    - 배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.
        - 배달 주소는 비워 둘 수 없다.
    - 테이블이 예약되지 않은 경우에는 매장 주문을 등록할 수 없다.
    - 숨겨진 메뉴는 주문할 수 없다.
    - 주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.
- 가게 점주는 접수 대기 중인 주문을 접수한다.
    - 접수 대기 중인 주문만 접수할 수 있다.
    - 배달 주문을 접수되면 배달 대행사를 호출한다.
- 가게 점주는 접수된 주문을 서빙한다.
    - 접수된 주문만 서빙할 수 있다.
- 가게 점주는 주문을 배달한다.
    - 배달 주문만 배달할 수 있다.
    - 서빙된 주문만 배달할 수 있다.
- 가게 점주는 손님이 음식을 받으면 배달 완료로 변경한다.
    - 배달 중인 주문만 배달 완료할 수 있다.
- 가게 점주는 손님이 식사를 마치거나 음식을 받으면 완료된 주문로 변경한다.
    - 배달 주문의 경우 배달 완료된 주문만 완료할 수 있다.
    - 포장 및 매장 주문의 경우 서빙된 주문만 완료할 수 있다.
    - 주문 테이블의 모든 매장 주문이 완료되면 빈 테이블로 설정한다.
    - 완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.
- 가게 점주와 손님은 주문 목록을 조회할 수 있다.

## 용어 사전

### 상품

| 한글명             | 영문명                     | 설명                            |
|-----------------|-------------------------|-------------------------------|
| 매니저             | Manager                 | 가게를 운영하는 매니저                  |
| 손님              | Guest                   | 가게를 이용하려는 손님                  |
| 상품              | Product                 | 상품은 이름과  가격을 포함하고, 메뉴에 포함된다.  |
| 상품 고유 번호        | ProductId               | 상품을 식별할 수 있는 고유 번호            |
| 상품 이름           | ProductName             | 상품의 이름을 말하고, 비속어를 포함할 수 없다.   |
| 상품 가격           | ProductPrice            | 상품의 가격을 말하고, 0원 이상의 가격만 들어간다. |
| 상품 목록           | ProductList             | 전체 상품 리스트의 정보                 |
| 상품 등록           | RegisterProduct         | 상품을 등록할 수 있다.                 |
| 비속어 확인 정책       | ProfanityPolicy         | 비속어를 확인 외부 시스템                |
| 비속어             | Profanity               | 욕설                            |
| 상품 가격 변경        | ChangeProductPrice      | 상품 가격을 변경한다.                  |
| 메뉴에 속한 상품 금액의 합 | TotalProductPriceInMenu | 메뉴에 속하는 전체 상품 금액을 함한 값이다.     |
| 메뉴              | Menu                    | 상품이 들어가는 메뉴                   |

### 메뉴 그룹

| 한글명         | 영문명                 | 설명                       |
|-------------|---------------------|--------------------------|
| 매니저         | Manager             | 가게를 운영하는 매니저             |
| 손님          | Guest               | 가게를 이용하려는 손님             |
| 메뉴 그룹       | MenuGroup           | 메뉴 그룹은 메뉴를 포함하고 이름을 가진다. |
| 메뉴 그룹 고유 번호 | MenuGroupId         | 메뉴 그룹을 식별할 수 있는 고유 번호    |
| 메뉴 그룹 이름    | MenuGroupName       | 메루 그룹의 이름                |
| 메뉴 그룹 등록    | Register menu group | 메뉴 그룹을 등록한다.             |
| 메뉴 그룹 목록    | Menu group list     | 전체 메뉴 그룹 리스트의 정보         |

### 메뉴

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 매니저 | Manager | 가게를 운영하는 매니저 |
| 손님 | Guest | 가게를 이용하려는 손님 |
| 메뉴 | Menu | 메뉴는 이름과 가격, 메뉴 그룹, 숨김 처리 여부, 메뉴에 등록된 상품의 정보를 가진다. |
| 메뉴 고유 번호 | MenuId | 메뉴를 식별할 수 있는 고유 번호 |
| 메뉴 이름 | MenuName | 비속어를 포함할 수 없는 메뉴의 이름 |
| 메뉴 그룹 | MenuGroup | 하나의 메뉴를 포함하는 메뉴의 한 묶음 |
| 메뉴 그룹 고유번호 | MenuGroupId | 메뉴 그룹을 식별할 수 있는 고유 번호 |
| 메뉴 품목 | MenuProduct | 메뉴 안에 들어간 상품 정보와 상품 개수 정보 |
| 상품 | Product | 메뉴 품목 안에 존재하는 상품의 정보 |
| 상품 수량 | Quantity | 메뉴 품목 안에 존재하는 상품의 수량 |
| 숨김 처리 | Displayed | 메뉴가 숨김 처리됐는지 정보. 손님은 숨겨지지 않은 메뉴만 주문할 수 있다. |
| 메뉴 가격 | MenuPrice | 메뉴에 등록된 0원 이상인 가격이다. 메뉴 가격은 메뉴에 속하는 전체 상품 금액을 합한 값보다 작거나 같아야 한다. |
| 메뉴 품목 금액의 합 | TotalProductPriceInMenu | 현재 메뉴에 속하는 전체 상품 금액을 합한 값이다. 메뉴에 속한 상품 금액의 합은 메뉴 가격 보다 크거나 같아야 한다. |
| 비속어 확인 정책 | ProfanityPolicy | 비속어가 존재하는지 확인하는 정책 |
| 비속어 | Profanity | 욕설 |
| 메뉴 등록 | RegisterMenu | 메뉴를 등록한다. |
| 메뉴 가격 변경 | ChangeMenuPrice | 메뉴에 속하는 메뉴 가격을 변경한다, |
| 노출 메뉴 | DisplayedMenu | 손님이 주문할 수 있도록 메뉴를 화면에 노출한다. |
| 숨김 메뉴 | HiddenMenu | 손님이 주문할 수 없도록 메뉴를 화면에서 숨긴다. |

### 주문 테이블

| 한글명           | 영문명                  | 설명                                                                 |
|---------------|----------------------|--------------------------------------------------------------------|
| 매니저           | Manager              | 가게를 운영하는 매니저                                                       |
| 손님            | Guest                | 가게를 이용하려는 손님                                                       |
| 주문 테이블        | OrderTable           | 주문 테이블은 주문 테이블의 이름과 매장 식사를 예약한 손님의 수의 정보, 그리고 테이블이 비어있는지의 정보를 가진다. |
| 테이블 고유 번호     | OrderTableId         | 테이블을 식별할 수 있는 고유 번호                                                |
| 테이블 이름        | OrderTableName       | 테이블의 이름                                                            |
| 손님의 수         | NumberOfGuests       | 매장 식사를 하는 손님의 수이다. 손님의 수는 0명 이상이어야 한다.                             |
| 비어있는지의 정보     | isEmpty              | 주문 테이블이 비어있는지의 정보                                                  |
| 비어있지 않은 테이블   | NonEmptyTable        | 비어있지 않은 주문 테이블                                                     |
| 빈 테이블         | EmptyTable           | 비어있는 주문 테이블                                                        |
| 테이블 예약        | SitTable             | 손님이 매장 식사 주문을 하면 테이블을 비어 있지않도록 설정한다.                               |
| 식사 완료         | ClearTable           | 손님이 식사를 완료하면 해당 테이블을 비어있도록 설정한다.                                   |
| 손님 수 변경       | ChangeNumberOfGuests | 매장 식사 주문을 한 손님의 수를 변경한다.                                           |
| 식사를 마치지 않은 주문 | IncompleteOrderTable | 식사를 마치지 않은 주문 테이블                                                  |
| 식사를 마친 주문     | CompletedOrderTable  | 식사를 마친 주문 테이블                                                      |
| 주문 테이블 등록     | RegisterOrderTable   | 주문 테이블에 등록한다.                                                      |

### 주문

#### 배달 주문

| 한글명           | 영문명                   | 설명                                 |
|---------------|-----------------------|------------------------------------|
| 주문            | Order                 | 주문한 음식                             |
| 배달 주문         | DeliveryOrder         | 배달해야하는 주문                          |
| 배달 주문 고유 번호   | DeliveryOrderId       | 배달하는 주문을 식별하는 고유 번호                |
| 주소            | Address               | 배달 주문이 배달될 위치                      |
| 주문한 시각        | OrderDateTime         | 배달 주문을 요청한 시간                      |
| 주문 상품 품목      | OrderLineItem         | 주문하는 수량과 메뉴를 지정하면 주문 상품 품목으로 등록된다. |
| 메뉴            | Menu                  | 주문 상품 품목에 등록된 메뉴                   |
| 숨겨져 있지 않은 메뉴  | DisplayedMenu         | 메뉴에서 노출 메뉴를 의미                     |
| 주문 상품 품목 수량   | OrderLineItemQuantity | 주문 상품 품목의 수량                       |
| 주문 상품 품목 가격   | OrderLineItemPrice    | 주문 상품 품목의 가격                       |
| 메뉴 가격         | MenuPrice             | 메뉴의 가격                             |
| 주문하려는 메뉴의 가격  | OrderMenuPrice        | 주문 상품 품목에 등록된 메뉴의 가격               |
| 주문한 전체 가격     | TotalDelOrderPrice    | 배달 주문에 등록된 주문 상품 품목의 전체 가격 함       |
| 주문 상태         | OrderStatus           | 배달 주문의 상태                          |
| 주문 대기 상태      | WAITING               | 배달 주문이 요청되어 대기중인 상태                |
| 주문 접수가 된 상태   | ACCEPTED              | 배달 주문이 접수된 상태                      |
| 주문 준비가 완료된 상태 | SERVED                | 주문이 완료된 상태                         |
| 배달 중인 상테      | DELIVERYING           | 배달을 시작한 상태                         |
| 전달된 상태        | DELIVERED             | 라이더가 손님에게 배달한 상태                   |
| 완료된 상태        | COMPLETE              | 손님이 배달을 받고 확인 완료한 상태               |
| 배달 대행사        | kitchenridersClient   | 요청 받은 배달을 해주는 대행사                  |
| 라이더           | rider                 | 요청 받은 배달을 하는 배달 기사                 |
| 주문을 생성한다.     | createOrder           | 주문을 요청한다.                          |
| 주문을 받는다.      | acceptOrder           | 요청이 온 주문을 접수한다.                    |
| 주문을 준비한다.     | serveOrder            | 접수한 주문을 준비한다.                      |
| 배달한다.         | startDelivery         | 준비한 주문을 배달한다.                      |
| 배달을 전달한다.     | completeDelivery      | 배달하는 주문을 전달한다.                     |
| 주문을 완료한다.     | completeOrder         | 전달한 주문을 완료한다.                      |

#### 포장 주문

| 한글명          | 영문명                   | 설명                                 |
|--------------|-----------------------|------------------------------------|
| 주문           | Order                 | 주문한 음식                             |
| 포장 주문        | TakeOutOrder          | 포장해야 하는 주문                         |
| 포장 주문 고유 번호  | TakeOutOrderId        | 포장하는 주문을 식별하는 고유 번호                |
| 주문 상품 품목     | OrderLineItem         | 주문하는 수량과 메뉴를 지정하면 주문 상품 품목으로 등록된다. |
| 메뉴           | Menu                  | 주문 상품 품목에 등록된 메뉴                   |
| 숨겨져 있지 않은 메뉴 | DisplayedMenu         | 메뉴에서 노출 메뉴를 의미                     |
| 주문 상품 품목 수량  | OrderLineItemQuantity | 주문 상품 품목의 수량                       |
| 주문 상품 품목 가격  | OrderLineItemPrice    | 주문 상품 품목의 가격                       |
| 주문 상태        | OrderStatus           | 포장 주문의 상태                          |
| 대기 상태        | WAINTING              | 포장 주문이 요청되어 대기중인 상태                |
| 접수된 상태       | ACCEPTED              | 포장 주문이 접수된 상태                      |
| 준비된 상태       | SERVED                | 주문이 준비 완료된 상태                      |
| 전달 완료된 상태    | COMPLETE              | 주문이 전달된 상태                         |
| 주문한 시각       | OrderDateTime         | 포장 주문을 주문한 시각                      |
| 주문을 생성한다.    | createOrder           | 주문을 요청한다.                          |
| 주문을 접수한다.    | acceptOrder           | 요청이 온 주문을 접수한다.                    |
| 주문을 준비한다.    | serveOrder            | 접수한 주문을 준비한다.                      |
| 주문을 전달한다.    | completeOrder         | 주문을 전달해 주문을 완료한다.                  |

#### 매장 식사 주문

| 한글명            | 영문명            | 설명                       |
|----------------|----------------|--------------------------|
| 주문             | Order          | 주문한 음식                   |
| 매장 식사 주문       | EatInOrder     | 매장에서 식사하는 주문             |
| 매장 식사 주문 고유 번호 | EatInOrderId   | 매장에서 식사하는 주문을 식별하는 고유 번호 |
| 주문 상태          | OrderStatus    | 매장 식사 주문의 상태             |
| 주문 대기 상태       | WAINTING       | 매장 식사 주문이 요청되어 대기중인 상태   |
| 주문 접수된 상태      | ACCEPTED       | 매장 식사 주문이 접수된 상태         |
| 주문 전달된 상태      | SERVED         | 주문이 완료되어 전달된 상태          |
| 식사 완료된 상태      | COMPLETE       | 매장 식사가 완료된 상태            |
| 주문한 시각         | OrderDateTime  | 매장 식사을 주문한 시각            |
| 테이블            | OrderTable     | 테이블의 정보                  |
| 테이블 고유 번호      | OrderTableId   | 테이블을 식별할 수 있는 고유 번호      |
| 주문을 생성한다.      | createOrder    | 주문을 요청한다.                |
| 주문을 접수한다.      | acceptOrder    | 요청이 온 주문을 접수한다.          |
| 주문을 전달하다.      | serveOrder     | 접수한 주문을 전달한다.            |
| 식사를 마치다.       | completeOrder  | 매장 식사를 마친다.              |
| 손님의 수          | NumberOfGuests | 테이블에 저장된 손님의 수           |

## 모델링
### 상품

속성

- 상품(`Product`)은 상품 고유 번호(`ProductId`)를 가진다.
- 상품(`Product`)은 비속어(`Profanity`)가 포함되지 않는 이름(`PriceName`)을 가진다.
- 상품(`Product`)은 0원 이상인 상품 가격(`ProductPrice`)을 가진다.

행위

- 상품 가격(`ProductPrice`), 상품 이름(`PriceName`)을 전달하면 상품(`Product`)을 생성할 수 있다.
  - 이름(`name`)은 비속어 확인 정책(`ProfanityPolicy`)을 통해 비속어(`Profanity`)가 포함됐는지 확인한다.
- 상품 고유번호(`ProductId`)와 상품 가격(`ProductPrice`)을 전달하면 상품(`Product`)의 가격(`price`)을 변경할 수 있다.
  - 해당 상품(`Product`)이 들어가는 메뉴 가격(`MenuPrice`)이 메뉴에 속한 메뉴 품목 금액 합(`TotalProductPriceInMenu`)보다 작으면, 숨김 메뉴(`HideMenu`)가 된다.

### 메뉴 그룹

속성

- 메뉴그룹(`MenuGroup`)은 고유한 메뉴 그룹 고유 번호 (`MenuGroupId`)를 가진다.
- 메뉴그룹은 비어있지 않은 이름(`MenuGroupName`)을 가진다.

행위

- 메뉴그룹 이름(`MenuGroupName`)을 전달하면 메뉴그룹(`MenuGroup`)을 생성할 수 있다.

### 메뉴

속성

- 메뉴(`Menu`)는 메뉴 고유 번호(`MenuId`)를 가진다.
- 메뉴(`Menu`)는 비속어(`Profanity`)가 포함되지 않는 이름(`MenuName`)을 가진다.
- 메뉴(`Menu`)는 메뉴 가격(`MenuPrice`)을 가진다.
- 메뉴(`Menu`)는 1개 이상의 메뉴그룹(`MenuGroup`)에 속해있다.
- 메뉴(`Menu`)는 노출 상태(`displayed`) 정보를 가지며 노출 매뉴(`DisplayMenu`), 숨김 메뉴(`HideMenu`) 중 하나를 가진다.
- 메뉴(`Menu`)는 하나 이상의 메뉴 품목(`MenuProduct`) 정보를 가진다.
  - 메뉴 품목(`MenuProduct`)은 상품(`Product`)과 상품 수량(`Quantity`)을 가진다.

행위

- 메뉴 가격(`MenuPrice`)과 메뉴 그룹 고유번호(`MenuGroupId`), 메뉴 이름(`MenuName`), 메뉴 숨김 처리 여부(`MenuDisplayed`), 그리고 메뉴 품목(`MenuProducts`)을 전달하면 메뉴(`Menu`)를 생성할 수 있다.
  - 이름(`MenuName`)은 비속어 확인 정책(`ProfanityPolicy`)를 통해 비속어(`Profanity`)가 존재하는지 확인한다.
  - 하나 이상의 메뉴 품목(`MenuProducts`)을 가져야 한다.
  - 하난 이상의 메뉴그룹(`MenuGroup`)에 속해야 한다.
  - 메뉴 품목 금액의 합(`TotalProductPriceInMenu`)보다 작은 메뉴 가격(`MenuPrice`)을 가져야 한다.
- 메뉴 고유 번호(`MenuId`)와 메뉴 가격(`MenuPrice`)을 전달하면 메뉴 가격(`MenuPrice`)을 변경할 수 있다.
  - 메뉴 품목 금액의 합(`TotalProductPriceInMenu`)보다 적은 메뉴 가격(`MenuPrice`)을 가지면 숨겨지는 메뉴(`HideMenu`)가 된다.
- 메뉴 고유 번호(`MenuId`)를 전달하면 노출 메뉴(`DisplayedMenu`)로 변경한다.
  - 메뉴 품목 금액의 합(`TotalProductPriceInMenu`)보다 적은 메뉴 가격(`MenuPrice`)을 가진 메뉴(`Menu`)여야 한다.
- 메뉴 고유 번호(`MenuId`)를 전달하면 숨김 메뉴(`HiddenMenu`)로 변경한다.

### 주문 테이블

속성

- 주문 테이블(`OrderTable`)은 주문 고유 번호(`OrderTableId`)를 가진다.
- 주문 테이블(`OrderTable`)은 테이블 이름(`OrderTableName`)을 가진다.
- 주문 테이블(`OrderTable`)이 비어있는 상태(`empty`) 정보를 가지고 있고, 빈 테이블(`EmptyTable`)이거나 비어있지 않은 테이블(`NonEmptyTable`) 둘 중 하나이다.
- 예약된 주문 테이블(`NonEmptyTable`)에는 0명 이상의 손님 수(`numberOfGuests`)를 가지고 있다.

행위

- 주문 이름(`OrderName`)을 전달하면 주문 테이블(`OrderTable`)을 생성한다.
  - 생성한 주문 테이블(`OrderTable`)은 손님 수(`numberOfGuests`)가 없는 빈 테이블(`EmptyTable`)이어야 한다.
- 주문 테이블 고유번호(`OrderTableId`)를 전달하면 빈 테이블(`EmptyTable`)을 비어있지 않은 테이블(`NonEmptyTable`) 로 변경한다.
- 주문 테이블 고유번호(`OrderTableId`)를 전달하면 비어있지 않은 테이블(`NonEmptyTable`)을 빈 테이블(`EmptyTable`)로 변경한다.
  - 식사를 마친 주문(`CompletedOrderTable`)이어야 한다.
  - 비어있지 않은 테이블(`NonEmptyTable`)을 비우면 빈 테이블(`EmptyTable`)로 변경하고, 손님 수(`numberOfGuests`)를 없앤다.
- 주문 테이블 고유번호(`OrderTableId`)와 변경할 손님 수(`numberOfGuests`)를 전달하면 비어있지 않은 테이블(`NonEmptyTable`)의 손님 수를 변경한다.
  - 변경하려는 손님 수가 0명 이상이고 비어있지 않은 테이블(`NonEmptyTable`)이어야 한다.

### 주문

### 배달 주문(DeliveryOrder)

속성

- 배달 주문(`DeliveryOrder`)은 배달 주문 고유 번호(`DeliveryOrderId`)를 가진다.
- 배달 주문(`DeliveryOrder`)은 주소(`Address`)를 가진다.
- 배달 주문(`DeliveryOrder`)은 주문 상태(`OrderStatus`)를 가지며, 주문 대기(`WAITING`), 주문 접수(`ACCEPTED`), 주문 준비(`SERVED`), 배달 시작(`DELIVERY`), 배달 전달(`DELIVERED`), 배달 완료(`DELIVERYCOMPLETE`) 중 하나이다.
- 배달 주문(`DeliveryOrder`)은 주문한 시각(`OrderDateTime`)을 가진다.
- 배달 주문(`DeliveryOrder`)은 한 개 이상의 주문 상품 품목(`OrderLineItem`)의 정보를 가진다.
  - 주문 상품 품목(`OrderLineItem`)은 메뉴(`Menu`)와 수량(`OrderLineItemQuantity`) 그리고 가격(`OrderLineItemPrice`)을 가진다.

행위

- 주소와 한 개 이상의 주문 상품 품목(`OrderLineItems`)을 전달하면 배달 주문(`DeliveryOrder`)을 생성한다.(`createOrder`)
  - 주문 상품 품목의 수량(`OrderLineItemQuantity`)이 0 이상이어야 한다.
  - 숨겨져 있지 않은 메뉴여야(`DisplayedMenu`)하고, 메뉴 가격(`MenuPrice`)과 주문하려는 메뉴의 가격(`OrderLineItemPrice`)이 같아야 한다.
  - 요청된 배달 주문(`DeliveryOrder`)은 대기 상태(`WAITING`)가 된다.
- 배달 주문 고유 번호(`DeliveryOrderId`)를 전달하면 배달 주문(`DeliveryOrder`)을 받는다.(`acceptOrder`)
  - 주문 고유번호(`OrderId`), 주문한 전체 가격(`TotalDeliveryOrderPrice`), 주소(`deliveryAddress`)를 전달해 배달 대행사(`kitchenridersClient`)에게 배달을 요청한다.
- 배달 주문 고유번호(`DeliveryOrderId`)를 전달하면 접수(`ACCEPTED`)한 배달 주문(`DeliveryOrder`)을 준비한다.(`serveOrder`)
  - 접주한(`ACCEPTED`) 배달 주문(`DeliveryOrder`)이 준비(`SERVED`)된다.
- 배달 주문 고유 번호(`DeliveryOrderId`)를 전달하면 준비가 된(`SERVED`) 배달 주문(`DeliveryOrder`)을 배달 시작한다.(`startDelivery`)
  - 준비한(`SERVED`) 배달 주문(`DeliveryOrder`)이 배달 중인 상태(`DELIVERYING`)가 된다.
- 배달 주문 고유 번호(`DeliveryOrderId`)를 전달하면 배달 중(`DELIVERYING`)인 배달 주문(`DeliveryOrder`)을 전달한다.(`completeDelivery`)
  - 배달 중인(`DELIVERYING`) 배달 주문(`DeliveryOrder`)이 전달(`DELIVERED`)된다.
- 배달 주문 고유 번호(`DeliveryOrderId`)를 전달하면 배달 주문(`DeliveryOrder`)을 완료한다.(`completeOrder`)
  - 배달 완료(`DELIVERED`)된 배달 주문(`DeliveryOrder`)이 배달 완료(`COMPLETE`)된다.

### 포장 주문

속성

- 포장 주문(`TakeOutOrder`)은 주문 고유 번호(`OrderId`)를 가진다.
- 포장주문(`TakeOutOrder`)은 한 개 이상의 주문 상품 품목(`OrderLineItem`)의 정보를 가진다.
  - 주문 상품 품목(`OrderLineItem`)은 메뉴(`Menu`)와 수량(`OrderLineItemQuantity`) 그리고 가격(`OrderLineItemPrice`)을 가진다.
- 포장 주문(`TakeOutOrder`)은 주문 상태(`OrderStatus`)를 가진다.
  - 포장 주문은 대기(`WAINTING`) 주문, 접수된(`ACCEPTED`) 주문, 준비된(`SERVED`) 주문, 전달 완료 (`COMPLETE`) 주문 중 하나이다.
- 포장 주문(`TakeOutOrder`)은 주문한 시각(`OrderDateTime`)을 가진다.

행위

- 한 개 이상의 주문 상품 품목(`OrderLineItems`)을 전달하면 포장 주문(`TakeOutOrder`)을 생성한다.(`createOrder`)
  - 숨겨져 있지 않은 메뉴여야(`DisplayedMenu`)하고, 메뉴 가격(`MenuPrice`)과 주문하려는 메뉴의 가격(`OrderLineItemPrice`)이 같아야 한다.
  - 요청된 포장 주문(`TakeOutOrder`)은 대기 상태(`WAITING`)가 된다.
- 포장 주문 고유번호(`TakeOutOrderId`)를 전달하면 주문 대기 상태(`WAITING`)인 포장 주문을 접수한다.(`acceptOrder`)
  - 대기 중(`WAITING`)인 포장 주문(`TakeOutOrder`)이 접수(`ACCEPTED`) 된다.
- 포장 주문 고유번호(`TakeOutOrderId`)를 전달하면 접수(`ACCEPTED`)한 포장 주문을 준비한다.(`serveOrder`)
  - 접수한(`ACCEPTED`) 포장 주문(`TakeOutOrder`)이 준비(`SERVED`)된다.
- 포장 주문 고유번호(`TakeOutOrderId`)를 전달하면 포장 주문을 전달한다.(`completeOrder`)
  - 준비된(`SERVED`) 포장 주문(`TakeOutOrder`)이 전달(`COMPLETE`)된다.

### 매장 식사 주문

속성

- 매장 식사 주문(`EatInOrder`)은 주문 고유 번호(`EatInOrderId`)를 가진다.
- 매장 식사 주문(`EatInOrder`)은 주문 상태를 가지며, 주문 상태(`OrderStatus`)는 주문 대기(`WAITING`), 주문 접수(`ACCEPTED`), 주문 전달(`SERVED`), 식사 완료(`COMPLETED`) 중 하나다.
- 매장 식사 주문(`EatInOrder`)은 주문한 시각(`OrderDateTime`)을 가진다.
- 매장 식사 주문(`EatInOrder`)은 테이블(`OrderTable`)의 정보를 가진다.

행위

- 테이블 고유번호를(`OrderTableId`) 전달하면 매장 식사 주문(`EatInOrder`)을 생성할 수 있다.(`createOrder`)
  - 요청된 매장 식사 주문(`EatInOrder`)은 대기 상태(`WAITING`)가 된다.
- 주문 고유번호(`EatInOrderId`)를 전달하면 대기 상태(`WAITING`)인 매장 식사 주문을 접수한다.(`AcceptOrder`)
  - 대기 중(`WAITING`)인 매장 식사 주문(`EatInOrder`)이 접수(`ACCEPTED`) 된다.
- 주문 고유번호(`EatInOrderId`)를 전달하면 접수(`ACCEPTED`)한 주문을 전달한다.(`serveOrder`)
  - 접수한(`ACCEPTED`) 매장 주문(`EatInOrder`)이 전달(`SERVED`)된다.
- 주문 고유번호(`EatInOrderId`)를 전달하면 식사를 마친다.(`completeOrder`)
  - 전달된(`SERVED`) 매장 식사 주문(`EatInOrder`)을 완료(`COMPLETED`)된다.
  - 완료된 주문(`COMPLETED`)은 주문 테이블(`OrderTable`)을 비우고, 손님의 수(`NumberOfGuests`)를 없앤다.
