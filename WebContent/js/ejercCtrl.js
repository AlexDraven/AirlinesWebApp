app.controller("ejercCtrl", function($scope, $http) {

	$scope.modal = "";
	
	/**
	 *	Accionado por evento clic. Realiza peticion al Server.
	 */
	$scope.verReservas = function() {
		// Envio de datos al servidor
		$http.get('javaAngularJS')
	    .then(function (response) {
	    	if (response.data != null && response.data != "null") {
		    	$scope.reservaciones = response.data;
		    	$scope.modal = "3";
		    	$scope.modal.mostrarLista = true;
		    	angular.element('#ventanaModal').modal('show');
	    	} else {
		    	$scope.reservaciones = {};
	    		alert('No se han encontrado datos');
	    	}

	    }, function(response) {
	    	$scope.reservaciones = {};
    		alert('Se ha producido un error');
	    });
	};
	
	/**
	 *	Accionado por evento clic. Realiza peticion al Server metodo POST.
	 */
	$scope.reservar = function() {
		if (isValido()) {
			// Envio de datos al servidor POST
			/** TODO - Completar parametros. */
			$http.post('javaAngularJS', { 
				nombre : $scope.nombre, 
				apellido : $scope.apellido,
				documento : $scope.documento,
				partida : $scope.partida,
				butaca : $scope.butaca,
				clase : $scope.clase
			} )
		    .then(function (response) {
		    	if (response.data != null && response.data != "null") {
		    		$scope.modal = response.data;
		    		/** TODO - Contemplar caso reserva valida. */
		    		if (response.data.isValido == true) {
		    			$scope.modal = "1";
		    		}else{
		    			$scope.modal = "2";
		    		}
			    	angular.element('#ventanaModal').modal('show');
		    	} else {
			    	$scope.respuesta = {};
		    		alert('No hay respuesta');
		    	}

		    }, function(response) {
		    	$scope.datosPersona = {};
	    		alert('Se ha producido un error');
		    });			
		}
	};
	
	/**
	 *	valida los campos al intentar reservar.
	 *	@return is Valido 
	 */
	function isValido() {
		var mensajeConcat = '';
		if ($scope.nombre == undefined || $scope.nombre == '') {
			mensajeConcat += "Nombre, ";
		} 
		if ($scope.apellido == undefined || $scope.apellido == '') {
			mensajeConcat += "Apellido, ";
		} 
		if ($scope.documento == undefined || $scope.documento == '') {
			mensajeConcat += "Documento, ";
		} 
		/** TODO - Completar validaciones. */
		if ($scope.butaca == undefined || $scope.butaca == '') {
			mensajeConcat += "Butaca, ";
		}
		if ($scope.clase == undefined || $scope.clase == '') {
			mensajeConcat += "Clase (ejecutiva o turista), ";
		}
		
		if (mensajeConcat != '') {
	    	$scope.modal = "0";
			$scope.mensaje = mensajeConcat.substring(0, mensajeConcat.length - 2);
	    	angular.element('#ventanaModal').modal('show');
			return false;			
		}
		return true;
	}
	
});
