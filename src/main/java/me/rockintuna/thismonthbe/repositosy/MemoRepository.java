package me.rockintuna.thismonthbe.repositosy;

import me.rockintuna.thismonthbe.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
