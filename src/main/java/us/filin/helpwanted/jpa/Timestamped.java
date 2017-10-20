package us.filin.helpwanted.jpa;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass @Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Timestamped {
  
  @javax.persistence.Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created", nullable = false)
  private Date created;
  
  @javax.persistence.Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated", nullable = false)
  private Date updated;
  
  @PrePersist
  protected void onCreate() {
    updated = created = new Date();
  }
  
  @PreUpdate
  protected void onUpdate() {
    updated = new Date();
  }

}
