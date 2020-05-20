package cn.com.yusys.oca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.yusys.oca.po.GroupAuthorityRel;


public interface GroupAuthorityRelRepository extends JpaRepository<GroupAuthorityRel, Long>{
	
	List<GroupAuthorityRel> findByGroupId(Long groupId);
	
	List<GroupAuthorityRel> findByAuthorityId(Long authorityId);
	
	void deleteByGroupId(Long groupId);
}
