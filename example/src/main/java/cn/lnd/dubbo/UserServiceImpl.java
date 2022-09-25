package cn.lnd.dubbo;

import cn.lnd.client.User;
import cn.lnd.client.UserService;

/**
 * @Author lnd
 * @Description
 * @Date 2022/9/26 0:21
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("张三");
        user.setGender("男");
        return user;
    }
}
