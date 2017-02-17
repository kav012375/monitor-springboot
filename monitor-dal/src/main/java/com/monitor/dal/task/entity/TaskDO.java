package com.monitor.dal.task.entity;

import java.io.Serializable;

/**
 * Created by zeusw on 2016/12/20.
 * @author zeusw
 */
public class TaskDO implements Serializable {
    private static final long serialVersionUID = 458045724375300033L;
    private Long id;
    private Integer status;
    private String taskContent;
    private Integer loopType;
    private Integer runTimes;
    private String mgroup;
    private String projectName;
    private Integer loopRunTimes;
    private String articleType;
    private Boolean ipFilter;

    public TaskDO(){
        this.setLoopType(100);
        this.setRunTimes(-1);
        this.setStatus(-1);
        this.setArticleType("null");
        this.setIpFilter(true);
    }

    @Override
    public String toString() {
        return "TaskDO{" +
                "id=" + id +
                ", status=" + status +
                ", taskContent='" + taskContent + '\'' +
                ", loopType=" + loopType +
                ", runTimes=" + runTimes +
                ", mgroup='" + mgroup + '\'' +
                ", projectName='" + projectName + '\'' +
                ", loopRunTimes=" + loopRunTimes +
                ", articleType='" + articleType + '\'' +
                ", ipFilter=" + ipFilter +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public int getLoopType() {
        return loopType;
    }

    public void setLoopType(int loopType) {
        this.loopType = loopType;
    }

    public int getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(int runTimes) {
        this.runTimes = runTimes;
    }

    public String getMgroup() {
        return mgroup;
    }

    public void setMgroup(String group) {
        this.mgroup = group;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getLoopRunTimes() {
        return loopRunTimes;
    }

    public void setLoopRunTimes(int loopRunTimes) {
        this.loopRunTimes = loopRunTimes;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public boolean isIpFilter() {
        return ipFilter;
    }

    public void setIpFilter(boolean ipFilter) {
        this.ipFilter = ipFilter;
    }
}
