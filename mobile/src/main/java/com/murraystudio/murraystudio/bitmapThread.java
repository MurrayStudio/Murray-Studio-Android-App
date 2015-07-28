package com.murraystudio.murraystudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by sushi_000 on 7/25/2015.
 */
public class bitmapThread extends AsyncTask<String, Void, Bitmap> {
    private ImageButton buttonImage;
    private Context context;

    public bitmapThread(Context c, ImageButton bmImage) {
        this.context = c;
        this.buttonImage = bmImage;
    }

/*    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bitmap = null;
        Bitmap resized = null;
        int height;
        int width;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
            height = bitmap.getHeight();
            width = bitmap.getWidth();

            //calculate slideshow height and width based off screen dimensions and
            //image size of 1400x500
            DisplayMetrics displaymetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(displaymetrics);
            int imageHeight = height;
            int imageWidth = width;
            //shrink images
            imageHeight = imageHeight/8;
            imageWidth = imageWidth/8;
            int containerWidth = displaymetrics.widthPixels;
            containerWidth = containerWidth/2;
            int ratioWidth;
            if(imageWidth > containerWidth) {
                ratioWidth = imageWidth / containerWidth;
            }
            else{
                ratioWidth = containerWidth / imageWidth;
            }
            int finalWidth = (imageWidth / ratioWidth);
            int finalHeight = (imageHeight / ratioWidth);

            //set new height and width for imageButton
           // result.setWidth(finalWidth);
           // result.setHeight(finalHeight);


            resized = Bitmap.createScaledBitmap(bitmap, finalHeight, finalWidth, true);
        } catch (Exception e) {
            Log.e("MyApp", e.getMessage());
        }
        return resized;
    }*/

    @Override
    protected Bitmap doInBackground(String... params) {
        return null;
    }

    protected void onPostExecute(Bitmap result) {
        buttonImage.setImageBitmap(result);
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