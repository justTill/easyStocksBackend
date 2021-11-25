package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sectors")
public class Sector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer sectorId;

    @Column
    private String nameEn;

    @Column
    private String nameDe;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(final Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(final String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameDe() {
        return nameDe;
    }

    public void setNameDe(final String nameDe) {
        this.nameDe = nameDe;
    }
}