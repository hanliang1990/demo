package org.demo.mybatis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = Application.class)  
public class ApplicationTests{
	
    @Autowired  
    private UserMapper userMapper;  
    
    @Test  
    public void testSys() {
//    	UserEntity ue = new UserEntity();
//    	ue.setNickName("11");
//    	ue.setPassWord("22");
//    	ue.setUserName("333");
//    	ue.setUserSex(UserSexEnum.MAN);
//        userMapper.insert(ue); 
    	
    	UserEntity ue1 = userMapper.getOne(28L);
    	System.out.println(ue1.getUserName());
    	
    	UserEntity ue2 = userMapper.getOne(28L);
    	System.out.println(ue2.getUserName());
    }  
  
} 