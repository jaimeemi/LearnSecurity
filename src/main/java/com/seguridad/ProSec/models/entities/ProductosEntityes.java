package com.seguridad.ProSec.models.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductosEntityes {

    private int id;

    private String nombre;

    private String descripcion;

    private int stock;

    private float precio;

    private int id_categoria;

    private int id_proveedor;

}
