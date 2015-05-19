
/*******************************************************************************/

//$(function () {
//    $("[data-mask]").inputmask();
//});

/*******************************************************************************/

function verDatosAccesoAdministrador() {
    var s = $("#gridAdmin").jqGrid('getGridParam','selrow');
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
        alert("Ingrese un usuario");
    else {
        $("#barraCargarEliminar").slideUp(100);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "buscarDatosMostrarFiltro",
            data: {nombreUsuario: nombreUsuario}
        }).done(function (msg) {
            $("#barraCargarEliminar").slideUp(100);
            if(msg.estatusMensajeEliminar === "usuarioEncontrado"){
                $("#barraCargarEliminar").slideDown('slow').delay(2500).slideUp('slow');
                var urlImg = 'imagenesPerfilAdmin/'+msg.nombreUsuario+'.jpeg';
                $("#nombreUsuarioFiltro").val(msg.nombreUsuario);
                $("#nombreUsuarioFiltro").attr('readonly', true);
                $("#claveFiltro").val(msg.clave);
                $("#claveFiltro").attr('readonly', true);
                $("#nombreFiltro").val(msg.nombre);
                $("#nombreFiltro").attr('readonly', true);
                $("#apellidoPaternoFiltro").val(msg.apellidoPaterno);
                $("#apellidoPaternoFiltro").attr('readonly', true);
                $("#apellidoMaternoFiltro").val(msg.apellidoMaterno);
                $("#apellidoMaternoFiltro").attr('readonly', true);
                $("#telParticularFiltro").val(msg.telParticular);
                $("#telParticularFiltro").attr('readonly', true);
                $("#correoFiltro").val(msg.correo);
                $("#correoFiltro").attr('readonly', true);
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
                $("#idDomAdminFiltro").val(msg.id);
                $("#idDomAdminFiltro").attr('readonly', true);
                $("#imagenFiltro").attr('src', urlImg);
                $("#datosAdministradorPorFiltro").show(1000);
                $("#gridAdministradores").slideUp(1000);
            }
            else if (msg.estatusMensajeEliminar === "usuarioNoEncontrado") {
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
