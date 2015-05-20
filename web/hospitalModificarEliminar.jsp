<%-- 
    Document   : hospitalModificarEliminar
    Created on : 26-abr-2015, 23:33:50
    Author     : Alejandro
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
            <h3 class="box-header"><b>Hospitales Registrados</b></h3>
        </div><!-- /.box-header -->
        <div class="box-body" style="width: 82%">
        <sjg:grid
            id		     ="gridListaHospitales"		gridModel	="gridListaHospitales"
            href	     ="ajaxLlenarListaHospitalesME"     dataType	="json"        
            caption	     ="Hospitales"			altRows		="true"								
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
        title : 'Modificacr Inicio de Sesion', 
        icon:'ui-icon-key',  
        onclick: function(){ muestraFormSesion() }
        },
        mostrarFormDatosHospital : { 
        title : 'Modificacr Datos del Hospital', 
        icon:'ui-icon-pencil', 
        onclick: function(){ muestraFormDatosHospital() }
        },
        mostrarFormDireccionHospital : { 
        title : 'Modificacr Direccion del Hospital', 
        icon:'ui-icon-home', 
        onclick: function(){ muestraDireccionHospital() }
        },
        mostrarFormDirectivo : { 
        title : 'Modificacr Datos del Directivo', 
        icon:'ui-icon-person', 
        onclick: function(){ muestraFormDirectivo() }
        },
        mostrarFormEspecialidades : { 
        title : 'Modificacr Especialidades', 
        icon:'ui-icon-circle-plus', 
        onclick: function(){ muestraFormEspecialidades() }
        },
        eliminarHospital : { 
        title : 'Eliminar Hospital', 
        icon:'ui-icon-trash', 
        onclick: function(){ eliminarHospital() }
        }
        }"
           >

            <sjg:gridColumn id="codigoHospital" key="true" name="codigoHospital"	title="Codigo Hospital" 		index="codigoHospital"		sortable="false" />

            <sjg:gridColumn id="nombre"  name="nombre"	title="Nombre" 		index="nombre"		sortable="false" />

            <sjg:gridColumn id="lada" name="lada"	title="lada" 		index="lada"		sortable="false"/>

            
            <sjg:gridColumn id="telefono" name="telefono"	title="telefono" 		index="telefono" />
 
            <sjg:gridColumn  name="EMail"			title="EMail" 		index="EMail"		sortable="false"/>

           </sjg:grid> 
            </div>
    </div>

    <!-- **************************************INICIA DIV FORM SESION**********************************************  -->
    <div class="box box-primary" id="divFormInicioSesion" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Modificar Hospital</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <form name="formDatosSesionHospital" action="modificarDatosSesionHospital" onsubmit="return validarCamposFormSesion();"  method="post" role="form">
            <input type="hidden" name="codigoHospitalEditar" id="codigoHSesion">
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos de inicio de sesión</h3>
                </div>

                <div id="divClaveUsuario" class="form-group">
                    <label for="claveUsuario">Clave de Acceso</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" id="claveUsuario" name="claveUsuario" placeholder="Clave de Acceso" type="text">
                </div>
            </div><!-- /.box-body -->

            <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>

    <!-- ***********************************INICIA DIV FORM DATOS HOSPITAL*****************************************  -->
    <div class="box box-primary" id="divFormDatosHospital" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Modificar Hospital</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <form name="formDatosHospital" action="modificarDatosHospital" onsubmit="return validarCamposFormDatosHospital();"  method="post" role="form">
            <input type="hidden" name="codigoHospitalEditar" id="codigoHDatos">
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


            <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>

    <!-- ************************************INICIA DIV FORM DIRECCION*********************************************  -->
    <div class="box box-primary" id="divFormDireccionHospital" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Modificar Hospital</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <form name="formDireccionHospital" action="modificarDatosDireccionHospital" onsubmit="return validarCamposFormDireccionHospital();"  method="post" role="form">
            <input type="hidden" name="latitudY" id="latitudY">
            <input type="hidden" name="longitudX" id="longitudX">
            
            <!-- /google maps -->
                <input id="autocomplete" class="form-control" placeholder="Ingresa la Dirección" type="textbox"></input>
                <div id="map_canvas" style="height: 300px; margin-bottom: 20px;"></div>
                <!-- /fin google maps -->
            
            <input type="hidden" name="codigoHospitalEditar" id="codigoHDireccion">
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Dirección</h3>
                </div>

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
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="colonia" placeholder="Colonia" type="text">
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
                    <input kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostal" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
                </div>


            </div><!-- /.box-body -->


            <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>

    <!-- **************************************INICIA DIV FORM DIRECTIVO*******************************************  -->
    <div class="box box-primary" id="divFormDirectivo" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Modificar Hospital</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <form name="formDirectivoHospital" action="modificarDatosDirectivoHospital" onsubmit="return validarFormDirectivo();"  method="post" role="form">
            <input type="hidden" name="codigoHospitalEditar" id="codigoHDirectivo">
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


            <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
    
    <!-- ***********************************INICIA DIV FORM ESPECIALIDADES*****************************************  -->
    <div class="box box-primary" id="divFormEspecialidades" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><b>Modificar Hospital</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <form name="formEspecialidadesHospital" action="modificarDatosEspecialidadesHospital" onsubmit="return validarCampos();"  method="post" role="form">
            <input type="hidden" name="codigoHospitalEditar" id="codigoHEspecialidades">
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Especialidades</h3>
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
<!--Script google maps -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDZqR4-rCjdcnt_0aIR_C4CB7B5BNmeLI&v=3.exp&signed_in=true&libraries=places"></script>
<script src="js/funcionesHospital/funcionesGoogleMapsDirecciones.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/estiloMapaAutoCompletado.css"/>

<!--Script creados -->
<script src="js/funcionesHospital/funcionesModificarEliminarHospital.js" type="text/javascript"></script>
<script src="bootstrap/js/jquery.modal.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="bootstrap/css/jquery.modal.css"/>





