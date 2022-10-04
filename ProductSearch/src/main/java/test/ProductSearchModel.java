package test;

/*
 * @author Sara Liu
 * 
 * This file is the Model component of the MVC, and it models the business
 * logic for the web application.  In this case, the business logic involves
 * making a request to flickr.com and then screen scraping the HTML that is
 * returned in order to fabricate an image URL.
 */
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ProductSearchModel {
    searchResponseJson searchResponse = null;
    responseHeaderJson responseHeader = null;
    responseBodyJson responseBody = null;


    /**
     * When user search by skuID, it will return a list (contains one product record).
     * @param skuID skuID user input
     * @return ArrayList of product entries
     */
    public ArrayList<productDetail> searchInSolrByID(String skuID) {
        return searchInSolrBy(skuID, "skuID");
    }

    /**
     * When user search by keyword, it will return a list of at most 20 result.
     * @param keyword keyword user input
     * @return ArrayList of product entries
     */
    public ArrayList<productDetail> searchInSolrByKeyword(String keyword) {
        return searchInSolrBy(keyword, "keyword");
    }

    /**
     * When user search by keyword, it will return a list of at most 20 result.
     * when both id and keyword provided, id is the first priority
     * @param input keyword or skuID
     * @param by by keyword or by skuID
     * @return ArrayList of product entries
     */
    public ArrayList<productDetail> searchInSolrBy(String input, String by) {
        ArrayList<productDetail> productList = new ArrayList<>();
        try{
            String searchURL = "";
            input = URLEncoder.encode(input, "UTF-8");

            if (by.equals("skuID")) {
                searchURL =
                        "http://localhost:8983/solr/new_core/select?indent=true&q.op=OR&q=SkuID%3A%20" + input +"&rows=20";

            } else if (by.equals("keyword")){
                searchURL =
                        "http://localhost:8983/solr/new_core/select?indent=true&q.op=OR&q=ProdDescription%3A" + input +"&rows=20";
            } else {
                searchURL = "http://localhost:8983/solr/new_core/select?indent=true&q.op=OR&q=*%3A*&rows=20";
            }
            System.out.println(searchURL);
            // convert json string into object created
            String response = fetch(searchURL);
            System.out.println(response);
            Gson gson = new Gson();
            // parse1: to searchResponse object { header; body }
            searchResponse = gson.fromJson(response, searchResponseJson.class);
            // convert Header and Object JsonObject to Json String
            String responseHeaderJsonString = gson.toJson(searchResponse.getResponseHeader());
            String responseBodyJsonString = gson.toJson(searchResponse.getResponseBody());

            // parse2: Json String to Java Object
            responseHeader = gson.fromJson(responseHeaderJsonString, responseHeaderJson.class);
            responseBody = gson.fromJson(responseBodyJsonString, responseBodyJson.class);

            for(int i = 0; i < responseBody.getDocs().size(); i++) {
                String product_content_string = gson.toJson(responseBody.getDocs().get(i));
                productDetail productInfo = gson.fromJson(product_content_string, productDetail.class);
                productList.add(productInfo);
            }
            for(int i = 0; i < productList.size(); i++){
                System.out.println(productList.get(i).getAverageRating());
                System.out.println(productList.get(i).getId());
                System.out.println(productList.get(i).getImageURL());
                System.out.println(productList.get(i).getSkuID());
                System.out.println(productList.get(i).getProdDescription());
            }

        } catch (UnsupportedEncodingException e) {
            System.out.println("Error when encoding input with url usable format.");
        }
        return productList;

    }

    /**
     * Make an HTTP request to a given URL.
     * @param urlString urlString The URL of the request
     * @return A string of the response from the HTTP GET.
     */
    private String fetch(String urlString) {
        String response = "";
        try {
            URL url = new URL(urlString);
            // Create an HttpURLConnection.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Cannot access to the url");
        }
        return response;
    }
}
