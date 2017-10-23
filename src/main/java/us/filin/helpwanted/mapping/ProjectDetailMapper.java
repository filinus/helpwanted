package us.filin.helpwanted.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.pojo.ProjectDetailPOJO;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class  ProjectDetailMapper extends BasicMapper<Project, ProjectDetailPOJO> {
  public static final ProjectDetailMapper INSTANCE = Mappers.getMapper(ProjectDetailMapper.class);
}

