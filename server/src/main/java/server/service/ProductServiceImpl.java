package server.service;

import lib.dto.ProductDTO;
import lib.dto.UserIdDTO;
import lib.service.ProductService;
import server.convert.ProductConvertor;
import server.dao.impl.ProductDaoImpl;
import server.dao.interfaces.ProductDao;

import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProductServiceImpl extends UnicastRemoteObject
        implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl() throws RemoteException {
        var emf = Persistence.createEntityManagerFactory("ordershopPU");
        var em = emf.createEntityManager();

        productDao = new ProductDaoImpl(em);
    }

    @Override
    public void persist(ProductDTO productDto) throws RemoteException {
        var product = ProductConvertor.convert(productDto);
        productDao.persist(product);
    }

    @Override
    public Collection<ProductDTO> findAll() throws RemoteException {
        return productDao.findAll().stream()
                .map(ProductConvertor::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ProductDTO> findProductsByUser(UserIdDTO userId) throws RemoteException {
        return productDao.findProductsByUser(userId).stream()
                .map(ProductConvertor::convert)
                .collect(Collectors.toList());
    }
/*    public ProductDTO findProductByUser(ProductDTO productDto) throws RemoteException {
       var product = ProductConvertor.convert(productDto);
        return productDao.findProductByUser(product.getId());

    }*/

    @Override
    @Transactional
    public void delete(ProductDTO productDTO) throws RemoteException {
        var product = ProductConvertor.convert(productDTO);
        productDao.getById(productDTO.getId());
        if (productDTO != null) {
             productDao.delete(product);
        }

    }
}
