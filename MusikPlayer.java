import javax.sound.sampled.*;
import java.io.File;

public class MusikPlayer {

    public static Clip clip;

    public static void spieleMusik() {

        try {

            File musikDatei = new File(
                    "C:\\Users\\ergen\\OneDrive\\Documents\\Minecraft1\\C418  Mice on Venus  Minecraft Volume Alpha.wav"
            );

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(musikDatei);

            clip = AudioSystem.getClip();

            clip.open(audioStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
