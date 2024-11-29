package fr.univartois.butinfo.r304.bomberman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.List;

public class SpriteAnimator {
    private final List<Image> frames;
    private int currentFrameIndex;
    private final Timeline animation;

    public SpriteAnimator(List<Image> frames, int frameDuration) {
        this.frames = frames;
        this.currentFrameIndex = 0;
        this.animation = new Timeline(new KeyFrame(Duration.millis(frameDuration), event -> nextFrame()));
        this.animation.setCycleCount(Timeline.INDEFINITE);
    }

    private void nextFrame() {
        currentFrameIndex = (currentFrameIndex + 1) % frames.size();
    }

    public Image getCurrentFrame() {
        return frames.get(currentFrameIndex);
    }

    public void start() {
        animation.play();
    }

    public void stop() {
        animation.stop();
    }
}