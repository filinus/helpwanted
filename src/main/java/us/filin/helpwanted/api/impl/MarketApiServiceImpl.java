package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.BidRequest;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.mapping.ProjectDetailMapper;
import us.filin.helpwanted.mapping.ProjectMapper;
import us.filin.helpwanted.pojo.*;

import java.util.List;
import java.util.UUID;
import us.filin.helpwanted.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class MarketApiServiceImpl extends AbstractApiService implements MarketApiService {
    
    @Override
    public Response findProjectsByStatus( @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
        
        List<Project> projects = em().createQuery("SELECT p FROM Project p WHERE p.visibilityStatus = :visibility ORDER BY P.updated DESC, P.id", Project.class)
          .setParameter("visibility", Project.VisibilityStatus.VISIBLE)
          .setMaxResults(1000)
          .getResultList();
        //List<ProjectDetailPOJO> projectJsons = ProjectDetailMapper.INSTANCE.toPOJOs(projects);
        List<ProjectPOJO> projectJsons = ProjectMapper.INSTANCE.toPOJOs(projects);

        return Response.ok().entity(projectJsons).build();
    }
    @Override
    public Response getProjectById(UUID projectId, SecurityContext securityContext) throws NotFoundException {
//TODO: could and should be done by single *QL request
        
        Project project = em().createQuery("SELECT p FROM Project p WHERE p.id = :id AND p.visibilityStatus = :visibility", Project.class)
          .setParameter("id", projectId.toString().toUpperCase())
          .setParameter("visibility", Project.VisibilityStatus.VISIBLE)
          .getSingleResult();
        
//        BidRequest bidRequest =
//          em().createQuery(
//            "SELECT min(b.price)" +
//              "FROM BidRequest b " +
//              "WHERE b.project = :project_id " +
//              "GROUP BY b.bidder " +
//              "ORDER BY b.bidded DESC"
//            , BidRequest.class)
//            .setParameter("project_id", projectId)
//            .getSingleResult();
        
        ProjectDetailPOJO projectDetailPOJO = ProjectDetailMapper.INSTANCE.toPOJO(project);
        BidRequest bidRequest = project.getBidRequest();
        if (bidRequest!=null) {
            projectDetailPOJO.setWinningPrice(bidRequest.getPrice());
        }
        
        return Response.ok().entity(projectDetailPOJO).build();
    }
}
