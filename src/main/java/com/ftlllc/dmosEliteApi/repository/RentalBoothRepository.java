package com.ftlllc.dmosEliteApi.repository;

import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalBoothRepository extends JpaRepository<RentalBooth, Integer>, PagingAndSortingRepository<RentalBooth, Integer>
{
    Optional<RentalBooth> findOneByRentalBoothId(Integer rentalBoothId);
}
