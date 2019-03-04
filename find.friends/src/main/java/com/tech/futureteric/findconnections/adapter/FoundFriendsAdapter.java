package com.tech.futureteric.findconnections.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tech.futureteric.backend.model.Friend;
import com.tech.futureteric.findconnections.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.tech.futureteric.backend.utils.user.UserFriendsUtils.addFriendToUserContacts;
import static com.tech.futureteric.sharedcomponents.utils.IconUtils.getAddFriendDrawable;
import static com.tech.futureteric.sharedcomponents.utils.IconUtils.getPersonDrawable;

public class FoundFriendsAdapter extends RecyclerView.Adapter<FoundFriendsAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Friend> mFriends;

    public FoundFriendsAdapter(Context context, List<Friend> friends) {
        mFriends = friends;
        mContext = context;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView friendPhoto_iv;
        TextView friendContactData_tv;
        ImageButton addFriend_ib;

        ViewHolder(View view) {
            super(view);
            friendPhoto_iv = view.findViewById(R.id.imageView_friendPhoto);
            friendContactData_tv = view.findViewById(R.id.textView_friendContactData);
            addFriend_ib = view.findViewById(R.id.imageButton_addFriend);
            addFriend_ib.setImageDrawable(getAddFriendDrawable(mContext));
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mFriends.get(position).getProfilePhoto() == null)
            holder.friendPhoto_iv.setImageDrawable(getPersonDrawable(mContext));
        else
            holder.friendPhoto_iv.setImageBitmap(mFriends.get(position).getProfilePhoto());

        holder.friendContactData_tv.setText(mFriends.get(position).getName());

        // TODO check friend is already added
        /*if (mFriends.get(position).isThisCurrentUser()){
            // TODO re-enable if isThisCurrentUser() add btn GONE
            holder.addFriend_ib.setVisibility(View.VISIBLE);

        } else*/
            holder.addFriend_ib.setOnClickListener(v -> {
                Log.e("Z_", "herexxxxxxx");
                addFriendToUserContacts(mFriends.get(position));
            });

    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }
}
