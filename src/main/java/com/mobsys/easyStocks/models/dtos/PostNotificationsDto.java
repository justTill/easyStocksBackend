package com.mobsys.easyStocks.models.dtos;


import java.util.List;

public class PostNotificationsDto {
    private List<String> symbolsSeen;

    public List<String> getSymbolsSeen() {
        return symbolsSeen;
    }

    public void setSymbolsSeen(final List<String> symbolsSeen) {
        this.symbolsSeen = symbolsSeen;
    }
}
