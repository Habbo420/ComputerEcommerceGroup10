package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.SupplierOrder;
import za.ac.cput.service.SupplierOrderService;
/*
SupplierOrderController.java
Controller for SupplierOrderController
Author: Ethan Botes (220101299)
Date: 20/08/2023
 */


import java.util.List;

@RestController
@RequestMapping("/supplierOrder")
public class SupplierOrderController {

    @Autowired
    private SupplierOrderService supplierOrderService;

    @PostMapping("/create")
    public SupplierOrder create(@RequestBody SupplierOrder supplierOrder){return supplierOrderService.create(supplierOrder);}

    @GetMapping("/read/{id}")
    public SupplierOrder read(@PathVariable String id){return supplierOrderService.read(id);}

    @PutMapping("/update")
    public SupplierOrder update(@RequestBody SupplierOrder supplierOrder){
        return supplierOrderService.update(supplierOrder);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id){return supplierOrderService.delete(id);}

    @GetMapping("/getAll")
    public List<SupplierOrder> getAll(){
        return supplierOrderService.getAll();
    }

}
