package com.eis.transaction.repository;

import com.eis.transaction.entity.Blotter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlotterRepository extends JpaRepository<Blotter,String> {
}
