package th.mfu;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    // Create hashmap for Product
    private HashMap<Long, Product> productDB = new HashMap<Long, Product>();

    // Select all Product
    @GetMapping("/products")
    public Collection<Product> getallProducts() {
        return productDB.values();
    }

    // Create new Product
    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        //Can't find id to delete return Product id already exists
        if (productDB.containsKey(product.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product id already exists");
        }
        // add Product to hashmap
        productDB.put(product.getId(), product);

        // return created success message Product created
        return ResponseEntity.ok("Product created");
    }
    
}
