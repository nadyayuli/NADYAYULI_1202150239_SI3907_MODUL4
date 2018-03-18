package com.example.nadyayulipratama.studycase4;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by nadya yuli pratama on 16/03/2018.
 */

public class ListNamaMhs extends AppCompatActivity {

    private Button btn;
    private ListView mainList;
    private AddItemToListView addItemToListView;
    private ProgressBar progress;
    private String[] texts = {"Nadya", "Ayu", "Elviera", "Shahniz", "Intan", "Syanaz", "Raras", "Leidy", "Mia", "Marissa", "Vennesa", "Rahmania", "Novi"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_nama_mahasiswa);
        btn = (Button) findViewById(R.id.btn);
        mainList = (ListView) findViewById(R.id.ListView1);
        progress = (ProgressBar) findViewById(R.id.progress);

        mainList.setVisibility(View.GONE);
        mainList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToListView = new AddItemToListView();
                addItemToListView.execute();

            }
        });
    }

    private class AddItemToListView  extends  AsyncTask<Void, String, Void>{
        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog progressDialog = new ProgressDialog(ListNamaMhs.this);



        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mainList.getAdapter();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Loading Data");
            progressDialog.setMessage("Data sedang di proses.....");
            progressDialog.setCancelable(false);
            progressDialog.setProgress(0);

            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            addItemToListView.cancel(true);
                            progress.setVisibility(View.VISIBLE);

                            dialog.dismiss();
                        }
                    });

                    progressDialog.show();
                }

        @Override
        protected Void doInBackground(Void... Params) {
            for (String item : texts){
                publishProgress(item);

                try{
                    Thread.sleep(500);

                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    addItemToListView.cancel(true);

                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);
            Integer current_status = (int) ((counter/(float)texts.length)*100);
            progress.setProgress(current_status);

            progressDialog.setProgress(current_status);
            progressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progress.setVisibility(View.GONE);
            progressDialog.dismiss();
            mainList.setVisibility(View.VISIBLE);
        }
    };
        }

