package com.example.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.R;

public class CustomProgressDialog {
    private static CustomProgressDialog ourInstance = new CustomProgressDialog();
    Dialog dialog;

    public static CustomProgressDialog getInstance() {
        return ourInstance;
    }

    private CustomProgressDialog() {

    }

    public void show(Context context) {

        try {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.pdialog);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            LinearLayout mkLoader = dialog.findViewById(R.id.llView);
            mkLoader.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
            dialog.show();
        } catch (Exception ignored) {

        }

    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public boolean isShowing() {
        if (dialog != null && dialog.isShowing())
            return true;
        else return false;
    }
}
