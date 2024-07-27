<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>

</style>
<aside id="sidebar">
    <div class="sidebar-title">
        <div class="sidebar-brand">
            <span class="material-icons-outlined"></span> ADMIN 
        </div>
        <span class="material-icons-outlined" onclick="closeSidebar()">close</span>
    </div>

    <ul class="sidebar-list">
        <li class="sidebar-list-item"">
            <a href="">
                <span class="material-icons-outlined">dashboard</span> Biểu đồ
            </a>
        </li>
                <li class="sidebar-list-item">
            <a href="AdminSettingURL">
                <span class="material-icons-outlined">settings</span> Cài Đặt
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="userList">
                <span class="material-icons-outlined">person</span> Quản lí nhân viên
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="SecurityQuestion">
                <span class="material-icons-outlined">help_outline</span> Câu hỏi bảo mật
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="editStatusOrderURL">
                <span class="material-icons-outlined">assignment_turned_in  </span> Trạng thái đơn hàng
            </a>
        </li>
         <li class="sidebar-list-item">
            <a href="editRoleURL">
                <span class="material-icons-outlined">person </span> Vai trò
            </a>
        </li>
        <li class="sidebar-list-item">
            <a href="CategoryServletURL">
                <span class="material-icons-outlined">person </span> Loại sản phẩm
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
