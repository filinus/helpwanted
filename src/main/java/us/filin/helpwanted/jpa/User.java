package us.filin.helpwanted.jpa;

import lombok.*;
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
  
  private String username;
  
  private String firstName;
  
  private String lastName;
}
