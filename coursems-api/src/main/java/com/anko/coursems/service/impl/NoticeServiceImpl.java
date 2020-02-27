package com.anko.coursems.service.impl;

import com.anko.coursems.dao.NoticeMapper;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.service.INoticeService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    public Notice addNotice(Notice notice) {
        notice.setNoticeId(RandomStringUtils.randomAlphanumeric(20));
        noticeMapper.add(notice);
        return notice;
    }

    public int deleteNotice(String id) {
        return noticeMapper.delete(id);
    }

}
