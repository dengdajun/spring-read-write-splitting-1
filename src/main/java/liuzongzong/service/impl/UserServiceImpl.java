package liuzongzong.service.impl;

import liuzongzong.dao.CommonDao;
import liuzongzong.entity.User;
import liuzongzong.entity.UserDetail;
import liuzongzong.service.UserService;
import liuzongzong.util.BizzException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuyz on 2016/12/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CommonDao<User> userDao;
    @Autowired
    private CommonDao<UserDetail> userDetailDao;

    @Override
    public Integer save(User user, UserDetail userDetail) throws Exception {
        if(null != userDao.select(user)){
            throw new BizzException("0","手机号已存在.");
        }
        Integer insert = userDao.insert(user);
        if(0==insert){
            throw new BizzException("0","手机号已存在.");
        }
        Integer userId = user.getId();
        userDetail.setUserId(userId);
        insert = userDetailDao.insert(userDetail);
        if(0==insert){
            throw new BizzException("0","手机号已存在.");
        }
        return user.getId();
    }

    @Override
    public User query(User user, UserDetail userDetail) throws Exception {

        user = userDao.select(user);
        if (null == user) {
            throw new BizzException("0","账号或密码错误.");
        }
        userDetail.setUserId(user.getId());
        userDetail = userDetailDao.select(userDetail);
        if (null == userDetail) {
            throw new BizzException("0","账号或密码错误.");
        }
        return user;
    }

}
