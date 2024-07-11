<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>

</style>
<aside id="sidebar">
    <div class="sidebar-title">
        <div class="sidebar-brand">
            <span class="material-icons-outlined">shopping_cart</span> STORE
        </div>
        <span class="material-icons-outlined" onclick="closeSidebar()">close</span>
    </div>

    <ul class="sidebar-list">
        <li class="sidebar-list-item">
            <a href="MKTDashboard">
                <span class="material-icons-outlined">dashboard</span> Dashboard
            </a>
            <ul class="submenu">
                <li class="sidebar-list-item"><a href="DBpost" >Post</a></li>
                <li class="sidebar-list-item"><a href="DBcus">Customer</a></li>
                <li class="sidebar-list-item"><a href="DBpro">Product</a></li>
                <li class="sidebar-list-item"><a href="DBfeedback">Feedback</a></li>
            </ul>
        </li>
        <li class="sidebar-list-item">
            <a href="productslist">
                <span class="material-icons-outlined">inventory_2</span> Products
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="FeedBackList">
                <span class="material-icons-outlined">feedback</span> FeedBack
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="CustomerServletURL">
                <span class="material-icons-outlined">groups</span> Customers
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="PostController">
                <span class="material-icons-outlined">fact_check</span> Post
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SliderServletURL">
                <span class="material-icons-outlined">poll</span> Slide
            </a>
        </li>
    </ul>
</aside>

<script>
    function toggleSubmenu(event) {
        event.preventDefault();
        const parent = event.currentTarget;
        parent.classList.toggle('active');
    }

</script>
