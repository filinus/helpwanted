package us.filin.helpwanted.api;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import us.filin.helpwanted.model.ProjectModel;

import java.util.List;
import us.filin.helpwanted.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

public abstract class BuyerApiService {
    public abstract Response bidBuyerProject(String username,String orderId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response bookmarkBuyerProject(String username,String orderId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteOrder(String username,String orderId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getBuyerProject(String username,String orderId,SecurityContext securityContext) throws NotFoundException;
}
