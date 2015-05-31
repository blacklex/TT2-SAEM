/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//$(document).ready(function () {
//    $.getJSON("recuperarMensajeEstatusPaciente");
//    
//    $(document).ajaxSuccess(function (event, request, settings) {
//        
//        if (settings.url.match('recuperarMensajeEstatusPaciente') !== null) {
//            
//            var tituloAlert = $.parseJSON(request.responseText).tituloAlert;
//            var textoAlert = $.parseJSON(request.responseText).textoAlert;
//            var estatusMensaje = $.parseJSON(request.responseText).estatusMensaje;
//
//            if (estatusMensaje === null)
//                return;
//
//            if (estatusMensaje === "success") {
//                $("#tituloDivAlertSuccess").html("<i class='icon fa fa-check'></i>" + tituloAlert);
//                $("#labelMensajeSuccess").html(textoAlert);
//                $("#divAlertSuccess").slideDown('slow').delay(2500).slideUp('slow');
//            } else if (estatusMensaje === "error") {
//                $("#tituloDivAlertError").html("<i class='icon fa fa-ban'></i>" + tituloAlert);
//                $("#labelMensajeError").html(textoAlert);
//                $("#divAlertError").slideDown('slow').delay(2500).slideUp('slow');
//            }
//        }
//    });
//});

/*******************************************************************************/

$(function () {
    $("[data-mask]").inputmask();
});

/*******************************************************************************/

function validarCampos() {
    var valido = true;
    $(".control-label").remove();
    $("#divNombreUsuarioPaciente").removeClass("has-error");
    $("#divClaveUsuarioPaciente").removeClass("has-error");
    $("#divNss").removeClass("has-error");
    $("#divNombrePaciente").removeClass("has-error");
    $("#divApellidoPaternoPaciente").removeClass("has-error");
    $("#divApellidoMaternoPaciente").removeClass("has-error");
    $("#divUniMedicaPaciente").removeClass("has-error");
    $("#divNoConsultorioPaciente").removeClass("has-error");
    $("#divCallePaciente").removeClass("has-error");
    $("#divColoniaPaciente").removeClass("has-error");
    $("#divDelegacionPaciente").removeClass("has-error");
    $("#divEntidadFederativaPaciente").removeClass("has-error");
    $("#divCodigoPostalPaciente").removeClass("has-error");
    $("#divCurpPaciente").removeClass("has-error");
    $("#divFechaNacimientoPaciente").removeClass("has-error");
    $("#divEdadPaciente").removeClass("has-error");
    $("#divPesoPaciente").removeClass("has-error");
    $("#divAlturaPaciente").removeClass("has-error");
    $("#divTallaPaciente").removeClass("has-error");
    

    if ($("#nombreUsuario").val().length < 4) {
        $("#divNombreUsuarioPaciente").addClass("has-error");
        $("#divNombreUsuarioPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu Nombre de Usuario.</label>");
        valido = false;
    }

    if ($("#clave").val().length < 6) {
        $("#divClaveUsuarioPaciente").addClass("has-error");
        $("#divClaveUsuarioPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa una clave con mas de 6 caracteres.</label>");
        valido = false;
    }

    if ($("#nss").val().length < 11) {
        $("#divNss").addClass("has-error");
        $("#divNss").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu NSS.</label>");
        valido = false;
    }
    
    if ($("#nombre").val().length < 6) {
        $("#divNombrePaciente").addClass("has-error");
        $("#divNombrePaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu nombre.</label>");
        valido = false;
    }
        
    if ($("#apellidoPaterno").val().length < 5) {
        $("#divApellidoPaternoPaciente").addClass("has-error");
        $("#divApellidoPaternoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu apellido paterno.</label>");
        valido = false;
    }
    
    if ($("#apellidoMaterno").val().length < 5) {
        $("#divApellidoMaternoPaciente").addClass("has-error");
        $("#divApellidoMaternoPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu apellido materno.</label>");
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
        
    if ($("#curp").val().length < 17) {
        $("#divCurpPaciente").addClass("has-error");
        $("#divCurpPaciente").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa un CURP valido.</label>");
        valido = false;
    }
        
    if ($("#fechaNacimiento").val().length < 10) {
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

    if (!valido) {
        $('html, body').animate({scrollTop: 0}, 'fast');
        $("#divAlertPaciente").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }

    $("#barraCargarPaciente").slideDown(100);
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "validarNombreUsuarioPaciente",
        data: {nombreUsuario: $("#nombreUsuario").val()}
    })
            .done(function (msg) {
                $("#barraCargarPaciente").slideUp(100);
                if (msg.estatusMensaje === "nombreNoValido") {
                    $('html, body').animate({scrollTop: 0}, 'fast');
                    $("#barraCargarPaciente").slideUp(100);
                    $("#tituloDivAlertErrorPaciente").html("<i class='icon fa fa-ban'></i>El Usuario ya existe");
                    $("#labelMensajeErrorPaciente").html("El nombre de usuario ya existe, escribe uno nuevo.");

                    $("#divNombreUsuario").addClass("has-error");
                    $("#divNombreUsuario").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  El nombre de usuario ya existe, escribe uno nuevo.</label>");

                    $("#divAlertError").slideDown('slow').delay(2500).slideUp('slow');
                    valido = false;
                    $("#barraCargarPaciente").slideUp(100);
                    return valido;
                } else if (msg.estatusMensaje === "nombreValido") {
                    valido = true;
                    $("#barraCargarPaciente").slideDown(100);
                    document.forms["formNuevoPaciente"].submit();
                }
            });
    return false;
}

