<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Header Example</title>
        <!-- Thêm CSS của bạn vào đây nếu cần -->
        <style>
           .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    background-color: #f8f9fa;
    border-bottom: 1px solid #e0e0e0;
}

.menu-icon {
    display: flex;
    align-items: center;
}

.menu-icon .material-icons-outlined {
    margin-right: 10px;
    cursor: pointer;
}

.header-right {
    display: flex;
    align-items: center;
}

.header-right .material-icons-outlined {
    margin-right: 20px;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropbtn {
    background: none;
    border: none;
    cursor: pointer;
}

.user-info {
    display: flex;
    align-items: center;
}

.user-info .avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
}

.user-info h5 {
    margin: 0;
    padding: 0 5px;
    font-size: 16px;
}

.user-info i {
    font-size: 14px;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {
    background-color: #ddd;
}

.dropdown:hover .dropdown-content {
    display: block;
    
}
.user-info .avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
}
.user-info h5 {
    margin: 0;
    padding: 0 5px;
    font-size: 16px;
}

.user-info i {
    font-size: 14px;
    color: white;
}

.user-info {
    display: flex;
    align-items: center;
}
.avatar{
    position: relative;
    width: 2.375rem;
    height: 2.375rem;
    cursor: pointer
}
.rounded-circle {
    border-radius: 1% !important;
}
.h-auto {
    height: auto !important;
}
.w-px-40 {
    width: 40px !important;
}
img {
    vertical-align: middle;
}
        </style>
    </head>
    <body>
        <header class="header">
            <div class="menu-icon" onclick="openSidebar()">
                <span class="material-icons-outlined">menu</span>
                <span class="material-icons-outlined" onclick="searchFunction()">search</span>                
            </div>
            <div class="header-right">
                <span class="material-icons-outlined">email</span>
                <div class="dropdown">
                    <button class="dropbtn">
                        <div class="user-info">
                            <div class="avatar">
                                <img style="width: 100px" src="${sessionScope.user.image}" alt="Avatar" class="w-px-40 h-auto rounded-circle">
                            </div>
                            <h5>${sessionScope.username}</h5>
                            <i class="fa fa-caret-down"></i>
                        </div>
                    </button>
                    <div class="dropdown-content">
                        <a href="/profile">Profile</a>
                        <a href="LoginController?service=logout">Logout</a>
                    </div>
                </div>
            </div>
        </header>

        <!-- Thêm JavaScript của bạn vào đây nếu cần -->

    </body>
</html>
