/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusConsultarHospital");
    $.getJSON("recuperarCodigoHospital");

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

        if (settings.url.match('recuperarCodigoHospital') != null) {
            var codigoHospitalTemp = $.parseJSON(request.responseText).codigoHospital;
            muestraInformacionPerfilHospital(codigoHospitalTemp);
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


function muestraFormSesion(codigoHospital) {
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "ajaxRecuperarDatosConsultaSesionHospital",
        data: {codigoHospital: codigoHospital}
    })
            .done(function (msg) {

                $("#claveUsuario").val(msg.claveUsuario);
                $("#nombreUsuario").val(msg.nombreUsuario);
                $("#divFormInicioSesion").slideDown('slow');
            });

}

function muestraFormDatosHospital(codigoHospital) {
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

                $("#divFormDatosHospital").slideDown('slow');

            });
}

function muestraDireccionHospital(codigoHospital) {
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "ajaxRecuperarDatosConsultaDireccionHospital",
        data: {codigoHospital: codigoHospital}
    })
            .done(function (msg) {

                $("#calle").val(msg.calle);
                $("#numero").val(msg.numero);
                $("#colonia").val(msg.colonia);
                $("#delegacion").val(msg.delegacion);
                $("#entidadFederativa").val(msg.entidadFederativa);
                $("#codigoPostal").val(msg.codigoPostal);

                $("#divFormDireccionHospital").slideDown('slow', function () {
                    inicializarMapaConsultar(msg.latitudY, msg.longitudX);
                });

            });

}

function muestraFormDirectivo(codigoHospital) {
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

                $("#divFormDirectivo").slideDown('slow');

            });

}

function muestraFormEspecialidades(codigoHospital) {
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "ajaxRecuperarDatosEspecialidades",
        data: {codigoHospital: codigoHospital}
    })
            .done(function (msg) {

                $("#divContEspecialidades").html(msg.especialidades);

                $("#divFormEspecialidades").slideDown('slow');

            });

}

function muestraInformacionPerfilHospital(codigoHospital) {
    $("#barraCargar").slideDown(100);
    muestraFormSesion(codigoHospital);
    muestraFormDatosHospital(codigoHospital);
    muestraDireccionHospital(codigoHospital);
    muestraFormDirectivo(codigoHospital);
    muestraFormEspecialidades(codigoHospital);
    $("#barraCargar").slideUp(100);
}


