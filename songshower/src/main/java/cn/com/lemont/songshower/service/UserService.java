package cn.com.lemont.songshower.service;

import cn.com.lemont.songshower.VO.DBUserInfoVO;
import cn.com.lemont.songshower.dao.DBUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project liutao-songshower
 * @Package cn.com.lemont.songshower.service
 * @File UserService.java
 * @Title UserService
 * @Date 2020/11/5 16:16
 * @Description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @Author Liutao
 * @Version 1.0
 * @Copyright Copyright (c) 2020 六桃
 * @History 修订历史（历次修订内容、修订人、修订时间等）
 */
@Service
public class UserService {
    @Autowired
    DBUserInfoMapper userInfoMapper;

    /** 
     * @Name selectByUserName
     * @Description 通过用户名查询用户信息
     * @Time 2020/11/6 17:26
     * @Param [userName]
     * @Return cn.com.lemont.songshower.VO.DBUserInfoVO
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public DBUserInfoVO selectByUserName(String userName){
        DBUserInfoVO userInfo = userInfoMapper.selectByUserName(userName);
        return userInfo;
    }
}
