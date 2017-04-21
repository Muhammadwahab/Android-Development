package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.abdull.scorebatao.R;

import Adapter.currentMatchesAdapter;
import pojo.currentLiveMatches;

/**
 * Created by abdull on 3/23/17.
 */

public class currentMatch extends Fragment {
    ListView listView;
    currentLiveMatches liveMatches[];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        liveMatches=new currentLiveMatches[5];
        liveMatches[0]=new currentLiveMatches();
        liveMatches[1]=new currentLiveMatches();
        liveMatches[2]=new currentLiveMatches();
        liveMatches[3]=new currentLiveMatches();
        liveMatches[4]=new currentLiveMatches();
        View view=LayoutInflater.from(getContext()).inflate(R.layout.current_match,container,false);
        listView=(ListView)view.findViewById(R.id.curretListView);
        currentMatchesAdapter currentMatchesAdapter=new currentMatchesAdapter(getActivity(),0,liveMatches);
        listView.setAdapter(currentMatchesAdapter);
        return view;
    }
}
