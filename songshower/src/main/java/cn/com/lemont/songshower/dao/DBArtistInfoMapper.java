package cn.com.lemont.songshower.dao;

import cn.com.lemont.songshower.VO.DBArtistInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DBArtistInfoMapper {
    int deleteByPrimaryKey(Integer artistId);

    int insert(DBArtistInfoVO record);

    DBArtistInfoVO selectByPrimaryKey(Integer artistId);

    List<DBArtistInfoVO> selectAll();

    int updateByPrimaryKey(DBArtistInfoVO record);

    DBArtistInfoVO selectByArtistName(@Param("artistName") String artistName);

    int insertAndGetId(DBArtistInfoVO record);
}