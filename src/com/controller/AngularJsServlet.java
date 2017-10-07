package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.businessMock.ReservasBOMock;
import com.constants.Constantes;
import com.google.gson.Gson;
import com.model.Respuesta;

import dto.ParamsEntradaDTO;

/**
 *	Servlet utilizado para obtener datos del servidor. 
 */
// @WebServlet("/javaAngularJS/*")
public class AngularJsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger("AngularJsServlet");

	private ReservasBOMock DBMock = new ReservasBOMock();
	/**
	 *	Constructor no-arg. 
	 */
	public AngularJsServlet() {
		super();
	}

	/**
	 *	Procesamiento de la peticion enviada por el cliente.
	 *	@param request
	 *	@param response
	 *	@throws ServletException, IOException  
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LOGGER.info("Inicio Service Servlet .... ");
		
		// Se reescriben headers del response para evitar caching del navegador web
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		Respuesta respuesta = null;
		List<ParamsEntradaDTO> reservaciones = new ArrayList<ParamsEntradaDTO>();
		String json = null;
		// Metodo para obtener los datos del mock.
		switch (request.getMethod()) {
		case Constantes.METODO_HTTP_GET:
			// Se obtiene mapa del request para GET
			/** TODO - Reemplazar MOCK */			
			reservaciones.addAll(DBMock.getListaReservacionesFromFile());
			json = new Gson().toJson(reservaciones);
			break;
		case Constantes.METODO_HTTP_POST:
			// Se obtiene DTO de entrada para POST
			ParamsEntradaDTO paramsEntrDTO = new Gson().fromJson(request.getReader().readLine(), ParamsEntradaDTO.class);
			/** TODO - Reemplazar MOCK */		
			respuesta = DBMock.isButacaOcupadaMock(paramsEntrDTO);
			json = new Gson().toJson(respuesta);
			LOGGER.info(" >>>>> param -> " + paramsEntrDTO +  " <<<<< ");
			break;
		}
		
		// Se utiliza la libreria GSON para pasar el objeto obtenido
		// en formato JSON para ambos tipos GET y POST
		response.setContentType("application/json");
		response.getWriter().write(json);
		
		LOGGER.info("Fin Service Servlet .... ");

	}

}
