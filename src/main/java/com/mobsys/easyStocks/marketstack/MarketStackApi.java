package com.mobsys.easyStocks.marketstack;

import com.mobsys.marketstack.api.DefaultApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MarketStackApi extends DefaultApi {
    private MarketStackApi(String apiKey) {
        super();
        getApiClient().setApiKey(apiKey);
    }

    @Autowired
    public MarketStackApi(Environment env) {
        this(env.getProperty("marketstack_apikey"));
    }
}
