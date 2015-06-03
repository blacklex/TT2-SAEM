<%-- 
    Document   : menu
    Created on : 05-abr-2015, 3:10:15
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <li class="treeview">
              <a href="#">
                <i class="fa fa-hospital-o"></i> <span>Hospitales</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                  <li><a href="pantallaMapaPaciente"><i class="fa fa-map-marker"></i> Mi ubicación</a></li>
                <li><a href="pantallaEnviarAlerta"><i class="fa fa-edit"></i> Enviar Alerta</a></li>
                <li><a href="pantallaHospitalesCercanos"><i class="fa fa-eye"></i> Hospitales Cercanos</a></li>
              </ul>
            </li>
             <!--  FIN MENU HOSPITALES CRUD    -->

            <!--  INCIA MENU CONSULTAR HOSPITALES    -->
            <li>
              <a href="pantallaConsultarMiInformacion">
                <i class="fa fa-info"></i> <span>Mi Informacion</span> <!--<small class="label pull-right bg-green">Hot</small>-->
              </a>
            </li> 
             <!--  FIN MENU CONSULTAR HOSPITALES    -->
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- =============================================== -->
