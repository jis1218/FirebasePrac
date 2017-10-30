package com.example.firebasebasic;

/**
 * Created by 정인섭 on 2017-10-30.
 */

public class Bbs {
    public String id;
    public String title;
    public String date;
    public String content;
    public String user_id;

    public Bbs(){
        //Firebase에서 parsing 할 때 한번 호출한다.
    }

    public Bbs(String id, String title, String date, String content, String user_id) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.user_id = user_id;
    }
}
