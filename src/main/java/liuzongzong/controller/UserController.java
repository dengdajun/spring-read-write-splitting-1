package liuzongzong.controller;

import liuzongzong.entity.User;
import liuzongzong.entity.UserDetail;
import liuzongzong.service.UserService;
import liuzongzong.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by liuyz on 2016/12/25.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public ResponseEntity<Map<String,Object>> register(User user, UserDetail userDetail) throws Exception{
        Integer save = userService.save(user, userDetail);
        return new ResponseEntity.Builder<Map<String,Object>>().success().data(user).build();
    }

    @RequestMapping("login")
    public ResponseEntity<Map<String,Object>> login(User user, UserDetail userDetail)  throws Exception{
        User query = userService.query(user, userDetail);
        return new ResponseEntity.Builder<Map<String,Object>>().success().data(query).build();
    }

    @RequestMapping("asdf1")
    public ResponseEntity<Map<String,Object>> asdf(User user, UserDetail userDetail) throws Exception{
        userService.asdf1();
        return new ResponseEntity.Builder<Map<String,Object>>().success().data(user).build();
    }

    @RequestMapping("asdf2")
    public ResponseEntity<Map<String,Object>> asdf2(User user, UserDetail userDetail)  throws Exception{
        userService.asdf2();
        return new ResponseEntity.Builder<Map<String,Object>>().success().data(user).build();
    }
}
