package cn.com.yusys.oca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.com.yusys.oca.po.Organization;


public interface OrganizationRepository extends JpaRepository<Organization, Long>,OrganizationRepositorySweeper{
	
	/*
	 * @Query("SELECT node FROM Organization AS node,Organization AS parent WHERE node.left_id BETWEEN parent.left_id AND parent.right_id AND parent. NAME = 'Food' ORDER BY node.left_id"
	 * )
	 */
	@Query("SELECT node FROM Organization AS node,Organization AS parent WHERE node.leftId BETWEEN parent.leftId AND parent.rightId AND parent.id = 1 ORDER BY node.leftId")
	public List<Organization> getOrganizationsByPreorderTraversal();
	
	
	@Query("SELECT node FROM Organization AS node,Organization AS parent WHERE node.leftId BETWEEN parent.leftId AND parent.rightId AND parent.id = 1 AND node.department = 0  ORDER BY node.leftId")
	public List<Organization> getOrganizationsExcludeDepartmentByPreorderTraversal();
	
	@Query("SELECT node FROM Organization AS node WHERE node.leftId < :leftId AND node.rightId > :rightId ORDER BY node.leftId")
	public List<Organization> getOrgPath(@Param("leftId") long leftId,@Param("rightId") long rightId);
	
	
}
