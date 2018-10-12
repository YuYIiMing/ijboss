package com.injoin.ijboss.repo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.injoin.ijboss.repo.entity.BaseEntity;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Relationship between robot and CSR (Customer Service Representation)
聊天机器人与客服的绑定关系表
 * </p>
 *
 * @author Payne.Liu
 * @since 2018-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ij_robot_csr")
public class IjRobotCsr extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 机器人 tid
     */
    @TableField("robot_tid")
    private Long robotTid;
    /**
     * 机器人云信 id
     */
    @TableField("robot_yunxin_accid")
    private Long robotYunxinAccid;
    /**
     * 机器人云信 token
     */
    @TableField("robot_yunxin_token")
    private String robotYunxinToken;
    /**
     * 管理员 id
     */
    @TableField("ad_id")
    private Long adId;


}
