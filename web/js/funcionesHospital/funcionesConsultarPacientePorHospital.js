/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*******************************************************************************/

function verDatosAccesoPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
       
    var clave = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'clave');
    var fechaRegistro = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'fechaRegistro');

    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $("#nombreUsuario").val(nombreUsuario);
        $("#nombreUsuario").attr('readonly', true);
        $("#clave").val(clave);
        $("#clave").attr('readonly', true);
        $("#fechaRegistro").val(fechaRegistro);
        $("#datosAccesoPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }    
}

function verDatosPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarDatosPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var urlImg = 'imagenesPerfilPaciente/'+msg.nombreUsuario+'.jpeg';
            $("#nss").val(msg.nss);
            $("#nss").attr('readonly', true);
            $("#nombre").val(msg.nombre);
            $("#nombre").attr('readonly', true);
            $("#apellidoPaterno").val(msg.apellidoPaterno);
            $("#apellidoPaterno").attr('readonly', true);
            $("#apellidoMaterno").val(msg.apellidoMaterno);
            $("#apellidoMaterno").attr('readonly', true);
            $("#unidadMedica").val(msg.unidadMedica);
            $("#unidadMedica").attr('readonly', true);
            $("#noConsultorio").val(msg.noConsultorio);
            $("#noConsultorio").attr('readonly', true);
            $("#nomUs").val(msg.nombreUsuario);
            $("#nomUs").attr('readonly', true);
            $("#codHos").val(msg.codigoHospital);
            $("#codHos").attr('readonly', true);
            $("#imagen").attr('src', urlImg);
            $("#imagen").attr('readonly', true);
        });
        $("#datosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verDatosDireccionPaciente() {
    
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarDatosDireccionPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#calle").val(msg.calle);
            $("#calle").attr('readonly', true);
            $("#colonia").val(msg.colonia);
            $("#colonia").attr('readonly', true);
            $("#delegacion").val(msg.delegacion);
            $("#delegacion").attr('readonly', true);
            $("#entidadFederativa").val(msg.entidadFederativa);
            $("#entidadFederativa").attr('readonly', true);
            $("#codigoPostal").val(msg.codigoPostal);
            $("#codigoPostal").attr('readonly', true);
            $("#idDomPaciente").val(msg.idDomicilioPaciente);
            $("#idDomPaciente").attr('readonly', true);
            $("#nomUsr").val(msg.nombreUsuario);
            $("#nomUsr").attr('readonly', true);
            $("#noSeSo").val(msg.nss);
            $("#noSeSo").attr('readonly', true);
        });

        $("#datosDireccionPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verDatosPersonalesPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarDatosPersonalesPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            if(msg.estadoCivil === "casado") {
                $("#radioCasado").attr('checked', true);
                $("#radioCasado").attr('readonly', true);
            }
            
            if(msg.estadoCivil === "union libre") {
                $("#radioUnionLibre").attr('checked', true);
                $("#radioUnionLibre").attr('readonly', true);
            }
            
            if(msg.estadoCivil === "soltero") {
                $("#radioSoltero").attr('checked', true);
                $("#radioSoltero").attr('readonly', true);
            }
            
            if(msg.estadoCivil === "divorciado") {
                $("#radioDivorciado").attr('checked', true);
                $("#radioDivorciado").attr('readonly', true);
            }
            
            $("#curp").val(msg.curp);
            
            if(msg.sexo === "masculino"){
                $("#radioMasculino").attr('checked', true);
                $("#radioMasculino").attr('readonly', true);
            }
            
            if(msg.sexo === "femenino"){
                $("#radioFemenino").attr('checked', true);
                $("#radioFemenino").attr('readonly', true);
            }
            
            $("#fechaNacimiento").val(msg.fechaNacimientoFormato);
            $("#fechaNacimiento").attr('readonly', true);
            $("#edad").val(msg.edad);
            $("#edad").attr('readonly', true);
            $("#peso").val(msg.peso);
            $("#peso").attr('readonly', true);
            $("#altura").val(msg.altura);
            $("#altura").attr('readonly', true);
            $("#talla").val(msg.talla);
            $("#talla").attr('readonly', true);
            $("#telCasa").val(msg.telCasa);
            $("#telCasa").attr('readonly', true);
            $("#telCel").val(msg.telCel);
            $("#telCel").attr('readonly', true);
            $("#correo").val(msg.correo);
            $("#correo").attr('readonly', true);
            $("#facebook").val(msg.facebook);
            $("#facebook").attr('readonly', true);
            $("#nomUs4").val(msg.nombreUsuario);
            $("#nomUs4").attr('readonly', true);
            $("#idDaPePa").val(msg.idDatosPersonalesPaciente);
            $("#idDaPePa").attr('readonly', true);
            $("#nssDatosPersonales").val(msg.nss);
            $("#nssDatosPersonales").attr('readonly', true);
        });

        $("#datosPersonalesPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verTelefonosPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarTelefonosPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            
            $("#TextBoxesGroupTelefonos").html(msg.telefonosDelPaciente);
            //$("#nomUs3").val(msg.nombreUsuario);
        });

        $("#datosTelefonosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verContactosPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarContactosPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#TextBoxesGroupContacto").html(msg.contactosDelPaciente);
            //$("#nomUs5").val(msg.nombreUsuario);
        });

        $("#datosContactosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verDatosClinicosPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarDatosClinicosPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            if(msg.drogas === "1"){
                $("#radioDrogasSi").attr('checked', true);
                $("#radioDrogasSi").attr('readonly', true);
            }
            else {
                $("#radioDrogasNo").attr('checked', true);
                $("#radioDrogasNo").attr('readonly', true);
            }
            
            if(msg.alcohol === "1"){
                $("#radioAlcoholSi").attr('checked', true);
                $("#radioAlcoholSi").attr('readonly', true);
            }
            else {
                $("#radioAlcoholNo").attr('checked', true);
                $("#radioAlcoholNo").attr('readonly', true);
            }
            if(msg.fuma === "1"){
                $("#radioFumaSi").attr('checked', true);
                $("#radioFumaSi").attr('readonly', true);
            }
            else {
                $("#radioFumaNo").attr('checked', true);
                $("#radioFumaNo").attr('readonly', true);
            }

            $("#nomUsrClin").val(msg.nombreUsuario);
            $("#noHisto").val(msg.noHistorial);
            $("#nssClinico").val(msg.nss);
        });

        $("#datosClinicosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verAlergiasPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarAlergiasPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var alergias = msg.alergias;

            for(var i = 0; i < alergias.length; i++) {
                var alergia = new Array();
                alergia = alergias[i].split(";");
                for(var indexAlergia = 0; indexAlergia < alergia.length; indexAlergia++) {
                    if(alergia[indexAlergia] === "polen") {
                        $("#checkboxAlergia0").attr('checked', true);
                        $("#checkboxAlergia0").attr('readonly', true);
                        $("#especificarAlergia0").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "acaros") {
                        $("#checkboxAlergia1").attr('checked', true);
                        $("#checkboxAlergia1").attr('readonly', true);
                        $("#especificarAlergia1").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "animales") {
                        $("#checkboxAlergia2").attr('checked', true);
                        $("#checkboxAlergia2").attr('readonly', true);
                        $("#especificarAlergia2").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "medicamentos") {
                        $("#checkboxAlergia3").attr('checked', true);
                        $("#checkboxAlergia3").attr('readonly', true);
                        $("#especificarAlergia3").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "insectos") {
                        $("#checkboxAlergia4").attr('checked', true);
                        $("#checkboxAlergia4").attr('readonly', true);
                        $("#especificarAlergia4").val(alergia[indexAlergia+1]);
                    }
                    
                    if(alergia[indexAlergia] === "alimentos") {
                        $("#checkboxAlergia5").attr('checked', true);
                        $("#checkboxAlergia5").attr('readonly', true);
                        $("#especificarAlergia5").val(alergia[indexAlergia+1]);
                    }
                }
            }
        });

        $("#datosAlergiasPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verCirugiasPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarCirugiasPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var cirugias = msg.cirugias;

            for(var i = 0; i < cirugias.length; i++) {
                var cirugia = new Array();
                cirugia = cirugias[i].split(";");
                for(var indexCirugia = 0; indexCirugia < cirugia.length; indexCirugia++) {
                    if(cirugia[indexCirugia] === "interna") {
                        $("#checkboxCirugia0").attr('checked', true);
                        $("#checkboxCirugia0").attr('readonly', true);
                        $("#noCirugia0").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "externa") {
                        $("#checkboxCirugia1").attr('checked', true);
                        $("#checkboxCirugia1").attr('readonly', true);
                        $("#noCirugia1").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "mayor") {
                        $("#checkboxCirugia2").attr('checked', true);
                        $("#checkboxCirugia2").attr('readonly', true);
                        $("#noCirugia2").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "menor") {
                        $("#checkboxCirugia3").attr('checked', true);
                        $("#checkboxCirugia3").attr('readonly', true);
                        $("#noCirugia3").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "curativa") {
                        $("#checkboxCirugia4").attr('checked', true);
                        $("#checkboxCirugia4").attr('readonly', true);
                        $("#noCirugia4").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "reparadora") {
                        $("#checkboxCirugia5").attr('checked', true);
                        $("#checkboxCirugia5").attr('readonly', true);
                        $("#noCirugia5").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "paliativa") {
                        $("#checkboxCirugia6").attr('checked', true);
                        $("#checkboxCirugia6").attr('readonly', true);
                        $("#noCirugia6").val(cirugia[indexCirugia+1]);
                    }
                    
                    if(cirugia[indexCirugia] === "cosmetica") {
                        $("#checkboxCirugia7").attr('checked', true);
                        $("#checkboxCirugia7").attr('readonly', true);
                        $("#noCirugia7").val(cirugia[indexCirugia+1]);
                    }
                }
            }
        });

        $("#datosCirugiasPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verDiscapacidadesPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarDiscapacidadesPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var discapacidades = msg.discapacidades;            
            for(var i = 0; i < discapacidades.length; i++) {
                var discapacidad = new Array();
                discapacidad = discapacidades[i].split(";");
                for(var indexDiscapacidad = 0; indexDiscapacidad < discapacidad.length; indexDiscapacidad++) {
                    if(discapacidad[indexDiscapacidad] === "fisica") {
                        $("#checkboxDiscapacidad0").attr('checked', true);
                        $("#checkboxDiscapacidad0").attr('readonly', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "intelectual") {
                        $("#checkboxDiscapacidad1").attr('checked', true);
                        $("#checkboxDiscapacidad1").attr('readonly', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "psiquica") {
                        $("#checkboxDiscapacidad2").attr('checked', true);
                        $("#checkboxDiscapacidad2").attr('readonly', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "visual") {
                        $("#checkboxDiscapacidad3").attr('checked', true);
                        $("#checkboxDiscapacidad3").attr('readonly', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "auditiva") {
                        $("#checkboxDiscapacidad4").attr('checked', true);
                        $("#checkboxDiscapacidad4").attr('readonly', true);
                    }
                    
                    if(discapacidad[indexDiscapacidad] === "habla") {
                        $("#checkboxDiscapacidad5").attr('checked', true);
                        $("#checkboxDiscapacidad5").attr('readonly', true);
                    }

                }
            }
        });

        $("#datosDiscapacidadesPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verMedicamentosPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarMedicamentosPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#TextBoxesGroup").html(msg.medicamentos);
        });

        $("#datosMedicamentosPaciente").show(1000);
        $("#gridPacientes").slideUp(1000);
    }
}

