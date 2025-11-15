package com.devsu.accounts.adapter.out.repository.movement;

import com.devsu.accounts.adapter.out.repository.movement.dto.MovementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMovementRepository extends JpaRepository<MovementEntity, String> {
    Page<MovementEntity> findAllByAccountId(String accountId, Pageable pageable);
}
