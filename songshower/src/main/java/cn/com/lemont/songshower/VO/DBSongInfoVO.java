package cn.com.lemont.songshower.VO;

public class DBSongInfoVO {
    private Integer songId;

    private String title;

    private String album;

    private String artist;

    public DBSongInfoVO() {}
    public DBSongInfoVO(Integer songId, String title, String album) {
        this.songId = songId;
        this.title = title;
        this.album = album;
    }


    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album == null ? null : album.trim();
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}