package us.filin.helpwanted.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.pojo.ProjectDetailPOJO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ProjectDetailMapper extends BasicMapper<Project, ProjectDetailPOJO> {
  ProjectDetailMapper INSTANCE = Mappers.getMapper(ProjectDetailMapper.class);
}

