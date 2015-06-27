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
                $("#gridAdmin").trigger("reloadGrid");
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

function editarAccesoAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridAdmin").jqGrid('getCell',s,'nombreUsuario');
       
    var clave = $("#gridAdmin").jqGrid('getCell',s,'clave');
    var fechaRegistro = $("#gridAdmin").jqGrid('getCell',s,'fechaRegistro');

    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $("#nombreUsuario").val(nombreUsuario);
        $("#clave").val(clave);
        $("#fechaRegistro").val(fechaRegistro);
        $("#datosAccesoAdministrador").show(1000);
        $("#gridAdministradores").slideUp(1000);
    }    
}

function editarDatosPersonalesAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridAdmin").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosPersonalesAdministrador",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            var urlImg = 'imagenesPerfilAdmin/'+msg.nombreUsuario+'.jpeg';
            $("#nombre").val(msg.nombre);
            $("#apellidoPaterno").val(msg.apellidoPaterno);
            $("#apellidoMaterno").val(msg.apellidoMaterno);
            $("#telParticular").val(msg.telParticular);
            $("#correo").val(msg.correo);
            $("#nomUs").val(msg.nombreUsuario);
            $("#imagen").attr('src', urlImg);
        });
        $("#datosPersonalesAdministrador").show(1000);
        $("#gridAdministradores").slideUp(1000);
    }
}

function editarDireccionAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridAdmin").jqGrid('getCell',s,'nombreUsuario');
    if(s === null) {
        alert("Seleccione un usuario");
    }
    else {
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosDireccionAdministrador",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#calle").val(msg.calle);
            $("#colonia").val(msg.colonia);
            $("#delegacion").val(msg.delegacion);
            $("#entidadFederativa").val(msg.entidadFederativa);
            $("#codigoPostal").val(msg.codigoPostal);
            $("#idDomAdmin").val(msg.id);
            $("#telPart").val(msg.telParticular);
            $("#nomUsr").val(msg.nombreUsuario);
        });

        $("#datosDireccionAdministrador").show(1000);
        $("#gridAdministradores").slideUp(1000);
    }
}

function buscarUsuarioPorFiltroUsuario() {
    var nombreUsuario = $("#nombreUsuarioPorFiltro").val();

    if(nombreUsuario.length === 0)
        nombreUsuario="";
  
        $("#barraCargarEliminar").slideUp(100);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosPorFiltro",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#barraCargarEliminar").slideUp(100);
            if(msg.estatusMensajeEliminar === "usuarioEncontrado"){
                $("#barraCargarEliminar").slideDown('slow').delay(100).slideUp('slow');
                $('#gridAdmin').trigger("reloadGrid", [{page: 1}]);
            } else if (msg.estatusMensajeEliminar === "usuarioNoEncontrado") {
                $('html, body').animate({scrollTop: 0}, 'fast');
                $("#barraCargarEliminar").slideUp(100);
                $("#tituloDivAlertErrorEliminar").html("<i class='icon fa fa-ban'></i>El Usuario no existe");
                $("#labelMensajeErrorEliminar").html("El nombre de usuario no existe.");

                $("#divAlertErrorEliminar").slideDown('slow').delay(100).slideUp('slow');
                $("#barraCargarEliminar").slideUp(100);

            }
                
            });
        
    }

/*******************************************************************************/

/*Cancelar ediciones*/

function cancelarEdicionAcceso() {
    $(".control-label").remove();
    $("#divClaveUsuario").removeClass("has-error");
    $("#nombreUsuario").val("");
    $("#clave").val("");
    $("#fechaRegistro").val("");
    
    $("#datosAccesoAdministrador").slideUp(1000);
    $("#gridAdministradores").show(1000);
}

function cancelarEdicionDatosPersonales() {
    $("#nombre").val("");
    $("#apellidoPaterno").val("");
    $("#apellidoMaterno").val("");
    $("#telParticular").val("");
    $("#correo").val("");
    
    $("#datosPersonalesAdministrador").slideUp(1000);
    $("#gridAdministradores").show(1000);   
}

function cancelarEdicionDireccion(){
    $("#calle").val("");
    $("#colonia").val("");
    $("#delegacion").val("");
    $("#entidadFederativa").val("");
    $("#codigoPostal").val("");
        
    $("#datosDireccionAdministrador").slideUp(1000);
    $("#gridAdministradores").show(1000);
}

