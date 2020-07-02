package com.myproject.ziffytech.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilMethods {


    public static void mt(Context context , String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public static String convertDate(int inputDate) {

        String convertedDate = "";

        Date datenew = new Date(inputDate);
        SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MM-yyyy", Locale.ROOT);
        convertedDate = sdfnew.format(datenew);

        return convertedDate;
    }

}
