package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private UUID uuid;

	@PrePersist
	protected void prePersist() {
		getUuid();
	}

	public UUID getUuid() {
		if (uuid == null) {
			uuid = UUID.randomUUID();
		}
		return uuid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !this.getClass().isInstance(o)) {
			return false;
		}
		AbstractModel that = (AbstractModel) o;
		return Objects.equals(getUuid(), that.getUuid());
	}

	@Override
	public int hashCode() {
		return getUuid().hashCode();
	}
}
