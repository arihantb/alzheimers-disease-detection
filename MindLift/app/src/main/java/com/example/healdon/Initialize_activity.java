package com.example.healdon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Initialize_activity {
    public String music, game, exercise, photo, tips, date;

    public Initialize_activity() {
        music ="0";
        game ="0";
        exercise ="0";
        photo ="0";
        date =  new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        tips ="0";
    }
}
