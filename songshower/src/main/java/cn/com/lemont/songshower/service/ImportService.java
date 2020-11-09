package cn.com.lemont.songshower.service;

import cn.com.lemont.songshower.VO.DBArtistInfoVO;
import cn.com.lemont.songshower.VO.DBSongInfoVO;
import cn.com.lemont.songshower.VO.DZSongArtistVO;
import cn.com.lemont.songshower.VO.DZUserSongVO;
import cn.com.lemont.songshower.dao.DBArtistInfoMapper;
import cn.com.lemont.songshower.dao.DBSongInfoMapper;
import cn.com.lemont.songshower.dao.DZSongArtistMapper;
import cn.com.lemont.songshower.dao.DZUserSongMapper;
import cn.com.lemont.songshower.utils.PublicUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project liutao-songshower
 * @Package cn.com.lemont.songshower.service
 * @File ImportService.java
 * @Title ImportService
 * @Date 2020/10/29 18:52
 * @Description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @Author Liutao
 * @Version 1.0
 * @Copyright Copyright (c) 2020 六桃
 * @History 修订历史（历次修订内容、修订人、修订时间等）
 */
@Transactional
@Service
public class ImportService {
    @Autowired
    DBSongInfoMapper songInfoMapper;

    @Autowired
    DBArtistInfoMapper artistInfoMapper;

    @Autowired
    DZSongArtistMapper songArtistMapper;

    @Autowired
    DZUserSongMapper userSongMapper;

    /**
     * @Name importExcel
     * @Description 导入主程序，未完成
     * @Time 2020/11/6 17:15
     * @Param [userID, filePath]
     * @Return void
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    public void importExcel(Integer userID,String filePath) throws Exception {
        Workbook sheets = readExcel(filePath);
//        String a = " ";
        if (PublicUtils.isNull(sheets)) {
            throw new Exception("文件读取失败，请检查控制台!");
        }
//        assert sheets != null;
        Sheet sheet = sheets.getSheetAt(0);
        final List<DBSongInfoVO> songInfoVOOList = readSheet(sheet);
        writeSongInfoIntoData(userID, songInfoVOOList);
    }

    /**
     * @Name readExcel
     * @Description 根据文件路径读取excel内容
     * @Time 2020/10/29 15:15
     * @Param [filePath]
     * @Return org.apache.poi.ss.usermodel.Workbook
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private Workbook readExcel(String filePath) {
        String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook sheets = null;
            if (suffix.equals("xlsx")) {
                sheets = new XSSFWorkbook(fileInputStream);
            } else {
                sheets = new HSSFWorkbook(fileInputStream);
            }
            return sheets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Name readSheet
     * @Description 根据sheet页信息，获取歌曲信息
     * @Time 2020/10/29 15:16
     * @Param [sheet]
     * @Return java.util.List<cn.com.lemont.songshower.VO.DBSongInfoVO>
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private List<DBSongInfoVO> readSheet(Sheet sheet) throws Exception {
        List<DBSongInfoVO> songInfoVOOList = new ArrayList<DBSongInfoVO>();
        for (int line = 2; line <= sheet.getLastRowNum(); line++) {
            Row row = sheet.getRow(line);
            if (PublicUtils.isNull(row) || PublicUtils.isNull(row.getCell(0))) {
                break;
            }
            Cell titleCell = row.getCell(1);
            Cell artistCell = row.getCell(2);
            Cell albumCell = row.getCell(3);
            DBSongInfoVO songInfo = new DBSongInfoVO();
            songInfo.setTitle(getCellStringValue(titleCell));
            songInfo.setArtist(getCellStringValue(artistCell));
            songInfo.setAlbum(getCellStringValue(albumCell));
            songInfoVOOList.add(songInfo);
        }
        return songInfoVOOList;
    }

    /**
     * @Name writeSongInfoIntoData
     * @Description 歌曲信息入库
     * @Time 2020/10/29 15:16
     * @Param [singerID, songInfoVOList]
     * @Return void
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private void writeSongInfoIntoData(Integer userID, List<DBSongInfoVO> songInfoVOList) {
        for (DBSongInfoVO vo : songInfoVOList) {
            final String title = vo.getTitle();
            final String album = vo.getAlbum();
            //获取歌曲信息
            DBSongInfoVO songResult = songInfoMapper.selectByTitleAlbum(title, album);
            if (PublicUtils.isNotNull(songResult)) {
                //如果歌曲信息不为空，仅保存用户和歌曲的关系
                userSongMapper.insert(new DZUserSongVO(PublicUtils.getUUID(), userID, songResult.getSongId()));
                continue;
            }
            //如果结果为空，保存歌曲信息并获得歌曲id
            DBSongInfoVO insertVO = new DBSongInfoVO(null,title,album);
            songInfoMapper.insertAndGetId(insertVO);
            final Integer songId = insertVO.getSongId();
            //保存用户和歌曲的关系
            userSongMapper.insert(new DZUserSongVO(PublicUtils.getUUID(), userID, songId));

            //保存艺术家的信息
            final String[] artistArr = vo.getArtist().split("\\/");
            for (String artistName : artistArr) {
                artistName = artistName.replaceAll("\\u00A0", " ");
                final DBArtistInfoVO artistResultVO = artistInfoMapper.selectByArtistName(artistName);
                if (PublicUtils.isNotNull(artistResultVO)) {
                    //如果结果不为空，则进保存歌曲和艺术家的关系
                    songArtistMapper.insert(new DZSongArtistVO(PublicUtils.getUUID(), songId, artistResultVO.getArtistId()));
                    continue;
                }
                //如果结果为空，保存艺术家信息并获得艺术家id
                DBArtistInfoVO artistVO = new DBArtistInfoVO();
                artistVO.setArtistName(artistName);
                artistInfoMapper.insertAndGetId(artistVO);
                Integer artistID = artistVO.getArtistId();
                //保存歌曲和艺术家的关系
                songArtistMapper.insert(new DZSongArtistVO(PublicUtils.getUUID(), songId, artistID));
            }
        }
    }

    /**
     * @Name getCellStringValue
     * @Description 根据单元格获取单元格字符串信息
     * @Time 2020/10/29 15:17
     * @Param [cell]
     * @Return java.lang.String
     * @Author Liutao
     * @History 修订历史（历次修订内容、修订人、修订时间等）
     */
    private String getCellStringValue(Cell cell) throws Exception {
        if (null == cell) {
            return "";
        }
        String value = "";
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                //TODO
                break;
            case NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    DecimalFormat df = new DecimalFormat("0");//格式化number String字符串
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    DecimalFormat df = new DecimalFormat(cell.getCellStyle().getDataFormatString());//格式化number String字符串
                    value = df.format(cell.getNumericCellValue());
                }
                break;
            case STRING:
                value = cell.getRichStringCellValue().getString().replaceAll("\\u00A0", " ").replace("  "," ");
                break;
            case FORMULA:
                throw new Exception("FORMULA Cell Type cannot be cast to String.");
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                boolean booleanValue = cell.getBooleanCellValue();
                if (booleanValue) {
                    value = "true";
                } else {
                    value = "false";
                }
                break;
            case ERROR:
                throw new Exception("ERROR Cell Type cannot be cast to String.");
        }
        return value;
    }

}
