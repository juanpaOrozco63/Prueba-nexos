package co.prueba.nexos.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.prueba.nexos.domain.Producto;
import co.prueba.nexos.dto.ProductoDTO;
@Mapper
public interface ProductoMapper {
	public ProductoDTO toProductoDTO(Producto producto);
	public List<ProductoDTO> toProductosDTO(List<Producto> productos);
	public Producto toProducto(ProductoDTO productoDTO);
	public List<Producto> toProductos(List<ProductoDTO> productsDTO);
}
