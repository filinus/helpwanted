package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.PersistenceListener;
import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.mapping.ProjectMapper;
import us.filin.helpwanted.model.*;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import javax.persistence.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class MarketApiServiceImpl extends MarketApiService {
    
    @Override
    public Response findProjectsByStatus( @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
        EntityManager em = PersistenceListener.createEntityManager();
        
        List<Project> projects = em.createQuery("SELECT p FROM Project p ORDER BY P.updated DESC, P.id", Project.class)
          .setMaxResults(100)
          .getResultList();
        List<ProjectJson> projectJsons = ProjectMapper.INSTANCE.toModels(projects);

        return Response.ok().entity(projectJsons).build();
    }
    @Override
    public Response getProjectById(Long projectId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
}
