package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public BaseDaoImpl() {
		// 使用反射技術得到T的真實類型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 獲取當前new的對象的 泛型的父類 類型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 獲取第一個類型參數的真實類型
		System.out.println("clazz ---> " + clazz);
	}

	/**
	 * 獲取當前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	public T getById(Long id) {
		if (id != null) {
			return (T) getSession().get(clazz, id);
		} else {
			return null;
		}
	}

	public List<T> getByIds(Long[] ids) {
		return getSession().createQuery(//
		        "FROM User WHERE id IN (:ids)")//
		        .setParameterList("ids", ids)//
		        .list();
	}

	public List<T> findAll() {
		return getSession().createQuery(//
		        "FROM " + clazz.getSimpleName())//
		        .list();
	}

}