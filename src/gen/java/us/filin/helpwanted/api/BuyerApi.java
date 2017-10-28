package us.filin.helpwanted.api;

import us.filin.helpwanted.pojo.*;
import us.filin.helpwanted.api.BuyerApiService;
import us.filin.helpwanted.api.factories.BuyerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import us.filin.helpwanted.pojo.BidRequestPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;
import java.util.UUID;

import java.util.Map;
import java.util.List;
import us.filin.helpwanted.api.ApiException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/buyer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the buyer API")

public class BuyerApi  {
   private final BuyerApiService delegate;

   public BuyerApi(@Context ServletConfig servletConfig, @Context ServletContext servletContext) {
      BuyerApiService delegate = null;

      if (servletConfig != null) {
         String implClass = servletConfig.getInitParameter("BuyerApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (BuyerApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = BuyerApiServiceFactory.getBuyerApi();
      }

      delegate.setServletContext(servletContext);
      this.delegate = delegate;
   }

    @PUT
    @Path("/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Bid on bookmarked project", notes = "", response = ProjectPOJO.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Project not found", response = Void.class) })
    public Response bidBuyerProject(@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") UUID projectId
,@ApiParam(value = "Bid on project" ,required=true) BidRequestPOJO body
,@Context SecurityContext securityContext)
    throws ApiException {
        return delegate.bidBuyerProject(projectId,body,securityContext);
    }
    @POST
    @Path("/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Bookmark a market project", notes = "", response = ProjectPOJO.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Project not found", response = Void.class) })
    public Response bookmarkBuyerProject(@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") UUID projectId
,@Context SecurityContext securityContext)
    throws ApiException {
        return delegate.bookmarkBuyerProject(projectId,securityContext);
    }
    @DELETE
    @Path("/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete not-active project by ID", notes = "Buyers may delete projects they bid. If projects won, it must be delivered. Delivered projects are moved to archive", response = Void.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Order not found", response = Void.class) })
    public Response deleteBuyerProjectBookmark(@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") UUID projectId
,@Context SecurityContext securityContext)
    throws ApiException {
        return delegate.deleteBuyerProjectBookmark(projectId,securityContext);
    }
    @GET
    @Path("/projects")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Buyer's projects", notes = "All those projects buyer's bid", response = ProjectPOJO.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findBuyersProjects(@ApiParam(value = "Status values that need to be considered for filter",required=true, allowableValues="active, published, finished") @QueryParam("status") List<String> status
,@Context SecurityContext securityContext)
    throws ApiException {
        return delegate.findBuyersProjects(status,securityContext);
    }
    @GET
    @Path("/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get buyers bid status in specified project", notes = "Buyers may track projects where they bid or just bookmarked", response = ProjectPOJO.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Order not found", response = Void.class) })
    public Response getBuyerProject(@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") UUID projectId
,@Context SecurityContext securityContext)
    throws ApiException {
        return delegate.getBuyerProject(projectId,securityContext);
    }
}
