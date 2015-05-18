<%-- 
    Document   : login
    Created on : 07-abr-2015, 2:49:37
    Author     : Alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SAEM | Iniciar Sesion</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- Bootstrap 3.3.2 -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Font Awesome Icons -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
        <!-- iCheck -->
        <link href="plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script type="text/javascript">
            function validarCampos() {
                $( ".control-label" ).remove();
                if ($("#formNombreUsuario").val() == "" && $("#formClave").val() == "") {
                    $("#divNombreUsuario").addClass("has-error");
                    $("#divClave").addClass("has-error");
                    $("#divNombreUsuario").append("<label id='labelNombreUsuario' class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu Nombre de Usuario.</label>");
                    $("#divClave").append("<label id='labelClave' class='control-label' for='formClave'><i class='fa fa-times-circle-o'></i>  Ingresa tu clave de acceso.</label>");

                    return false;
                }

                if ($("#formNombreUsuario").val() == "") {
                    $("#divNombreUsuario").append("<label id='labelNombreUsuario' class='control-label' for='formNombreUsuario'><i class='fa fa-times-circle-o'></i>  Ingresa tu Nombre de Usuario.</label>");
                    $("#divNombreUsuario").addClass("has-error");
                    $("#divClave").removeClass("has-error");
                    return false;
                }else{
                     $("#divNombreUsuario").removeClass("has-error");
                }
                if ($("#formClave").val() == "") {
                    $("#divClave").append("<label id='labelClave' class='control-label' for='formClave'><i class='fa fa-times-circle-o'></i>  Ingresa tu clave de acceso.</label>");
                    $("#divClave").addClass("has-error");
                    return false;
                }
                

                return true;

            }
        </script>
    </head>
    <body class="login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="../../index2.html"><b>SAE</b>M</a>
            </div><!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">Iniciar Sesión</p>
                <form action="Login" method="post" onsubmit="return validarCampos()">

                    <div id="divNombreUsuario" class="form-group has-feedback">
                        <input  type="text" id="formNombreUsuario" name="formNombreUsuario" class="form-control" placeholder="Nombre Usuario"/>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>


                    <div id="divClave" class="form-group has-feedback">
                        <input name="formClave" id="formClave" type="password" class="form-control" placeholder="Contraseña"/>
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-8">    
                            <div class="checkbox icheck">
                                <label>
                                    <input name="formCheckRecordarme" id="formCheckRecordarme" value="1" type="checkbox"> Recordarme
                                </label>
                            </div>                        
                        </div><!-- /.col -->
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Iniciar</button>
                        </div><!-- /.col -->
                    </div>
                </form>

                <div class="social-auth-links text-center">
                    <p>- O -</p>
                    <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Iniciar sesión por Facebook</a>
                </div><!-- /.social-auth-links -->

                <!--<a href="#">I forgot my password</a><br>
                <a href="register.html" class="text-center">Register a new membership</a>
                -->
            </div><!-- /.login-box-body -->
        </div><!-- /.login-box -->

        <!-- jQuery 2.1.3 -->
        <script src="plugins/jQuery/jQuery-2.1.3.min.js"></script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        <script>
                    $(function () {
                        $('input').iCheck({
                            checkboxClass: 'icheckbox_square-blue',
                            radioClass: 'iradio_square-blue',
                            increaseArea: '20%' // optional
                        });
                    });
        </script>
    </body>
</html>
