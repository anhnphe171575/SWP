<%@page contentType="text/html" pageEncoding="UTF-8"%>

<aside id="sidebar">
    <div class="sidebar-title">
        <div class="sidebar-brand">
            <span class="material-icons-outlined">shopping_cart</span> STORE
        </div>
        <span class="material-icons-outlined" onclick="closeSidebar()">close</span>
    </div>

    <ul class="sidebar-list">
        <li class="sidebar-list-item">
            <a href="SaleDashboardURL">
                <span class="material-icons-outlined">dashboard</span> Sale Dashboard
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="orderlist">
                <span class="material-icons-outlined">assignment</span> Order
            </a>
        </li>
         <li class="sidebar-list-item">
            <a href="productslist">
                <span class="material-icons-outlined">assignment</span> Product List
            </a>
        </li>
    </ul>
</aside>
