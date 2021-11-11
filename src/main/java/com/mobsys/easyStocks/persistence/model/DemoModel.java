package com.mobsys.easyStocks.persistence.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "model")
public class DemoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
    private Integer id;

    @Column
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
