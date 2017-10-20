package us.filin.helpwanted.mapping;

import org.mapstruct.Mappings;
import us.filin.helpwanted.jpa.Identified;

import java.util.List;
import java.util.UUID;

public abstract class BasicMapper<Persitent extends Identified, Pojo> {

  @Mappings({})
  public abstract Pojo toPOJO(Persitent persitent);

  @Mappings({})
  public abstract Persitent toPeristent(Pojo pojo);

  @Mappings({})
  public abstract List<Pojo> toPOJOs(List<Persitent> persitentEntities);

  @Mappings({})
  public abstract List<Persitent> toPersistents(List<Pojo> pojos);
  
  public UUID map(String value) {
    return UUID.fromString(value);
  }
  
  public String map(UUID value) {
    return value.toString();
  }
  

}
