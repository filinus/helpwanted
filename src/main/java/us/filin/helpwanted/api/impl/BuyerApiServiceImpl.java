package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.PersistenceListener;
import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.BidRequest;
import us.filin.helpwanted.jpa.BuyerProject;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.jpa.User;
import us.filin.helpwanted.jpa.UserProjectKey;
import us.filin.helpwanted.mapping.BidRequestMapper;
import us.filin.helpwanted.pojo.*;

import us.filin.helpwanted.pojo.BidRequestPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;

import java.util.List;
import java.util.UUID;

import us.filin.helpwanted.api.NotFoundException;


import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class BuyerApiServiceImpl extends BuyerApiService {
    EntityManager em = PersistenceListener.createEntityManager();
    User buyer = null;
    
    private void setupBuyer(SecurityContext securityContext) {
        String username = securityContext.getUserPrincipal().getName();
    
        buyer = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
          .setParameter("username", username)
          .getSingleResult();
    }
    
    private Project getUserProject(UUID projectId) throws NotFoundException {
        Project project = em.createQuery(
          "SELECT p "+
          "FROM Project p " +
          "WHERE p.id = :project_id " +
          "AND p.visibilityStatus = Project.VisibilityStatus.VISIBLE " +
          "AND p.owner.id != :buyer_id",
          Project.class)
          .setParameter("project_id", projectId.toString().toUpperCase())
          .setParameter("buyer_id", buyer.getId())
          .getSingleResult();
        
        if (project == null) {
            throw new NotFoundException(404, "project "+projectId+" not found or is not available for buyer acrivity");
        }
        return project;
    }
    
    
    @Override
    public Response bidBuyerProject(String username, UUID projectId, BidRequestPOJO body, SecurityContext securityContext) throws NotFoundException {
        if (body == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "bid payload lost")).build();
        }
        
        BidRequest bidRequest = BidRequestMapper.INSTANCE.toPeristent(body);
        
        setupBuyer(securityContext);
        
        bidRequest.setBidder(buyer);
        Project project = getUserProject(projectId);

        bidRequest.setProject(project);
        
        em.getTransaction().begin();
        
        
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response bookmarkBuyerProject(String username, UUID projectId, SecurityContext securityContext) throws NotFoundException {
    
        User buyer = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
          .setParameter("username", username).getSingleResult();
        Project project = em.find(Project.class, projectId.toString().toUpperCase());
        
        UserProjectKey userProjectKey = new UserProjectKey(buyer.getId(), projectId.toString().toUpperCase());
        try {
            em.getTransaction().begin();
            
            BuyerProject buyerProject = new BuyerProject(userProjectKey, false);
            em.persist(buyerProject);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.find(BuyerProject.class, userProjectKey);
        }
        
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "hghgjhgh")).build();
    }
    @Override
    public Response deleteBuyerProjectBookmark(String username, UUID projectId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response findBuyersProjects(String username,  @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getBuyerProject(String username, UUID projectId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
