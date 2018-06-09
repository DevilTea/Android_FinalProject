package com.kamijou.deviltea;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class KanaFragment extends Fragment {
    private int type;
    private boolean isExam;
    private RecyclerView recyclerView;
    private KanaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public KanaFragment() {
        // Required empty public constructor
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExam() {
        return isExam;
    }

    public void setExam(boolean exam) {
        isExam = exam;
    }

    public KanaAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kana, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_hiragana);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new KanaAdapter(getActivity(), type, KanaData.getAllKanaList1(), KanaData.getAllKanaList2(), isExam);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
