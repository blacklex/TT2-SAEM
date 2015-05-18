/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusConsultarHospital");
    

    $(document).ajaxSuccess(function (event, request, settings) {

        
        if (settings.url.match('recuperarMensajeEstatusConsultarHospital') != null) {

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


function ocultarForms() {
    $("#divFormInicioSesion").slideUp('slow');
    $("#divFormDatosHospital").slideUp('slow');
    $("#divFormDireccionHospital").slideUp('slow');
    $("#divFormDirectivo").slideUp('slow');
    $("#divFormEspecialidades").slideUp('slow');
    $("#divjGrid").slideDown('slow');
}

function muestraFormSesion() {
    if ($("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaConsultaHospitales").jqGrid('getCell', s, 'codigoHospital');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaSesion",
            data: {codigoHospital: codigoHospital}
        })
                .done(function (msg) {

                    $("#claveUsuario").val(msg.claveUsuario);
                    $("#nombreUsuario").val(msg.nombreUsuario);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormInicioSesion").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Hospital de la tabla.");
    }
}

function muestraFormDatosHospital() {
    if ($("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaConsultaHospitales").jqGrid('getCell', s, 'codigoHospital');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaHospital",
            data: {codigoHospital: codigoHospital}
        })
                .done(function (msg) {

                    var nombreHospital = msg.nombreHospital;
                    var telefonoHospital = msg.telefonoHospital;
                    var emailHospital = msg.emailHospital;

                    $("#nombreHospital").val(nombreHospital);
                    $("#telefonoHospital").val(telefonoHospital);
                    $("#emailHospital").val(emailHospital);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormDatosHospital").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Hospital de la tabla.");
    }

}

function muestraDireccionHospital() {
    if ($("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaConsultaHospitales").jqGrid('getCell', s, 'codigoHospital');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaDireccion",
            data: {codigoHospital: codigoHospital}
        })
                .done(function (msg) {

                    $("#calle").val(msg.calle);
                    $("#numero").val(msg.numero);
                    $("#colonia").val(msg.colonia);
                    $("#delegacion").val(msg.delegacion);
                    $("#entidadFederativa").val(msg.entidadFederativa);
                    $("#codigoPostal").val(msg.codigoPostal);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormDireccionHospital").slideDown('slow',function (){inicializarMapaConsultar(msg.latitudY,msg.longitudX);});
                    });
                });
    } else {
        alert("Seleccione un Hospital de la tabla.");
    }

}

function muestraFormDirectivo() {
    if ($("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaConsultaHospitales").jqGrid('getCell', s, 'codigoHospital');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaDirectivo",
            data: {codigoHospital: codigoHospital}
        })
                .done(function (msg) {

                    $("#telefonoDirectivo").val(msg.telefonoDirectivo);
                    $("#emailDirectivo").val(msg.emailDirectivo);
                    $("#nombreDirectivo").val(msg.nombreDirectivo);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormDirectivo").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Hospital de la tabla.");
    }


}

function muestraFormEspecialidades() {
    $("#divjGrid").slideUp('slow', function () {
        $("#divFormEspecialidades").slideDown('slow');
    });
}







