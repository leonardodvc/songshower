package cn.com.lemont.songshower.dao;

import cn.com.lemont.songshower.VO.DBUserInfoVO;
import java.util.List;

public interface DBUserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(DBUserInfoVO record);

    DBUserInfoVO selectByPrimaryKey(Integer userId);

    List<DBUserInfoVO> selectAll();

    int updateByPrimaryKey(DBUserInfoVO record);

    DBUserInfoVO selectByUserName(String userName);
}