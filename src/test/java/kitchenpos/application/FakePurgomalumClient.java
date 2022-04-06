package kitchenpos.application;

import kitchenpos.menu.application.port.out.MenuPurgomalumClient;
import kitchenpos.product.application.port.out.ProductPurgomalumClient;

import java.util.Arrays;
import java.util.List;

public class FakePurgomalumClient implements ProductPurgomalumClient, MenuPurgomalumClient {
    private static final List<String> profanities;

    static {
        profanities = Arrays.asList("비속어", "욕설");
    }

    @Override
    public boolean containsProfanity(final String text) {
        return profanities.stream()
                          .anyMatch(profanity -> text.contains(profanity));
    }
}
