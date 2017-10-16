package us.filin.helpwanted.api.factories;

import us.filin.helpwanted.api.SellerApiService;
import us.filin.helpwanted.api.impl.SellerApiServiceImpl;


public class SellerApiServiceFactory {
    private final static SellerApiService service = new SellerApiServiceImpl();

    public static SellerApiService getSellerApi() {
        return service;
    }
}
