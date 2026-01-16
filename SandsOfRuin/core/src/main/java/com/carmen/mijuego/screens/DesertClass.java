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

    // ===== CONTROLES (solo visual) =====
    private Texture btnLeft, btnRight, btnGrenade, btnShoot, btnJump;

    // Tamaños UI (en coordenadas del mundo)
    private float uiSize;   // tamaño del botón
    private float uiMargin; // margen a borde
    private float uiGap;    // separación

    public DesertClass(Main game) {
        this.game = game;

        // Cámara + viewport (pantalla completa SIN bandas)
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);

        // Texturas fondos
        cielo  = new Texture("backgrounds/desert/05cielo.png");
        nubes  = new Texture("backgrounds/desert/04nubes.png");
        ruinas = new Texture("backgrounds/desert/03ruinas.png");
        medio  = new Texture("backgrounds/desert/02medio.png");
        cerca  = new Texture("backgrounds/desert/01cerca.png");

        // Personaje
        ayla   = new Texture("characters/ayla/ayla_defrente.png");

        // Controles (assets/controls/...)
        btnLeft    = new Texture("ui/controls/btn_move_left.png");
        btnRight   = new Texture("ui/controls/btn_move_right.png");
        btnGrenade = new Texture("ui/controls/btn_grenade.png");
        btnShoot   = new Texture("ui/controls/btn_shoot.png");
        btnJump    = new Texture("ui/controls/btn_jump.png");

        // Filtros para escalado limpio
        setLinear(cielo);
        setLinear(nubes);
        setLinear(ruinas);
        setLinear(medio);
        setLinear(cerca);
        setLinear(ayla);

        setLinear(btnLeft);
        setLinear(btnRight);
        setLinear(btnGrenade);
        setLinear(btnShoot);
        setLinear(btnJump);

        // Posición inicial personaje
        x = 120;
        y = 80;

        // Tamaños de UI
        uiSize = 120f;   // tamaño del botón (en mundo 1280x720)
        uiMargin = 25f;  // margen desde el borde
        uiGap = 18f;     // separación entre botones
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

        float worldW = viewport.getWorldWidth();
        float worldH = viewport.getWorldHeight();

        game.batch.begin();

        // Cielo ocupa TODO el mundo
        game.batch.draw(cielo, 0, 0, worldW, worldH);

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

        // ===== DIBUJAR CONTROLES (sin funcionalidad) =====

        // Left (esquina inferior izquierda)
        float leftX = uiMargin;
        float leftY = uiMargin;

        // Granada encima de Left
        float grenadeX = leftX;
        float grenadeY = leftY + uiSize + uiGap;

        // Right (esquina inferior derecha)
        float rightX = worldW - uiMargin - uiSize;
        float rightY = uiMargin;

        // Jump encima de Right
        float jumpX = rightX;
        float jumpY = rightY + uiSize + uiGap;

        // Shoot a la izquierda de Right
        float shootX = rightX - uiGap - uiSize;
        float shootY = rightY;

        // Dibujar (mismo tamaño para todos)
        game.batch.draw(btnLeft, leftX, leftY, uiSize, uiSize);
        game.batch.draw(btnGrenade, grenadeX, grenadeY, uiSize, uiSize);

        game.batch.draw(btnRight, rightX, rightY, uiSize, uiSize);
        game.batch.draw(btnJump, jumpX, jumpY, uiSize, uiSize);
        game.batch.draw(btnShoot, shootX, shootY, uiSize, uiSize);

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

        btnLeft.dispose();
        btnRight.dispose();
        btnGrenade.dispose();
        btnShoot.dispose();
        btnJump.dispose();
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
