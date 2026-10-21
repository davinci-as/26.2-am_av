package ar.edu.davinci.a262_am_av;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {

    public ImageView details;

    public ImageDownloader(ImageView detailsImage) {
        this.details = detailsImage;
    }

    @Override
    protected Bitmap doInBackground(String... strings) { //Worker
        String url = strings[0];
        try {
            URL query = new URL(url);
            InputStream stream = (InputStream) query.getContent(); //Object -> InputStream
            return BitmapFactory.decodeStream(stream);  //InputStream -> Bitmap (Por medio de un Factory)
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(Bitmap downloadedImage) { //Tienen acceso a UI
        super.onPostExecute(downloadedImage);

        if(downloadedImage == null) return;

        //actualizar en la UI
        this.details.setImageBitmap(downloadedImage);
    }
}
