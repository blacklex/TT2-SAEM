/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusPaciente");
    
    $(document).ajaxSuccess(function (event, request, settings) {
        
        if (settings.url.match('recuperarMensajeEstatusPaciente') !== null) {
            
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
    $(".has-error").removeClass("has-error");

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

    if ($("#nss").val().length < 11) {
        $("#divNss").addClass("has-error");
        $("#divNss").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu NSS.</label>");
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
    
    if ($("#unidadMedica").val().length < 1) {
        $("#divUniMedicaPaciente").addClass("has-error");
        $("#divUniMedicaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la unidad medica.</label>");
        valido = false;
    }
    
    if ($("#noConsultorio").val().length < 1) {
        $("#divNoConsultorioPaciente").addClass("has-error");
        $("#divNoConsultorioPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el numero de consultorio.</label>");
        valido = false;
    }
    
    if ($("#calle").val().length < 2) {
        $("#divCallePaciente").addClass("has-error");
        $("#divCallePaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la calle y el numero.</label>");
        valido = false;
    }

    if ($("#colonia").val().length < 1) {
        $("#divColoniaPaciente").addClass("has-error");
        $("#divColoniaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la colonia.</label>");
        valido = false;
    }

    if ($("#delegacion").val().length < 1) {
        $("#divDelegacionPaciente").addClass("has-error");
        $("#divDelegacionPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Delegación.</label>");
        valido = false;
    }

    if ($("#entidadFederativa").val().length < 1) {
        $("#divEntidadFederativaPaciente").addClass("has-error");
        $("#divEntidadFederativaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Entidad Federativa.</label>");
        valido = false;
    }
    
    if ($("#codigoPostal").val().length < 5 || $("#codigoPostal").val().search('_') !== -1) {
        $("#divCodigoPostalPaciente").addClass("has-error");
        $("#divCodigoPostalPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un codigo postal valido.</label>");
        valido = false;
    }

    if ($("#telefonoFijo").val().length < 17 || $("#telefonoFijo").val().search('_') !== -1) {
        $("#divTelefonoFijoPaciente").addClass("has-error");
        $("#divTelefonoFijoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono fijo valido.</label>");
        valido = false;

    }
    
    if ($("#telefonoParticular").val().length < 17 || $("#telefonoParticular").val().search('_') !== -1) {
        $("#divTelefonoParticularPaciente").addClass("has-error");
        $("#divTelefonoParticularPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono particular valido.</label>");
        valido = false;

    }
    
    if ($("#estadoCivil").val().length < 3) {
        $("#divEdoCivilPaciente").addClass("has-error");
        $("#divEdoCivilPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el estado civil.</label>");
        valido = false;
    }

    if ($("#curp").val().length < 18) {
        $("#divCurpPaciente").addClass("has-error");
        $("#divCurpPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un CURP valido.</label>");
        valido = false;

    }
    
    if ($("#sexo").val().length < 1) {
        $("#divSexoPaciente").addClass("has-error");
        $("#divSexoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el sexo.</label>");
        valido = false;

    }
    
    if ($("#fechaNacimiento").val().length < 6) {
        $("#divFechaNacimientoPaciente").addClass("has-error");
        $("#divFechaNacimientoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la fecha de nacimiento.</label>");
        valido = false;

    }
    
    if ($("#edad").val().length < 1) {
        $("#divEdadPaciente").addClass("has-error");
        $("#divEdadPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una edad valida.</label>");
        valido = false;

    }
    
    if ($("#peso").val().length < 2) {
        $("#divPesoPaciente").addClass("has-error");
        $("#divPesoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un peso valido.</label>");
        valido = false;

    }
    
    if ($("#altura").val().length < 1) {
        $("#divAlturaPaciente").addClass("has-error");
        $("#divAlturaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una altura valida.</label>");
        valido = false;

    }
    
    if ($("#talla").val().length < 1) {
        $("#divTallaPaciente").addClass("has-error");
        $("#divTallaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una talla valida.</label>");
        valido = false;

    }
    
    if ($("#telCasa").val().length < 17 || $("#telCasa").val().search('_') !== -1) {
        $("#divTelefonoCasaPaciente").addClass("has-error");
        $("#divTelefonoCasaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono valido.</label>");
        valido = false;

    }
    
    if ($("#telCel").val().length < 17 || $("#telCel").val().search('_') !== -1) {
        $("#divTelefonoCelPaciente").addClass("has-error");
        $("#divTelefonoCelPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono valido.</label>");
        valido = false;

    }
    
    if ($("#correo").val().length < 3) {
        $("#divCorreoPaciente").addClass("has-error");
        $("#divCorreoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un correo valido.</label>");
        valido = false;

    }
    
    if ($("#facebook").val().length < 5) {
        $("#divFacebookPaciente").addClass("has-error");
        $("#divFacebookPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa Facebook.</label>");
        valido = false;

    }
    
    if ($("#nombreC").val().length < 1) {
        $("#divNombreCPaciente").addClass("has-error");
        $("#divNombreCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un nombre.</label>");
        valido = false;

    }
    
    if ($("#apellidoPaternoC").val().length < 1) {
        $("#divApellidoPaternoCPaciente").addClass("has-error");
        $("#divApellidoPaternoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un apellido paterno.</label>");
        valido = false;

    }
    
    if ($("#apellidoMaternoC").val().length < 1) {
        $("#divApellidoMaternoCPaciente").addClass("has-error");
        $("#divApellidoMaternoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un apellido materno.</label>");
        valido = false;

    }
    
    if ($("#parentesco").val().length < 1) {
        $("#divParentescoCPaciente").addClass("has-error");
        $("#divParentescoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el parentesco.</label>");
        valido = false;

    }
    
    if ($("#celular").val().length < 17 || $("#celular").val().search('_') !== -1) {
        $("#divCelularCPaciente").addClass("has-error");
        $("#divCelularCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un telefono valido.</label>");
        valido = false;

    }
    
    if ($("#facebookC").val().length < 4) {
        $("#divFacebookCPaciente").addClass("has-error");
        $("#divFacebookCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa Facebook.</label>");
        valido = false;

    }
    
    if ($("#correoC").val().length < 4) {
        $("#divCorreoCPaciente").addClass("has-error");
        $("#divCorreoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa correo.</label>");
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
        url: "validarNombreUsuarioPaciente",
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
                    document.forms["formNuevoPaciente"].submit();
                }
            });
    return false;
}


