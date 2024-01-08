package org.densoft.springsecurity.controller;

import org.densoft.springsecurity.model.Notice;
import org.densoft.springsecurity.service.NoticeService;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getLoans() {
        return ResponseEntity.ok()
                .cacheControl(
                        CacheControl.maxAge(60, TimeUnit.SECONDS)).body(noticeService.getNotices()
                );
    }
}
