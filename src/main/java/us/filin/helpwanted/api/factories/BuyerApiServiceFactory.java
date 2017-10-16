package us.filin.helpwanted.api.factories;

import us.filin.helpwanted.api.BuyerApiService;
import us.filin.helpwanted.api.impl.BuyerApiServiceImpl;


public class BuyerApiServiceFactory {
    private final static BuyerApiService service = new BuyerApiServiceImpl();

    public static BuyerApiService getBuyerApi() {
        return service;
    }
}
