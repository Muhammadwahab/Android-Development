package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdull.scorebatao.Activity.PersonsDetail;
import com.example.abdull.scorebatao.R;

import pojo.currentLiveMatches;

/**
 * Created by abdull on 3/23/17.
 */


public class currentMatchesAdapter extends ArrayAdapter implements View.OnClickListener {
    currentLiveMatches liveMatches[];
    Button setCoverage;
    Context context;

    public currentMatchesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull currentLiveMatches[] live) {
        super(context, resource, live);
        this.context=context;
        liveMatches=live;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.matches_view_adapter_layout,parent,false);
        }
           TextView oneVsTwo=(TextView)convertView.findViewById(R.id.oneVsTwo);
            oneVsTwo.setText(liveMatches[position].getName());
          setCoverage=(Button) convertView.findViewById(R.id.setCoverage);
        setCoverage.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Button button= (Button) v;

        if(button.getId()==setCoverage.getId())
        {
            Toast.makeText(getContext(),"set Coverage",Toast.LENGTH_LONG).show();
           Intent addPerson=new Intent(getContext(), PersonsDetail.class);
           getContext().startActivity(addPerson);
            ((Activity)context).finish();
        }

    }
}
