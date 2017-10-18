package us.filin.helpwanted.jpa;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@UuidGenerator(name="uuid-gen")
public abstract class Persistent {
  
  @Id
  @GeneratedValue(generator = "uuid-gen")
  @Column(name = "id", nullable = false)
  private String id;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created", nullable = false)
  private Date created;
  
  @Temporal(TemporalType.TIMESTAMP)
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
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
}
