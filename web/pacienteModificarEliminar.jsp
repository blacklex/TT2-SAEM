<%-- 
    Document   : pacienteModificarEliminar
    Created on : 23/05/2015, 07:09:32 PM
    Author     : gabriela
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
            <h3 class="box-header"><b>Modificar/Eliminar Paciente</b></h3>
        </div>
        <div id="gridPacientes" class="box-body">
           <s:url var="remoteurl" action="Listado"/>

            <sjg:grid
                id="gridPaciente"
                caption="Pacientes"
                dataType="json"
                href="%{remoteurl}"
                pager="true"
                gridModel="gridModel"
                rowList="10,15,20"
                rowNum="15"
                rownumbers="true"
                navigatorSearch="true"
                navigatorRefresh="true"
                navigator="true"
                navigatorAdd="false"
                navigatorEdit="false"
                navigatorDelete="false"
                viewrecords="true"
                navigatorAddOptions="{closeAfterAdd:true,reloadAfterSubmit:true}"
                navigatorEditOptions="{closeAfterEdit:true,reloadAfterSubmit:true }"
                navigatorExtraButtons="{
                                        seperator: {
                                                    title : 'seperator'
                                                   },
                                        editarAcceso : {
                                                    title : 'Editar Acceso',
                                                    icon: 'ui-icon-key', 
                                                    onclick: function(){ editarAccesoPaciente(); }
                                                 },
                                        editarDatos : {
                                                    title : 'Editar Datos',
                                                    icon: 'ui-icon-person', 
                                                    onclick: function(){ editarDatosPaciente(); }
                                                 },
                                        editarDireccion : {
                                                    title : 'Editar Dirección',
                                                    icon: 'ui-icon-home', 
                                                    onclick: function(){ editarDireccionPaciente(); }
                                                 },
                                        editarTelefonos : {
                                                    title : 'Editar Teléfonos',
                                                    icon: 'ui-icon-home', 
                                                    onclick: function(){ editarTelefonosPaciente(); }
                                                 }, 
                                        editarDatosPersonales : {
                                                    title : 'Editar Datos Personales',
                                                    icon: 'ui-icon-home', 
                                                    onclick: function(){ editarDatosPersonalesPaciente(); }
                                                 },
                                        editarContactos : {
                                                    title : 'Editar Contactos',
                                                    icon: 'ui-icon-home', 
                                                    onclick: function(){ editarContactosPaciente(); }
                                                 },    
                                        editarDatosClinicos : {
                                                    title : 'Editar Datos Clínicos',
                                                    icon: 'ui-icon-home', 
                                                    onclick: function(){ editarDatosClinicosPaciente(); }
                                                 },         
                                        eliminar : { 
                                                    title : 'Eliminar Registro', 
                                                    icon: 'ui-icon-trash',
                                                    onclick: function(){ eliminarPaciente(); }
                                                   }
                                       }"
                autowidth="true">
                 <!-- Se coloca key=true para tener una columna id (solo puede existir una columna llave) la cual nos dira que registro se va a elimnar o a editar -->
                <sjg:gridColumn name="nombreUsuario" editable="true" index="nombreUsuario" title="ID" key="true" sortable="true"/>
                <sjg:gridColumn editable="true"   name="tipoUsuario" index="tipoUsuario" title="Tipo" sortable="true"/>
                <sjg:gridColumn editable="true" name="clave" index="clave" title="Password" sortable="false"/>
                <sjg:gridColumn editable="false" name="fechaRegistro" index="fechaRegistro" title="Fecha Registro" sortable="false"/>
            </sjg:grid>
        </div>
        <div class="box-body">
            <!--Busqueda por Filtro-->
            <form class="sidebar-form" method="get" action="">
                <div class="box-header">
                    <h3 class="box-title">Filtrar por usuario:</h3>
                </div>
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search..." name="nombreUsuario" id="nombreUsuarioPorFiltro"/>
                    <span class="input-group-btn">
                        <button id="search-btn" class="btn btn-flat" name="search" type="button" onclick="buscarUsuarioPorFiltroUsuario();">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                </div>
            </form>
            <div class="form-group">
                <div class="radio">
                    <label>
                        <input id="radioEditar" type="radio" checked="" value="option1" name="optionsRadios"/>
                        Editar
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input id="radioEliminar" type="radio" value="option2" name="optionsRadios"/>
                        Eliminar
                    </label>
                </div>
            </div>
        <!--Fin Busqueda por Filtro-->
        </div>
           <form name="formEditarAccesoPaciente" action="editarAccesoPaciente" onsubmit="return validarCamposAcceso();" method="post" role="form">
            <div id="datosAccesoPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Datos de acceso</h3>
                </div>
                <div id="divNombreUsuario" class="form-group">
                    <label for="nombreUsuario">Nombre de usuario</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuario" placeholder="Nombre de usuario" type="text" readonly="true">
                </div>
                <s:hidden name="tipoUsuario" value="Administrador" />
                <div id="divClaveUsuario" class="form-group">
                    <label for="claveUsuario">Clave de Acceso</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" id="clave" name="clave" placeholder="Clave de Acceso" type="password" autofocus="true">
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionAcceso();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.col -->
        </form>
        <s:form enctype="multipart/form-data" name="formEditarDatosPaciente" action="editarDatosPaciente" method="post" role="form">
            <s:hidden name="nombreUsuario" id="nomUs"/>
            <div id="datosPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Datos Paciente</h3>
                </div>
                
                <div id="divNss" class="form-group">
                    <label for="nss">NSS</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nss" id="nss" placeholder="NSS" type="text" readonly="true">
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
                
                <div id="divImagenPaciente" class="form-group">
                        <label for="imagen">Imagen Actual</label>
                        <img id="imagen" src="" width="220" height="150">
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionDatos();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarDireccionPaciente" action="editarDireccionPaciente" onsubmit="return validarCamposDireccion();" method="post" role="form">
            <div id="datosDireccionPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Dirección</h3>
                </div>
                <s:hidden name="id" id="idDomPaciente"/>
                <s:hidden name="nombreUsuario" id="nomUsr"/>
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
                
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionDireccion();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarTelefonosPaciente" action="editarTelefonosPaciente" onsubmit="return validarCamposTelefonos();" method="post" role="form">
            <div id="datosTelefonosPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Teléfonos</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUs3"/>
                <div id="divTelefonoFijoPaciente" class="form-group">
                    <label for="telefonoFijo">Telefono Fijo</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telefonoFijo" id="telefonoFijo" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Fijo" type="text">
                </div>

                <div id="divTelefonoParticularPaciente" class="form-group">
                    <label for="telefonoParticular">Telefono Particular</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telefonoParticular" id="telefonoParticular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Particular" type="text">
                </div>
                
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionTelefonos();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarDatosPersonalesPaciente" action="editarDatosPersonalesPaciente" onsubmit="return validarCamposDatosPersonales();" method="post" role="form">
            <div id="datosPersonalesPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Datos Personales</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUs4"/>
                <div id="divEdoCivilPaciente" class="form-group">
                    <label for="estadoCivil">Estado Civil</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="estadoCivil" id="estadoCivil" placeholder="Estado Civil" type="text">
                </div>

                <div id="divCurpPaciente" class="form-group">
                    <label for="curp">CURP</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="curp" id="curp" placeholder="CURP" type="text">
                </div>

                <div id="divSexoPaciente" class="form-group">
                    <label for="sexo">Sexo</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="sexo" id="sexo" placeholder="Sexo" type="text">
                </div>

                <div id="divFechaNacimientoPaciente" class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="fechaNacimiento" id="fechaNacimiento" placeholder="Fecha de Nacimiento" type="date">
                </div>

                <div id="divEdadPaciente" class="form-group">
                    <label for="edad">Edad</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="edad" id="edad" placeholder="Edad" type="text">
                </div>

                <div id="divPesoPaciente" class="form-group">
                    <label for="peso">Peso (Kg)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="peso" id="peso" placeholder="Peso" type="text">
                </div>

                <div id="divAlturaPaciente" class="form-group">
                    <label for="altura">Altura (metros)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="altura" id="altura" placeholder="Altura" type="text">
                </div>

                <div id="divTallaPaciente" class="form-group">
                    <label for="talla">Talla </label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="talla" id="talla" placeholder="Talla" type="text">
                </div>

                <div id="divTelefonoCasaPaciente" class="form-group">
                    <label for="telCasa">Telefono de Casa</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telCasa" id="telCasa" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono de Casa" type="text">
                </div>

                <div id="divTelefonoCelPaciente" class="form-group">
                    <label for="telCel">Telefono Celular</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telCel" id="telCel" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Celular" type="text">
                </div>

                <div id="divCorreoPaciente" class="form-group">
                    <label for="correo">Email</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correo" placeholder="Email" type="text">
                </div>

                <div id="divFacebookPaciente" class="form-group">
                    <label for="facebook">Facebook (www.facebook.com/alguien)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="facebook" id="facebook" placeholder="Facebook" type="text">
                </div>
                
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionDatosPersonales();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
                
            </div><!-- /.box-body -->
        </s:form>  
        <s:form name="formEditarContactosPaciente" action="editarContactosPaciente" onsubmit="return validarCamposContactos();" method="post" role="form">
            <div id="datosContactosPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Contacto</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUs5"/>
                <div id="divNombreCPaciente" class="form-group">
                    <label for="nombreC">Nombre</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreC" id="nombreC" placeholder="Nombre" type="text">
                </div>

                <div id="divApellidoPaternoCPaciente" class="form-group">
                    <label for="apellidoPaternoC">Apellido Paterno</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaternoC" id="apellidoPaternoC" placeholder="Apellido Paterno" type="text">
                </div>

                <div id="divApellidoMaternoCPaciente" class="form-group">
                    <label for="apellidoMaternoC">Apellido Materno</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaternoC" id="apellidoMaternoC" placeholder="Apellido Materno" type="text">
                </div>

                <div id="divParentescoCPaciente" class="form-group">
                    <label for="parentesco">Parentesco</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="parentesco" id="parentesco" placeholder="Parentesco" type="text">
                </div>

                <div id="divCelularCPaciente" class="form-group">
                    <label for="celular">Telefono Celular</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="celular" id="celular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Celular" type="text">
                </div>

                <div id="divFacebookCPaciente" class="form-group">
                    <label for="facebookC">Facebook (www.facebook.com/alguien)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="facebookC" id="facebookC" placeholder="Facebook" type="text">
                </div>

                <div id="divCorreoCPaciente" class="form-group">
                    <label for="correoC">Email</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="correoC" id="correoC" placeholder="Email" type="text">
                </div>
                
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionContactos();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form> 
        <s:form name="formEditarDatosClinicosPaciente" action="editarDatosClinicosPaciente" onsubmit="return validarCamposDatosClinicos();" method="post" role="form">
            <div id="datosClinicosPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Datos Clinicos</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUs6"/>
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
                
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionDatosClinicos();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>    
    </div>
    <!--**********************************************Busqueda por Filtro*******************************************************-->
    <s:form enctype="multipart/form-data" name="formNuevoPaciente" action="editarPacientePorFiltro"  method="post" role="form">
        <div id="datosPacientePorFiltro" class="box box-primary" style='display:none;'>
            <div class="box-header">
                <h3 class="box-header"><b>Los siguientes datos del Paciente seran eliminados/editados</b></h3>
            </div><!-- /.box-header -->
            <!-- form start -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos de inicio de sesión</h3>
                </div>

                <div id="divNombreUsuario" class="form-group">
                    <label for="nombreUsuario">Nombre de usuario</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuarioFiltro" placeholder="Nombre de usuario" type="text" autofocus="true">
                </div>
                <s:hidden name="id" id="idDomPacienteFiltro"/>
                <s:hidden name="tipoUsuario" value="Administrador" />
                <div id="divClaveUsuario" class="form-group">
                    <label for="claveUsuario">Clave de Acceso</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" id="claveFiltro" name="clave" placeholder="Clave de Acceso" type="password">
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos</h3>
                </div>

                <div id="divNss" class="form-group">
                    <label for="nss">NSS</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nss" id="nssFiltro" placeholder="NSS" type="text" readonly="true">
                </div>

                <div id="divNombre" class="form-group">
                    <label for="nombre">Nombre(s)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombre" id="nombreFiltro" placeholder="Nombre(s)" type="text" readonly="true">
                </div>
                
                <div id="divApellidoPaterno" class="form-group">
                    <label for="apellidoPaterno">Apellido Paterno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaterno" id="apellidoPaternoFiltro" placeholder="Apellido Paterno" type="text" readonly="true">
                </div>
                
                <div id="divApellidoMaterno" class="form-group">
                    <label for="apellidoMaterno">Apellido Materno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaterno" id="apellidoMaternoFiltro" placeholder="Apellido Paterno" type="text" readonly="true">
                </div>

                <div id="divUniMedicaPaciente" class="form-group">
                    <label for="unidadMedica">Unidad Médica</label>
                    <input kl_virtual_keyboard_secure_input="on" name="unidadMedica" id="unidadMedicaFiltro" class="form-control" placeholder="Unidad Medica" type="text">
                </div>

                <div id="divNoConsultorioPaciente" class="form-group">
                    <label for="noConsultorio">No Consultorio</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="noConsultorio" id="noConsultorioFiltro" placeholder="No. de Consultorio" type="text">
                </div>
                
                <div id="divImagenPaciente" class="form-group">
                    <label for="imagen">Imagen</label>
                    <input multiple=false data-preview-file-type="any" name="imagen" id="file-1" class="file" placeholder="Imagen" type="file">
                </div>
                
                <div id="divImagenPaciente" class="form-group">
                        <label for="imagen">Imagen Actual</label>
                        <img id="imagenFiltro" src="" width="220" height="150">
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin" id="actualizar" value="Actualizar" style='display:none;'>Actualizar</button>
                    <button type="button" class="btn btn-danger btn-sm margin" onclick="eliminarPacientePorFiltro();" id="eliminar" style='display:none;'>Eliminar</button>
                    <button type="button" class="btn btn-orange btn-sm margin" onclick="cancelarEdicionPorFiltro();" >Cancelar</button>
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Dirección</h3>
                </div>

                <div id="divCallePaciente" class="form-group">
                    <label for="calle">Calle y No.</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calleFiltro" placeholder="Calle" type="text">
                </div>

                <div id="divColoniaPaciente" class="form-group">
                    <label for="colonia">Colonia</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="coloniaFiltro" placeholder="Colonia" type="text">
                </div>

                <div id="divDelegacionPaciente" class="form-group">
                    <label for="delegacion">Delegación</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacionFiltro" placeholder="Delegación" type="text">
                </div>

                <div id="divEntidadFederativaPaciente" class="form-group">
                    <label for="entidadFederativa">Entidad Federativa</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativaFiltro" placeholder="Entidad Federativa" type="text">
                </div>

                <div id="divCodigoPostalPaciente" class="form-group">
                    <label for="codigoPostal">Código Postal</label>
                    <input kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostalFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
                </div>

                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin" id="actualizar" value="Actualizar" style='display:none;'>Actualizar</button>
                    <button type="button" class="btn btn-danger btn-sm margin" onclick="eliminarPacientePorFiltro();" id="eliminar" style='display:none;'>Eliminar</button>
                    <button type="button" class="btn btn-orange btn-sm margin" onclick="cancelarEdicionPorFiltro();" >Cancelar</button>
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Teléfonos</h3>
                </div>

                <div id="divTelefonoFijoPaciente" class="form-group">
                    <label for="telefonoFijo">Telefono Fijo</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telefonoFijo" id="telefonoFijoFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Fijo" type="text">
                </div>

                <div id="divTelefonoParticularPaciente" class="form-group">
                    <label for="telefonoParticular">Telefono Particular</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telefonoParticular" id="telefonoParticularFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Particular" type="text">
                </div>

                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin" id="actualizar" value="Actualizar" style='display:none;'>Actualizar</button>
                    <button type="button" class="btn btn-danger btn-sm margin" onclick="eliminarPacientePorFiltro();" id="eliminar" style='display:none;'>Eliminar</button>
                    <button type="button" class="btn btn-orange btn-sm margin" onclick="cancelarEdicionPorFiltro();" >Cancelar</button>
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos Personales</h3>
                </div>

                <div id="divEdoCivilPaciente" class="form-group">
                    <label for="estadoCivil">Estado Civil</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="estadoCivil" id="estadoCivilFiltro" placeholder="Estado Civil" type="text">
                </div>

                <div id="divCurpPaciente" class="form-group">
                    <label for="curp">CURP</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="curp" id="curpFiltro" placeholder="CURP" type="text">
                </div>

                <div id="divSexoPaciente" class="form-group">
                    <label for="sexo">Sexo</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="sexo" id="sexoFiltro" placeholder="Sexo" type="text">
                </div>

                <div id="divFechaNacimientoPaciente" class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="fechaNacimiento" id="fechaNacimientoFiltro" placeholder="Fecha de Nacimiento" type="date">
                </div>

                <div id="divEdadPaciente" class="form-group">
                    <label for="edad">Edad</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="edad" id="edadFiltro" placeholder="Edad" type="text">
                </div>

                <div id="divPesoPaciente" class="form-group">
                    <label for="peso">Peso (Kg)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="peso" id="pesoFiltro" placeholder="Peso" type="text">
                </div>

                <div id="divAlturaPaciente" class="form-group">
                    <label for="altura">Altura (metros)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="altura" id="alturaFiltro" placeholder="Altura" type="text">
                </div>

                <div id="divTallaPaciente" class="form-group">
                    <label for="talla">Talla </label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="talla" id="tallaFiltro" placeholder="Talla" type="text">
                </div>

                <div id="divTelefonoCasaPaciente" class="form-group">
                    <label for="telCasa">Telefono de Casa</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telCasa" id="telCasaFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono de Casa" type="text">
                </div>

                <div id="divTelefonoCelPaciente" class="form-group">
                    <label for="telCel">Telefono Celular</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telCel" id="telCelFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Celular" type="text">
                </div>

                <div id="divCorreoPaciente" class="form-group">
                    <label for="correo">Email</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correoFiltro" placeholder="Email" type="text">
                </div>

                <div id="divFacebookPaciente" class="form-group">
                    <label for="facebook">Facebook (www.facebook.com/alguien)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="facebook" id="facebookFiltro" placeholder="Facebook" type="text">
                </div>

                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin" id="actualizar" value="Actualizar" style='display:none;'>Actualizar</button>
                    <button type="button" class="btn btn-danger btn-sm margin" onclick="eliminarPacientePorFiltro();" id="eliminar" style='display:none;'>Eliminar</button>
                    <button type="button" class="btn btn-orange btn-sm margin" onclick="cancelarEdicionPorFiltro();" >Cancelar</button>
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Contactos</h3>
                </div>

                <div id="divNombreCPaciente" class="form-group">
                    <label for="nombreC">Nombre</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreC" id="nombreCFiltro" placeholder="Nombre" type="text">
                </div>

                <div id="divApellidoPaternoCPaciente" class="form-group">
                    <label for="apellidoPaternoC">Apellido Paterno</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaternoC" id="apellidoPaternoCFiltro" placeholder="Apellido Paterno" type="text">
                </div>

                <div id="divApellidoMaternoCPaciente" class="form-group">
                    <label for="apellidoMaternoC">Apellido Materno</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaternoC" id="apellidoMaternoCFiltro" placeholder="Apellido Materno" type="text">
                </div>

                <div id="divParentescoCPaciente" class="form-group">
                    <label for="parentesco">Parentesco</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="parentesco" id="parentescoFiltro" placeholder="Parentesco" type="text">
                </div>

                <div id="divCelularCPaciente" class="form-group">
                    <label for="celular">Telefono Celular</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" name="celular" id="celularFiltro" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Celular" type="text">
                </div>

                <div id="divFacebookCPaciente" class="form-group">
                    <label for="facebookC">Facebook (www.facebook.com/alguien)</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="facebookCFiltro" id="facebookC" placeholder="Facebook" type="text">
                </div>

                <div id="divCorreoCPaciente" class="form-group">
                    <label for="correoC">Email</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="correoCFiltro" id="correoC" placeholder="Email" type="text">
                </div>

                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin" id="actualizar" value="Actualizar" style='display:none;'>Actualizar</button>
                    <button type="button" class="btn btn-danger btn-sm margin" onclick="eliminarPacientePorFiltro();" id="eliminar" style='display:none;'>Eliminar</button>
                    <button type="button" class="btn btn-orange btn-sm margin" onclick="cancelarEdicionPorFiltro();" >Cancelar</button>
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos Clinicos</h3>
                </div>

                <div id="divDrogasPaciente" class="form-group">
                    <label for="usoDrogas">Usa Drogas</label>
                    <input name="usoDrogas" id="usoDrogasFiltro" value="1" type="radio">
                </div>

                <div id="divAlcoholPaciente" class="form-group">
                    <label for="usoAlcohol">Consume Alcohol</label>
                    <input name="usoAlcohol" id="usoAlcoholFiltro" value="1" type="radio">
                </div>

                <div id="divFumaPaciente" class="form-group">
                    <label for="fumador">Fuma</label>
                    <input name="fumador" id="fumadorFiltro" value="1" type="radio">
                </div>

                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin" id="actualizar" value="Actualizar" style='display:none;'>Actualizar</button>
                    <button type="button" class="btn btn-danger btn-sm margin" onclick="eliminarPacientePorFiltro();" id="eliminar" style='display:none;'>Eliminar</button>
                    <button type="button" class="btn btn-orange btn-sm margin" onclick="cancelarEdicionPorFiltro();" >Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </div>
    </s:form>
</section>

<!-- InputMask -->
<script src="bootstrap/js/fileinput.min.js" type="text/javascript"></script>
<link href="bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

<script src="js/funcionesPaciente/funcionesEliminarEditarPaciente.js" type="text/javascript"></script>
<script type="text/javascript" src="bootstrap/js/jquery.modal.js"></script>
<link href="bootstrap/css/jquery.modal.css" type="text/css" rel="stylesheet" />
