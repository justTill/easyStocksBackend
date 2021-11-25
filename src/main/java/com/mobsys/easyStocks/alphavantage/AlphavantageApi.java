package com.mobsys.easyStocks.alphavantage;

import java.util.concurrent.TimeUnit;

import com.mobsys.alphavantage.api.DefaultApi;
import com.mobsys.alphavantage.invoker.ApiClient;
import com.mobsys.alphavantage.model.DailyDataResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class AlphavantageApi extends DefaultApi {
    private Logger logger = LoggerFactory.getLogger(AlphavantageApi.class);

    private int minuteQuotaCounter = 0;
    private final int MAX_MINUTE_QUOTA = 5;

    private int waitCount = 0;
    private final int MAX_WAIT_COUNT = 10;

    private AlphavantageApi(String apiKey) {
        super();
        WebClient overridenWebClient = getApiClient().getWebClient().mutate()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 1024)).build();
        this.setApiClient(new ApiClient(overridenWebClient));
        getApiClient().setApiKey(apiKey);
    }

    @Autowired
    public AlphavantageApi(Environment env) {
        this(env.getProperty("stock_apikey"));
    }

    public Mono<DailyDataResponse> getDailyData(String symbol, String outputSize, String datatype)
            throws WebClientResponseException {
        logger.info("Getting data for {}...", symbol);
        checkQuotaLimitSync();
        return queryGet(symbol, "TIME_SERIES_DAILY_ADJUSTED", outputSize, datatype);
    }

    private void checkQuotaLimitSync() {
        while (minuteQuotaCounter >= MAX_MINUTE_QUOTA) {
            if (waitCount >= MAX_WAIT_COUNT) {
                throw new WebClientResponseException("Max wait count for quota reached. Will not retry.",
                        HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
            }
            try {
                waitCount++;
                logger.debug("Quota limit reached, waiting 10 seconds");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitCount = 0;
        minuteQuotaCounter++;
        logger.debug("Quota remaining: {}", MAX_MINUTE_QUOTA - minuteQuotaCounter);
    }

    /**
     * This is run async
     */
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    private void resetCounter() {
        if (minuteQuotaCounter > 0) {
            minuteQuotaCounter = 0;
        }
    }
}
