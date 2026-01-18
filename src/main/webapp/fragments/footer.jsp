<%@ page contentType="text/html; charset=UTF-8" %>

<footer>
    <div class="footer-container">

        <div class="footer-section">
            <h4>MelodiaCenter</h4>
            <p>
                Negozio online di strumenti musicali.<br>
                Chitarre, bassi, tastiere e accessori.
            </p>
        </div>

        <div class="footer-section">
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

    <div class="footer-bottom">
        <p>© 2025 MelodiaCenter – Progetto universitario</p>
    </div>
</footer>