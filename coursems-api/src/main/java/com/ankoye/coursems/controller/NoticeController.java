package com.ankoye.coursems.controller;

import com.ankoye.coursems.common.annotation.LogAnnotation;
import com.ankoye.coursems.common.result.Result;
import com.ankoye.coursems.common.support.BaseController;
import com.ankoye.coursems.entity.Notice;
import com.ankoye.coursems.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "班级通知管理")
@RestController
@RequestMapping("/api/v1/notices")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "添加通知")
    @LogAnnotation(operation = "添加通知")
    @PostMapping
    public Result sendNotice(@RequestBody Notice noticeForm) {
        Notice notice = noticeService.addNotice(noticeForm);
        return handleResult(notice);
    }

    @ApiOperation(value = "删除通知")
    @LogAnnotation(operation = "删除通知")
    @DeleteMapping("/{id}")
    public Result deleteNotice(@PathVariable String id) {
        return handleResult(noticeService.deleteByExId(id));
    }
}
