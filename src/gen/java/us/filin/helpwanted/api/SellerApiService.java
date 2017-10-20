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

public abstract class SellerApiService {
    public abstract Response addSellerProject(String username,ProjectPOJO body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteProjectImage(String username,UUID projectId,String imageId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteSellerProject(String username,UUID projectId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getSellerProjects(String username,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateSellerProject(String username,UUID projectId,ProjectPOJO body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response uploadFile(String username,UUID projectId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
}
