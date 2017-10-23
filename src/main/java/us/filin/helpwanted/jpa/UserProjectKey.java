package us.filin.helpwanted.jpa;

import lombok.*;
import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProjectKey implements Serializable {
  @Column(name = "project_id", nullable = false, updatable = false, length = 36)
  private String projectId;
  @Column(name = "user_id", nullable = false, updatable = false, length = 36)
  private String userId;
}
