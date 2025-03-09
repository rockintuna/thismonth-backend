package me.rockintuna.thismonthbe.repositosy;

import me.rockintuna.thismonthbe.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
