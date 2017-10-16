package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.mapping.ProjectMapper;
import us.filin.helpwanted.model.*;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;


import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@Stateless
public class MarketApiServiceImpl extends MarketApiService {
    @PersistenceContext(unitName = "the-unit")
    EntityManager entityManager;
    
    @Override
    public Response findProjectsByStatus( @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
        Project project = new Project();
        project.setDescription("description");
        try {
            
            EntityManager entityManager = getEntityManager();
    
            entityManager.persist(project);
            
            List<Project> projects = entityManager.createQuery("SELECT p FROM Project p", Project.class)
              .setMaxResults(100)
              .getResultList();
            List<ProjectModel> projectModels = ProjectMapper.INSTANCE.toModels(projects);
    
            return Response.ok().entity(projectModels).build();
        } catch (NamingException e) {
            return Response.serverError().entity(e).build();
        }
    }
    @Override
    public Response getProjectById(Long projectId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    
    
    protected EntityManager getEntityManager() throws NamingException {
        boolean isLoaded = Persistence.getPersistenceUtil().isLoaded(org.eclipse.persistence.jpa.PersistenceProvider.class);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("the-unit");
        return emf.createEntityManager();
    }
}
