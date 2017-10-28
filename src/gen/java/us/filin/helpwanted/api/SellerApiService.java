package us.filin.helpwanted.api;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.pojo.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import us.filin.helpwanted.pojo.ApiResponsePOJO;
import java.io.File;
import us.filin.helpwanted.pojo.ProjectPOJO;
import java.util.UUID;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public interface SellerApiService extends ApiServiceInContext {
    public Response addSellerProject(ProjectPOJO body,SecurityContext securityContext) throws NotFoundException;
    public Response deleteProjectImage(UUID projectId,String imageId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
    public Response deleteSellerProject(UUID projectId,SecurityContext securityContext) throws NotFoundException;
    public Response getSellerProjects(SecurityContext securityContext) throws NotFoundException;
    public Response updateSellerProject(UUID projectId,ProjectPOJO body,SecurityContext securityContext) throws NotFoundException;
    public Response uploadFile(UUID projectId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
}
