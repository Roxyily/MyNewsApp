package com.example.thinkpad.wenews;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class financeFragment extends channelFragment {

    private  RecyclerView recyclerView_finance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_finance, container, false);
        recyclerView_finance=(RecyclerView) view.findViewById(R.id.recyclerview_finace) ;
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView_finance.setLayoutManager(layoutManager);
        adapter=new NewsAdapter(newItems);
        address="https://v.juhe.cn/toutiao/index?type=caijing&key=159fcc069e8f11876501f359679b449f";
        recyclerView_finance.setAdapter(adapter);
        GetNews();
        if(!newItems.isEmpty())
            recyclerView_finance.scrollToPosition(0);
        return view;
    }

}
