package cn.com.yusys.oca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.yusys.oca.dao.OrganizationRepository;
import cn.com.yusys.oca.dao.OrganizationRepositorySweeper;
import cn.com.yusys.oca.po.Organization;

@Transactional
public class OrganizationRepositoryImpl implements OrganizationRepositorySweeper{
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
    private OrganizationRepository organizationRepository;
	
	@Override
	public List<Organization> getChildNodesByPreorderTraversal(Organization organization) {
		// TODO Auto-generated method stub
		
		Organization organizationFullProperties = (Organization) em.createQuery("SELECT org from Organization as org WHERE org.id = " + organization.getId()).getSingleResult();

		String HQLString = "SELECT org FROM Organization AS org WHERE org.leftId BETWEEN " + organizationFullProperties.getLeftId() + " AND " + organizationFullProperties.getRightId() 
							+ " AND org.id <> " + organization.getId() 
							+ " ORDER BY org.leftId ASC";
		 
		//String HQLString = "SELECT org from Organization as org";
		
		@SuppressWarnings("unchecked")
		List<Organization> organizations = em.createQuery(HQLString).getResultList();
		
		//List<Organization> organizations = new ArrayList<Organization>();
		//organizations.add(organization);
		
		return organizations;

	}

	
	@Override
	public List<Organization> getChildOrgsByPreorderTraversal(Organization organization) {
		// TODO Auto-generated method stub
		Organization organizationFullProperties = (Organization) em.createQuery("SELECT org from Organization as org WHERE org.id = " + organization.getId()).getSingleResult();

		String HQLString = "SELECT org FROM Organization AS org WHERE org.leftId BETWEEN " + organizationFullProperties.getLeftId() + " AND " + organizationFullProperties.getRightId() 
							+ " AND org.id <> " + organization.getId() 
							+ " AND org.department = 0"
							+ " ORDER BY org.leftId ASC";
		 
		
		@SuppressWarnings("unchecked")
		List<Organization> organizations = em.createQuery(HQLString).getResultList();
		
		//List<Organization> organizations = new ArrayList<Organization>();
		//organizations.add(organization);
		
		return organizations;
	}
	
	
	@Override
	public void addChildOrg(Organization parent, Organization child) {
		// TODO Auto-generated method stub
		Organization organizationParentFullProperties = (Organization) em.createQuery("SELECT org from Organization as org WHERE org.id = " + parent.getId()).getSingleResult();
		
		String HQLString1 = "UPDATE	Organization org SET org.rightId = (org.rightId + 2) WHERE org.rightId >= " + organizationParentFullProperties.getRightId();
		String HQLString2 = "UPDATE	Organization org SET org.leftId = (org.leftId + 2) WHERE org.leftId >= " + organizationParentFullProperties.getRightId();
		
		em.createQuery(HQLString1).executeUpdate();
		em.createQuery(HQLString2).executeUpdate();
		
		child.setLeftId(organizationParentFullProperties.getRightId());
		child.setRightId(organizationParentFullProperties.getRightId() + 1);
		
	 	Organization childSaved = organizationRepository.save(child);
		
		String hQLLayer = "SELECT COUNT(*) FROM Organization as org WHERE org.leftId <= " + childSaved.getLeftId() + " AND org.rightId >= " + childSaved.getRightId();
		
		long layer = (long) em.createQuery(hQLLayer).getSingleResult();
		
		childSaved.setLayer(layer);
		
		organizationRepository.save(child);
	}

	@Override
	public void deleteOrg(Long id) {
		// TODO Auto-generated method stub
		Organization organizationForDel = (Organization) em.createQuery("SELECT org from Organization as org WHERE org.id = " + id).getSingleResult();
		
		long lft = organizationForDel.getLeftId();
		long rft = organizationForDel.getRightId();	
		
		
		
		String hQLDelete = "DELETE FROM Organization org WHERE org.leftId >= "+ lft +" AND org.rightId <= " + rft;
		
		String HQLString1 = "UPDATE	Organization org SET org.leftId = (org.leftId - " + (rft - lft + 1) + ") WHERE org.leftId >= " + lft;
		String HQLString2 = "UPDATE	Organization org SET org.rightId = (org.rightId - " + (rft - lft + 1) + ") WHERE org.rightId >= " + rft;
		
		em.createQuery(hQLDelete).executeUpdate();
		em.createQuery(HQLString1).executeUpdate();
		em.createQuery(HQLString2).executeUpdate();
		
		
	}



	
	
	

}
