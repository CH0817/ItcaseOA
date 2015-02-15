package cn.itcast.oa.base;

import java.util.List;

public interface DaoSupport<T> {
	/**
	 * 保存實體
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 刪除實體
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新實體
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查詢
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 按id查詢
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 查詢所有
	 * 
	 * @return
	 */
	List<T> findAll();
}
