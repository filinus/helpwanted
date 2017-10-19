package us.filin.helpwanted.api;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class MarketApiService {
    public abstract Response findProjectsByStatus( @NotNull List<String> status,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getProjectById(String projectId,SecurityContext securityContext) throws NotFoundException;
}
