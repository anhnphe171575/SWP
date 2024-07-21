<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>

</style>
<aside id="sidebar">
    <div class="sidebar-title">
        <div class="sidebar-brand">
            <span class="material-icons-outlined">shopping_cart</span>QUẢN LÝ
        </div>
    </div>

    <ul class="sidebar-list">
        <li class="sidebar-list-item">
            <a href="MKTDashboard">
                <span class="material-icons-outlined">dashboard</span> BIỂU ĐỒ
            </a>
            <ul class="submenu">
                <li class="sidebar-list-item"><a href="DBpost" >Bài viết</a></li>
                <li class="sidebar-list-item"><a href="DBcus">Khách hàng</a></li>
                <li class="sidebar-list-item"><a href="DBpro">Sản Phẩm</a></li>
                <li class="sidebar-list-item"><a href="DBfeedback">Phản hồi</a></li>
            </ul>
        </li>
        <li class="sidebar-list-item">
            <a href="productslist">
                <span class="material-icons-outlined">inventory_2</span> SẢN PHẨM
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="FeedBackList">
                <span class="material-icons-outlined">feedback</span> PHẢN HỒI
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="CustomerServletURL">
                <span class="material-icons-outlined">groups</span> KHÁCH HÀNG
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="PostController">
                <span class="material-icons-outlined">fact_check</span> BÀI VIẾT
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SliderServletURL">
                <span class="material-icons-outlined">poll</span> THANH TRƯỢT
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
