import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import com.sun.jna.NativeLibrary;

import javax.swing.*;
import java.awt.*;

public class VideoPlayer {

    public static void main(String[] args) {
        // Визначте шлях до бібліотек VLC на вашому комп'ютері
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");

        // Створіть головний фрейм
        JFrame frame = new JFrame("Video Player in JPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Створіть панель, в якій розміститься відеоплеєр
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Створіть медіаплеєр
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        // Додайте медіаплеєр до панелі
        panel.add(mediaPlayerComponent, BorderLayout.CENTER);

        // Додайте панель до головного фрейму
        frame.add(panel);

        // Відтворіть ваше відео
        mediaPlayerComponent.getMediaPlayer().playMedia("/resources/end-of-the-game.mpg");

        // Показуємо вікно
        frame.setVisible(true);
    }
}
