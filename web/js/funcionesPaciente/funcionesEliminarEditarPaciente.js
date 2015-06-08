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
            $("#codHos").val(msg.codigoHospital);
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
            $("#idDomPaciente").val(msg.idDomicilioPaciente);
            $("#nomUsr").val(msg.nombreUsuario);
            $("#noSeSo").val(msg.nss);
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
            
            $("#TextBoxesGroupTelefonos").html(msg.telefonosDelPaciente);
            //$("#nomUs3").val(msg.nombreUsuario);
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
            if(msg.estadoCivil === "casado") {
                $("#radioCasado").attr('checked', true);
            }
            
            if(msg.estadoCivil === "union libre") {
                $("#radioUnionLibre").attr('checked', true);
            }
            
            if(msg.estadoCivil === "soltero") {
                $("#radioSoltero").attr('checked', true);
            }
            
            if(msg.estadoCivil === "divorciado") {
                $("#radioDivorciado").attr('checked', true);
            }
            
            $("#curp").val(msg.curp);
            
            if(msg.sexo === "masculino"){
                $("#radioMasculino").attr('checked', true)
            }
            
            if(msg.sexo === "femenino"){
                $("#radioFemenino").attr('checked', true)
            }
            
            $("#fechaNacimiento").val(msg.fechaNacimientoFormato);
            $("#edad").val(msg.edad);
            $("#peso").val(msg.peso);
            $("#altura").val(msg.altura);
            $("#talla").val(msg.talla);
            $("#telCasa").val(msg.telCasa);
            $("#telCel").val(msg.telCel);
            $("#correo").val(msg.correo);
            $("#facebook").val(msg.facebook);
            $("#nomUs4").val(msg.nombreUsuario);
            $("#idDaPePa").val(msg.idDatosPersonalesPaciente);
            $("#nssDatosPersonales").val(msg.nss);
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
            $("#TextBoxesGroupContacto").html(msg.contactosDelPaciente);
            //$("#nomUs5").val(msg.nombreUsuario);
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
            if(msg.drogas === "1"){
                $("#radioDrogasSi").attr('checked', true);
            }
            else {
                $("#radioDrogasNo").attr('checked', true);
            }
            
            if(msg.alcohol === "1"){
                $("#radioAlcoholSi").attr('checked', true);
            }
            else {
                $("#radioAlcoholNo").attr('checked', true);
            }
            if(msg.fuma === "1"){
                $("#radioFumaSi").attr('checked', true);
            }
            else {
                $("#radioFumaNo").attr('checked', true);
            }

            $("#nomUsrClin").val(msg.nombreUsuario);
            $("#noHisto").val(msg.noHistorial);
            $("#nssClinico").val(msg.nss);
        });

        $("#datosClinicosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarAlergiasPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarAlergiasPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var alergias = msg.alergias;

            for(var i = 0; i < alergias.length; i++) {
                var alergia = new Array();
                alergia = alergias[i].split(";");
                for(var indexAlergia = 0; indexAlergia < alergia.length; indexAlergia++) {
                    if(alergia[indexAlergia] === "polen") {
                        $("#checkboxAlergia0").attr('checked', true);
                        $("#especificarAlergia0").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "acaros") {
                        $("#checkboxAlergia1").attr('checked', true);
                        $("#especificarAlergia1").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "animales") {
                        $("#checkboxAlergia2").attr('checked', true);
                        $("#especificarAlergia2").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "medicamentos") {
                        $("#checkboxAlergia3").attr('checked', true);
                        $("#especificarAlergia3").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "insectos") {
                        $("#checkboxAlergia4").attr('checked', true);
                        $("#especificarAlergia4").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "alimentos") {
                        $("#checkboxAlergia5").attr('checked', true);
                        $("#especificarAlergia5").val(alergia[indexAlergia+1]);
                    }
                }
            }
        });

        $("#datosAlergiasPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarCirugiasPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarCirugiasPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var cirugias = msg.cirugias;

            for(var i = 0; i < cirugias.length; i++) {
                var cirugia = new Array();
                cirugia = cirugias[i].split(";");
                for(var indexCirugia = 0; indexCirugia < cirugia.length; indexCirugia++) {
                    if(cirugia[indexCirugia] === "interna") {
                        $("#checkboxCirugia0").attr('checked', true);
                        $("#noCirugia0").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "externa") {
                        $("#checkboxCirugia1").attr('checked', true);
                        $("#noCirugia1").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "mayor") {
                        $("#checkboxCirugia2").attr('checked', true);
                        $("#noCirugia2").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "menor") {
                        $("#checkboxCirugia3").attr('checked', true);
                        $("#noCirugia3").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "curativa") {
                        $("#checkboxCirugia4").attr('checked', true);
                        $("#noCirugia4").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "reparadora") {
                        $("#checkboxCirugia5").attr('checked', true);
                        $("#noCirugia5").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "paliativa") {
                        $("#checkboxCirugia6").attr('checked', true);
                        $("#noCirugia6").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "cosmetica") {
                        $("#checkboxCirugia7").attr('checked', true);
                        $("#noCirugia7").val(cirugia[indexCirugia+1]);
                    }
                }
            }
        });

        $("#datosCirugiasPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarDiscapacidadesPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDiscapacidadesPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var discapacidades = msg.discapacidades;            
            for(var i = 0; i < discapacidades.length; i++) {
                var discapacidad = new Array();
                discapacidad = discapacidades[i].split(";");
                for(var indexDiscapacidad = 0; indexDiscapacidad < discapacidad.length; indexDiscapacidad++) {
                    if(discapacidad[indexDiscapacidad] === "fisica") {
                        $("#checkboxDiscapacidad0").attr('checked', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "intelectual") {
                        $("#checkboxDiscapacidad1").attr('checked', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "psiquica") {
                        $("#checkboxDiscapacidad2").attr('checked', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "visual") {
                        $("#checkboxDiscapacidad3").attr('checked', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "auditiva") {
                        $("#checkboxDiscapacidad4").attr('checked', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "habla") {
                        $("#checkboxDiscapacidad5").attr('checked', true);
                    }

                }
            }
        });

        $("#datosDiscapacidadesPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarMedicamentosPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarMedicamentosPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#TextBoxesGroup").attr("class", 'row');
            $("#TextBoxesGroup").html(msg.medicamentos);
        });

        $("#datosMedicamentosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function editarEnfermedadesCronicasPaciente() {
    var s = $("#gridPaciente").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridPaciente").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarEnfermedadesCronicasPaciente",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#TextBoxesGroupEnfermedadCronica").attr("class", 'row');
            $("#TextBoxesGroupEnfermedadCronica").html(msg.enfermedadesCronicas);
        });

        $("#datosEnfermedadesPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
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

function cancelarEdicionNumTel(){
    $("#telefonoFijo").val("");
        
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
        
    $("#datosClinicosPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionAlergias(){
        
    $("#datosAlergiasPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionCirugias(){
        
    $("#datosCirugiasPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionDiscapacidades() {
    $("#datosDiscapacidadesPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

function cancelarEdicionMedicamentos() {
    $("#datosMedicamentosPaciente").slideUp(1000);
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

function validarCamposDatosIdentificacionPaciente() {
    var valido = true;
    $(".control-label").remove();
    $("#divUniMedicaPaciente").removeClass("has-error");
    $("#divNoConsultorioPaciente").removeClass("has-error");
    
    if ($("#unidadMedica").val().length > 4) {
        $("#divUniMedicaPaciente").addClass("has-error");
        $("#divUniMedicaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la unidad medica (no mayor a 4 digitos).</label>");
        valido = false;
    }
    
    if ($("#noConsultorio").val().length < 1) {
        $("#divNoConsultorioPaciente").addClass("has-error");
        $("#divNoConsultorioPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa el numero de consultorio.</label>");
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
        document.forms["formEditarDatosIdentificacionPaciente"].submit();
    }
    
    return false;
}

function validarCamposDireccion() {
    var valido = true;
    $(".control-label").remove();
    $("#divCallePaciente").removeClass("has-error");
    $("#divColoniaPaciente").removeClass("has-error");
    $("#divDelegacionPaciente").removeClass("has-error");
    $("#divEntidadFederativaPaciente").removeClass("has-error");
    $("#divCodigoPostalPaciente").removeClass("has-error");
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

function validarCamposDatosPersonalesPaciente() {
    var valido = true;
    $(".control-label").remove();
    $("#divEdadPaciente").removeClass("has-error");
    $("#divPesoPaciente").removeClass("has-error");
    $("#divAlturaPaciente").removeClass("has-error");
    $("#divTallaPaciente").removeClass("has-error");
    $("#divCorreoPaciente").removeClass("has-error");
    $("#divFacebookPaciente").removeClass("has-error");
    $('.has-error').removeClass('has-error');
    
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
    
    if ($("#talla").val().length < 1) {
        $("#divTallaPaciente").addClass("has-error");
        $("#divTallaPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una talla valida.</label>");
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
