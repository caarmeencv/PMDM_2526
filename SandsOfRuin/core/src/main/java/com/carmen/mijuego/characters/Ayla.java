package com.carmen.mijuego.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Ayla {

    private float x, y;

    private float speed = 260f;
    private boolean facingRight = true;

    private Texture runSheet;
    private Animation<TextureRegion> runAnim;
    private float stateTime = 0f;

    private float scale = 0.4f;

    public Ayla(float startX, float startY) {
        this.x = startX;
        this.y = startY;

        runSheet = new Texture("characters/ayla/ayla_run.png");
        runSheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        int FRAME_WIDTH = 464;
        int FRAME_HEIGHT = 688;

        TextureRegion[][] tmp = TextureRegion.split(runSheet, FRAME_WIDTH, FRAME_HEIGHT);

        TextureRegion[] frames = new TextureRegion[tmp[0].length];
        for (int i = 0; i < tmp[0].length; i++) {
            frames[i] = tmp[0][i];
        }

        runAnim = new Animation<>(0.10f, frames);
    }

    public void update(float delta, boolean moveLeft, boolean moveRight) {
        boolean moving = false;

        if (moveRight) {
            x += speed * delta;
            facingRight = true;
            moving = true;
        }
        if (moveLeft) {
            x -= speed * delta;
            facingRight = false;
            moving = true;
        }

        x = MathUtils.clamp(x, 0f, 999999f);

        if (moving) stateTime += delta;
        else stateTime = 0f;
    }

    public void draw(SpriteBatch batch) {
        TextureRegion frame = runAnim.getKeyFrame(stateTime, true);

        float drawW = frame.getRegionWidth() * scale;
        float drawH = frame.getRegionHeight() * scale;

        if (facingRight) {
            batch.draw(frame, x, y, drawW, drawH);
        } else {
            batch.draw(frame, x + drawW, y, -drawW, drawH);
        }
    }

    public void dispose() {
        runSheet.dispose();
    }
}
