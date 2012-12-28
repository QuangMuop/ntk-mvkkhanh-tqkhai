<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left (navigation) side -->
    <div class="navigation-block">

        <!-- User profile -->
        <section class="user-profile">
            <figure class="clearfix">
                <img alt="John Pixel avatar" src="${pageContext.request.contextPath}/shared-res/images/admin.jpg" />
                <figcaption>
                    <strong><a href="#" class="">Khánh Mã</a></strong>
                    <em>Admin</em>
                    <ul>
                        <li><a class="button" href="/BabyShop/authentication/logout" title="Thoát ra">Thoát</a></li>
                    </ul>
                </figcaption>
            </figure>
        </section>
        <!-- /User profile -->


        <!-- InstanceBeginEditable name="main-navigation" -->
        <!-- Main navigation -->
        <nav class="main-navigation">
            <ul>
                <li class="${(currentMenu == 'dashboard')?'current':''}"><a href="/BabyShop/dashboard/welcome" class="no-submenu"><span class="icon-home"></span>Dashboard</a></li>
                <li class="${(currentMenu == 'toyList' || currentMenu == 'toyAdd' || currentMenu == 'toyDetail' )?'current':''}">
                    <a href="#"><span class="icon-toy"></span>Quản lý đồ chơi</a>
                    <ul>
                    		<li><a href="/BabyShop/toy/add" class="${currentMenu == 'toyAdd'?'current':''}">Thêm đồ chơi</a></li>            	                       
                        	<li><a href="/BabyShop/toy/list?page=1" class="${currentMenu == 'toyList'?'current':''}">Danh sách đồ chơi</a></li>
                    </ul>
                </li>
                <li class="${(currentMenu == 'categoryList' || currentMenu == 'categoryAdd' || currentMenu == 'categoryDetail' )?'current':''}">
               		<a href="#"><span class="icon-toy-type"></span>Quản lý loại đồ chơi</a>
					<ul>
                        <li><a href="/BabyShop/category/add" class="${currentMenu == 'categoryAdd'?'current':''}">Thêm loại đồ chơi</a></li>
                        <li><a href="/BabyShop/category/list?page=1" class="${currentMenu == 'categoryList'?'current':''}">Danh sách loại đồ chơi</a></li>
                    </ul>
                </li>
               <li class="${(currentMenu == 'manufacturerList' || currentMenu == 'manufacturerAdd' || currentMenu == 'manufacturerDetail' )?'current':''}">
               		<a href="#"><span class="icon-manufacturer"></span>Quản lý nhà sản xuất</a>
					<ul>
                        <li><a href="/BabyShop/manufacturer/add" class="${currentMenu == 'manufacturerAdd'?'current':''}">Thêm nhà sản xuất</a></li>
                        <li><a href="/BabyShop/manufacturer/list?page=1" class="${currentMenu == 'manufacturerList'?'current':''}">Danh sách nhà sản xuất</a></li>
                    </ul>
                </li>
                <li class="${(currentMenu == 'accountList' || currentMenu == 'accountAdd' || currentMenu == 'accountDetail' )?'current':''}">
               		<a href="#"><span class="icon-people"></span>Quản lý người dùng</a>
					<ul>
                        <li><a href="/BabyShop/account/add" class="${currentMenu == 'accountAdd'?'current':''}">Thêm tài khoản</a></li>
                        <li><a href="/BabyShop/account/list?page=1" class="${currentMenu == 'accountList'?'current':''}">Danh sách tài khoản</a></li>
                    </ul>
                </li>
                <li class="${(currentMenu == 'cartList' || currentMenu == 'cartDetail' )?'current':''}">
               		<a href="#"><span class="icon-shopping-cart"></span>Quản lý hóa đơn</a>
					<ul>
                        <li><a href="/BabyShop/cart/list?page=1" class="${currentMenu == 'cartList'?'current':''}">Danh sách hóa đơn</a></li>
                    </ul>
                </li>
                <li class="${(currentMenu == 'commentList' || currentMenu == 'commentDetail' )?'current':''}">
               		<a href="#"><span class="icon-comment"></span>Quản lý bình luận</a>
					<ul>
                        <li><a href="/BabyShop/comment/list?page=1" class="${currentMenu == 'commentList'?'current':''}">Danh sách bình luận</a></li>
                    </ul>
                </li>
                <li class="${(currentMenu == 'advertisementList' || currentMenu == 'advertisementDetail')?'current':''}">
               		<a href="#"><span class="icon-advertisement"></span>Quản lý quảng cáo</a>
					<ul>
                        <li><a href="/BabyShop/advertisement/list" class="${currentMenu == 'advertisementList'?'current':''}">Danh sách quảng cáo</a></li>
                    </ul>
                </li>
                <li class="${(currentMenu == 'reportList')?'current':''}"><a href="/BabyShop/report/list" class="no-submenu"><span class="icon-report"></span>Báo cáo thống kê</a></li>
            </ul>
        </nav>          
        <!-- /Main navigation -->	
        <!-- InstanceEndEditable -->

    </div>
    <!-- Left (navigation) side -->