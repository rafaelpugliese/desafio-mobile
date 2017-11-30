package com.mobile.desafio.repositoriosgithub.util.imageloader;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public final static String DATE_FORMAT_dd_MM_HH_mm = "dd/MM - HH:mm";

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static String formatDate(Date date, String format) {

        SimpleDateFormat formater = new SimpleDateFormat(format);

        return formater.format(date);
    }

}