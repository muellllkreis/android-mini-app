package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddItemActivity extends AppCompatActivity {

    TextView title;
    TextView description;
    TextView longitude;
    TextView latitude;
    DatePicker duedate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent();
        title = (TextView) findViewById(R.id.item_title);
        description = (TextView) findViewById(R.id.item_description);
        longitude = (TextView) findViewById(R.id.item_longitude);
        latitude = (TextView) findViewById(R.id.item_latitude);
        duedate = (DatePicker) findViewById(R.id.item_duedate);
    }

    protected void saveItem() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        BucketItem newitem = new BucketItem(this.title.getText().toString(),
                                            this.description.getText().toString(),
                                            (Date) formatter.parse(this.duedate.getMonth() + "-" + this.duedate.getDayOfMonth() + "-" + this.duedate.getYear()) ,
                                            Double.parseDouble(this.longitude.getText().toString()),
                                            Double.parseDouble(this.latitude.getText().toString()));
    }
    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        try {
            BucketItem newitem = new BucketItem(this.title.getText().toString(),
                    this.description.getText().toString(),
                    (Date) formatter.parse(this.duedate.getMonth() + "-" + this.duedate.getDayOfMonth() + "-" + this.duedate.getYear()),
                    Double.parseDouble(this.longitude.getText().toString()),
                    Double.parseDouble(this.latitude.getText().toString()));
                    returnIntent.putExtra("passed_item", newitem);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setResult(RESULT_OK);
        super.finish();
    }
}
