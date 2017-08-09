package touhou.animation;

import touhou.bases.FrameCounter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private final int totalFrame;
    private FrameCounter frameCounter;
    private FrameCounter currentFrame;
    private boolean resetFrame ;

    private ArrayList<Frame> frames = new ArrayList<Frame>();

    public Animation(BufferedImage[] frames, int countMax) {
        this.totalFrame = frames.length;
        //System.out.println(Integer.toString(totalFrame));
        this.currentFrame = new FrameCounter(totalFrame);
        //this.animationDirection = 1;
        this.frameCounter = new FrameCounter(countMax);
        //this.stopped = false;
        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], countMax);
        }
        this.resetFrame = false;
    }

    public FrameCounter getCurrentFrame() {
        return currentFrame;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void addFrame(BufferedImage frame, int duration){
        frames.add(new Frame(frame, duration));
    }

    public void reset(){
        frameCounter.reset();
        currentFrame.reset();
    }

    public BufferedImage getSprite(){
        //System.out.println("---- " + Integer.toString(currentFrame.getCount()));
        return frames.get(currentFrame.getCount()).getFrame();
    }

    public boolean isResetFrame() {
        return resetFrame;
    }

    public void update(){
        if (frameCounter.run()){
            frameCounter.reset();
            currentFrame.coolDown();
            //if (currentFrame.run()){ @@@@@@ Hoi 2 anh, cai nay # gi voi coolDown ma em bo vao thi sai
            //    currentFrame.reset();
            //}
        }
        if (frameCounter.getCount() == 0 && currentFrame.getCount() == 0)
            resetFrame = true;
    }
}
