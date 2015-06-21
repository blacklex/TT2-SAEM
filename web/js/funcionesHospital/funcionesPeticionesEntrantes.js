/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusPeticionesEntrantes");
    //consultarPeticionesEntrantesGrid();

    $(document).ajaxSuccess(function (event, request, settings) {


        if (settings.url.match('recuperarMensajeEstatusPeticionesEntrantes') != null) {

            var tituloAlert = $.parseJSON(request.responseText).tituloAlert;
            var textoAlert = $.parseJSON(request.responseText).textoAlert;
            var estatusMensaje = $.parseJSON(request.responseText).estatusMensaje;

            if (estatusMensaje == null)
                return;

            if (estatusMensaje === "success") {
                $("#tituloDivAlertSuccess").html("<i class='icon fa fa-check'></i>" + tituloAlert);
                $("#labelMensajeSuccess").html(textoAlert);
                $("#divAlertSuccess").slideDown('slow').delay(2500).slideUp('slow');
            } else if (estatusMensaje === "error") {
                $("#tituloDivAlertError").html("<i class='icon fa fa-ban'></i>" + tituloAlert);
                $("#labelMensajeError").html(textoAlert);
                $("#divAlertError").slideDown('slow').delay(2500).slideUp('slow');
            }
        }
    });


});

/*******************************************************************************/

$(function () {

    $("[data-mask]").inputmask();


});

function muestraFormPeticionEntrante() {

    if ($("#gridListaPeticionesEntrantes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaPeticionesEntrantes").jqGrid('getGridParam', 'selrow');

        var idPeticion = $("#gridListaPeticionesEntrantes").jqGrid('getCell', s, 'idPeticionesEntrantes');

        $("#idPeticionesEntrantesFormPeticion").val(idPeticion);

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarPacientePeticionEntrante",
            data: {idPeticionesEntrantes: idPeticion}
        })
                .done(function (msg) {
                    $("#barraCargar").slideUp(100);

                    
                    $("#nombrePaciente").val(msg.nombre);
                    $("#apellidoPaterno").val(msg.apellidoPaterno);
                    $("#apellidoMaterno").val(msg.apellidoMaterno);

                    $("#nss").val(msg.nss);
                    $("#unidadMedica").val(msg.unidadMedica);
                    $("#noConsultorio").val(msg.noConsultorio);
                    $("#edad").val(msg.edad);
                    $("#peso").val(msg.peso);
                    $("#altura").val(msg.altura);
                    $("#noHistorial").val(msg.noHistorial);

                    $("#labelNombrePaciente").html(msg.nombre + "  " + msg.apellidoPaterno);
                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormDatosPacientePeticion").slideDown('slow');
                    });

                    $.getJSON("recuperarMensajeEstatusPeticionesEntrantes");
                    $("#divMapa").slideDown('slow');
                    inicializarMapaConsultar(msg.latitudPeticion,msg.longitudPeticion);

                });
    } else {
        alert("Seleccione una peticion de la tabla.");
    }
}

function mostrarDivGrid() {
    $("#divMapa").slideUp('slow');
    $("#divFormDatosPacientePeticion").slideUp('slow');
    $("#divjGrid").slideDown('slow');

}

function validarFormRespuestaPeticionEntrante() {
    //$("#divNombreDirectivo").removeClass("has-error");
    if ($("#comentario").val().length < 1) {
        $("#divFormComentario").addClass("has-error");
        $("#divFormComentario").append("<label class='control-label' for='comentario'><i class='fa fa-times-circle-o'></i>  Ingresa un comnetario para el paciente.</label>");
        return false;

    } else {
        return true;
    }

}

function cerrarModalFormContestarPeticion() {
    $("#modalFormContestarPeticion").fadeOut('slow');
}

function mostrarModalFormContestarPeticion() {
    $("#modalFormContestarPeticion").fadeIn('slow');
}

function finalizarPeticion(){
    $("#tipoDeRespuestaPeticion").val("finalizarPeticion");
    document.forms["formPeticionEntrante"].submit();
    
}

function imprimirPeticion(){
    window.open("reportePaciente?idPeticion="+$("#idPeticionesEntrantesFormPeticion").val()+"&tipoPeticion=entrante");
}
