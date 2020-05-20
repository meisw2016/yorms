package cn.com.yusys.oca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.yusys.oca.po.UserAuthorityRel;


public interface UserAuthorityRelRepository extends JpaRepository<UserAuthorityRel, Long>{
	
	void deleteByUserId(Long userId);
	
	List<UserAuthorityRel> findByUserId(Long userId);
	
	List<UserAuthorityRel> findByAuthorityId(Long authorityId);
}
