# 키친포스

## 요구 사항

### 상품

* 상품을 등록할 수 있다.
* 상품의 가격이 올바르지 않으면 등록할 수 없다.
    * 상품의 가격은 0 원 이상이어야 한다.
* 상품의 목록을 조회할 수 있다.

### 메뉴 그룹

* 메뉴 그룹을 등록할 수 있다.
* 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴

* 1 개 이상의 등록된 상품으로 메뉴를 등록할 수 있다.
* 메뉴의 가격이 올바르지 않으면 등록할 수 없다.
    * 메뉴의 가격은 0 원 이상이어야 한다.
    * 메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다.
* 메뉴는 특정 메뉴 그룹에 속해야 한다.
* 메뉴의 목록을 조회할 수 있다.

### 테이블

* 테이블을 등록할 수 있다.
* 테이블의 목록을 조회할 수 있다.
* 빈 테이블 설정 또는 해지할 수 있다.
* 단체 지정된 테이블은 빈 테이블 설정 또는 해지할 수 없다.
* 주문 상태가 조리 또는 식사인 테이블은 빈 테이블 설정 또는 해지할 수 없다.
* 방문한 손님 수를 입력할 수 있다.
* 방문한 손님 수가 올바르지 않으면 입력할 수 없다.
    * 방문한 손님 수는 0 명 이상이어야 한다.
* 빈 테이블은 방문한 손님 수를 입력할 수 없다.

### 단체 지정

* 2 개 이상의 빈 테이블을 단체로 지정할 수 있다.
* 단체 지정은 중복될 수 없다.
* 단체 지정을 해지할 수 있다.
* 단체 지정된 테이블의 주문 상태가 조리 또는 식사인 경우 단체 지정을 해지할 수 없다.

### 주문

* 1 개 이상의 등록된 메뉴로 주문을 등록할 수 있다.
* 빈 테이블에는 주문을 등록할 수 없다.
* 주문의 목록을 조회할 수 있다.
* 주문 상태를 변경할 수 있다.
* 주문 상태가 계산 완료인 경우 변경할 수 없다.

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | Product | 식당에서 판매하는 음식이고, 이름과 가격을 갖고 있다 |
| 상품 가격 | Product Price | 상품의 가격이고, 0 원 이상이다. |
| 메뉴 | Menu | 식당에서 판매하는 단위이고, 메뉴 구성 상품, 이름, 가격을 갖고 있다. |
| 메뉴 구성 상품 | Menu Product | 메뉴를 구성하는 상품이고, 수량을 갖는다. |
| 메뉴 가격 정책 | Menu Price Rule | 모든 메뉴 구성 상품들의 가격의 총합보다 메뉴의 가격은 저렴해야 한다. |
| 메뉴 상품 정책 | Menu Product Rule | 모든 메뉴들은 메뉴 구성 상품을 1개 이상 갖는다. |
| 메뉴 수량 정책 | Menu Quantity Rule | 모든 메뉴 구성 상품들의 수량은 1개 이상이다. |
| 메뉴 그룹 | Menu Category | 식당에서 메뉴를 소개하는 단위로, 복수 개의 메뉴를 갖는다. - 한식, 중식 등 |
| 손님 테이블 | Guest Table | 식당에서 손님들이 식사를 하는 장소 |
| 손님 테이블 상태 | Guest Table Status | 공석, 착석 |
| 손님 테이블 상태 정책 | Guest Table Status Rule | 단체 지정 되어 있지 않거나, 모든 주문들이 최종 주문 상태인 경우에만 손님 테이블 상태를 변경할 수 있다. |
| 손님 테이블 착석 손님 | Guest Table Occupied Guest | 착석 상태의 손님 테이블에 앉은 손님을 뜻하며, 아직 도착하지 않은 경우에는 0명으로 취급한다. |
| 손님 | Guest | 식사를 하려고, 손님 테이블에 앉은 손님, 손님은 공석인 손님 테이블에만 앉을 수 있다. |
| 주문 | Order | 손님 테이블에 앉은 손님이 등록된 메뉴에 있는 음식으로 식사를 하는 절차 |
| 주문 메뉴 | Ordered Menu | 손님이 주문을 하면서 고른 메뉴이고, 각 메뉴별 수량을 갖는다. |
| 주문 상태 | Order Status | 조리(COOKING), 식사(MEAL), 계산완료(COMPLETION) |
| 최종 주문 상태 | Final Order Status | 최종 주문 상태의 주문은 상태를 변경할 수 없다. - 계산 완료(COMPLETION) |
| 주문 메뉴 정책 | Order Menu Rule | 주문은 1개 이상의 메뉴를 갖는다. |
| 주문 손님 테이블 정책 | Order Guest Table Rule | 주문은 착석 상태의 손님 테이블에서만 발생한다. |
| 손님 테이블 단체 지정 | Guest Table Group | 단체 손님을 위해, 2개 이상의 테이블을 묶어서 관리할 수 있다. |
| 손님 테이블 단체 지정 - 테이블 상태 정책 | Guest Table Group Status Rule | 공석 손님 테이블만 손님 테이블 단체 지정으로 묶을 수 있다. |
| 손님 테이블 단체 지정 - 중복 방지 정책 | Guest Table Group Deduplication Rule | 단체 지정 대상이 될 손님 테이블은 중복될 수 없다. |
| 손님 테이블 단체 지정 - 해지 정책 | Guest Table Group Termination Rule | 조리, 식사 상태의 주문이 있는 손님 테이블은 단체 지정을 해지 할 수 없다. |

