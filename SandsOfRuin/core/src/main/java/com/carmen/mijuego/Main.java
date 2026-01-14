package com.carmen.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carmen.mijuego.screens.DesertClass;

public class Main extends Game {

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Pantalla inicial del juego
        setScreen(new DesertClass(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
