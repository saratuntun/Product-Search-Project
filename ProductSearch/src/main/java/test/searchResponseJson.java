package test;

import com.google.gson.JsonObject;

/**
 *
 */
public class searchResponseJson {
    private JsonObject responseHeader;
    private JsonObject response;

    public JsonObject getResponseHeader() {
        return responseHeader;
    }

    public JsonObject getResponseBody() {
        return response;
    }

}