package cn.com.yusys.oca.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;

import cn.com.yusys.oca.po.Authority;
import cn.com.yusys.oca.po.Group;

public interface GroupService {
	
	public List<Group> getAllGroupList();
	
	public Group findGroupById(long id);
	
	public void save(Group group);
	
	public void edit(Group group);
	
	public void deleteGroup(long id);
	
	public Page<Group> findAllGroupPage(Pageable pageable);
	
	public void dealGroupAuthorityGrant(Map<Long, Long> groupAuthorityRelMap);
	
	/**获取用户组的角色
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public List<Authority> findGroupAuthorities(Long groupId);

}
