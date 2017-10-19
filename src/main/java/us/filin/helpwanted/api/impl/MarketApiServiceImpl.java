package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.PersistenceListener;
import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.mapping.ProjectDetailMapper;
import us.filin.helpwanted.model.*;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class MarketApiServiceImpl extends MarketApiService {
    EntityManager em = PersistenceListener.createEntityManager();
    
    @Override
    public Response findProjectsByStatus( @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
        
        List<Project> projects = em.createQuery("SELECT p FROM Project p WHERE p.visibilityStatus = :visibility ORDER BY P.updated DESC, P.id", Project.class)
          .setParameter("visibility", Project.VisibilityStatus.VISIBLE)
          .setMaxResults(1000)
          .getResultList();
        List<ProjectDetailJson> projectJsons = ProjectDetailMapper.INSTANCE.toModels(projects);

        return Response.ok().entity(projectJsons).build();
    }
    @Override
    public Response getProjectById(String projectId, SecurityContext securityContext) throws NotFoundException {

        Project project = em.createQuery("SELECT p FROM Project p WHERE p.id = :id AND p.visibilityStatus = :visibility", Project.class)
          .setParameter("id", projectId)
          .setParameter("visibility", Project.VisibilityStatus.VISIBLE)
          .setMaxResults(1)
          .getSingleResult();
          
        ProjectDetailJson projectDetailJson = ProjectDetailMapper.INSTANCE.toModel(project);
        
        return Response.ok().entity(projectDetailJson).build();
    }
}
