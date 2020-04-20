package com.example.androidproject;

public class UserProfile {
    public String userEmail;
    public String userName;
    public String passWord;

    public UserProfile(){

    }

    public UserProfile(String userEmail, String userName, String passWord ){
        this.userEmail=userEmail;
        this.userName=userName;
        this.passWord=passWord;
     }

     public  String getUserName(){
        return userName;
     }

     public void setUserName(String userName){
            this.userName= userName;
     }

     public String getUserEmail(){
        return userEmail;
     }

     public void setUserEmail(String userEmail){
            this.userEmail = userEmail;
     }

     public String getPassWord(){
        return passWord;
     }

     public void setPassWord(String passWord){
        this.passWord=passWord;
     }

}
