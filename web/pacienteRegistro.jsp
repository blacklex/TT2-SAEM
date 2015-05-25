<%-- 
    Document   : pacienteRegistro
    Created on : 23/05/2015, 02:45:35 PM
    Author     : gabriela
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
            <h3 class="box-header"><b>Registro de Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <s:form enctype="multipart/form-data" name="formNuevoPaciente" action="registrarPaciente" onsubmit="return validarCampos();"  method="post" role="form">
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos de inicio de sesión</h3>
                </div>

                <div id="divNombreUsuario" class="form-group">
                    <label for="nombreUsuario">Nombre de usuario</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuario" placeholder="Nombre de usuario" type="text" autofocus="true">
                </div>
                <div id="divClaveUsuario" class="form-group">
                    <label for="claveUsuario">Clave de Acceso</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" id="clave" name="clave" placeholder="Clave de Acceso" type="password">
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos</h3>
                </div>
                
                <div id="divNss" class="form-group">
                    <label for="nss">NSS</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nss" id="nss" placeholder="NSS" type="text">
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

                <div id="divUniMedicaPaciente" class="form-group">
                    <label for="unidadMedica">Unidad Médica</label>
                    <input kl_virtual_keyboard_secure_input="on" name="unidadMedica" id="unidadMedica" class="form-control" placeholder="Unidad Medica" type="text">
                </div>

                <div id="divNoConsultorioPaciente" class="form-group">
                    <label for="noConsultorio">No Consultorio</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="noConsultorio" id="noConsultorio" placeholder="No. de Consultorio" type="text">
                </div>
                
                <div id="divImagenPaciente" class="form-group">
                    <label for="imagen">Imagen</label>
                    <input multiple=false data-preview-file-type="any" name="imagen" id="file-1" class="file" placeholder="Imagen" type="file">
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Domicilio</h3>
                </div>

                <div id="divCallePaciente" class="form-group">
                    <label for="calle">Calle y No.</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calle" placeholder="Calle" type="text">
                </div>

                <div id="divColoniaPaciente" class="form-group">
                    <label for="colonia">Colonia</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="colonia" placeholder="Colonia" type="text">
                </div>

                <div id="divDelegacionPaciente" class="form-group">
                    <label for="delegacion">Delegación</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacion" placeholder="Delegación" type="text">
                </div>

                <div id="divEntidadFederativaPaciente" class="form-group">
                    <label for="entidadFederativa">Entidad Federativa</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativa" placeholder="Entidad Federativa" type="text">
                </div>

                <div id="divCodigoPostalPaciente" class="form-group">
                     <label for="codigoPostal">Código Postal</label>
                    <input kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostal" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Teléfonos del Paciente</h3>
                </div>

                <div id="divTelefonoFijoPaciente" class="form-group">
                    <label for="telefonoFijo">Telefono Fijo</label>
                    <input kl_virtual_keyboard_secure_input="on" name="telefonoFijo" id="telefonoFijo" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Fijo" type="text">
                </div>

                <div id="divTelefonoParticularPaciente" class="form-group">
                    <label for="telefonoParticular">Telefono Particular</label>
                    <input kl_virtual_keyboard_secure_input="on" name="telefonoParticular" id="telefonoParticular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Particular" type="text">
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos Personales del Paciente</h3>
                </div>

                <div id="divEdoCivilPaciente" class="form-group">
                    <label for="estadoCivil">Estado Civil</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="estadoCivil" id="estadoCivil" placeholder="Estado Civil" type="text">
                </div>

                <div id="divCurpPaciente" class="form-group">
                    <label for="curp">CURP</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="curp" id="curp" placeholder="CURP" type="text">
                </div>

                <div id="divSexoPaciente" class="form-group">
                    <label for="sexo">Sexo</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="sexo" id="sexo" placeholder="Sexo" type="text">
                </div>

                <div id="divFechaNacimientoPaciente" class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="fechaNacimiento" id="fechaNacimiento" placeholder="Fecha de Nacimiento" type="date">
                </div>

                <div id="divEdadPaciente" class="form-group">
                    <label for="edad">Edad</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="edad" id="edad" placeholder="Edad" type="text">
                </div>

                <div id="divPesoPaciente" class="form-group">
                    <label for="peso">Peso (Kg)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="peso" id="peso" placeholder="Peso" type="text">
                </div>

                <div id="divAlturaPaciente" class="form-group">
                    <label for="altura">Altura (metros)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="altura" id="altura" placeholder="Altura" type="text">
                </div>

                <div id="divTallaPaciente" class="form-group">
                    <label for="talla">Talla </label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="talla" id="talla" placeholder="Talla" type="text">
                </div>

                <div id="divTelefonoCasaPaciente" class="form-group">
                    <label for="telCasa">Telefono de Casa</label>
                    <input kl_virtual_keyboard_secure_input="on" name="telCasa" id="telCasa" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono de Casa" type="text">
                </div>

                <div id="divTelefonoCelPaciente" class="form-group">
                    <label for="telCel">Telefono Celular</label>
                    <input kl_virtual_keyboard_secure_input="on" name="telCel" id="telCel" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Celular" type="text">
                </div>

                <div id="divCorreoPaciente" class="form-group">
                    <label for="correo">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correo" placeholder="Email" type="text">
                </div>

                <div id="divFacebookPaciente" class="form-group">
                    <label for="facebook">Facebook (www.facebook.com/alguien)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="facebook" id="facebook" placeholder="Facebook" type="text">
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Contactos del Paciente</h3>
                </div>
                
                <div id="divNombreCPaciente" class="form-group">
                    <label for="nombreC">Nombre</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreC" id="nombreC" placeholder="Nombre" type="text">
                </div>

                <div id="divApellidoPaternoCPaciente" class="form-group">
                    <label for="apellidoPaternoC">Apellido Paterno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaternoC" id="apellidoPaternoC" placeholder="Apellido Paterno" type="text">
                </div>

                <div id="divApellidoMaternoCPaciente" class="form-group">
                    <label for="apellidoMaternoC">Apellido Materno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaternoC" id="apellidoMaternoC" placeholder="Apellido Materno" type="text">
                </div>

                <div id="divParentescoCPaciente" class="form-group">
                    <label for="parentesco">Parentesco</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="parentesco" id="parentesco" placeholder="Parentesco" type="text">
                </div>

                <div id="divCelularCPaciente" class="form-group">
                    <label for="celular">Telefono Celular</label>
                    <input kl_virtual_keyboard_secure_input="on" name="celular" id="celular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Celular" type="text">
                </div>

                <div id="divFacebookCPaciente" class="form-group">
                    <label for="facebookC">Facebook (www.facebook.com/alguien)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="facebookC" id="facebookC" placeholder="Facebook" type="text">
                </div>

                <div id="divCorreoCPaciente" class="form-group">
                    <label for="correoC">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correoC" id="correoC" placeholder="Email" type="text">
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos Clínicos</h3>
                </div>

                <div id="divDrogasPaciente" class="form-group">
                    <label for="usoDrogas">Usa Drogas</label>
                    <input name="usoDrogas" id="usoDrogas" value="1" type="radio">
                </div>

                <div id="divAlcoholPaciente" class="form-group">
                    <label for="usoAlcohol">Consume Alcohol</label>
                    <input name="usoAlcohol" id="usoAlcohol" value="1" type="radio">
                </div>

                <div id="divFumaPaciente" class="form-group">
                    <label for="fumador">Fuma</label>
                    <input name="fumador" id="fumador" value="1" type="radio">
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
<script src="js/funcionesPaciente/funcionesRegistroPaciente.js" type="text/javascript"></script>
