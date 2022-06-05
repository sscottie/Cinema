package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "file_info")
public class FileInfo extends AbstractModel {

	public static final int TYPE_LENGTH = 255;
	public static final int NAME_LENGTH = 255;

	/** Тип */
	@Column(nullable = false)
	private String type;

	/** Размер */
	@Column(nullable = false)
	private Long size;

	/** Наименование */
	@Column(nullable = false)
	private String name;
}
