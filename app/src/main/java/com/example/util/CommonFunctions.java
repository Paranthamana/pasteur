package com.example.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;
import com.example.application.MyApplication;
import com.example.model.ErrorApiResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import okhttp3.ResponseBody;

public class CommonFunctions {


    //public static String fontPath = "fonts/font_regular.ttf";

    private static CommonFunctions ourInstance = new CommonFunctions();

    public static CommonFunctions getInstance() {
        return ourInstance;
    }

    public static Socket socket;

    private CommonFunctions() {
    }

    public void newIntent(Context context, Class destinationClass, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    public void validationInfoError(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorIndicator));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        //snackbar.show();

        MDToast mdToast = MDToast.makeText(context, message, Toast.LENGTH_SHORT, MDToast.TYPE_INFO);
        mdToast.show();
    }
}
