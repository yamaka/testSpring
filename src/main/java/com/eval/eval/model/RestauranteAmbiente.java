package com.eval.eval.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table( name = "res_restaurante_ambientes")
public class RestauranteAmbiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "res_restaurante")
    Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "res_tipo_ambientes")
    TipoAmbiente tipoAmbiente;

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

}
