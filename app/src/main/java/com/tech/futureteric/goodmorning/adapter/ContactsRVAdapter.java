package com.tech.futureteric.goodmorning.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tech.futureteric.backend.model.Friend;
import com.tech.futureteric.goodmorning.R;
import com.tech.futureteric.lockscreenui.ui.LockScreenBuilder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.tech.futureteric.sharedcomponents.utils.IconUtils.getPersonDrawable;


public class ContactsRVAdapter extends RecyclerView.Adapter<ContactsRVAdapter.ViewHolder> {

    private final List<Friend> mFriends;

    public ContactsRVAdapter(List<Friend> friends) {
        mFriends = friends;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mContactName_tv;
        final ImageView mContactPhoto_iv;

        ViewHolder(View view) {
            super(view);
            mContactName_tv = view.findViewById(R.id.textView_contactName);
            mContactPhoto_iv = view.findViewById(R.id.imageView_contactPhoto);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mContactName_tv.setText(mFriends.get(position).getName());

        Friend currentFriend = mFriends.get(position);

        Bitmap photo = currentFriend.getProfilePhoto();
        if (photo == null)
            holder.mContactPhoto_iv.setImageDrawable(getPersonDrawable(holder.itemView.getContext()));
        else
            holder.mContactPhoto_iv.setImageBitmap(currentFriend.getProfilePhoto());

        holder.itemView.setOnClickListener(v -> {
            // TODO handle how many messages user can send
            LockScreenBuilder.buildPreview(holder.itemView.getContext(),
                    currentFriend.getUid(),currentFriend.getToken());
        });
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

}
