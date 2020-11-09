package cn.com.lemont.songshower.dao;

import cn.com.lemont.songshower.VO.DZSongArtistVO;
import java.util.List;

public interface DZSongArtistMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(DZSongArtistVO record);

    DZSongArtistVO selectByPrimaryKey(String uuid);

    List<DZSongArtistVO> selectAll();

    int updateByPrimaryKey(DZSongArtistVO record);
}