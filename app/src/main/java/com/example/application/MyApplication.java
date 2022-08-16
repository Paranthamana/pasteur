package com.example.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.widget.Toast;

import androidx.multidex.MultiDex;

import com.example.util.Constants;
import com.valdesekamdem.library.mdtoast.MDToast;
import org.json.JSONObject;

import java.util.Locale;

public class    MyApplication extends Application {

    public static Context context;
    private Locale locale = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
        /*Fabric.with(this, new Crashlytics());
        //Fabric.with(this, new Crashlytics());
        context = getBaseContext();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationReceivedHandler(new NotificationReceivedHandler())
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        Realm.init(context);
        new RealmConfiguration.Builder()
                .name(ConstantsInternal.DBName.toLowerCase() + ".realm")
                .schemaVersion(42)
                .deleteRealmIfMigrationNeeded()
                .build();*/

        /*Configuration config = getBaseContext().getResources().getConfiguration();
        locale = new Locale("en");
        Locale.setDefault(locale);
        setSystemLocale(config, locale);
        updateConfiguration(config);*/
    }

    /*class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data = notification.payload.additionalData;
            String customKey;
        }
    }*/


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (locale != null) {
            setSystemLocale(newConfig, locale);
            Locale.setDefault(locale);
            updateConfiguration(newConfig);
        }
    }

    @SuppressWarnings("deprecation")
    private static void setSystemLocale(Configuration config, Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
    }

    @SuppressWarnings("deprecation")
    private void updateConfiguration(Configuration config) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getBaseContext().createConfigurationContext(config);
        } else {
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    public static void displayKnownError(Context mContext, String message) {
        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();
    }

    public static void displayKnownErrorSuccess(Context mContext, String message) {
        //CommonFunctions.getInstance().ShowSnackBar(mContext, message);
        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
        mdToast.show();
    }

    public static void displayUnKnownError(Context mContext) {
        MDToast mdToast = MDToast.makeText(context, Constants.SomethingWentWrong, Toast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();
        //CommonFunctions.getInstance().ShowSnackBar(mContext, );
    }

    public static void displayUnKnownError() {
        Toast.makeText(context, Constants.SomethingWentWrong, Toast.LENGTH_LONG).show();
    }

    public static void result(Context mContext, String message) {
       // CommonFunctions.getInstance().ShowSnackBar(mContext, message);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
