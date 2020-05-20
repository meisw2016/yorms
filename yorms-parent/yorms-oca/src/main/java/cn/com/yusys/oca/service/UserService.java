package cn.com.yusys.oca.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.yusys.oca.po.Authority;
import cn.com.yusys.oca.po.Group;
import cn.com.yusys.oca.po.User;

public interface UserService {

	public List<User> getUserList();

	public User findUserById(long id);

	public User findByUserName(String username);

	public void save(User user);

	public void edit(User user);

	public void deleteUser(long id);

	public Page<User> findAllUserPage(Pageable pageable);
	
	/**该方法处理用户的用户组管理,将自动根据新传入的参数调整用户的组分配,包括取消及新增
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public boolean dealUserGroupManage(Long userId,List<Long> groupIds);
	
	/**该方法处理用户的角色管理,将自动根据新传入的参数调整用户的角色分配,包括取消及新增
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public boolean dealUserAuthorityManage(Long userId,List<Long> authorityIds);
	
	
	/**获取用户的用户组
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public List<Group> findUserGroups(Long userId);
	
	
	/**获取用户的角色(包括用户的属组所赋予的角色)
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public List<Authority> findUserAuthoritys(Long userId);
	
	
	
	
	/**按用户组获取用户 不分页
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public List<User> findUsersByGroup(Long groupId);
	
	
	/**按角色获取用户 不分页
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public List<User> findUsersByAuthority(Long authorityId);
	
	
	
	
	/**按用户组获取用户 并分页
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public Page<User> findUsersByGroup(Long groupId,Pageable pageable);
	
	
	/**按角色获取用户 并分页
	 * @author Zhangal@yusys.com.cn
	 * @version 1.0
	 * */
	public Page<User> findUsersByAuthority(Long authorityId,Pageable pageable);
	
	
	public Page<User> findUserListByFilterCriteria(Long orgId,Long dptId,Long groupId, Long roleId, Boolean locked,Pageable pageable);
	
	

}