function cancelarEdicionPorFiltro(){
    $("#nombreUsuarioFiltro").val("");
    $("#claveFiltro").val("");
    $("#nombreFiltro").val("");
    $("#apellidoPaternoFiltro").val("");
    $("#apellidoMaternoFiltro").val("");
    $("#telParticularFiltro").val("");
    $("#correoFiltro").val("");
    $("#calleFiltro").val("");
    $("#coloniaFiltro").val("");
    $("#delegacionFiltro").val("");
    $("#entidadFederativaFiltro").val("");
    $("#codigoPostalFiltro").val("");
    $("#idDomAdminFiltro").val("");
    $("#nombreUsuarioPorFiltro").val("");

    $("#datosAdministradorPorFiltro").slideUp(1000);
    $("#gridAdministradores").show(1000);
}

/*Fin de cancelar ediciones*/

/*******************************************************************************/

function eliminarAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
    var nombreUsuario = $("#gridAdmin").jqGrid('getCell',s,'nombreUsuario');
    $("#barraCargarEliminar").slideDown(100);
    modal({
        type: 'confirm',
        title: 'Borrar Administrador',
        text: '¿Desea borrar al Administrador ' + nombreUsuario + ' y todos sus datos?',
        callback: function(result) {
            if(result) {
                $.ajax({
                    dataType: "json",
                    method: "POST",
                    url: "eliminarAdministrador",
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
                                $("#tituloDivAlertSuccessEliminar").html("<i class='icon fa fa-check'></i>" + "Administrador Eliminado");
                                $("#labelMensajeSuccessEliminar").html("El Administrador fue eliminado exitosamente.");
                                $("#divAlertSuccessEliminar").slideDown('slow').delay(3000).slideUp('slow');
                                $("#gridAdmin").trigger("reloadGrid");
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
        document.forms["formEditarAccesoAdministrador"].submit();
    }
    
    return false;
}

function validarCamposDireccion() {
    var valido = true;
    $(".control-label").remove();
    $("#divCalleAdministrador").removeClass("has-error");
    $("#divColoniaAdministrador").removeClass("has-error");
    $("#divDelegacionAdministrador").removeClass("has-error");
    $("#divEntidadFederativaAdministrador").removeClass("has-error");
    $("#divCodigoPostalAdministrador").removeClass("has-error");
    
    if ($("#calle").val().length < 6) {
        $("#divCalleAdministrador").addClass("has-error");
        $("#divCalleAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la calle.</label>");
        valido = false;
    }

    if ($("#colonia").val().length < 6) {
        $("#divColoniaAdministrador").addClass("has-error");
        $("#divColoniaAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la colonia.</label>");
        valido = false;
    }

    if ($("#delegacion").val().length < 6) {
        $("#divDelegacionAdministrador").addClass("has-error");
        $("#divDelegacionAdministrador").append("<label class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa la Delegación.</label>");
        valido = false;
    }

    if ($("#entidadFederativa").val().length < 6) {
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
        $("#divAlertEliminar").slideDown('slow').delay(2500).slideUp('slow');
        return valido;
    }
    
    if(valido) {
        valido = true;
        $("#barraCargarEliminar").slideDown(100);
        document.forms["formEditarDireccionAdministrador"].submit();
    }
    
    return false;
}

function eliminarAdministradorPorFiltro() {
    var nombreUsuario = $("#nombreUsuarioPorFiltro").val();
    $("#barraCargarEliminar").slideDown(100);
    modal({
        type: 'confirm',
        title: 'Borrar Administrador',
        text: '¿Desea borrar al Administrador ' + nombreUsuario + ' y todos sus datos?',
        callback: function(result) {
            if(result) {
                $.ajax({
                    dataType: "json",
                    method: "POST",
                    url: "eliminarAdministradorPorFiltro",
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
                                $("#tituloDivAlertSuccessEliminar").html("<i class='icon fa fa-check'></i>" + "Administrador Eliminado");
                                $("#labelMensajeSuccessEliminar").html("El Administrador fue eliminado exitosamente.");
                                $("#divAlertSuccessEliminar").slideDown('slow').delay(3000).slideUp('slow');
                                $("#gridAdmin").trigger("reloadGrid");
                                $("#nombreUsuarioPorFiltro").val("");
                                $("#datosAdministradorPorFiltro").slideUp(1000);
                                $("#gridAdministradores").show(1000);
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

function buscarUsuarioModificarEliminarPorFiltroUsuario() {
    var nombreUsuario = $("#nombreUsuarioPorFiltro").val();

    if(nombreUsuario.length === 0)
        nombreUsuario="";

        $("#barraCargarEliminar").slideUp(100);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosMostrarFiltro",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#barraCargarEliminar").slideUp(100);
            if(msg.estatusMensajeEliminar === "usuarioEncontrado"){
                $("#barraCargarEliminar").slideDown('slow').delay(100).slideUp('slow');
                $('#gridAdmin').trigger("reloadGrid", [{page: 1}]);
               
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