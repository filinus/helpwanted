package us.filin.helpwanted.api;

import us.filin.helpwanted.api.factories.BuyerApiServiceFactory;

import io.swagger.annotations.ApiParam;

import us.filin.helpwanted.pojo.BidPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/buyer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the buyer API")

public class BuyerApi  {
   private final BuyerApiService delegate;

   public BuyerApi(@Context ServletConfig servletContext) {
      BuyerApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("BuyerApi.implementation");
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

      this.delegate = delegate;
   }

    @PUT
    @Path("/{username}/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Bid on project", notes = "", response = ProjectPOJO.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Project not found", response = Void.class) })
    public Response bidBuyerProject(@ApiParam(value = "The buyer's username",required=true) @PathParam("username") String username
,@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") String projectId
,@ApiParam(value = "Bid on project" ,required=true) BidPOJO body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.bidBuyerProject(username,projectId,body,securityContext);
    }
    @POST
    @Path("/{username}/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Bookmark project", notes = "", response = ProjectPOJO.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Project not found", response = Void.class) })
    public Response bookmarkBuyerProject(@ApiParam(value = "The buyer's username",required=true) @PathParam("username") String username
,@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") String projectId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.bookmarkBuyerProject(username,projectId,securityContext);
    }
    @DELETE
    @Path("/{username}/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete not-active project by ID", notes = "Buyer may delete project without his bid, delivered", response = Void.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Order not found", response = Void.class) })
    public Response deleteOrder(@ApiParam(value = "The buyer's username",required=true) @PathParam("username") String username
,@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") String projectId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteOrder(username,projectId,securityContext);
    }
    @GET
    @Path("/{username}/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get buyers bid status in specified project", notes = "", response = ProjectPOJO.class, tags={ "buyer", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Order not found", response = Void.class) })
    public Response getBuyerProject(@ApiParam(value = "The buyer's username",required=true) @PathParam("username") String username
,@ApiParam(value = "an existing project ID",required=true) @PathParam("projectId") String projectId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getBuyerProject(username,projectId,securityContext);
    }
}
