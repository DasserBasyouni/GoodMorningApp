package com.tech.futureteric.lockscreenui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.futureteric.lockscreenui.R;
import com.tech.futureteric.backend.model.Message;

import java.util.List;

public class MessagesRV extends RecyclerView.Adapter<MessagesRV.ViewHolder> {

    private final List<Message> mMessages;

    public MessagesRV(List<Message> messagesList) {
        mMessages = messagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message currentMessage = mMessages.get(position);

        holder.msg_tv.setText(currentMessage.getMsg());

        String senderName = currentMessage.getSenderName();

        // TODO handle this more pro
        if (senderName.equals("null"))
            senderName = "Unknown Friend";

        holder.senderName_tv.setText(senderName);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView senderName_tv, msg_tv;

        ViewHolder(View itemView) {
            super(itemView);
            senderName_tv = itemView.findViewById(R.id.textView_senderName);
            msg_tv = itemView.findViewById(R.id.textView_msg);
        }
    }
}