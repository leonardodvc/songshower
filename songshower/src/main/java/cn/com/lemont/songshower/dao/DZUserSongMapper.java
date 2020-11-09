package cn.com.lemont.songshower.dao;

import cn.com.lemont.songshower.VO.DZUserSongVO;
import java.util.List;

public interface DZUserSongMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(DZUserSongVO record);

    DZUserSongVO selectByPrimaryKey(String uuid);

    List<DZUserSongVO> selectAll();

    int updateByPrimaryKey(DZUserSongVO record);
}