package us.filin.helpwanted.api;

import us.filin.helpwanted.api.factories.MarketApiServiceFactory;

import io.swagger.annotations.ApiParam;

import us.filin.helpwanted.pojo.ProjectDetailPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/market")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the market API")

public class MarketApi  {
   private final MarketApiService delegate;

   public MarketApi(@Context ServletConfig servletContext) {
      MarketApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("MarketApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (MarketApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = MarketApiServiceFactory.getMarketApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/projects")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds Projects by criteria", notes = "Multiple status values can be provided with comma separated strings", response = ProjectPOJO.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "petstore_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "market", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findProjectsByStatus(@ApiParam(value = "Status values that need to be considered for filter",required=true, allowableValues="active, published, finished") @QueryParam("status") List<String> status
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findProjectsByStatus(status,securityContext);
    }
    @GET
    @Path("/projects/{projectId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Find project by ID", notes = "Returns a single project", response = ProjectDetailPOJO.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "api_key")
    }, tags={ "market", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectDetailPOJO.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Project not found on market", response = Void.class) })
    public Response getProjectById(@ApiParam(value = "ID of project to return",required=true) @PathParam("projectId") String projectId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getProjectById(projectId,securityContext);
    }
}
