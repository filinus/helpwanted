package us.filin.helpwanted.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.pojo.ProjectPOJO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class ProjectMapper extends BasicMapper<Project, ProjectPOJO> {
  
  public static final ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
}

