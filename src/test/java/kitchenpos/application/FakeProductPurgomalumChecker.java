package kitchenpos.application;

import kitchenpos.client.purgomalum.PurgomalumClient;
import kitchenpos.product.application.port.out.PurgomalumChecker;

public class FakeProductPurgomalumChecker implements PurgomalumChecker {

	private final PurgomalumClient purgomalumClient;

	public FakeProductPurgomalumChecker() {
		this.purgomalumClient = new FakePurgomalumClient();
	}

	@Override
	public boolean containsProfanity(final String text) {
		return purgomalumClient.containsProfanity(text);
	}
}
