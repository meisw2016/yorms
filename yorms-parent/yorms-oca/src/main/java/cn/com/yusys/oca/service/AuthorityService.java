package cn.com.yusys.oca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;

import cn.com.yusys.oca.po.Authority;

public interface AuthorityService {
	
	public List<Authority> getAllAuthoriytList();
	
	public Authority findAuthorityById(long id);
	
	public void save(Authority authority);
	
	public void edit(Authority authority);
	
	public void deleteAuthority(long id);
	
	public Page<Authority> findAllAuthorityPage(Pageable pageable);
	
	public void dealGroupAuthorityGrantJSON(JSONObject jsonParam);
}
