package com.injoin.ijboss.repo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 删除标记（0、未删除，1、已删除）
     */
    @TableLogic(value = "0", delval = "1")
    @TableField("del_flag")
    private Integer delFlag;
}
