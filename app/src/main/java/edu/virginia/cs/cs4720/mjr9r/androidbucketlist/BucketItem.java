
/**
 * Created by rieti on 31.08.2017.
 */
package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class BucketItem implements Comparable<BucketItem>{
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


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    private static int lastBucketItemId = 0;

    public static ArrayList<BucketItem> createBucketList(int numItems) {
        ArrayList<BucketItem> bucketlist = new ArrayList<BucketItem>();
        Random rnd = new Random();

        for (int i = 1; i <= numItems; i++) {
            bucketlist.add(new BucketItem("Item " + ++lastBucketItemId, new Date(System.currentTimeMillis() + ((long) rnd.nextInt((2543346) + 1) + 86400)*1000)));
        }
        Collections.sort(bucketlist);
        return bucketlist;
    }

    //Compares Status finished - not finished and then sorts by duedate
    @Override
    public int compareTo(BucketItem item) {
        int comparestatus = Boolean.compare(this.isFinished(), item.isFinished());
        if(comparestatus == 0) {
            if(this.getDuedate().before(item.getDuedate())) {
                return -1;
            }
            else if(this.getDuedate().after(item.getDuedate())) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return comparestatus;
        }
    }
}