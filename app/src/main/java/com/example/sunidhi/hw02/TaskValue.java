package com.example.sunidhi.hw02;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Sunidhi on 11-Mar-18.
 */

public class TaskValue implements Comparable<TaskValue>, Serializable {
    String task, date, time, priority;

    public TaskValue(String task, String date, String time, String priority) {
        this.task = task;
        this.date = date;
        this.time = time;
        this.priority = priority;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(@NonNull TaskValue taskValue) {
        return 0;
    }
}
