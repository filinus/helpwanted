package us.filin.helpwanted.mapping;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.pojo.ProjectDetailPOJO;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class  ProjectDetailMapper extends BasicMapper<Project, ProjectDetailPOJO> {
  public static final ProjectDetailMapper INSTANCE = Mappers.getMapper(ProjectDetailMapper.class);
  
  @AfterMapping
  void putWinningPrice(Project project, @MappingTarget ProjectDetailPOJO pojo) {
    pojo.setWinningPrice ((project.getBidRequest() != null) ? project.getBidRequest().getPrice() : null);
  }
  
}

