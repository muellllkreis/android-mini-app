
/**
 * Created by rieti on 31.08.2017.
 */
package edu.virginia.cs.cs4720.mjr9r.androidbucketlist;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class BucketItem implements Comparable<BucketItem>, Parcelable{
    private String title;
    private String description;
    private Date duedate;
    private Double longitude;
    private Double latitude;
    private boolean finished;

    public BucketItem(String title, Date duedate) {
        this.title = title;
        this.description = "";
        this.duedate = duedate;
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.finished = false;
    }

    public BucketItem(String title, String description, Date duedate, Double longitude, Double latitude) {
        this.title = title;
        this.description = description;
        this.duedate = duedate;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeValue(duedate);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
    }
}