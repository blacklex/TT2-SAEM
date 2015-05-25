<%-- 
    Document   : pacienteConsultar
    Created on : 20/05/2015, 12:17:09 PM
    Author     : gabriela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="true" jquerytheme="redmond" />


<!-- **************************************INICIA DIV BARRA CARGA**********************************************  -->

<div style="position: fixed; z-index: 1; width: 100%; display: none;"  id="barraCargar" class="progress progress-sm active">
    <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
        <span class="sr-only">100% Complete (success)</span>
    </div>
</div>

<!-- ****************************************FIN DIV BARRA CARGA***********************************************  -->


<!-- *****************************************INCIA DIVS ALERT*************************************************  -->

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

<!-- ****************************************FIN DIVS ALERT****************************************************  -->

<section class="content">
    <!-- ****************************************INICIA DIV JGRID**************************************************  -->
    <div id="divjGrid" class="box box-primary" style="padding: 5px;">
        <div class="box-header">
            <h3 class="box-header"><b>Pacientes Registrados</b></h3>
        </div><!-- /.box-header -->
        <div  class="box-body" style="width: 82%">
            <sjg:grid
                id		     ="gridListaConsultaPacientes"		gridModel	="gridListaConsultaPacientes"
                href	     ="ajaxLlenarListaConsultarPacientes"     dataType	="json"        
                caption	     ="Pacientes"			altRows		="true"								
                pager	     ="true"				pagerInput	="false"
                pagerButtons     ="true"				rowList		="20,30,40,50"
                rowNum	     ="20"				rownumbers	="true"
                navigator	     ="true"				viewrecords	="true"
                hidegrid	     ="false"                           multiselect	="false"
                navigatorRefresh ="false"				navigatorSearch ="false"				
                resizable	     ="true"				autowidth="true"
                navigatorAdd     ="false"				
                navigatorEdit    ="false"	
                navigatorDelete  ="false"
                navigatorAddOptions ="{closeAfterAdd:true, closeAfterSubmit:true}"
                navigatorEditOptions="{reloadAfterSubmit:true,closeAfterEdit:true}"
                navigatorExtraButtons="{

                mostrarFormSesion : { 
                title : 'Mostrar Inicio de Sesion', 
                icon:'ui-icon-key',  
                onclick: function(){ muestraFormSesion() }
                },
                mostrarFormDatosPaciente : { 
                title : 'Mostrar Datos del Paciente', 
                icon:'ui-icon-pencil', 
                onclick: function(){ muestraFormDatosPaciente() }
                },
                mostrarFormDireccionPaciente : { 
                title : 'Mostrar Direccion del Paciente', 
                icon:'ui-icon-home', 
                onclick: function(){ muestraDireccionPaciente() }
                },
                mostrarFormTelefonosPaciente : { 
                title : 'Mostrar Teléfonos del Paciente', 
                icon:'ui-icon-person', 
                onclick: function(){ muestraFormTelefonosPaciente() }
                },
                mostrarFormDatosPersonalesPaciente : { 
                title : 'Mostrar Datos Personales del Paciente', 
                icon:'ui-icon-person', 
                onclick: function(){ muestraFormDatosPersonalesPaciente() }
                },
                mostrarFormContactosPaciente : { 
                title : 'Mostrar Contactos del Paciente', 
                icon:'ui-icon-person', 
                onclick: function(){ muestraFormContactosPaciente() }
                },
                mostrarFormDatosClinicosPaciente : { 
                title : 'Mostrar Datos Clínicos del Paciente', 
                icon:'ui-icon-person', 
                onclick: function(){ muestraFormDatosClinicosPaciente() }
                }
                }"
                >

                <sjg:gridColumn id="nssP" key="true" name="nssP"	title="Numero de Seguro Social" 		index="nss"		sortable="false"	width="100"
                                editable="false"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

                <sjg:gridColumn id="nombre"  name="nombre"	title="Nombre" 		index="nombre"		sortable="false"	width="100"
                                editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

                <sjg:gridColumn id="apellidoPaterno" name="apellidoPaterno"	title="Apellido Paterno" 		index="apellidoPaterno"		sortable="false"	width="100"
                                editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />


                <sjg:gridColumn id="apellidoMaterno" name="apellidoMaterno"	title="Apellido Materno" 		index="apellidoMaterno"		sortable="false"	width="100"
                                editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

                <sjg:gridColumn  id="unidadMedica"   name="unidadMedica"	title="Unidad Medica" 		index="unidadMedica"		sortable="false"	width="100"
                                 editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

            </sjg:grid> 
        </div>
    </div>

    <!-- **************************************INICIA DIV FORM SESION**********************************************  -->
    <div class="box box-primary" id="divFormInicioSesion" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Consultar Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <h3 class="box-title">Datos de inicio de sesión</h3>
            </div>

            <div id="divNombreUsuario" class="form-group">
                    <label for="nombreUsuario">Nombre de usuario</label>
                    <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuario" placeholder="Nombre de usuario" type="text">
                </div>
            <div id="divClaveUsuario" class="form-group">
                <label for="claveUsuario">Clave de Acceso</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" id="claveUsuario" name="claveUsuario" placeholder="Clave de Acceso" type="text">
            </div>
        </div><!-- /.box-body -->



    </div>

    <!-- ***********************************INICIA DIV FORM DATOS PACIENTE*****************************************  -->
    <div class="box box-primary" id="divFormDatosPaciente" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Consultar Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <h3 class="box-title">Datos del Paciente</h3>
            </div>
            
            <div id="divNSSPaciente" class="form-group">
                <label for="nss">NSS</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="nss" id="nss" placeholder="NSS" type="text">
            </div>

            <div id="divNombrePaciente" class="form-group">
                <label for="nombrePaciente">Nombre</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="nombrePaciente" id="nombrePaciente" placeholder="Nombre del Paciente" type="text">
            </div>
            
            <div id="divApellidoPaternoPaciente" class="form-group">
                <label for="apellidoPaternoPaciente">Apellido Paterno</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaternoPaciente" id="apellidoPaternoPaciente" placeholder="Apellido Paterno" type="text">
            </div>
            
            <div id="divApellidoMaternoPaciente" class="form-group">
                <label for="apellidoMaternoPaciente">Apellido Materno</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaternoPaciente" id="apellidoMaternoPaciente" placeholder="Apellido Materno" type="text">
            </div>

            <div id="divUnidadMedicaPaciente" class="form-group">
                <label for="unidadMedicaPaciente">Unidad Médica</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="unidadMedicaPaciente" id="unidadMedicaPaciente" placeholder="Unidad Medica" type="text">
            </div>
            
            <div id="divNoConsultorioPaciente" class="form-group">
                <label for="noConsultorio">Numero de Consultorio</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="noConsultorio" id="noConsultorio" placeholder="Numero de Consultorio" type="text">
            </div>

        </div><!-- /.box-body -->

    </div>

    <!-- ************************************INICIA DIV FORM DOMICILIO*********************************************  -->
    <div class="box box-primary" id="divFormDireccionPaciente" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Consultar Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <h3 class="box-title">Domicilio</h3>
            </div>

            <!-- /google maps -->
            <input id="autocomplete" class="form-control" placeholder="Ingresa el Domicilio" type="hidden"></input>
                <div id="map_canvas" style="height: 300px; margin-bottom: 20px;"></div>
                <!-- /fin google maps -->
            <div id="divCallePaciente" class="form-group">
                <label for="calle">Calle</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calle" placeholder="Calle" type="text">
            </div>

            <div id="divNumeroPaciente" class="form-group">
                <label for="numero">Numero</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="numero" id="numero" placeholder="Numero" type="text">
            </div>

            <div id="divColoniaPaciente" class="form-group">
                <label for="colonia">Colonia</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="colonia" placeholder="Colonia" type="text">
            </div>

            <div id="divDelegacionPaciente" class="form-group">
                <label for="delegacion">Delegacón</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacion" placeholder="Delegación" type="text">
            </div>

            <div id="divEntidadFederativaPaciente" class="form-group">
                <label for="entidadFederativa">Entidad Federativa</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativa" placeholder="Entidad Federativa" type="text">
            </div>

            <div id="divCodigoPostalPaciente" class="form-group">
                <label for="codigoPostal">Codigo Postal</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostal" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
            </div>

        </div><!-- /.box-body -->
    </div>

    <!-- **************************************INICIA DIV FORM TELEFONOS*******************************************  -->
    <div class="box box-primary" id="divFormTelefonos" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Consultar Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <h3 class="box-title">Teléfonos del Paciente</h3>
            </div>

            <div id="divTelefonoFijoPaciente" class="form-group">
                <label for="telefonoFijo">Telefono Fijo</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telefonoFijo" id="telefonoFijo" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Fijo" type="text">
            </div>

            <div id="divTelefonoParticularPaciente" class="form-group">
                <label for="telefonoParticular">Telefono Particular</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" name="telefonoParticular" id="telefonoParticular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Telefono Particular" type="text">
            </div>

        </div><!-- /.box-body -->

    </div>
    
    <!-- ***********************************INICIA DIV FORM DATOS PERSONALES PACIENTE*****************************************  -->
    <div class="box box-primary" id="divFormDatosPersonalesPaciente" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Consultar Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <h3 class="box-title">Datos Personales del Paciente</h3>
            </div>
            
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

        </div><!-- /.box-body -->

    </div>
    
    <!-- ***********************************INICIA DIV FORM CONTACTOS PACIENTE*****************************************  -->
    <div class="box box-primary" id="divFormContactosPaciente" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Consultar Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <h3 class="box-title">Contactos del Paciente</h3>
            </div>
            
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
            

        </div><!-- /.box-body -->

    </div>

    <!-- ***********************************INICIA DIV FORM DATOS CLINICOS*****************************************  -->
    <div class="box box-primary" id="divFormDatosClinicosPaciente" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Consultar Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <h3 class="box-title">Datos Clínicos</h3>
            </div>
            
            <div id="divDrogasPaciente" class="form-group">
                <label for="usoDrogas">Usa Drogas</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="usoDrogas" id="usoDrogas" value="1" type="checkbox">
            </div>
            
            <div id="divAlcoholPaciente" class="form-group">
                <label for="usoAlcohol">Consume Alcohol</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="usoAlcohol" id="usoAlcohol" value="1" type="checkbox">
            </div>
            
            <div id="divFumaPaciente" class="form-group">
                <label for="fumador">Fuma</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="fumador" id="fumador" value="1" type="checkbox">
            </div>

        </div><!-- /.box-body -->

    </div>
</section>


<!-- InputMask -->
<script src="plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
<!--Script creados -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDZqR4-rCjdcnt_0aIR_C4CB7B5BNmeLI&v=3.exp&signed_in=true&libraries=places"></script>
<script src="js/funcionesGoogleMapsDirecciones.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/estiloMapaAutoCompletado.css"/>

<script src="js/funcionesPaciente/funcionesConsultarPaciente.js" type="text/javascript"></script>
