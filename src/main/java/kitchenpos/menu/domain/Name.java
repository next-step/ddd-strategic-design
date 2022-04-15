package kitchenpos.menu.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {

	@Column(name = "name", nullable = false)
	private final String value;

	public Name(String name) {
		this.value = validateName(name);
	}

	private String validateName(String name) {
		if (Objects.isNull(name) || name.isEmpty()) {
			throw new IllegalArgumentException();
		}

		return name;
	}

	public String getValue() {
		return value;
	}
}
