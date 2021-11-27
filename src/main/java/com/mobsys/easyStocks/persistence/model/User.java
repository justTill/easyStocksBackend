package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String mail;

    @Column
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_id", referencedColumnName = "watchlist_id")
    private String watchlistId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}