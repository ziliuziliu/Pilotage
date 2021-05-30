package com.eis.transaction.daoImpl;

import com.eis.transaction.dao.BlotterDao;
import com.eis.transaction.entity.Blotter;
import com.eis.transaction.repository.BlotterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlotterDaoImpl implements BlotterDao {
    @Autowired
    private BlotterRepository blotterRepository;

    @Override
    public Blotter saveBlotter(Blotter blotter) {
        return blotterRepository.save(blotter);
    }

    @Override
    public List<Blotter> findAllBySellNameAndSellCompany(String sellName, String sellCompany) {
        return blotterRepository.findAllBySellNameAndSellCompany(sellName,sellCompany);
    }

    @Override
    public List<Blotter> findAllByBuyNameAndBuyCompany(String buyName, String buyCompany) {
        return blotterRepository.findAllByBuyNameAndBuyCompany(buyName,buyCompany);
    }
}
