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
        <div class="box-body">
            <!--Busqueda por Filtro-->
            <form class="sidebar-form" method="get" action="">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Bucar por nombre de Paciente..." name="nombreUsuario" id="nombreUsuarioPorFiltro"/>
                    <span class="input-group-btn">
                        <button id="search-btn" class="btn btn-flat" name="search" type="button" onclick="buscarPacientePorFiltroUsuario();">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                </div>
            </form>
        <!--Fin Busqueda por Filtro-->
        </div>
        <div id="gridPacientes" class="box-body">
           <s:url var="remoteurl" action="ajaxLlenarListaPacientes"/>

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
                                                    title : 'Editar Acceso',
                                                    icon: 'ui-icon-key', 
                                                    onclick: function(){ editarAccesoPaciente(); }
                                                 },
                                        editarDatos : {
                                                    title : 'Editar Datos de Identicifación',
                                                    icon: 'ui-icon-person', 
                                                    onclick: function(){ editarDatosPaciente(); }
                                                 },
                                        editarDireccion : {
                                                    title : 'Editar Domicilio',
                                                    icon: 'ui-icon-home', 
                                                    onclick: function(){ editarDireccionPaciente(); }
                                                 },
                                        editarDatosPersonales : {
                                           title : 'Editar Datos Personales',
                                           icon: '  ui-icon-info', 
                                           onclick: function(){ editarDatosPersonalesPaciente(); }
                                        },
                                        editarTelefonos : {
                                                    title : 'Editar Teléfonos',
                                                    icon: 'ui-icon-clipboard', 
                                                    onclick: function(){ editarTelefonosPaciente(); }
                                                 }, 
                                        editarContactos : {
                                                    title : 'Editar Contactos',
                                                    icon: 'ui-icon-note', 
                                                    onclick: function(){ editarContactosPaciente(); }
                                                 },    
                                        editarDatosClinicos : {
                                                    title : 'Editar Datos Clínicos',
                                                    icon: 'ui-icon-folder-open', 
                                                    onclick: function(){ editarDatosClinicosPaciente(); }
                                                 },
                                        editarAlergias : {
                                                    title : 'Editar Alergias',
                                                    icon: 'ui-icon-document', 
                                                    onclick: function(){ editarAlergiasPaciente(); }
                                                 },
                                        editarCirugias : {
                                                    title : 'Editar Cirugias',
                                                    icon: 'ui-icon-document', 
                                                    onclick: function(){ editarCirugiasPaciente(); }
                                                 },
                                        editarDiscapacidades : {
                                                    title : 'Editar Discapacidades',
                                                    icon: 'ui-icon-document', 
                                                    onclick: function(){ editarDiscapacidadesPaciente(); }
                                                 },
                                        editarMedicamentos : {
                                                    title : 'Editar Medicamentos',
                                                    icon: 'ui-icon-document', 
                                                    onclick: function(){ editarMedicamentosPaciente(); }
                                                 },
                                        editarEnfermedadesCronicas: {
                                                    title : 'Editar EnfermedadesCronicas',
                                                    icon: 'ui-icon-document', 
                                                    onclick: function(){ editarEnfermedadesCronicasPaciente(); }
                                                 },
                                        seperator: {
                                                    title : 'seperator'
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
                <sjg:gridColumn editable="true" hidden="true" name="clave" index="clave" title="Password" sortable="false"/>
                <sjg:gridColumn editable="false" name="fechaRegistro" index="fechaRegistro" title="Fecha Registro" sortable="false"/>
            </sjg:grid>
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
                <s:hidden name="tipoUsuario" value="Paciente" />
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
        <s:form enctype="multipart/form-data" name="formEditarDatosIdentificacionPaciente" action="editarDatosIdentificacionPaciente" onsubmit="return validarCamposDatosIdentificacionPaciente();" method="post" role="form">
            <s:hidden name="nombreUsuario" id="nomUs"/>
            <s:hidden name="codigoHospital" id="codHos"/>
            
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
                <s:hidden name="idDomicilioPaciente" id="idDomPaciente"/>
                <s:hidden name="nombreUsuario" id="nomUsr"/>
                <s:hidden name="nss" id="noSeSo"/>
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
        <form id="formEditarTelefonosPaciente" name="formEditarTelefonosPaciente" action="editarTelefonosPaciente" method="post" role="form">
            <div class="box-body" id="datosTelefonosPaciente" style='display:none;'>
                <div class="box box-primary box-solid">
                    <div class="box-header with-border">
                        <i class="fa fa-phone"></i>
                        <h3 class="box-title"><label>Números Telefónicos</label></h3>
                        <div class="box-tools pull-right">
                            <button title="Expandir" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-minus"></i>
                            </button>
                            <button title="Agregar Teléfono" type="button" id="agregarTelefono" class="btn btn-box-tool btn-primary">
                                <i class="fa fa-plus-circle"></i>
                            </button>
                            <button title="Eliminar Teléfono" type="button" id="eliminarTelefono" class="btn btn-box-tool btn-danger">
                                <i class="fa fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body" id="TextBoxesGroupTelefonos">
                        
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionNumTel();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div>
        </form>
        <s:form name="formEditarDatosPersonalesPaciente" action="editarDatosPersonalesPaciente" onsubmit="return validarCamposDatosPersonalesPaciente();" method="post" role="form">
            <div id="datosPersonalesPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Datos Personales</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUs4"/>
                <s:hidden name="idDatosPersonalesPaciente" id="idDaPePa"/>
                <s:hidden name="nss" id="nssDatosPersonales"/>
                <div id="divEdoCivilPaciente" class="form-group">
                    <label for="estadoCivil">Estado Civil</label>
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="estadoCivil" id="radioCasado" value="casado"/>
                                        Casado(a)
                                    </label>
                                </div>
                            </div><!-- /.col-lg-6 -->
                        </div>
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="estadoCivil" id="radioUnionLibre" value="union libre" />
                                        Unión libre
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="estadoCivil" id="radioSoltero" value="soltero" />
                                        Soltero(a)
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="estadoCivil" id="radioDivorciado" value="divorciado" />
                                        Divorsiado(a)
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="divCurpPaciente" class="form-group">
                    <label for="curp">CURP</label>
                    <input readonly="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="curp" id="curp" placeholder="CURP" type="text">
                </div>

                <div id="divSexoPaciente" class="form-group">
                    <label for="sexo">Género</label>
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="sexo" id="radioMasculino" value="masculino"/>
                                        Masculino
                                    </label>
                                </div>
                            </div><!-- /.col-lg-6 -->
                        </div>
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="sexo" id="radioFemenino" value="femenino" />
                                        Femenino
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="divFechaNacimientoPaciente" class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento</label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <input kl_virtual_keyboard_secure_input="on" class="form-control" name="fechaNacimiento" id="fechaNacimiento" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask type="text">
                    </div>
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

                <div id="divCorreoPaciente" class="form-group">
                    <label for="correo">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correo" placeholder="Email" type="text">
                </div>

                <div id="divFacebookPaciente" class="form-group">
                    <label for="facebook">Facebook (www.facebook.com/alguien)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="facebook" id="facebook" placeholder="Facebook" type="text">
                </div>
                
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionDatosPersonales();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
                
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarContactosPaciente" action="editarContactosPaciente" method="post" role="form">
            <div class="box-body" id="datosContactosPaciente" style="display: none;">
                <div class="box box-primary box-solid">
                    <div class="box-header with-border">
                        <i class="fa fa-book"></i>
                        <h3 class="box-title"><label>Contactos del Paciente</label></h3>
                        <div class="box-tools pull-right">
                            <button title="Expandir" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-minus"></i>
                            </button>
                            <button type="button" title="Agregar Contacto" id="agregarContacto" class="btn btn-box-tool btn-primary">
                                <i class="fa fa-plus-circle"></i>
                            </button>
                            <button type="button" title="Eliminar Contacto" id="eliminarContacto" class="btn btn-box-tool btn-danger">
                                <i class="fa fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <input type="hidden" name="nombreUsuarioContacto" id="nombreUsuarioContacto"/>
                    <div class="box-body" id="TextBoxesGroupContacto">
                        
                        
<!--                        <div id="contactoPaciente1">
                            <label for="nombreC">Contacto #1</label>
                            <div id="divNombreCPaciente1" class="form-group">
                                <label for="nombreC">Nombre</label>
                                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreContacto1" id="nombreContacto1" placeholder="Nombre" type="text">
                            </div>

                            <div id="divApellidoPaternoCPaciente1" class="form-group">
                                <label for="apellidoPaternoC">Apellido Paterno</label>
                                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaternoContacto1" id="apellidoPaternoContacto1" placeholder="Apellido Paterno" type="text">
                            </div>

                            <div id="divApellidoMaternoCPaciente1" class="form-group">
                                <label for="apellidoMaternoC">Apellido Materno</label>
                                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaternoContacto1" id="apellidoMaternoContacto1" placeholder="Apellido Materno" type="text">
                            </div>

                            <div id="divParentescoCPaciente1" class="form-group">
                                <label for="parentesco">Parentesco</label>
                                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="parentescoContacto1" id="parentescoContacto1" placeholder="Parentesco" type="text">
                            </div>

                            <div id="divCelularCPaciente1" class="form-group">
                                <label for="celular">Telefono Celular</label>
                                <input kl_virtual_keyboard_secure_input="on" name="celularContacto1" id="celularContacto1" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Celular" type="text">
                            </div>

                            <div id="divFacebookCPaciente1" class="form-group">
                                <label for="facebookC">Facebook (www.facebook.com/alguien)</label>
                                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="facebookContacto1" id="facebookContacto1" placeholder="Facebook" type="text">
                            </div>

                            <div id="divCorreoCPaciente1" class="form-group">
                                <label for="correoC">Email</label>
                                <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correoContacto1" id="correoContacto1" placeholder="Email" type="text">
                            </div>
                        </div>-->
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionContactos();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form> 
        <s:form name="formEditarDatosClinicosPaciente" action="editarDatosClinicosPaciente" method="post" role="form">
            <div id="datosClinicosPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Datos Clinicos</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUsrClin"/>
                <s:hidden name="noHistorial" id="noHisto"/>
                <s:hidden name="nss" id="nssClinico"/>
                
                <div id="divDrograsPaciente" class="form-group">
                    <label for="sexo">Uso de Drogas?</label>
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="drogas" id="radioDrogasSi" value="1" />
                                        Si
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="drogas" id="radioDrogasNo" value="0"/>
                                    No
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="divAlcoholPaciente" class="form-group">
                    <label for="sexo">Uso de Alcohol?</label>
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="alcohol" id="radioAlcoholSi" value="1" />
                                        Si
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="alcohol" id="radioAlcoholNo" value="0"/>
                                        No
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="divFumaPaciente" class="form-group">
                    <label for="sexo">Fuma?</label>
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="fuma" id="radioFumaSi" value="1"/>
                                        Si
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2">
                            <div class="input-group">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="fuma" id="radioFumaNo" value="0"/>
                                        No
                                    </label>
                                </div>
                            </div>
                        </div>    
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionDatosClinicos();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarAlergiasPaciente" action="editarAlergiasPaciente" method="post" role="form">
            <div id="datosAlergiasPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Alergias</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUsrClin"/>
                <s:hidden name="noHistorial" id="noHisto"/>
                <s:hidden name="nss" id="nssClinico"/>

                <!--***************************************Alergias***********************************************-->
                <div class="box box-primary box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title"><label>Alergias</label></h3>
                        <div class="box-tools pull-right">
                            <button title="Expandir" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxAlergia0" name="checkboxAlergia0" value="polen" onclick="validarCheckboxAlergias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Polen"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <textarea class="form-control" disabled="true" id="especificarAlergia0" name="especificarAlergia0" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxAlergia1" name="checkboxAlergia1" value="acaros" onclick="validarCheckboxAlergias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Ácaros del polvo">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <textarea class="form-control" disabled="true" name="especificarAlergia1" id="especificarAlergia1" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxAlergia2" name="checkboxAlergia2" value="animales" onclick="validarCheckboxAlergias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Animales">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <textarea class="form-control" disabled="true" name="especificarAlergia2" id="especificarAlergia2" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxAlergia3" name="checkboxAlergia3" value="medicamentos" onclick="validarCheckboxAlergias();"/>
                                    </span>
                                    <input disabled="true" disabled="true" class="form-control" type="text" value="Medicamentos">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <textarea class="form-control" disabled="true" name="especificarAlergia3" id="especificarAlergia3" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxAlergia4" name="checkboxAlergia4" value="insectos" onclick="validarCheckboxAlergias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Picadura de insectos">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <textarea class="form-control" disabled="true" name="especificarAlergia4" id="especificarAlergia4" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxAlergia5" name="checkboxAlergia5" value="alimentos" onclick="validarCheckboxAlergias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Alimentos">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <textarea class="form-control" disabled="true" name="especificarAlergia5" id="especificarAlergia5" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                                </div><!-- /input-group -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionAlergias();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarCirugiasPaciente" action="editarCirugiasPaciente" method="post" role="form">
            <!--***************************************Cirugias***********************************************-->
            <div id="datosCirugiasPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Cirugias</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUsrClin"/>
                <s:hidden name="noHistorial" id="noHisto"/>
                <s:hidden name="nss" id="nssClinico"/>

                <!--***************************************Cirugias***********************************************-->
                <div class="box box-primary box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title"><label>Cirugías</label></h3>
                        <div class="box-tools pull-right">
                            <button title="Expandir" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxCirugia0" name="checkboxCirugia0" value="interna" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Interna"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia0" id="noCirugia0" disabled="true" type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxCirugia1" name="checkboxCirugia1" value="externa" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Externa">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia1" id="noCirugia1" disabled="true" type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" name="checkboxCirugia2" id="checkboxCirugia2" value="mayor" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Mayor">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia2" id="noCirugia2" disabled="true" type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" name="checkboxCirugia3" id="checkboxCirugia3" value="menor" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Menor">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia3" id="noCirugia3" disabled="true" type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" name="checkboxCirugia4" id="checkboxCirugia4" value="curativa" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Curativa">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia4" id="noCirugia4" disabled="true"  type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" name="checkboxCirugia5" id="checkboxCirugia5" value="reparadora" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Reparadora">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia5" id="noCirugia5" disabled="true" type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" name="checkboxCirugia6" id="checkboxCirugia6" value="paliativa" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Paliativa">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia6" id="noCirugia6" disabled="true" type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" name="checkboxCirugia7" id="checkboxCirugia7" value="cosmetica" onclick="validarCheckboxCirugias();"/>
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Cosmetica">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">

                                    <input class="form-control" name="noCirugia7" id="noCirugia7" disabled="true" type="number" min="0" value="" placeholder="no. de cirugias"/>
                                </div><!-- /input-group -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionCirugias();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarDiscapacidadesPaciente" action="" method="post" role="form">
            <!--***************************************Discapacidades***********************************************-->
                <div id="datosDiscapacidadesPaciente" class="box-body" style='display:none;'>
                <div class="box-header">
                    <h3 class="box-title">Editar Discapacidades</h3>
                </div>
                <s:hidden name="nombreUsuario" id="nomUsrClin"/>
                <s:hidden name="noHistorial" id="noHisto"/>
                <s:hidden name="nss" id="nssClinico"/>

                <!--***************************************Discapacidades***********************************************-->
                <div class="box box-primary box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title"><label>Discapacidades</label></h3>
                        <div class="box-tools pull-right">
                            <button title="Expandir" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-minus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxDiscapacidad0" name="checkboxDiscapacidad0" value="fisica">
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Fisica"/>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxDiscapacidad1" name="checkboxDiscapacidad1" value="intelectual">
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Intelectual">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxDiscapacidad2" name="checkboxDiscapacidad2" value="psiquica">
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Psíquica">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxDiscapacidad3" name="checkboxDiscapacidad3" value="visual">
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Visual">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxDiscapacidad4" name="checkboxDiscapacidad4" value="auditiva">
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Auditiva">
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div style="margin-bottom:10px;" class="input-group">
                                    <span class="input-group-addon">
                                        <input type="checkbox" id="checkboxDiscapacidad5" name="checkboxDiscapacidad5" value="habla">
                                    </span>
                                    <input disabled="true" class="form-control" type="text" value="Habla">
                                </div><!-- /input-group -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionDiscapacidades();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div><!-- /.box-body -->
        </s:form>
        <s:form name="formEditarMedicamentosPaciente" action="editarMedicamentosPacientes" method="post" role="form">
            <div class="box-body" id="datosMedicamentosPaciente" style='display:none;'>
                <div class="box box-primary box-solid">
                    <div class="box-header with-border">
                        <i class="fa fa-phone"></i>
                        <h3 class="box-title"><label>Medicamentos</label></h3>
                        <div class="box-tools pull-right">
                            <button title="Expandir" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-minus"></i>
                            </button>
                            <button title="Agregar Medicamento" type="button" id="agregarMedicamento" class="btn btn-box-tool btn-primary">
                                <i class="fa fa-plus-circle"></i>
                            </button>
                            <button title="Eliminar Medicamento" type="button" id="eliminarMedicamento" class="btn btn-box-tool btn-danger">
                                <i class="fa fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body" id="TextBoxesGroup">
                        
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionMedicamentos();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div>
        </s:form>
        <s:form name="formEditarEnfermedadesCronicasPaciente" action="editarEnfermedadesCronicasPaciente" method="post" role="form">
            <div class="box-body" id="datosEnfermedadesPaciente" style='display:none;'>
                <div class="box box-primary box-solid">
                    <div class="box-header with-border">
                        <h3 class="box-title"><label>Enfermedades Crónicas</label></h3>
                        <div class="box-tools pull-right">
                            <button title="Expandir" class="btn btn-box-tool" data-widget="collapse">
                                <i class="fa fa-minus"></i>
                            </button>
                            <button title="Agregar Enfermedad" type="button" id="agregarEnfermedad" class="btn btn-box-tool btn-primary">
                                <i class="fa fa-plus-circle"></i>
                            </button>
                            <button title="Eliminar Enfermedad" type="button" id="eliminarEnfermedad" class="btn btn-box-tool btn-danger">
                                <i class="fa fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body" id="TextBoxesGroupEnfermedadCronica">
<!--                        <div class="row" id="enfermedadesCronicas1">
                            <div class="col-lg-4">
                                <div style="margin-bottom:10px;" class="form-group">
                                    <label>Nombre enfermedad #1</label>
                                    <input class="form-control" name="enfermedadCronica1" type="text" placeholder="Nombre enfermedad"/>
                                </div> /input-group 
                            </div>
                            <div class="col-lg-4">
                                <label>Tipo</label>
                                <select name="tipoEnfermedad1" class="form-control">
                                    <option value="-1">Seleccionar</option>
                                    <option value="diabetes">Diabetes</option>
                                    <option value="cardiovascular">Enfermedades cardiovasculares</option>
                                    <option value="obesidad">Obesidad</option>
                                    <option value="cancer">Cáncer</option>
                                    <option value="dislipidemias">Dislipidemias</option>
                                </select>
                            </div>
                            <div class="col-lg-4">
                                <div id="divInicioEnfermedadPaciente" class="form-group">
                                    <label for="inicioEnfermedad1">Inicio de enfermedad</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <input kl_virtual_keyboard_secure_input="on" class="form-control" name="inicioEnfermedad" id="inicioEnfermedad" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask type="text">
                                    </div>
                                </div>
                            </div>
                        </div> -->
                    </div>
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary btn-sm margin">Actualizar</button>
                    <button type="button" onclick="cancelarEdicionEnfermedades();" class="btn btn-danger btn-sm margin">Cancelar</button>
                </div>
            </div>
        </s:form>
    </div>
</section>

<!-- InputMask -->
<script src="bootstrap/js/fileinput.min.js" type="text/javascript"></script>
<link href="bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

<script src="js/funcionesPaciente/funcionesEliminarEditarPaciente.js" type="text/javascript"></script>
<script type="text/javascript" src="bootstrap/js/jquery.modal.js"></script>
<link href="bootstrap/css/jquery.modal.css" type="text/css" rel="stylesheet" />
