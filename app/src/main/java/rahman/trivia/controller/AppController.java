package rahman.trivia.controller;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.collection.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG=AppController.class.getSimpleName();
    private static AppController instance;
    private RequestQueue requestQueue;

    public AppController() {
    }


    public static synchronized AppController getInstance() {
//        if (instance == null) {
//            instance = new AppController(context);
//        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {

            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

//    public ImageLoader getImageLoader() {
//        return imageLoader;
//    }
}