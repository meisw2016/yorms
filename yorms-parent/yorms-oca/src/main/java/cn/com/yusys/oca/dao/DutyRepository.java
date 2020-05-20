package cn.com.yusys.oca.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.yusys.oca.po.Duty;



public interface DutyRepository extends JpaRepository<Duty, Long> {

	Duty findById(long id);

    void deleteById(Long id);
}