<%-- 
    Document   : menu
    Created on : 05-abr-2015, 3:10:15
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
    $(document).ready(function () {
        consultarPeticionPendienteInicio();
    });
    
    function consultarPeticionPendienteInicio() {
        var nombreUsuario = $("#nombreUsuario").val();
        var idPetIni = localStorage.getItem("idPeticion");
        
        if(idPetIni==="" || idPetIni ===null){
            
            $("#barraCargar").slideDown(100);
            $.ajax({
                dataType: "json",
                method: "POST",
                url: "ajaxObtenerEstatusPeticionInicio",
                data: {nombreUsuario: nombreUsuario}
            }).done(function (msg) {
                var idPeticion = msg.idPeticionSaliente;
            
                if(idPeticion==="" || idPeticion===null) {
                    return;
                }
                else {
                    localStorage.setItem("idPeticion", idPeticion);
                    consultarTotalPeticionesPacientes();
                }
            });
        }else{
            consultarTotalPeticionesPacientes();
        }
}

    function consultarTotalPeticionesPacientes() {
        var idPeticionSaliente = localStorage.getItem("idPeticion");
       //alert(idPeticionSaliente);
		if(idPeticionSaliente === null)
                    return;
		
		

        var nombreUsuario = $("#nombreUsuario").val();
        $("#barraCargar").slideDown(100);
        $.ajax({
            dataType: "json",
            method: "POST",
            url: "ajaxRecuperarTotalPeticionesPacientes",
            data: {nombreUsuario: nombreUsuario, idPeticionS: idPeticionSaliente}
        }).done(function (msg) {
           
            //alert(idPeticionSaliente);
            //if(msg.recuperarEstatus === "PP")
            
            
            $("#barraCargar").slideUp(100);
            $("#smallEnt").remove();
            if (msg.recuperarEstatus === "PA") {
                localStorage.removeItem("idPeticion");
                $("#notifiacionPeticionesAtendidas").append("<small id='smallEnt' class='label pull-right bg-green'>1</small>");
				if(msg.comentario === ""|| msg.comentario === null)
					$("#comentarioPeticionAceptada").html("Petición Atendida.");
				 else
				    $("#comentarioPeticionAceptada").html("Petición Atendida: "+msg.comentario);
                
				$("#modalFormPeticionAceptada").fadeIn('slow');
                
            }
            else {
                $("#notifiacionPeticionesAtendidas").append("<small id='smallEnt' class='label pull-right bg-green'>0</small>");
            }
            $("#smallSal").remove();
            if (msg.recuperarEstatus === "PR") {
				
                 localStorage.removeItem("idPeticion");
                
                $("#notifiacionPeticionesRechazadas").append("<small id='smallSal' class='label pull-right bg-green'>1</small>");
				if(msg.comentario === ""|| msg.comentario === null)
					$("#comentarioPeticionRechazada").html("Petición Rechazada.");
				 else
				    $("#comentarioPeticionRechazada").html("Petición Rechazada: "+msg.comentario);
                $("#modalFormPeticionRechazada").fadeIn('slow');    
                
            }
            else {
                $("#notifiacionPeticionesRechazadas").append("<small id='smallSal' class='label pull-right bg-green'>0</small>");
            }
            $("#smallSal1").remove();
            if (msg.recuperarEstatus === "PNR") {
			
                 localStorage.removeItem("idPeticion");
                 
                
                $("#notifiacionPeticionesNoAtendidas").append("<small id='smallSal1' class='label pull-right bg-green'>1</small>");
				if(msg.comentario === "" || msg.comentario === null)
					$("#comentarioPeticionNoAtendida").html("Petición No Respondida.");
				 else
				    $("#comentarioPeticionNoAtendida").html("Petición No Respondida: "+msg.comentario);
                $("#modalFormPeticionNoAtendida").fadeIn('slow');  
                
            }
            else {
                $("#notifiacionPeticionesNoAtendidas").append("<small id='smallSal1' class='label pull-right bg-green'>0</small>");
            }
            $("#smallSal2").remove();
            if (msg.recuperarEstatus === "PP") {
		
                $("#notifiacionPeticionesPendientes").append("<small id='smallSal2' class='label pull-right bg-green'>1</small>");
                $("#modalFormPeticionPendiente").fadeIn('slow');
                setTimeout(function() {
                    $("#modalFormPeticionPendiente").fadeOut(1500);
                },5000);
                setTimeout(function () {
                    consultarTotalPeticionesPacientes();
                }, 60000);
            }
            
        });
    }
    
