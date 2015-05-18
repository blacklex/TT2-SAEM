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
              <p>Usuario</p>

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
            
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="grid"><i class="fa fa-circle-o"></i> Jgrid</a></li>
                <li><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
              </ul>
            </li>
            <!--  INICIA MENU HOSPITALES CRUD    -->
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>Hospitales</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pantallaAltaHospital"><i class="fa fa-circle-o"></i> Registrar</a></li>
                <li><a href="pantallaModificarHospital"><i class="fa fa-circle-o"></i> Modificar / Eliminar</a></li>
              </ul>
            </li>
             <!--  FIN MENU HOSPITALES CRUD    -->
             
             <!--  INICIA MENU PACIENTES CRUD    -->
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>Pacientes</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pantallaAltaPaciente"><i class="fa fa-circle-o"></i> Registrar</a></li>
                <li><a href="pantallaModificarPaciente"><i class="fa fa-circle-o"></i> Modificar / Eliminar</a></li>
              </ul>
            </li>
             <!--  FIN MENU PACIENTES CRUD    -->
             
            <!--  INCIA MENU CONSULTAR HOSPITALES    -->
            <li>
              <a href="pantallaConsultarHospitales">
                <i class="fa fa-th"></i> <span>Consultar Hospitales</span> <!--<small class="label pull-right bg-green">Hot</small>-->
              </a>
            </li> 
             <!--  FIN MENU CONSULTAR HOSPITALES    -->
             
             <!--  INCIA MENU CONSULTAR PACIENTES    -->
            <li>
              <a href="pantallaConsultarPacientes">
                <i class="fa fa-th"></i> <span>Consultar Pacientes</span> <!--<small class="label pull-right bg-green">Hot</small>-->
              </a>
            </li> 
             <!--  FIN MENU CONSULTAR PACIENTES    -->
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- =============================================== -->
