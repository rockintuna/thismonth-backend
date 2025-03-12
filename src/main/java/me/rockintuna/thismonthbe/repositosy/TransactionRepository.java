package me.rockintuna.thismonthbe.repositosy;

import me.rockintuna.thismonthbe.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t " +
            "join fetch t.user " +
            "where t.year = :year and t.month = :month " +
            "order by t.amount desc")
    List<Transaction> findAllByYearAndMonth(Integer year, Integer month);
}
