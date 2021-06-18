package com.eis.transaction.dao;

import com.eis.transaction.entity.Blotter;

import java.util.List;

public interface BlotterDao {
    Blotter saveBlotter(Blotter blotter);

    List<Blotter> findAllBySellNameAndSellCompany(String sellName,String sellCompany);

    List<Blotter> findAllByBuyNameAndBuyCompany(String buyName,String buyCompany);
}
