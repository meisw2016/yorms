package cn.com.yusys.oca;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.yusys.oca.po.Organization;
import cn.com.yusys.oca.service.OrganizationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationServiceTest {
	
	@Autowired
	private OrganizationService organizationService;
	
	@Test
	public void getChildDepartmentTest() {
		Organization organization = new Organization();
		
		organization.setId(6);
		
		List<Organization> resultList = organizationService.getChildDepartment(organization);
		
		//Assert.assertThat(resultList.size(), is(1));
		
		for (int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i).getName());
		}
		
		
	}

}
