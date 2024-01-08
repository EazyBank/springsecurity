package org.densoft.springsecurity.repository;

import org.densoft.springsecurity.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    @Query(value = "SELECT n FROM Notice n WHERE CURDATE() BETWEEN n.noticBegDt AND n.noticEndDt")
    List<Notice> findAllActiveNotices();
}