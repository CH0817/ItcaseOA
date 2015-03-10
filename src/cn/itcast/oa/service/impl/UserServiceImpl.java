package cn.itcast.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		// 使用密碼的MD5摘要對比
		String md5Digest = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery("FROM User WHERE loginName = ? AND password = ?")//
		        .setParameter(0, loginName)//
		        .setParameter(1, md5Digest)//
		        .uniqueResult();
	}

}
