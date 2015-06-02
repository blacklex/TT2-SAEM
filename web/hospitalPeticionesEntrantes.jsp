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
            <h3 class="box-header"><label>Peticiones Entrantes</label></h3>
        </div><!-- /.box-header -->
        <div  class="box-body">
            <sjg:grid
                id           ="gridListaPeticionesEntrantes"		gridModel="gridListaPeticionesEntrantes"
                href	     ="ajaxLlenarListaPetionesEntrantes"        dataType="json"        
                caption	     ="Hospitales"                              altRows	="true"								
                pager	     ="true"                                    pagerInput="false"
                pagerButtons ="true"                                    rowList="20,30,40,50"
                rowNum	     ="20"                                      rownumbers="true"
                navigator    ="true"                                    viewrecords="true"
                hidegrid     ="false"                                   multiselect="false"
                navigatorRefresh ="false"				navigatorSearch ="false"				
                resizable    ="true"                                    autowidth="true"
                navigatorAdd ="false"				
                navigatorEdit="false"	
                navigatorDelete="false"
                navigatorAddOptions ="{closeAfterAdd:true, closeAfterSubmit:true}"
                navigatorEditOptions="{reloadAfterSubmit:true,closeAfterEdit:true}"
                navigatorExtraButtons="{

                mostrarFormSesion : { 
                title : 'Atender Petición', 
                icon:'ui-icon-key',  
                onclick: function(){ muestraFormPeticionEntrante() }
                }
                }"
                >

                <sjg:gridColumn id="codigoHospital" key="true" name="codigoHospital"	title="Codigo Hospital" 		index="codigoHospital"		sortable="false"
                                editable="false"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

                <sjg:gridColumn id="nombre"  name="nombre"	title="Nombre" 		index="nombre"		sortable="false"
                                editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

                <sjg:gridColumn id="lada" name="lada"	title="lada" 		index="lada"		sortable="false"
                                editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />


                <sjg:gridColumn id="telefono" name="telefono"	title="telefono" 		index="telefono"		sortable="false"
                                editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

                <sjg:gridColumn  name="EMail"			title="EMail" 		index="EMail"		sortable="false"
                                 editable="true"		editrules="{required:true}" editoptions="{size: 25, maxlength:15}" />

            </sjg:grid> 
        </div>
    </div>

    <!-- **************************************INICIA DIV FORM SESION**********************************************  -->

    <!-- ***********************************INICIA DIV FORM DATOS HOSPITAL*****************************************  -->

</section>


<!-- InputMask -->
<script src="plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
<!--Script creados -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDZqR4-rCjdcnt_0aIR_C4CB7B5BNmeLI&v=3.exp&signed_in=true&libraries=places"></script>
<!--<script src="js/funcionesHospital/funcionesGoogleMapsDirecciones.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/estiloMapaAutoCompletado.css"/>

<script src="js/funcionesHospital/funcionesConsultarHospital.js" type="text/javascript"></script>-->




