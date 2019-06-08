package com.digitalexplorers.tipify.App;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ConnectionQuality;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ConnectionQualityChangeListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.digitalexplorers.tipify.Constants.Constants;
import com.digitalexplorers.tipify.util.EasyPermission;
import com.digitalexplorers.tipify.util.TinyDB;


import org.json.JSONException;
import org.json.JSONObject;


import okhttp3.OkHttpClient;
/**
 * Created by izzy
 */
public class MyApplication extends Application implements Constants {

    private static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication appInstance = null;
    private static EasyPermission easyPermission;
    public static synchronized MyApplication getInstance() {
        return appInstance;
    }
    private TinyDB tinyDB;
    private String username;
    private String email;
    private String token ="";



    public String getToken() {
        return token;
    }

    public TinyDB getTinyDB(){
        return this.tinyDB;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        appInstance = this;
        tinyDB = new TinyDB(getApplicationContext());
        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
               // .addNetworkInterceptor(new StethoInterceptor())
                .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        AndroidNetworking.setBitmapDecodeOptions(options);
        AndroidNetworking.enableLogging();
        AndroidNetworking.setConnectionQualityChangeListener(new ConnectionQualityChangeListener() {
            @Override
            public void onChange(ConnectionQuality currentConnectionQuality, int currentBandwidth) {
                Log.d(TAG, "onChange: currentConnectionQuality : " + currentConnectionQuality + " currentBandwidth : " + currentBandwidth);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
       // SugarContext.terminate();
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    public void updateProfile() {
        if (MyApplication.getInstance().isConnected()) {
            AndroidNetworking.get(GET_USER_DETAILS)
                    .addHeaders("Authorization", "Token " + tinyDB.getString("auth_token"))
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {


                        }
                        @Override
                        public void onError(ANError error) {

                        }
                    });
        }
    }


//    public void readData() {
//        this.setId(tinyDB.getLong(getString(R.string.settings_account_id), 0));
//        this.setUsername(tinyDB.getString(getString(R.string.settings_account_username)));
//    }
//    public void saveData() {
//        tinyDB.putLong(getString(R.string.settings_account_id), this.getId());
//        tinyDB.putString("token", this.getToken());
//    }
//    public long getId() {
//        return this.id;
//    }
//    public void setId(long id) {
//        this.id = id;
//    }


}