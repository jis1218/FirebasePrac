package com.example.firebasebasic;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-10-30.
 */

public class BbsAdapter extends RecyclerView.Adapter<MyNewHolder> {
    // 리사이클러뷰 아답터 규현
    ArrayList<User> list = new ArrayList<>();

    public BbsAdapter(ArrayList<User> list) {
        this.list = list;
        Log.d("list size", String.valueOf(list.size()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyNewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new MyNewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyNewHolder holder, int position) {
        //holder.tvID.setText(list.get(position).user_id);
        holder.tvID.setText(list.get(position).username);
        holder.tvName.setText(list.get(position).email);
        holder.tvAge.setText(list.get(position).age);
    }


}

class MyNewHolder extends RecyclerView.ViewHolder{
    TextView tvID, tvName, tvAge;
    public MyNewHolder(View itemView) {
        super(itemView);
        tvID = itemView.findViewById(R.id.tvID);
        tvName = itemView.findViewById(R.id.tvName);
        tvAge = itemView.findViewById(R.id.tvAge);
    }
}
