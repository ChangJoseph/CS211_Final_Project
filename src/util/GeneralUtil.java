package util;

import java.io.Closeable;
import java.io.IOException;

public class GeneralUtil {


    public static void closeStream(Closeable stream) {
        try {
            if(stream != null) {
                stream.close();
            }
        }
        catch(IOException e){
            System.err.println("Stream was not closed properly");
            e.printStackTrace();
        }
    }
}