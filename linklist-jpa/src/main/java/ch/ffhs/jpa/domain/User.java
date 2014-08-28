package ch.ffhs.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="user")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Column(length=50, nullable=false)
  private String username;
  @Column(length=50, nullable=false)
  private String firstname;
  @Column(length=50, nullable=false)
  private String lastname;
  @Column(length=255, nullable=false)
  private String email;
  @OneToOne
  private Role role;

  public int getId() {
	return id;
  }

  public void setId(int id) {
	this.id = id;
  }

  public String getUsername() {
	return username;
  }

  public void setUsername(String username) {
	this.username = username;
  }

  public String getFirstname() {
	return firstname;
  }

  public void setFirstname(String firstname) {
	this.firstname = firstname;
  }

  public String getLastname() {
	return lastname;
  }

  public void setLastname(String lastname) {
	this.lastname = lastname;
  }

  public String getEmail() {
	return email;
  }

  public void setEmail(String email) {
	this.email = email;
  }

  public Role getRole() {
	return role;
  }

  public void setRole(Role role) {
	this.role = role;
  }
}
