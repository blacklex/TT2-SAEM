
/*******************************************************************************/

//$(function () {
//    $("[data-mask]").inputmask();
//});

/*******************************************************************************/

function verDatosAccesoAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
    if(s === null) {
        alert("Seleccione un usuario");
        return;
    }

    var nombreUsuario = $("#gridAdmin").jqGrid('getCell',s,'nombreUsuario');
       
    var clave = $("#gridAdmin").jqGrid('getCell',s,'clave');
    var fechaRegistro = $("#gridAdmin").jqGrid('getCell',s,'fechaRegistro');
    $("#nombreUsuario").val(nombreUsuario);
    $("#clave").val(clave);
    $("#fechaRegistro").val(fechaRegistro);
    $("#datosAccesoAdministrador").show(1000);
    $("#gridAdministradores").slideUp(1000);
}

function verDatosPersonalesAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
     if(s === null) {
        alert("Seleccione un usuario");
        return;
    }
    var nombreUsuario = $("#gridAdmin").jqGrid('getCell',s,'nombreUsuario');
    
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "verDatosPersonalesAdministrador",
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

function verDireccionAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
     if(s === null) {
        alert("Seleccione un usuario");
        return;
    }
    var nombreUsuario = $("#gridAdmin").jqGrid('getCell',s,'nombreUsuario');
    
    $.ajax({
        dataType: "json",
        method: "POST",
        url: "verDatosDireccionAdministrador",
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

function buscarUsuarioPorFiltroUsuario() {
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

/*******************************************************************************/

/*Cancelar ediciones*/

function cancelarEdicionAcceso() {
    $("#nombreUsuario").val("");
    $("#clave").val("");
    $("#fechaRegistro").val("");
    
    $("#datosAccesoAdministrador").slideUp(1000);
    $("#gridAdministradores").show(1000);
}

function cancelarEdicionDatosPersonales() {
    $("#nombre").val();
    $("#apellidoPaterno").val();
    $("#apellidoMaterno").val();
    $("#telParticular").val();
    $("#correo").val();
    
    $("#datosPersonalesAdministrador").slideUp(1000);
    $("#gridAdministradores").show(1000);   
}

function cancelarEdicionDireccion(){
    $("#calle").val();
    $("#colonia").val();
    $("#delegacion").val();
    $("#entidadFederativa").val();
    $("#codigoPostal").val();
        
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
