/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusAdmin");
    
    $(document).ajaxSuccess(function (event, request, settings) {
        
        if (settings.url.match('recuperarMensajeEstatusAdmin') !== null) {
            
            var tituloAlert = $.parseJSON(request.responseText).tituloAlert;
            var textoAlert = $.parseJSON(request.responseText).textoAlert;
            var estatusMensaje = $.parseJSON(request.responseText).estatusMensaje;

            if (estatusMensaje === null)
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

/*******************************************************************************/

function validarCampos() {
    var valido = true;
    $(".control-label").remove();
    $("#divNombreUsuario").removeClass("has-error");
    $("#divClaveUsuario").removeClass("has-error");
    $("#divNombre").removeClass("has-error");
    $("#divApellidoPaterno").removeClass("has-error");
    $("#divApellidoMaterno").removeClass("has-error");
    $("#divTelefonoAdministrador").removeClass("has-error");
    $("#divEmailAdministrador").removeClass("has-error");
    $("#divCalleAdministrador").removeClass("has-error");
    $("#divNumeroAdministrador").removeClass("has-error");
    $("#divColoniaAdministrador").removeClass("has-error");
    $("#divDelegacionAdministrador").removeClass("has-error");
    $("#divEntidadFederativaAdministrador").removeClass("has-error");
    $("#divCodigoPostalAdministrador").removeClass("has-error");

    if ($("#nombreUsuario").val().length < 1) {
        $("#divNombreUsuario").addClass("has-error");
        $("#divNombreUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu Nombre de Usuario.</label>");
        valido = false;
    }

    if ($("#clave").val().length < 6) {
        $("#divClaveUsuario").addClass("has-error");
        $("#divClaveUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una clave con mas de 6 caracteres.</label>");
        valido = false;
    }

    if ($("#nombre").val().length < 1) {
        $("#divNombre").addClass("has-error");
        $("#divNombre").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu nombre.</label>");
        valido = false;
    }
        
    if ($("#apellidoPaterno").val().length < 1) {
        $("#divApellidoPaterno").addClass("has-error");
        $("#divApellidoPaterno").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu apellido paterno.</label>");
        valido = false;
    }
    
    if ($("#apellidoMaterno").val().length < 1) {
        $("#divApellidoMaterno").addClass("has-error");
        $("#divApellidoMaterno").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu apellido materno.</label>");
        valido = false;
    }

    if ($("#telParticular").val().length < 17 || $("#telParticular").val().search('_') !== -1) {
        $("#divTelParticularAdministrador").addClass("has-error");
        $("#divTelParticularAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono valido.</label>");
        valido = false;

    }

    if ($("#correo").val().length < 3) {
        $("#divEmailAdministrador").addClass("has-error");
        $("#divEmailAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un email valido.</label>");
        valido = false;

    }

    if ($("#calle").val().length < 2) {
        $("#divCalleAdministrador").addClass("has-error");
        $("#divCalleAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la calle.</label>");
        valido = false;
    }

    if ($("#colonia").val().length < 1) {
        $("#divColoniaAdministrador").addClass("has-error");
        $("#divColoniaAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la colonia.</label>");
        valido = false;
    }

    if ($("#delegacion").val().length < 1) {
        $("#divDelegacionAdministrador").addClass("has-error");
        $("#divDelegacionAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Delegación.</label>");
        valido = false;
    }

    if ($("#entidadFederativa").val().length < 1) {
        $("#divEntidadFederativaAdministrador").addClass("has-error");
        $("#divEntidadFederativaAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Entidad Federativa.</label>");
        valido = false;
    }
    
    if ($("#codigoPostal").val().length < 5 || $("#codigoPostal").val().search('_') !== -1) {
        $("#divCodigoPostalAdministrador").addClass("has-error");
        $("#divCodigoPostalAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un codigo postal valido.</label>");
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
        url: "validarNombreUsuarioAdmin",
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
                    document.forms["formNuevoAdministrador"].submit();
                }
            });
    return false;
}