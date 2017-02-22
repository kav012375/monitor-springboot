package com.monitor.service.task.dto;

/**
 * Created by Zeus Feng on 2017/2/22.
 *
 * @author Zeus Feng
 * @date 2017/02/22
 */
public class TaskDTO {
    String mediaName;
    String username;
    String password;
    String positions;
    String actions;
    String runtimes;
    String mgroup;
    String projectName;
    String looptype;
    String loopruntimes;
    String artType;
    String ipFilter;
    String id;

   public TaskDTO(){
        this.mediaName = "default";
        this.username = "default";
        this.password = "default";
        this.positions = "default";
        this.actions = "default";
        this.runtimes = "default";
        this.mgroup = "default";
        this.projectName = "default";
        this.loopruntimes = "default";
        this.looptype = "default";
        this.artType = "null";
        this.ipFilter = "true";
        this.id = "0";
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getRuntimes() {
        return runtimes;
    }

    public void setRuntimes(String runtimes) {
        this.runtimes = runtimes;
    }

    public String getMgroup() {
        return mgroup;
    }

    public void setMgroup(String mgroup) {
        this.mgroup = mgroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLooptype() {
        return looptype;
    }

    public void setLooptype(String looptype) {
        this.looptype = looptype;
    }

    public String getLoopruntimes() {
        return loopruntimes;
    }

    public void setLoopruntimes(String loopruntimes) {
        this.loopruntimes = loopruntimes;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getIpFilter() {
        return ipFilter;
    }

    public void setIpFilter(String ipFilter) {
        this.ipFilter = ipFilter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
