package com.witanowski.tapptic.data.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import okhttp3.Request;

/**
 * Created by Michal Witanowski on 2017-11-21.
 */

public class APIUtils {
    public static Request getRequest(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
