package com.example.riaz.rate_batao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by riaz on 4/23/17.
 */

public class Tabtwo extends android.support.v4.app.Fragment {
    int[] profile_image=new int[]{R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter};
    String[] profile_titles=new String[]{"youtube","Twitter","youtube","Twitter","youtube","Twitter","youtube","Twitter","youtube","Twitter"};
    String[] profile_descprictions=new String[]{"video watching website","Social Websites","video watching website","Social Websites","video watching website","Social Websites","video watching website","Social Websites","video watching website","Social Websites"};
    ListView profilelist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootprofileview=  inflater.inflate(R.layout.profiletab,container,false);
        profilelist=(ListView) rootprofileview.findViewById(R.id.listview_2);
        profileadapter profileadapter=new profileadapter();
        profilelist.setAdapter(profileadapter);
        return rootprofileview;

    }
    class profileadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return  profile_image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getActivity().getLayoutInflater().inflate(R.layout.listviewlayout,null);
            ImageView image= (ImageView) convertView.findViewById(R.id.profile_view);
            TextView title=(TextView) convertView.findViewById(R.id.title_view);
            TextView description=(TextView) convertView.findViewById(R.id.desp_view);
            image.setImageResource(profile_image[position]);
            title.setText(profile_titles[position]);
            description.setText(profile_descprictions[position]);
            return convertView;
        }
    }

}
