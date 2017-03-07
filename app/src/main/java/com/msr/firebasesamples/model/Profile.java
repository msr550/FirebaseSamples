package com.msr.firebasesamples.model;

/**
 * Created by Sandeep on 3/7/2017.
 */

public class Profile {
    private String name;
    private String mobile;
    private String address;
    private String imageUrl;

    public Profile(String name, String mobile, String address, String imageUrl) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
