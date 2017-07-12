package com.example.riaz.rate_batao;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    Object count;

    int[] icon=new int[]{R.drawable.homes,R.drawable.profiles};
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    FirebaseDatabase database;
    int countData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       Intent intent=getIntent();
        countData=intent.getIntExtra("count",100);

        //Toast.makeText(getApplicationContext(),countData,Toast.LENGTH_LONG).show();




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),countData);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
          //  tabLayout.getTabAt(i).setIcon(icon[i]);
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(icon[i]);
            tabLayout.getTabAt(i).setCustomView(imageView);

        }
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#e7d2bd"));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

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
        switch(item.getItemId())
        {
            case R.id.posts:
//                  Dialogclass dailog=new Dialogclass();
//                  dailog.show(getSupportFragmentManager(),"Posts");

                postDailog();


            default:
                return true;
        }


    }

    private void postDailog() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();



        final View dialogView = inflater.inflate(R.layout.dailog, null);

        final EditText post = (EditText) dialogView.findViewById(R.id.profile_post); //here
        final Button submitPost = (Button) dialogView.findViewById(R.id.post_ok); //here
        final Button discard = (Button) dialogView.findViewById(R.id.post_cancle); //here

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();

                final DatabaseReference myRef = database.getReference("count");
                // myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                //final DatabaseReference myRef = database.getReference("users/identity/posts");

                //  myRef.child("useremail").setValue("mwahab@Live.com");
//                myRef.child("count").setValue("0");



                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        count=dataSnapshot.getValue();
                        int localCount=Integer.parseInt(count.toString());

                        DatabaseReference RootReference = database.getReference();

                        for(int i=0;i<localCount;i++)
                        {

                            RootReference.child("users-"+i).removeValue();

                        }

                        myRef.setValue(0);

//                        Toast.makeText(getApplicationContext(),"data is "+count,Toast.LENGTH_LONG).show();


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();

               final DatabaseReference myRef = database.getReference("count");
               // myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                  //final DatabaseReference myRef = database.getReference("users/identity/posts");

              //  myRef.child("useremail").setValue("mwahab@Live.com");
//                myRef.child("count").setValue("0");



                 myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                         count=dataSnapshot.getValue();


                        DatabaseReference RootReference = database.getReference("users-"+count+"/identity");
                        RootReference.child("useremail").setValue("mwahab@Live.com");
                        RootReference = database.getReference("users-"+count+"/identity/posts");
                        DatabaseReference local=RootReference.push();
                        local.child("tile").setValue(post.getText().toString());
                        local.child("discription").setValue("Nothing Added");
                        local.child("comments").setValue("No Comments");



                int localCount=Integer.parseInt(count.toString());
                localCount=localCount+1;

                myRef.setValue(localCount);

//                        Toast.makeText(getApplicationContext(),"data is "+count,Toast.LENGTH_LONG).show();


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




               // Toast.makeText(getApplicationContext(),"post",Toast.LENGTH_LONG).show();

            }
        });




        builder.setView(dialogView);

        AlertDialog dialogUpdate=builder.create();
        dialogUpdate.show();

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.hometab, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        int data;

        public SectionsPagerAdapter(FragmentManager fm,int data) {
            super(fm);
            this.data=data;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position){
                case 0:
                    TabOne tabOne=new TabOne();
                    Bundle bundle = new Bundle();
                    bundle.putInt("count", this.data);
tabOne.setArguments(bundle);
                    return tabOne;
                case 1:
                    Tabtwo tabtwo=new Tabtwo();
                    return tabtwo;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "";
//                case 1:
//                    return "";
//            }
            return null;
        }
    }
    @Override
    public void startActivity(Intent intent) {
        // check if search intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            intent.putExtra("KEY", countData);
        }

        super.startActivity(intent);
    }


}
