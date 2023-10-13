package com.progresoft.fxdealsdatawarehouse.repository;

import com.progresoft.fxdealsdatawarehouse.model.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FXDealRepository extends JpaRepository<FXDeal, String> {


    boolean existsByUniqueId(String uniqueId);


    Optional<FXDeal> findFXDealByUniqueId(String uniqueId);
}
