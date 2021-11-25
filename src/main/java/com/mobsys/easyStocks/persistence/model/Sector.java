package com.mobsys.easyStocks.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sectors")
public class Sector implements Serializable {

    @Id
    @GeneratedValue
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameDe() {
        return nameDe;
    }

    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }
}