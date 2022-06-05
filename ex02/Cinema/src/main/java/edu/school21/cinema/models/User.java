package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class User extends AbstractModel {
}
