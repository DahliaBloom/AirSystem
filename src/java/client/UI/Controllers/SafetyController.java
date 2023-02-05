package client.UI.Controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SafetyController extends Controller {


    @FXML
    private Button btn_play;

    @FXML
    private MediaView mv;

    private boolean playing = false;
    private MediaPlayer mediaplayer;


    @Override
    public void setup(boolean fadeAll) {
        final String VURL = Objects.requireNonNull(getClass().getClassLoader().getResource("client/UI/img/Safety Video.mp4")).toString();
        Media media = new Media(VURL);
        mediaplayer = new MediaPlayer(media);
        fadePane.setOpacity(fadeAll ? 0 : 1);
        minFadePane.setOpacity(fadeAll ? 1 : 0);
        mv.setMediaPlayer(mediaplayer);
        mv.requestFocus();
    }

    @Override
    protected void stopTimers() {
        mediaplayer.pause();
    }

    @FXML
    public void play_video() {
        if (playing) {
            mediaplayer.pause();
            playing = false;
        } else {
            mediaplayer.play();
            playing = true;
        }
    }

}