function validarCheckboxAlergias() {
    if( $('#checkboxPolen').is(':checked') ) {
        $("#especificarPolen").attr('disabled', false);
        $("#especificarPolen").focus();
    }
    else {
        $("#especificarPolen").attr('disabled', true);
    }
    
    if( $('#checkboxAcaros').is(':checked') ) {
        $("#especificarAcaros").attr('disabled', false);
        $("#especificarAcaros").focus();
    }
    else {
        $("#especificarAcaros").attr('disabled', true);
    }
    
    if( $('#checkboxAnimales').is(':checked') ) {
        $("#especificarAnimales").attr('disabled', false);
        $("#especificarAnimales").focus();
    }
    else {
        $("#especificarAnimales").attr('disabled', true);
    }
    
    if( $('#checkboxMedicamentos').is(':checked') ) {
        $("#especificarMedicamentos").attr('disabled', false);
        $("#especificarMedicamentos").focus();
    }
    else {
        $("#especificarMedicamentos").attr('disabled', true);
    }
    
    if( $('#checkboxInsectos').is(':checked') ) {
        $("#especificarInsectos").attr('disabled', false);
        $("#especificarInsectos").focus();
    }
    else {
        $("#especificarInsectos").attr('disabled', true);
    }
    
    if( $('#checkboxAlimentos').is(':checked') ) {
        $("#especificarAlimentos").attr('disabled', false);
        $("#especificarAlimentos").focus();
    }
    else {
        $("#especificarAlimentos").attr('disabled', true);
    }
}

function validarCheckboxCirugias() {
    if( $('#checkboxInterna').is(':checked') ) {
        $("#noCirugiaInterna").attr('disabled', false);
        $("#noCirugiaInterna").focus();
    }
    else {
        $("#noCirugiaInterna").attr('disabled', true);
        $("#noCirugiaInterna").val("");
        
    }
    
    if( $('#checkboxExterna').is(':checked') ) {
        $("#noCirugiaExterna").attr('disabled', false);
        $("#noCirugiaExterna").focus();
    }
    else {
        $("#noCirugiaExterna").attr('disabled', true);
        $("#noCirugiaExterna").val("");
    }
    
    if( $('#checkboxMayor').is(':checked') ) {
        $("#noCirugiaMayor").attr('disabled', false);
        $("#noCirugiaMayor").focus();
    }
    else {
        $("#noCirugiaMayor").attr('disabled', true);
        $("#noCirugiaMayor").val("");
    }
    
    if( $('#checkboxMenor').is(':checked') ) {
        $("#noCirugiaMenor").attr('disabled', false);
        $("#noCirugiaMenor").focus();
    }
    else {
        $("#noCirugiaMenor").attr('disabled', true);
        $("#noCirugiaMenor").val("");
    }
    
    if( $('#checkboxCurativa').is(':checked') ) {
        $("#noCirugiaCurativa").attr('disabled', false);
        $("#noCirugiaCurativa").focus();
    }
    else {
        $("#noCirugiaCurativa").attr('disabled', true);
        $("#noCirugiaCurativa").val("");
    }
    
    if( $('#checkboxReparadora').is(':checked') ) {
        $("#noCirugiaReparadora").attr('disabled', false);
        $("#noCirugiaReparadora").focus();
    }
    else {
        $("#noCirugiaReparadora").attr('disabled', true);
        $("#noCirugiaReparadora").val("");
    }
    
    if( $('#checkboxPaliativa').is(':checked') ) {
        $("#noCirugiaPaliativa").attr('disabled', false);
        $("#noCirugiaPaliativa").focus();
    }
    else {
        $("#noCirugiaPaliativa").attr('disabled', true);
        $("#noCirugiaPaliativa").val("");
    }
    
    if( $('#checkboxCosmetica').is(':checked') ) {
        $("#noCirugiaCosmetica").attr('disabled', false);
        $("#noCirugiaCosmetica").focus();
    }
    else {
        $("#noCirugiaCosmetica").attr('disabled', true);
        $("#noCirugiaCosmetica").val("");
    }
       
}

