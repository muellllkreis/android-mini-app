
/**
 * Created by rieti on 31.08.2017.
 */
package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import java.util.ArrayList;
import java.util.Date;

public class BucketItem {
    private String title;
    private Date duedate;
    private boolean finished;

    public BucketItem(String title, Date duedate) {
        this.title = title;
        this.duedate = duedate;
        this.finished = false;
    }

    public String getTitle() {
        return title;
    }

    public Date getDuedate() {
        return duedate;
    }

    public boolean isFinished() {
        return finished;
    }


    private static int lastBucketItemId = 0;

    public static ArrayList<BucketItem> createBucketList(int numItems) {
        ArrayList<BucketItem> bucketlist = new ArrayList<BucketItem>();

        for (int i = 1; i <= numItems; i++) {
            bucketlist.add(new BucketItem("Item " + ++lastBucketItemId, new Date(System.currentTimeMillis())));
        }

        return bucketlist;
    }
}