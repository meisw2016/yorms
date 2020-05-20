package cn.com.yusys.oca.util;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;



public class PageUtil {

	public static <T> PageImpl<T> convertToPages(List<T> list, Pageable pageable) {

		// 手动分页
		if (pageable.getOffset() > list.size()) {
			long total = 0L;
			PageImpl<T> resultPage = new PageImpl<>(list, pageable, total);
			return resultPage;
		}
		
		if (pageable.getOffset() <= list.size() && pageable.getOffset() + pageable.getPageSize() > list.size()) {
		    List<T> thisPage = list.subList( (int) pageable.getOffset(), list.size());
		    PageImpl<T> resultPage = new PageImpl<>(thisPage, pageable, list.size());
		    return resultPage;
		}
		
		List<T> tList = list.subList((int) pageable.getOffset(), (int)pageable.getOffset() + pageable.getPageSize());

		PageImpl<T> resultPage = new PageImpl<>(tList, pageable, list.size());
		
		return resultPage;

	}
}
