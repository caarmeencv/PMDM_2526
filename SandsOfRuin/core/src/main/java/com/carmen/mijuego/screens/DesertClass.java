package com.carmen.mijuego.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.carmen.mijuego.Main;

public class DesertClass implements Screen {

    private final Main game;

    // fondos
    private Texture cerca, medio, ruinas, nubes, cielo;

    // personaje
    private Texture ayla;
    private float x, y;
    private float velocidad = 200f;

    // parallax
    private float x1, x2, x3, x4;
    private float v1 = 120, v2 = 80, v3 = 40, v4 = 20;

    public DesertClass(Main game) {
        this.game = game;

        ayla = new Texture("ayla_defrente.png");
        x = 100;
        y = 50;

        cielo  = new Texture("fondos/05cielo.png");
        nubes  = new Texture("fondos/04nubes.png");
        ruinas = new Texture("fondos/03ruinas.png");
        medio  = new Texture("fondos/02medio.png");
        cerca  = new Texture("fondos/01cerca.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);

        // mover capas (cielo fijo)
        x1 -= v1 * delta;
        x2 -= v2 * delta;
        x3 -= v3 * delta;
        x4 -= v4 * delta;

        // movimiento personaje (PC)
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) x += velocidad * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT))  x -= velocidad * delta;

        game.batch.begin();

        game.batch.draw(cielo, 0, 0);
        drawLayer(nubes, x4);
        drawLayer(ruinas, x3);
        drawLayer(medio, x2);
        drawLayer(cerca, x1);

        game.batch.draw(ayla, x, y, ayla.getWidth()*0.4f, ayla.getHeight()*0.4f);

        game.batch.end();
    }

    private void drawLayer(Texture texture, float x) {
        float w = texture.getWidth();
        float xNorm = x % w;
        if (xNorm > 0) xNorm -= w;

        game.batch.draw(texture, xNorm, 0);
        game.batch.draw(texture, xNorm + w, 0);
    }

    @Override
    public void dispose() {
        cerca.dispose();
        medio.dispose();
        ruinas.dispose();
        nubes.dispose();
        cielo.dispose();
        ayla.dispose();
    }

    @Override public void show() {}
    @Override public void resize(int w, int h) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
