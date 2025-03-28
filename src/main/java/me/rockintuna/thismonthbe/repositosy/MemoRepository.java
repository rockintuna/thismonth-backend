package me.rockintuna.thismonthbe.repositosy;

import me.rockintuna.thismonthbe.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByYearAndMonth(Integer year, Integer month);
}
