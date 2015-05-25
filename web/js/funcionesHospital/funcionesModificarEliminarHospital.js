/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    
   $.getJSON("recuperarMensajeEstatusMEHospital");

    $(document).ajaxSuccess(function (event, request, settings) {

        if (settings.url.match('recuperarMensajeEstatusMEHospital') != null || settings.url.match('ajaxEliminarHospital') != null) {
            
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

function validarCamposFormSesion() {
    var valido = true;
    $(".control-label").remove();
    $("#divClaveUsuario").removeClass("has-error");

    if ($("#claveUsuario").val().length < 6) {
        $("#divClaveUsuario").addClass("has-error");
        $("#divClaveUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una clave con mas de 6 caracteres.</label>");
        valido = false;
    }

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlert").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }

    return true;

}

function validarCamposFormDatosHospital() {
    var valido = true;
    $(".control-label").remove();
    $("#divNombreHospital").removeClass("has-error");
    $("#divTelefonoHospital").removeClass("has-error");
    $("#divEmailHospital").removeClass("has-error");


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

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlert").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }

    return true;

}

function validarCamposFormDireccionHospital() {
    var valido = true;
    $(".control-label").remove();

    $("#divCalleHospital").removeClass("has-error");
    $("#divNumeroHospital").removeClass("has-error");
    $("#divColoniaHospital").removeClass("has-error");
    $("#divDelegacionHospital").removeClass("has-error");
    $("#divEntidadFederativaHospital").removeClass("has-error");
    $("#divCodigoPostalHospital").removeClass("has-error");

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

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlert").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }

    return true;

}

function validarFormDirectivo() {
    var valido = true;
    $(".control-label").remove();

    $("#divTelefonoDirectivo").removeClass("has-error");
    $("#divEmailDirectivo").removeClass("has-error");
    $("#divNombreDirectivo").removeClass("has-error");

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

    return true;

}


function ocultarForms() {
    $("#divFormInicioSesion").slideUp('slow');
    $("#divFormDatosHospital").slideUp('slow');
    $("#divFormDireccionHospital").slideUp('slow');
    $("#divFormDirectivo").slideUp('slow');
    $("#divFormEspecialidades").slideUp('slow');
    $("#divjGrid").slideDown('slow');
}

function muestraFormSesion() {
    if ($("#gridListaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaHospitales").jqGrid('getCell', s, 'codigoHospital');
        $("#codigoHSesion").val(codigoHospital);
        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosFormSesion",
            data: {codigoHospital: codigoHospital}
        })
                .done(function (msg) {


                    var claveUsuario = msg.claveUsuario;
                    $("#claveUsuario").val(claveUsuario);

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
    if ($("#gridListaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaHospitales").jqGrid('getCell', s, 'codigoHospital');
        $("#codigoHDatos").val(codigoHospital);
        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosFormHospital",
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
    if ($("#gridListaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaHospitales").jqGrid('getCell', s, 'codigoHospital');
        $("#codigoHDireccion").val(codigoHospital);
        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosFormDireccion",
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
                        $("#divFormDireccionHospital").slideDown('slow',function (){inicializarMapaEditar(msg.latitudY,msg.longitudX);});
                    });
                    
                });
    } else {
        alert("Seleccione un Hospital de la tabla.");
    }

}

function muestraFormDirectivo() {
    if ($("#gridListaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaHospitales").jqGrid('getCell', s, 'codigoHospital');
        $("#codigoHDirectivo").val(codigoHospital);
        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosFormDirectivo",
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
    if ($("#gridListaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaHospitales").jqGrid('getCell', s, 'codigoHospital');
        $("#codigoHEspecialidades").val(codigoHospital);
        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarFormEspecialidades",
            data: {codigoHospital: codigoHospital}
        })
                .done(function (msg) {

                    $("#divContEspecialidades").html(msg.especialidades);


                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormEspecialidades").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Hospital de la tabla.");
    }

}

function eliminarHospital() {
    if ($("#gridListaHospitales").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaHospitales").jqGrid('getGridParam', 'selrow');

        var codigoHospital = $("#gridListaHospitales").jqGrid('getCell', s, 'codigoHospital');



        modal({
            type: 'confirm',
            title: 'Borrar Hospital',
            text: '¿Desea borrar al Hospital ' + codigoHospital + ' y todos sus datos?',
            callback: function (result) {
                if (result) {
                    $("#barraCargar").slideDown(100);

                    $.ajax({
                        dataType: "json",
                        method: "POST",
                        url: "ajaxEliminarHospital",
                        data: {codigoHospital: codigoHospital}
                    })
                            .done(function (msg) {
                                $('#gridListaHospitales').trigger("reloadGrid", [{page: 1}]);
                                $("#barraCargar").slideUp(100);
                            });
                }

            }
        });






    } else {
        alert("Seleccione un Hospital de la tabla.");
    }


}







