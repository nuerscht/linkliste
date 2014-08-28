package ch.ffhs.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="linklist")
public class LinkList {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Column(length=255, nullable=false, unique=false)
  private String name;
  @Column(length=1024, nullable=false, unique=true)
  private String url;
  @OneToOne
  private User insertuser;
  @OneToOne(optional=true)
  private User updateuser;
  @Temporal(TemporalType.TIMESTAMP)
  private Date inserttime;
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatetime;
  @Column
  private int state;

  public int getId() {
	return id;
  }

  public void setId(int id) {
	this.id = id;
  }

  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public String getUrl() {
	return url;
  }

  public void setUrl(String url) {
	this.url = url;
  }

  public User getInsertuser() {
	return insertuser;
  }

  public void setInsertuser(User insertuser) {
	this.insertuser = insertuser;
  }

  public User getUpdateuser() {
	return updateuser;
  }

  public void setUpdateuser(User updateuser) {
	this.updateuser = updateuser;
  }

  public Date getInserttime() {
	return inserttime;
  }

  public void setInserttime(Date inserttime) {
	this.inserttime = inserttime;
  }

  public Date getUpdatetime() {
	return updatetime;
  }

  public void setUpdatetime(Date updatetime) {
	this.updatetime = updatetime;
  }

  public int getState() {
	return state;
  }

  public void setState(int state) {
	this.state = state;
  }

}
