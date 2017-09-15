package com.visa.ncg.canteen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {

    public int convertToGbp(int usdAmount) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://jitterted-currency-conversion.herokuapp.com/convert?from={from}&to={to}&amount={amount}";
        Map<String, String> params = new HashMap<>();
        params.put("from", "USD");
        params.put("to", "GBP");
        params.put("amount", Integer.toString(usdAmount));
        ConvertedCurrency converted = restTemplate.getForObject(apiUrl, ConvertedCurrency.class, params);

        return converted.getConverted();
    }
}
