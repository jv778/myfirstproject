package com.example.findaparttime.data;

import java.util.List;

public class DataFile {
    private String job_title;
    private String job_description;
    private String job_timings;
    private String job_salary;
    private Double latitude_val;
    private Double longitude_val;
    private String email_id;
    public DataFile(String job_title, String job_description, String job_timings, String job_salary,Double latitude_val,Double longitude_val,String email_id) {
        this.job_title = job_title;
        this.job_description = job_description;
        this.job_timings = job_timings;
        this.job_salary = job_salary;
        this.latitude_val = latitude_val;
        this.longitude_val = longitude_val;
        this.email_id = email_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public Double getLongitude_val() {
        return longitude_val;
    }

    public void setLongitude_val(Double longitude_val) {
        this.longitude_val = longitude_val;
    }

    public Double getLatitude_val() {
        return latitude_val;
    }

    public void setLatitude_val(Double latitude_val) {
        this.latitude_val = latitude_val;
    }



    public DataFile() {
    }

    public String getFname1() {
        return job_title;
    }

    public void setFname1(String job_title) {
        this.job_title = job_title;
    }

    public String getEmail1() {
        return job_description;
    }

    public void setEmail1(String job_description) {
        this.job_description = job_description;
    }

    public String getPass1() {
        return job_timings;
    }

    public void setPass1(String job_timings) {
        this.job_timings = job_timings;
    }

    public String getPhn1() {
        return job_salary;
    }

    public void setPhn1(String job_salary) {
        this.job_salary = job_salary;
    }

}
