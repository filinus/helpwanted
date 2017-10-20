package us.filin.helpwanted.jpa;

import lombok.*;
import javax.persistence.*;

@Builder @AllArgsConstructor
@Entity @Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "buyer_projects")
public class BuyerProject extends Timestamped {
  
  @EmbeddedId
  private UserProjectKey userProjectKey;
  
  private boolean visible;
}
