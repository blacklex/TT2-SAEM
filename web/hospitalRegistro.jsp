<%-- 
    Document   : hospitalRegistro1
    Created on : 26-abr-2015, 22:53:25
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <style type="text/css">
        input[type=checkbox] {

 -ms-transform: scale(1.2); /* IE */
 -moz-transform: scale(1.2); /* FF */
 -webkit-transform: scale(1.2); /* Safari and Chrome */
 -o-transform: scale(1.2); /* Opera */

}
    </style>
    
<div style="position: fixed; z-index: 1; width: 100%; display: none;"  id="barraCargar" class="progress progress-sm active">
    <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
        <span class="sr-only">100% Complete (success)</span>
    </div>
</div>

<div id="divAlertSuccess" style="display: none;"  class="alert alert-success alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertSuccess"></h4>
    <label id="labelMensajeSuccess"></label>
</div>

<div id="divAlertError" style="display: none;"  class="alert alert-error alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertError"></h4>
    <label id="labelMensajeError"></label>
</div>

<div id="divAlert" style="display: none;" class="alert alert-warning alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="textoAlert"><i class="icon fa fa-warning"></i>Campos no validos.</h4>
    Verifique que los datos que ha ingresado sean correctos.
</div>



<section class="content">

    <div class="box box-primary">
        <div class="box-header">
            <h3 class="box-header"><b>Registro de Hospital</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <form name="formNuevoHospital" action="registrarHospital" onsubmit="return validarCampos();"  method="post" role="form">
            <input type="hidden" name="latitudY" id="latitudY">
            <input type="hidden" name="longitudX" id="longitudX">
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos de inicio de sesión</h3>
                </div>

                <div id="divNombreUsuario" class="form-group">
                    <label for="nombreUsuario">Nombre de usuario</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuario" placeholder="Nombre de usuario" type="text">
                </div>

                <div id="divClaveUsuario" class="form-group">
                    <label for="claveUsuario">Clave de Acceso</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" id="claveUsuario" name="claveUsuario" placeholder="Clave de Acceso" type="password">
                </div>


            </div><!-- /.box-body -->


            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos del Hospital</h3>
                </div>

                <div id="divNombreHospital" class="form-group">
                    <label for="nombreHospital">Nombre del Hospital</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreHospital" id="nombreHospital" placeholder="Nombre del Hospital" type="text">
                </div>

                <div id="divTelefonoHospital" class="form-group">
                    <label for="telefonoHospital">Telefono</label>
                    <input kl_virtual_keyboard_secure_input="on" name="telefonoHospital" id="telefonoHospital" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono" type="text">
                </div>

                <div id="divEmailHospital" class="form-group">
                    <label for="emailHospital">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="emailHospital" id="emailHospital" placeholder="Email" type="text">
                </div>

            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Dirección</h3>
                </div>
                <!-- /google maps -->
                <input id="autocomplete" class="form-control" placeholder="Ingresa la Dirección" type="textbox"></input>
                <div id="map_canvas" style="height: 300px; margin-bottom: 20px;"></div>
                <!-- /fin google maps -->
                
                <div id="divCalleHospital" class="form-group">
                    <label for="calle">Calle</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calle" placeholder="Calle" type="text">
                </div>

                <div id="divNumeroHospital" class="form-group">
                    <label for="numero">Numero</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="numero" id="numero" placeholder="Numero" type="text">
                </div>

                <div id="divColoniaHospital" class="form-group">
                    <label for="colonia">Colonia</label>
                    <input  kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="colonia" placeholder="Colonia" type="text">
                </div>

                <div id="divDelegacionHospital" class="form-group">
                    <label for="delegacion">Delegacón</label>
                    <input  kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacion" placeholder="Delegación" type="text">
                </div>

                <div id="divEntidadFederativaHospital" class="form-group">
                    <label for="entidadFederativa">Entidad Federativa</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativa" placeholder="Entidad Federativa" type="text">
                </div>

                <div id="divCodigoPostalHospital" class="form-group">
                    <label for="codigoPostal">Codigo Postal</label>
                    <input  kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostal" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
                </div>


            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Directivo</h3>
                </div>

                <div id="divNombreDirectivo" class="form-group">
                    <label for="nombreDirectivo">Nombre</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreDirectivo" id="nombreDirectivo" placeholder="Nombre" type="text">
                </div>

                <div id="divTelefonoDirectivo" class="form-group">
                    <label for="telefonoDirectivo">Telefono</label>
                    <input kl_virtual_keyboard_secure_input="on" name="telefonoDirectivo" id="telefonoDirectivo" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono" type="text">
                </div>

                <div id="divEmailDirectivo" class="form-group">
                    <label for="emailDirectivo">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="emailDirectivo" id="emailDirectivo" placeholder="Email" type="text">
                </div>

            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Especialidades</h3>
                    <div class="form">
                        <div id="divEspecialidades" class="col-md-6">
                        
                        </div>
                        </div>
                </div>

            </div><!-- /.box-body -->



            <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
</section>


<!-- InputMask -->
<script src="plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
<!--Script creados  -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDZqR4-rCjdcnt_0aIR_C4CB7B5BNmeLI&v=3.exp&signed_in=true&libraries=places"></script>
<script src="js/funcionesHospital/funcionesGoogleMapsDirecciones.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/estiloMapaAutoCompletado.css"/>

<script src="js/funcionesHospital/funcionesRegistroHospital.js" type="text/javascript"></script>

<!-- SlimScroll 1.3.0 -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- iCheck 1.0.1 -->
    <script src="plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    
   


    

