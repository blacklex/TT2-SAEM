/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON("recuperarMensajeEstatusEditar");

    $(document).ajaxSuccess(function (event, request, settings) {

        if (settings.url.match('recuperarMensajeEstatusEditar') !== null) {

            var tituloAlertEditar = $.parseJSON(request.responseText).tituloAlertEditar;
            var textoAlertEditar = $.parseJSON(request.responseText).textoAlertEditar;
            var estatusMensajeEditar = $.parseJSON(request.responseText).estatusMensajeEditar;
            //var nombreUsuario = $.parseJSON(request.responseText).nombreUsuario;

            if (estatusMensajeEditar === null)
                return;

            if (estatusMensajeEditar === "success") {
                $("#tituloDivAlertSuccessEliminar").html("<i class='icon fa fa-check'></i>" + tituloAlertEditar);
                $("#labelMensajeSuccessEliminar").html(textoAlertEditar);
                $("#divAlertSuccessEliminar").slideDown('slow').delay(2500).slideUp('slow');
                $("#gridPaciente").trigger("reloadGrid");
                //alert(nombreUsuario);
            } else if (estatusMensajeEditar === "error") {
                $("#tituloDivAlertErrorEliminar").html("<i class='icon fa fa-ban'></i>" + tituloAlertEditar);
                $("#labelMensajeErrorEliminar").html(textoAlertEditar);
                $("#divAlertErrorEliminar").slideDown('slow').delay(2500).slideUp('slow');
            }
        }
    });
});

/*******************************************************************************/

//$(function () {
//    $("[data-mask]").inputmask();
//});

/*******************************************************************************/

function editarAccesoPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
       
    var clave = $("#gridPaciente").jqGrid('getCell',s,'clave');
    var fechaRegistro = $("#gridPaciente").jqGrid('getCell',s,'fechaRegistro');

    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $("#nombreUsuario").val(nombreUsuario);
        $("#clave").val(clave);
        $("#fechaRegistro").val(fechaRegistro);
        $("#datosAccesoPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }    
}

function editarDatosPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var urlImg = 'imagenesPerfilPaciente/'+msg.nombreUsuario+'.jpeg';
            $("#nss").val(msg.nss);
            $("#nombre").val(msg.nombre);
            $("#apellidoPaterno").val(msg.apellidoPaterno);
            $("#apellidoMaterno").val(msg.apellidoMaterno);
            $("#unidadMedica").val(msg.unidadMedica);
            $("#noConsultorio").val(msg.noConsultorio);
            $("#nomUs").val(msg.nombreUsuario);
            $("#imagen").attr('src', urlImg);
        });
        $("#datosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarDireccionPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosDireccionPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#calle").val(msg.calle);
            $("#colonia").val(msg.colonia);
            $("#delegacion").val(msg.delegacion);
            $("#entidadFederativa").val(msg.entidadFederativa);
            $("#codigoPostal").val(msg.codigoPostal);
            $("#idDomPaciente").val(msg.id);
            $("#nomUsr").val(msg.nombreUsuario);
        });

        $("#datosDireccionPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarTelefonosPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarTelefonosPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#telefonoFijo").val(msg.telefonoFijo);
            $("#telefonoParticular").val(msg.telefonoParticular);
            $("#nomUs3").val(msg.nombreUsuario);
        });

        $("#datosTelefonosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarDatosPersonalesPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosPersonalesPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
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
            $("#nomUs4").val(msg.nombreUsuario);
        });

        $("#datosPersonalesPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarContactosPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarContactosPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#nombreC").val(msg.nombreC);
            $("#apellidoPaternoC").val(msg.apellidoPaternoC);
            $("#apellidoPaternoC").val(msg.apellidoPaternoC);
            $("#apellidoPaternoC").val(msg.apellidoPaternoC);
            $("#apellidoPaternoC").val(msg.apellidoPaternoC);
            $("#apellidoPaternoC").val(msg.apellidoPaternoC);
            $("#apellidoPaternoC").val(msg.apellidoPaternoC);
            $("#apellidoPaternoC").val(msg.apellidoPaternoC);
            $("#nomUs5").val(msg.nombreUsuario);
        });

        $("#datosContactosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarDatosClinicosPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosClinicosPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#usoDrogas").val(msg.usoDrogas);
            $("#usoAlcohol").val(msg.usoAlcohol);
            $("#fumador").val(msg.fumador);
            $("#nomUs6").val(msg.nombreUsuario);
        });

        $("#datosTelefonosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function buscarUsuarioPorFiltroUsuario() {
    var nombreUsuario = $("#nombreUsuarioPorFiltro").val();

    if(nombreUsuario.length === 0)
        alert("Ingrese un usuario");
    else {
        $("#barraCargarEliminar").slideUp(100);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosPorFiltro",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#barraCargarEliminar").slideUp(100);
            if(msg.estatusMensajeEliminar === "usuarioEncontrado"){
                $("#barraCargarEliminar").slideDown('slow').delay(2500).slideUp('slow');
                var urlImg = 'imagenesPerfilPaciente/'+msg.nombreUsuario+'.jpeg';
                if($("#radioEliminar").is(':checked')) {
                    $("#nombreUsuarioFiltro").val(msg.nombreUsuario);
                    $("#nombreUsuarioFiltro").attr('readonly', true);
                    $("#claveFiltro").val(msg.clave);
                    $("#claveFiltro").attr('readonly', true);
                    $("#nssFiltro").val(msg.nss);
                    $("#nssFiltro").attr('readonly', true);
                    $("#nombreFiltro").val(msg.nombre);
                    $("#nombreFiltro").attr('readonly', true);
                    $("#apellidoPaternoFiltro").val(msg.apellidoPaterno);
                    $("#apellidoPaternoFiltro").attr('readonly', true);
                    $("#apellidoMaternoFiltro").val(msg.apellidoMaterno);
                    $("#apellidoMaternoFiltro").attr('readonly', true);
                    $("#unidadMedicaFiltro").val(msg.unidadMedica);
                    $("#unidadMedicaFiltro").attr('readonly', true);
                    $("#noConsultorioFiltro").val(msg.noConsultorio);
                    $("#noConsultorioFiltro").attr('readonly', true);
                    $("#calleFiltro").val(msg.calle);
                    $("#calleFiltro").attr('readonly', true);
                    $("#coloniaFiltro").val(msg.colonia);
                    $("#coloniaFiltro").attr('readonly', true);
                    $("#delegacionFiltro").val(msg.delegacion);
                    $("#delegacionFiltro").attr('readonly', true);
                    $("#entidadFederativaFiltro").val(msg.entidadFederativa);
                    $("#entidadFederativaFiltro").attr('readonly', true);
                    $("#codigoPostalFiltro").val(msg.codigoPostal);
                    $("#codigoPostalFiltro").attr('readonly', true);
                    $("#idDomPacienteFiltro").val(msg.id);
                    $("#idDomPacienteFiltro").attr('readonly', true);
                    $("#imagenFiltro").attr('src', urlImg);
                    $("#telefonoFijoFiltro").val(msg.telefonoFijo);
                    $("#telefonoFijoFiltro").attr('readonly', true);
                    $("#telefonoParticularFiltro").val(msg.telefonoParticular);
                    $("#telefonoParticularFiltro").attr('readonly', true);
                    $("#estadoCivilFiltro").val(msg.estadoCivil);
                    $("#estadoCivilFiltro").attr('readonly', true);
                    $("#curpFiltro").val(msg.curp);
                    $("#curpFiltro").attr('readonly', true);
                    $("#sexoFiltro").val(msg.sexo);
                    $("#sexoFiltro").attr('readonly', true);
                    $("#fechaNacimientoFiltro").val(msg.colonia);
                    $("#fechaNacimientoFiltro").attr('readonly', true);
                    $("#edadFiltro").val(msg.edad);
                    $("#edadFiltro").attr('readonly', true);
                    $("#pesoFiltro").val(msg.peso);
                    $("#pesoFiltro").attr('readonly', true);
                    $("#alturaFiltro").val(msg.altura);
                    $("#alturaFiltro").attr('readonly', true);
                    $("#tallaFiltro").val(msg.talla);
                    $("#tallaFiltro").attr('readonly', true);
                    $("#telCasaFiltro").val(msg.telCasa);
                    $("#telCasaFiltro").attr('readonly', true);
                    $("#telCelFiltro").val(msg.telCel);
                    $("#telCelFiltro").attr('readonly', true);
                    $("#correoFiltro").val(msg.correo);
                    $("#correoFiltro").attr('readonly', true);
                    $("#facebookFiltro").val(msg.facebook);
                    $("#facebookFiltro").attr('readonly', true);
                    $("#nombreCFiltro").val(msg.nombreC);
                    $("#nombreCFiltro").attr('readonly', true);
                    $("#apellidoPaternoCFiltro").val(msg.apellidoPaternoC);
                    $("#apellidoPaternoCFiltro").attr('readonly', true);
                    $("#apellidoMaternoCFiltro").val(msg.apellidoMaternoC);
                    $("#apellidoMaternoCFiltro").attr('readonly', true);
                    $("#parentescoFiltro").val(msg.parentesco);
                    $("#parentescoFiltro").attr('readonly', true);
                    $("#celularFiltro").val(msg.celular);
                    $("#celularFiltro").attr('readonly', true);
                    $("#facebookCFiltro").val(msg.facebookC);
                    $("#facebookCFiltro").attr('readonly', true);
                    $("#correoCFiltro").val(msg.correoC);
                    $("#correoCFiltro").attr('readonly', true);
                    $("#usoDrogasFiltro").val(msg.usoDrogas);
                    $("#usoDrogasFiltro").attr('readonly', true);
                    $("#usoAlcoholFiltro").val(msg.usoAlcohol);
                    $("#usoAlcoholFiltro").attr('readonly', true);
                    $("#fumadorFiltro").val(msg.fumador);
                    $("#fumadorFiltro").attr('readonly', true);
                    
                    $("#eliminar").show(100);
                }
                if($("#radioEditar").is(':checked')) {
                    $("#nombreUsuarioFiltro").val(msg.nombreUsuario);
                    $("#nombreUsuarioFiltro").attr('readonly', true);
                    $("#claveFiltro").val(msg.clave);
                    $("#claveFiltro").attr('readonly', false);
                    $("#nssFiltro").val(msg.nss);
                    $("#nssFiltro").attr('readonly', false);
                    $("#nombreFiltro").val(msg.nombre);
                    $("#nombreFiltro").attr('readonly', false);
                    $("#apellidoPaternoFiltro").val(msg.apellidoPaterno);
                    $("#apellidoPaternoFiltro").attr('readonly', false);
                    $("#apellidoMaternoFiltro").val(msg.apellidoMaterno);
                    $("#apellidoMaternoFiltro").attr('readonly', false);
                    $("#unidadMedicaFiltro").val(msg.unidadMedica);
                    $("#unidadMedicaFiltro").attr('readonly', false);
                    $("#noConsultorioFiltro").val(msg.noConsultorio);
                    $("#noConsultorioFiltro").attr('readonly', false);
                    $("#idDomPacienteFiltro").val(msg.id);
                    $("#idDomPacienteFiltro").attr('readonly', false);
                    $("#imagenFiltro").attr('src', urlImg);
                    $("#imagenUpload").attr('readonly', false);
                    $("#calleFiltro").val(msg.calle);
                    $("#calleFiltro").attr('readonly', false);
                    $("#coloniaFiltro").val(msg.colonia);
                    $("#coloniaFiltro").attr('readonly', false);
                    $("#delegacionFiltro").val(msg.delegacion);
                    $("#delegacionFiltro").attr('readonly', false);
                    $("#entidadFederativaFiltro").val(msg.entidadFederativa);
                    $("#entidadFederativaFiltro").attr('readonly', false);
                    $("#codigoPostalFiltro").val(msg.codigoPostal);
                    $("#codigoPostalFiltro").attr('readonly', false);
                    $("#idDomPacienteFiltro").val(msg.id);
                    $("#idDomPacienteFiltro").attr('readonly', false);
                    $("#imagenFiltro").attr('src', urlImg);
                    $("#telefonoFijoFiltro").val(msg.telefonoFijo);
                    $("#telefonoFijoFiltro").attr('readonly', false);
                    $("#telefonoParticularFiltro").val(msg.telefonoParticular);
                    $("#telefonoParticularFiltro").attr('readonly', false);
                    $("#estadoCivilFiltro").val(msg.estadoCivil);
                    $("#estadoCivilFiltro").attr('readonly', false);
                    $("#curpFiltro").val(msg.curp);
                    $("#curpFiltro").attr('readonly', false);
                    $("#sexoFiltro").val(msg.sexo);
                    $("#sexoFiltro").attr('readonly', false);
                    $("#fechaNacimientoFiltro").val(msg.colonia);
                    $("#fechaNacimientoFiltro").attr('readonly', false);
                    $("#edadFiltro").val(msg.edad);
                    $("#edadFiltro").attr('readonly', false);
                    $("#pesoFiltro").val(msg.peso);
                    $("#pesoFiltro").attr('readonly', false);
                    $("#alturaFiltro").val(msg.altura);
                    $("#alturaFiltro").attr('readonly', false);
                    $("#tallaFiltro").val(msg.talla);
                    $("#tallaFiltro").attr('readonly', false);
                    $("#telCasaFiltro").val(msg.telCasa);
                    $("#telCasaFiltro").attr('readonly', false);
                    $("#telCelFiltro").val(msg.telCel);
                    $("#telCelFiltro").attr('readonly', false);
                    $("#correoFiltro").val(msg.correo);
                    $("#correoFiltro").attr('readonly', false);
                    $("#facebookFiltro").val(msg.facebook);
                    $("#facebookFiltro").attr('readonly', false);
                    $("#nombreCFiltro").val(msg.nombreC);
                    $("#nombreCFiltro").attr('readonly', false);
                    $("#apellidoPaternoCFiltro").val(msg.apellidoPaternoC);
                    $("#apellidoPaternoCFiltro").attr('readonly', false);
                    $("#apellidoMaternoCFiltro").val(msg.apellidoMaternoC);
                    $("#apellidoMaternoCFiltro").attr('readonly', false);
                    $("#parentescoFiltro").val(msg.parentesco);
                    $("#parentescoFiltro").attr('readonly', false);
                    $("#celularFiltro").val(msg.celular);
                    $("#celularFiltro").attr('readonly', false);
                    $("#facebookCFiltro").val(msg.facebookC);
                    $("#facebookCFiltro").attr('readonly', false);
                    $("#correoCFiltro").val(msg.correoC);
                    $("#correoCFiltro").attr('readonly', false);
                    $("#usoDrogasFiltro").val(msg.usoDrogas);
                    $("#usoDrogasFiltro").attr('readonly', false);
                    $("#usoAlcoholFiltro").val(msg.usoAlcohol);
                    $("#usoAlcoholFiltro").attr('readonly', false);
                    $("#fumadorFiltro").val(msg.fumador);
                    $("#fumadorFiltro").attr('readonly', false);
                    
                    $("#actualizar").show(100);
                }
                $("#datosPacientePorFiltro").show(1000);
                $("#gridPacientes").slideUp(1000);
            } else if (msg.estatusMensajeEliminar === "usuarioNoEncontrado") {
                $('html, body').animate({scrollTop: 0}, 'fast');
                $("#barraCargarEliminar").slideUp(100);
                $("#tituloDivAlertErrorEliminar").html("<i class='icon fa fa-ban'></i>El Usuario no existe");
                $("#labelMensajeErrorEliminar").html("El nombre de usuario no existe.");

                $("#divAlertErrorEliminar").slideDown('slow').delay(2500).slideUp('slow');
                $("#barraCargarEliminar").slideUp(100);

            }
                
            });
        }
    }

