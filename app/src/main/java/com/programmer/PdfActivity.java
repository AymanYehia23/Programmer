package com.programmer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfActivity extends AppCompatActivity {

    private int pageNumber = 1;
    private PDFView pdfView;
    private String tutorial = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        int position = getIntent().getIntExtra(Helper.EXTRA_POSITION, 0);
        if (position == 0) tutorial = "java_";
        else if (position == 1) tutorial = "kotlin_";


        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(tutorial + pageNumber + ".pdf").load();
    }

    public void onClickBack(View view) {
        if (pageNumber == 1) pageNumber = 5;
        else pageNumber--;
        pdfView.fromAsset(tutorial + pageNumber + ".pdf").load();
    }

    public void onClickHome(View view) {
        PdfActivity.this.finish();
    }

    public void onClickNext(View view) {
        if (pageNumber == 5) pageNumber = 1;
        else pageNumber++;
        pdfView.fromAsset(tutorial + pageNumber + ".pdf").load();
    }
}