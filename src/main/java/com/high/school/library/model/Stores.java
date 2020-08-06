package com.high.school.library.model;

import javax.persistence.*;

@Entity
@Table(name="stores_table")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class Stores {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name="library_stores")
    private Long storeCode;

    @Column(name="store_name")
    private String storeName;

    @Column(name="store_stationery")
    private String stationery;

    public Long getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Long storeCode) {
        this.storeCode = storeCode;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStationery() {
        return stationery;
    }

    public void setStationery(String stationery) {
        this.stationery = stationery;
    }
}
