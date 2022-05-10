package com.labor.entity;

/**
 * @author BoCong
 * @date 2022/5/10
 */
public class ProgameCompany {
    private static final long serialVersionUID = 1L;
    private Long ID;
    private String progameCompanyName;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getProgameCompanyName() {
        return progameCompanyName;
    }

    public void setProgameCompanyName(String progameCompanyName) {
        this.progameCompanyName = progameCompanyName;
    }
}
