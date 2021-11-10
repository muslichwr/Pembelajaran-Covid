package com.mumus.pembelajarancovid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancyaboutpagelib.FancyAboutPage;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        FancyAboutPage fancyAboutPage=findViewById(R.id.fancyaboutpage);
        //fancyAboutPage.setCoverTintColor(Color.BLUE);  //Optional
        fancyAboutPage.setCover(R.drawable.allah); //Pass your cover image
        fancyAboutPage.setName("Kelompok 6");
        fancyAboutPage.setDescription("blablabla");
        fancyAboutPage.setAppIcon(R.mipmap.ic_launcher); //Pass your app icon image
        fancyAboutPage.setAppName("blablabla");
        fancyAboutPage.setVersionNameAsAppSubTitle("1.0.1");
        fancyAboutPage.setAppDescription("blablabla");
        fancyAboutPage.addEmailLink("blablabla");     //Add your email id
        fancyAboutPage.addFacebookLink("blablabla");  //Add your facebook address url
        fancyAboutPage.addGitHubLink("blablabla");
    }
}
