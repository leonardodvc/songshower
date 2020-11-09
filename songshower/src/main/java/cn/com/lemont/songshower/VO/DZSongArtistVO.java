package cn.com.lemont.songshower.VO;

public class DZSongArtistVO {
    private String uuid;

    private Integer songId;

    private Integer artistId;

    public DZSongArtistVO(String uuid, Integer songId, Integer artistId) {
        this.uuid = uuid;
        this.songId = songId;
        this.artistId = artistId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}