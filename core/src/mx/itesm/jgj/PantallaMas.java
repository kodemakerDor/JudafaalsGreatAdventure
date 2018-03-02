package mx.itesm.jgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static mx.itesm.jgj.MenuJudafaals.ALTO;
import static mx.itesm.jgj.MenuJudafaals.ANCHO;

/**
 * Created by taylu on 23/02/2018.
 */

 public class PantallaMas implements Screen {

    private final JudafaalsGreatAdventure jdj;
    // Camara
    private OrthographicCamera camara;
    private Viewport vista;

    // Definimos imágenes
    private Texture fondoMas;

    // Batch
    private SpriteBatch batch;

    // Escenas
    private Stage escenaMas;

    public PantallaMas(JudafaalsGreatAdventure JudafaalsGreatAdventure) {
        this.jdj = JudafaalsGreatAdventure;
    }

    @Override
    public void show() {
        crearCamara();
        crearEscena();
        batch = new SpriteBatch();
        fondoMas = new Texture("Fondos/FondoMasS.png");

    }

    private void crearEscena() {
        escenaMas = new Stage(vista);

        TextureRegionDrawable trdBack = new TextureRegionDrawable(new TextureRegion(new Texture("Botones/homeNegro.png")));
        TextureRegionDrawable trdBackOnClick = new TextureRegionDrawable(new TextureRegion(new Texture("Botones/homeGris.png")));

        // Creción del botón back.
        ImageButton backButton = new ImageButton(trdBack, trdBackOnClick);
        backButton.setPosition(0, ALTO - backButton.getHeight());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.log("ClickListener", "Hizo click el usuario");
                // Cambia de pantalla, solo lo puede hacer 'juego' una escena no.
                jdj.setScreen(new MenuJudafaals(jdj));

            }
        });

        escenaMas.addActor(backButton);
        Gdx.input.setInputProcessor(escenaMas);

    }

    private void crearCamara() {
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO/2,ALTO/2, 0);
        camara.update();
        vista = new StretchViewport(ANCHO, ALTO, camara);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(fondoMas,0,0);
        batch.end();
        escenaMas.draw();

    }

    @Override
    public void resize(int width, int height) {
        vista.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        escenaMas.dispose();
        batch.dispose();
        fondoMas.dispose();

    }
}