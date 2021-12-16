package com.mobsys.easyStocks.models.dtos;

public class AuthResponseDto {
    Integer id;
    String username;
    String watchlistId;
    String token;

    public AuthResponseDto(Integer id, String username, String watchlistId, String token) {
        this.id = id;
        this.username = username;
        this.watchlistId = watchlistId;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(String watchlistId) {
        this.watchlistId = watchlistId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
