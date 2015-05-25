<%-- 
    Document   : mapaPaciente
    Created on : 17/05/2015, 05:49:43 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<section class="content">
    <div class="box box-primary">
        <div class="box-header">
            <h3 class="box-header"><b>¿Dondé me encuentro?</b></h3>
        </div><!-- /.box-header -->
        <div class="box-body">
            <div class="info-box bg-aqua">
                <span class="info-box-icon">
                    <i class="fa fa-flag-o">
                    </i>
                </span>
                <div id="info" class="info-box-content">
                    
                </div>
            </div>
            <!-- /google maps -->
            <div id="map_canvas"></div>
            <!-- /fin google maps -->           
        </div><!-- /.box-body -->
    </div>
</section>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDZqR4-rCjdcnt_0aIR_C4CB7B5BNmeLI&v=3.exp&signed_in=true&libraries=places"></script>
<script src="js/funcionesPaciente/funcionesGoogleMapsUbicacionPaciente.js" type="text/javascript"></script>