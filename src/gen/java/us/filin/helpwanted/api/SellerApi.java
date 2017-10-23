package us.filin.helpwanted.api;

import us.filin.helpwanted.pojo.*;
import us.filin.helpwanted.api.SellerApiService;
import us.filin.helpwanted.api.factories.SellerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import us.filin.helpwanted.pojo.ApiResponsePOJO;
import java.io.File;
import us.filin.helpwanted.pojo.ProjectPOJO;
import java.util.UUID;

import java.util.Map;
import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/seller")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the seller API")

public class SellerApi  {
   private final SellerApiService delegate;

   public SellerApi(@Context ServletConfig servletContext) {
      SellerApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SellerApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SellerApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = SellerApiServiceFactory.getSellerApi();
      }

      this.delegate = delegate;
   }

    @POST
    @Path("/{username}/projects/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Crteates a new project description", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "seller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    public Response addSellerProject(@ApiParam(value = "The seller's username",required=true) @PathParam("username") String username
,@ApiParam(value = "Project description object that needs to be added" ,required=true) ProjectPOJO body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addSellerProject(username,body,securityContext);
    }
    @DELETE
    @Path("/{username}/projects/{projectId}/images/{imageId}")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "delete an image", notes = "", response = ApiResponsePOJO.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "seller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ApiResponsePOJO.class) })
    public Response deleteProjectImage(@ApiParam(value = "Seller's username",required=true) @PathParam("username") String username
,@ApiParam(value = "ID of project containg image",required=true) @PathParam("projectId") UUID projectId
,@ApiParam(value = "image ID",required=true) @PathParam("imageId") String imageId
,@ApiParam(value = "Additional data to pass to server")@FormDataParam("additionalMetadata")  String additionalMetadata
,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteProjectImage(username,projectId,imageId,additionalMetadata,fileInputStream, fileDetail,securityContext);
    }
    @DELETE
    @Path("/{username}/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete an existing seller's project", notes = "Delete an existing seller's project if project hidden or finished without bids", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "seller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Project not found", response = Void.class) })
    public Response deleteSellerProject(@ApiParam(value = "The seller's username",required=true) @PathParam("username") String username
,@ApiParam(value = "A seller's existing project",required=true) @PathParam("projectId") UUID projectId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteSellerProject(username,projectId,securityContext);
    }
    @GET
    @Path("/{username}/projects/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Seller's projects", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "seller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "TODO", response = Void.class) })
    public Response getSellerProjects(@ApiParam(value = "The seller's username",required=true) @PathParam("username") String username
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSellerProjects(username,securityContext);
    }
    @PUT
    @Path("/{username}/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update an existing project description", notes = "", response = Void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "seller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Project not found", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Validation exception", response = Void.class) })
    public Response updateSellerProject(@ApiParam(value = "The seller's username",required=true) @PathParam("username") String username
,@ApiParam(value = "A seller's existing project ",required=true) @PathParam("projectId") UUID projectId
,@ApiParam(value = "Project description that needs to be updated" ,required=true) ProjectPOJO body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateSellerProject(username,projectId,body,securityContext);
    }
    @POST
    @Path("/{username}/projects/{projectId}/images")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "uploads an image", notes = "", response = ApiResponsePOJO.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "seller", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ApiResponsePOJO.class) })
    public Response uploadFile(@ApiParam(value = "Seller's username",required=true) @PathParam("username") String username
,@ApiParam(value = "ID of project to update",required=true) @PathParam("projectId") UUID projectId
,@ApiParam(value = "Additional data to pass to server")@FormDataParam("additionalMetadata")  String additionalMetadata
,
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.uploadFile(username,projectId,additionalMetadata,fileInputStream, fileDetail,securityContext);
    }
}
