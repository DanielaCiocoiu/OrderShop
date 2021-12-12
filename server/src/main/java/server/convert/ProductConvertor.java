package server.convert;

import lib.dto.ProductDTO;
import server.model.Product;

public final class ProductConvertor {
    private ProductConvertor() {
    }

    public static Product convert(ProductDTO productDto) {
        var product = new Product();

        product.setCategory(productDto.getCategory());
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setUserDTO(product.getUserDTO());

        return product;
    }

    public static ProductDTO convert(Product product) {
        var productDTO = new ProductDTO(
                product.getProductName(),
                product.getPrice(),
                product.getCategory(),
                product.getUserDTO()
        );

        productDTO.setId(product.getId());

        return productDTO;
    } }