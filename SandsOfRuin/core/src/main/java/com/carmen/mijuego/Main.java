package com.carmen.mijuego;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    //imagenes de fondo, por orden 1 es el que esta mas cerca y 5 el que esta mas lejos para hacer el efecto paralax
    private Texture cerca, medio, ruinas, nubes, cielo;

    //imagenes del personaje
    private Texture ayla_defrente;
    private float personajeX, personajeY;
    private float velocidadPersonaje = 200f;

    //posiciones de los fondos
    private float x1, x2, x3, x4, x5;

    //velocidades para los fondos
    private float v1 = 120f;
    private float v2 = 80f;
    private float v3 = 40f;
    private float v4 = 20f;
    private float v5 = 10f;

    @Override
    public void create() {
        batch = new SpriteBatch();

        ayla_defrente = new Texture("ayla_defrente.png");
        personajeX = 100; // posición inicial X
        personajeY = 50;  // posición inicial Y

        cerca = new Texture("01cerca.png");
        medio = new Texture("02medio.png");
        ruinas = new Texture("03ruinas.png");
        nubes = new Texture("04nubes.png");
        cielo = new Texture("05cielo.png");

        x1 = x2 = x3 = x4 = x5 = 0;


    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        float delta = Gdx.graphics.getDeltaTime();

        // Mover cada capa
        x1 -= v1 * delta;
        x2 -= v2 * delta;
        x3 -= v3 * delta;
        x4 -= v4 * delta;
        x5 -= v5 * delta;

        // Mover personaje con el teclado
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) personajeX += velocidadPersonaje * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) personajeX -= velocidadPersonaje * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) personajeY += velocidadPersonaje * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) personajeY -= velocidadPersonaje * delta;

        // Limitar dentro de la pantalla
        personajeX = Math.max(0, Math.min(personajeX, Gdx.graphics.getWidth() - ayla_defrente.getWidth()));
        personajeY = Math.max(0, Math.min(personajeY, Gdx.graphics.getHeight() - ayla_defrente.getHeight()));

        batch.begin();

        drawLayer(cielo, x5);
        drawLayer(nubes, x4);
        drawLayer(ruinas, x3);
        drawLayer(medio, x2);
        drawLayer(cerca, x1);

        // Dibujar personaje
        float escala = 0.4f; // 50% del tamaño original
        batch.draw(ayla_defrente, personajeX, personajeY,
            ayla_defrente.getWidth() * escala,
            ayla_defrente.getHeight() * escala);
        batch.end();
    }

    private void drawLayer(Texture texture, float x) {
        float width = texture.getWidth();

        // Normaliza x para que siempre esté entre -width y 0
        x = x % width;

        // Primera imagen
        batch.draw(texture, x, 0);

        // Segunda imagen a la derecha
        batch.draw(texture, x + width, 0);

        // Reiniciar cuando salga de pantalla
        if (x <= -width) {
            if (texture == cerca) x1 = 0;
            if (texture == medio) x2 = 0;
            if (texture == ruinas) x3 = 0;
            if (texture == nubes) x4 = 0;
            if (texture == cielo) x5 = 0;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        cerca.dispose();
        medio.dispose();
        ruinas.dispose();
        nubes.dispose();
        cielo.dispose();
        ayla_defrente.dispose();

    }
}
