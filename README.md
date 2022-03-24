# 키친포스

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
- 상품의 가격이 변경될 때 메뉴의 가격이 메뉴에 속한 상품 가격의 합보다 크면 메뉴가 숨겨진다.
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
- 메뉴에 속한 상품 가격의 합은 메뉴의 가격보다 크거나 같아야 한다.
- 메뉴는 특정 메뉴 그룹에 속해야 한다.
- 메뉴의 이름이 올바르지 않으면 등록할 수 없다.
    - 메뉴의 이름에는 비속어가 포함될 수 없다.
- 메뉴의 가격을 변경할 수 있다.
- 메뉴의 가격이 올바르지 않으면 변경할 수 없다.
    - 메뉴의 가격은 0원 이상이어야 한다.
- 메뉴에 속한 상품 가격의 합은 메뉴의 가격보다 크거나 같아야 한다.
- 메뉴를 노출할 수 있다.
- 메뉴의 가격이 메뉴에 속한 상품 가격의 합보다 높을 경우 메뉴를 노출할 수 없다.
- 메뉴를 숨길 수 있다.
- 메뉴의 목록을 조회할 수 있다.

### 주문 테이블

- 주문 테이블을 등록할 수 있다.
- 주문 테이블의 이름이 올바르지 않으면 등록할 수 없다.
    - 주문 테이블의 이름은 비워 둘 수 없다.
- 빈 테이블을 해지할 수 있다.
- 빈 테이블로 설정할 수 있다.
- 완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정할 수 없다.
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
- 주문 테이블의 모든 매장 주문 상태가 완료되면 빈 테이블로 설정한다.
- 완료되지 않은 매장 주문이 있는 주문 테이블은 빈 테이블로 설정하지 않는다.
- 주문 목록을 조회할 수 있다.

## 용어 사전

### 상품
| 한글명          | 영문명                   | 설명                          |
|--------------|-----------------------|-----------------------------|
| 상품           | Product               | 팔고자 하는 것                    |
| 상품 등록        | Product Registration  | 판매하고자 하는 상품을 등록      |
| 상품 가격 변경      | Product Price Change  | 등록된 상품의 가격을 변경              |
| 상품 목록 조회     | Product List Inquiry  | 등록된 전체 상품의 목록 조회            |
| 메뉴에 속한 상품 가격의 합 | Product Price In menu | 메뉴에 속한 모든 상품의 가격의 합         |
| 비속어          | Profanity               | 욕설                          |

### 메뉴그룹
| 한글명         | 영문명                     | 설명                           |
|-------------|-------------------------|------------------------------|
| 메뉴 그룹       | Menu Group              | 메뉴 그룹은 메뉴의 상위로 하나 이상의 메뉴를 포함 |
| 메뉴 그룹 등록    | Menu Group Registration | 메뉴 그룹을 등록                    |
| 메뉴 그룹 목록 조회 | Menu Group List Inquiry | 등록된 전체 메뉴 그룹 목록 조회           |

### 메뉴
| 한글명      | 영문명                         | 설명                             |
|----------|-----------------------------|--------------------------------|
| 메뉴       | Menu                        | 상품의 정보로 구성된 제공할 메뉴로 메뉴 그룹에 포함됨 |
| 메뉴 등록    | Menu Registration           | 메뉴를 등록                         |
| 메뉴 가격 변경 | Menu Price Change           | 기존에 등록된 메뉴의 가격을 변경             |
| 메뉴 노출    | Menu Display                | 메뉴가 보이도록 노출                    |
| 메뉴 숨김    | Menu Hide                   | 메뉴가 보이지 않음                     |
| 메뉴 목록 조회 | Menu List Inquiry           | 등록된 전체 메뉴의 목록 조회               |
| 등록된 상품 | Registered Product          | "상품" > "상품 등록" 발생으로 등록된 상품     |
| 메뉴에 속한 상품 수량 | Quantity Product In Menu    | 메뉴에 속해있는 전체 상품의 수량             |
| 메뉴에 속한 상품 가격의 합 | Total Product Price In Menu | 메뉴에 속해있는 전체 상품 가격의 합           |
| 비속어          | Profanity                            | 욕설                             |

