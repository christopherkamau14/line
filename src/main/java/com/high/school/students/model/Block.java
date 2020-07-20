package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="current_block")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "block_id")
    private Long blockCode;

    @Column(name = "block_name")
    private String blockName;

    @Column(name = "block_desc")
    private String blockDesc;

    public Block(Long block) {
        this.blockCode=block;
    }

    public Block() {

    }

    public Long getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(Long blockCode) {
        this.blockCode = blockCode;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockDesc() {
        return blockDesc;
    }

    public void setBlockDesc(String blockDesc) {
        this.blockDesc = blockDesc;
    }
}
