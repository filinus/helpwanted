package us.filin.helpwanted.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import us.filin.helpwanted.jpa.BidRequest;
import us.filin.helpwanted.pojo.BidRequestPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BidRequestMapper extends BasicMapper<BidRequest, BidRequestPOJO> {
  public static final BidRequestMapper INSTANCE = Mappers.getMapper(BidRequestMapper.class);
}

