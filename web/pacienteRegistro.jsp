<%-- 
    Document   : pacienteRegistro
    Created on : 23/05/2015, 02:45:35 PM
    Author     : gabriela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="position: fixed; z-index: 1; width: 100%; display: none;"  id="barraCargarPaciente" class="progress progress-sm active">
    <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
        <span class="sr-only">100% Complete (success)</span>
    </div>
</div>

<div id="divAlertSuccess" style="display: none;"  class="alert alert-success alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertSuccessPaciente"></h4>
    <label id="labelMensajeSuccessPaciente"></label>
</div>

<div id="divAlertError" style="display: none;"  class="alert alert-error alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertErrorPaciente"></h4>
    <label id="labelMensajeErrorPaciente"></label>
</div>

<div id="divAlertPaciente" style="display: none;" class="alert alert-warning alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="textoAlertPaciente"><i class="icon fa fa-warning"></i>Campos no validos.</h4>
    Verifique que los datos que ha ingresado sean correctos.
</div>

<section class="content">

    <div class="box box-primary">
        <div class="box-header">
            <h3 class="box-header"><b>Registro de Paciente</b></h3>
        </div><!-- /.box-header -->
        <!-- form start -->
        <s:form enctype="multipart/form-data" name="formNuevoPaciente" action="registrarPaciente" onsubmit="return validarCampos();"  method="post" role="form">
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos de inicio de sesión</h3>
                </div>

                <div id="divNombreUsuario" class="form-group">
                    <label for="nombreUsuario">Nombre de usuario</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreUsuario" id="nombreUsuario" placeholder="Nombre de usuario" type="text" autofocus="true">
                </div>
                <div id="divClaveUsuario" class="form-group">
                    <label for="claveUsuario">Clave de Acceso</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" id="clave" name="clave" placeholder="Clave de Acceso" type="password">
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos</h3>
                </div>
                
                <div id="divNss" class="form-group">
                    <label for="nss">NSS</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nss" id="nss" placeholder="NSS" type="text">
                </div>

                <div id="divNombre" class="form-group">
                    <label for="nombre">Nombre(s)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombre" id="nombre" placeholder="Nombre(s)" type="text">
                </div>
                
                <div id="divApellidoPaterno" class="form-group">
                    <label for="apellidoPaterno">Apellido Paterno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido Paterno" type="text">
                </div>
                
                <div id="divApellidoMaterno" class="form-group">
                    <label for="apellidoMaterno">Apellido Materno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido Paterno" type="text">
                </div>

                <div id="divUniMedicaPaciente" class="form-group">
                    <label for="unidadMedica">Unidad Médica</label>
                    <input kl_virtual_keyboard_secure_input="on" name="unidadMedica" id="unidadMedica" class="form-control" placeholder="Unidad Medica" type="text">
                </div>

                <div id="divNoConsultorioPaciente" class="form-group">
                    <label for="noConsultorio">No Consultorio</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="noConsultorio" id="noConsultorio" placeholder="No. de Consultorio" type="text">
                </div>
                
                <div id="divImagenPaciente" class="form-group">
                    <label for="imagen">Imagen</label>
                    <input multiple=false data-preview-file-type="any" name="imagen" id="file-1" class="file" placeholder="Imagen" type="file">
                </div>
            </div><!-- /.box-body -->

            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Domicilio</h3>
                </div>

                <div id="divCallePaciente" class="form-group">
                    <label for="calle">Calle y No.</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="calle" id="calle" placeholder="Calle" type="text">
                </div>

                <div id="divColoniaPaciente" class="form-group">
                    <label for="colonia">Colonia</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="colonia" id="colonia" placeholder="Colonia" type="text">
                </div>

                <div id="divDelegacionPaciente" class="form-group">
                    <label for="delegacion">Delegación</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="delegacion" id="delegacion" placeholder="Delegación" type="text">
                </div>

                <div id="divEntidadFederativaPaciente" class="form-group">
                    <label for="entidadFederativa">Entidad Federativa</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="entidadFederativa" id="entidadFederativa" placeholder="Entidad Federativa" type="text">
                </div>

                <div id="divCodigoPostalPaciente" class="form-group">
                     <label for="codigoPostal">Código Postal</label>
                    <input kl_virtual_keyboard_secure_input="on" name="codigoPostal" id="codigoPostal" class="form-control" data-inputmask="&quot;mask&quot;: &quot;99999&quot;" data-mask="" placeholder="Codigo Postal" type="text">
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Teléfonos del Paciente</h3>
                </div>

                <div id="divTelefonoFijoPaciente" class="form-group">
                    <label for="telefonoPaciente">Telefono Fijo</label>
                    <input kl_virtual_keyboard_secure_input="on" name="numTelefono" id="numTelefono" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="No. Telefono" type="text">
                </div>
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Datos Personales del Paciente</h3>
                </div>

                <div id="divEdoCivilPaciente" class="form-group">
                    <label for="estadoCivil">Estado Civil</label>
                    <div class="row">
                    <div class="col-lg-6">
                      <div class="input-group">
                        <div class="radio">
                            <label>
                              <input type="radio" name="estadoCivil" id="radioCasado" value="casado" />
                              Casado(a)
                            </label>
                        </div>
                    </div><!-- /.col-lg-6 -->
                    </div>
                        <div class="col-lg-6">
                        <div class="input-group">
                            <div class="radio">
                            <label>
                              <input type="radio" name="estadoCivil" id="radioUnionLibre" value="union libre" />
                              Unión libre
                            </label>
                        </div>
                        </div>
                </div>
                    <div class="col-lg-6">
                        <div class="input-group">
                            <div class="radio">
                            <label>
                              <input type="radio" name="estadoCivil" id="radioSoltero" value="soltero" />
                              Soltero(a)
                            </label>
                        </div>
                        </div>
                </div>
                        <div class="col-lg-6">
                        <div class="input-group">
                            <div class="radio">
                            <label>
                              <input type="radio" name="estadoCivil" id="radioDivorciado" value="divorciado" />
                              Divorsiado(a)
                            </label>
                        </div>
                        </div>
                </div>
                
                  </div>
                </div>

                <div id="divCurpPaciente" class="form-group">
                    <label for="curp">CURP</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="curp" id="curp" placeholder="CURP" type="text">
                </div>

                <div id="divSexoPaciente" class="form-group">
                    <label for="sexo">Sexo</label>
                    
            
                <div class="row">
                    <div class="col-lg-6">
                      <div class="input-group">
                        <div class="radio">
                            <label>
                              <input type="radio" name="sexo" id="radioMasculino" value="masculino" />
                              Masculino
                            </label>
                        </div>
                    </div><!-- /.col-lg-6 -->
                    </div>
                    <div class="col-lg-6">
                        <div class="input-group">
                            <div class="radio">
                            <label>
                              <input type="radio" name="sexo" id="radioFemenino" value="femenino" />
                              Femenino
                            </label>
                        </div>
                        </div>
                </div>
                
                  </div>
                </div>

                <div id="divFechaNacimientoPaciente" class="form-group">
                    <label for="fechaNacimiento">Fecha de Nacimiento</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="fechaNacimiento" id="fechaNacimiento" placeholder="Fecha de Nacimiento" type="date">
                </div>

                <div id="divEdadPaciente" class="form-group">
                    <label for="edad">Edad</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="edad" id="edad" placeholder="Edad" type="text">
                </div>

                <div id="divPesoPaciente" class="form-group">
                    <label for="peso">Peso (Kg)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="peso" id="peso" placeholder="Peso" type="text">
                </div>

                <div id="divAlturaPaciente" class="form-group">
                    <label for="altura">Altura (metros)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="altura" id="altura" placeholder="Altura" type="text">
                </div>

                <div id="divTallaPaciente" class="form-group">
                    <label for="talla">Talla </label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="talla" id="talla" placeholder="Talla" type="text">
                </div>

                <div id="divCorreoPaciente" class="form-group">
                    <label for="correo">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correo" id="correo" placeholder="Email" type="email" required="true">
                </div>

                <div id="divFacebookPaciente" class="form-group">
                    <label for="facebook">Facebook (www.facebook.com/alguien)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="facebook" id="facebook" placeholder="Facebook" type="text">
                </div>
            
                <div class="box-body">
                    <div class="box-header">
                        <h3 class="box-title">Datos Clinicos</h3>
                    </div>
                    <div id="divDrograsPaciente" class="form-group">
                        <label for="sexo">Uso de Drogas?</label>
                        
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="drogas" id="radioDrogasSi" value="1" >
                                            Si
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="drogas" id="radioDrogasNo" value="0" >
                                        No
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                           
                    <div id="divAlcoholPaciente" class="form-group">
                        <label for="sexo">Uso de Alcohol?</label>
                        
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="alcohol" id="radioAlcoholSi" value="1" >
                                            Si
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="alcohol" id="radioAlcoholNo" value="0" >
                                            No
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div id="divFumaPaciente" class="form-group">
                        <label for="sexo">Fuma?</label>
                    
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="fuma" id="radioFumaSi" value="1" >
                                            Si
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="fuma" id="radioFumaNo" value="0" >
                                            No
                                        </label>
                                    </div>
                                </div>
                            </div>    
                        </div><!-- /.col-lg-6 -->
                    </div><!-- /.box-body -->
                </div>
                <!--***************************************Alergias***********************************************-->
                <div class="box-header">
                    <h2 class="box-title">Alergias</h2>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxPolen" value="polen">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Polen"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <textarea class="form-control" name="especificarPolen" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxAcaros" value="acaros">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Ácaros del polvo">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <textarea class="form-control" name="especificarAcaros" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxAnimales" value="animales">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Animales">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <textarea class="form-control" name="especificarAnimales" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxMedicamentos" value="medicamentos">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Medicamentos">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <textarea class="form-control" name="especificarMedicamentos" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxInsectos" value="insectos">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Picadura de insectos">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <textarea class="form-control" name="especificarInsectos" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxAlimentos" value="alimentos">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Alimentos">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <textarea class="form-control" name="especificarAlimentos" placeholder="Especificar ..." rows="3" style="width: 485px; height: 50px;"></textarea>
                        </div><!-- /input-group -->
                    </div>
                </div>
                <!--***************************************Cirugias***********************************************-->
                <div class="box-header">
                    <h2 class="box-title">Cirugías</h2>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxInterna" value="interna">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Interna"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxExterna" value="externa">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Externa">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxMayor" value="mayor">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Mayor">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxMenor" value="menor">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Menor">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxCurativa" value="curativa">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Curativa">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxReparadora" value="reparadora">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Reparadora">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxPaliativa" value="paliativa">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Paliativa">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxCosmetica" value="cosmetica">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Cosmetica">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            
                            <input class="form-control" type="number" min="0" value="" placeholder="no. de cirugias"/>
                        </div><!-- /input-group -->
                    </div>
                </div>
                <!--***************************************Discapacidades***********************************************-->
                <div class="box-header">
                    <h2 class="box-title">Discapacidades</h2>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxFisica" value="fisica">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Fisica"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxIntelectual" value="intelectual">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Intelectual">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxPsiquica" value="psiquica">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Psíquica">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxVisual" value="visual">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Visual">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxAuditiva" value="auditiva">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Auditiva">
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <span class="input-group-addon">
                                <input type="checkbox" name="checkboxHabla" value="habla">
                            </span>
                            <input disabled="true" class="form-control" type="text" value="Habla">
                        </div><!-- /input-group -->
                    </div>
                </div>
                <!--***************************************Medicacion***********************************************-->
                <div class="box-header">
                    <h2 class="box-title">Medicación</h2>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <label>Nombre del medicamento</label>
                            <input class="form-control" type="text" value="" placeholder="Medicamentó"/>
                        </div><!-- /input-group -->
                    </div>
                    <div class="col-lg-6">
                        <div style="margin-bottom:10px;" class="input-group">
                            <label>Frecuencia</label>
                            <input class="form-control" type="text" value="" placeholder="Frecuencia"/>
                        </div><!-- /input-group -->
                    </div>
                </div>
                <!--***************************************Enfermedades Cronicas***********************************************-->
                <div class="box-header">
                    <h2 class="box-title">Enfermedades Crónicas</h2>
                </div>
                <div class="col-lg-6">
                    <div style="margin-bottom:10px;" class="input-group">
                        <label>Nombre enfermedad</label>
                        <input class="form-control" name="enfermedadCronica" type="text" />
                    </div><!-- /input-group -->
                </div>
                
                <div class="col-lg-6">
                    <div style="margin-bottom:10px;" class="input-group">
                        <label>Tipo</label>
                        <select name="tipoEnfermedad" class="form-control">
                            <option value="-1">Seleccionar</option>
                            <option value="diabetes">Diabetes</option>
                            <option value="cardiovascular">Enfermedades cardiovasculares</option>
                            <option value="obesidad">Obesidad</option>
                            <option value="cancer">Cáncer</option>
                            <option value="dislipidemias">Dislipidemias</option>
                        </select>
                    </div>
                </div>
                
                
                <div class="col-lg-6">
                    <div style="margin-bottom:10px;" class="input-group">
                        <label>Inicio de enfermedad</label>
                        <input class="form-control" name="inicioEnfermedad" type="date" />
                    </div><!-- /input-group -->
                </div>
                
            </div><!-- /.box-body -->
            
            <div class="box-body">
                <div class="box-header">
                    <h3 class="box-title">Contactos del Paciente</h3>
                </div>
                
                <div id="divNombreCPaciente" class="form-group">
                    <label for="nombreC">Nombre</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="nombreC" id="nombreC" placeholder="Nombre" type="text">
                </div>

                <div id="divApellidoPaternoCPaciente" class="form-group">
                    <label for="apellidoPaternoC">Apellido Paterno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoPaternoC" id="apellidoPaternoC" placeholder="Apellido Paterno" type="text">
                </div>

                <div id="divApellidoMaternoCPaciente" class="form-group">
                    <label for="apellidoMaternoC">Apellido Materno</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="apellidoMaternoC" id="apellidoMaternoC" placeholder="Apellido Materno" type="text">
                </div>

                <div id="divParentescoCPaciente" class="form-group">
                    <label for="parentesco">Parentesco</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="parentesco" id="parentesco" placeholder="Parentesco" type="text">
                </div>

                <div id="divCelularCPaciente" class="form-group">
                    <label for="celular">Telefono Celular</label>
                    <input kl_virtual_keyboard_secure_input="on" name="celular" id="celular" class="form-control" data-inputmask="&quot;mask&quot;: &quot;(99-99) 9999-9999&quot;" data-mask="" placeholder="Celular" type="text">
                </div>

                <div id="divFacebookCPaciente" class="form-group">
                    <label for="facebookC">Facebook (www.facebook.com/alguien)</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="facebookC" id="facebookC" placeholder="Facebook" type="text">
                </div>

                <div id="divCorreoCPaciente" class="form-group">
                    <label for="correoC">Email</label>
                    <input kl_virtual_keyboard_secure_input="on" class="form-control" name="correoC" id="correoC" placeholder="Email" type="text">
                </div>
            </div><!-- /.box-body -->

            <div class="box-footer">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </s:form>
    </div>
</section>

<!-- InputMask -->
<script src="plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
<script src="bootstrap/js/fileinput.min.js" type="text/javascript"></script>
<link href="bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<!--Script creados -->
<script src="js/funcionesPaciente/funcionesRegistroPaciente.js" type="text/javascript"></script>
