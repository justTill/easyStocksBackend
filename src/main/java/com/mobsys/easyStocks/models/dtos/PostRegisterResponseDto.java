package com.mobsys.easyStocks.models.dtos;

public class PostRegisterResponseDto {
    Integer id;
    String username;
    String watchlistId;

    public PostRegisterResponseDto() {
    }

    public PostRegisterResponseDto(final Integer id, final String username, final String watchlistId) {
        this.id = id;
        this.username = username;
        this.watchlistId = watchlistId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(final String watchlistId) {
        this.watchlistId = watchlistId;
    }
}
