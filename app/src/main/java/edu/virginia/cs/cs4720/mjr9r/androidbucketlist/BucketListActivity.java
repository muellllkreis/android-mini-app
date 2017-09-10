package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import android.app.usage.NetworkStats;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;

public class BucketListActivity extends AppCompatActivity {

    ArrayList<BucketItem> bucketlist;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        if(savedInstanceState != null) {
            bucketlist = savedInstanceState.getParcelableArrayList("bucketlist");
        }
        else {
            bucketlist = BucketItem.createBucketList(5);
            Log.i("Bucketlist in Activity", "" + bucketlist.size());
        }


//        Log.i("Still", "Starting Forloop");
//        for (int i = 0; i < bucketlist.size(); i++) {
//            Log.i("Still Bucketlist Elem", bucketlist.get(i).getTitle());
//        }

        BucketItem.sortList(bucketlist);
        BucketItemAdapter adapter = new BucketItemAdapter(this, bucketlist);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bucket_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addItem(View view) {
        startActivityForResult(new Intent(this, AddItemActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == AddItemActivity.RESULT_OK) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                Bundle extras = data.getExtras();
                Log.i("Still", " here");
                try {
                    Log.i("Still", " here!!");
                    BucketItem newitem = new BucketItem(extras.getString("title"),
                            extras.getString("description"),
                            formatter.parse(extras.getString("duedate")),
                            extras.getDouble("longitude"),
                            extras.getDouble("latitude"));
                    Log.i("Still", " here!!!");
                    bucketlist.add(newitem);
                    Log.i("Still", " here!!!!");
                    Log.i("onCreate", "" + bucketlist.size());
                    for (int i = 0; i < bucketlist.size(); i++) {
                        Log.i("Still Bucketlist Elem", bucketlist.get(i).getTitle());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
        }
        if(requestCode == 1 && resultCode == Edit_Item.RESULT_OK) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Bundle extras = data.getExtras();
            try {
                BucketItem newitem = new BucketItem(extras.getString("title"),
                        extras.getString("description"),
                        formatter.parse(extras.getString("duedate")),
                        extras.getDouble("longitude"),
                        extras.getDouble("latitude"));
                bucketlist.remove(extras.getInt("id"));
                bucketlist.add(newitem);


                Log.i("onCreate", "" + bucketlist.size());
                for (int i = 0; i < bucketlist.size(); i++) {
                    Log.i("Still Bucketlist Elem", bucketlist.get(i).getTitle());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
            Log.i("After Intent", "Continue below if");
            BucketItem.sortList(bucketlist);
            BucketItemAdapter adapter = new BucketItemAdapter(this, bucketlist);
            rvItems.setAdapter(adapter);
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("bucketlist", bucketlist);
        Log.i("Still", "Saving instance state");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        bucketlist = inState.getParcelableArrayList("bucketlist");
    }
}
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });