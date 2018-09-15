import java.net.URL;
import java.applet.AudioClip;

public class musicOn {

    URL url;
    static startingPoint sp;
    static AudioClip music, bounce, wasted;
    static int level = 1;

    public musicOn(startingPoint sp) {

        try {
            url = sp.getDocumentBase();
        } catch (Exception e) {
            //TODO: handle exception 
        }

        music = sp.getAudioClip(url, "Music/music.au");
        bounce = sp.getAudioClip(url, "Music/bounce.au");
        //		wasted=sp.getAudioClip(url,"Music/wasted.au");
        this.sp = sp;
        // TODO Auto-generated constructor stub
	}
	
    public static int getLevel() {
        return level;
    }
}