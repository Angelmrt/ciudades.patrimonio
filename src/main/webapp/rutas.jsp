<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <title>City</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
    <style>
    	.rating a {
			text-decoration: none;
			font-size: 32px;
			color: #888;
		}
		.rating:hover a {
		color: gold;
		}
		.rating a:hover ~ a {
		color: #888;
		}
    </style>
</head>

<body>
    <header class="bg-primary py-3">
        <div class="container">
            <div class="row align-items-center justify-content-between">
                <div class="col-lg-6">
                    <img src="img/logo.png" alt="Logo" class="img-fluid" />
                </div>
                <div class="col-lg-6 text-end">
                    <div class="d-flex justify-content-end align-items-center gap-3">
                        <a href="Controller?operacion=iniciar" class="text-decoration-none text-white">Ciudades</a>
                         <c:if test="${not empty listaciudades}">
                       		 <form action="Controller?operacion=getruta" method="post">
                      			 <select name="ciudad" id="ciudad" onchange="this.form.submit()">
                      			 	<option value="todas">Todas</option>
                           			<c:forEach var="ciudad" items="${listaciudades}">
                            			<option value="${ciudad.id}">
                      						<c:out value="${ciudad.nombre}"></c:out>      
                            			</option>>
                            		</c:forEach>
                        		</select>
                        	</form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <main>
        <div class="container mt-4">
            <div class="row justify-content-center">
                <c:if test="${not empty listarutas}">
                    <c:forEach var="ruta" items="${listarutas}">
                        <div class="col-lg-6 col-md-12 col-sm-12 col-12 d-flex align-items-stretch mt-4">
                            <div class="card h-100">
                            	<a class="text-decoration-none text-dark" href="${ruta.link}">
                                	<img class="card-img-top" src="${ruta.imagen}" alt="Imagen de ${ruta.nombre}" />
                                </a>
                                <div class="card-body d-flex flex-column">
                                    <h4 class="card-title">
                                        <c:out value="${ruta.nombre}" />
                                    </h4>
                                    <p class="card-text flex-grow-1">
                                        <c:out value="${ruta.descripcion}" />
                                    </p>
                                    <div>
                                    	 <c:forEach begin="1" end="${ruta.mediaptos}">
                                    	 	<span class="rating">&#9733;</span>
                                    	 </c:forEach>
                                    	
                                    </div>
                                    <div>
                                    	<span class="rating">
										<a href="Controller?operacion=rating&rating=1&rutaid=${ruta.id}">&#9733;</a>
										<a href="Controller?operacion=rating&rating=2&rutaid=${ruta.id}">&#9733;</a>
										<a href="Controller?operacion=rating&rating=3&rutaid=${ruta.id}">&#9733;</a>
										<a href="Controller?operacion=rating&rating=4&rutaid=${ruta.id}">&#9733;</a>
										<a href="Controller?operacion=rating&rating=5&rutaid=${ruta.id}">&#9733;</a>
										</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </main>

    <footer>
        <!-- place footer here -->
    </footer>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQ+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>

</html>