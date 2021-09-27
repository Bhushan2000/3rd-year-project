package com.example.myvenue.util;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;

import com.example.myvenue.R;

public class CustomView {

    public static Dialog loadingDialogue;

    public static void showDialog(Context context) {

        ////////////////////////////////// loading dialogue /////////////////////////
        loadingDialogue = new Dialog(context);
        loadingDialogue.setContentView(R.layout.loadingprogressdialogue);
        loadingDialogue.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.slider_background));
        loadingDialogue.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialogue.setCancelable(true);
        loadingDialogue.show();
        ////////////////////////////////// loading dialogue /////////////////////////
    }

    public static void hideDialog() {

        ////////////////////////////////// loading dialogue /////////////////////////

        loadingDialogue.dismiss();
        ////////////////////////////////// loading dialogue /////////////////////////
    }

}
