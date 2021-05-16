package ru.khusyainov.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khusyainov.rest.dto.BuyerDto;
import ru.khusyainov.rest.service.BuyerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerRestController {
    private BuyerService buyerService;

    @Autowired
    public void setBuyerService(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping
    public List<BuyerDto> getAll() {
        return buyerService.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BuyerDto addBuyer(@RequestBody BuyerDto buyer) {
        return buyerService.save(buyer);
    }

    @GetMapping(value = "/{id}")
    public BuyerDto get(@PathVariable Integer id) {
        return buyerService.findOne(id);
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BuyerDto update(@RequestBody BuyerDto cart) {
        return buyerService.save(cart);
    }

    @DeleteMapping(value = "/{id}")
    public int delete(@PathVariable Integer id) {
        buyerService.delete(id);
        return HttpStatus.OK.value();
    }
}