$(document).ready(function() {
    var counter = 0;
    
    $("#agregarEnfermedad").click(function () {
        
	if(counter > 4) {
            alert("Limite alcanzado");
            return false;
        }
        
        var newTextBoxDiv = $(document.createElement('div')).attr("id", 'enfermedadesCronicas' + counter);
        newTextBoxDiv.attr("class", 'row');
 
	newTextBoxDiv.after().html(
                                    '<div class="col-lg-4">' +
                                        '<div style="margin-bottom:10px;" class="form-group">' +
                                            '<label>Nombre enfermedad #'+ (counter + 1)+ ' : </label>' +
                                            '<input class="form-control" type="text" name="enfermedadCronica' + counter + '" id="enfermedadCronica' + counter + '" value="" placeholder="Nombre enfermedad'+counter+'" />' + 
                                        '</div>'+
                                    '</div>'+
                                    '<div class="col-lg-4">'+
                                        '<label>Tipo</label>'+
                                        '<select name="tipoEnfermedad'+ counter +'" class="form-control">'+
                                            '<option value="-1">Seleccionar</option>'+
                                            '<option value="diabetes">Diabetes</option>'+
                                            '<option value="cardiovascular">Enfermedades cardiovasculares</option>'+
                                            '<option value="obesidad">Obesidad</option>'+
                                            '<option value="cancer">Cáncer</option>'+
                                            '<option value="dislipidemias">Dislipidemias</option>'+
                                        '</select>'+
                                    '</div>'+
                                    '<div class="col-lg-4">'+
                                        '<div id="divInicioEnfermedadPaciente" class="form-group">'+
                                            '<label for="inicioEnfermedad">Inicio de enfermedad</label>'+
                                            '<div class="input-group">'+
                                                '<div class="input-group-addon">'+
                                                    '<i class="fa fa-calendar"></i>'+
                                                '</div>'+
                                                '<input kl_virtual_keyboard_secure_input="on" class="form-control" name="inicioEnfermedad'+ counter +'" id="inicioEnfermedad'+counter+'" data-inputmask="\'alias\': \'dd/mm/yyyy\'" data-mask type="text">'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>');
        newTextBoxDiv.appendTo("#TextBoxesGroup");
        counter++;
    });
    
    $("#eliminarEnfermedad").click(function () {
        if(counter === 0) {
            alert("Se borraron todas las entradas");
            return false;
        }
        
        counter--;
        $("#enfermedadesCronicas" + counter).remove();
    });
//*********************************************************************************************************************************
    var counterContacto = 0;
    
    $("#agregarContacto").click(function () {
        if(counterContacto > 7) {
            alert("Limite alcanzado");
            return false;
        }
        
	var newTextBoxDiv = $(document.createElement('div')).attr("id", 'contactoPaciente' + counterContacto);
 
	newTextBoxDiv.after().html( '<label for="nombreC">Contacto #'+ (counterContacto + 1) +'</label>'+
                                    '<div id="divNombreCPaciente'+counterContacto+'" class="form-group">'+
                                        '<label for="nombreC">Nombre</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreContacto'+counterContacto+'" id="nombreContacto'+counterContacto+'" placeholder="Nombre" type="text">'+
                                    '</div>'+

                                    '<div id="divApellidoPaternoCPaciente'+counterContacto+'" class="form-group">'+
                                        '<label for="apellidoPaternoC">Apellido Paterno</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaternoContacto'+counterContacto+'" id="apellidoPaternoContacto'+counterContacto+'" placeholder="Apellido Paterno" type="text">'+
                                    '</div>'+

                                    '<div id="divApellidoMaternoCPaciente'+counterContacto+'" class="form-group">'+
                                        '<label for="apellidoMaternoC">Apellido Materno</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaternoContacto'+counterContacto+'" id="apellidoMaternoContacto'+counterContacto+'" placeholder="Apellido Materno" type="text">'+
                                    '</div>'+

                                    '<div id="divParentescoCPaciente'+counterContacto+'" class="form-group">'+
                                        '<label for="parentesco">Parentesco</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" class="form-control" name="parentescoContacto'+counterContacto+'" id="parentescoContacto'+counterContacto+'" placeholder="Parentesco" type="text">'+
                                    '</div>'+

                                    '<div id="divCelularCPaciente'+counterContacto+'" class="form-group">'+
                                        '<label for="celular">Telefono Celular</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" name="celularContacto'+counterContacto+'" id="celularContacto'+counterContacto+'" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Celular" type="text">'+
                                    '</div>'+

                                    '<div id="divFacebookCPaciente'+counterContacto+'" class="form-group">'+
                                        '<label for="facebookC">Facebook (www.facebook.com/alguien)</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" class="form-control" name="facebookContacto'+counterContacto+'" id="facebookContacto'+counterContacto+'" placeholder="Facebook" type="text">'+
                                    '</div>'+

                                    '<div id="divCorreoCPaciente'+counterContacto+'" class="form-group">'+
                                        '<label for="correoC">Email</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" class="form-control" name="correoContacto'+counterContacto+'" id="correoContacto'+counterContacto+'" placeholder="Email" type="text">'+
                                    '</div>');
        

	newTextBoxDiv.appendTo("#TextBoxesGroupContacto");
	counterContacto++;
     });
 
     $("#eliminarContacto").click(function () {
         if(counterContacto === 0){
            alert("Se borraron todas las entradas");
            return false;
        }
        
         counterContacto--;
         $("#contactoPaciente" + counterContacto).remove();
     });
     
//*************************************************************************************************************************************************

var counterTelefono = 0;
    
    $("#agregarTelefono").click(function () {
        if(counterTelefono > 7) {
            alert("Limite alcanzado");
            return false;
        }
        
	var newTextBoxDiv = $(document.createElement('div')).attr("id", 'telefonoPaciente' + counterTelefono);
 
	newTextBoxDiv.after().html(
                                    '<div id="divTelefonoFijoPaciente" class="form-group">'+
                                        '<label for="telefonoPaciente">Teléfono #'+ (counterTelefono + 1) +'</label>'+
                                        '<input kl_virtual_keyboard_secure_input="on" name="numTelefono'+counterTelefono+'" id="numTelefono'+counterTelefono+'" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="No. Telefono" type="text">'+
                                    '</div>');
        

	newTextBoxDiv.appendTo("#TextBoxesGroupTelefonos");
	counterTelefono++;
     });
 
     $("#eliminarTelefono").click(function () {
         if(counterTelefono === 0){
            alert("Se borraron todas las entradas");
            return false;
        }
        
         counterTelefono--;
         $("#telefonoPaciente" + counterTelefono).remove();
     });
//******************************************************************************************************************************************************************

var counterMedicamento = 0;
    
    $("#agregarMedicamento").click(function () {
        
	if(counterMedicamento > 4) {
            alert("Limite alcanzado");
            return false;
        }
        
        var newTextBoxDiv = $(document.createElement('div')).attr("id", 'medicamentos' + counterMedicamento);
        newTextBoxDiv.attr("class", 'row');
 
	newTextBoxDiv.after().html(
                                    '<div class="col-lg-6">'+
                                        '<div style="margin-bottom:10px;" class="form-group">'+
                                            '<label>Nombre del medicamento #'+ (counterMedicamento + 1) +'</label>'+
                                            '<input class="form-control" type="text" value="" name="medicamento'+counterMedicamento+'" id="medicamento'+counterMedicamento+'" placeholder="Medicamentó"/>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="col-lg-6">'+
                                        '<div style="margin-bottom:10px;" class="form-group">'+
                                            '<label>Frecuencia</label>'+
                                            '<input class="form-control" type="text" value="" name="frecuencia'+counterMedicamento+'" id="frecuencia'+counterMedicamento+'" placeholder="Frecuencia"/>'+
                                        '</div>'+
                                    '</div>');
        newTextBoxDiv.appendTo("#TextBoxesGroupMedicamentos");
        counterMedicamento++;
    });
    
    $("#eliminarMedicamento").click(function () {
        if(counterMedicamento === 0) {
            alert("Se borraron todas las entradas");
            return false;
        }
        
        counterMedicamento--;
        $("#medicamentos" + counterMedicamento).remove();
    });
 });