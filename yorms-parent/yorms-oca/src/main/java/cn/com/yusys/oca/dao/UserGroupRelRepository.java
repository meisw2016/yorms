package cn.com.yusys.oca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.yusys.oca.po.UserGroupRel;


public interface UserGroupRelRepository extends JpaRepository<UserGroupRel, Long>{
	
	void deleteByUserId(Long id);
	
	List<UserGroupRel> findByUserId(Long userId);
	List<UserGroupRel> findByGroupId(Long groupId);

}
