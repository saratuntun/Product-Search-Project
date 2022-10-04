package test;

/**
 * Product detail object.
 * Use to parse gson to java Object
 * format like:
 * {
 *   "AverageRating":4.6667,
 *   "ImageURL":"http://images.nyandcompany.com/is/image/NewYorkCompany/productdetaildefault/NY-C-Beauty-Lip-Gloss-Nude_08779747_935.jpg",
 *   "id":"31885",
 *   "SkuID":"81002755",
 *   "ProdDescription":"NY&C Beauty - Lip Gloss - Nude A matte nude shade on this lip gloss from our NY&C Beauty Collection is a versatile beauty accent, now and all year long!<br><br><h3>overview</h3><ol><li>Nude color lip gloss.</li><li>Finish: Matte.</li><li>0.21 ounces.</li><li>Domestic.</li></ol> ",
 *    "_version_":1745675963359821846
 * }
 */
public class productDetail {
    //corresponding fields with json
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
