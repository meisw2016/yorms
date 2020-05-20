package cn.com.yusys.oca;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.yusys.oca.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	
	@Autowired
	private UserService userService;
	
	@Test
	public void dealUserAuthorityManageTest() {
		List<Long> testData = new ArrayList<Long>();
		
		Long userId = (long) 1;
		Long authorityId1 = (long) 1;
		Long authorityId2 = (long) 2;
		Long authorityId3 = (long) 3;
		
		testData.add(authorityId1);
		//testData.add(authorityId2);
		testData.add(authorityId3);

		userService.dealUserAuthorityManage(userId,testData);
		
	}

}
