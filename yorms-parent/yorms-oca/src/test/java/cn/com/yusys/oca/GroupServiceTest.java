package cn.com.yusys.oca;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.yusys.oca.po.Group;
import cn.com.yusys.oca.service.GroupService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceTest {

	@Autowired
	private GroupService groupService;

	@Test
	public void getAllGroupListTest() {
		Group group = groupService.getAllGroupList().get(0);
		Assert.assertThat(group.getGroupName(), is("系统管理员组"));
	}
	
	@Test
	public void dealGroupAuthorityGrantTest() {
		Map<Long, Long> testData = new HashMap<Long, Long>();
		Long keyLong = (long) 1;
		Long valueLong = (long) 2;
		testData.put(keyLong, valueLong);
		
		groupService.dealGroupAuthorityGrant(testData);
		
	}
}
