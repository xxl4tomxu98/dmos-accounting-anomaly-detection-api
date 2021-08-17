package com.ftlllc.dmosEliteApi.repository.rentalBooth;

import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalBoothRepository extends JpaRepository<RentalBooth, Integer>, JpaSpecificationExecutor<RentalBooth>
{}
