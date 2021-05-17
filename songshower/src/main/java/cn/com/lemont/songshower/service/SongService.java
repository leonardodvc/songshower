package cn.com.lemont.songshower.service;

import cn.com.lemont.songshower.VO.DBSongInfoVO;
import cn.com.lemont.songshower.dao.DBSongInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project liutao-personal
 * @Package cn.com.lemont.songshower.service
 * @File SongService.java
 * @Title SongService
 * @Date 2020/10/21 13:53
 * @Description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @Author Liutao
 * @Version 1.0
 * @Copyright Copyright (c) 2020 六桃
 * @History 修订历史（历次修订内容、修订人、修订时间等）
 */
@Service
public class SongService {
    @Autowired
    DBSongInfoMapper songInfoMapper;

    public DBSongInfoVO getSongByID(Integer songID){
        DBSongInfoVO song = songInfoMapper.selectByPrimaryKey(songID);
        return song;
    }

    /**
     * @Name getAllSongByUserID
     * @Description 根据用户名称获取歌曲清单
     * @Time 2020/10/22 14:36
     * @Param [userID]
     * @Return java.util.List<cn.com.lemont.songshower.VO.SongVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public List<DBSongInfoVO> getAllSongByUserId(Integer userID){
        List<DBSongInfoVO> songList = songInfoMapper.selectByUserId(userID);
        List<DBSongInfoVO> resultList = this.getResultList(songList);
        return resultList;
    }

    /**
     * @Name getAllSongByUserIdAndTitle
     * @Description 通过用户id和标题查询歌曲
     * @Time 2020/11/6 17:07
     * @Param [userId, title]
     * @Return java.util.List<cn.com.lemont.songshower.VO.DBSongInfoVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public List<DBSongInfoVO> getAllSongByUserIdAndTitle(Integer userId, String title) {
        String titleTemp = title.replace("%","\\%");
        List<DBSongInfoVO> songList = songInfoMapper.selectByUserIdAndTitle(userId,titleTemp);
        List<DBSongInfoVO> resultList = this.getResultList(songList);
        return resultList;
    }

    /**
     * @Name getAllSongByUserIdAndAlbum
     * @Description 通过用户id和专辑查询歌曲
     * @Time 2020/11/6 17:08
     * @Param [userId, album]
     * @Return java.util.List<cn.com.lemont.songshower.VO.DBSongInfoVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public List<DBSongInfoVO> getAllSongByUserIdAndAlbum(Integer userId, String album) {
        String albumTemp = album.replace("%","\\%");
        List<DBSongInfoVO> songList = songInfoMapper.selectByUserIdAndAlbum(userId,albumTemp);
        List<DBSongInfoVO> resultList = this.getResultList(songList);
        return resultList;
    }

    /**
     * @Name getAllSongByUserIdAndArtist
     * @Description 通过用户id和艺术家查询歌曲
     * @Time 2020/11/6 17:08
     * @Param [userId, artist]
     * @Return java.util.List<cn.com.lemont.songshower.VO.DBSongInfoVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public List<DBSongInfoVO> getAllSongByUserIdAndArtist(Integer userId, String artist) {
        String artistTemp = artist.replace("%","\\%");
        List<DBSongInfoVO> songList = songInfoMapper.selectByUserIdAndArtist(userId,artistTemp);
        List<DBSongInfoVO> resultList = this.getResultList(songList);
        return resultList;
    }

    /**
     * @Name getRandomSongByUserId
     * @Description 通过用户id获取一条随机歌曲
     * @Time 2020/11/6 17:09
     * @Param [userId]
     * @Return java.util.List<cn.com.lemont.songshower.VO.DBSongInfoVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public List<DBSongInfoVO> getRandomSongByUserId(Integer userId) {
        DBSongInfoVO randomIdSong = songInfoMapper.selectRandomSongIdByUserId(userId);
        Integer randomId = randomIdSong.getSongId();
        List<DBSongInfoVO> songList = songInfoMapper.selectByUserIdAndSongId(userId,randomId);
        List<DBSongInfoVO> resultList = this.getResultList(songList);
        return resultList;
    }

    /**
     * @Name getResultList
     * @Description 对查询结果进行封装，主要是对artist进行封装
     * @Time 2020/11/6 11:26
     * @Param [songList]
     * @Return java.util.List<cn.com.lemont.songshower.VO.DBSongInfoVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private List<DBSongInfoVO> getResultList(List<DBSongInfoVO> songList){
        List<DBSongInfoVO> resultList = new ArrayList<DBSongInfoVO>();
        Map<Integer,Object> resultMap = new HashMap<Integer,Object>();
        for(DBSongInfoVO vo : songList){
            Integer songId = vo.getSongId();
            if (resultMap.containsKey(songId)){
                DBSongInfoVO tempVO = (DBSongInfoVO) resultMap.get(songId);
                tempVO.setArtist(tempVO.getArtist()+"/"+vo.getArtist());
                resultMap.put(songId,tempVO);
            }else {
                resultMap.put(songId,vo);
            }
        }
        for (Integer i : resultMap.keySet()){
            DBSongInfoVO tempVO = (DBSongInfoVO) resultMap.get(i);
            resultList.add(tempVO);
        }
        return resultList;
    }
}
