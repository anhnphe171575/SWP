<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>

</style>
<aside id="sidebar">
    <div class="sidebar-title">
        <div class="sidebar-brand">
            <span class="material-icons-outlined"></span> ADMIN STORE
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
            <a href="userList">
                <span class="material-icons-outlined">person</span> User
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SecurityQuestion">
                <span class="material-icons-outlined">help_outline</span> Security Question
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SecurityQuestion">
                <span class="material-icons-outlined">assignment_turned_in  </span> Status Order
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SecurityQuestion">
                <span class="material-icons-outlined">settings</span> Setting
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
