<%-- 
    Document   : baseLayout
    Created on : 05-abr-2015, 1:57:45
    Author     : Alejandro
--%>
<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="true" jquerytheme="redmond" />

<html>
  <head>
    <meta charset="UTF-8">
    <title>SAEM</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="skin-blue wysihtml5-supported fixed">
      <!--<body class="skin-blue"> -->
      
    <!-- Site wrapper -->
    <div class="wrapper">
      
     <tiles:insertAttribute name="header" />
     
     <tiles:insertAttribute name="menu" />

      <!-- Right side column. Contains the navbar and content of the page -->
      <div class="content-wrapper">
       <tiles:insertAttribute name="body" />
      </div><!-- /.content-wrapper -->

     <tiles:insertAttribute name="footer" />
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.3
    <script src="plugins/jQuery/jQuery-2.1.3.min.js"></script>-->
    <!-- Bootstrap 3.3.2 JS -->
    <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- SlimScroll -->
    <script src="plugins/slimScroll/jquery.slimScroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="dist/js/app.min.js" type="text/javascript"></script>
    <!-- AdminLTE for demo purposes
    <script src="dist/js/demo.js" type="text/javascript"></script> -->
    
    
  </body>
</html>