package us.filin.helpwanted.jpa;

import lombok.*;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder @AllArgsConstructor
@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "users")
public class User extends Identified {
  
  @Index(unique = true) //maybe in future we shall allow disable users and reuse their names
  private String username;
  
  private String firstName;
  
  private String lastName;
}
