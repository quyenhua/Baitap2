package com.tqt.btsoxo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class XoSo extends AppCompatActivity {

    private LoadXML loadXml;
    private TextView tvKetqua;
    private Spinner spinnerNgay;
    private String proName;
    private ArrayList<String> arrDate;
    private ArrayAdapter<String> adapter;

    private final String SOURCE = "http://thanhhungqb.tk:8080/kqxsmn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xo_so);

        initialize();

        intentExtra();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute(SOURCE);
            }
        });
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment1 fragment1 = new Fragment1();
        //fragmentManager.beginTransaction().add(R.id.fragment, fragment1).commit();
    }

    private void intentExtra() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            proName = bundle.getString("proName");
        }
        tvKetqua.setText(tvKetqua.getText() + " " + proName);
    }

    private void initialize() {
        tvKetqua = (TextView) findViewById(R.id.tvKetqua);
        spinnerNgay = (Spinner) findViewById(R.id.spinnerNgay);
        arrDate = new ArrayList<>();
    }

    class docJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return loadXml.LoadDataFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            try {
                final JSONObject json = new JSONObject(s);
                final JSONObject object = json.getJSONObject(proName);
                JSONArray date = object.names();
                for(int i = 0; i < date.length(); i++){
                    arrDate.add(i, date.getString(i));
                }
                adapter = new ArrayAdapter<>(XoSo.this, android.R.layout.simple_list_item_1, arrDate);
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinnerNgay.setAdapter(adapter);

                spinnerNgay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        JSONObject jsonObject = object.optJSONObject(arrDate.get(i));
                        Bundle bundle = new Bundle();
                        Fragment2 fragment1 = new Fragment2();
                        bundle.putString("object", jsonObject.toString());
                        fragment1.setArguments(bundle);
                        FragmentManager fManager = getSupportFragmentManager();
                        fManager.beginTransaction().replace(R.id.fragment, fragment1).commit();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
