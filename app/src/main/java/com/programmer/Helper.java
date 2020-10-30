package com.programmer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Helper {

    public static final String DATABASE = "favorites.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_WEBSITES = "favorite_websites";
    public static final String TABLE_TUTORIALS = "favorite_tutorials";

    public static final String COLUMN_WEBSITE_POSITION = "website_position";
    public static final String COLUMN_TUTORIALS_POSITION = "tutorials_position";

    public static final String CREATE_WEBSITES_TABLE =
            "CREATE TABLE " + TABLE_WEBSITES + "(" + COLUMN_WEBSITE_POSITION + " TEXT PRIMARY KEY )";

    public static final String CREATE_TUTORIALS_TABLE =
            "CREATE TABLE " + TABLE_TUTORIALS + "(" + COLUMN_TUTORIALS_POSITION + " TEXT PRIMARY KEY )";

    public static final String DROP_WEBSITES_TABLE = "DROP TABLE IF EXISTS " + TABLE_WEBSITES;
    public static final String DROP_TUTORIALS_TABLE = "DROP TABLE IF EXISTS " + TABLE_TUTORIALS;

    public static int [] websitesImages = {
            R.mipmap.android, R.mipmap.java, R.mipmap.kotlin,
            R.mipmap.firebase, R.mipmap.material, R.mipmap.dart,
            R.mipmap.flutter, R.mipmap.swift, R.mipmap.apple,
            R.mipmap.udacity, R.mipmap.udemy, R.mipmap.coursera,
            R.mipmap.react, R.mipmap.ionic, R.mipmap.unity,
            R.mipmap.phone, R.mipmap.tutorials, R.mipmap.python,
            R.mipmap.php, R.mipmap.javascript, R.mipmap.w3schools,
            R.mipmap.micro, R.mipmap.stack, R.mipmap.github
    };

    public static int [] tutorialsImages = {
            R.mipmap.java, R.mipmap.kotlin
    };

    public static final String EXTRA_POSITION = "position";


    public static void toast(Context context, String message){
        @SuppressLint("InflateParams")
        View toastLayout = LayoutInflater.from(context).inflate(R.layout.toast_layout, null, false);
        TextView toastTextView = toastLayout.findViewById(R.id.text_view_toast);
        toastTextView.setText(message);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastLayout);
        toast.show();
    }

}
