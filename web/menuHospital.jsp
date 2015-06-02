<%-- 
    Document   : menu
    Created on : 05-abr-2015, 3:10:15
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    setInterval(function() {
    $("#small").remove();
    //$("#notifiacionPeticiones").append("<small id='small' class='label pull-right bg-green'>Hot</small>");
    }, 1000 * 60 * .1);
    
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
              <p>Alexander Pierce</p>

              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
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
            
           
             <!--  INICIA MENU PACIENTES CRUD    -->
            <li class="treeview">
              <a href="#">
                <i class="fa fa-users"></i> <span>Pacientes</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pantallaAltaPaciente"><i class="fa fa-pencil"></i> Registrar</a></li>
                <li><a href="pantallaModificarPaciente"><i class="fa fa-edit"></i> Modificar / Eliminar</a></li>
                <li><a href="pantallaConsultarPacientes"><i class="fa fa-eye"></i> Consultar</a></li>
              
              </ul>
            </li>
             <!--  FIN MENU PACIENTES CRUD    -->
             
            <!--  INCIA MENU PETICIONES ENTRANTES    -->
            <li>
                <a id="notifiacionPeticiones" href="pantallaPeticionesEntrantesHospital">
                <i class="fa fa-exchange"></i> <span>Peticiones Entrantes</span> <!--<small class="label pull-right bg-green">Hot</small>-->
              </a>
            </li> 
             <!--  FIN MENU PETICIONES ENTRANTES    -->
             
             <!--  INCIA MENU RESPONDER PETICIONES     -->
            <li>
              <a href="pantallaPeticionesExternasHospital">
                <i class="fa fa-mail-reply"></i> <span>Peticiones Externas</span> <!--<small class="label pull-right bg-green">Hot</small>-->
              </a>
            </li> 
             <!--  FIN MENU RESPONDER PETICIONES     -->
             
             
             
              <!--  INCIA MENU CONSULTAR INFORMACION DEL HOSPITAL    -->
            <li>
              <a href="pantallaConsultarInformacionHospital">
                <i class="fa fa-hospital-o"></i> <span>Informacion Del Hospital</span> <!--<small class="label pull-right bg-green">Hot</small>-->
              </a>
            </li> 
             <!--  FIN MENU CONSULTAR INFORMACION DEL HOSPITAL    -->
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- =============================================== -->
