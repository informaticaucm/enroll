<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Enroll - Web Version</title>
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css" />
	
	</head>
	
	<body>
		
		<div class="row">
		
			<div class="col-xs-5">
			
				<div class="row">
				
					<div class="col-xs-12 seccion">
					
						<h4>1º cuatrimestre</h4>
					
						 <table class="table table-bordered table-striped">
						    <thead>
						        <tr>
						        	<th>Hora</th>
						            <th>Lunes</th>
						            <th>Martes</th>
						            <th>Miércoles</th>
						            <th>Jueves</th>
						            <th>Viernes</th>
						        </tr>
						    </thead>
						    <tbody>
						    	
						    	<c:forEach var="hora"  begin="9" end="20">
								    <tr id="1${hora}">
							            <td class="hora">${hora}</td>
							            <td class="L"></td>
							            <td class="M"></td>
							            <td class="X"></td>
							            <td class="J"></td>
							            <td class="V"></td>
							        </tr>
								</c:forEach>						    
						    
						    </tbody>
						</table>
						
					</div>
				
				</div>
			
				<div class="row">
				
					<div class="col-xs-12 seccion">
					
						<h4>2º cuatrimestre</h4>
					
					 	<table class="table table-bordered table-striped">
						    <thead>
						        <tr>
						        	<th>Hora</th>
						            <th>Lunes</th>
						            <th>Martes</th>
						            <th>Miércoles</th>
						            <th>Jueves</th>
						            <th>Viernes</th>
						        </tr>
						    </thead>
						    <tbody>
						    	
						    	<c:forEach var="hora"  begin="9" end="20">
								    <tr id="2${hora}">
							            <td class="hora">${hora}</td>
							            <td class="L"></td>
							            <td class="M"></td>
							            <td class="X"></td>
							            <td class="J"></td>
							            <td class="V"></td>
							        </tr>
								</c:forEach>						    
						    
						    </tbody>
						</table>
					
					</div>
				
				</div>			
			
			
			</div>		
				
			<div class="col-xs-7">
			
				<div id="selecItinerario" class="row">
				
					<div class="col-xs-12 seccion">
					
						<h4>Itinerario</h4>
						
						<label class="radio-inline"><input type="radio" name="itinerario" value="I">Ciencias de la Información</label>
						<label class="radio-inline"><input type="radio" name="itinerario" value="C">Tecnología de la computación</label>
					
					</div>
				
				</div>
			
				<div id="contenedor" class="row">
				
					<div class="col-xs-4 seccion">
						
						<h4>Curso</h4>
					
						<ul id="curso">
							<c:forEach var="curso" items="${cursos}">
							    <li>${curso}</li>
							</c:forEach>
						</ul>					
					
					</div>
					
					<div class="col-xs-4 seccion">					
					
						<h4>Grupo</h4>
					
						<ul id="grupo">
<!-- 							contenido añadido con JQuery -->
						</ul>	
					
					</div>
					
					<div class="col-xs-4 seccion">
					
						<h4>Asignaturas</h4>
					
						<ul id="asignatura">
<!-- 							contenido añadido con JQuery -->
						</ul>	
					
					</div>
				
				</div>	
				
				<div id="botonera" class="row text-center seccion">
					
					<button id="btnAniadir" type="button" class="btn btn-default">Añadir</button>
					<button id="btnEliminar" type="button" class="btn btn-default">Eliminar</button>
					<button id="btnEliminarTodos" type="button" class="btn btn-default">Eliminar todas</button>
					<button id="btnExportar" type="button" class="btn btn-default">Exportar calendario</button>
			 
				</div>	
				
			</div>
		
		</div>		

	</body>

	<!-- jQuery 2.1.4 -->
	<script src="${pageContext.request.contextPath}/resources/js/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<!-- Slimscroll -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.slimscroll.min.js"></script>
	<!-- Nuestro js -->
	<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>

</html>
