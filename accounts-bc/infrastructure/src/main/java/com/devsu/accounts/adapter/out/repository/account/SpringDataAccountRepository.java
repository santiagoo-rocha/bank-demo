package com.devsu.accounts.adapter.out.repository.account;

import com.devsu.accounts.adapter.out.repository.account.dto.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, String> {
}
