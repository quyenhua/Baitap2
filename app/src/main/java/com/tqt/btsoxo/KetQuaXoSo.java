package com.tqt.btsoxo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class KetQuaXoSo extends AppCompatActivity {

    private ListView lvMenuDrawer;
    private LoadXML loadXml;
    private String proName;
    private ArrayList<String> arrDate;
    private ArrayAdapter<String> adapter;

    private final String SOURCE = "http://thanhhungqb.tk:8080/kqxsmn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua_xo_so);

        initialize();

        intentExtra();

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new docJSON().execute(SOURCE);
//            }
//        });
    }

    private void intentExtra() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            proName = bundle.getString("proName");
        }
    }

    private void initialize() {
        lvMenuDrawer = (ListView) findViewById(R.id.lvMenuDrawer);
        arrDate = new ArrayList<>();
    }

//    class docJSON extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return loadXml.LoadDataFromURL(strings[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//            try {
//                JSONObject json = new JSONObject(s);
//                JSONObject object = json.getJSONObject(proName);
//                JSONArray date = object.names();
//                for(int i = 0; i < date.length(); i++){
//                    arrDate.add(i, date.getString(i));
//                }
//                adapter = new ArrayAdapter<String>(KetQuaXoSo.this, android.R.layout.simple_list_item_1, arrDate);
//                adapter.notifyDataSetChanged();
//                lvMenuDrawer.setAdapter(adapter);
//                lvMenuDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        FragmentManager fManager = getSupportFragmentManager();
//
//                        //Toast.makeText(KetQuaXoSo.this, "" + i, Toast.LENGTH_SHORT).show();
//                        switch (i){
//                            case 0:
//                                Fragment1 fragment1 = new Fragment1();
//                                Bundle bundle = new Bundle();
//                                bundle.putInt("position", i);
//                                bundle.putString("proName", proName);
//                                fragment1.setArguments(bundle);
//                                fManager.beginTransaction().replace(R.id.noidung, fragment1).commit();
//                                break;
//                            case 1:
//                                Fragment2 fragment2 = new Fragment2();
//                                fManager.beginTransaction().replace(R.id.noidung, fragment2).commit();
//                                break;
//                        }
//                    }
//                });
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
