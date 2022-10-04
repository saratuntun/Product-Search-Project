package test;
import com.google.gson.JsonObject;
import java.util.ArrayList;


public class productDetail {
    private String AverageRating;
    private String ImageURL;
    private String id;
    private String SkuID;
    private String ProdDescription;
    private String _version_;

    public Double getAverageRating() {
        return Double.parseDouble(AverageRating);
    }

    public String getImageURL() {
        return ImageURL;
    }

    public String getId() {
        return id;
    }

    public String getSkuID() {
        return SkuID;
    }

    public String getProdDescription() {
        return ProdDescription;
    }
}
