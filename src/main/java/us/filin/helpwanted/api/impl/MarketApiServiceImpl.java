package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.model.*;

import us.filin.helpwanted.model.Project;

import java.util.Collections;
import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public class MarketApiServiceImpl extends MarketApiService {
    @Override
    public Response findProjectsByStatus( @NotNull List<String> status, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        
        Project project = new Project();
        project.description("some description");
        
        return Response.ok().entity(Collections.singletonList(project)).build();
    }
    @Override
    public Response getProjectById(Long projectId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
