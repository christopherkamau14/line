package com.high.school.library.model;

import javax.persistence.*;

@Entity
@Table(name="library_shelves")
@SequenceGenerator(name="shelfseq", initialValue=1, allocationSize=1)
public class Shelves {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shelfseq")
    @Column(name = "shelf_id")
    private Long shelfId;

    @Column(name = "shelf_name")
    private String shelfName;

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }
}
