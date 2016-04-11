package com.adamant.boxoffice;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;

/**
 * Created by jayesh on 14-03-2016.
 */
public class BoxOfficeApp extends Application {



        private static BoxOfficeApp mInstance;
        private static Context mAppContext;


        public static BoxOfficeApp getInstance() {
            return mInstance;
        }

        public static Context getAppContext() {
            return mAppContext;
        }





        @Override
        public void onCreate() {
            super.onCreate();

            mInstance = this;
            mAppContext = this.getApplicationContext();
            Picasso p=new Picasso.Builder(this).defaultBitmapConfig(Bitmap.Config.RGB_565).build();
            Picasso.setSingletonInstance(p);
            /*
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mAppContext)
                    .denyCacheImageMultipleSizesInMemory()
                    .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                            .resetViewBeforeLoading(true)
                            .cacheOnDisk(true)
                            .cacheInMemory(true)
                            .bitmapConfig(Bitmap.Config.RGB_565)
                            .considerExifParams(true)
                            .showImageOnLoading(R.drawable.product_bg_grey)
                            .build())
                    .diskCacheSize(100 * 1024 * 1024)
                    .build();

            ImageLoader.getInstance().init(config);*/


        }
}
