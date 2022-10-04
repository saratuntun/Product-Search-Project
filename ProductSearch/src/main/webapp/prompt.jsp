<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Search Result</title>
</head>
<body>

    <p>Search by skuID or keyword.</p>
    <form action="searchProduct" method="GET">
        <label for="kw">Type the keyword.</label>
        <input type="text" id="kw" name="searchWord" value="" /><br>
        <label for="sku">Type the skuID.</label>
        <input type="text" id="sku" name="skuID" value="" /><br>
        <input type="submit" value="Search" />
    </form>
</body>
</html>

