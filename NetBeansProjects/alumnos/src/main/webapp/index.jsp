<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head> 
        <meta charset="utf-8">
        <title>Alumnos</title>
        <link rel="stylesheet" href="css/vista.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <style>
            .carousel-inner > .item > img {
                width: 33%;
                margin: auto;
            }
        </style>
    </head>	
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img alt="Logo" src="imagen/humo.png" style="width: 40px; height: 40px;">
                    </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Inicio</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Facultades <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:forEach var="facu" items="${facultades}">
                                    <li><a href="Facultad?codigoFacu=${facu.getIdfacultad()}">${facu.getNombre()}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li><a href="#">Docentes</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Alumnos <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="SolicitarDatosAlumno">Solicitar Datos Alumno</a></li>
                                <li><a href="Listar">Listar</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Consultas <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="Listar">Listar Alumnos</a></li>
                                <li><a href="ListarCarreras">Listar Carreras</a></li>
                                <li><a href="ListarMaterias">Listar Materias</a></li>
                                <li><a href="ListarAlumnosMaterias">Listar Alumnos con Materias</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Solicitar Datos <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="SolicitarDatosAlumno">Solicitar Datos Alumno</a></li>
                                <li><a href="SolicitarDatosMateria">Solicitar Datos Materia</a></li>
                                <li><a href="SolicitarDatosCarrera">Solicitar Datos Carrera</a></li>
                                <li><a href="SolicitarDatosFacultad">Solicitar Datos Facultad</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Docentes <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <c:forEach var="docente" items="${docentes}">
                                    <li><a href="Docente?codigoDocen=${docente.getIddocente()}">${docente.getNombre()}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li><a href="SolicitarDatos">AlumnoMateria</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container" style="margin-top: 80px;">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="imagen/imagen1.jpg" alt="Imagen 1" style="opacity: 0.6;">
                        <img src="imagen/imagen2.jpg" alt="Imagen 2">
                        <img src="imagen/imagen3.jpg" alt="Imagen 3" style="opacity: 0.6;">
                    </div>
                    <div class="item">
                        <img src="imagen/imagen2.jpg" alt="Imagen 2" style="opacity: 0.6;">
                        <img src="imagen/imagen3.jpg" alt="Imagen 3">
                        <img src="imagen/imagen5.jpg" alt="Imagen 5" style="opacity: 0.6;">
                    </div>
                    <div class="item">
                        <img src="imagen/imagen3.jpg" alt="Imagen 3" style="opacity: 0.6;">
                        <img src="imagen/imagen5.jpg" alt="Imagen 5">
                        <img src="imagen/imagen1.jpg" alt="Imagen 1" style="opacity: 0.6;">
                    </div>
                    <div class="item">
                        <img src="imagen/imagen5.jpg" alt="Imagen 5" style="opacity: 0.6;">
                        <img src="imagen/imagen1.jpg" alt="Imagen 1">
                        <img src="imagen/imagen2.jpg" alt="Imagen 2" style="opacity: 0.6;">
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <footer class="container text-right" style="margin-top: 50px;">
            <p>© 2020 - Catedra Tecnologias Web - <a href="http://www.unsj.edu.ar" target="_blank">unsj</a></p>
        </footer>
    </body>
</html>
