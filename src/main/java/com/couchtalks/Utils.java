package com.couchtalks;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by NShkarupa on 08.08.2017.
 */
public class Utils {
    public static String parseException(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}
