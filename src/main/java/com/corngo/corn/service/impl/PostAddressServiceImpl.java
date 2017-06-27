package com.corngo.corn.service.impl;

import com.corngo.corn.model.PostAddress;
import com.corngo.corn.mapper.PostAddressMapper;
import com.corngo.corn.service.IPostAddressService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 邮寄地址 服务实现类
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
@Service
public class PostAddressServiceImpl extends ServiceImpl<PostAddressMapper, PostAddress> implements IPostAddressService {
	
}
