package com.example.nadyayulipratama.studycase4;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Created by nadya yuli pratama on 17/03/2018.
 */


public class PencarianGambar extends AppCompatActivity {

    private EditText ImgURL;
    private Button btn;
    private ImageView img;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pencarian_gambar);


        ImgURL = (EditText) findViewById(R.id.ImgURL);
        btn = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.img);
    }

            public void search(View view) {
                loadImage();
            }




    private void loadImage() {
        String imgurl = ImgURL.getText().toString();
        new  LoadImageTask().execute(imgurl);
    }

    private class LoadImageTask extends AsyncTask<String, Integer,Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(PencarianGambar.this);
            progress.setTitle("Mencari gambar");
            progress.setMessage("Loading...");
            progress.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            String url = URL[0];
            Bitmap btp = null;
            try{
               InputStream input = new java.net.URL(url).openStream();
               btp = BitmapFactory.decodeStream(input);

            }catch (Exception e){
                e.printStackTrace();

            }
            return btp;
        }



        @Override
        protected void onPostExecute(Bitmap btp) {
            super.onPostExecute(btp);
            img.setImageBitmap(btp);
            progress.dismiss();
        }
    }
}
