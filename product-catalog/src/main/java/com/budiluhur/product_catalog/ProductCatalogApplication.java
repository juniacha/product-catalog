
package com.budiluhur.product_catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.service.ProductService;

@SpringBootApplication
public class ProductCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductService productService) {
        return args -> {

            System.out.println("=== SPRING BOOT PRODUCT CATALOG IS RUNNING ===");

            // Menambahkan produk baru
            productService.createProduct(
                    new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));

            System.out.println("\nDaftar Produk di Spring Container:");

            productService.getAllProducts()
                    .forEach(System.out::println);

            // Menguji fitur diskon
            System.out.println("\n--- INFORMASI HARGA & DISKON ---");

            tampilkanDiskon(productService, "PRD-01", 10.0);
            tampilkanDiskon(productService, "PRD-02", 0.0);
        };
    }

    private void tampilkanDiskon(
            ProductService productService,
            String id,
            double diskon) throws Exception {

        Product product = productService.getProductById(id);

        double hargaAkhir =
                productService.getDiscountedProductPrice(id, diskon);

        System.out.println("\n[" + product.getId() + "] "
                + product.getName());

        System.out.println("- Harga Normal : Rp" + product.getPrice());
        System.out.println("- Diskon       : " + diskon + "%");
        System.out.println("- Harga Akhir  : Rp" + hargaAkhir);
    }
}
