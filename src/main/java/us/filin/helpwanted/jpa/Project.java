package us.filin.helpwanted.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projects",
  indexes = {
    @Index(name = "time_desc", columnList = "created DESC, id"),
    @Index(name = "visibility_status", columnList = "visbilityStatus")
  }
)
public class Project extends Persistent {
  
  @OneToOne(optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "owner_id", nullable = false, updatable = false)
  private User owner;
  
  @OneToOne(optional = true, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "bid_id", nullable = true, updatable = true)
  private Bid bid;
  
  private String title;
  
  private String description;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start", nullable = false)
  private Date start;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "finish", nullable = false)
  private Date finish;
  
  @PostUpdate
  void postUpdate() {
    updateBiddingStatus();
  }
  
  @PostLoad
  void postLoad() {
    updateBiddingStatus();
  }
  
  private void updateBiddingStatus() {
    Date now = new Date();
    if (now.before(start)) {
      biddingStatus = BiddingStatus.WAITING_START;
    } else if (now.before(finish)) {
      biddingStatus = (bid == null) ? BiddingStatus.WAITING_BIDS : BiddingStatus.BIDDING;
    } else if (now.before(new Date(finish.getTime()+60*1000))) {
      biddingStatus = BiddingStatus.PENDING;
    } else {
      biddingStatus = (bid == null) ? BiddingStatus.NO_WINNER : BiddingStatus.WINNER;
    }
  }
  
  public enum VisibiltyStatus {
    HIDDEN,
    VISIBLE,
    CLOSED_MODERATOR
  }
  
  public enum BiddingStatus {
    WAITING_START,
    WAITING_BIDS,
    BIDDING,
    PENDING,
    WINNER,
    NO_WINNER
  }
  
  @Enumerated(EnumType.STRING) // I want to make it obvious
  @Column(name = "visibility_status", nullable = false, updatable = true)
  private VisibiltyStatus visibilityStatus;
  
  @Transient
  private BiddingStatus biddingStatus;
  
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
  
  public VisibiltyStatus getVisibilityStatus() {
    return visibilityStatus;
  }
  
  public void setVisibilityStatus(VisibiltyStatus visibilityStatus) {
    this.visibilityStatus = visibilityStatus;
  }
  
  public BiddingStatus getBiddingStatus() {
    return biddingStatus;
  }
}
