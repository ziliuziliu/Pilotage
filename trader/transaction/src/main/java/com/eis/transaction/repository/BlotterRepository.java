package com.eis.transaction.repository;

import com.eis.transaction.entity.Blotter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlotterRepository extends JpaRepository<Blotter,String> {
}