### 주문 테이블
| 한글명          | 영문명                          | 설명                                                   |
|--------------|------------------------------|------------------------------------------------------|
| 주문 테이블       | Order Table                  | 매장 주문을 이용하는 손님을 위한 테이블                               |
| 주문 테이블 등록    | Order Table Registration     | 주문 테이블을 포스기에 등록                                      |
| 주문 테이블 목록 조회 | Order Table List Inquiry     | 등록된 전체 주문 테이블 목록을 조회                                 |
| 빈 테이블        | Empty Table                  | 손님이 사용 가능한 상태의 테이블로 비어있는 테이블                         |
| 빈 테이블 해지     | Empty Table Off              | 손님이 매장 주문을 이용하기 위해 테이블을 사용하면 해당 테이블은 다른 빈 테이블이 아니게 됨 |
| 빈 테이블 설정     | Empty Table On               | "주문 상태" > "완료" 상태 이면 매장 주문 손님이 이용가능한 빈 테이블로 설정       |
| 손님           | Guest                        | 매장 주문을 하기 위해 주문 테이블을 이용하는 사람                         |
| 방문한 손님 수 변경  | Change In Number Of Guests | 기존에 매장 주문을 위해 방문한 손님의 수가 늘거나 줄되면 손님의 수를 변경           |

### 주문
| 한글명           | 영문명                         | 설명                                              |
|---------------|-----------------------------|-------------------------------------------------|
| 주문            | Order                       | 주문은 주문 유형과 주문의 상태를 가짐                           |
| 주문 유형         | Order Type                  | 주문 유형은 배달, 포장, 매장이 있으며 하기 "주문 유형" 용어사전을 참고      |
| 주문 상태         | Order Status                | 주문 상태는 주문이 처리되고 있는 상태를 뜻하며, 하기 "주문 상태" 용어사전을 참고 |
| 손님            | Guest                       | 주문을 하는 사람                                       |
| 주문 등록         | Order Registration          | 손님이 원하는 주문 유형으로 주문 등록                           |
| 주문 목록 조회      | Order List Inquiry          | 주문되었던 전체 주문 목록을 조회한다.                           |
| 주문 항목의 수량     | Quantity of Order Line Item | 주문 항목 정보에서 해당 메뉴를 주문한 수량                        |
| 배달 주소         | Delivery Address            | "주문 유형"이 "배달 주문"인 경우에 해당하며 배달이 도착해야할 곳의 주소      |
| 배달 대행사        | Delivery Agency             | "주문 유형"이 "배달 주문"인 경우에 해당하며 배달을 대행해주는 업체         |
| 숨겨진 메뉴        | Hidden Menu                 | 용어사전 "메뉴" > "메뉴 숨김"이 발생한 것으로 노출되지 않는 메뉴         |
| 주문한 메뉴 가격     | Ordered Menu Price          | 주문 요청한 항목의 가격                                   |
| 실제 메뉴 가격      | Actual Menu Price           | 항목의 실제 메뉴 가격                                    |
| 접수된 주문        | ACCEPTED                    | "주문 접수"가 된 상태로 하기 "주문 상태" 용어 사전 참고              |
| 서빙된 주문        | SERVED                      | "제공" 상태로 하기 "주문 상태" 용어 사전 참고                    |
| 완료되지 않은 매장 주문 | Incomplete EAT_IN           | "주문 유형" > "매장 주문" 이면서 "주문 상태" > "완료"가 아닌 상태     |

#### 주문 유형 
| 한글명           | 영문명      | 설명                 |
|---------------|----------|--------------------|
| 배달 주문         | DELIVERY | 배달로 받기 위해 요청한 주문   |
| 포장 주문         | TAKEOUT  | 포장을 위해 요청한 주문      |
| 매장 주문         | EAT_IN   | 매장을 이용하기 위해 요청한 주문 |

#### 주문 상태
| 한글명    | 영문명      | 설명                         |
|--------|----------|----------------------------|
| 접수 대기  | WAITING  | 주문 접수 대기 중인 상태             |
| 주문 접수  | ACCEPTED | 주문한 사람의 주문을 수락하여 접수한 상태    |
| 제공     | SERVED  | 주문한 사람에게 제공 되는 상태          |
| 배달 중   | DELIVERING  | 배달 중인 상태                   |
| 배달 완료  | DELIVERED  | 배달 완료된 상태                  |
| 완료 | COMPLETED  | 주문 한 것이 주문한 사람에게 제공 완료된 상태 |

