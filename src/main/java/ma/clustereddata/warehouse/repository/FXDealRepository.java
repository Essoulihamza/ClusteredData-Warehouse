package ma.clustereddata.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.clustereddata.warehouse.model.entities.FXDeal;

@Repository
public interface FXDealRepository extends JpaRepository<FXDeal, String> {}
