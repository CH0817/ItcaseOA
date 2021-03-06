package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {

	@Override
	public List<Department> findTopList() {
		return sessionFactory.getCurrentSession().createQuery(//
		        "FROM Department d WHERE d.parent IS NULL")//
		        .list();
	}

	@Override
	public List<Department> findChildren(Long parentId) {
		return sessionFactory.getCurrentSession().createQuery(//
		        "FROM Department d WHERE d.parent.id = :parentId")//
		        .setParameter("parentId", parentId)//
		        .list();
	}

}
