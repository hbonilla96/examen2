package com.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productor")
public class Productor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="provincia")
	private String provincia;
	
	@Column(name="canton")
	private String canton;
		
	@Column(name="distrito")
	private String distrito;
	
	@Column(name="cedula")
	private String cedula;
	
	@Column(name="empresa")
	private String empresa;

	public Productor(String nombre, String direccion, String provincia, String canton, String distrito,
					String cedula, String empresa) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.provincia = provincia;
		this.canton = canton;
		this.distrito = distrito;
		this.cedula = cedula;
		this.empresa = empresa;
	}

	public Productor() {
		
	}

	public Productor(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return nombre;
	}

	public void setFirstname(String nombre) {
		this.nombre = nombre;
	}

	public String getNominalAddress() {
		return direccion;
	}

	public void setNominalAddress(String direccion) {
		this.direccion = direccion;
	}

	public String getProvince() {
		return provincia;
	}

	public void setProvince(String provincia) {
		this.provincia = provincia;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getDistrict() {
		return distrito;
	}

	public void setDistrict(String distrito) {
		this.distrito = distrito;
	}

	public String getIdentification() {
		return cedula;
	}

	public void setIdentification(String cedula) {
		this.cedula = cedula;
	}

	public String getCompanyName() {
		return empresa;
	}

	public void setCompanyName(String empresa) {
		this.empresa = empresa;
	}
}
