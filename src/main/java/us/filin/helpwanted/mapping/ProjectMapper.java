package us.filin.helpwanted.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.model.ProjectJson;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper extends BasicMapper<Project, ProjectJson> {
  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
}

