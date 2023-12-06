package org.salon_frumusete.controller;

import org.salon_frumusete.databasemodell.ProductReceipt;
import org.salon_frumusete.repository.ProductReceiptRepository;

import java.util.List;

public class ProductReceiptController {
    private ProductReceiptRepository productReceiptRepository;

    public ProductReceiptController(ProductReceiptRepository productReceiptRepository) {
        this.productReceiptRepository = productReceiptRepository;
    }

    public void addProductReceipt(ProductReceipt productReceipt) {
        productReceiptRepository.addProductReceipt(productReceipt);
    }

    public List<ProductReceipt> getProductReceipts() {
        return productReceiptRepository.getProductReceipts();
    }

    public void deleteProductReceipt(ProductReceipt productReceipt) {
        productReceiptRepository.deleteProductReceipt(productReceipt);
    }

    public void clearProductReceipts() {
        productReceiptRepository.clearProductReceipts();
    }
}