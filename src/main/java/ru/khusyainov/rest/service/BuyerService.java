package ru.khusyainov.rest.service;

import org.springframework.stereotype.Service;
import ru.khusyainov.rest.dto.BuyerDto;
import ru.khusyainov.model.Buyer;
import ru.khusyainov.repository.BuyerRepository;
import ru.khusyainov.rest.mapper.BuyerConverter;

import java.util.List;

@Service
public class BuyerService {

    private BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<BuyerDto> findAll() {
        return BuyerConverter.toDtoList(buyerRepository.findAll());
    }

    public BuyerDto findOne(Integer id) {
        return BuyerConverter.toDto(buyerRepository.getOne(id));
    }

    public BuyerDto save(BuyerDto buyerDto) {
        Buyer buyer = BuyerConverter.fromDto(buyerDto);
        buyer = buyerRepository.save(buyer);
        return BuyerConverter.toDto(buyer);
    }

    public void delete(BuyerDto buyerDto) {
        Buyer buyer = BuyerConverter.fromDto(buyerDto);
        buyerRepository.delete(buyer);
    }

    public void delete(Integer buyerId) {
        Buyer buyer = buyerRepository.getOne(buyerId);
        buyerRepository.delete(buyer);
    }
}