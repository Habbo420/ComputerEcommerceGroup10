package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.StoreDetails;
import za.ac.cput.service.StoreDetailsService;

import java.util.List;

@RestController
@RequestMapping("/storeDetails")
public class StoreDetailsController {
    @Autowired
    private StoreDetailsService storeDetailsService;

    @PostMapping("/create")
    public StoreDetails create(@RequestBody StoreDetails storeDetails) {
        return storeDetailsService.create(storeDetails);
    }

    @GetMapping("/read/{id}")
    public StoreDetails read(@PathVariable Long id) {
        return storeDetailsService.read(id);
    }

    @PutMapping("/update/{id}")
    public StoreDetails update(@RequestBody StoreDetails storeDetails) {
        return storeDetailsService.update(storeDetails);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        return storeDetailsService.delete(id);
    }

    @GetMapping("/getAll")
    public List<StoreDetails> getAll() {
        return storeDetailsService.getAll();
    }
}
