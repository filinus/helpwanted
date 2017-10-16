package us.filin.helpwanted.jpa;

import us.filin.helpwanted.model.ProjectModel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project implements Persistent {
  
  @Id
  private Long id;
  
  private String description;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
}
