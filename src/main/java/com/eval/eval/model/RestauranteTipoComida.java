package com.eval.eval.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table( name = "res_restaurantes_tipos_comidas")
public class RestauranteTipoComida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "res_restaurante")
    Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "res_tipo_comidas")
    TipoComida tipoComida;

}
