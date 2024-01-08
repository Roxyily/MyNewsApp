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

public class headlineFragment extends channelFragment {

    private  RecyclerView recyclerView_sports;


    public headlineFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_sports, container, false);
        recyclerView_sports=(RecyclerView) view.findViewById(R.id.recyclerview_sports) ;
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView_sports.setLayoutManager(layoutManager);
        adapter=new NewsAdapter(newItems);
        recyclerView_sports.setAdapter(adapter);
        address="https://v.juhe.cn/toutiao/index?type=top&key=159fcc069e8f11876501f359679b449f";
        GetNews();
        return view;
    }

}
