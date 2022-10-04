package test;

import com.google.gson.JsonObject;

/**
 * searchResponse object.
 * Use to parse gson to java Object
 * format like:
 * {
 *    "responseHeader":{},
 *    "response":{}
 * }
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