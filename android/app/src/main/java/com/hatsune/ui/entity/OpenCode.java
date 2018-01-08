package com.hatsune.ui.entity;

public class OpenCode {
    private String auditdate;
    private String moneys;
    private String nums;
    private String sales;
    private String at;
    private String gname;
    private String gid;
    private String codes;
    private String pid;
    private String et;
    private String pools;
    private String bt;

    public OpenCode() {
    }

    public OpenCode(String auditdate, String moneys, String nums, String sales, String at, String gname, String gid, String codes, String pid, String et, String pools, String bt) {
        this.auditdate = auditdate;
        this.moneys = moneys;
        this.nums = nums;
        this.sales = sales;
        this.at = at;
        this.gname = gname;
        this.gid = gid;
        this.codes = codes;
        this.pid = pid;
        this.et = et;
        this.pools = pools;
        this.bt = bt;
    }

    public String getAuditdate() {
        return auditdate;
    }

    public void setAuditdate(String auditdate) {
        this.auditdate = auditdate;
    }

    public String getMoneys() {
        return moneys;
    }

    public void setMoneys(String moneys) {
        this.moneys = moneys;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public String getPools() {
        return pools;
    }

    public void setPools(String pools) {
        this.pools = pools;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }
}
