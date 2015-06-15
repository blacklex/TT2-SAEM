<%-- 
    Document   : mapaPaciente
    Created on : 17/05/2015, 05:49:43 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<div style="position: fixed; z-index: 1; width: 100%; display: none;"  id="barraCargarPaciente" class="progress progress-sm active">
    <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
        <span class="sr-only">100% Complete (success)</span>
    </div>
</div>

<div id="divAlertSuccessPaciente" style="display: none;"  class="alert alert-success alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertSuccessPaciente"></h4>
    <label id="labelMensajeSuccessPaciente"></label>
</div>

<div id="divAlertErrorPaciente" style="display: none;"  class="alert alert-error alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertErrorPaciente"></h4>
    <label id="labelMensajeErrorPaciente"></label>
</div>

<div id="divAlertExcepcionPaciente" style="display: none;"  class="alert alert-warning alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4 id="tituloDivAlertExcepcionPaciente"></h4>
    <label id="labelMensajeExcepcionPaciente"></label>
</div>

<section class="content">
    <div class="box box-primary">
        <div class="box-header">
            <h3 class="box-header"><b>¿Dondé me encuentro?</b></h3>
        </div><!-- /.box-header -->
        <div class="box-body">
            <div class="info-box bg-aqua">
                <span class="info-box-icon">
                    <i class="fa fa-flag-o">
                    </i>
                </span>
                <div id="info" class="info-box-content">
                    
                </div>
            </div>
            <!-- /google maps -->
            <input type="hidden" value="<%out.print(session.getAttribute("nombreUsuario").toString());%>" id="nombreUsuario"/>
            <div id="map_canvas"></div>
            <!-- /fin google maps -->           
        </div><!-- /.box-body -->
    </div>

<!--            <div id="modalFormPeticionAceptada" class="modal modal-success">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header">
                      <button class="close" aria-label="Close" data-dismiss="modal" onclick="cerrarModalFormPeticionAceptada();" type="button">
                          <span aria-hidden="true">×</span>
                      </button>
                      <h4 class="modal-title">Petición Atendida</h4>
                  </div>
                  <div class="modal-body">
                      <p>Su petición fue atendida... permanece donde estas, pronto llegara ayuda por ti!!!</p>
                  </div>
                  <div class="modal-footer">
                      <button class="btn btn-outline" onclick="cerrarModalFormPeticionAceptada();" type="button">Aceptar</button>
                  </div>
              </div>
          </div>
      </div>
      
      <div id="modalFormPeticionRechazada" class="modal modal-danger">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" aria-label="Close" data-dismiss="modal" onclick="cerrarModalFormPeticionRechazada();" type="button">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title">Petición Rechazada</h4>
                </div>
                <div class="modal-body">
                    <p>Su petición fue rechazada... por favor intente en otro hospital!!!</p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-outline" onclick="cerrarModalFormPeticionRechazada();" type="button">Aceptar</button>
                </div>
            </div>
        </div>
      </div>
      
      <div id="modalFormPeticionNoAtendida" class="modal modal-warning">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header">
                      <button class="close" aria-label="Close" data-dismiss="modal" onclick="cerrarModalFormPeticionNoAtendida();" type="button">
                          <span aria-hidden="true">×</span>
                      </button>
                      <h4 class="modal-title">Petición No Atendida</h4>
                  </div>
                  <div class="modal-body">
                      <p>Su petición no fue atendida... intentelo nuevamente!!!</p>
                  </div>
                  <div class="modal-footer">
                      <button class="btn btn-outline" onclick="cerrarModalFormPeticionNoAtendida();" type="button">Aceptar</button>
                  </div>
              </div>
          </div>
      </div>
      
      <div id="modalFormPeticionPendiente" class="modal modal-info">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header">
                      <button class="close" aria-label="Close" data-dismiss="modal" onclick="cerrarModalFormPeticionPendiente();" type="button">
                          <span aria-hidden="true">×</span>
                      </button>
                      <h4 class="modal-title">Petición No Atendida</h4>
                  </div>
                  <div class="modal-body">
                      <p>Su petición pendiente... en breve se le dara una respuesta!!!</p>
                  </div>
                  <div class="modal-footer">
                      <button class="btn btn-outline" onclick="cerrarModalFormPeticionPendiente();" type="button">Aceptar</button>
                  </div>
              </div>
          </div>
      </div>-->
</section>

            

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDZqR4-rCjdcnt_0aIR_C4CB7B5BNmeLI&v=3.exp&signed_in=true"></script>
<script src="js/funcionesPaciente/funcionesGoogleMapsUbicacionPaciente.js" type="text/javascript"></script>
<script src="js/funcionesPaciente/funcionesEnviarAlerta.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/estiloInfoWindow.css"/>
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700|Open+Sans+Condensed:700,300,300italic|Open+Sans:400,300italic,400italic,600,600italic,700,700italic,800,800italic|PT+Sans:400,400italic,700,700italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'/>