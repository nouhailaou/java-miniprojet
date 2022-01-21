<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <h1 class="m-0">Salle Update</h1>
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
                <h3 class="card-title">Update Salle</h3>
            </div>
            <!-- /.card-header -->
            <form id="insert-item">
                <div class="card-body">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Code</label>
                        <input name="code" type="text" required class="form-control" id="exampleInputEmail1" placeholder="Enter Code de Salle">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Type</label>
                        <input type="text" name="type" required class="form-control" id="exampleInputPassword1" placeholder="Enter Type de Salle">
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
        const params = getUrlVars();
        console.log({params})
        if(params.id){
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/mini-projet/salle/api/get?id="+params.id,
                success: function(data) {
                    console.log({salleOne:data})
                    $('input[name=code]').val(data.code);
                    $('input[name=type]').val(data.type);
                }
            });
        }else{
            alert("No Id Submitted for this Edit  View!!")
            throw new Error("No Id submitted for Edit")
        }

        $("#insert-item").submit(function(e) {

            e.preventDefault(); // avoid to execute the actual submit of the form.

            const form = $(this);
            console.log({datata:form.serialize()})
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/mini-projet/salle/api/update?id="+params.id,
                data:form.serialize(),
                success: function(data) {
                    console.log({updateResult:data})
                    alert("Salle updated");
                },
                error: function (error) {
                    alert("error while trying to update")
                    console.log({error})
                },
            });


        });

    });
</script>
<c:import url="/footer.jsp"></c:import>
