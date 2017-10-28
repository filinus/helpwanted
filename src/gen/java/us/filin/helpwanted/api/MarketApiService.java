package us.filin.helpwanted.api;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.pojo.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import us.filin.helpwanted.pojo.ProjectDetailPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;
import java.util.UUID;

import java.util.List;
import us.filin.helpwanted.api.ApiException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public interface MarketApiService extends ApiServiceInContext {
    public Response findProjectsByStatus( @NotNull List<String> status,SecurityContext securityContext) throws ApiException;
    public Response getProjectById(UUID projectId,SecurityContext securityContext) throws ApiException;
}
