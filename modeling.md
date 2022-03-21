## 모델링

### Product (상품)

속성

- name(이름)
  - 공백일 수 없다.
  - 비속어가 포함될 수 없다.
- price(가격)
  - 0원 이상이어야 한다.

행위

- register (등록할 수 있다)
- changePrice (가격을 변경할 수 있다)
  - 변경될 때 메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 크면 메뉴가 숨겨진다.
  

### Menu (메뉴)

속성

- menuLineItems (메뉴 항목 목록)
  - 1개 이상을 포함해야 한다.
- name (이름)
  - 공백일 수 없다.
  - 비속어가 포함될 수 없다.
- price (가격)
  - 0원 이상이어야 한다.
  - 상품 목록 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
- isDisplayed(노출 여부)
  - 가격이 상품 목록 금액의 합보다 높을 경우 노출할 수 없다.
- menuGroup (메뉴 그룹)
  - 반드시 특정 메뉴 그룹에 속해야 한다.

행위

- register (등록할 수 있다)
- changePrice (가격을 변경할 수 있다)
- display (노출할 수 있다)
- hide (숨길 수 있다)

### Menu Group (메뉴 그룹)

속성

- name(이름)
  - 이름을 공백일 수 없다.

행위

- register (등록할 수 있다)

### Menu Line Item (메뉴 항목)

속성

- product (상품)
- quantity (수량)
  - 0 이상이어야 한다.

행위

- calculateAmount (금액을 계산할 수 있다)

### Order Table (주문 테이블)

속성

- name (이름)
  - 공백일 수 없다.
- numberOfGuests (방문한 손님 수)
  - 0 이상이어야 한다.
- isEmpty (빈 테이블 여부)

행위

- register (등록할 수 있다)
- empty (빈 테이블로 설정할 수 있다)
  - 완료되지 않은 주문이 있는 주문 테이블은 빈 테이블로 설정할 수 없다.
- inUse (빈 테이블을 해지할 수 있다)
- changeNumberOfGuests (손님 수를 변경할 수 있다)
  - 빈 테이블은 변경할 수 없다.

### Order (주문)

속성

- type (종류)
  - TakeOut, EatIn, Delivery
- orderLineItems (주문 항목 목록)
  - 1개 이상을 포함해야 한다.
  - 매장 주문은 주문 항목의 메뉴 수량이 0 미만일 수 있다.
  - 매장 주문을 제외한 주문의 경우 주문 항목의 메뉴 수량은 0 이상이어야 한다.
- status (상태)
  - Waiting, Accepted, Served, Delivering, Delivered, Done
- orderTable (주문 테이블)
  - 매장 주문일 때만 포함해야 한다.
- deliveryAddress (배달 주소)
  - 배달 주문일 때만 공백일 수 없다.

행위

- register (등록할 수 있다)
  - 주문 종류가 올바르지 않으면 등록할 수 없다.
  - 배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다.
  - 빈 테이블에는 매장 주문을 등록할 수 없다.
  - 숨겨진 메뉴는 주문할 수 없다.
  - 주문한 메뉴의 가격은 실제 메뉴 가격과 일치해야 한다.
- accept (접수한다)
  - 접수 대기 중인 주문만 접수할 수 있다.
  - 배달 주문을 접수되면 배달 대행사를 호출한다.

serve (서빙한다)

- 접수된 주문만 서빙할 수 있다.
- delivery (배달한다)
  - 배달 주문만 배달할 수 있다.
  - 서빙된 주문만 배달할 수 있다.
- delivered (배달 완료한다)
  - 배달 중인 주문만 배달 완료할 수 있다.
- done (완료한다)
  - 주문 테이블의 모든 주문이 완료되었으면 빈 테이블로 설정한다.

### Order Line Item (주문 항목)

속성

- menu (메뉴)
- quantityOfMenus (메뉴 수량)

행위

- calculateAmount (금액을 계산할 수 있다)
