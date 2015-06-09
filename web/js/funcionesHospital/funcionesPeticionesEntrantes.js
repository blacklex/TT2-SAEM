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
                });
    } else {
        alert("Seleccione una peticion de la tabla.");
    }
}

function mostrarDivGrid() {
    $("#divFormDatosPacientePeticion").slideUp('slow');
    $("#divjGrid").slideDown('slow');

}
