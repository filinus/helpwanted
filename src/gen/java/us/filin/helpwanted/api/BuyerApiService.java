package us.filin.helpwanted.api;

import us.filin.helpwanted.pojo.BidPOJO;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class BuyerApiService {
    public abstract Response bidBuyerProject(String username, String projectId, BidPOJO body, SecurityContext securityContext) throws NotFoundException;
    public abstract Response bookmarkBuyerProject(String username,String projectId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteOrder(String username,String projectId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getBuyerProject(String username,String projectId,SecurityContext securityContext) throws NotFoundException;
}
