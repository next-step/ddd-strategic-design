package kitchenpos.infra.adapter.out.web;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import kitchenpos.menu.application.port.out.MenuPurgomalumClient;
import kitchenpos.product.application.port.out.ProductPurgomalumClient;

@Component
public class DefaultPurgomalumClient implements ProductPurgomalumClient, MenuPurgomalumClient {
    private final RestTemplate restTemplate;

    public DefaultPurgomalumClient(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public boolean containsProfanity(final String text) {
        final URI url = UriComponentsBuilder.fromUriString("https://www.purgomalum.com/service/containsprofanity")
                                            .queryParam("text", text)
                                            .build()
                                            .toUri();
        return Boolean.parseBoolean(restTemplate.getForObject(url, String.class));
    }
}
