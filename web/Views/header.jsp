<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,Entity.Staff"%>
<%@page import="Entity.Security"%>
<%@page import="Entity.Role"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <style>
        .header {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            padding: 10px 20px; /* Adjust padding as needed */
            background-color: #f8f9fa; /* Example background color */
        }
        .header-right {
            display: flex;
            align-items: center;
        }
       .dropbtn {
    background-color: #fff; /* Button background color */
    border: none; /* Remove default button border */
    cursor: pointer; /* Pointer cursor on hover */
    display: flex;
    align-items: center;
    padding: 10px; /* Padding inside the button */
    border-radius: 8px; /* Rounded corners */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Box shadow for button */
    transition: background-color 0.3s; /* Smooth background color transition */
}

.dropbtn:hover {
    background-color: #f0f0f0; /* Background color on hover */
}

.user-info {
    display: flex;
    align-items: center;
    gap: 10px; /* Space between elements */
}

.avatar {
    display: flex;
    align-items: center;
    justify-content: center;
}

.profile-image {
    width: 50px !important; /* Adjust the width as needed */
    height: 50px; /* Ensures the image is a perfect circle */
    object-fit: cover; /* Ensures the image covers the entire area */
    border-radius: 80%; /* Makes the image circular */
    border: 6px solid #fff; /* Adds a thicker white border */
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.3); /* Adds a more pronounced shadow for better visibility */
}

.user-info h5 {
    margin: 0;
    font-size: 16px; /* Adjust the font size as needed */
    color: #333; /* Adjust the text color as needed */
}

.user-info i {
    margin-left: 5px; /* Adjust the spacing as needed */
    font-size: 14px; /* Adjust the icon size as needed */
    color: #333; /* Adjust the icon color as needed */
}

    </style>
    <body>
        <header class="header">
            <div class="header-right" style="text-align: right">
                <div class="dropdown">
                    <button class="dropbtn">
                        <div class="user-info">
                            <div class="avatar">
<img style="width: 100px" src="${sessionScope.user.image}" alt="Ảnh" class="profile-image">
                            </div>
                            <h5>${sessionScope.username}</h5>
                            <i class="fa fa-caret-down"></i>
                        </div>
                    </button>
                    <div class="dropdown-content">
                        <c:set  value="${sessionScope.user}" var="cus1"></c:set>

                        <c:if test="${not empty sessionScope.user}">
                            <a href="editProfileUserURL?userid=${cus1.getStaffID()}" class="dropdown-item">Trang cá nhân</a>
                            <a href="LoginController?service=logout">Đăng xuất</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
