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




<script>
    //Esta funcion se ocupa para relaizar alguna operacion despues de una peticion ajax
    //settings.url nos perminte conocer si de ese ajax hubo una operacion exitosa
    $(document).ajaxSuccess(function (event, request, settings) {
        if (settings.url == "abcClientes") {
            var resultadoOper = $.parseJSON(request.responseText).resultadoOper;
            alert(resultadoOper);
        }
    });

    $(document).ajaxError(function (event, request, settings, thrownError) {
        if (settings.url == "abcClientes") {
            alert("Usuario no agregado");
        }
    });

    function muestraAlert() {
        alert('Llamado por el boto de alert!');
    }

</script>

<div class="alert alert-success alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4>	<i class="icon fa fa-check"></i> Alert!</h4>
    Success alert preview. This alert is dismissable.
</div>


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            <h4 class="modal-title">Modal Default</h4>
        </div>
        <div class="modal-body">
            <p>One fine body…</p>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->

<div>
    <h1>JGRID PRUEBA</h1>

 <!--

    <sjg:grid

        id="gridtable"
        caption="Clientes"
        dataType="json"
        href="Listado"
        editurl="abcClientes"
        pager="true"
        gridModel="gridModel"
        rowList="10,15,20"
        rowNum="15"
        rownumbers="true"
        navigatorSearch="false"
        navigatorRefresh="false"
        navigator="true"
        navigatorAddOptions="{closeAfterAdd:true,reloadAfterSubmit:true }"
        navigatorEditOptions="{closeAfterEdit:true,reloadAfterSubmit:true }"
        navigatorExtraButtons="{

        alert : { 
        title : 'Alert', 
        caption : 'Muestra Alert!', 
        onclick: function(){ muestraAlert() }
        }
        }"

        >
        <sjg:gridColumn name="clienteId" editable="true" index="clienteId" title="ID" key="true" sortable="false"/>
        <sjg:gridColumn editable="true"   name="name" index="name" title="Nombre" sortable="true"/>
        <sjg:gridColumn editable="true" name="country" index="country" title="Pais" sortable="false"/>
        <sjg:gridColumn editable="true" name="city" index="city" title="Ciudad" sortable="false"/>
        <sjg:gridColumn editable="true" name="creditLimit" index="Liminte de Credito" title="Credit Limit" formatter="integer" sortable="false"/>
    </sjg:grid>
 Hola
 -->
</div>


