package com.mobile.desafio.repositoriosgithub.util.imageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.github.siyamed.shapeimageview.CircularImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    private MemoryCache memoryCache = MemoryCache.getInstance();
    private FileCache fileCache;
    private Map<CircularImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<CircularImageView, String>());
    private ExecutorService executorService;

    public ImageLoader(Context contexto) {
        fileCache = new FileCache(contexto);
        executorService = Executors.newFixedThreadPool(5);
    }

    public void loadImage(String imageURL, CircularImageView circularImageView, int loadingImage) {

        imageViews.put(circularImageView, imageURL);

        Bitmap bitmap = memoryCache.get(imageURL);
        if (bitmap != null) {
            circularImageView.setImageBitmap(bitmap);
        } else {
            queuePhoto(imageURL, circularImageView, loadingImage);
            circularImageView.setImageResource(loadingImage);
        }
    }

    private void queuePhoto(String url, CircularImageView circularImageView, int loadingImage) {
        PhotoToLoad p = new PhotoToLoad(url, circularImageView, loadingImage);
        executorService.submit(new PhotosLoader(p));
    }


    private Bitmap getBitmap(String url) {
        File f = fileCache.getFile(url);

        //from SD cache
        Bitmap b = decodeFile(f);
        if (b != null)
            return b;

        //from web
        try {
            Bitmap bitmap;
            URL imageUrl = new URL(url);
            HttpURLConnection conn;


            String port = System.getProperty("http.proxyPort");
            String hostName = System.getProperty("http.proxyHost");

            if (port != null && hostName != null) {
                int portInt;
                portInt = Integer.valueOf(port.equals("-1") ? "3128" : port);

                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostName, portInt));
                conn = (HttpURLConnection) imageUrl.openConnection(proxy);

            } else {
                conn = (HttpURLConnection) imageUrl.openConnection();
            }

            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 70;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    //Task for the queue
    private class PhotoToLoad {
        private String url;
        private CircularImageView circularImageView;
        private int loadingImage;

        PhotoToLoad(String u, CircularImageView c, int loadingImage) {
            url = u;
            circularImageView = c;
            this.loadingImage = loadingImage;
        }
    }

    private class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;

        PhotosLoader(PhotoToLoad photoToLoad) {
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {

            if (imageViewReused(photoToLoad)) {
                return;
            }
            Bitmap bmp = getBitmap(photoToLoad.url);
            memoryCache.put(photoToLoad.url, bmp);
            if (imageViewReused(photoToLoad))
                return;

            //if (photoToLoad.circularImageView != null) {
            BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad, photoToLoad.loadingImage);
            Activity a = (Activity) photoToLoad.circularImageView.getContext();
            a.runOnUiThread(bd);
            //}
        }

        private boolean imageViewReused(PhotoToLoad photoToLoad) {
            String tag = imageViews.get(photoToLoad.circularImageView);
            return tag == null || !tag.equals(photoToLoad.url);
        }

        //Used to display bitmap in the UI thread
        private class BitmapDisplayer implements Runnable {
            Bitmap bitmap;
            PhotoToLoad photoToLoad;
            int loadingImage;

            BitmapDisplayer(Bitmap b, PhotoToLoad p, int li) {
                bitmap = b;
                photoToLoad = p;
                loadingImage = li;
            }

            BitmapDisplayer(Bitmap b, PhotoToLoad p) {
                bitmap = b;
                photoToLoad = p;
            }

            public void run() {
                if (imageViewReused(photoToLoad)) {
                    return;
                }

                if (bitmap != null) {
                    photoToLoad.circularImageView.setImageBitmap(bitmap);
                } else {
                    photoToLoad.circularImageView.setImageResource(loadingImage);
                }

            }
        }

        public void clearCache() {
            memoryCache.clear();
            fileCache.clear();
        }
    }
}