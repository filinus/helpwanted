package us.filin.helpwanted.mapping;

import org.mapstruct.Mappings;
import us.filin.helpwanted.jpa.Persistent;

import java.util.List;

interface BasicMapper<Persitent extends Persistent, Pojo> {

  @Mappings({})
  Pojo toPOJO(Persitent persitent);

  @Mappings({})
  Persitent toPeristent(Pojo pojo);

  @Mappings({})
  List<Pojo> toPOJOs(List<Persitent> persitentEntities);

  @Mappings({})
  List<Persitent> toPersistents(List<Pojo> pojos);

}
