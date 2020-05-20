package cn.com.yusys.oca.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.yusys.oca.dao.AuthorityRepository;
import cn.com.yusys.oca.po.Authority;
import cn.com.yusys.oca.service.AuthorityService;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
    private AuthorityRepository authorityRepository;
	
	@Override
	public List<Authority> getAllAuthoriytList() {
		// TODO Auto-generated method stub
		return authorityRepository.findAll();
	}
	
	@Override
	public void edit(Authority authority) {
		authorityRepository.save(authority);
	}
	
	@Override
	public void deleteAuthority(long id) {
		authorityRepository.deleteById(id);
	}
	
	@Override
	public Authority findAuthorityById(long id) {
		// TODO Auto-generated method stub
		return authorityRepository.findById(id);
	}
	
	@Override
	public Page<Authority> findAllAuthorityPage(Pageable pageable) {
		return authorityRepository.findAll(pageable);
	}
	
	@Override
	public void save(Authority authority) {
		authorityRepository.save(authority);
		
	}
	
	@Override
	public void dealGroupAuthorityGrantJSON(JSONObject jsonParam) {
		// TODO Auto-generated method stub
		/*
		 * JSONObject roleIds = jsonParam.getJSONObject("roles"); List<Authority>
		 * authorities = new ArrayList<Authority>(); for (int i = 0; i < roleIds.size();
		 * i++) { Authority authority =
		 * this.findAuthorityById(roleIds.getIntValue("roleId" + i));
		 * authorities.add(authority); } Group group =
		 * this.findGroupById(Integer.parseInt(jsonParam.getString("id")));
		 * group.setAuthorities(authorities); this.save(group);
		 */
	}

}
