package cn.com.yusys.oca.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.yusys.oca.po.Authority;



public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Authority findById(long id);

    void deleteById(Long id);
}