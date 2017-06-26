package com.corngo.corn.service.impl;

import com.corngo.corn.model.Users;
import com.corngo.corn.mapper.UsersMapper;
import com.corngo.corn.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hanfc
 * @since 2017-06-26
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
	
}
