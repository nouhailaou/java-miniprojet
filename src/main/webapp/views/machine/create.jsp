<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <h1 class="m-0">Machine Add</h1>
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
                        <h3 class="card-title">Add Salle</h3>
                    </div>
                    <!-- /.card-header -->
                    <form id="insert-item">
                        <div class="card-body">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Reference</label>
                                <input name="reference" type="text" required class="form-control" id="exampleInputEmail1" placeholder="Enter Reference">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Marque</label>
                                <input type="text" name="type" required class="form-control" id="exampleInputPassword1" placeholder="Enter Type ">
                            </div>
                            <div class="form-group">
                                <label for="exampleInpudate">Date d'achat</label>
                                <input type="date" name="dateAchat" required class="form-control" id="exampleInpudate" placeholder="Enter Date">
                            </div>
                            <div class="form-group">
                                <label>Salle</label>
                                <select name= "salle_id" required class="form-control">
                                    <option value="" disabled selected>Select Salle </option>
                                    <c:forEach var="y" items="${listSalle}">
                                        <option value="${y.id}">${y.code}</option>
                                    </c:forEach>
                                </select>
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

    $(function () {
        $("#insert-item").submit(function(e) {
            e.preventDefault();
            const form = $(this);
            console.log({datata:form.serialize()})
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/mini-projet/machine/api/add",
                data: form.serialize(), // serializes the form's elements.
                success: function(data) {
                    alert("Machine Added");
                }
            });


        });

    });
</script>
<c:import url="/footer.jsp"></c:import>

