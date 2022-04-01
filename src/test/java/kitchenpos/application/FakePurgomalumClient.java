package kitchenpos.application;

import kitchenpos.menu.domain.BannedMenuNameClient;
import kitchenpos.product.domain.BannedProductNameClient;

import java.util.Arrays;
import java.util.List;

public class FakePurgomalumClient implements BannedMenuNameClient, BannedProductNameClient {
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
