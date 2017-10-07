package dto;

/**
 * 	DTO con parametros de entrada.
 */
public class ParamsEntradaDTO {
	
	private String nombre;
	private boolean partida;
	private String butaca;
	private String clase;
	/** TODO - Agregar propiedades restantes. */
	private String apellido;
	private Integer documento;
	
	
	/**
	 *	Constructor no-arg. 
	 */
	public ParamsEntradaDTO() {
		super();
	}

	/**
	 *	Constructor arg. 
	 */
	public ParamsEntradaDTO(String nombre, String apellido, Integer documento, boolean partida, String butaca,
			String clase) {
		super();
		this.nombre = nombre;
		this.partida = partida;
		this.butaca = butaca;
		this.clase = clase;
		this.apellido = apellido;
		this.documento = documento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPartida() {
		return this.partida;
	}

	public void setPartida(boolean partida) {
		this.partida = partida;
	}

	public String getButaca() {
		return this.butaca;
	}

	public void setButaca(String butaca) {
		this.butaca = butaca;
	}

	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	/**
	 *	Formato para persistencia en archivo TXT.
	 *	@return String  
	 */
	public String getObjetoFormateado() {
		return this.nombre + "&"+ this.apellido + "&"+ this.documento + "&" + this.partida + "&" + this.butaca + "&" + this.clase;  
	}
	
}
