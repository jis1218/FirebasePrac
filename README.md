##### 생성자를 만들 때 주의할 점

##### 생성자를 아래와 같이 넣어줬더니 실행이 안되었다.

```java
public UserAdapter(ArrayList<User> list) {
    this.list = list;
    Log.d("list size", String.valueOf(list.size()));
}
```

##### 그 이유는 생성자를 생성할 때 list에 아무것도 안들어갔기 때문이며
##### list.size()가 호출될 때 list가 null값이므로 오류가 생긴 것이다.
##### 생성자를 생성할 때는 항상 null 값을 넘겨주지 말자


##### FireBase에서 객체 가져오는 방법
```java
FirebaseDatabase database;
DatabaseReference bbsRef;
DatabaseReference userRef;

database = FirebaseDatabase.getInstance();
bbsRef = database.getReference("bbs");
userRef = database.getReference("user");
```
##### key가 "user"인 database를 가지고 온다. userRef = database.getReference("user");

```java
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

```

##### DataSnapshot은 userRef가 가지고 있는 데이터를 통째로 가지고 있으며 User.class가 가지고 있는 field의 타입대로 데이터베이스 변수에 저장되어야 한다.

##### 굳이 List나 Hashmap으로 데이터베이스에 넣지 않고 클래스를 넣어줘도 firebase는 그 필드값을 인식한다.
