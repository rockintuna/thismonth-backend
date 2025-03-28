package me.rockintuna.thismonthbe.repositosy;

import me.rockintuna.thismonthbe.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    @Query("select m from Memo m " +
            "join fetch m.user " +
            "where m.year=:year " +
            "and m.month=:month")
    List<Memo> findAllByYearAndMonth(Integer year, Integer month);
}
