package com.bentie.listatareas.model;

import java.util.Date;

public class Task {

    private Integer id;
    private int urgency;
    private String name;
    private String description;
    private Date finishDate;

    public Task() {
    }

    public Task(Integer id, String name, String description, Date finishDate, int urgency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.finishDate = finishDate;
        this.urgency = urgency;
    }

    public Task(int urgency, String name, String description, Date finishDate) {
        this.urgency = urgency;
        this.name = name;
        this.description = description;
        this.finishDate = finishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }
}
