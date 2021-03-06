/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusPeticionExterna");
    //consultarPeticionesEntrantesGrid();

    $(document).ajaxSuccess(function (event, request, settings) {


        if (settings.url.match('recuperarMensajeEstatusPeticionExterna') != null) {

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

function muestraFormPeticionExterna() {

    if ($("#gridListaPeticionesExternas").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaPeticionesExternas").jqGrid('getGridParam', 'selrow');

        var idPeticion = $("#gridListaPeticionesExternas").jqGrid('getCell', s, 'idPeticionesSalientes');
        $("#idPeticionesExternasFormPeticion").val(idPeticion);
        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarPacientePeticionExterna",
            data: {idPeticionesSalientes: idPeticion}
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

                    $.getJSON("recuperarMensajeEstatusPeticionExterna");
                    $("#divMapa").slideDown('slow');
                    inicializarMapaConsultar(msg.latitudPeticion,msg.longitudPeticion);
                    
                });
    } else {
        alert("Seleccione una peticion de la tabla.");
    }
}

function mostrarDivGrid() {
    $("#divFormDatosPacientePeticion").slideUp('slow');
    $("#divMapa").slideUp('slow');
    $("#divjGrid").slideDown('slow');

}

function cerrarModalFormContestarPeticion(){
    $("#modalFormContestarPeticion").fadeOut('slow');
}

function mostrarModalFormContestarPeticion(){
    $("#modalFormContestarPeticion").fadeIn('slow');
}

function validarFormRespuestaPeticionExterna(){
    //$("#divNombreDirectivo").removeClass("has-error");
    if ($("#comentario").val().length < 1) {
        $("#divFormComentario").addClass("has-error");
        $("#divFormComentario").append("<label class='control-label' for='comentario'><i class='fa fa-times-circle-o'></i>  Ingresa un comnetario para el paciente.</label>");
        return false;

    }else{return true;}
    
}

function contestarPeticion(){
    $("#tipoDeRespuestaPeticion").val("contestarPeticion");
    imprimirPeticion();
    document.forms["formPeticionExterna"].submit();
}

function rechazarPeticion(){
    $("#tipoDeRespuestaPeticion").val("rechazarPeticion");
    document.forms["formPeticionExterna"].submit();
}

function imprimirPeticion(){
    window.open("reportePaciente?idPeticion="+$("#idPeticionesExternasFormPeticion").val()+"&tipoPeticion=externa");
}
