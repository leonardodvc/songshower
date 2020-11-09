package cn.com.lemont.songshower.dao;

import cn.com.lemont.songshower.VO.DBSongInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DBSongInfoMapper {
    int deleteByPrimaryKey(Integer songId);

    int insert(DBSongInfoVO record);

    DBSongInfoVO selectByPrimaryKey(Integer songId);

    List<DBSongInfoVO> selectAll();

    int updateByPrimaryKey(DBSongInfoVO record);

    DBSongInfoVO selectByTitleAlbum(@Param("title") String title, @Param("album") String album);

    int insertAndGetId(DBSongInfoVO record);

    List<DBSongInfoVO> selectByUserId(Integer userId);

    List<DBSongInfoVO> selectByUserIdAndSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    List<DBSongInfoVO> selectByUserIdAndTitle(@Param("userId") Integer userId, @Param("title") String title);

    List<DBSongInfoVO> selectByUserIdAndAlbum(@Param("userId") Integer userId, @Param("album") String album);

    List<DBSongInfoVO> selectByUserIdAndArtist(@Param("userId") Integer userId, @Param("artist") String artist);

    DBSongInfoVO selectRandomSongIdByUserId(Integer userId);
}