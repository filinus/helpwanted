package us.filin.helpwanted.api;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.pojo.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import us.filin.helpwanted.pojo.ProjectDetailPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class MarketApiService {
    public abstract Response findProjectsByStatus( @NotNull List<String> status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getProjectById(String projectId,SecurityContext securityContext) throws NotFoundException;
}
