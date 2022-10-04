package test;
/*
 * @author Sara Liu
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "searchProductServlet",
        urlPatterns = {"/searchProduct"})
public class ProductSearchServlet extends HttpServlet {

    ProductSearchModel psm = null;  // The business model for this app

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        psm = new ProductSearchModel();
    }

    // This servlet will reply to HTTP GET requests via this doGet method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            // get the search parameter if it exists
            String keyword = request.getParameter("searchWord");
            String skuID = request.getParameter("skuID");

            String nextView = "";
            /*
             * Check if the search parameter is present.
             * If not, then give the user instructions and prompt for a search string.
             * If there is a search parameter, then do the search and return the result.
             */
            List<productDetail> res = null;
            if(skuID != null && keyword != null) {

                if (skuID!= null && !skuID.equals("")) {
                    // use model to do the search and choose the result view
                    res = psm.searchInSolrByID(skuID);
                } else if (keyword != null && !keyword.equals("")) {
                    res = psm.searchInSolrByKeyword(keyword);
                } else {
                    res = psm.searchInSolrBy("", "default");
                }
                if(res == null) {
                    //error
                } else {
                    //collect information 20 entries
                    if(res.size()>20){
                        res = res.subList(0,10);
                    }
                    request.setAttribute("entryList", res);

                    nextView = "result.jsp";
                }
            } else {
                // no search parameter so choose the prompt view
                nextView = "prompt.jsp";
            }

            // Transfer control over the correct "view"
            RequestDispatcher view = request.getRequestDispatcher(nextView);
            view.forward(request, response);

        } catch (IOException e) {
        System.out.println(e);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}

