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

### 상품
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 상품을 뜻 한다. |
| 상품 명 | product name | 상품 명을 뜻하며 비속어가 포함 될 수 없다. |
| 상품 가격 | product price | 상품 가격을 뜻하며 가격은 0 이상이어야 한다. |

### 메뉴 그룹
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 그룹 | menu group | 음식, 음료와 같은 메뉴 그룹핑 정보를 뜻한다. |
| 메뉴 그룹 명 | menu group name | 메뉴 그룹 명은 비워 둘 수 없다. |

### 메뉴
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 | menu | 주문시 고객이 선택하는 항목을 뜻한다. |
| 메뉴 명 | menu name | 메뉴 명을 뜻하며 비속어가 포함 될 수 없다. |
| 메뉴 가격 | menu price | 메뉴 가격을 뜻하며 가격은 0 이상이다. |
| 메뉴 공개 여부 | menu displayed | 메뉴 공개/비공개 여부를 뜻한다. 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우에만 공개 가능하다. |

### 메뉴 상품
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 메뉴 상품 | menu product | 메뉴에 구성에 필요한 상품을 뜻한다. |
| 메뉴 상품 수량| menu product quantity | 메뉴 구성에 들어가는 상품 수량을 뜻한다. |
| 메뉴 상품 가격 | menu product price | 메뉴 상품 가격 x 수량 값을 듯한다. |

### 주문 테이블
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 테이블 | order table | 가게에 배치된 테이블을 뜻한다. |
| 주문 테이블 명 | order table name | 주문 테이블 명을 부르는 명칭을 뜻한다. |
| 주문 테이블 손님 수 | order table number of guest | 주문 테이블에 착석한 손님 수를 뜻한다. |
| 주문 테이블 착석 여부 | order table empty | 주문 테이블에 손님이 착석했는지 여부를 뜻한다.  |

### 주문
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 | orders | 고객이 요청하는 주문을 뜻한다. |
| 주문 타입 | order type | 주문 타입을 뜻한다. (배달주문, 포장주문, 매장내식사주문) |
| 주문 상태 | order status | 주문 상태를 뜻한다. (주문 대기, 주문 수락, 주문 서빙, 배달중, 배달 완료, 주문완료)|
| 주문 시간 | order date time | 주문이 접수된 시간을 듯한다. |
| 배달 주소 | order delivery address | 배송 주문의 배송지를 뜻한다. |

### 주문 > 주문 타입
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 배달주문 | DELIVERY | 배달주문을 뜻한다. |
| 포장주문 | TAKEOUT | 포장주문을 뜻한다. |
| 방문주문 | EAT_IN | 방문주문을 뜻한다. |

### 주문 > 주문 상태
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문대기 | WAITING | 주문 대기 상태를 뜻한다. |
| 주문수락 | ACCEPTED | 주문 수락 상태를 뜻한다. |
| 주문서빙 | SERVED | 주문들어온 상품을 준비 완료한 상태를 뜻한다. |
| 배달중 | DELIVERING | 배달중 상태를 뜻한다. |
| 배달완료 | DELIVERED | 배달완료 상태를 뜻한다. |
| 주문처리완료 | COMPLETED | 주문 처리 완료 상태를 뜻한다. |

### 배달 대행사
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 배달 대행사 | kitchen riders | 배달 대행사를 뜻 한다. 배달 주문 타입 주문시 배달 대행사에 배달 요청한다. |

### 주문 항목
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주문 항목 | order line item | 주문에 포함된 메뉴와 수량 정보를 뜻한다. |
| 주문 항목 상품 | order line item product | 주문 항목 상품을 뜻한다. |
| 주문 항목 수량 | order line item quantity | 주문 항목 상품 수량을 뜻한다. |
| 주문 항목 가격 | order line item price | 주문 항목 가격을 뜻하며 상품 가격 x 상품수량 이다. |

## 모델링

### 상품

- `product`를 등록한다.
  - `product`는 `product name` 과 `product price`를 가진다.
  - `product price`가 올바르지 않으면 등록 할 수 없다.
    - `product price`는 0 이상이어야 한다.
  - `product name`이 올바르지 않으면 등록 할 수 없다.
    - `product name`은 비속어가 될 수 없다.
- `product price`를 변경한다.
  - `product price`가 올바르지 않으면 변경 할 수 없다.
    - `product price`는 0원 이상이어야 한다.
    - `product price`가 변경될때 `menu`의 `menu product`들의 `menu product price`합보다 크면 `menu`의 `menu displayed` 값이 `false` 로 변한다. 
- `product`목록을 조회 한다.

### 메뉴 그룹

