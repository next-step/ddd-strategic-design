## 모델링

### Product

속성

- `Product`는 비속어가 포함될 수 없는 `name`을 가진다.
- `Product`는 0원 이상인 `price`를 가진다.

기능

- `Product`를 등록할 수 있다
- `Product` `price`를 변경할 수 있다
  - 변경될 때 `Menu`의 `price`가 `Menu`에 속한 `Product` `amount`를 합보다 크면 `Menu`가 숨겨진다.
  

### `Menu`

속성

- `Menu`는 1개 이상의 `MenuLineItem` 목록을 가진다.
- `Menu`는 비속어가 포함될 수 없는 `name`을 가진다
- `Menu`는 0원 이상의 `price`를 가진다.
  - `MenuLineItem` 목록 `amount`를 합은 `Menu`의 가격보다 크거나 같아야 한다.
- `Menu`는 `isDisplayed`를 가진다.
- `Menu`는 `MenuGroup`을 가진다.

행위

- `Menu`를 등록할 수 있다.
- `Menu`는 `price`를 변경할 수 있다
- `Menu`를 노출할 수 있다
- `Menu`를 숨길 수 있다

### MenuLineItem

속성
- `MenuLineItem`은 `Product`를 가진다.
- `MenuLineItem`은 0개 이상의 `quantity`를 가진다.

행위

- `MenuLineItem`은 `Product`와 `quantity`로 `amount`를 계산할 수 있다

### MenuGroup

속성

- `MenuGroup`은 공백이 아닌 `name`을 가진다.

행위

- `MenuGroup`을 등록할 수 있다.

### `OrderTable`

속성

- `OrderTable`은 공백이 아닌 `name`을 가진다.
- `OrderTable`은 방문한 `numberOfGuests`를 가진다.
- `OrderTable`은 `isEmpty`을 가진다.

행위

- `OrderTable`을 등록할 수 있다.
- `OrderTable`은 빈 테이블로 설정할 수 있다.
  - 완료되지 않은 주문이 있는 `OrderTable`은 빈 테이블로 설정할 수 없다.
- `OrderTable`을 사용 중으로 변경할 수 있다.
- `OrderTable`이 사용 중이면 `numberOfGuests`를 변경할 수 있다

### Order

속성

- `Order`는 `OrderType`를 가진다.
  - `OrderType`은 `EatIn`, `Delivery`, `TakeOut`이 있다.
- `Order`는 하나 이상의 `OrderLineItem`을 가진다
- `Order`는 `OrderStatus`를 가진다.
  - `OrderStatus`는 `Waiting`, `Accepted`, `Served`, `Delivering`, `Delivered`, `Done`이 있다.
- `Order`는 `OrderType`이 `EatIn`이면 손님들이 식사하는 `OrderTable`을 가진다.
- `Order`는 `OrderType`이 `Delivery`일 때 `DeliveryAddress`를 가진다.

행위

- `Order`를 등록할 수 있다.
  - `empty`인 `OrderTable`에는 `OrderType`이 `EatIn`인 `Order`를 등록할 수 없다.
  - `hide`된 `Menu`는 주문할 수 없다.
  - `OrderLineItem`의 `amount`는 실제 `Menu`의 `price`와 일치해야 한다.
- `Order`를 접수할 수 있다.
  - `OrderStatus`가 `Waiting`일 때만 접수할 수 있다.
  - `OrderType`이 `Delivery`면 `DeliveryAgency`를 호출한다.
- `Order`를 서빙할 수 있다.
  - `OrderStatus`가 `Accepted`일 때만 서빙할 수 있다.
- `Order`를 배달할 수 있다.
  - `OrderType`이 `Delivery`일 때만 배달할 수 있다.
  - `OrderStatus`가 `Served`일 때만 배달할 수 있다.
- `Order`를 배달 완료할 수 있다.
  - `OrderStatus`가 `Delivery`일 때만 배달 완료할 수 있다.
- `Order`를 완료할 수 있다
  - `OrderTable`의 모든 `Order`가 완료되었으면 빈 테이블로 설정한다.

### OrderLineItem

속성

- `OrderLineItem`은 `Menu`를 가진다.
- `OrderLineItem`은 `quantity`를 가진다.
  - `Order`의 `status`가 `EatIn`이면 `quantity`가 0 미만일 수 있다.
  - `EatIn`을 제외한 `Order`는 `quantity`가 0 이상이어야 한다.

행위

- `OrderLineItem`은 `Menu`와 `quantity`로 `amount`를 계산할 수 있다.
