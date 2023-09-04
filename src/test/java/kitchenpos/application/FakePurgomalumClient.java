package kitchenpos.application;

import kitchenpos.external.PurgomalumClient;
import kitchenpos.menu.application.MenuPurgomalumClient;
import kitchenpos.product.application.ProductPurgomalClient;

import java.util.Arrays;
import java.util.List;

public class FakePurgomalumClient implements PurgomalumClient, ProductPurgomalClient, MenuPurgomalumClient {
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
