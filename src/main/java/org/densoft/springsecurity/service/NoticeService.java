package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Notice;
import org.densoft.springsecurity.repository.NoticeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepo noticeRepo;

    public NoticeService(NoticeRepo noticeRepo) {
        this.noticeRepo = noticeRepo;
    }


    public List<Notice> getNotices() {
        return noticeRepo.findAllActiveNotices();
    }
}
