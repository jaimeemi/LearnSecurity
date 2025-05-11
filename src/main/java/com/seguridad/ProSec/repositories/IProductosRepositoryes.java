package com.seguridad.ProSec.repositories;

import com.seguridad.ProSec.models.entities.ProductosEntityes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductosRepositoryes extends JpaRepository<ProductosEntityes, Integer> {


}
