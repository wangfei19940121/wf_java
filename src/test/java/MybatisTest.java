import com.wangfei.dao.UserDao;
import com.wangfei.domain.User;
import com.wangfei.domain.UserDif;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
   private  InputStream is;
   private  SqlSessionFactory factory;
   private  SqlSession sqlSession;
   private  UserDao mapper;
    @Before
   public void init() throws IOException {
        is = Resources.getResourceAsStream("MainSqlConfig");
        factory = new SqlSessionFactoryBuilder().build(is);
       sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(UserDao.class);
   }

    /**
     * 释放资源方法
     * @throws IOException
     */
   @After
   public void destroy() throws IOException {
       //释放资源
       sqlSession.close();
       is.close();
   }

    /**
     * 测试查询所有方法
     */
    @Test
    public void test1() throws IOException {

        List<User> lists = mapper.searchAll();
        for (User user : lists) {
            System.out.println(user);
        }

    }

    /**
     * 执行删除用户操作
     */
    @Test
    public void test2(){
        mapper.deleteUser(50);
        sqlSession.commit();
    }

    /**
     * 多条件查询，失败，必须把参数封装进一个实体类
     */
    @Test
    public void Test3(){
        User user = mapper.selectBy(46, "老王");
        System.out.println(user);
    }

    /**
     * 模糊查询
     */
    @Test
    public void test4(){
        List<User> users = mapper.selectLike("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 当实体类属性名与表格列名不同时查询
     */
    @Test
    public void test5(){
        List<UserDif> users = mapper.selectDif();
        for (UserDif ud : users) {
            System.out.println(ud);
        }

    }
}
