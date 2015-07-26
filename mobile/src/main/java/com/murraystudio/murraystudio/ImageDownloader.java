package com.murraystudio.murraystudio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by sushi_000 on 7/25/2015.
 */
public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    ImageButton buttonImage;

    public ImageDownloader(ImageButton bmImage) {
        this.buttonImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
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
            resized = Bitmap.createScaledBitmap(bitmap, (height/8), (width/8), true);
        } catch (Exception e) {
            Log.e("MyApp", e.getMessage());
        }
        return resized;
    }

    protected void onPostExecute(Bitmap result) {
        buttonImage.setImageBitmap(result);
    }
}