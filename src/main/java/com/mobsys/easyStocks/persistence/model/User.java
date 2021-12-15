package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {

    public User() {

    }

    public User(final String mail, final String password, final UUID watchlistId) {
        this.mail = mail;
        this.password = password;
        this.watchlistId = watchlistId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String mail;

    @Column
    private String password;

    @Column
    private UUID watchlistId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlistId", referencedColumnName = "watchlistId")
    private List<WatchlistData> watchlistData;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<WatchlistData> getWatchlistData() {
        return watchlistData;
    }

    public void setWatchlistData(final List<WatchlistData> watchlistData) {
        this.watchlistData = watchlistData;
    }

    public UUID getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(final UUID watchlistId) {
        this.watchlistId = watchlistId;
    }
}