/*******************************************************************************/

/*Cancelar ediciones*/

function cancelarEdicionAcceso() {
    $(".control-label").remove();
    $("#divClaveUsuario").removeClass("has-error");
    $("#nombreUsuario").val("");
    $("#clave").val("");
    $("#fechaRegistro").val("");
    
    $("#datosAccesoPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionDatos() {
    $("#nombre").val("");
    $("#apellidoPaterno").val("");
    $("#apellidoMaterno").val("");
    $("#unidadMedica").val("");
    $("#noConsultorio").val("");
    
    $("#datosPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);   
}

function cancelarEdicionDireccion(){
    $("#calle").val("");
    $("#colonia").val("");
    $("#delegacion").val("");
    $("#entidadFederativa").val("");
    $("#codigoPostal").val("");
        
    $("#datosDireccionPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionTelefonos(){
    $("#telefonoFijo").val("");
    $("#telefonoParticular").val("");
        
    $("#datosTelefonosPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionDatosPersonales(){
    $("#estadoCivil").val("");
    $("#curp").val("");
    $("#sexo").val("");
    $("#fechaNacimiento").val("");
    $("#edad").val("");
    $("#peso").val("");
    $("#altura").val("");
    $("#talla").val("");
    $("#telCasa").val("");
    $("#telCel").val("");
    $("#correo").val("");
    $("#facebook").val("");
        
    $("#datosPersonalesPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionContactos(){
    $("#nombreC").val("");
    $("#apellidoPaternoC").val("");
    $("#apellidoMaternoC").val("");
    $("#parentesco").val("");
    $("#celular").val("");
    $("#facebookC").val("");
    $("#correoC").val("");
        
    $("#datosContactosPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionDatosClinicos(){
    $("#usoDrogas").val("");
    $("#usoAlcohol").val("");
    $("#fumador").val("");
        
    $("#datosClinicosPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionPorFiltro(){
    $("#nombreUsuarioFiltro").val("");
    $("#claveFiltro").val("");
    $("#nombreFiltro").val("");
    $("#apellidoPaternoFiltro").val("");
    $("#apellidoMaternoFiltro").val("");
    $("#unidadMedicaFiltro").val("");
    $("#noConsultorioFiltro").val("");
    $("#calleFiltro").val("");
    $("#coloniaFiltro").val("");
    $("#delegacionFiltro").val("");
    $("#entidadFederativaFiltro").val("");
    $("#codigoPostalFiltro").val("");
    $("#telefonoFijoFiltro").val("");
    $("#telefonoParticularFiltro").val("");
    $("#estadoCivilFiltro").val("");
    $("#curpFiltro").val("");
    $("#sexoFiltro").val("");
    $("#fechaNacimientoFiltro").val("");
    $("#edadFiltro").val("");
    $("#pesoFiltro").val("");
    $("#alturaFiltro").val("");
    $("#tallaFiltro").val("");
    $("#telCasaFiltro").val("");
    $("#telCelFiltro").val("");
    $("#correoFiltro").val("");
    $("#facebookFiltro").val("");
    $("#nombreCFiltro").val("");
    $("#apellidoPaternoCFiltro").val("");
    $("#apellidoMaternoCFiltro").val("");
    $("#parentescoFiltro").val("");
    $("#celularFiltro").val("");
    $("#facebookCFiltro").val("");
    $("#correoCFiltro").val("");
    $("#usoDrogasFiltro").val("");
    $("#usoAlcoholFiltro").val("");
    $("#fumadorFiltro").val("");
    
    $("#idDomPacienteFiltro").val("");
    $("#nombreUsuarioPorFiltro").val("");

    $("#datosPacientePorFiltro").slideUp(1000);
    $("#gridPacientes").show(1000);
}

/*Fin de cancelar ediciones*/

/*******************************************************************************/

function eliminarPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    $("#barraCargarEliminar").slideDown(100);
    modal({
        type: 'confirm',
        title: 'Borrar Paciente',
        text: '¿Desea borrar al Paciente ' + nombreUsuario + ' y todos sus datos?',
        callback: function(result) {
            if(result) {
                $.ajax({
                    dataType: "json",
                    method: "POST",
                    url: "eliminarPaciente",
                    data: {nombreUsuario: nombreUsuario}
                })
                        .done(function (msg) {
                            $("#barraCargarEliminar").slideUp(100);
                            if (msg.estatusMensajeEliminar === "usuarioNoEncontrado") {
                                $('html, body').animate({scrollTop: 0}, 'fast');
                                $("#barraCargarEliminar").slideUp(100);
                                $("#tituloDivAlertErrorEliminar").html("<i class='icon fa fa-ban'></i>El Usuario no existe");
                                $("#labelMensajeErrorEliminar").html("El nombre de usuario no existe.");

                                $("#divAlertErrorEliminar").slideDown('slow').delay(2500).slideUp('slow');
                                $("#barraCargarEliminar").slideUp(100);
                            } else if (msg.estatusMensajeEliminar === "usuarioEncontrado") {
                                $("#barraCargarEliminar").slideDown('slow').delay(2500).slideUp('slow');
                                $("#tituloDivAlertSuccessEliminar").html("<i class='icon fa fa-check'></i>" + "Paciente Eliminado");
                                $("#labelMensajeSuccessEliminar").html("El Paciente fue eliminado exitosamente.");
                                $("#divAlertSuccessEliminar").slideDown('slow').delay(3000).slideUp('slow');
                                $("#gridPaciente").trigger("reloadGrid");
                            }
                        });
                    }
                    else {
                        alert("Se cancelo la operación");
                        $("#barraCargarEliminar").slideUp(100);
                    }
                }
         });
}

/******************************************************************************************************************************************/

function validarCamposAcceso() {
    var valido = true;
    $(".control-label").remove();
    $("#divClaveUsuario").removeClass("has-error");
    
    if ($("#clave").val().length < 6) {
        $("#divClaveUsuario").addClass("has-error");
        $("#divClaveUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una clave con mas de 6 caracteres.</label>");
        valido = false;
    }
    
    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlertEliminar").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }
    
    if(valido) {
        valido = true;
        $("#barraCargarEliminar").slideDown(100);
        document.forms["formEditarAccesoPaciente"].submit();
    }
    
    return false;
}

function validarCamposDireccion() {
    var valido = true;
    $(".control-label").remove();
    $('.has-error').removeClass('has-error');
    
    if ($("#calle").val().length < 6) {
        $("#divCallePaciente").addClass("has-error");
        $("#divCallePaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la calle.</label>");
        valido = false;
    }

    if ($("#colonia").val().length < 6) {
        $("#divColoniaPaciente").addClass("has-error");
        $("#divColoniaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la colonia.</label>");
        valido = false;
    }

    if ($("#delegacion").val().length < 6) {
        $("#divDelegacionPaciente").addClass("has-error");
        $("#divDelegacionPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Delegación.</label>");
        valido = false;
    }

    if ($("#entidadFederativa").val().length < 6) {
        $("#divEntidadFederativaPaciente").addClass("has-error");
        $("#divEntidadFederativaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Entidad Federativa.</label>");
        valido = false;
    }
    
    if ($("#codigoPostal").val().length < 5 || $("#codigoPostal").val().search('_') !== -1) {
        $("#divCodigoPostalPaciente").addClass("has-error");
        $("#divCodigoPostalPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un codigo postal valido.</label>");
        valido = false;
    }
    
    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlertEliminar").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }
    
    if(valido) {
        valido = true;
        $("#barraCargarEliminar").slideDown(100);
        document.forms["formEditarDireccionPaciente"].submit();
    }
    
    return false;
}

function validarCamposTelefonos() {
    var valido = true;
    $(".control-label").remove();
    $('.has-error').removeClass('has-error');
    
    if ($("#telefonoFijo").val().length < 8) {
        $("#divTelefonoFijoPaciente").addClass("has-error");
        $("#divTelefonoFijoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un teléfono fijo.</label>");
        valido = false;
    }

    if ($("#telefonoParticular").val().length < 8) {
        $("#divTelefonoParticularPaciente").addClass("has-error");
        $("#divTelefonoParticularPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un teléfono particular.</label>");
        valido = false;
    }

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlertEliminar").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }
    
    if(valido) {
        valido = true;
        $("#barraCargarEliminar").slideDown(100);
        document.forms["formEditarTelefonosPaciente"].submit();
    }
    
    return false;
}

function validarCamposDatosPersonales() {
    var valido = true;
    $(".control-label").remove();
    $('.has-error').removeClass('has-error');
    
    if ($("#estadoCivil").val().length < 3) {
        $("#divEdoCivilPaciente").addClass("has-error");
        $("#divEdoCivilPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un estado civil.</label>");
        valido = false;
    }

    if ($("#curp").val().length < 18) {
        $("#divCurpPaciente").addClass("has-error");
        $("#divCurpPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un CURP.</label>");
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
        $("#divEdadPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la edad.</label>");
        valido = false;
    }
    
    if ($("#peso").val().length < 2) {
        $("#divPesoPaciente").addClass("has-error");
        $("#divPesoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el peso (Kg).</label>");
        valido = false;
    }
    
    if ($("#altura").val().length < 1) {
        $("#divAlturaPaciente").addClass("has-error");
        $("#divAlturaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la altura (m).</label>");
        valido = false;
    }
    
    if ($("#telCasa").val().length < 8) {
        $("#divTelefonoCasaPaciente").addClass("has-error");
        $("#divTelefonoCasaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el teléfono de casa.</label>");
        valido = false;
    }
    
    if ($("#telCel").val().length < 8) {
        $("#divTelefonoCelPaciente").addClass("has-error");
        $("#divTelefonoCelPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el teléfono celular.</label>");
        valido = false;
    }
    
    if ($("#correo").val().length < 3) {
        $("#divCorreoPaciente").addClass("has-error");
        $("#divCorreoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un correo.</label>");
        valido = false;
    }
    
    if ($("#facebook").val().length < 5) {
        $("#divFacebookPaciente").addClass("has-error");
        $("#divFacebookPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa facebook (www.facebook.com/alguien).</label>");
        valido = false;
    }

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlertEliminar").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }
    
    if(valido) {
        valido = true;
        $("#barraCargarEliminar").slideDown(100);
        document.forms["formEditarDatosPersonalesPaciente"].submit();
    }
    
    return false;
}

function validarCamposContactos() {
    var valido = true;
    $(".control-label").remove();
    $('.has-error').removeClass('has-error');
    
    if ($("#nombreC").val().length < 1) {
        $("#divNombreCPaciente").addClass("has-error");
        $("#divNombreCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el nombre.</label>");
        valido = false;
    }

    if ($("#apellidoPaternoC").val().length < 1) {
        $("#divApellidoPaternoCPaciente").addClass("has-error");
        $("#divApellidoPaternoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el apellido paterno.</label>");
        valido = false;
    }
    
    if ($("#apellidoMaternoC").val().length < 1) {
        $("#divApellidoMaternoCPaciente").addClass("has-error");
        $("#divApellidoMaternoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el apellido materno.</label>");
        valido = false;
    }
    
    if ($("#parentesco").val().length < 1) {
        $("#divParentescoCPaciente").addClass("has-error");
        $("#divParentescoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el parentesco.</label>");
        valido = false;
    }
    
    if ($("#celular").val().length < 10) {
        $("#divCelularCPaciente").addClass("has-error");
        $("#divCelularCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el número de celular.</label>");
        valido = false;
    }
    
    if ($("#facebookC").val().length < 1) {
        $("#divFacebookCPaciente").addClass("has-error");
        $("#divFacebookCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa facebook (www.facebook.com/alguien).</label>");
        valido = false;
    }
    
    if ($("#correoC").val().length < 1) {
        $("#divCorreoCPaciente").addClass("has-error");
        $("#divCorreoCPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el correo.</label>");
        valido = false;
    }

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlertEliminar").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }
    
    if(valido) {
        valido = true;
        $("#barraCargarEliminar").slideDown(100);
        document.forms["formEditarContactosPaciente"].submit();
    }
    
    return false;
}

function eliminarPacientePorFiltro() {
    var nombreUsuario = $("#nombreUsuarioPorFiltro").val();
    $("#barraCargarEliminar").slideDown(100);
    modal({
        type: 'confirm',
        title: 'Borrar Paciente',
        text: '¿Desea borrar al Paciente ' + nombreUsuario + ' y todos sus datos?',
        callback: function(result) {
            if(result) {
                $.ajax({
                    dataType: "json",
                    method: "POST",
                    url: "eliminarPacientePorFiltro",
                    data: {nombreUsuario: nombreUsuario}
                })
                        .done(function (msg) {
                            $("#barraCargarEliminar").slideUp(100);
                            if (msg.estatusMensajeEliminar === "usuarioNoEncontrado") {
                                $('html, body').animate({scrollTop: 0}, 'fast');
                                $("#barraCargarEliminar").slideUp(100);
                                $("#tituloDivAlertErrorEliminar").html("<i class='icon fa fa-ban'></i>El Usuario no existe");
                                $("#labelMensajeErrorEliminar").html("El nombre de usuario no existe.");

                                $("#divAlertErrorEliminar").slideDown('slow').delay(2500).slideUp('slow');
                                $("#barraCargarEliminar").slideUp(100);
                            } else if (msg.estatusMensajeEliminar === "usuarioEncontrado") {
                                $("#barraCargarEliminar").slideDown('slow').delay(2500).slideUp('slow');
                                $("#tituloDivAlertSuccessEliminar").html("<i class='icon fa fa-check'></i>" + "Paciente Eliminado");
                                $("#labelMensajeSuccessEliminar").html("El Paciente fue eliminado exitosamente.");
                                $("#divAlertSuccessEliminar").slideDown('slow').delay(3000).slideUp('slow');
                                $("#gridPaciente").trigger("reloadGrid");
                                $("#nombreUsuarioPorFiltro").val("");
                                $("#datosPacientePorFiltro").slideUp(1000);
                                $("#gridPacientes").show(1000);
                            }
                        });
                    }
                    else {
                        alert("Se cancelo la operación");
                        $("#barraCargarEliminar").slideUp(100);
                    }
                }
         });
}
