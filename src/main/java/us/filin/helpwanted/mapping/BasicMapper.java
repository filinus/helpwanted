package us.filin.helpwanted.mapping;

import org.mapstruct.Mappings;
import us.filin.helpwanted.jpa.Persistent;

import java.util.List;

interface BasicMapper<Persitent extends Persistent, Model> {

  @Mappings({})
  Model toModel(Persitent persitent);

  @Mappings({})
  Persitent toPeristent(Model model);

  @Mappings({})
  List<Model> toModels(List<Persitent> persitentEntities);

  @Mappings({})
  List<Persitent> toPersinents(List<Model> models);

}