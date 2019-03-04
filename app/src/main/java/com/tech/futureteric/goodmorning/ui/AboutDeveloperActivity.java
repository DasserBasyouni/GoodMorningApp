package com.tech.futureteric.goodmorning.ui;

import android.os.Bundle;
import android.view.ViewGroup;

import com.tech.futureteric.goodmorning.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutDeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.developer_profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName("Dasser Basyouni")
                .setSubTitle("Android Developer")
                .setBrief("I learn more about Android Development day by day & I took some small courses in order to know how others think and to be sure that I'm on the right way.")
                .addGitHubLink("DasserBasyouni")
                .addFacebookLink("DasserBasyouni")
                .addLinkedInLink("dasser-basyouni-000850122")
                .addEmailLink("dasserbasyouni@gmail.com")
                .addWhatsappLink("Dasser Basyouni", "+201208968336")
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(false)
                .build();

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        addContentView(view, layoutParams);
    }
}
