package com.example.simplechat;

import android.app.Application;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ChatApplication extends Application {
    // ...
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Message.class);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("BgMeRhXN4Mfsr4iu7cg2PJPITPJgn9T4meopyWPG") // provided in Lab instructions
                .clientKey("r6fZMD0BL8dZx0pVlYemVJbHwqaBtIRqO19SX40c") // provided in Lab instructions
                .clientBuilder(builder)
                .server("https://parseapi.back4app.com/").build());  // provided in Lab instructions

        // Use for monitoring Parse OkHttp traffic
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        // ...

    }


    // ...
}
