package us.filin.helpwanted.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projects",
  indexes = {
    @Index(name = "time_desc", columnList = "created DESC,id")
  }
)
public class Project extends Persistent {
  
  @OneToOne(optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "owner_id", nullable=false, updatable=false)
  private User owner;
  
  @OneToOne(optional = true, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "bid_id", nullable=true, updatable=true)
  private Bid bid;
  
  private String title;
  
  private String description;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start", nullable = false)
  private Date start;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "finish", nullable = false)
  private Date finish;
  
  public enum ProjectStatus {
    INACTIVE,  // publisher editing it
    ACTIVE_NO_BIDS_YET,  // publisher made project public
    ACTIVE_BID,  // project was active but did
    PENDING, // we give up to minute to process last second bid
    WINNER,
    CLOSED
  }
  
  @Enumerated(EnumType.STRING) // I want to make it obvious
  @Column(nullable = false, updatable = true)
  private ProjectStatus status;
  
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
  
  public Date getStart() {
    return start;
  }
  
  public void setStart(Date start) {
    this.start = start;
  }
  
  public Date getFinish() {
    return finish;
  }
  
  public void setFinish(Date finish) {
    this.finish = finish;
  }
  
  public ProjectStatus getStatus() {
    return status;
  }
  
  public void setStatus(ProjectStatus status) {
    this.status = status;
  }
}
