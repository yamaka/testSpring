package com.eval.eval.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "res_restaurante")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "description")
    private String description;

    @Column(name = "telefono")
    private long telefono;

    @Column(name = "estado")
    private String estado;

    @Column(name = "logo")
    private String logo;

    @Column(name = "horario")
    private String horario;

    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;

    @Column(name = "id_tipo_comida")
    private long id_tipo_comida;

    @Column(name = "fecha_alta")
    private LocalDateTime fecha_alta;

    @Column(name = "id_usuario_alta")
    private long id_usuario_alta;

    @Column(name = "fecha_baja")
    private LocalDateTime fecha_baja;

    @Column(name = "id_usuario_baja")
    private long id_usuario_baja;

    @Column(name = "fecha_desde")
    private LocalDateTime fecha_desde;

    @Column(name = "id_usuario_desde")
    private long id_usuario_desde;

    @Column(name = "fecha_hasta")
    private LocalDateTime fecha_hasta;

    @Column(name = "id_usuario_hasta")
    private long id_usuario_hasta;


    @OneToMany(mappedBy = "restaurante")
    Set<RestauranteTipoComida> restauranteTipoComidas;

    @OneToMany(mappedBy = "restaurante")
    Set<RestauranteAmbiente> restauranteAmbientes;


    public Restaurante() {
    }

    public Restaurante(String nombre, String description, long telefono, String estado, String logo, String horario, String latitud, String longitud) {
        this.nombre = nombre;
        this.description = description;
        this.telefono = telefono;
        this.estado = estado;
        this.logo = logo;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Restaurante(String nombre, String description, long telefono, String estado, String logo, String horario, String latitud, String longitud, long id_tipo_comida, LocalDateTime fecha_alta, long id_usuario_alta, LocalDateTime fecha_baja, long id_usuario_baja, LocalDateTime fecha_desde, long id_usuario_desde, LocalDateTime fecha_hasta, long id_usuario_hasta) {
        this.nombre = nombre;
        this.description = description;
        this.telefono = telefono;
        this.estado = estado;
        this.logo = logo;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.id_tipo_comida = id_tipo_comida;
        this.fecha_alta = fecha_alta;
        this.id_usuario_alta = id_usuario_alta;
        this.fecha_baja = fecha_baja;
        this.id_usuario_baja = id_usuario_baja;
        this.fecha_desde = fecha_desde;
        this.id_usuario_desde = id_usuario_desde;
        this.fecha_hasta = fecha_hasta;
        this.id_usuario_hasta = id_usuario_hasta;
    }

    public Restaurante(String nombre, String description) {
        this.nombre = nombre;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public long getId_tipo_comida() {
        return id_tipo_comida;
    }

    public void setId_tipo_comida(long id_tipo_comida) {
        this.id_tipo_comida = id_tipo_comida;
    }

    public LocalDateTime getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDateTime fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public long getId_usuario_alta() {
        return id_usuario_alta;
    }

    public void setId_usuario_alta(long id_usuario_alta) {
        this.id_usuario_alta = id_usuario_alta;
    }

    public LocalDateTime getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(LocalDateTime fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public long getId_usuario_baja() {
        return id_usuario_baja;
    }

    public void setId_usuario_baja(long id_usuario_baja) {
        this.id_usuario_baja = id_usuario_baja;
    }

    public LocalDateTime getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(LocalDateTime fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public long getId_usuario_desde() {
        return id_usuario_desde;
    }

    public void setId_usuario_desde(long id_usuario_desde) {
        this.id_usuario_desde = id_usuario_desde;
    }

    public LocalDateTime getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(LocalDateTime fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public long getId_usuario_hasta() {
        return id_usuario_hasta;
    }

    public void setId_usuario_hasta(long id_usuario_hasta) {
        this.id_usuario_hasta = id_usuario_hasta;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", description='" + description + '\'' +
                ", telefono=" + telefono +
                ", estado='" + estado + '\'' +
                ", logo='" + logo + '\'' +
                ", horario='" + horario + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", id_tipo_comida=" + id_tipo_comida +
                ", fecha_alta=" + fecha_alta +
                ", id_usuario_alta=" + id_usuario_alta +
                ", fecha_baja=" + fecha_baja +
                ", id_usuario_baja=" + id_usuario_baja +
                ", fecha_desde=" + fecha_desde +
                ", id_usuario_desde=" + id_usuario_desde +
                ", fecha_hasta=" + fecha_hasta +
                ", id_usuario_hasta=" + id_usuario_hasta +
                '}';
    }
}
