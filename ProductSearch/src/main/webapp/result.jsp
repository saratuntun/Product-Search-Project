<%@ page import="test.productDetail" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <title>DashBoard for Product Search</title>
    </head>
    <body>
        <% if (request.getAttribute("entryList") != null) { %>
        <h1>Here is search result: </h1><br>
        <table style="width:100%">
            <tr>
                <th>ID</th>
                <th>SkuID</th>
                <th>ImageURL</th>
                <th>ProdDescription</th>
            </tr>
            <%ArrayList<productDetail> entry=(ArrayList<productDetail>) request.getAttribute("entryList");
                for (productDetail e: entry) {%>
            <tr>
                <th><%=e.getId()%></th>
                <th><%=e.getSkuID()%></th>
                <th><%=e.getImageURL()%></th>
                <th><%=e.getProdDescription()%></th>
            </tr>
            <%}%>
        </table>

        <% } else { %>
        <h1>No record found</h1><br>
        <% } %>
        <form action="searchProduct" method="GET">
            <label for="kw">Type the keyword.</label>
            <input type="text" id="kw" name="searchWord" value="" /><br>
            <input type="submit" value="Click Here" />
            <label for="sku">Type the skuID.</label>
            <input type="text" id="sku" name="skuID" value="" /><br>
        </form>
    </body>
</html>

