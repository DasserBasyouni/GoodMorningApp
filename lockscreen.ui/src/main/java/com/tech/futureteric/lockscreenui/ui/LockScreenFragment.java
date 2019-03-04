package com.tech.futureteric.lockscreenui.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jaredrummler.android.widget.AnimatedSvgView;
import com.tech.futureteric.lockscreenui.R;
import com.tech.futureteric.lockscreenui.adapter.MessagesRV;
import com.tech.futureteric.sharedcomponents.DataType;
import com.tech.futureteric.sharedcomponents.model.IdlingDataModel;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

import static com.tech.futureteric.backend.utils.MessagingUtils.sendMessageToUser;
import static com.tech.futureteric.backend.utils.user.UserInfoUtils.getUserDisplayName;
import static com.tech.futureteric.backend.utils.user.UserInfoUtils.getUserUid;
import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_FRIEND_TOKEN;
import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_FRIEND_UID;
import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_IS_PREVIEW;
import static com.tech.futureteric.lockscreenui.Constants.BUNDLE_MESSAGES_LIST;

public class LockScreenFragment extends Fragment implements IdlingDataModel.OnDataReady {

    EditText message_et;

    public static LockScreenFragment newInstance(Bundle bundle) {
        LockScreenFragment fragment = new LockScreenFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView;
        boolean isPreview = false;
        IdlingDataModel.getInstance().setListener(this);

        if (getArguments() != null)
            isPreview = getArguments().getBoolean(BUNDLE_IS_PREVIEW, false);

        if (isPreview) {
            rootView = inflater.inflate(R.layout.fragment_lock_screen_preview, container, false);
            setupPreviewLayout(rootView);
        }else {
            rootView = inflater.inflate(R.layout.fragment_lock_screen, container, false);
            setupLockScreenLayout(rootView);
        }

        AnimatedSvgView svgView = rootView.findViewById(R.id.animated_svg_view);
        svgView.start();

        return rootView;
    }

    @Override
    public void dataAreReceived() {
        Object data = IdlingDataModel.getInstance().getData();
        int dataType = IdlingDataModel.getInstance().getDataType();
        Timber.i("Received data type: %s", dataType);

       if (dataType == DataType.DIALOG_ON_POSITIVE) {
            sendMessageToUser( getActivity(),
                    message_et.getText().toString(),
                    (Date) data,
                    getUserUid(),
                    getUserDisplayName(),
                    getArguments().getString(BUNDLE_FRIEND_UID),
                    getArguments().getString(BUNDLE_FRIEND_TOKEN));
        }
    }

    private void setupLockScreenLayout(View rootView) {
        RecyclerView msg_rv = rootView.findViewById(R.id.recyclerView_morningMessages);

        msg_rv.setHasFixedSize(true);
        msg_rv.setLayoutManager( new LinearLayoutManager(getActivity()));
        msg_rv.setAdapter(new MessagesRV(getArguments().getParcelableArrayList(BUNDLE_MESSAGES_LIST)));
    }

    private void setupPreviewLayout(View rootView) {
        rootView.findViewById(R.id.fab_send).setOnClickListener(v -> {
            message_et = rootView.findViewById(R.id.editText_message);

            if(!TextUtils.isEmpty(message_et.getText().toString())) {
                DateAndTimePickerDialog.showDialog(getActivity(),
                        ( (AppCompatActivity) getActivity() ).getSupportFragmentManager());
            }else {
                // TODO handle empty and long MSGs
            }

        });
    }

}
