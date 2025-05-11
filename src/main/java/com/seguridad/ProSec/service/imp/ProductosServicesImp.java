package com.seguridad.ProSec.service.imp;

import com.seguridad.ProSec.models.DTO.ProductoDTO;
import com.seguridad.ProSec.repositories.IProductosRepositoryes;
import com.seguridad.ProSec.repositories.RepositoriesServices.RepositoriesServices;
import com.seguridad.ProSec.service.IProductosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServicesImp implements IProductosServices {

    protected final IProductosRepositoryes productosRepositoryes;

    @Autowired
    public ProductosServicesImp(IProductosRepositoryes productosRepositoryes) {
        this.productosRepositoryes = productosRepositoryes;
    }

    @Override
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(int productoId) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO =  RepositoriesServices.optionalProductoDTO (productosRepositoryes.findById(productoId) );

        return new ResponseEntity<>(productoDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {
        List<ProductoDTO> productos = new java.util.ArrayList<>();

        productosRepositoryes.findAll().forEach(producto -> {
            productos.add(RepositoriesServices.toDto(producto));
        });

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoDTO> grabarProducto(ProductoDTO productoDTO) {
        productosRepositoryes.save(RepositoriesServices.toEntity(productoDTO));
        return new ResponseEntity<>(productoDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoDTO> actualizarProducto(int productoId, ProductoDTO productoDTO) {
        ProductoDTO productoActualizado;
        productoActualizado = RepositoriesServices.optionalProductoDTO( productosRepositoryes.findById(productoId) );

        productoActualizado.setNombre(productoDTO.getNombre());
        productoActualizado.setPrecio(productoDTO.getPrecio());
        productoActualizado.setStock(productoDTO.getStock());
        productoActualizado.setId_categoria(productoDTO.getId_categoria());
        productoActualizado.setId_proveedor(productoDTO.getId_proveedor());

        productosRepositoryes.save( RepositoriesServices.toEntity( productoActualizado ) );

        if (productoActualizado != null)
            productosRepositoryes.save( RepositoriesServices.toEntity( productoActualizado ) );
        else
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productoActualizado);
    }

    @Override
    public ProductoDTO eliminarProducto(int productoId) {
        ProductoDTO productoDTO = RepositoriesServices.optionalProductoDTO(  productosRepositoryes.findById(productoId) );
        if (productoDTO.equals(null))
            return null;
        else
            return productoDTO;
    }
}