</script> 
      <!-- =============================================== -->

      <!-- Left side column. contains the sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
           <!-- <div class="pull-left image">
              <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
            </div>-->
            <div class="pull-left info">
              <p><%out.print(session.getAttribute("nombreUsuario").toString());%></p>
              <input type="hidden" id="nombreUsuario" value="<%out.print(session.getAttribute("nombreUsuario").toString());%>">
            </div>
          </div>
          <!-- search form -->
         <!-- <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search..."/>
              <span class="input-group-btn">
                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
         -->
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">MENU</li>
            
          
            <!--  INICIA MENU HOSPITALES CRUD    -->
            <li>
              <a href="pantallaMapaPaciente">
                <i class="fa fa-hospital-o"></i> <span>Hospitales Cercanos</span> <i></i>
              </a>
<!--              <ul class="treeview-menu">
                  <li><a href="pantallaMapaPaciente"><i class="fa fa-map-marker"></i> Mi ubicación</a></li>
                <li><a href="pantallaEnviarAlerta"><i class="fa fa-edit"></i> Enviar Alerta</a></li>
                <li><a href="pantallaHospitalesCercanos"><i class="fa fa-eye"></i> Hospitales Cercanos</a></li>
              </ul>-->
            </li>
             <!--  FIN MENU HOSPITALES CRUD    -->
             <!--  INCIA MENU PETICIONES ENTRANTES    -->
            <li>
                <a id="notifiacionPeticionesAtendidas" href="#">
                    <i class="fa fa-check"></i> <span>Peticiones Atendida</span> <!--<small class="label pull-right bg-green">Hot</small>-->
                </a>
            </li> 
            <!--  FIN MENU PETICIONES ENTRANTES    -->

            <!--  INCIA MENU RESPONDER PETICIONES     -->
            <li>
                <a id="notifiacionPeticionesRechazadas" href="#">
                    <i class="fa fa-times-circle"></i> <span>Peticiones Rechazada</span> <!--<small class="label pull-right bg-green">Hot</small>-->
                </a>
            </li> 
            <!--  FIN MENU RESPONDER PETICIONES     -->
            <!--  INCIA MENU RESPONDER PETICIONES     -->
            <li>
                <a id="notifiacionPeticionesNoAtendidas" href="#">
                    <i class="fa fa-stop"></i> <span>Peticiones No Atendida</span> <!--<small class="label pull-right bg-green">Hot</small>-->
                </a>
            </li> 
            <!--  FIN MENU RESPONDER PETICIONES     -->
            <!--  INCIA MENU RESPONDER PETICIONES     -->
            <li>
                <a id="notifiacionPeticionesPendientes" href="#">
                    <i class="fa fa-pause"></i> <span>Peticiones Pendiente</span> <!--<small class="label pull-right bg-green">Hot</small>-->
                </a>
            </li> 
            <!--  FIN MENU RESPONDER PETICIONES     -->
            <!--  INCIA MENU CONSULTAR HOSPITALES    -->
            <li>
              <a href="pantallaConsultarMiInformacion">
                <i class="fa fa-info"></i> <span>Mi Información</span> <!--<small class="label pull-right bg-green">Hot</small>-->
              </a>
            </li> 
             <!--  FIN MENU CONSULTAR HOSPITALES    -->
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- =============================================== -->
      <div id="modalFormPeticionAceptada" class="modal modal-success">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header">
                      <button class="close" aria-label="Close" data-dismiss="modal" onclick="cerrarModalFormPeticionAceptada();" type="button">
                          <span aria-hidden="true">×</span>
                      </button>
                      <h4 class="modal-title">Petición Atendida</h4>
                  </div>
                  <div  class="modal-body">
                      <p id="comentarioPeticionAceptada"></p>
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
                    <p id="comentarioPeticionRechazada"></p>
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
                      <p id="comentarioPeticionNoAtendida"></p>
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
                      <h4 class="modal-title">Petición Pendiente</h4>
                  </div>
                  <div class="modal-body">
                      <p>Esperando respuesta del hospital.</p>
                  </div>
                  <div class="modal-footer">
                      <button class="btn btn-outline" onclick="cerrarModalFormPeticionPendiente();" type="button">Aceptar</button>
                  </div>
              </div>
          </div>
      </div>