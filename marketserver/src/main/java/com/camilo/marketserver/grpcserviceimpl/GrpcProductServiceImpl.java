package com.camilo.marketserver.grpcserviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.camilo.marketserver.model.Product;
import com.camilo.marketserver.service.ProductService;
import com.marketservices.grpc.Empty;
import com.marketservices.grpc.ProductObject;
import com.marketservices.grpc.ProductsList;
import com.marketservices.grpc.id;
import com.marketservices.grpc.marketServiceGrpc.marketServiceImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcProductServiceImpl extends marketServiceImplBase {

    private final ProductService productService;

    @Autowired
    public GrpcProductServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void findById(id request, StreamObserver<ProductObject> responseObserver) {
        Product product = productService.findById(request.getId());

        ProductObject productObject = ProductObject.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .build();

        responseObserver.onNext(productObject); // This is the way to send a response to the client
        responseObserver.onCompleted(); // To close the communication channel
    }

    @Override
    public void getAll(Empty request, StreamObserver<ProductsList> responseObserver) {
        Iterable<Product> allProducts = productService.findAll();

        List<ProductObject> productObjects = new ArrayList<>();

        allProducts.forEach(product -> productObjects.add(ProductObject.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .build()));

        responseObserver.onNext(ProductsList.newBuilder()
                .addAllProduct(productObjects)
                .build()); // This is the way to send a response to the client
        responseObserver.onCompleted(); // To close the communication channel
    }
}
