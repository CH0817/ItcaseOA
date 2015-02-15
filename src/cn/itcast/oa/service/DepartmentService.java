package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Department;

public interface DepartmentService {

	List<Department> findAll();

	void delete(Long id);

	void save(Department model);

	Department getById(Long id);

	void update(Department department);

	/**
	 * 查詢頂級部門列表
	 * 
	 * @author Rex
	 *
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查詢子部門列表
	 * 
	 * @author Rex
	 *
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
