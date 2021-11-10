package com.mumus.pembelajarancovid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class Helpline extends AppCompatActivity {


    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("coronvavirushelplinenumber.pdf").load();


    }
}
