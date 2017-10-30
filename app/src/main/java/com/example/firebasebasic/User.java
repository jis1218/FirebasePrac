package com.example.firebasebasic;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 정인섭 on 2017-10-30.
 */

public class User {
    public String username;
    public String age;
    public String email;
    public String user_id;
    public Bbs bbs;

    //내가 작성한 글 목록


    public User(){

    }

    public User(String username, String age, String email){
        this.username = username;
        this.age = age;
        this.email = email;
    }
}
