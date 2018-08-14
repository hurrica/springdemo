package com.bluepay.spring.demo.thread.file;

import org.jetbrains.annotations.NotNull;

/**
 * Created by ping.chen on 2018/8/11.
 */
public class FileData implements Comparable<FileData>{
    public FileData(String[] data){
        this.groupId = data[1];
        this.id = data[0];
        this.quota = Float.valueOf(data[2]);
    }
    private String groupId;
    private String id;
    private Float quota;

    @Override
    public int compareTo(@NotNull FileData data) {
        return Integer.parseInt(this.groupId) - Integer.parseInt(data.getGroupId());
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "groupId='" + groupId + '\'' +
                ", id='" + id + '\'' +
                ", quota=" + quota +
                '}';
    }
}
