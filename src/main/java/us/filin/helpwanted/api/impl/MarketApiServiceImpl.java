package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.*;
import us.filin.helpwanted.mapping.*;
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
        List<ProjectPOJO> projectJsons = ProjectMapper.INSTANCE.toPOJOs(projects);

        return Response.ok().entity(projectJsons).build();
    }
    @Override
    public Response getProjectById(UUID projectId, SecurityContext securityContext) throws NotFoundException {
        Project project = getProject(projectId);
        BidRequest bidRequest = project.getBidRequest();
        ProjectDetailPOJO projectDetailPOJO = ProjectDetailMapper.INSTANCE.toPOJO(project);
        if (bidRequest!=null) {
            projectDetailPOJO.setWinningPrice(bidRequest.getPrice());
        }
        
        return Response.ok().entity(projectDetailPOJO).build();
    }
}
