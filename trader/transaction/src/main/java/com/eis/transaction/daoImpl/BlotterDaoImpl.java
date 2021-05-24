package com.eis.transaction.daoImpl;

import com.eis.transaction.dao.BlotterDao;
import com.eis.transaction.entity.Blotter;
import com.eis.transaction.repository.BlotterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BlotterDaoImpl implements BlotterDao {
    @Autowired
    private BlotterRepository blotterRepository;

    @Override
    public Blotter saveBlotter(Blotter blotter) {
        return blotterRepository.save(blotter);
    }
}
