package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.PersistenceListener;
import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.mapping.ProjectDetailMapper;
import us.filin.helpwanted.pojo.*;

import us.filin.helpwanted.pojo.ProjectPOJO;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;
import java.util.UUID;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class SellerApiServiceImpl extends SellerApiService {
    EntityManager em = PersistenceListener.createEntityManager();
    
    @Override
    public Response addSellerProject(String username, ProjectPOJO body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteProjectImage(String username, UUID projectId, String imageId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteSellerProject(String username, UUID projectId, SecurityContext securityContext) throws NotFoundException {
        int result = em.createQuery("DELETE FROM Project p WHERE p.id = :project_id AND p.owner.username = :username")
            .setParameter("project_id", projectId.toString().toUpperCase())
            .setParameter("username", username)
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
    public Response getSellerProjects(String username, SecurityContext securityContext) throws NotFoundException {
        List<Project> projects = em.createQuery("SELECT p FROM Project p WHERE p.owner.username = :username ORDER BY P.updated DESC, P.id", Project.class)
          .setParameter("username", username)
          .setMaxResults(1000)
          .getResultList();
        List<ProjectDetailPOJO> projectJsons = ProjectDetailMapper.INSTANCE.toPOJOs(projects);
    
        return Response.ok().entity(projectJsons).build();
    }
    @Override
    public Response updateSellerProject(String username, UUID projectId, ProjectPOJO body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response uploadFile(String username, UUID projectId, String additionalMetadata, InputStream fileInputStream, FormDataContentDisposition fileDetail, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
