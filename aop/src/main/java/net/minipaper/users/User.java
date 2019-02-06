package net.minipaper.users;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private long idx;

  @Column
  private String email;

  @Column
  private String name;

  public User(String email, String name) {
    this.email = email;
    this.name = name;
  }
}
