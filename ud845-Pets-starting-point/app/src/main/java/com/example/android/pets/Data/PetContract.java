package com.example.android.pets.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Engr.Uzma on 09/09/2016.
 */
public  final class PetContract {

    public static String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";






    /**
     * ineer class pets entry
     */
    public static class PetsEntry implements BaseColumns {

        public static final String TABLE_NAME="pet";
        public  int DATABASE_VERSION=1;
        public static String ID=BaseColumns._ID;
        public static final String COLUM_NAME="NAME";
        public static final String COLUM_BREED="BREED";
        public static final String COLUM_GENDER="GENDER";
        public static final String COLUM_WEIGHT="WEIGHT";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);


        /**
         * GENDERS VALUE;
         *
         */
        public static final int MALE=1;
        public static final int FEMALE=2;
        public static final int UNKNOWN=0;


    }



}
