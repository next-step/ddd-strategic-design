package kitchenpos.application;

import kitchenpos.client.purgomalum.PurgomalumClient;
import kitchenpos.menu.application.port.out.PurgomalumChecker;

public class FakeMenuPurgomalumChecker implements PurgomalumChecker {
	private final PurgomalumClient purgomalumClient;

	public FakeMenuPurgomalumChecker() {
		this.purgomalumClient = new FakePurgomalumClient();
	}

	@Override
	public boolean containsProfanity(final String text) {
		return purgomalumClient.containsProfanity(text);
	}
}
