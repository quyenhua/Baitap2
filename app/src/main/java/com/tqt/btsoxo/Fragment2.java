package com.tqt.btsoxo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    private String proName;
    private String[] listKetqua = new String[]{"8", "7", "6", "5", "4", "3", "2", "1", "DB"};
    private ArrayList<DataKetQua> arrayList;
    private Adapter adapter;
    //private TextView tv;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, null);

        initialize(view);
        Bundle bundle = getArguments();

        if(bundle != null) {
            proName = bundle.getString("object");
            arrayList = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(proName);
                for (int i = 0; i < listKetqua.length; i++) {
                    JSONArray arr = object.getJSONArray(listKetqua[i]);
                    String value = "";
                    for (int j = 0; j < arr.length(); j++) {
                        value += arr.get(j).toString() + "  ";
                    }
                    arrayList.add(new DataKetQua(listKetqua[i], value));
                }

                //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listKetqua);
                //listView.setAdapter(arrayAdapter);
                //setListAdapter(arrayAdapter);
                adapter = new Adapter(getActivity(), R.layout.item_ketquasoxo, arrayList);
                //setListAdapter(adapter);
                listView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
                //tv.setText(e.toString());
            }
        }

        return view;
    }

    private void initialize(View view) {
        //tv = (TextView) view.findViewById(R.id.textView3);
        listView = (ListView) view.findViewById(R.id.listviewKq);
    }

}
