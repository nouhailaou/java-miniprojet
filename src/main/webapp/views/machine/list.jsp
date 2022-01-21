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
                    <h1 class="m-0">Machine View</h1>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">List des Machines</h3>
                        <div class="card-tools">
                            <div class="input-group input-group-sm" style="width: 150px;">
                                <a href="/mini-projet/machine/new" class="btn btn-info"> Add</a>
                            </div>
                        </div>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body table-responsive p-0">
                        <table class="table table-hover text-nowrap">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Reference</th>
                                <th>Marque</th>
                                <th>date d'Achat</th>
                                <th>Id de Salle</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody id="table-body">
                            </tbody>
                        </table>
                    </div>
                    <!-- /.card-body -->
                </div>
                <!-- /.card -->
            </div>
        </div>
    </section>
    <!-- /.content -->
    <!-- jsGrid -->
</div>
<c:import url="/global_scripts.jsp"></c:import>
<script>

    $(function () {
        function filljsGridData(data){
            let rowHtml = ''
            for (const x of data) {
                console.log({x})
                rowHtml+=`
                    <tr>
                        <td>${x.id}</td>
                        <td>${x.reference}</td>
                        <td>${x.type}</td>

                        <td>${x.dateAchat}</td>
                        <td>${x.salleId}</td>
                        <td>
                            <a href="/mini-projet/machine/edit?id=${x.id}" class="btn btn-info btn-flat">Edit</a>
                            <button  data-id="${x.id}" class="delete-item btn btn-danger btn-flat"> Delete </button>
                        </td>
                    </tr>
                `
            }
            $("#table-body").html(rowHtml)
            $('.delete-item').each(function () {
                var $this = $(this);
                $this.on("click", function () {
                    if(confirm("delete machine?")){
                        const id = $(this).data("id")
                        $.ajax({
                            method: "POST",
                            url: 'http://localhost:8080/mini-projet/machine/api/delete?id='+id,
                            dataType: 'json',
                            success: function (data) {
                                alert("machine with id "+id+" deleted")
                                getData();
                            },
                            error: function (error) {
                                alert("error while trying to delete")
                                console.log({error})
                            },

                        });
                    }
                });
            });
        }
        function getData(){
            $.ajax({
                method: "GET",
                url: 'http://localhost:8080/mini-projet/machine/api/',
                dataType: 'json',
                success: function (data) {
                    console.log({data})
                    filljsGridData(data)
                },
                error: function (error) {
                    console.log({error})
                    window.db = null;
                },

            });
        }
        getData()
    });
</script>
<c:import url="/footer.jsp"></c:import>

