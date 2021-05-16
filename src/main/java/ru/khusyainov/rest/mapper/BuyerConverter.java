package ru.khusyainov.rest.mapper;

import ru.khusyainov.model.Buyer;
import ru.khusyainov.rest.dto.BuyerDto;

import java.util.ArrayList;
import java.util.List;

public class BuyerConverter {

    public static Buyer fromDto(BuyerDto buyerDto) {
        if (buyerDto == null) {
            return null;
        }
        return new Buyer(buyerDto.getId(), buyerDto.getName(), CartConverter.fromDto(buyerDto.getCart()));
    }

    public static BuyerDto toDto(Buyer buyer) {
        if (buyer == null) {
            return null;
        }
        return new BuyerDto(buyer.getId(), buyer.getName(), CartConverter.toDto(buyer.getCart()));
    }

    public static List<Buyer> fromDtoList(List<BuyerDto> buyerDtos) {
        if (buyerDtos == null) {
            return null;
        }
        List<Buyer> list = new ArrayList<>(buyerDtos.size());
        buyerDtos.forEach(buyerDto -> list.add(fromDto(buyerDto)));
        return list;
    }

    public static List<BuyerDto> toDtoList(List<Buyer> buyers) {
        if (buyers == null) {
            return null;
        }
        List<BuyerDto> list = new ArrayList<>(buyers.size());
        buyers.forEach(buyer -> list.add(toDto(buyer)));
        return list;
    }
}