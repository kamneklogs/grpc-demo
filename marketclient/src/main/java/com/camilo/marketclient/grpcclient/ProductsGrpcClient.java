package com.camilo.marketclient.grpcclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.camilo.marketclient.model.Product;
import com.marketservices.grpc.Empty;
import com.marketservices.grpc.ProductObject;
import com.marketservices.grpc.ProductsList;
import com.marketservices.grpc.id;
import com.marketservices.grpc.marketServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class ProductsGrpcClient {

    private ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9090")
            .usePlaintext()
            .build();

    @GrpcClient("cloud-grpc-server")
    private marketServiceGrpc.marketServiceBlockingStub simpleStub;

    public List<Product> getAllProducts() {
        //marketServiceGrpc.marketServiceBlockingStub stub = marketServiceGrpc.newBlockingStub(channel);


        ProductsList retrievedProducts = simpleStub.getAll(Empty.getDefaultInstance());

        List<Product> products = new ArrayList<>() {
            {
                retrievedProducts.getProductList().forEach(product -> {
                    add(new Product(product.getId(), product.getName(), product.getDescription()));
                });
            }
        };

        //channel.shutdown();
        return products;
    }

    public Product getProductById(int prodcutId) {
        marketServiceGrpc.marketServiceBlockingStub stub = marketServiceGrpc.newBlockingStub(channel);

        ProductObject retrievedProduct = stub.findById(id.newBuilder().setId(prodcutId).build());

        return new Product(retrievedProduct.getId(), retrievedProduct.getName(), retrievedProduct.getDescription());
    }

}
