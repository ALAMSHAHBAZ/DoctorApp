package com.example.lenovo.doctorapp.dbutil;

/**
 * Created by lenovo on 7/8/2017.
 */

public class Constants {
    public static final String DB_NAME="doctorapp1";


    public static final int DB_VERSION=1;
    public static final String TBL_NAME="clerk";
    public static final String COL_ID="id";
    public static final String COL_NAME="name";
    public static final String COL_PASS="password";
    public static final String COL_EMAIL="email";
    public static final String COL_PHONE="phone";
    public static final String COL_PIC="picture";



    public static final String P_TBL_NAME="patient";
    public static final String P_COL_TOKEN="token";
    public static final String P_COL_NAME="name";
    public static final String P_COL_PHONE="phone";
    public static final String P_COL_PROB="problem";
    public static final String P_COL_DATE="date";
    public static final String P_COL_PIC="picname";


    public static final
    String TBL_QUERY="create table "+TBL_NAME+"("+COL_ID+" integer primary key,"+COL_NAME+" text,"+COL_PASS+" text,"+COL_EMAIL+" text,"+COL_PHONE+" text,"+COL_PIC+" blob)";


    public static final
    String P_TBL_QUERY="create table "+P_TBL_NAME+"("+P_COL_TOKEN+"" + " integer primary key autoincrement,"+P_COL_NAME+" text,"+P_COL_PHONE+" text,"+P_COL_PROB+" text,"+P_COL_DATE+" text,"+P_COL_PIC+" blob)";


}
