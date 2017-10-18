package us.filin.helpwanted.api;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import us.filin.helpwanted.model.ApiResponseJson;
import java.io.File;
import us.filin.helpwanted.model.ProjectJson;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class SellerApiService {
    public abstract Response addSellerProject(String username,ProjectJson body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteProjectImage(String username,String projectId,String imageId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteSellerProject(String username,String projectId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getSellerProjects(String username,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateSellerProject(String username,String projectId,ProjectJson body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response uploadFile(String username,String projectId,String additionalMetadata,InputStream fileInputStream, FormDataContentDisposition fileDetail,SecurityContext securityContext) throws NotFoundException;
}
