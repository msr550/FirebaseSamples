package com.msr.firebasesamples.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Sandeep Maram on 1/6/15.
 */
public class NotificationID {
    private final static AtomicInteger integer = new AtomicInteger(0);

    /**
     * Used to get the unique notification id
     *
     * @return
     */
    public static int getID() {
        return integer.incrementAndGet();
    }
}
