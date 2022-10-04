package test;

import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 *
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