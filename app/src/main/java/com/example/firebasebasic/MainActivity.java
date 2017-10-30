package com.example.firebasebasic;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.TransferID {

    FirebaseDatabase database;
    DatabaseReference bbsRef;
    DatabaseReference userRef;
    private EditText editID, editName, editAge, editTitle;
    RecyclerView userrecyclerView, bbsrecyclerView;
    UserAdapter userAdapter;
    Button btnPost, btnSignUp;
    List<User> data;
    HashMap<String, Bbs> bbsList;
    String current_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDatabase();
        initView();

        userAdapter = new UserAdapter(this);
        userrecyclerView.setAdapter(userAdapter);
        userrecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        getDataFromDB();

        btnListner();
    }

    @Override
    public void transferID(String ID) {
        current_ID = ID;
    }

    @Override
    public void renewList() {
        bbsList = new HashMap<>();
    }

    private void setDatabase() {
        database = FirebaseDatabase.getInstance();
        bbsRef = database.getReference("bbs");
        userRef = database.getReference("user");

    }


    private void getDataFromDB() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    Log.d("User Name", user.username);
                    data.add(user);
                }
                Log.d("data size", String.valueOf(data.size()));
                userAdapter.refreshList((ArrayList) data);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void btnListner() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strID = editID.getText().toString();
                String strName = editName.getText().toString();
                String strAge = editAge.getText().toString();

                User user = new User(strID, strAge, strName);
                userRef.child(strID).setValue(user);

                //userAdapter.notifyDataSetChanged();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                Long time = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH mm ss");
                Bbs bbs = new Bbs();
                bbs.date = sdf.format(time);
                bbs.title = title;

                userRef.child(current_ID).child(bbs.date).setValue(bbs);
                bbsRef.child(current_ID).child(bbs.date).setValue(bbs);



            }
        });
    }

    private void initView() {
        editAge = (EditText) findViewById(R.id.editAge);
        editName = (EditText) findViewById(R.id.editName);
        editID = (EditText) findViewById(R.id.editID);
        userrecyclerView = (RecyclerView) findViewById(R.id.userrecyclerView);
        bbsrecyclerView = (RecyclerView) findViewById(R.id.bbsrecyclerView);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        editTitle = (EditText) findViewById(R.id.editTitle);
        btnPost = (Button) findViewById(R.id.btnPost);
    }


//    private void writeNewUser(String userId, String name, int age, String email) {
//        User user = new User(name, age, email);
//
//        rootRef.child("users") // 레퍼런스를 가져오고
//                .child(userId)  // 추가될 노드를 생성
//                .setValue(user); // 추가된 노드의 값을 입력
//
//
//    }

//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            textView.setText("");
//            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                String msg = snapshot.getValue().toString();
//                textView.setText(textView.getText()+ "\n" + msg);
//            }
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    };

//    public void buttonListner(){
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                send();
//            }
//        });
//    }

//    public void send() {
//        String msg = editText.getText().toString();
//        if(msg==null||"".equals(msg)){ // 키의 값에 아무것도 없으면 생성이 안되므로 임의값을 넣어준다.
//            msg="none";
//        }
//        String key = myRef.push().getKey();
//        Log.d("key", key);
//
//        myRef.child(key).setValue(msg);
//    }

//    private void initView() {
//        editText = (EditText) findViewById(R.id.editText);
//        textView = (TextView) findViewById(R.id.textView);
//        button = (Button) findViewById(R.id.button);
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        buttonListner();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        myRef.removeEventListener(valueEventListener);
//
//    }
}
