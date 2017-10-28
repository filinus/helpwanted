package us.filin.helpwanted.mapping;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import us.filin.helpwanted.jpa.Identified;
import us.filin.helpwanted.jpa.Timestamped;

import java.util.List;
import java.util.UUID;

public abstract class BasicMapper<Persitent extends Timestamped, Pojo> {

  @Mappings({})
  public abstract Pojo toPOJO(Persitent persitent);

  @Mappings({})
  public abstract Persitent toPeristent(Pojo pojo);

  @Mappings({})
  public abstract List<Pojo> toPOJOs(List<Persitent> persitentEntities);

  @Mappings({})
  public abstract List<Persitent> toPersistents(List<Pojo> pojos);
  
  @Mapping(source = "id", target = "id", resultType = UUID.class)
  public UUID toUUID(String value) {
    return UUID.fromString(value);
  }
  
  @Mapping(source = "id", target = "id", resultType = String.class)
  public String toString(UUID value) {
    return value.toString().toUpperCase();
  }
  
}
