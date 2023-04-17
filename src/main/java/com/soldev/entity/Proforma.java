package com.soldev.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "proforma")
public class Proforma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProforma;
	
	/*
	 * @Column(name = "nro_proforma") private Integer nroProforma;
	 */
	
	@Size(min=3)
	@Column(name = "cliente", length = 300)
	private String cliente;
	
	@Size(min=7, max=11)
	@Column(name = "documento")
	private String documento;
	

	@Column(columnDefinition = "decimal(10,2)")
	private Double total;
	
	@Column(name = "fecha")
	private LocalDateTime fecha;
	
	@OneToMany(mappedBy = "proforma", cascade = { CascadeType.ALL, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<DetalleProforma> detalleProforma;
	
	/*
	 * @Column(name = "total_proforma") private Double totalProforma;
	 */

	public Integer getIdProforma() {
		return idProforma;
	}

	public void setIdProforma(Integer idProforma) {
		this.idProforma = idProforma;
	}

	/*
	 * public Integer getNroProforma() { return nroProforma; }
	 * 
	 * public void setNroProforma(Integer nroProforma) { this.nroProforma =
	 * nroProforma; }
	 */

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<DetalleProforma> getDetalleProforma() {
		return detalleProforma;
	}

	public void setDetalleProforma(List<DetalleProforma> detalleProforma) {
		this.detalleProforma = detalleProforma;
	}

	//metodo para calcular el importe total
	public Double getTotalProforma() {
		Double totalProforma = 0.00;
		for(DetalleProforma detalle : detalleProforma) {
			totalProforma += detalle.getTotal();
		}
		return totalProforma;
	}

	
	
	
	
}
