package com.debayanapps.firebaseauthentication1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.debayanapps.firebaseauthentication1.R;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private Context mContext;
    private List<User> mUsers;

    private UserAdapter(Context mContext,List<User> mUser){
        this.mContext=mContext;
        this.mUsers=mUser;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user=mUsers.get(position);

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView username34;
        private CircleImageView profile2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username34=itemView.findViewById(R.id.username34);
            profile2=itemView.findViewById(R.id.profile2);
        }
    }
}
