package com.murraystudio.murraystudio;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by sushi_000 on 7/27/2015.
 */
public class GalleryImage {
    private int drawableID;
    private String name;
    private Context context;
    Drawable drawable;
    private Drawable scaledDrawable;

    public GalleryImage(int drawableID, String name, Context c){
        this.drawableID = drawableID;
        this.name = name;
        this.context = c;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public Drawable getScaledDrawable() {
        drawable = context.getResources().getDrawable(drawableID);
        //resize(drawable);
        drawable = null;
        return scaledDrawable;
    }

    public String getName() {
        return name;
    }

    public Bitmap getBitmap(Resources resources, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}