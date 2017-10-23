package us.filin.helpwanted.api;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.pojo.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import us.filin.helpwanted.pojo.BidRequestPOJO;
import us.filin.helpwanted.pojo.ProjectPOJO;
import java.util.UUID;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public interface BuyerApiService {
    public Response bidBuyerProject(String username,UUID projectId,BidRequestPOJO body,SecurityContext securityContext) throws NotFoundException;
    public Response bookmarkBuyerProject(String username,UUID projectId,SecurityContext securityContext) throws NotFoundException;
    public Response deleteBuyerProjectBookmark(String username,UUID projectId,SecurityContext securityContext) throws NotFoundException;
    public Response findBuyersProjects(String username, @NotNull List<String> status,SecurityContext securityContext) throws NotFoundException;
    public Response getBuyerProject(String username,UUID projectId,SecurityContext securityContext) throws NotFoundException;
}