## 모델링
### 상품 (Product)  
> 요구사항  
  >> 상품 (Product)을 등록할 수 있다.  
  >> 상품 (Product)의 목록을 조회할 수 있다.  

* **상품 (Product)** 은 이름과 **상품 가격 (Product Price)** 을 갖는다.

### 메뉴 그룹 (Menu Category)
> 요구사항  
  >> 메뉴 그룹 (Menu Category)을 등록할 수 있다.  
  >> 메뉴 그룹 (Menu Category)의 목록을 조회할 수 있다.  
  
* **메뉴 그룹(Menu Category)** 은 이름을 갖는다.  
* **메뉴 그룹(Menu Category)** 에서 신규 **메뉴 (Menu)** 를 생성한다.  

### 메뉴 (Menu)  
> 요구사항  
  >> 1 개 이상의 등록된 상품으로 메뉴 (Menu)를 등록할 수 있다.  
  >> 메뉴 (Menu)의 목록을 조회할 수 있다.  

* **메뉴 (Menu)** 는 복수 개의 **메뉴 구성 상품 (Menu Product)** , 이름, 가격을 갖는다.  
* **메뉴 (Menu)** 가 갖는 **메뉴 가격 정책 (Menu Price Rule)** 을 따라   
  **메뉴 구성 상품 (Menu Product)** 과 가격을 갖는다.  
* **메뉴 (Menu)** 가 갖는 **메뉴 상품 정책 (Menu Product Rule)** 을 따라   
  **메뉴 구성 상품 (Menu Product)** 을 갖는다.  
* **메뉴 (Menu)** 가 갖는 **메뉴 수량 정책 (Menu Quantity Rule)** 을 따라   
  **메뉴 구성 상품 (Menu Product)** 을 갖는다.  

### 손님 테이블 (Guest Table)
> 요구사항    
  >> 테이블 (Guest Table)을 등록할 수 있다.  
  >> 테이블 (Guest Table)의 목록을 조회할 수 있다.  
  >> 빈 테이블 (Guest Table) 설정 또는 해지할 수 있다.  
  >> 방문한 손님 수 (Guest Table Occupied Guest)를 입력할 수 있다.  

* **손님 테이블 (Guest Table)** 은 착석한 손님의 숫자와 **손님 테이블 상태 (Guest Table Status)** 를 갖는다.  
* **손님 테이블 (Guest Table)** 은 **손님 테이블 상태 정책 (Guest Table Status Rule)** 에 따라    
  **손님 테이블 상태 (Guest Table Status)** 를 바꿀 수 있다.  
* **손님 테이블 (Guest Table)** 에서 **주문 손님 테이블 정책 (Order Guest Table Rule)** 을 따라  
  신규 **주문 (Order)** 을 생성한다.  

### 주문 (Order)
> 요구사항  
  >> 1 개 이상의 등록된 메뉴 (Menu)로 주문 (Order)을 등록할 수 있다.  
  >> 주문(Order)의 목록을 조회할 수 있다.  
  >> 주문 상태 (Order Status)를 변경할 수 있다.  
  
* **주문 (Order)** 은 **주문 상태 (Order Status)**, 주문 일자, **주문 메뉴 (Ordered Menu)** 을 갖는다.  
* **주문 상태 (Order Status)** 가 **최종 주문 상태 (Final Order Status)** 일 경우, 변경 할 수 없다.  
* **주문 (Order)** 은 **주문 메뉴 정책 (Order Menu Rule)** 을 따라  
  **주문 메뉴 (Ordered Menu)** 을 갖는다.  

### 손님 테이블 단체 지정 (Guest Table Group)
> 요구사항  
  >> 2 개 이상의 빈 테이블 (Guest Table)을 단체로 지정 (Guest Table Group)할 수 있다.  
  >> 단체 지정 (Guest Table Group)을 해지할 수 있다.  
  
* **손님 테이블 단체 지정 (Guest Table Group)** 은 단체 지정 일자, 복수 개의 **손님 테이블 (Guest Order)** 을 갖는다.  
* **손님 테이블 단체 지정 (Guest Table Group)** 은 **손님 테이블 단체 지정 - 테이블 상태 정책 (Guest Table Group Status Rule)** 을 따라  
  **손님 테이블 (Guest Order)** 을 단체 지정할 수 있다.  
* **손님 테이블 단체 지정 (Guest Table Group)** 은 **손님 테이블 단체 지정 - 중복 방지 정책 (Guest Table Group Deduplication Rule)** 을 따라  
  **손님 테이블 (Guest Order)** 을 단체 지정할 수 있다.  
* **손님 테이블 단체 지정 (Guest Table Group)** 은 **손님 테이블 단체 지정 - 해지 정책 (Guest Table Group Termination Rule)** 을 따라  
  기존에 단체 지정된 **손님 테이블 (Guest Order)** 을 해지 할 수 있다.  