package com.mobsys.easyStocks.models.dtos;


public class NotificationsDto {
    private String symbol;
    private String name;
    private Integer interval;
    private Float percentage;

    public NotificationsDto(final String symbol, final String name, final Integer interval, final Float percentage) {
        this.symbol = symbol;
        this.name = name;
        this.interval = interval;
        this.percentage = percentage;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(final Integer interval) {
        this.interval = interval;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(final Float percentage) {
        this.percentage = percentage;
    }
}
