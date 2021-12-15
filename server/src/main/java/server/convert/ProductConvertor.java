package server.convert;

import lib.dto.ProductDTO;
import server.model.Product;
import server.model.User;

public final class ProductConvertor {
    private ProductConvertor() {
    }


    public static ProductDTO convert(Product product) {

        ProductDTO productDTO = new ProductDTO(
                product.getProductName(),
                product.getPrice(),
                product.getCategory(),
                UserConvertor.convert(product.getUser())
        );

        productDTO.setId(product.getId());

        return productDTO;
    }

    public static Product convert(ProductDTO productDto) {
        User user = UserConvertor.convert(productDto.getUserDTO());

        Product product = new Product();
        product.setCategory(productDto.getCategory());
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setUser(user);

        return product;
    }

}