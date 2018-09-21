package com.galleryappusingglide.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized AppController getmInstance(){
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        //set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getmRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getmRequestQueue().add(req);
    }
    public void cancelPendingRequest(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
