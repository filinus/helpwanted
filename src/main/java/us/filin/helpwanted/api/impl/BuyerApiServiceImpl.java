package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.PersistenceListener;
import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.BuyerProject;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.jpa.User;
import us.filin.helpwanted.jpa.UserProjectKey;
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
    
    @Override
    public Response bidBuyerProject(String username, UUID projectId, BidRequestPOJO body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response bookmarkBuyerProject(String username, UUID projectId, SecurityContext securityContext) throws NotFoundException {
    
        User buyer = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
          .setParameter("username", username).getSingleResult();
        Project project = em.find(Project.class, projectId);
        
        UserProjectKey userProjectKey = new UserProjectKey(buyer.getId().toString(), projectId.toString());
        try {
            em.getTransaction().begin();
            
            BuyerProject buyerProject = new BuyerProject(userProjectKey, false);
            em.persist(buyerProject);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.find(BuyerProject.class, userProjectKey);
        }
        em.close();
        
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
