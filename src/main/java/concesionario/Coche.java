package concesionario;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "concesionario")
class Coche {

	//private @Id @GeneratedValue Long id;
	private @Id @Column(unique=true) String matricula;
	private String modelo;
	private String marca;

	Coche() {
	}

	Coche(String matricula, String marca,  String modelo) {

		this.matricula = matricula;
		this.modelo = modelo;
		this.marca = marca;
	}

	/*
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	*/

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	/*
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Coche))
			return false;
		Coche c = (Coche) o;
		return Objects.equals(this.matricula, c.matricula);
	}
	*/
	/*
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.matricula, this.marca, this.modelo);
	}

	@Override
	  public String toString() {
	    return "Coche{\" + \"id=\" + this.id +" + ", matricula='" + this.matricula + '\'' + ", marca='" + this.marca
	        + '\'' + ", modelo='" + this.modelo + '\'' + '}';
	  }
	  */
	
	@Override
	public int hashCode() {
		return Objects.hash(this.matricula, this.marca, this.modelo);
	}

	@Override
	  public String toString() {
	    return "Coche{"+"matricula='" + this.matricula + '\'' + ", marca='" + this.marca
	        + '\'' + ", modelo='" + this.modelo + '\'' + '}';
	  }
}