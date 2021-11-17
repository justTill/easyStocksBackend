package com.mobsys.easyStocks.marketstack;

import com.mobsys.marketstack.api.DefaultApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class CustomApi extends DefaultApi {

    @Autowired
    private Environment env;

    public CustomApi() {
        super();
        getApiClient().setApiKey(env.getProperty("marketstackApiKey"));
    }
}
