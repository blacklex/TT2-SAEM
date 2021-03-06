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
            
            <!--  INICIA MENU ADMINISTRADORES CRUD    -->
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-adn"></i> <span>Administradores</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="pantallaAltaAdministrador"><i class="fa fa-pencil"></i> Registrar</a></li>
                        <li><a href="pantallaModificarEliminarAdministrador"><i class="fa fa-edit"></i> Modificar / Eliminar</a></li>
                        <li><a href="pantallaConsultarAdministrador"><i class="fa fa-eye"></i> Consultar</a></li>
                    </ul>
                </li>
            <!--  FIN MENU ADMINISTRADORES CRUD    -->
            
            <!--  INICIA MENU HOSPITALES CRUD    -->
            <li class="treeview">
              <a href="#">
                <i class="fa fa-hospital-o"></i> <span>Hospitales</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pantallaRegistroHospital"><i class="fa fa-pencil"></i> Registrar</a></li>
                <li><a href="pantallaModificarEliminarHospital"><i class="fa fa-edit"></i> Modificar / Eliminar</a></li>
                <li><a href="pantallaConsultarHospitales"><i class="fa fa-eye"></i> Consultar</a></li>
                
              </ul>
            </li>
             <!--  FIN MENU HOSPITALES CRUD    -->
             
             <!--  INICIA MENU PACIENTES CRUD    -->
            <li class="treeview">
              <a href="#">
                <i class="fa fa-users"></i> <span>Pacientes</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="pantallaAltaPaciente"><i class="fa fa-pencil"></i> Registrar</a></li>
                <li><a href="pantallaModificarEliminarPaciente"><i class="fa fa-edit"></i> Modificar / Eliminar</a></li>
                <li><a href="pantallaConsultarPacientes"><i class="fa fa-eye"></i> Consultar</a></li>
                
              </ul>
            </li>
             <!--  FIN MENU PACIENTES CRUD    -->
             
           
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- =============================================== -->
