package com.yimei.cron.service;

import com.yimei.cron.basic.exception.BusinessException;
import com.yimei.cron.domain.User;
import com.yimei.cron.mapper.UserMapper;
import com.yimei.cron.util.Digests;
import com.yimei.cron.util.Encodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hongpf on 16/12/31.
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);


    //初始化密码
    //@Value("${preset_password}")
    private String PRESET_PASSWORD = "zrjtfm";


    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        //登陆密码
        byte[] passwordSalt = Digests.generateSalt(SALT_SIZE);
        user.setPasswordsalt(Encodes.encodeHex(passwordSalt));
        byte[] hashPassword = Digests.sha1(user.getPlainpassword().getBytes(), passwordSalt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }


    public static final int HASH_INTERATIONS = 1024;

    private static final int SALT_SIZE = 8;

    //insertSelective

    @Autowired
    private UserMapper userMapper ;

    public User validateLogin (String loginName , String plainPassword){

        User user = userMapper.loadByLoginName(loginName) ;
        if(user == null ){
           return  null ;
        }

        if(user.getPassword().length()<30){
            user.setPlainpassword(user.getPassword());
            entryptPassword(user);
            userMapper.updateByPrimaryKeySelective(user) ;
        }

        String credentials = Encodes.encodeHex(Digests.sha1(plainPassword.getBytes(),
                Encodes.decodeHex(user.getPasswordsalt()), HASH_INTERATIONS));
        if(user.getPassword().equals(credentials)){
            return  user ;
        }else  {
            throw new  BusinessException("用户名或者密码错误");
        }
    }

    @Transactional(readOnly = false)
    public void resetPassword(String plainPayPassword, Long userid) {
        byte[] payPasswordSalt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPayPassword.getBytes(), payPasswordSalt, HASH_INTERATIONS);
        userMapper.updatePassword(Encodes.encodeHex(hashPassword), Encodes.encodeHex(payPasswordSalt), userid);
    }

    public void addUser(User user) {
        User u = userMapper.loadByLoginName(user.getLoginname());

        if(u!=null){
            throw new BusinessException("已经存在的用户名") ;
        }

        entryptPassword(user) ;

        user.setCreatetime(LocalDateTime.now()) ;

        userMapper.insertSelective(user) ;
    }

    public void updateUser(User userinfo) {
        if(!StringUtils.isEmpty(userinfo.getPlainpassword())){
            entryptPassword(userinfo) ;
        }
        userMapper.updateByPrimaryKeySelective(userinfo);
    }

    public List<User> loadUserList() {
        return  userMapper.loadUserList();
    }
}