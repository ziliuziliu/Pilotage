package com.eis.transaction.repository;

import com.eis.transaction.entity.Blotter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlotterRepository extends JpaRepository<Blotter,String> {
    List<Blotter> findAllBySellNameAndSellCompany(String sellName,String sellCompany);

    List<Blotter> findAllByBuyNameAndBuyCompany(String buyName,String buyCompany);
}
