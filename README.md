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

### 공통용어
| 한글명           | 영문명                                 | 설명                             |
|---------------|-------------------------------------|--------------------------------|
| 비속어           | Profanity                           | 막말이 가미된 모욕적인 말 또는 모독적인 말       |
| 수량            | Quantity                            | 재화의 양적 단위                      |
| 노출여부          | Displayed                           | 비즈니스 요소를 대상에게 보여주기 위한 기준       |
| 손님            | Guest                               | 매장을 이용하는 사람                    |

### 상품
| 한글명           | 영문명                                 | 설명                                              |
|---------------|-------------------------------------|-------------------------------------------------|
| 상품            | Product                             | 판매를 위해 매장에서 제조하거나 보유한 음식 또는 물건으로 상품명과 상품 가격을 가짐 |
| 상품 생성         | Create Product                      | 상품을 새로 정의해 등록하는 행위                              |
| 상품 가격 변경      | Change Product Price                | 상품의 가격을 변경하는 행위                                 |

### 메뉴그룹
| 한글명           | 영문명                                 | 설명                               |
|---------------|-------------------------------------|----------------------------------|
| 메뉴 그룹         | Menu Group                          | 메뉴의 집합. 메뉴를 분류하기 위한 기준으로 메뉴명을 가짐 |
| 메뉴 그룹 생성      | Create Menu Group                   | 메뉴그룹을 새로 정의해 등록하는 행위             |

### 메뉴
| 한글명      | 영문명               | 설명                                           |
|----------|-------------------|----------------------------------------------|
| 메뉴       | Menu              | 고객이 상품을 선택하기 위한 단위로 메뉴명과 가격, 노출여부를 표시할 수 있다. |
| 메뉴 생성    | Create Menu       | 메뉴를 정의해 등록하는 행위.                             |
| 메뉴 가격 변경 | Change Menu Price | 메뉴의 가격을 변경하는 행위                              |
| 메뉴 노출    | Display Menu      | 메뉴의 노출여부를 여 로 변경하는 행위                        |
| 메뉴 숨김    | Hide Menu         | 메뉴의 노출여부를 부 로 변경하는 행위                        |
| 메뉴상품     | Menu Product      | 메뉴를 구성하는 상품                                  |

### 주문테이블
| 한글명           | 영문명                                 | 설명                                                            |
|---------------|-------------------------------------|---------------------------------------------------------------|
| 주문테이블         | Order Table                         | 매장에서 주문이 일어나는 테이블로 이름과 손님 수를 갖는다. 테이블에 앉은 손님의 착석여부를 표시할 수 있다. |
| 주문테이블 손님 수    | Order Table Number Of Guests        | 주문 테이블에 착석한 손님의 수                                             |
| 주문테이블 착석여부    | Order Table Empty                   | 주문 테이블에 착석한 손님이 존재하는 지 여부                                     |
| 주문테이블 착석      | Sit Order Table                     | 비어있는 주문 테이블에 손님이 착석하는 행위                                      |
| 주문테이블 비움      | Clear Order Table                   | 주문 테이블의 손님 착석정보를 모두 지우는 행위                                    |
| 주문테이블 손님 수 변경 | Change Order Table Number Of Guests | 주문 테이블에 착석한 손님 수를 변경하는 행위                                     |

### 주문
| 한글명           | 영문명                                 | 설명                             |
|---------------|-------------------------------------|--------------------------------|
| 주문            | Order                               | 손님이 메뉴를 제공받기 위해 매장에 행하는 요청     |
| 주문유형          | Order Type                          | 주문의 형태                         |
| 주문상태          | Order Status                        | 주문의 진행상태                       |
| 주문시각          | Order Date Time                     | 주문이 생성된 시각                     |
| 주문항목          | Order Line Item                     | 주문을 구성하는 메뉴항목                  |
| 주문항목 순번       | Order Line Item Sequence            | 주문을 구성하는 메뉴항목 하나의 순서           |
| 주문항목 수량       | Order Line Item Quantity            | 주문을 구성하는 메뉴항목 하나의 수량           |
| 주문항목 가격       | Order Line Item Price               | 주문을 구성하는 메뉴항목 하나의 가격           |
| 주문 배달주소       | Order Delivery Address              | 배달주문의 목적지                      |

#### [주문]코드용어
| 한글명         | 영문명        | 설명                                 |
|-------------|------------|------------------------------------|
| [주문유형] 배달   | DELIVERY   | 배달주문                               |
| [주문유형] 포장   | TAKEOUT    | 포장주문                               |
| [주문유형] 매장식사 | EAT_IN     | 매장식사주문                             |
||||
| [주문상태] 대기   | WAITING    | 주문 생성 시 초기 상태                      |
| [주문상태] 접수   | ACCEPTED   | 매장에서 주문을 확인한 후 접수한 상태              |
| [주문상태] 제공   | SERVED     | 주문 접수 후, 메뉴에 해당하는 음식 또는 상품이 제공된 상태 |
| [주문상태] 배달중  | DELIVERING | 제공된 메뉴의 배달이 시작된 상태                 |
| [주문상태] 배달완료 | DELIVERED  | 제공된 메뉴의 배달이 완료된 상태                 |
| [주문상태] 완료   | COMPLETED  | 매장에서 제공한 메뉴가 손님에게 전달된 상태           |

## 모델링
