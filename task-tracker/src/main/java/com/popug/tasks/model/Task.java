package com.popug.tasks.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    private String id;

    private String description;

    private String publicUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicUserId() {
        return publicUserId;
    }

    public void setPublicUserId(String publicUserId) {
        this.publicUserId = publicUserId;
    }
}
