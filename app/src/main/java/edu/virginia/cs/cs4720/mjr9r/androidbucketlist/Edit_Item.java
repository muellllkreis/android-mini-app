package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/* Created by Cabell-Glancy on 04/09/17 */

public class Edit_Item extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView longitude;
    TextView latitude;
    DatePicker duedate;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        String title1 = i.getStringExtra("title");
        String description1 = i.getStringExtra("description");
        Double longitude1 = i.getDoubleExtra("longitude", 0.0);
        Double latitude1 = i.getDoubleExtra("latitude", 0.0);
        String duedate1 = i.getStringExtra("duedate");
        id = i.getIntExtra("id", 0);

        String[] date_array = duedate1.split("-");

        title = (TextView) findViewById(R.id.item_title);
        title.setText(title1);

        description = (TextView) findViewById(R.id.item_description);
        description.setText(description1);

        longitude = (TextView) findViewById(R.id.item_longitude);
        longitude.setText(Double.toString(longitude1));

        latitude = (TextView) findViewById(R.id.item_latitude);
        latitude.setText(Double.toString(latitude1));

        duedate = (DatePicker) findViewById(R.id.item_duedate);
        duedate.updateDate(Integer.parseInt(date_array[2]),Integer.parseInt(date_array[0])-1,Integer.parseInt(date_array[1]));

        latitude.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        longitude.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


   public void saveNewItem(View view) {
        Log.i("Date", this.duedate.getDayOfMonth() + "-" + this.duedate.getMonth() + "-" + this.duedate.getYear());
        if(this.title.getText().equals("")
                || this.description.getText().equals("")
                || this.longitude.getText().toString().equals("")
                || this.latitude.getText().toString().equals("")) {
            Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
        }

        else {
            String title = this.title.getText().toString();
            String description = this.description.getText().toString();
            String duedate = this.duedate.getMonth()+1 + "-" + this.duedate.getDayOfMonth() + "-" + this.duedate.getYear();
            Double longitude = Double.parseDouble(this.longitude.getText().toString());
            Double latitude = Double.parseDouble(this.latitude.getText().toString());

            Intent i = new Intent(this, BucketListActivity.class);
            i.putExtra("title", title);
            i.putExtra("description", description);
            i.putExtra("duedate", duedate);
            i.putExtra("longitude", longitude);
            i.putExtra("latitude", latitude);
            i.putExtra("id", id);
            setResult(Edit_Item.RESULT_OK, i);
            finish();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
