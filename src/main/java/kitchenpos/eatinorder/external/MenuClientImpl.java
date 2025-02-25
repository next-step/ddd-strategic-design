package kitchenpos.eatinorder.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Component
public class MenuClientImpl implements MenuClient {
    private final RestTemplate restTemplate;
    private final String menuServiceUrl;

    // Spring Boot의 application.properties/yml에 menu.service.url을 정의해두고 주입받습니다.
    public MenuClientImpl(RestTemplate restTemplate, @Value("${menu.service.url}") String menuServiceUrl) {
        this.restTemplate = restTemplate;
        this.menuServiceUrl = menuServiceUrl;
    }

    @Override
    public MenuDTO getMenuById(UUID menuId) {
        // 예: http://menu-service/api/menus/{menuId}
        String url = menuServiceUrl + "/api/menus/" + menuId;
        return restTemplate.getForObject(url, MenuDTO.class);
    }
} 