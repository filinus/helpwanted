package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.*;
import us.filin.helpwanted.mapping.*;
import us.filin.helpwanted.pojo.*;

import us.filin.helpwanted.pojo.ProjectPOJO;

import java.util.List;
import us.filin.helpwanted.api.ApiException;

import java.io.InputStream;
import java.util.UUID;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.persistence.FlushModeType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class SellerApiServiceImpl extends AbstractApiService implements SellerApiService {
    @Override
    public Response addSellerProject(ProjectPOJO body, SecurityContext securityContext) throws ApiException {
        setupCurrentUser(securityContext);
        
        Project project = ProjectMapper.INSTANCE.toPeristent(body);
        em().setFlushMode(FlushModeType.AUTO);
        em().getTransaction().begin();
        em().persist(project);
        em().getTransaction().commit();
    
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, project.getId())).build();
    }
    
    @Override
    public Response deleteProjectImage(UUID projectId, String imageId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws ApiException {
        // do some magic!
        return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Hypothetical feature, not implemented")).build();
    }
    @Override
    public Response deleteSellerProject(UUID projectId, SecurityContext securityContext) throws ApiException {
        setupCurrentUser(securityContext);
        
        int result = em().createQuery("DELETE FROM Project p WHERE p.id = :project_id AND p.owner.id = :owner_id")
            .setParameter("project_id", projectId.toString().toUpperCase())
            .setParameter("owner_id", user.getId())
            .executeUpdate();
        
        switch (result) {
            case 1:
            case -1:
                return Response.ok(new ApiResponseMessage(ApiResponseMessage.OK, "removed "+projectId+" project")).build();
            case 0:
                return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "not found")).build(); //
            default:
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "file a bug")).build();
        }
    }
    @Override
    public Response getSellerProjects(SecurityContext securityContext) throws ApiException {
        setupCurrentUser(securityContext);
        
        List<Project> projects = em().createQuery(
          "SELECT p "+
          "FROM Project p "+
          "LEFT JOIN FETCH BidRequest b " +
          "WHERE p.owner.id = :user_id " +
          "ORDER BY p.updated DESC, p.id",
          Project.class)
          .setParameter("user_id", user.getId())
          .setMaxResults(1000) // TODO pagination
          .getResultList();
        List<ProjectDetailPOJO> projectJsons = ProjectDetailMapper.INSTANCE.toPOJOs(projects);
    
        return Response.ok().entity(projectJsons).build();
    }
    @Override
    public Response updateSellerProject(UUID projectId, ProjectPOJO body, SecurityContext securityContext) throws ApiException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response uploadFile(UUID projectId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws ApiException {
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Hypothetical feature, not implemented")).build();
    }
}
