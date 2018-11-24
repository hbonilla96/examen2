package com.modelos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "finca")
public class Finca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_productor")
	private Productor productor = new Productor();

	@Transient
	private int idProductor;

	// This insert a list of coffee types into the tfarmland_x_coffee table
	@ManyToMany
	@JoinTable(name="cafe_por_finca", joinColumns = {@JoinColumn(name="id_finca")}, inverseJoinColumns = {@JoinColumn(name = "id_cafe")})
	private List<Cafe> tiposCafe = new ArrayList<>();


	public Finca(String nombre, Productor productor) {
		this.nombre = nombre;
		this.productor = productor;
	}

	public Finca() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return nombre;
	}

	public void setName(String nombre) {
		this.nombre = nombre;
	}

	public int getIdProducer() {
		return idProductor;
	}

	public void setIdProducer(int idProductor) {
		this.idProductor = idProductor;
	}

	public List<Cafe> getCoffeTypes() {
		return tiposCafe;
	}

	public void setCoffeTypes(List<Cafe> tiposCafe) {
		this.tiposCafe = tiposCafe;
	}

	//////////////////////////////////


	public Productor getProducer() {
		return productor;
	}

	public void setProducer(Productor productor) {
		this.productor = productor;
	}
}
