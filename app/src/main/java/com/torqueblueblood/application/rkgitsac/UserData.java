package com.torqueblueblood.application.rkgitsac;

public class UserData {
    public String username;
    public String useremail;
    public String userphone;
    public String gender;
    public String rollno;
    public String event_det;

    public  UserData(){

    }
    public  UserData(String username){
        this.username=username;
    }
    public UserData(String username, String useremail, String userphone,String gender) {
        this.username = username;
        this.useremail = useremail;
        this.userphone=userphone;
        this.gender=gender;
    }
    public UserData(String username, String useremail, String userphone,String gender,String rollno,String event_det) {
        this.username = username;
        this.useremail = useremail;
        this.userphone=userphone;
        this.gender=gender;
        this.rollno=rollno;
        this.event_det=event_det;
    }


    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getEvent_det() {
        return event_det;
    }

    public void setEvent_det(String event_det) {
        this.event_det = event_det;
    }

    public String getUsername() {
        return username;
    }

    public String getUseremail() {
        return useremail;
    }

    public String getUserphone() {
        return userphone;
    }

    public String getGender() {
        return gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
