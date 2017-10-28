package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.*;
import us.filin.helpwanted.mapping.BidRequestMapper;

import us.filin.helpwanted.pojo.BidRequestPOJO;

import java.util.List;
import java.util.UUID;

import us.filin.helpwanted.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class BuyerApiServiceImpl extends AbstractApiService implements BuyerApiService {

    
    @Override
    public Response bidBuyerProject(UUID projectId, BidRequestPOJO body, SecurityContext securityContext) throws NotFoundException {
        if (body == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "bid payload lost")).build();
        }
        
        BidRequest bidRequest = BidRequestMapper.INSTANCE.toPeristent(body);
        
        setupCurrentUser(securityContext);
        
        bidRequest.setUserId(user.getId());
        Project project = getNotUserProject(projectId);
        
        if (bidRequest.getPrice().compareTo(project.getBidRequest().getPrice()) > 1) {
            return Response.status(Response.Status.ACCEPTED)
              .entity(new ApiResponseMessage(ApiResponseMessage.OK, "bid considered but don't win"))
              .build();
        }

        bidRequest.setProjectId(project.getId());
        
        int updated = em().createQuery(
          "UPDATE Project p " +
            "SET p.bidRequest = :bidRequest " +
            "WHERE p.id = :requestId " +
            "AND p.bidRequest != :bidRequest " + // prevent write written
            "AND p.visibilityStatus = Project.VisibilityStatus.VISIBLE " +
            "AND p.owner.id != :bidderId " + // do not bid on own projects anyway
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
        } else if (updated == 1) {
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "your bid won")).build();
        }
        return Response.status(Response.Status.ACCEPTED).entity(new ApiResponseMessage(ApiResponseMessage.OK, "bid entered for processing")).build();
    }
    
    @Override
    public Response bookmarkBuyerProject(UUID projectId, SecurityContext securityContext) throws NotFoundException {
        setupCurrentUser(securityContext);
        
        Project project = getProject(projectId);
        
        String buyerId = user.getId();
        
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
            UserProjectKey userProjectKey = new UserProjectKey(buyerId, project.getId());
            em().find(BuyerProject.class, userProjectKey);
        }
        
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "hghgjhgh")).build();
    }
    @Override
    public Response deleteBuyerProjectBookmark(UUID projectId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response findBuyersProjects(@NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getBuyerProject(UUID projectId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
