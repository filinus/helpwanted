package us.filin.helpwanted.api.factories;

import us.filin.helpwanted.api.MarketApiService;
import us.filin.helpwanted.api.impl.MarketApiServiceImpl;


public class MarketApiServiceFactory {
    private final static MarketApiService service = new MarketApiServiceImpl();

    public static MarketApiService getMarketApi() {
        return service;
    }
}
