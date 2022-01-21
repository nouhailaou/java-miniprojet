<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/global_styles.jsp"></c:import>
<!-- jsGrid -->
<link rel="stylesheet" href="resources/plugins/jsgrid/jsgrid.min.css">
<link rel="stylesheet" href="resources/plugins/jsgrid/jsgrid-theme.min.css">
<c:import url="/header.jsp"></c:import>

<!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <c:import url="/page_header.jsp"></c:import>
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Dashboard</h3>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                    <div id="jsGrid1"></div>
                </div>
             <!-- /.card-body -->
            </div>
                    <!-- /.card -->
        </section>
        <!-- /.content -->
        <!-- jsGrid -->
    </div>
<c:import url="/global_scripts.jsp"></c:import>
<script>

    $(function () {
        function filljsGridData(data,fields){
            $("#jsGrid1").jsGrid({
                height: "100%",
                width: "100%",
                sorting: true,
                paging: true,
                data: data,
                fields:fields
            });
        }

        $.ajax({
            method: "GET",
            url: 'http://localhost:8180/mini-projet/salle/json',
            dataType: 'json',
            success: function (data) {
                console.log({data})

                const fields = [
                    { name: "id", type: "number", width: 150 },
                    { name: "code", type: "text", width: 50 },
                    { name: "type", type: "text", width: 200 },
                ]
                filljsGridData([data],fields)
                window.db = data;
            },
            error: function (error) {
                console.log({error})
                window.db = null;
            },

        });

    });
</script>
<c:import url="/footer.jsp"></c:import>

