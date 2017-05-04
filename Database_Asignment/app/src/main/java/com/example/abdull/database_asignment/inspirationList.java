package com.example.abdull.database_asignment;


import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;


public class inspirationList extends AppCompatActivity {

    Context context;


    ListView listView;
    String Dummy[]={"wahab","wahab","wahab","wahab","wahab"};
    ArrayAdapter arrayAdapter;
     ArrayList arrayList;
     helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration_list);
        context=this;

        listView= (ListView) findViewById(R.id.inspirations);
         helper=new helper(getApplicationContext());
        arrayList=helper.showRecord();

         arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"ID"+id,Toast.LENGTH_LONG).show();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                Toast.makeText(getApplicationContext(),"ID"+position,Toast.LENGTH_LONG).show();

                final AlertDialog.Builder optionBuilder=new AlertDialog.Builder(context);

                LayoutInflater inflaterOption = getLayoutInflater();

                final View OptionView = inflaterOption.inflate(R.layout.options, null);


                optionBuilder.setTitle("Options");
                optionBuilder.setMessage("Want to Update Record or Delete");

                optionBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder builder=new AlertDialog.Builder(context);

                        LayoutInflater inflater = getLayoutInflater();

                        final View dialogView = inflater.inflate(R.layout.update, null);


                        builder.setTitle("Update Record");
                        builder.setView(dialogView);


                        //

                        final String previous=arrayList.get((int) id).toString();
                        final String array[]=previous.split(" ");


                        final EditText name = (EditText) dialogView.findViewById(R.id.nameUpdate); //here
                        final EditText inspiration = (EditText) dialogView.findViewById(R.id.inspirationUpdate); //here

                        name.setText(array[2].toString());
                        inspiration.setText(array[5].toString());


                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                                String localName= String.valueOf(name.getText());
                                String localInspiration= String.valueOf(inspiration.getText());

                                if(localName.equals(array[2].toString()) && localInspiration.equals(array[5].toString()))
                                {

                                }
                                else
                                {
                                    helper update=new helper(context);
                                    update.updateRecord(id,localName,localInspiration);
                                    arrayList=helper.showRecord();
                                    arrayAdapter.clear();
                                    arrayAdapter.addAll(arrayList);
                                    arrayAdapter.notifyDataSetChanged();

                                }



                                Toast.makeText(getApplicationContext(),"localName"+localName+localInspiration,Toast.LENGTH_LONG).show();



                            }
                        });
                        AlertDialog dialogUpdate=builder.create();
                        dialogUpdate.show();



                    }
                });
                optionBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        helper helper1=new helper(context);
                        helper.deleteSpecificRecord(id);
                        arrayList=helper.showRecord();
                        arrayAdapter.clear();
                        arrayAdapter.addAll(arrayList);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                AlertDialog optionAlert=optionBuilder.create();
                optionAlert.show();




                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater layoutInflater=getMenuInflater();
        layoutInflater.inflate(R.menu.menu,menu);
        // Associate searchable configuration with the SearchView

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.deleteAll:
                new helper(getApplicationContext()).deleteAll();
                arrayAdapter.clear();
                arrayAdapter.notifyDataSetChanged();

            default :
                return true;

        }
    }
}
