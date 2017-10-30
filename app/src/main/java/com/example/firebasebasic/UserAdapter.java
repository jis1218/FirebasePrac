package com.example.firebasebasic;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-10-30.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {
    String current_name;
    // 리사이클러뷰 아답터 규현
    ArrayList<User> list = new ArrayList<>();
    TransferID transferID;

    public UserAdapter(TransferID transferID) {
        //this.list = list;
        //Log.d("list size", String.valueOf(list.size()));
        this.transferID = transferID;
    }

    @Override
    public int getItemCount() {
        Log.d("언제 호출??", String.valueOf(list.size()));
        return list.size();
    }

    public void refreshList(ArrayList<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //holder.tvID.setText(list.get(position).user_id);
        holder.tvID.setText(list.get(position).username);
        holder.tvName.setText(list.get(position).email);
        holder.tvAge.setText(list.get(position).age);
        holder.strID = list.get(position).username;
        //current_name = list.get(position).username;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvName, tvAge;
        String strID;

        public MyHolder(final View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), strID, Toast.LENGTH_SHORT).show();
                    transferID.transferID(strID);
                    transferID.renewList();


                }
            });
        }
    }

    interface TransferID {
        void transferID(String ID);
        void renewList();
    }
}






