package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by rieti on 31.08.2017.
 */


public class BucketItemAdapter extends
        RecyclerView.Adapter<BucketItemAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView duedateTextView;
        public CheckBox finishedButton;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.bucketitem_title);
            duedateTextView = (TextView) itemView.findViewById(R.id.bucketitem_duedate);
            finishedButton = (CheckBox) itemView.findViewById(R.id.finished_checkbox);

        }

    }
    private List<BucketItem> mBucketlist;
    private Context mContext;

    public BucketItemAdapter(Context context, List<BucketItem> bucketlist) {
        this.mBucketlist = bucketlist;
        this.mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public BucketItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_bucketitem, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BucketItemAdapter.ViewHolder viewHolder, int position) {
        final BucketItem item = mBucketlist.get(position);

        final TextView titletext = viewHolder.titleTextView;
        titletext.setText(item.getTitle());
        TextView duedatetext = viewHolder.duedateTextView;
        duedatetext.setText(formatDate(item.getDuedate()));
        final CheckBox checkbox = viewHolder.finishedButton;
        checkbox.setChecked(item.isFinished());

        //Reorganizes list when item is checked
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setFinished(!item.isFinished());
                Collections.sort(mBucketlist);
                notifyDataSetChanged();
                Toast.makeText(mContext, "Click " + item.getTitle() + " " + item.isFinished(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {

        return mBucketlist.size();
    }


    public String formatDate(Date duedate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(duedate);
    }
}