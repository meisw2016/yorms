package cn.com.yusys.oca.util;

import java.util.ArrayList;
import java.util.List;

import cn.com.yusys.file.util.Page;

public class test {
	/**
	 * 取分页后的结果集
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

	public static <T> List<T> startPage(List<T> list, Integer pageNum, Integer pageSize) {
		if (list == null || list.size() == 0) {
			return new ArrayList<T>();
		}

		Integer count = list.size();// 记录总数
		Integer pageCount = 0;// 页数

		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}

		int fromIndex = 0;// 开始索引
		int toIndex = 0;// 结束索引
		if (pageNum != pageCount) {
			fromIndex = (pageNum - 1) * pageSize;
			toIndex = fromIndex + pageSize;
		} else {
			fromIndex = (pageNum - 1) * pageSize;
			toIndex = count;
		}
		List<T> pageList = list.subList(fromIndex, toIndex);
		return pageList;

	}

	/**
	 * 
	 * 分页
	 * @param list
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * 
	 */

	public static <T> Page<T> startPages(List<T> list, Integer pageNum, Integer pageSize) {
		Page<T> page = new Page<T>();
		page.setTotal(list.size());
		page.setPageData(startPage(list, pageNum, pageSize));
		return page;

	}
}
