package com.carmen.mijuego.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.carmen.mijuego.Main;

public class DesertClass implements Screen {

    private final Main game;

    // ===== MUNDO VIRTUAL =====
    // Estas son las coordenadas con las que trabajas SIEMPRE
    private static final float WORLD_WIDTH = 1280f;
    private static final float WORLD_HEIGHT = 720f;

    private OrthographicCamera camera;
    private Viewport viewport;

    // Fondos
    private Texture cielo, nubes, ruinas, medio, cerca;

    // Personaje
    private Texture ayla;
    private float x, y;
    private float velocidad = 260f;

    // Parallax
    private float xNubes, xRuinas, xMedio, xCerca;
    private float vNubes = 20f, vRuinas = 40f, vMedio = 80f, vCerca = 120f;

    public DesertClass(Main game) {
        this.game = game;

        // Cámara + viewport (pantalla completa SIN bandas)
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);

        // Texturas
        cielo  = new Texture("backgrounds/desert/05cielo.png");
        nubes  = new Texture("backgrounds/desert/04nubes.png");
        ruinas = new Texture("backgrounds/desert/03ruinas.png");
        medio  = new Texture("backgrounds/desert/02medio.png");
        cerca  = new Texture("backgrounds/desert/01cerca.png");
        ayla   = new Texture("characters/ayla/ayla_defrente.png");

        // Filtros para escalado limpio
        setLinear(cielo);
        setLinear(nubes);
        setLinear(ruinas);
        setLinear(medio);
        setLinear(cerca);
        setLinear(ayla);

        // Posición inicial personaje
        x = 120;
        y = 80;
    }

    private void setLinear(Texture t) {
        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // Movimiento personaje (PC)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= velocidad * delta;

        // Parallax
        xNubes  -= vNubes  * delta;
        xRuinas -= vRuinas * delta;
        xMedio  -= vMedio  * delta;
        xCerca  -= vCerca  * delta;

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        // Cielo ocupa TODO el mundo
        game.batch.draw(cielo, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        drawLayer(nubes,  xNubes);
        drawLayer(ruinas, xRuinas);
        drawLayer(medio,  xMedio);
        drawLayer(cerca,  xCerca);

        // Personaje
        float scale = 0.4f;
        game.batch.draw(
            ayla,
            x,
            y,
            ayla.getWidth() * scale,
            ayla.getHeight() * scale
        );

        game.batch.end();
    }

    // Dibuja capas parallax ocupando toda la altura
    private void drawLayer(Texture texture, float offsetX) {
        float worldW = viewport.getWorldWidth();
        float worldH = viewport.getWorldHeight();

        float scale = worldH / texture.getHeight();
        float drawW = texture.getWidth() * scale;

        float x = offsetX % drawW;
        if (x > 0) x -= drawW;

        for (float xx = x; xx < worldW; xx += drawW) {
            game.batch.draw(texture, xx, 0, drawW, worldH);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        cielo.dispose();
        nubes.dispose();
        ruinas.dispose();
        medio.dispose();
        cerca.dispose();
        ayla.dispose();
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
