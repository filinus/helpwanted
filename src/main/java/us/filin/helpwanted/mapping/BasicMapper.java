package us.filin.helpwanted.mapping;

import org.mapstruct.Mappings;
import us.filin.helpwanted.jpa.Persistent;

import java.util.List;

interface BasicMapper<Persitent extends Persistent, JsonModel> {

  @Mappings({})
  JsonModel toModel(Persitent persitent);

  @Mappings({})
  Persitent toPeristent(JsonModel jsonModel);

  @Mappings({})
  List<JsonModel> toModels(List<Persitent> persitentEntities);

  @Mappings({})
  List<Persitent> toPersinents(List<JsonModel> jsonModels);

}
