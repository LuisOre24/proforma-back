package com.soldev.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "atencion")
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdAtencion;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name = "fk_atencion_cliente"))
    private Cliente cliente;

    private LocalDateTime fechaAtencion;

    private LocalDateTime fechaEntrega;

    @Size(min = 5)
    @Column(name = "detalle_atencion", nullable = false, length = 500)
    private String detalleAtencion;

    @Size(min = 5)
    @Column(name = "detalle_entrega")
    private String detalleEntrega;

    @OneToMany(mappedBy = "atencion", cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = true )
    private List<DetallePago> detallePago;

    private Double costoAtencion;

    private Integer estadoAtencion;

    private Integer estadoCancelacion;

    public Integer getIdAtencion() {
        return IdAtencion;
    }

    public void setIdAtencion(Integer idAtencion) {
        IdAtencion = idAtencion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(LocalDateTime fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDetalleAtencion() {
        return detalleAtencion;
    }

    public void setDetalleAtencion(String detalleAtencion) {
        this.detalleAtencion = detalleAtencion;
    }

    public String getDetalleEntrega() {
        return detalleEntrega;
    }

    public void setDetalleEntrega(String detalleEntrega) {
        this.detalleEntrega = detalleEntrega;
    }

   public List<DetallePago> getDetallePago() {
        return detallePago;
    }

    public void setDetallePago(List<DetallePago> detallePago) {
        this.detallePago = detallePago;
    }

    public Integer getEstadoAtencion() {
        return estadoAtencion;
    }

    public void setEstadoAtencion(Integer estadoAtencion) {
        this.estadoAtencion = estadoAtencion;
    }

    public Double getCostoAtencion() {
        return costoAtencion;
    }

    public void setCostoAtencion(Double costoAtencion) {
        this.costoAtencion = costoAtencion;
    }

    public Integer getEstadoCancelacion() {
        return estadoCancelacion;
    }

    public void setEstadoCancelacion(Integer estadoCancelacion) {
        this.estadoCancelacion = estadoCancelacion;
    }
}
