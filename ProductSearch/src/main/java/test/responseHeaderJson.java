package test;
import com.google.gson.JsonObject;

/**
 * responseHeader object.
 * Use to parse gson to java Object
 * format like:
 * {
 *       "status":0,
 *       "QTime":11,
 *       "params":{}
 *    },
 */
public class responseHeaderJson {
    private String status;
    private String QTime;
    private JsonObject params;

    public String getStatus() {
        return status;
    }
}