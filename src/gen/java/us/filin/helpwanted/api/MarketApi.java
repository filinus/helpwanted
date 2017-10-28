package us.filin.helpwanted.api;

import us.filin.helpwanted.pojo.*;
import us.filin.helpwanted.api.MarketApiService;
import us.filin.helpwanted.api.factories.MarketApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import us.filin.helpwanted.pojo.ProjectDetailPOJO;
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

@Path("/market")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the market API")

public class MarketApi  {
   private final MarketApiService delegate;

   public MarketApi(@Context ServletConfig servletConfig, @Context ServletContext servletContext) {
      MarketApiService delegate = null;

      if (servletConfig != null) {
         String implClass = servletConfig.getInitParameter("MarketApi.implementation");
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

      delegate.setServletContext(servletContext);
      this.delegate = delegate;
   }

    @GET
    @Path("/projects")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds Projects by criteria", notes = "Multiple status values can be provided with comma separated strings", response = ProjectPOJO.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "helpwanted_auth", scopes = {
            @io.swagger.annotations.AuthorizationScope(scope = "write:projects", description = "modify projects in your account"),
            @io.swagger.annotations.AuthorizationScope(scope = "bid:projects", description = "bookmark projects and bid")
        })
    }, tags={ "market", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation", response = ProjectPOJO.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
    public Response findProjectsByStatus(@ApiParam(value = "Status values that need to be considered for filter",required=true, allowableValues="active, published, finished") @QueryParam("status") List<String> status
,@Context SecurityContext securityContext)
    throws ApiException {
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
    public Response getProjectById(@ApiParam(value = "ID of project to return",required=true) @PathParam("projectId") UUID projectId
,@Context SecurityContext securityContext)
    throws ApiException {
        return delegate.getProjectById(projectId,securityContext);
    }
}
