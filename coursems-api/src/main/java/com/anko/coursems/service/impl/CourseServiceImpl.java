package com.anko.coursems.service.impl;

import com.anko.coursems.common.util.FileUtils;
import com.anko.coursems.core.BaseService;
import com.anko.coursems.dao.CourseMapper;
import com.anko.coursems.dao.MemberMapper;
import com.anko.coursems.dao.NoticeMapper;
import com.anko.coursems.dao.ResourceMapper;
import com.anko.coursems.entity.Course;
import com.anko.coursems.entity.Member;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.CourseDTO;
import com.anko.coursems.service.CourseService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl extends BaseService<Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Course> getAllCourses(String userId) {
        List<Course> joinList = courseMapper.joinList(userId);
        joinList.addAll(courseMapper.createList(userId));
        return joinList;
    }

    @Override
    public CourseDTO getCourseDetail(String clazzId) {
        return courseMapper.getCourseDetail(clazzId);
    }

    @Override
    public Course searchCourse(String courseNum) {
        return courseMapper.selectOne(new Course().setCourseNum(courseNum));
    }

    @Override
    public Course createCourse(Course course) {
        course.setCourseId(RandomStringUtils.randomAlphanumeric(20));
        course.setCourseNum(RandomStringUtils.randomNumeric(6));
        course.setCoursePic("public/pic_default.jpeg");
        courseMapper.insert(course);
        return course;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCourse(String id) {
        memberMapper.delete(new Member().setCourseId(id));
        resourceMapper.delete(new Resource().setCourseId(id));
        noticeMapper.delete(new Notice().setCourseId(id));
        courseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean toggleAppraise(String id) {
        Course course = courseMapper.selectById(id);
        // 跟新数据库
        Course tmp = new Course();
        tmp.setCourseId(id);
        tmp.setAppraise(!course.getAppraise());
        courseMapper.updateById(tmp);
        return tmp.getAppraise();
    }

    @Override
    public String uploadPic(String id, MultipartFile file) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "pic_" + format.format(new Date()) + ".jpeg";
        String path = FileUtils.storeFile(file, fileName, FileUtils.getUserPath(id));
        // 存入数据库
        Course course = new Course();
        course.setCourseId(id);
        course.setCoursePic(path);
        courseMapper.updateById(course);
        return path;
    }

}
