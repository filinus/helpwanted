package us.filin.helpwanted.jpa;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Builder @AllArgsConstructor
@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "bids")
public class BidRequest extends Identified {
  
  //@OneToOne(optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "project_id", nullable=false, updatable=false)
  private String projectId;
  
  //@OneToOne(optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "bidder_id", nullable=false, updatable=false)
  private String userId;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "bidded", nullable = false)
  private Date bidded;
  
  @Column(nullable = false, updatable = false)
  private double quantity;
  
  @Column(nullable = false, updatable = false)
  private BigDecimal pricePerUnit;
  
  @Column(nullable = false, updatable = false)
  private BigDecimal price;
  
}
