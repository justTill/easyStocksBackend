package com.mobsys.easyStocks.persistence.repository;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Date;

public class MockData {

    String name;

    String sector;

    String datum;

    float close;

    public MockData (String _name,String _sector,String _datum,float _close){

        name = _name;
        sector = _sector;
        datum = _datum;
        close = _close;

    }
}
