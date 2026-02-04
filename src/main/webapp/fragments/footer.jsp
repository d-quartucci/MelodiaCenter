<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css" type="text/css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<footer>
<hr>
    <div class="footerContainer">

        <div class="footerInfo">
            <p>
                <strong>MelodiaCenter</strong>: Negozio online di strumenti musicali.<br>
            </p>
        </div>

        <div class="footerLinkUtili">
            <h4>Link utili</h4>
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/CatalogServlet">Catalogo</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/common/cart.jsp">Carrello</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="footerCR">
        <p>© 2025 MelodiaCenter – Progetto universitario</p>
    </div>
</footer>
</body>
</html>