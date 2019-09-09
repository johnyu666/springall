package cn.johnyu;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@ContextConfiguration(classes = App.class) //java config
public class AppTest {
    @Autowired
    PropertyPlaceholderConfigurer placeholderConfigurer;
    @Test
    public void testApplicaition(){

        Assert.assertNotNull(placeholderConfigurer);
    }
}
