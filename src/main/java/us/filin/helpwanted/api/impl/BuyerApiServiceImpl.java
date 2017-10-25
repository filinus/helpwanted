package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.BidRequest;
import us.filin.helpwanted.jpa.BuyerProject;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.jpa.User;
import us.filin.helpwanted.jpa.UserProjectKey;
import us.filin.helpwanted.mapping.BidRequestMapper;

import us.filin.helpwanted.pojo.BidRequestPOJO;

import java.util.List;
import java.util.UUID;

import us.filin.helpwanted.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class BuyerApiServiceImpl extends AbstractApiService implements BuyerApiService {

    
    private Project getUserProject(UUID projectId) throws NotFoundException {
        Project project = em().createQuery(
          "SELECT p "+
          "FROM Project p " +
          "WHERE p.id = :project_id " +
          "AND p.visibilityStatus = Project.VisibilityStatus.VISIBLE " +
          "AND p.owner.id != :buyer_id",
          Project.class)
          .setParameter("project_id", projectId.toString().toUpperCase())
          .setParameter("buyer_id", user.getId())
          .getSingleResult();
        
        if (project == null) {
            throw new NotFoundException(404, "project "+projectId+" not found or is not available for user acrivity");
        }
        return project;
    }
    
    
    @Override
    public Response bidBuyerProject(String username, UUID projectId, BidRequestPOJO body, SecurityContext securityContext) throws NotFoundException {
        if (body == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "bid payload lost")).build();
        }
        
        BidRequest bidRequest = BidRequestMapper.INSTANCE.toPeristent(body);
        
        setupCurrentUser(securityContext);
        
        bidRequest.setUserId(user.getId());
        Project project = getUserProject(projectId);

        bidRequest.setProjectId(project.getId());
        
        em().getTransaction().begin();
    
        int updated = em().createQuery(
          "UPDATE Project p " +
            "SET p.bidRequest = :bidRequest " +
            "WHERE p.id = :requestId " +
            "AND p.bidRequest != :bidRequest " + // prevent write written
            "AND p.visibilityStatus = Project.VisibilityStatus.VISIBLE " +
            "AND p.owner.id != :bidderId " + // do not bid on own projects
            "AND :price < (SELECT min(price) FROM BidRequest WHERE projectId = :projectId )"
          )
          .setParameter("bidRequest", bidRequest.getId())
          .setParameter("projectId", project.getId())
          .setParameter("bidderId", user.getId())
          .executeUpdate();
        if (updated == 0 ) {
            Response.status(Response.Status.CONFLICT)
              .entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "bid declined or not won"))
              .build();
        }
        
    
        em().getTransaction().commit();
        
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response bookmarkBuyerProject(String username, UUID projectId, SecurityContext securityContext) throws NotFoundException {
        String projId = projectId.toString().toUpperCase();
        
        User buyer = em().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
          .setParameter("username", username).getSingleResult();
        Project project = em().find(Project.class, projId);
        
        String buyerId = buyer.getId();
        
        try {
            em().getTransaction().begin();
            
            BuyerProject buyerProject = BuyerProject.builder()
              .projectId(projectId.toString().toUpperCase())
              .bidderId(buyerId)
              .visible(false)
              .build();
            em().persist(buyerProject);
            em().getTransaction().commit();
        } catch (Exception e) {
            UserProjectKey userProjectKey = new UserProjectKey(buyerId, projId);
            em().find(BuyerProject.class, userProjectKey);
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
