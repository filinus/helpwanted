package us.filin.helpwanted.jpa;

import lombok.*;
import javax.persistence.*;

@Builder @AllArgsConstructor
@Entity @Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "buyer_projects")
public class BuyerProject extends Timestamped {
  
  //@OneToOne(optional = false, cascade = CascadeType.PERSIST)
  //@JoinColumn(name = "project_id", nullable=false, updatable=false)
  @Id
  private String projectId;
  
  //@OneToOne(optional = false, cascade = CascadeType.PERSIST)
  //@JoinColumn(name = "bidder_id", nullable=false, updatable=false)
  @Id
  private String bidderId;
  
  private boolean visible;
}