## 모델링

### 상품(Product)

#### 속성
- 상품은 `이름`(name)을 가진다.
- 상품은 `가격`(price)을 가진다.

#### 기능
- 상품을 등록(create)할 수 있다.
  - 가격은 비어있거나 0 보다 작을 수 없다.
  - 이름은 비어있거나 비속어(profanity)를 포함할 수 없다.
- 상품의 가격을 변경(changePrice)할 수 있다.
  - 가격은 비어있거나 0 보다 작을 수 없다.
  - 기존에 등록된 상품만 변경할 수 있다.
  - 메뉴에 속한 상품(MenuProduct)들 가격의 합보다 메뉴의 가격이 크면 메뉴가 숨겨진다.
- 등록된 상품을 모두 조회(findAll)할 수 있다.

### 메뉴 그룹(MenuGroup)

#### 속성
- 메뉴 그룹은 `이름`(name)을 가진다.

#### 기능
- 메뉴 그룹을 등록(create)할 수 있다.
  - 이름은 비어 있을 수 없다.
- 등록된 모든 메뉴 그룹을 조회(findAll)할 수 있다.

### 메뉴(Menu)

#### 속성
- 메뉴는 `이름`(name)을 가진다.
- 메뉴는 `가격`(price)을 가진다.
- 메뉴를 노출(메뉴 노출)할 것인지 메뉴를 숨길(메뉴 숨김) 것 인지에 대한 상태 값 `메뉴 노출`(displayed) 여부를 가진다.
- 메뉴는 하나의 `메뉴 그룹`(MenuGroup)에 포함 된다.

#### 기능
- 메뉴를 등록(create)할 수 있다.
  - 가격은 비어있거나 0 보다 작을 수 없다.
  - 기존에 등록된 메뉴 그룹(MenuGroup)에 속해야 한다.
  - 메뉴에 속할 상품(MenuProduct)이 없을 수 없다.
  - 메뉴에 속할 상품(MenuProduct)들이 기존에 등록된 상품(Product)인지 확인하고 이 개수가 다를 수 없다.
  - 메뉴에 속할 상품(MenuProduct)은 개수가 0 보다 작을 수 없다.
  - 메뉴에 속할 상품(MenuProduct)은 기존에 등록된 상품 이어야 한다. 
  - 가격은 메뉴에 속할 상품(MenuProduct)들 가격의 합 보다 클 수 없다. 
  - 이름은 비어있거나 비속어(profanity)를 포함할 수 없다.
- 메뉴 가격을 변경(changePrice)할 수 있다.
  - 가격은 비어있거나 0 보다 작을 수 없다.
  - 기존에 등록된 메뉴 이어야 한다.
  - 변경될 가격이 메뉴에 속한 상품(MenuProduct) 들 가격의 합보다 클 수 없다.
- 메뉴를 노출(display)할 수 있다.
  - 기존에 등록된 메뉴 이어야 한다.
  - 노출할 메뉴의 가격이 메뉴에 속한 상품(MenuProduct)들 가격의 합 보다 클 수 없다. 
- 메뉴를 숨길(hide) 수 있다.
  - 기존에 등록된 메뉴 이어야 한다.
- 등록된 모든 메뉴를 조회(findAll)할 수 있다.
### 주문 테이블(OrderTable)

#### 속성
- 주문 테이블은 `이름`(name)을 가진다.
- 주문 테이블은 `손님 수`(numberOfGuests)를 가진다.
- 주문 테이블은 테이블이 비었는지에 대한 상태 값 `빈 테이블`(empty) 여부를 가진다.

#### 기능
- 주문 테이블을 등록(create)할 수 있다.
  - 이름은 비어 있을 수 없다.
  - 등록 시 주문 테이블은 빈 테이블로 설정 된다.
  - 등록 시 주문 테이블의 방문한 손님 수는 0으로 설정 된다.
- 빈 테이블을 해지(sit)할 수 있다.
  - 기존에 등록된 주문 테이블 이어야 한다.
