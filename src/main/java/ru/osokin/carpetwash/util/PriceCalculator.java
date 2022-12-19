package ru.osokin.carpetwash.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.osokin.carpetwash.models.Carpet;
import ru.osokin.carpetwash.models.Client;
import ru.osokin.carpetwash.services.SaleService;

@Component
public class PriceCalculator {

    private final SaleService saleService;
    @Autowired
    public PriceCalculator(SaleService saleService) {
        this.saleService = saleService;
    }

    public void calculate(Carpet carpet) {
        Client owner = carpet.getOwner();
        saleService.updateClientsSale(owner.getPhoneNumber(), owner);
        int saleAmount = carpet
                .getOwner()
                .getSale();
        double totalPrice = salePrice(carpet, saleAmount);
        carpet.setTotalPrice(totalPrice);
    }

    private double ordinaryPrice(Carpet carpet) {
        return carpet.getArea() * 1000;
    }

    private double salePrice(Carpet carpet, int percentage) {
        return ordinaryPrice(carpet) * ((100 - percentage) * 1.0 / 100);
    }
}
