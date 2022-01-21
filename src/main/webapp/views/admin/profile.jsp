<%@ page language="java" isELIgnored="false" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/global_styles.jsp"></c:import>
<!-- jsGrid -->
<link rel="stylesheet" href="/mini-projet/resources/plugins/jsgrid/jsgrid.min.css">
<link rel="stylesheet" href="/mini-projet/resources/plugins/jsgrid/jsgrid-theme.min.css">
<c:import url="/header.jsp"></c:import>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1 class="m-0">Admin Update</h1>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <!-- general form elements -->
        <div class="card card-primary">
            <div class="card-header">
                <h3 class="card-title">Profile Information</h3>
            </div>
            <!-- /.card-header -->
            <form id="insert-item" action="/mini-projet/admin/profile" method="post">
                     <c:if test="${error!=null}">
                		<span class="text-danger">${error}</span>
            		</c:if>
            		 <c:if test="${success!=null}">
                		<center><span class="text-info">${success}</span></center>
            		</c:if>
                <div class="card-body">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Username</label>
                        <input type="text" name="username" value="${account.username}" class="form-control" id="exampleInputEmail1" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">First Name</label>
                        <input type="text" name="fname" value="${account.firstname}" class="form-control" id="exampleInputPassword1" placeholder="Enter Type ">
                    </div>
                    <div class="form-group">
                        <label for="exampleInpudate">Last Name</label>
                        <input type="text" name="lname" value="${account.lastname}" class="form-control" id="exampleInpudate" placeholder="Enter Type date">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputsalle">Phone</label>
                        <input type="text" name="phone" value="${account.phone}" class="form-control" id="exampleInputsalle" placeholder="Enter de Salle">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputsalle">Password</label>
                        <input type="password" name="pass" value="${account.password}" class="form-control" id="exampleInputsalle" placeholder="Enter de Salle">
                    </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
        <!-- /.card -->
    </section>
    <!-- /.content -->
    <!-- jsGrid -->
</div>
<c:import url="/global_scripts.jsp"></c:import>
<script>
</script>
<c:import url="/footer.jsp"></c:import>

