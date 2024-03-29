package Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinkpad.wenews.NewsAdapter;
import com.example.thinkpad.wenews.R;

import Fragments.channelFragment;

public class armyFragment extends channelFragment {

    private  RecyclerView recyclerView_army;


    public armyFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_army, container, false);
        recyclerView_army=(RecyclerView) view.findViewById(R.id.recyclerview_army) ;
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView_army.setLayoutManager(layoutManager);
        adapter=new NewsAdapter(newItems);
        recyclerView_army.setAdapter(adapter);
        typeCode="BD2A9LEIwangning";
        address="https://v.juhe.cn/toutiao/index?type=junshi&key=159fcc069e8f11876501f359679b449f";
        GetNews();
        return view;
    }

}
