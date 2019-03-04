package com.tech.futureteric.goodmorning.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.futureteric.goodmorning.R;
import com.tech.futureteric.goodmorning.adapter.PendingMessageRVAdapter;
import com.tech.futureteric.goodmorning.model.PendingMessage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PendingMessageFragment extends Fragment {


    public PendingMessageFragment() {}


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_morning_message, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new PendingMessageRVAdapter(PendingMessage.ITEMS));
        }

        return view;
    }

}
