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
        <li class="sidebar-list-item"">
            <a href="AdminDashboard">
                                <span class="material-icons-outlined">dashboard</span> Dashboard
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="userList" target="_blank">
                <span class="material-icons-outlined">inventory_2</span> User
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SecurityQuestion" target="_blank">
                <span class="material-icons-outlined">feedback</span> Security Question
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SecurityQuestion" target="_blank">
                <span class="material-icons-outlined">feedback</span> Status Order
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
