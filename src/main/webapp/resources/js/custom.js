
$(function() {
	
	var $cuadranteCurso = $("#curso");
	var $cuadranteGrupos = $("#grupo");
	var $cuadranteAsignatura = $("#asignatura");
	var itinerario;
	var curso;
	var grupo;
	var asignatura;
		
	var $itinerario = $("input[name=itinerario]");
	var $btnAniadir = $("#btnAniadir");
	var $btnEliminar = $("#btnEliminar");
	var $btnElimiarTodos = $("#btnEliminarTodos");
	var $btnExportar = $("#btnExportar");
	
	var hayConflicto = false;
	
	//ocultamos los cursos hasta que se selecciona un itinerario
	
	limpiarVista();
	limpiarJava();
	
	function inicializarBotones(){
		$btnAniadir.attr("disabled",true);
		$btnEliminar.attr("disabled",true);
	}
	
	function limpiarVista(){
		$cuadranteCurso.hide();
		$cuadranteGrupos.empty();
		$cuadranteAsignatura.empty();
		inicializarBotones();
	}
	
	function limpiarJava(){
		$.ajax({
			url: "limpiarTodoHorario",
			method: "PUT",			
		});	
	}
	
	function limpiarTabla(){
		$("table td.conflicto").removeClass("conflicto");
		$("table td:not(.hora)").html("");
	}
	
	//cuando el itinerario cambia se muestran los cursos en el caso que esten ocultos
	//se resetea los grupos y asignaturas
	//se bloquean los botones
	$itinerario.change(function(){
		limpiarVista();
		$cuadranteCurso.show();		
		$("#curso li.active").removeClass("active");
		itinerario = $(this).val();
	});	
	
	$(document).on('click', '#curso li', function(){
		var $this = $(this);
		curso = $this.html();
		cargarGruposCurso();	
		
		$cuadranteAsignatura.empty();
		inicializarBotones();
		
		$("#curso li.active").removeClass("active");
		$this.addClass("active");
	});
	
	$(document).on('click', '#grupo li', function(){		
		var $this = $(this);
		grupo = $this.html();
		cargarAsignaturasGrupo();	
		
		inicializarBotones();
		
		$("#grupo li.active").removeClass("active");
		$this.addClass("active");
	});
	
	$(document).on('click', '#asignatura li', function(){		
		var $this = $(this);
		asignatura = $this.html();
		
		$("#asignatura li.active").removeClass("active");
		$this.addClass("active");
		
		$btnAniadir.attr("disabled",false);
		$btnEliminar.attr("disabled",false);
	});

	//llamada ajax que carga los grupos correspondientes al curso
	function cargarGruposCurso(){
			
		$.ajax({
			url: "obtenerGrupoCurso",
			data: {'curso':curso},
			method: "POST",
			success: function(result){	
						convertirArrayAListadoHTML(result, $cuadranteGrupos);
					}
		});		

	};
	
	//llamada ajax que carga las asignaturas correspondientes al grupo y curso
	function cargarAsignaturasGrupo(){
			
		$.ajax({
			url: "obtenerAsignaturasGrupoCurso",
			data: {'curso':curso, 'grupo':grupo, 'itinerario':itinerario},
			method: "POST",
			success: function(result){	
							convertirArrayAListadoHTML(result, $cuadranteAsignatura);
						}
		});		

	};
	
	//funcion que convierte el array que devuelve la llamada ajax, lo transforma el listado html y lo inyecta en el jsp
	function convertirArrayAListadoHTML(result, $div){
		var listado = "";
		
		$.each(result, function (ind, elem) { 
			listado += "<li>"+elem+"</li>";
		}); 
		
		$div.empty();
		$div.html(listado);
		
	}
	
	$btnAniadir.click(function(){
			
		$.ajax({
			url: "obtenerHorariosAsignatura",
			data: {'curso':curso, 'grupo':grupo, 'itinerario':itinerario, 'asignatura':asignatura},
			method: "POST",
			success: function(result){							
						
				if(result != ''){
					$.each(result, function(i,item){	
						var celda = $("table tr#" + item.cuatrimestre + item.hora + " td."+ item.dia);
						var valorCelda = celda.html();
					
						if(valorCelda != ''){
							valorCelda = valorCelda + " - " + item.nombre;
							celda.addClass("conflicto");
							hayConflicto = true;
						}
						else{
							valorCelda = item.nombre;
						}
						
						celda.html(valorCelda);
					});	
					if(hayConflicto){
						alert("Cuidado! Tienes materias solapadas");
					}
				}		
				else{
					if(result.lenght == 0 || result.lenght == undefined){
						alert("No te puedes matricular dos veces de la misma asignatura");
					}
					else{
						if(hayConflicto){
							alert("Cuidado! Tienes materias solapadas");
						}
					}
				}
			}
		});		
		
	});
	
	$btnEliminar.click(function(){
		
		borrada = true;
		
		$.ajax({
			url: "eliminarAsignatura",
			data: {'asignatura':asignatura},
			method: "POST",
			success: function(result){	
							if(!result){
								alert("No se ha podido eliminar la asignatura o ésta no esta agregada al calendario");		
							}
							else{
								
								hayConflicto = false;
								
								$.ajax({
									url: "recuperarAsignaturas",
									method: "POST",
									success: function(result){	
										
										limpiarTabla();
										
										$.each(result, function(i,item){	
											var celda = $("table tr#" + item.cuatrimestre + item.hora + " td."+ item.dia);
											var valorCelda = celda.html();
										
											if(valorCelda != ''){
												valorCelda = valorCelda + " - " + item.nombre;
												celda.addClass("conflicto");
												hayConflicto = true;
											}
											else{
												valorCelda = item.nombre;
											}
											
											celda.html(valorCelda);
										});	
										
									}
								});		
								
							}
						}
		});		
		
		
	
	});
	
	$btnElimiarTodos.click(function(){
		
		hayConflicto = false;
		limpiarVista();
		limpiarTabla();		
		limpiarJava();	
		
	});
	
	$btnExportar.click(function(){
		
		$.ajax({
			url: "exportarCalendario",
			method: "POST",
			success: function(result){	
							
				var textToWrite = result;
				var textFileAsBlob = new Blob([textToWrite], { type: 'application/vnd.ms-excel'});
				var fileNameToSaveAs = "Horario-clases";

				var downloadLink = document.createElement("a");
				downloadLink.download = fileNameToSaveAs;
				downloadLink.innerHTML = "Download File";
				if (window.webkitURL != null)
				{
					// Chrome allows the link to be clicked
					// without actually adding it to the DOM.
					downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
				}
				else
				{
					// Firefox requires the link to be added to the DOM
					// before it can be clicked.
					downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
					downloadLink.onclick = destroyClickedElement;
					downloadLink.style.display = "none";
					document.body.appendChild(downloadLink);
				}

				downloadLink.click();				
				
				
						}
		});
		
	});
	
	function destroyClickedElement(event)
	{
		document.body.removeChild(event.target);
	}
	
	
	
});