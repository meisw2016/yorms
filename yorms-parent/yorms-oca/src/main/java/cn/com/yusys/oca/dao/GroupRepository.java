package cn.com.yusys.oca.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.yusys.oca.po.Group;


public interface GroupRepository extends JpaRepository<Group, Long>{
	
	Group findById(long id);
	
    void deleteById(Long id);
}
