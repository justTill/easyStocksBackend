package com.mobsys.easyStocks.alphavantage;

import com.mobsys.alphavantage.api.DefaultApi;
import com.mobsys.alphavantage.invoker.ApiClient;
import com.mobsys.alphavantage.model.DailyDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AlphavantageApi extends DefaultApi {
    private final Logger logger = LoggerFactory.getLogger(AlphavantageApi.class);
    private final AtomicInteger minuteQuotaCounter = new AtomicInteger(0);
    private final int MAX_MINUTE_QUOTA = 5;

    private final AtomicInteger waitCount = new AtomicInteger();
    private final int MAX_WAIT_COUNT = 13;

    private AlphavantageApi(final String apiKey) {
        super();
        final WebClient overridenWebClient = getApiClient().getWebClient().mutate()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 1024)).build();
        this.setApiClient(new ApiClient(overridenWebClient));
        getApiClient().setApiKey(apiKey);
    }

    @Autowired
    public AlphavantageApi(final Environment env) {
        this(env.getProperty("stock_apikey"));
    }

    public Mono<DailyDataResponse> getDailyData(final String symbol, final String outputSize, final String datatype) {
        logger.info("Getting data for {}...", symbol);
        checkQuotaLimitSync();
        return queryGet(symbol, "TIME_SERIES_DAILY_ADJUSTED", outputSize, datatype);
    }

    private void checkQuotaLimitSync() {
        while (minuteQuotaCounter.get() >= MAX_MINUTE_QUOTA) {
            if (waitCount.get() >= MAX_WAIT_COUNT) {
                logger.error("Max wait count for quota reached. Will not retry.");
                waitCount.set(0);
                return;
            }
            try {
                waitCount.incrementAndGet();
                logger.info("Quota limit reached, waiting 10 seconds");
                TimeUnit.SECONDS.sleep(10);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitCount.set(0);
        minuteQuotaCounter.incrementAndGet();
        logger.info("Quota remaining: {}", MAX_MINUTE_QUOTA - minuteQuotaCounter.get());
    }

    /**
     * This is run async
     */
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    void resetCounter() {
        if (minuteQuotaCounter.get() > 0) {
            minuteQuotaCounter.set(0);
        }
    }
}
