package us.filin.helpwanted.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity

public class Project extends Persistent {
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private User owner;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private Bid bid;
  
  private String title;
  
  private String description;
  
  public User getOwner() {
    return owner;
  }
  
  public void setOwner(User owner) {
    this.owner = owner;
  }
  
  public Bid getBid() {
    return bid;
  }
  
  public void setBid(Bid bid) {
    this.bid = bid;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
}
