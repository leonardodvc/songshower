package cn.com.lemont.songshower.VO;

public class DZUserSongVO {
    private String uuid;

    private Integer userId;

    private Integer songId;

    public DZUserSongVO(String uuid, Integer singerID, Integer songId) {
        this.uuid = uuid;
        this.userId = singerID;
        this.songId = songId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }
}