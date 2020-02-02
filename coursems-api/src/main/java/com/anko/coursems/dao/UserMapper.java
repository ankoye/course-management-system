package com.anko.coursems.dao;

import com.anko.coursems.entity.UserInfo;
import com.anko.coursems.model.UserCourse;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    // 查找用户
    UserInfo findUserById(String userId);
    // 更新用户信息
    int updateUserInfo(UserInfo userInfo);
    // 重置密码
    int updatePassword(UserInfo userInfo);
    // 绑定邮箱
    int bindEmail(UserInfo userInfo);
    // 绑定邮箱
    int bindPhone(UserInfo userInfo);
    // 判断邮箱是否存在
    UserInfo emailIsExist(String email);
    // 判断手机号是否被使用
    UserInfo phoneIsExist(String phoneNum);
}
