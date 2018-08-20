package com.tech.futureteric.goodmorning.utils;

import android.content.Intent;
import android.view.View;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.LibsConfiguration;
import com.mikepenz.aboutlibraries.ui.item.HeaderItem;
import com.mikepenz.aboutlibraries.ui.item.LibraryItem;
import com.tech.futureteric.goodmorning.R;
import com.tech.futureteric.goodmorning.ui.AboutDeveloperActivity;
import com.tech.futureteric.goodmorning.ui.MainActivity;

import static com.tech.futureteric.sharedcomponents.utils.AnimationUtils.startActivityWithAnimation;

public class UiUtils {

    public static void launchAboutActivity(MainActivity activity) {
        new LibsBuilder()
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                .withActivityTitle("About")
                .withAboutAppName(activity.getString(R.string.app_name))
                .withAboutSpecial1("APP CHANGELOG")
                .withAboutSpecial1Description(activity.getString(R.string.aboutLibraries_changelog_text))
                .withAboutSpecial2("ABOUT DEVELOPER").withAboutSpecial2Description("test 2")
                .withAboutDescription("This is a small sample which can be set in the about my app description file.<br /><b>You can style this with html markup :D</b>")
                .withLibsRecyclerViewListener(new LibsConfiguration.LibsRecyclerViewListener() {
                    @Override
                    public void onBindViewHolder(HeaderItem.ViewHolder headerViewHolder) {
                        View button = headerViewHolder.itemView.findViewById(R.id.aboutSpecial2);
                        if (button != null)
                            button.setOnClickListener(v -> {
                                startActivityWithAnimation(new Intent(activity, AboutDeveloperActivity.class), activity);
                            });
                    }

                    @Override
                    public void onBindViewHolder(LibraryItem.ViewHolder viewHolder) {

                    }
                }).start(activity);
    }

}
