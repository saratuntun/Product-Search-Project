package test;

import com.google.gson.JsonObject;
import java.util.ArrayList;

/**
 * responseBody object.
 * Use to parse gson to java Object
 *  format like
 *  {    "numFound":144,
 *       "start":0,
 *       "numFoundExact":true,
 *       "docs": []
 *   }
 */
public class responseBodyJson {
    private String numFound;
    private String start;
    private String numFoundExact;
    private ArrayList<JsonObject> docs;

    public String getNumFound() {
        return numFound;
    }

    public ArrayList<JsonObject> getDocs() {
        return docs;
    }
}