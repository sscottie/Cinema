package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "hall")
public class Hall extends AbstractModel {

	@Column(nullable = false)
	private Integer seatsCount;
}
