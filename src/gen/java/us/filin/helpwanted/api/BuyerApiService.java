package us.filin.helpwanted.api;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import us.filin.helpwanted.model.BidJson;
import us.filin.helpwanted.model.ProjectJson;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class BuyerApiService {
    public abstract Response bidBuyerProject(String username,String projectId,BidJson body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response bookmarkBuyerProject(String username,String projectId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteOrder(String username,String projectId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getBuyerProject(String username,String projectId,SecurityContext securityContext) throws NotFoundException;
}
