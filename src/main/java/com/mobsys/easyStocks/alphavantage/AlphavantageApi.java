package com.mobsys.easyStocks.alphavantage;

import com.mobsys.alphavantage.api.DefaultApi;
import com.mobsys.alphavantage.model.DailyDataResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class AlphavantageApi extends DefaultApi {
    private AlphavantageApi(String apiKey) {
        super();
        getApiClient().setApiKey(apiKey);
    }

    @Autowired
    public AlphavantageApi(Environment env) {
        this(env.getProperty("stock_apikey"));
    }

    public Mono<DailyDataResponse> getDailyData(String symbol, String outputSize, String datatype)
            throws WebClientResponseException {
        return queryGet(symbol, "TIME_SERIES_DAILY_ADJUSTED", outputSize, datatype);
    }
}
