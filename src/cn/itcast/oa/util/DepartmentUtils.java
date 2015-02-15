package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtils {

	/**
	 * 遍歷部門樹，把所有部門遍歷出來放到同一個集合中返回，並且其中所有部門名稱都被修改了，以表示層次
	 * 
	 * @author Rex
	 *
	 * @param topList
	 * @return
	 */
	public static List<Department> getAllDepartment(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList, "→", list);
		return list;
	}

	/**
	 * 遍歷部門樹，把遍歷出的部門信息放到指定的集合中 * @author Rex
	 *
	 * @param topList
	 */
	private static void walkDepartmentTreeList(Collection<Department> topList, String prefix, List<Department> list) {
		for (Department top : topList) {
			// 頂點
			Department copy = new Department();// 使用副本，因為原對象在Session中
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			list.add(copy);// 把副本添加到同一個集合中
			// 子樹
			walkDepartmentTreeList(top.getChildren(), "　" + prefix, list);
		}
	}
}
