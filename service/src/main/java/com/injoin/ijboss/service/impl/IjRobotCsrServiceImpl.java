package com.injoin.ijboss.service.impl;

import com.injoin.ijboss.repo.entity.IjRobotCsr;
import com.injoin.ijboss.repo.mapper.IjRobotCsrMapper;
import com.injoin.ijboss.service.IjRobotCsrService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Relationship between robot and CSR (Customer Service Representation)
聊天机器人与客服的绑定关系表 服务实现类
 * </p>
 *
 * @author Payne.Liu
 * @since 2018-10-13
 */
@Service
public class IjRobotCsrServiceImpl extends ServiceImpl<IjRobotCsrMapper, IjRobotCsr> implements IjRobotCsrService {

}
