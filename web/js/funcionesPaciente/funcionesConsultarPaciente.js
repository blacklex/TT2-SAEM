/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusConsultarPaciente");
    

    $(document).ajaxSuccess(function (event, request, settings) {

        
        if (settings.url.match('recuperarMensajeEstatusConsultarPaciente') != null) {

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
    $("#divFormDatosPaciente").slideUp('slow');
    $("#divFormDireccionPaciente").slideUp('slow');
    $("#divFormTelefonos").slideUp('slow');
    $("#divFormDatosPersonalesPaciente").slideUp('slow');
    $("#divFormContactosPaciente").slideUp('slow');
    $("#divFormDatosClinicosPaciente").slideUp('slow');
    $("#divjGrid").slideDown('slow');
}

function muestraFormSesion() {
    if ($("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow');

        var nssP = $("#gridListaConsultaPacientes").jqGrid('getCell', s, 'nssP');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaSesion",
            data: {nssP: nssP}
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
        alert("Seleccione un Paciente de la tabla.");
    }
}

function muestraFormDatosPaciente() {
    if ($("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow');

        var nssP = $("#gridListaConsultaPacientes").jqGrid('getCell', s, 'nssP');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaPaciente",
            data: {nssP: nssP}
        })
                .done(function (msg) {

                    var nss = msg.nss;
                    var nombrePaciente = msg.nombrePaciente;
                    var apellidoPaternoPaciente = msg.apellidoPaternoPaciente;
                    var apellidoMaternoPaciente = msg.apellidoMaternoPaciente;
                    var unidadMedicaPaciente = msg.unidadMedicaPaciente;
                    var noConsultorio = msg.noConsultorio;

                    $("#nss").val(nss);
                    $("#nombrePaciente").val(nombrePaciente);
                    $("#apellidoPaternoPaciente").val(apellidoPaternoPaciente);
                    $("#apellidoMaternoPaciente").val(apellidoMaternoPaciente);
                    $("#unidadMedicaPaciente").val(unidadMedicaPaciente);
                    $("#noConsultorio").val(noConsultorio);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormDatosPaciente").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Paciente de la tabla.");
    }

}

function muestraDireccionPaciente() {
    if ($("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow');

        var nssP = $("#gridListaConsultaPacientes").jqGrid('getCell', s, 'nssP');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaDireccion",
            data: {nssP: nssP}
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
                        $("#divFormDireccionPaciente").slideDown('slow',function (){inicializarMapaConsultar(msg.latitudY,msg.longitudX);});
                    });
                });
    } else {
        alert("Seleccione un Paciente de la tabla.");
    }

}

function muestraFormTelefonosPaciente() {
    if ($("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow');

        var nssP = $("#gridListaConsultaPacientes").jqGrid('getCell', s, 'nssP');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaTelPaciente",
            data: {nssP: nssP}
        })
                .done(function (msg) {

                    $("#telefonoFijo").val(msg.telefonoFijo);
                    $("#telefonoParticular").val(msg.telefonoParticular);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormTelefonos").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Paciente de la tabla.");
    }


}

function muestraFormDatosPersonalesPaciente() {
    if ($("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow');

        var nssP = $("#gridListaConsultaPacientes").jqGrid('getCell', s, 'nssP');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosPersonalesConsulta",
            data: {nssP: nssP}
        })
                .done(function (msg) {

                    $("#estadoCivil").val(msg.estadoCivil);
                    $("#curp").val(msg.curp);
                    $("#sexo").val(msg.sexo);
                    $("#fechaNacimiento").val(msg.fechaNacimiento);
                    $("#edad").val(msg.edad);
                    $("#peso").val(msg.peso);
                    $("#altura").val(msg.altura);
                    $("#talla").val(msg.talla);
                    $("#telCasa").val(msg.telCasa);
                    $("#telCel").val(msg.telCel);
                    $("#correo").val(msg.correo);
                    $("#facebook").val(msg.facebook);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormDatosPersonalesPaciente").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Paciente de la tabla.");
    }

}

function muestraFormContactosPaciente() {
    if ($("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow');

        var nssP = $("#gridListaConsultaPacientes").jqGrid('getCell', s, 'nssP');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosConsultaContactos",
            data: {nssP: nssP}
        })
                .done(function (msg) {

                    $("#nombreC").val(msg.nombreC);
                    $("#apellidoPaternoC").val(msg.apellidoPaternoC);
                    $("#apellidoMaternoC").val(msg.apellidoMaternoC);
                    $("#parentesco").val(msg.parentesco);
                    $("#celular").val(msg.celular);
                    $("#facebookC").val(msg.facebookC);
                    $("#correoC").val(msg.correoC);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormContactosPaciente").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Paciente de la tabla.");
    }

}

function muestraFormDatosClinicosPaciente() {
    if ($("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow') != null) {
        var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam', 'selrow');

        var nssP = $("#gridListaConsultaPacientes").jqGrid('getCell', s, 'nssP');

        $("#barraCargar").slideDown(100);

        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarDatosClinicosConsulta",
            data: {nssP: nssP}
        })
                .done(function (msg) {

                    $("#usoDrogas").val(msg.usoDrogas);
                    $("#usoAlcohol").val(msg.usoAlcohol);
                    $("#fumador").val(msg.fumador);

                    $("#barraCargar").slideUp(100);

                    $("#divjGrid").slideUp('slow', function () {
                        $("#divFormDatosClinicosPaciente").slideDown('slow');
                    });
                });
    } else {
        alert("Seleccione un Paciente de la tabla.");
    }

}


