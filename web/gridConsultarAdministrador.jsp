<%-- 
    Document   : grid-llamada-action.jsp
    Created on : 05-ene-2015, 21:36:58
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="true" jquerytheme="redmond" />

<div style="position: fixed; z-index: 1; width: 100%; display: none;"  id="barraCargarEliminar" class="progress progress-sm active">
    <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
        <span class="sr-only">100% Complete (success)</span>
    </div>
</div>

<div id="divAlertSuccessEliminar" style="display: none;"  class="alert alert-success alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertSuccessEliminar"></h4>
    <label id="labelMensajeSuccessEliminar"></label>
</div>

<div id="divAlertErrorEliminar" style="display: none;"  class="alert alert-error alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertErrorEliminar"></h4>
    <label id="labelMensajeErrorEliminar"></label>
</div>

<div id="divAlertEliminar" style="display: none;" class="alert alert-warning alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="textoAlertEliminar"><i class="icon fa fa-warning"></i>Campos no validos.</h4>
    Verifique que los datos que ha ingresado sean correctos.
</div>

<section class="content">
    <div class="box box-primary">
        <div class="box-header">
            <h3 class="box-header"><label>Consultar Administrador</label></label></h3>
        </div>
         
        <div id="gridAdministradores" class="box-body">
           
            <!--Busqueda por Filtro-->
            <div class="sidebar-form">
               
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Buscar Por Nombre De Usuario" name="nombreUsuario" id="nombreUsuarioPorFiltro"/>
                    <span class="input-group-btn">
                        <button id="search-btn" class="btn btn-flat" name="search" type="button" onclick="buscarUsuarioPorFiltroUsuario();">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                </div>
            </div> 
        <!--Fin Busqueda por Filtro-->
                    
           <s:url var="remoteurl" action="ajaxLlenarListaAdministradores"/>

            <sjg:grid
                id="gridAdmin"
                caption="Administradores"
                dataType="json"
                href="%{remoteurl}"
                pager="true"
                gridModel="gridModel"
                rowList="10,15,20"
                rowNum="15"
                rownumbers="true"
                navigatorSearch="false"
                navigatorRefresh="false"
                navigator="true"
                navigatorAdd="false"
                navigatorEdit="false"
                navigatorDelete="false"
                viewrecords="true"
                navigatorAddOptions="{closeAfterAdd:true,reloadAfterSubmit:true}"
                navigatorEditOptions="{closeAfterEdit:true,reloadAfterSubmit:true }"
                navigatorExtraButtons="{
                                        editarAcceso : {
                                                    title : 'Ver Datos de Acceso',
                                                    icon: 'ui-icon-key', 
                                                    onclick: function(){ verDatosAccesoAdministrador(); }
                                                 },
                                        editarDatos : {
                                                    title : 'Ver Datos Personales',
                                                    icon: 'ui-icon-person', 
                                                    onclick: function(){ verDatosPersonalesAdministrador(); }
                                                 },
                                        editarDireccion : {
                                                    title : 'Ver Dirección',
                                                    icon: 'ui-icon-home', 
                                                    onclick: function(){ verDireccionAdministrador(); }
                                                 }
                                       }"
                autowidth="true">
                 <!-- Se coloca key=true para tener una columna id (solo puede existir una columna llave) la cual nos dira que registro se va a elimnar o a editar -->
                <sjg:gridColumn name="nombreUsuario" editable="true" index="nombreUsuario" title="Nombre de Usuario" key="true" sortable="true"/>
                <sjg:gridColumn editable="true"   name="tipoUsuario" index="tipoUsuario" title="Rol del Usuario" sortable="true"/>
                <sjg:gridColumn editable="true" name="clave" index="clave" title="Clave de Acceso" sortable="false"/>
                <sjg:gridColumn editable="false" name="fechaRegistro" index="fechaRegistro" title="Fecha de Registro" sortable="false" formatter="date" formatoptions="{newformat : 'd/m/YH:i', srcformat : 'Y-m-d H:i:s'}"/>
            </sjg:grid>
        </div>
       
        <div id="datosAccesoAdministrador" class="box-body" style='display:none;'>
            <div class="box-header">
                <i class="fa fa-key"></i>
                <h3 class="box-title"><label>Datos de acceso</label></h3>
            </div>
            <div id="divNombreUsuario" class="form-group">
                <label for="nombreUsuario">Nombre de usuario</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuario" placeholder="Nombre de usuario" type="text" readonly="true">
            </div>
            <s:hidden name="tipoUsuario" value="Administrador" />
            <div id="divClaveUsuario" class="form-group">
                <label for="claveUsuario">Clave de Acceso</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" id="clave" name="clave" placeholder="Clave de Acceso" type="text" autofocus="true" readonly="true">
            </div>
            <div class="box-footer">
                <button type="button" onclick="cancelarEdicionAcceso();" class="btn btn-primary btn-sm margin">Regresar</button>
            </div>
        </div><!-- /.col -->

        <s:hidden name="nombreUsuario" id="nomUs"/>
        <div id="datosPersonalesAdministrador" class="box-body" style='display:none;'>
            <div class="box-header">
                <i class="fa fa-info-circle"></i>
                <h3 class="box-title"><label>Datos Personales</label></h3>
            </div>

            <div id="divNombre" class="form-group">
                <label for="nombre">Nombre(s)</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombre" id="nombre" placeholder="Nombre(s)" type="text" readonly="true">
            </div>

            <div id="divApellidoPaterno" class="form-group">
                <label for="apellidoPaterno">Apellido Paterno</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido Paterno" type="text" readonly="true">
            </div>

            <div id="divApellidoMaterno" class="form-group">
                <label for="apellidoMaterno">Apellido Materno</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido Paterno" type="text" readonly="true">
            </div>

            <div id="divTelParticularAdministrador" class="form-group">
                <label for="telParticular">Teléfono celular</label>
                <input kl_virtual_keyboard_secure_input="on" name="telParticular" id="telParticular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono" type="text" readonly="true">
            </div>

            <div id="divEmailAdministrador" class="form-group">
                <label for="correo">Email</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correo" placeholder="Email" type="text" readonly="true">
            </div>

            <div id="divImagenAdministrador" class="form-group">
                <label for="imagen">Imagen</label>
                <img id="imagen" src="" width="220" height="150">
            </div>
            <div class="box-footer">
                <button type="button" onclick="cancelarEdicionDatosPersonales();" class="btn btn-primary btn-sm margin">Regresar</button>
            </div>
        </div><!-- /.box-body -->
        
        <div id="datosDireccionAdministrador" class="box-body" style='display:none;'>
            <div class="box-header">
                <i class="fa fa-map-marker"></i>
                <h3 class="box-title"><label>Dirección</label></h3>
            </div>
            <s:hidden name="id" id="idDomAdmin"/>
            <s:hidden name="telParticular" id="telPart"/>
            <s:hidden name="nombreUsuario" id="nomUsr"/>
            <div id="divCalleAdministrador" class="form-group">
                <label for="calle">Calle y No.</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calle" placeholder="Calle" type="text" readonly="true">
            </div>

            <div id="divColoniaAdministrador" class="form-group">
                <label for="colonia">Colonia</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="colonia" placeholder="Colonia" type="text" readonly="true">
            </div>

            <div id="divDelegacionAdministrador" class="form-group">
                <label for="delegacion">Delegacón</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacion" placeholder="Delegación" type="text" readonly="true">
            </div>

            <div id="divEntidadFederativaAdministrador" class="form-group">
                <label for="entidadFederativa">Entidad Federativa</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativa" placeholder="Entidad Federativa" type="text" readonly="true">
            </div>

            <div id="divCodigoPostalAdministrador" class="form-group">
                <label for="codigoPostal">Codigo Postal</label>
                <input kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostal" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text" readonly="true">
            </div>

            <div class="box-footer">
                <button type="button" onclick="cancelarEdicionDireccion();" class="btn btn-primary btn-sm margin">Regresar</button>
            </div>
        </div><!-- /.box-body -->
    </div>
    <!--**********************************************Busqueda por Filtro*******************************************************-->
    <div id="datosAdministradorPorFiltro" class="box box-primary" style='display:none;'>
        <div class="box-header">
            <h3 class="box-header"><label>Datos del Administrador</label></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        
        <div class="box-body">
            <div class="box-header">
                <i class="fa fa-key"></i>
                <h3 class="box-title"><label>Datos acceso</label></h3>
            </div>

            <div id="divNombreUsuario" class="form-group">
                <label for="nombreUsuario">Nombre de usuario</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuarioFiltro" placeholder="Nombre de usuario" type="text" autofocus="true">
            </div>
            <s:hidden name="id" id="idDomAdminFiltro"/>
            <s:hidden name="tipoUsuario" value="Administrador" />
            <div id="divClaveUsuario" class="form-group">
                <label for="claveUsuario">Clave de Acceso</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" id="claveFiltro" name="clave" placeholder="Clave de Acceso" type="password">
            </div>
        </div><!-- /.box-body -->

        <div class="box-body">
            <div class="box-header">
                <i class="fa fa-info-circle"></i>
                <h3 class="box-title"><label>Datos Personales</label></h3>
            </div>

            <div id="divNombre" class="form-group">
                <label for="nombre">Nombre(s)</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombre" id="nombreFiltro" placeholder="Nombre(s)" type="text">
            </div>

            <div id="divApellidoPaterno" class="form-group">
                <label for="apellidoPaterno">Apellido Paterno</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaterno" id="apellidoPaternoFiltro" placeholder="Apellido Paterno" type="text">
            </div>

            <div id="divApellidoMaterno" class="form-group">
                <label for="apellidoMaterno">Apellido Materno</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaterno" id="apellidoMaternoFiltro" placeholder="Apellido Paterno" type="text">
            </div>

            <div id="divTelParticularAdministrador" class="form-group">
                <label for="telParticular">Teléfono celular</label>
                <input kl_virtual_keyboard_secure_input="on" name="telParticular" id="telParticularFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono" type="text">
            </div>

            <div id="divEmailAdministrador" class="form-group">
                <label for="correo">Email</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correoFiltro" placeholder="Email" type="text">
            </div>

            <div id="divImagenAdministrador" class="form-group">
                    <label for="imagen">Imagen Actual</label>
                    <img id="imagenFiltro" src="" width="220" height="150">
            </div>
        </div><!-- /.box-body -->

        <div class="box-body">
            <div class="box-header">
                <i class="fa fa-map-marker"></i>
                <h3 class="box-title"><label>Dirección</label></h3>
            </div>

            <div id="divCalleAdministrador" class="form-group">
                <label for="calle">Calle y No.</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calleFiltro" placeholder="Calle" type="text">
            </div>

            <div id="divColoniaAdministrador" class="form-group">
                <label for="colonia">Colonia</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="coloniaFiltro" placeholder="Colonia" type="text">
            </div>

            <div id="divDelegacionAdministrador" class="form-group">
                <label for="delegacion">Delegación</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacionFiltro" placeholder="Delegación" type="text">
            </div>

            <div id="divEntidadFederativaAdministrador" class="form-group">
                <label for="entidadFederativa">Entidad Federativa</label>
                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativaFiltro" placeholder="Entidad Federativa" type="text">
            </div>

            <div id="divCodigoPostalAdministrador" class="form-group">
                <label for="codigoPostal">Código Postal</label>
                <input kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostalFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
            </div>
            
            <div class="box-footer">
                <button type="button" onclick="cancelarEdicionPorFiltro();" class="btn btn-danger btn-sm margin">Regresar</button>
            </div>


        </div><!-- /.box-body -->
    </div>
</section>

<!-- InputMask -->
<script src="bootstrap/js/fileinput.min.js" type="text/javascript"></script>
<link href="bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

<script src="js/funcionesAdministrador/funcionesConsultarAdministrador.js" type="text/javascript"></script>
<script type="text/javascript" src="bootstrap/js/jquery.modal.js"></script>
<link href="bootstrap/css/jquery.modal.css" type="text/css" rel="stylesheet" />
        