package jukeboxapplication;
/**
 *
 * @author berkb
 */
public class music {
    private String musicName ;
    private String artistName ;
    private String musicType ;
    private String musicPath ;
    public music(String musicName, String artistName, String musicType,String musicPath) {
        this.musicName = musicName;
        this.artistName = artistName;
        this.musicType = musicType;
        this.musicPath = musicPath ; 
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }    
}
