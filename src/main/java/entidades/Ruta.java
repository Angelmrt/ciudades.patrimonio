package entidades;

public class Ruta {

	private int id;
	private int ciudad;
	private String nombre;
	private String imagen;
	private String descripcion;
	private String link;
	private double mediaptos;
	
	public Ruta(int id, int ciudad, String nombre, String imagen, String descripcion, String link , double mediaptos) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.link = link;
		this.mediaptos = mediaptos;
	}

	public Ruta() {
		super();
	}
	  public double getMediaPuntos() {
	        return mediaptos;
	    }

	    public void setMediaPuntos(double mediaPuntos) {
	        this.mediaptos = mediaPuntos;
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCiudad() {
		return ciudad;
	}

	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Ruta [id=" + id + ", ciudad=" + ciudad + ", nombre=" + nombre + ", imagen=" + imagen + ", descripcion="
				+ descripcion + ", link=" + link + "]";
	}
	
}
