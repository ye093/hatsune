package com.hatsune.ui.entity;

public class HistoryOpenCode {
    private String fet;
    private String flag;
    private String pid;
    private String st;
    private String et;
    private String opencode;

    public HistoryOpenCode(String fet, String flag, String pid, String st, String et, String opencode) {
        this.fet = fet;
        this.flag = flag;
        this.pid = pid;
        this.st = st;
        this.et = et;
        this.opencode = opencode;
    }

    public String getFet() {
        return fet;
    }

    public String getFlag() {
        return flag;
    }

    public String getPid() {
        return pid;
    }

    public String getSt() {
        return st;
    }

    public String getEt() {
        return et;
    }

    public String getOpencode() {
        return opencode;
    }
}
