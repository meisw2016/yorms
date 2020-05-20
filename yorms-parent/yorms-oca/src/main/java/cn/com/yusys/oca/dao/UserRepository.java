package cn.com.yusys.oca.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.com.yusys.oca.po.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    
    public User findByUserName(String username);

    void deleteById(Long id);
}