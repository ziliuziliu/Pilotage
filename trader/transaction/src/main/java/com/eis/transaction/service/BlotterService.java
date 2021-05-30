package com.eis.transaction.service;

import com.eis.transaction.entity.Blotter;

import java.util.List;

public interface BlotterService {
    List<Blotter> findByUserId(Integer userId);
}
