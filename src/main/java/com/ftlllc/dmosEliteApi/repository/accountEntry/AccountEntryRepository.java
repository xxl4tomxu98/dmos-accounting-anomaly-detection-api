package com.ftlllc.dmosEliteApi.repository.accountEntry;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountEntryRepository extends JpaRepository<AccountEntry, Integer>, JpaSpecificationExecutor<AccountEntry>
{}
