<%-- 
    Document   : hospitalPeticionesEntrantes.jsp
    Created on : 01-jun-2015, 23:33:50
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
    <div id="divjGrid" class="box box-primary">
        <div class="box-header">
            <h3 class="box-header"><label>Peticiones Externas</label></h3>
        </div><!-- /.box-header -->
        <div  class="box-body">
            <sjg:grid
                id           ="gridListaPeticionesExternas"		gridModel="gridListaPeticionesExternas"
                href	     ="ajaxLlenarListaPetionesExternas"         dataType="json"        
                caption	     ="Hospitales"                              altRows	="true"								
                pager	     ="true"                                    pagerInput="false"
                pagerButtons ="true"                                    rowList="20,30,40,50"
                rowNum	     ="20"                                      rownumbers="true"
                navigator    ="true"                                    viewrecords="true"
                hidegrid     ="false"                                   multiselect="false"
                navigatorRefresh ="false"				navigatorSearch ="false"				
                autowidth="true"
                navigatorAdd ="false"				
                navigatorEdit="false"	
                navigatorDelete="false"
                navigatorAddOptions ="{closeAfterAdd:true, closeAfterSubmit:true}"
                navigatorEditOptions="{reloadAfterSubmit:true,closeAfterEdit:true}"
                navigatorExtraButtons="{

                mostrarPetcionExterna : { 
                title : 'Atender Petición', 
                icon:'ui-icon-key',  
                onclick: function(){ muestraFormPeticionExterna() }
                }
                }"
                >

                <sjg:gridColumn id="idPeticionesSalientes" key="true" name="idPeticionesSalientes"	title="ID Peticion" 		index="idPeticionesSalientes"		sortable="false"
                                editable="false" />

                <sjg:gridColumn editable="false" name="fechaRegistro" index="fechaRegistro" title="Fecha de Registro" sortable="false" formatter="date" formatoptions="{newformat : 'd/m/Y H:i', srcformat : 'Y-m-d H:i:s'}"/>

                <sjg:gridColumn id="estatus" name="estatus" hidden="false"	title="Estatus" 		index="estatus"		sortable="false"
                                editable="true"	 />

                <sjg:gridColumn id="nombrePaciente" name="nombrePaciente"	title="Nombre del paciente" 		index="nombrePaciente"		sortable="false"
                                editable="true"	/>

                <sjg:gridColumn id="apellidoPaciente" name="apellidoPaciente"	title="Apellido del paciente" 		index="apellidoPaciente"		sortable="false"
                                editable="true"	/>

                <sjg:gridColumn editable="false" name="fechaNacimineto" index="fechaNacimineto" title="Fecha de Nacimiento" sortable="false" formatter="date" formatoptions="{newformat : 'd/m/Y'}"/>


                <sjg:gridColumn id="nss" name="nss"	title="NSS" 		index="nss"		sortable="false"
                                editable="true"	/>

                <sjg:gridColumn id="latitudPaciente" hidden="true" name="latitudPaciente"	title="latitudPaciente" 		index="latitudPaciente"		sortable="false"
                                editable="true"	/>

                <sjg:gridColumn  name="longitudPaciente" hidden="true"			title="longitudPaciente" 		index="longitudPaciente"		sortable="false"
                                 editable="true"	/>

            </sjg:grid> 
        </div>
    </div>

    <div class="box box-primary" id="divMapa" style="display: none;">
        <div class="box-body">
            <div class="box-header">
                <i class="fa fa-map-marker"></i>
                <h3 class="box-title"><label>Ubicación del Paciente</label></h3>
            </div>
            <div class="form-group">
                <!-- /google maps -->
                <div id="map_canvas" style="height: 300px; margin-bottom: 20px;"></div>
                <!-- /fin google maps -->
            </div>
        </div>

    </div>
    <!-- **************************************INICIA DIV  DATOS DEL PACIENTE**********************************************  -->
    <div class="box box-primary" id="divFormDatosPacientePeticion" style="display: none;">
        <div class="box-header">
            <h3 class="box-header"><label id="labelNombrePaciente"></label></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <div class="box-body">
            <div class="box-header">
                <i class="fa fa-user"></i>
                <h3 class="box-title"><label>Información Clinica</label></h3>
            </div>

            <div id="divNombre" class="form-group">
                <label for="nombrePaciente">Nombre</label>
                <input disabled="true" kl_virtual_keyboard_secure_input="on" class="form-control" name="nombre" id="nombrePaciente" placeholder="Nombre del Paciente" type="text">
            </div>
            <div id="divApellidoPaterno" class="form-group">
                <label for="apellidoPaterno">Apellido Paterno</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="apellidoPaterno" name="apellidoPaterno" placeholder="Apellido Paterno" type="text">
            </div>
            <div id="divApellidoMaterno" class="form-group">
                <label for="apellidoMaterno">Apellido Materno</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="apellidoMaterno" name="apellidoMaterno" placeholder="Apellido Materno" type="text">
            </div>
            <div id="divNoHistorial" class="form-group">
                <label for="noHistorial">No. de Historial</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="noHistorial" name="noHistorial" placeholder="No. de Hisptorial" type="text">
            </div>
            <div id="divNss" class="form-group">
                <label for="nss">Numero de Seguro Social</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="nss" name="nss" placeholder="NSS" type="text">
            </div>
            <div id="divUnidadMedica" class="form-group">
                <label for="unidadMedica">Unidad Medica</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="unidadMedica" name="unidadMedica" placeholder="Unidad Medica" type="text">
            </div>
            <div id="divNoConsultorio" class="form-group">
                <label for="noConsultorio">No. Consultorio</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="noConsultorio" name="noConsultorio" placeholder="No. Consultorio" type="text">
            </div>
            <div id="divEdad" class="form-group">
                <label for="edad">Edad</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="edad" name="edad" placeholder="Edad" type="text">
            </div>
            <div id="divPeso" class="form-group">
                <label for="peso">Peso</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="peso" name="peso" placeholder="Peso" type="text">
            </div>
            <div id="divAltura" class="form-group">
                <label for="altura">Altura</label>
                <input kl_virtual_keyboard_secure_input="on" disabled="true" class="form-control" disabled="true" id="altura" name="altura" placeholder="Altura" type="text">
            </div>

        </div><!-- /.box-body -->

        <button type="button" onclick="mostrarModalFormContestarPeticion();" class="btn btn-primary btn-sm margin">Contestar Petición</button>
        <button type="button" onclick="mostrarDivGrid();" class="btn btn-primary btn-sm margin">Regresar</button>

    </div>
    <!-- ***********************************INICIA DIV DATOS DEL PACIENTE*****************************************  -->
    <div id="modalFormContestarPeticion" class="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" onclick="cerrarModalFormContestarPeticion()" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Contestar Petición Externa</h4>
                </div>
                <div class="modal-body">
                    <div id="divFormContestarPeticionExterna">
                        <form id="formPeticionExterna" method="post" action="responderPeticionExterna" onsubmit="return validarFormRespuestaPeticionExterna()">
                            <div id="divFormComentario" class="form-group">
                                <input type="hidden" id="idPeticionesExternasFormPeticion" name="idPeticionesExternasFormPeticion"/>
                                <input type="hidden" id="tipoDeRespuestaPeticion" name="tipoDeRespuestaPeticion"/>
                                <textarea class="form-control" id="comentario" name="comentario" placeholder="Ingresa el comentario para el paciente." style="width: 100%; height: 50px;"></textarea>                 
                            </div>
                        </form>
                        <div>

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="contestarPeticion();" class="btn btn-primary  pull-left">Contestar Petición</button>
                        <button type="button" onclick="rechazarPeticion();" class="btn btn-primary  pull-left">Rechazar Petición</button>
                        <button type="button" onclick="cerrarModalFormContestarPeticion()" class="btn btn-default" data-dismiss="modal">Cerrar</button>

                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
    </div>

</section>




<!-- InputMask -->
<script src="plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
<!--Script creados -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDZqR4-rCjdcnt_0aIR_C4CB7B5BNmeLI&v=3.exp&signed_in=true&libraries=places"></script>
<script src="js/funcionesHospital/funcionesGoogleMapsDirecciones.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/estiloMapaAutoCompletado.css"/>

<script src="js/funcionesHospital/funcionesPeticionesExternas.js" type="text/javascript"></script>





