package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rieti on 31.08.2017.
 */

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class BucketItemAdapter extends
        RecyclerView.Adapter<BucketItemAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView titleTextView;
        public TextView duedateTextView;
        public CheckBox finishedButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.bucketitem_title);
            duedateTextView = (TextView) itemView.findViewById(R.id.bucketitem_duedate);
            finishedButton = (CheckBox) itemView.findViewById(R.id.finished_checkbox);
        }
    }
    // Store a member variable for the contacts
    private List<BucketItem> mBucketlist;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public BucketItemAdapter(Context context, List<BucketItem> bucketlist) {
        this.mBucketlist = bucketlist;
        this.mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public BucketItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.item_bucketitem, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(BucketItemAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        BucketItem item = mBucketlist.get(position);

        // Set item views based on your views and data model
        TextView titletext = viewHolder.titleTextView;
        titletext.setText(item.getTitle());
        TextView duedatetext = viewHolder.duedateTextView;
        duedatetext.setText(item.getDuedate().toString());
        CheckBox checkbox = viewHolder.finishedButton;
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mBucketlist.size();
    }
}
