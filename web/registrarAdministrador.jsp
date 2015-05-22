<%-- 
    Document   : hospitalRegistro1
    Created on : 26-abr-2015, 22:53:25
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
            <h3 class="box-header"><b>Registro de Administrador</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <s:form enctype="multipart/form-data" name="formNuevoAdministrador" action="registrarAdministrador" onsubmit="return validarCampos();"  method="post" role="form">
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos de inicio de sesión</h3>
                </div>

                <div id="divNombreUsuario" class="form-group">
                    <label for="nombreUsuario">Nombre de usuario</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuario" placeholder="Nombre de usuario" type="text" autofocus="true">
                </div>
                <s:hidden name="tipoUsuario" value="Administrador" />
                <div id="divClaveUsuario" class="form-group">
                    <label for="claveUsuario">Clave de Acceso</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" id="clave" name="clave" placeholder="Clave de Acceso" type="password">
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos Personales</h3>
                </div>

                <div id="divNombre" class="form-group">
                    <label for="nombre">Nombre(s)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombre" id="nombre" placeholder="Nombre(s)" type="text">
                </div>
                
                <div id="divApellidoPaterno" class="form-group">
                    <label for="apellidoPaterno">Apellido Paterno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido Paterno" type="text">
                </div>
                
                <div id="divApellidoMaterno" class="form-group">
                    <label for="apellidoMaterno">Apellido Materno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido Paterno" type="text">
                </div>

                <div id="divTelParticularAdministrador" class="form-group">
                    <label for="telParticular">Teléfono celular</label>
                    <input kl_virtual_keyboard_secure_input="on" name="telParticular" id="telParticular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono" type="text">
                </div>

                <div id="divEmailAdministrador" class="form-group">
                    <label for="correo">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correo" placeholder="Email" type="text">
                </div>
                
                <div id="divImagenAdministrador" class="form-group">
                    <label for="imagen">Imagen</label>
                    <input multiple=false data-preview-file-type="any" name="imagen" id="file-1" class="file" placeholder="Imagen" type="file">
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Dirección</h3>
                </div>

                <div id="divCalleAdministrador" class="form-group">
                    <label for="calle">Calle y No.</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calle" placeholder="Calle" type="text">
                </div>

                <div id="divColoniaAdministrador" class="form-group">
                    <label for="colonia">Colonia</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="colonia" placeholder="Colonia" type="text">
                </div>

                <div id="divDelegacionAdministrador" class="form-group">
                    <label for="delegacion">Delegación</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacion" placeholder="Delegación" type="text">
                </div>

                <div id="divEntidadFederativaAdministrador" class="form-group">
                    <label for="entidadFederativa">Entidad Federativa</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativa" placeholder="Entidad Federativa" type="text">
                </div>

                <div id="divCodigoPostalAdministrador" class="form-group">
                    <label for="codigoPostal">Código Postal</label>
                    <input kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostal" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
                </div>
            </div><!-- /.box-body -->

            <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </s:form>
    </div>
</section>

<!-- InputMask -->
<script src="plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
<script src="bootstrap/js/fileinput.min.js" type="text/javascript"></script>
<link href="bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<!--Script creados -->
<script src="js/funcionesAdministrador/funcionesRegistroAdministrador.js" type="text/javascript"></script>