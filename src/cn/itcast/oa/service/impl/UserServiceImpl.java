package cn.itcast.oa.service.impl;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;

@Service
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

}
