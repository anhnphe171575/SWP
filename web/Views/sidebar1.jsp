<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="sidebar">
    <div class="sidebar-title">
        <div class="sidebar-brand">
            <span class="material-icons-outlined">shopping_cart</span> STORE
        </div>
        <span class="material-icons-outlined" onclick="closeSidebar()">close</span>
    </div>
   
    <ul class="sidebar-list">
         <c:if test="${sessionScope.user.getRole().getRoleID() == 3}">
        <li class="sidebar-list-item">
            <a href="SaleDashboardURL">
                <span class="material-icons-outlined">dashboard</span> Sale Dashboard
            </a>
        </li>
         </c:if>
       <c:if test="${sessionScope.user.getRole().getRoleID() == 3 || sessionScope.user.getRole().getRoleID() == 2 || sessionScope.user.getRole().getRoleID() == 4}">
        <li class="sidebar-list-item">
            <a href="orderlist">
                <span class="material-icons-outlined">assignment</span> Order
            </a>
        </li>
        </c:if>
         <c:if test="${ sessionScope.user.getRole().getRoleID() == 1 || sessionScope.user.getRole().getRoleID() == 4}">
                        <li class="sidebar-list-item">
            <a href="productslist">
                <span class="material-icons-outlined">assignment</span> Product
            </a>
        </li>
          </c:if>
          <c:if test="${    sessionScope.user.getRole().getRoleID() == 4}">
                <li class="sidebar-list-item">
            <a href="TransactionURL">
                <span class="material-icons-outlined">assignment</span> Transaction

            </a>
        </li>
        </c:if>
    </ul>
</aside>
