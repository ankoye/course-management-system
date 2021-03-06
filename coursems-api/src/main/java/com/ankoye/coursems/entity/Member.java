package com.ankoye.coursems.entity;

import com.ankoye.coursems.common.support.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ankoye@qq.com
 */
@Data
@Accessors(chain = true)
public class Member extends BaseEntity {

//    private static final long serialVersionUID = 5678845178960858841L;

    private String userId;

    private String courseId;

    private Integer arrive;

    private Integer resource;

    private Integer experience;

    private Integer score;

    private String remark;
}
