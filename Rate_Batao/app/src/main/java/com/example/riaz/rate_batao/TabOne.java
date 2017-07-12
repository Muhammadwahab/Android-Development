package com.example.riaz.rate_batao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by riaz on 4/23/17.
 */

public class TabOne extends android.support.v4.app.Fragment {
    ArrayList arrayList=new ArrayList();
   int[] images=new int[]{R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter,R.drawable.youtube,R.drawable.twitter};
    String[] titles=new String[]{"youtube","Twitter","youtube","Twitter","youtube","Twitter","youtube","Twitter","youtube","Twitter"};
    String[] descprictions=new String[]{"video watching website","Social Websites","video watching website","Social Websites","video watching website","Social Websites","video watching website","Social Websites","video watching website","Social Websites"};
    ListView list;
    int count;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.hometab,container,false);
        Bundle args = getArguments();
       count= args.getInt("count");
        if (savedInstanceState != null) {

            int myInt = savedInstanceState.getInt("count",1000);
        }
       retrievePost();
    list= (ListView) rootview.findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),Personal_post.class);
             //   Toast.makeText(getActivity(),"id"+position,Toast.LENGTH_LONG).show();

                 final Post post= (Post) arrayList.get(position);
              //  Toast.makeText(getContext(), ""+post.getPostReference().toString(), Toast.LENGTH_LONG).show();
//                intent.putExtra("titlte",post.getPostTitle());
//                intent.putExtra("id",position);

                //startActivity(intent);

                final AlertDialog.Builder optionBuilder=new AlertDialog.Builder(getContext());

                optionBuilder.setTitle("Options");
                optionBuilder.setMessage("Want to Update Record or Delete");
                optionBuilder.setPositiveButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  Toast.makeText(getContext(), "Update"+post.getPostTitleReference(), Toast.LENGTH_LONG).show();
//

                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

                        LayoutInflater inflater = getActivity().getLayoutInflater();

                        final View dialogView = inflater.inflate(R.layout.update, null);


                        builder.setTitle("Update Record");
                        builder.setView(dialogView);

                        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                String newV=post.getPostReference();

                                StringBuilder stringBuilder=new StringBuilder(newV);

                                //Toast.makeText(getContext(), ""+ stringBuilder.replace(0,33,"").toString(), Toast.LENGTH_SHORT).show();

                            String refvalue=stringBuilder.replace(0,33,"").toString();
                         final DatabaseReference myRef = database.getReference(refvalue);
                               final EditText title = (EditText) dialogView.findViewById(R.id.nameUpdate); //here
                                myRef.child("tile").setValue(title.getText().toString());

                                if(refvalue.equalsIgnoreCase("users-0/identity/posts/-Kj2PNMuU3ntJptUGag5"))
                                {
                                    Toast.makeText(getContext(), "equals", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(getContext(), "not equals"+myRef.toString(), Toast.LENGTH_SHORT).show();

                                }


                             //   Toast.makeText(getContext(), ""+myRef.toString(), Toast.LENGTH_SHORT).show();





                            }
                        });

                        AlertDialog dialogUpdate=builder.create();
                        dialogUpdate.show();






                    }
                });
                optionBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();

                    }
                });

                AlertDialog optionAlert=optionBuilder.create();
                optionAlert.show();




            }
        });
        adapter adapt=new adapter();
        list.setAdapter(adapt);
        return  rootview;
    }
    class adapter extends BaseAdapter{
        @Override
        public int getCount() {
           return arrayList.size();
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
            image.setImageResource(images[position]);
            Post TitlePost= (Post) arrayList.get(position);
            title.setText(TitlePost.getPostTitle());
            description.setText(TitlePost.getPostDiscription());
            return convertView;
        }
    }

    public ArrayList retrievePost()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
       // database.setPersistenceEnabled(true);
           for(int i=0;i<count;i++)
           {
               DatabaseReference databaseReference=database.getReference("users-"+i+"/identity/posts");
               databaseReference.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       Iterable<DataSnapshot> childrenData= dataSnapshot.getChildren();
                       for (DataSnapshot child : childrenData) {
                           Iterable<DataSnapshot> postsData= child.getChildren();
                           // inner iteratable for post values
                           int childs=0;
                           String ref=child.getRef().toString();
                           Post insertData=new Post();
                           for (DataSnapshot post : postsData) {

                               insertData.setPostReference(ref);

                               if(childs==0)
                               {
                                   // DataSnapshot titleData=post.child("comments");
                                   String value=post.getValue().toString();
                                   insertData.setComments(value);
                                   childs++;
                               }
                               else if(childs==1)
                               {
//                                DataSnapshot titleData=post.child("discription");
                                   String value=post.getValue().toString();
                                   insertData.setPostDiscription(value);
                                   childs++;
                               }
                               else if(childs==2)
                               {
//                                DataSnapshot titleData=post.child("tile");
                                   String titleRef=post.getRef().toString();
                                   String value=post.getValue().toString();
                                   insertData.setPostTitleReference(titleRef);
                                   insertData.setPostTitle(value);
                                   childs++;
                               }

                           }
                           arrayList.add(insertData);
                       }
                   }
                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
           }
        return arrayList;
    }
}