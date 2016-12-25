package liuzongzong.service;

import liuzongzong.entity.User;
import liuzongzong.entity.UserDetail;
import liuzongzong.util.BizzException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liuyz on 2016/12/25.
 */
public interface UserService {
    Integer save(User user, UserDetail userDetail) throws Exception;
    User query(User user, UserDetail userDetail) throws Exception;

}
