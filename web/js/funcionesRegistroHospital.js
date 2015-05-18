/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarEspecialidades");
    $.getJSON("recuperarMensajeEstatus");
    
    
    $(document).ajaxSuccess(function (event, request, settings) {
        
        

        if (settings.url.match('recuperarMensajeEstatus') != null) {

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
        
        if (settings.url.match('recuperarEspecialidades') != null) {
            var html = $.parseJSON(request.responseText).htmlEspecialidades;
            $("#divEspecialidades").html(html);
        }
    });
initialize();

});

/*******************************************************************************/

$(function () {

    $("[data-mask]").inputmask();


});

function validarCampos() {
    var valido = true;
    $(".control-label").remove();
    $("#divNombreUsuario").removeClass("has-error");
    $("#divClaveUsuario").removeClass("has-error");
    $("#divNombreHospital").removeClass("has-error");
    $("#divTelefonoHospital").removeClass("has-error");
    $("#divEmailHospital").removeClass("has-error");
    $("#divCalleHospital").removeClass("has-error");
    $("#divNumeroHospital").removeClass("has-error");
    $("#divColoniaHospital").removeClass("has-error");
    $("#divDelegacionHospital").removeClass("has-error");
    $("#divEntidadFederativaHospital").removeClass("has-error");
    $("#divCodigoPostalHospital").removeClass("has-error");
    $("#divTelefonoDirectivo").removeClass("has-error");
    $("#divEmailDirectivo").removeClass("has-error");
    $("#divNombreDirectivo").removeClass("has-error");

    if ($("#nombreUsuario").val().length < 1) {
        $("#divNombreUsuario").addClass("has-error");
        $("#divNombreUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu Nombre de Usuario.</label>");
        valido = false;
    }

    if ($("#claveUsuario").val().length < 6) {
        $("#divClaveUsuario").addClass("has-error");
        $("#divClaveUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una clave con mas de 6 caracteres.</label>");
        valido = false;
    }

    if ($("#nombreHospital").val().length < 1) {
        $("#divNombreHospital").addClass("has-error");
        $("#divNombreHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un nombre valido.</label>");
        valido = false;
    }

    if ($("#telefonoHospital").val().length < 17 || $("#telefonoHospital").val().search('_') != -1) {
        $("#divTelefonoHospital").addClass("has-error");
        $("#divTelefonoHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono valido.</label>");
        valido = false;

    }

    if ($("#emailHospital").val().length < 3) {
        $("#divEmailHospital").addClass("has-error");
        $("#divEmailHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un email valido.</label>");
        valido = false;

    }

    if ($("#calle").val().length < 2) {
        $("#divCalleHospital").addClass("has-error");
        $("#divCalleHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la calle.</label>");
        valido = false;
    }

    if ($("#numero").val().length < 1) {
        $("#divNumeroHospital").addClass("has-error");
        $("#divNumeroHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el numero.</label>");
        valido = false;
    }

    if ($("#colonia").val().length < 1) {
        $("#divColoniaHospital").addClass("has-error");
        $("#divColoniaHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la colonia.</label>");
        valido = false;
    }

    if ($("#delegacion").val().length < 1) {
        $("#divDelegacionHospital").addClass("has-error");
        $("#divDelegacionHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Delegación.</label>");
        valido = false;
    }

    if ($("#entidadFederativa").val().length < 1) {
        $("#divEntidadFederativaHospital").addClass("has-error");
        $("#divEntidadFederativaHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Entidad Federativa.</label>");
        valido = false;
    }

    if ($("#codigoPostal").val().length < 5 || $("#codigoPostal").val().search('_') != -1) {
        $("#divCodigoPostalHospital").addClass("has-error");
        $("#divCodigoPostalHospital").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un codigo postal valido.</label>");
        valido = false;

    }

    if ($("#telefonoDirectivo").val().length < 17 || $("#telefonoDirectivo").val().search('_') != -1) {
        $("#divTelefonoDirectivo").addClass("has-error");
        $("#divTelefonoDirectivo").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono valido.</label>");
        valido = false;

    }

    if ($("#emailDirectivo").val().length < 3) {
        $("#divEmailDirectivo").addClass("has-error");
        $("#divEmailDirectivo").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un email valido.</label>");
        valido = false;

    }

    if ($("#nombreDirectivo").val().length < 1) {
        $("#divNombreDirectivo").addClass("has-error");
        $("#divNombreDirectivo").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un nombre valido.</label>");
        valido = false;
    }

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlert").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }

    $("#barraCargar").slideDown(100);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "validarNombreUsuario",
        data: {nombreUsuario: $("#nombreUsuario").val()}
    })
            .done(function (msg) {
                $("#barraCargar").slideUp(100);
                if (msg.estatusMensaje === "nombreNoValido") {
                    $('html, body').animate({scrollTop: 0}, 'fast');
                    $("#barraCargar").slideUp(100);
                    $("#tituloDivAlertError").html("<i class='icon fa fa-ban'></i>El Usuario ya existe");
                    $("#labelMensajeError").html("El nombre de usuario ya existe, escribe uno nuevo.");

                    $("#divNombreUsuario").addClass("has-error");
                    $("#divNombreUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  El nombre de usuario ya existe, escribe uno nuevo.</label>");

                    $("#divAlertError").slideDown('slow').delay(2500).slideUp('slow');
                    valido = false;
                    $("#barraCargar").slideUp(100);
                    return valido;
                } else if (msg.estatusMensaje === "nombreValido") {
                    valido = true;
                    $("#barraCargar").slideDown(100);
                    document.forms["formNuevoHospital"].submit();

                }
            });

    return false;

}





