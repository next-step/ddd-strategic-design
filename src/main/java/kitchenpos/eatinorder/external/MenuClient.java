package kitchenpos.eatinorder.external;

import java.util.UUID;

// EatInOrder 컨텍스트에서 Menu 컨텍스트에 접근하기 위한 ACL 인터페이스
public interface MenuClient {
    MenuDTO getMenuById(UUID menuId);
} 