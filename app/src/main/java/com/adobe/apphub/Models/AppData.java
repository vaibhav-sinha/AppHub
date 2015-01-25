package com.adobe.apphub.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by vaisinha on 25/01/15.
 */
public class AppData implements Serializable, Comparable<AppData> {

    private String name;
    private String type;
    private String url;
    private String image;
    private String rating;
    @SerializedName("last updated")
    private String last_updated;
    @SerializedName("inapp-purchase")
    private String inapp_purchase;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getInapp_purchase() {
        return inapp_purchase;
    }

    public void setInapp_purchase(String inapp_purchase) {
        this.inapp_purchase = inapp_purchase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AppData{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", rating='" + rating + '\'' +
                ", last_updated='" + last_updated + '\'' +
                ", inapp_purchase='" + inapp_purchase + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int compareTo(AppData o)
    {
        if(Double.valueOf(rating) - Double.valueOf(o.rating) == 0) {
            return 0;
        }
        if(Double.valueOf(rating) - Double.valueOf(o.rating) > 0) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
