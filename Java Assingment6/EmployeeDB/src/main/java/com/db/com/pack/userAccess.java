package com.db.com.pack;

public class userAccess {
        private String user;
        private  String password;
public userAccess(String user,String password){
    this.user = user;
    this.password = password;

}
    public String getUser(){
    return  user;
    }

    public String getPassword() {
        return password;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    @Override
        public String toString() {
            return "UserAccesss{" +
                    "user'" + user + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
