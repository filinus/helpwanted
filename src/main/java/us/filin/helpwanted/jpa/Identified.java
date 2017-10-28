package us.filin.helpwanted.jpa;

import lombok.*;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

@MappedSuperclass @Getter @Setter @NoArgsConstructor
@UuidGenerator(name="uuid-gen")
public abstract class Identified extends Timestamped {
  
  @Id
  @GeneratedValue(generator = "uuid-gen")
  @Column(name = "id", nullable = false, updatable = false, length = 36)
  private String id;
}
