package com.digiplan.services;

import java.util.List;

import com.digiplan.entities.Dealer;

public interface DealerService {

    Dealer getDealer(Integer id);

    List<Dealer> getAllDealers();

    Dealer addDealer(Dealer dealerData);

    Dealer updateDealer(Integer id, Dealer dealerData);

    String deleteDealer(Integer id);
}
