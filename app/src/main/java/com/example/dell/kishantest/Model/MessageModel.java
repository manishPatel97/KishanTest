package com.example.dell.kishantest.Model;

public class MessageModel {
private String First_Name,Last_Name,Phone,Message,Date,timestamp;
    public  MessageModel(){}

    public MessageModel(String first_Name, String last_Name, String phone, String message, String date, String timestamp) {
        First_Name = first_Name;
        Last_Name = last_Name;
        Phone = phone;
        Message = message;
        Date = date;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
