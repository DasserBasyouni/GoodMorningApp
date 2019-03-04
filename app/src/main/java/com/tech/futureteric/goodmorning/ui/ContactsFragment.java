package com.tech.futureteric.goodmorning.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.futureteric.backend.model.Friend;
import com.tech.futureteric.goodmorning.R;
import com.tech.futureteric.goodmorning.adapter.ContactsRVAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tech.futureteric.goodmorning.Constants.BUNDLE_FRIENDS_LIST;


public class ContactsFragment extends Fragment {

    @BindView(R.id.recyclerView_contacts) RecyclerView contacts_rv;

    public ContactsFragment() {}

    public static ContactsFragment newInstance(List<Friend> friends) {
        ContactsFragment fragment = new ContactsFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_FRIENDS_LIST, (ArrayList<? extends Parcelable>) friends);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.bind(this, rootView);

        if (getArguments() != null) {
            List<Friend> friends = getArguments().getParcelableArrayList(BUNDLE_FRIENDS_LIST);

            if (friends != null && friends.size() > 0) {
                contacts_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                contacts_rv.setAdapter(new ContactsRVAdapter(friends));
            }
        }

        return rootView;
    }


}
