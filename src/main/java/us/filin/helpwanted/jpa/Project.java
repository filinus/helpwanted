package us.filin.helpwanted.jpa;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Date;

@Builder @AllArgsConstructor
@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "projects",
  indexes = {
    @Index(name = "time_desc", columnList = "created DESC, id"),
    @Index(name = "visibility_status", columnList = "visbilityStatus")
  }
)
public class Project extends Identified {
  
  @OneToOne(optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "owner_id", nullable = false, updatable = false)
  private User owner;
  
  @OneToOne(optional = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "bid_id", nullable = true, updatable = true)
  private BidRequest bidRequest;
  
  private String title;
  
  private String description;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start", nullable = false)
  private Date start;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "finish", nullable = false)
  private Date finish;
  
  public enum VisibilityStatus {
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
  private VisibilityStatus visibilityStatus;
  
  @Transient
  private BiddingStatus biddingStatus;
  
  @PostConstruct
  @PostLoad
  @PostUpdate
  public void updateBiddingStatus() {
    Date now = new Date();
    if (now.before(start)) {
      biddingStatus = BiddingStatus.WAITING_START;
    } else if (now.before(finish)) {
      biddingStatus = (bidRequest == null) ? BiddingStatus.WAITING_BIDS : BiddingStatus.BIDDING;
    } else if (now.before(new Date(finish.getTime()+60*1000))) {
      biddingStatus = BiddingStatus.PENDING;
    } else {
      biddingStatus = (bidRequest == null) ? BiddingStatus.NO_WINNER : BiddingStatus.WINNER;
    }
  }
  
}
