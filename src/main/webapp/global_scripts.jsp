 
<!-- jQuery -->
<script src="/mini-projet/resources/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/mini-projet/resources/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="/mini-projet/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="/mini-projet/resources/plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="/mini-projet/resources/plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="/mini-projet/resources/plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="/mini-projet/resources/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="/mini-projet/resources/plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="/mini-projet/resources/plugins/moment/moment.min.js"></script>
<script src="/mini-projet/resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="/mini-projet/resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="/mini-projet/resources/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="/mini-projet/resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="/mini-projet/resources/dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/mini-projet/resources/dist/js/demo.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="/mini-projet/resources/dist/js/pages/dashboard.js"></script>

<!-- jsGrid -->
<script src="/mini-projet/resources/plugins/jsgrid/jsgrid.min.js"></script>
<script src="/mini-projet/resources/plugins/chart.js/Chart.min.js"></script>

<script>
    function getUrlVars()
    {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    }
</script>
