package com.lofitskyi.api;

import com.lofitskyi.entity.Currency;
import com.lofitskyi.entity.UserView;
import com.lofitskyi.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/stat")
public class StatisticApi {

    private final RestTemplate rest;
    private final CurrencyService currencyService;

    @Autowired
    public StatisticApi(RestTemplate rest, CurrencyService currencyService) {
        this.rest = rest;
        this.currencyService = currencyService;
    }

    @RequestMapping(path = "/{currencyName}/{id}", method = GET)
    public BigDecimal getStatisticById(@PathVariable Long id,
                                      @PathVariable String currencyName){

        final UserView user = rest.getForObject("http://localhost:8080/user/" + id, UserView.class);
        final Currency currency = currencyService.getCurrencyByName(currencyName);

        return user.getWastes().stream()
                .map(value -> value.multiply(currency.getRate()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
