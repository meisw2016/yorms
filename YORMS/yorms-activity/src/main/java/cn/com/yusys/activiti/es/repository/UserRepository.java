package cn.com.yusys.activiti.es.repository;

import org.springframework.data.repository.CrudRepository;

import cn.com.yusys.activiti.es.entity.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity,String>{

}