function verEnfermedadesCronicasPaciente() {
    var s = $("#gridListaConsultaPacientes").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridListaConsultaPacientes").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "mostrarEnfermedadesCronicasPacientePorHospital",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
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

function cancelarEdicionEnfermedades() {
    $("#datosEnfermedadesPaciente").slideUp(1000);
    $("#gridPacientes").show(1000);
}

/*Fin de cancelar ediciones*/

/*******************************************************************************/

function consultPacientePorHospitalPorFiltroUsuario() {
    var nombreUsuario = $("#nombreUsuarioPorFiltro").val();
    var nombreHospital = $("#nombreHospital").val();

    if(nombreUsuario.length === 0)
        nombreUsuario="";

        $("#barraCargarEliminar").slideUp(100);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "consultarDatosPacientePorHospitalMostrarFiltro",
            data: {nombreUsuario: nombreUsuario, nombreHospital: nombreHospital}
        }).done(function (msg) {
            $("#barraCargarEliminar").slideUp(100);
            if(msg.estatusMensajeEliminar === "usuarioEncontrado"){
                $("#barraCargarEliminar").slideDown('slow').delay(100).slideUp('slow');
                $('#gridListaConsultaPacientes').trigger("reloadGrid", [{page: 1}]);
               
            }
            else if (msg.estatusMensajeEliminar === "usuarioNoEncontrado") {
                $('html, body').animate({scrollTop: 0}, 'fast');
                $("#barraCargarEliminar").slideUp(100);
                $("#tituloDivAlertErrorEliminar").html("<i class='icon fa fa-ban'></i>El Usuario no existe");
                $("#labelMensajeErrorEliminar").html("El nombre de usuario no existe.");

                $("#divAlertErrorEliminar").slideDown('slow').delay(100).slideUp('slow');
                $("#barraCargarEliminar").slideUp(100);
                $("#nombreUsuarioPorFiltro").focus();
            }           
        });
    
}