- 빈 테이블을 설정(clear)할 수 있다.
  - 기존에 등록된 주문 테이블 이어야 한다.
  - 주문 상태는 완료(COMPLETED) 상태 이어야 한다.
  - 빈 테이블이 설정 되면 방문한 손님 수는 0으로 설정 된다. 
- 주문 테이블에 방문한 손님 수를 변경(changeNumberOfGuests)할 수 있다.
  - 방문한 손님 수는 0 보다 작을 수 없다.
  - 기존에 등록된 주문 테이블 이어야 한다.
  - 빈 테이블이 설정(clear)이 되지 않은 주문 테이블 이어야 한다.
- 등록된 모든 주문 테이블을 조회(findAll)할 수 있다.

### 주문(Order)

#### 속성
- 주문은 `배달 주소`(deliveryAddress)를 가진다.
- 주문은 손님이 `주문한 시간`(orderDateTime)을 가진다.
- 주문은 `주문 유형`(type)을 가진다.
  - 주문 유형은 다음과 같은 유형이 있다.
    - 주문 유형의 종류는 용어 사전의 `주문 유형`을 참고한다.
- 주문은 `주문 상태`(status)를 가진다.
  - 주문 상태의 종류는 용어 사전의 `주문 상태`를 참고한다.
- 주문은 `주문 테이블`(orderTable)에 포함되거나 되지 않을 수 있다. 

#### 기능
- 주문을 등록(create)할 수 있다.
  - 주문 유형은 비어 있을 수 없다.
  - 주문한 메뉴(orderLineItem)가 없을 수 없다.
  - 주문한 메뉴가 실제 존재하는 메뉴인지 확인하고 개수가 다를 수 없다.
  - 주문 유형이 매장 주문(EAT_IN)이라면 주문한 메뉴(orderLineItem) 수량이 0 보다 작을 수 없다.
  - 주문한 메뉴(orderLineItem)가 등록된 메뉴 이어야 한다.
  - 메뉴가 숨김 상태일 수 없다.
  - 메뉴 가격이 주문한 메뉴(orderLineItem) 가격과 다를 수 없다.
  - 주문 유형이 배달 주문이라면 배달 주소가 비어 있을 수 없다.
  - 주문 유형이 매장 주문이라면 주문 등록 시 주문 테이블에 방문한 손님 이어야 한다. 
  - 주문 상태는 접수 대기(WAITING) 상태가 된다.
- 주문을 접수할 수 있다.(accept)
  - 기존에 등록된 주문 이어야 한다.
  - 주문 상태가 접수 대기(WAITING) 상태 이어야 한다.
  - 주문 유형이 배달 주문(DELIVERY)이라면 배달 기사(kitchenRider)를 호출 한다.
- 주문을 제공할 수 있다.(serve)
  - 기존에 등록된 주문 이어야 한다.
  - 주문 상태가 주문 접수(ACCEPTED) 상태 이어야 한다.
- 주문 상태를 배달 시작으로 할 수 있다.(startDelivery)
  - 기존에 등록된 주문 이어야 한다.
  - 주문 유형이 배달 주문(DELIVERY) 이어야 한다.
  - 주문 상태가 제공(SERVED) 상태 이어야 한다.
- 주문 상태를 배달 완료로 할 수 있다.(completeDelivery)
  - 기존에 등록된 주문 이어야 한다.
  - 주문 상태가 배달 중(DELIVERING) 이어야 한다.
- 주문 상태를 완료로 할 수 있다.(complete)
  - 기존에 등록된 주문 이어야 한다.
  - 주문 유형이 배달 주문(DELIVERY)이라면 주문 상태는 배달 완료(DELIVERED) 상태 이어야 한다.
  - 주문 유형이 포장 주문(TAKEOUT)이라면 주문 상태는 제공(SERVED) 상태 이어야 한다.
  - 주문 유형이 매장 주문(EAT_IN)이라면 주문 상태는 제공(SERVED) 상태 이어야 한다.
  - 주문 유형이 매장 주문(EAT_IN)이면서 주문 상태가 완료(COMPLETED)라면 방문 고객 수는 0이고 빈 테이블로 설정 되어야 한다.
- 등록된 모든 주문을 조회(findAll)할 수 있다.
