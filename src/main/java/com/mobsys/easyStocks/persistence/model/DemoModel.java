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

    @Column
    private String sector;


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public void setSector(final String sector) {
        this.sector = sector;
    }
}
