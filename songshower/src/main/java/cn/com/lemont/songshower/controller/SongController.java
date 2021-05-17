package cn.com.lemont.songshower.controller;

import cn.com.lemont.songshower.VO.DBSongInfoVO;
import cn.com.lemont.songshower.VO.DBUserInfoVO;
import cn.com.lemont.songshower.service.ImportService;
import cn.com.lemont.songshower.service.SongService;
import cn.com.lemont.songshower.service.UserService;
import cn.com.lemont.songshower.utils.PublicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project liutao-personal
 * @Package cn.com.lemont.songshower.controller
 * @File SongController.java
 * @Title SongController
 * @Date 2020/10/21 13:52
 * @Description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @Author Liutao
 * @Version 1.0
 * @Copyright Copyright (c) 2020 六桃
 * @History 修订历史（历次修订内容、修订人、修订时间等）
 */
@RestController
@RequestMapping("/songshower")
public class SongController {
    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    @Autowired
    private ImportService importService;

    /**
     * @Name queryByUser
     * @Description 通过用户名和参数查询歌曲清单
     * @Time 2020/11/5 18:52
     * @Param [username, jsonMap]
     * @Return java.util.List<cn.com.lemont.songshower.VO.DBSongInfoVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    @CrossOrigin
    @PostMapping(value = "/show")
    public List<DBSongInfoVO> queryByUser(@RequestParam(value = "username") String username, @RequestBody Map jsonMap) {
        if (PublicUtils.isNull(username)) {
            return new ArrayList<DBSongInfoVO>();
        }
        final String userName = username;
        DBUserInfoVO userInfo = userService.selectByUserName(userName);
        if (PublicUtils.isNull(userInfo)) {
            return new ArrayList<DBSongInfoVO>();
        }
        final Integer userId = userInfo.getUserId();
        final String type = (String) jsonMap.get("type");
        final String param = (String) jsonMap.get("param");
        final boolean randomFlag = (boolean) jsonMap.get("randomFlag");
        List<DBSongInfoVO> songList = new ArrayList<DBSongInfoVO>();
        if (randomFlag) {
            songList = songService.getRandomSongByUserId(userId);
        }else if(PublicUtils.isNull(param)){
            songList = songService.getAllSongByUserId(userId);
        }else{
            switch (type) {
                case "title":
                    songList = songService.getAllSongByUserIdAndTitle(userId, param);
                    break;
                case "album":
                    songList = songService.getAllSongByUserIdAndAlbum(userId, param);
                    break;
                case "artist":
                    songList = songService.getAllSongByUserIdAndArtist(userId, param);
                    break;
                case "none":
                    songList = songService.getAllSongByUserId(userId);
                    break;
            }
        }
        return songList;
    }

    /** 
     * @Name importExcel
     * @Description 导入功能，未完成，测试可用
     * @Time 2020/11/6 17:13
     * @Param []
     * @Return void
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    @RequestMapping("/import")
    public void importExcel() throws Exception {
        final String userName = "xiaohuang";
        final String filePath = "C:\\Users\\shanq\\Desktop\\歌单\\小黄歌单.xlsx";

        if (PublicUtils.isNull(userName)) {
            throw new Exception("Username is empty!");
        }
        DBUserInfoVO userInfo = userService.selectByUserName(userName);
        if (PublicUtils.isNull(userInfo)) {
            throw new Exception("Can't find user,username:"+userName);
        }
        final Integer userId = userInfo.getUserId();
        importService.importExcel(userId, filePath);
    }

}
