package examples.com.fireapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by 2114 on 13-01-2017.
 */

public class FireApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
