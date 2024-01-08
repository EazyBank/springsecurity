package org.densoft.springsecurity.service;

import org.densoft.springsecurity.model.Notice;
import org.densoft.springsecurity.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    public List<Notice> getNotices() {
        return noticeRepository.findAllActiveNotices();
    }
}