- `menu group`을 등록한다.
  - `menu group`는 `menu group name`을 가진다.
  - `menu group name`이 올바르지 않으면 등록 할 수 없다.
    - `menu group name`은 필수이다.
- `menu group`목록을 조회 한다.

### 메뉴

- `menu`를 등록한다. 
  - `menu`는 `menu name`, `menu price`와 `menu displayed`를 가진다.
  - `menu`를 등록하려면 `menu product`가 필요하다.
    - `menu product`의 `product`가 등록되어있지 않으면 `menu`를 등록 할 수 없다.
    - `menu product`의 수량은 0 이상이어야 한다.
  - `menu price`가 올바르지 않으면 등록 할 수 없다.
    - `menu price`는 0원 이상이어야 한다.
    - `menu product`들의 `menu product price`합은 `menu price`보다 크거나 같아야 한다.
- `menu displayed`를 `true`로 변경한다.
  - `menu price`가 `menu product`들의 `menu product price`합보다 높을경우 값을 변경 할 수 없다.
- `menu displayed`를 `false`로 변경한다.
- `menu`목록을 조회 한다.

### 주문 테이블

- `order table`를 등록한다.
  - `order table`은 `order table name`, `order table number of guest`, `order table empty`를 가진다.
  - `order table name`이 올바르지 않으면 등록 할 수 없다.
    - `order table name`은 필수다.
- `order table empty`를 `false`로 변경한다.
- `order table empty`를 `true`로 변경한다.
  - 완료되지 않은 `order`가 있는 `order table`은 `order table empty`를 `true`로 변경 할 수 없다.
- `order table number of guest`를 변경한다.
  - `order table number of guest`가 올바르지 않으면 변경 할 수 없다.
    - `order table number of guest`는 0 이상이어야 한다.
- `order table`목록을 조회 한다.

### 주문

- `order`를 등록한다.
  - `order`는 `order type`, `order status`, `order date time`, `order delivery address`, `order line item`, `order table`를 가진다. 
  - 1개 이상의 등록된 `menu`로 `order type`이 `DELIVERY`인 `order`를 등록 할 수 있다.
  - 1개 이상의 등록된 `menu`로 `order type`이 `TAKEOUT`인 `order`를 등록 할 수 있다.
  - 1개 이상의 등록된 `menu`로 `order type`이 `EAT_IN`인 `order`를 등록 할 수 있다.
  - `order type`이 올바르지 않으면 등록 할 수 없다.
    - `order type`은 필수다.
  - `order line item`이 없으면 `order`를 등록 할 수 없다.
  - `order type`이 `EAT_IN`인 `order`는 `order line item quantity`가 0 미만일 수 있다.
  - `order type`이 `EAT_IN`인 `order`를 제외한 `order type`을 가진 `order`는 `order line item quantity`가 0 미만일 수 있다.
  - `order type`이 `DELIVERY` 인 경우 `order delivery address`가 올바르지 않으면 등록 할 수 없다.
    - `order type`이 `DELIVERY` 인 경우 `order delivery address`는 필수이다.
  - `order type`이 `EAT_IN`인 경우 `order table empty`가 `true`일 수 없다.
  - `menu displayed`가 `false`인 `menu`를 포함한 `order`는 등록 할 수 없다.
  - `order line item price`와 `menu price`는 동일해야한다.
- `order`의 `order status`를 `ACCEPTED`로 변경한다.
  - `order status`가 `WAITING`인 `order`만 변경 가능하다.
  - `order type`이 `DELIVERY`인 `order`일 경우 `kitchen riders`에 요청한다.
- `order`의 `order status`를 `SERVED`로 변경한다.
  - `order status`가 `ACCEPTED`인 `order`만 변경 가능하다.
- `order`의 `order status`를 `DELIVERING`로 변경한다.
  - `order type`이 `DELIVERY`인 `order`만 변경 가능하다.
  - `order status`가 `SERVED`인 `order`만 변경 가능하다.
- `order`의 `order status`를 `DELIVERED`로 변경한다.
  - `order status`가 `DELIVERED`인 `order`만 변경 가능하다.
- `order`의 `order status`를 `COMPLETED`로 변경한다.
  - `order type`이 `DELIVERY`인 `order`일 경우 `order status`가 `DELIVERED`일때만 변경 가능하다.
  - `order type`이 `TAKEOUT` 또는 `EAT_IN`인 `order`일 경우 `order status`가 `SERVED`일때만 변경 가능하다.
  - `order table empty`를 변경한다.
    - `order table`의 모든 `order status`가 `COMPLETED`일 경우 `order table empty`를 `true`로 변경한다.
    - `order table`의 모든 `order status`가 `COMPLETED`가 아닐 경우 `order table empty`를 변경 할 수 없다.
- `order`를 조회한다.
