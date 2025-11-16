package com.cs1530.coursereview.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrators")
public class Administrator extends User {

    private Boolean monitor = false;

    public Boolean getMonitor() {
        return monitor;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }

    public Boolean monitorContent() {
        return monitor;
    }
}
