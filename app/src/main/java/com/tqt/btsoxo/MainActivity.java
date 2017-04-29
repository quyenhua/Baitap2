package com.tqt.btsoxo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvDanhsach;
    private LoadXML loadXML;
    private ArrayList<String> arr;
    private ArrayAdapter<String> adapter;

    //private String[] province = new String[]{"AG", "BD", "BL", "BP", "BTH", "CM", "CT", "HCM"};

    private final String SOURCE = "http://thanhhungqb.tk:8080/kqxsmn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intitalize();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute(SOURCE);
            }
        });
    }

    private void intitalize() {
        lvDanhsach = (ListView) findViewById(R.id.lvDanhsach);
        arr = new ArrayList<>();
    }

    class docJSON extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            return loadXML.LoadDataFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            try {
                JSONObject json = new JSONObject(s);
                JSONArray province = json.names();
                for(int i = 0; i < province.length(); i++){
                    arr.add(i, province.getString(i));
                }
                //Toast.makeText(MainActivity.this, arr.get(0), Toast.LENGTH_SHORT).show();
                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arr);
                adapter.notifyDataSetChanged();
                lvDanhsach.setAdapter(adapter);

                lvDanhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this, XoSo.class);
                        intent.putExtra("proName", arr.get(i));
                        startActivity(intent);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
