<%@ page language="java" contentType="text/html; charset=UTF-8" session="true" isELIgnored="false" pageEncoding="UTF-8"%>

<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
        <span class="brand-text font-weight-light">NOUHAILA OUAHBANI</span>
    </a>
    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="/mini-projet/resources/dist/img/noha-160x160.jpg" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="/mini-projet/admin/profile" class="d-block">${sessionScope.fullname}</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item">
                    <a href="/mini-projet/dashboard" class="nav-link active">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Dashboard
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/#" class="nav-link">
                        <p>
                            <i class="nav-icon fas fa-plus"></i>
                            Salle
                            <i class="fas fa-angle-left right"></i>


                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="/mini-projet/salle/list" class="nav-link">
                                <p>List</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="/mini-projet/salle/new" class="nav-link">
                                <p>Add</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a href="/#" class="nav-link">
                        <i class="nav-icon fas fa-plus"></i>
                        <p>
                            Machines
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="/mini-projet/machine/list" class="nav-link">
                                <p>List</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="/mini-projet/machine/new" class="nav-link">
                                <p>Add</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="/mini-projet/machine/salle" class="nav-link">
                                <p>Search par Salle</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="/mini-projet/machine/date" class="nav-link">
                                <p>Search par date</p>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>
