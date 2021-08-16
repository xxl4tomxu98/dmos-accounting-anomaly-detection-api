package com.ftlllc.dmosEliteApi.repository;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountEntryRepository extends JpaRepository<AccountEntry, Integer>, JpaSpecificationExecutor<AccountEntry>
{

    @Query(value = "from AccountEntry a where a.createDate BETWEEN :startDate AND :endDate")
    List<AccountEntry> getAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

//    @Query(value = "select DISTINCT ON , count(a.accountEntryId) from AccountEntry a where a.createDate BETWEEN :startDate AND :endDate GROUP BY a.createDate")
//    Dictionary<LocalDate, Integer> getFrequencyCountBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
