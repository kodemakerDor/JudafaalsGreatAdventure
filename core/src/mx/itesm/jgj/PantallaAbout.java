package mx.itesm.jgj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.UIManager;
import javax.xml.soap.Text;

import static mx.itesm.jgj.MenuJudafaals.ALTO;
import static mx.itesm.jgj.MenuJudafaals.ANCHO;


class PantallaAbout implements Screen {

    private final JudafaalsGreatAdventure jdj;

    // Camara
    private OrthographicCamera camara;
    private Viewport vista;
    private Texto texto;

    // Batch
    private SpriteBatch batch;

    //Imagenes
    private Texture foto1, foto2, foto3, foto4;
    private Texture fondoAbout;
    // Escena
    private Stage escenaAbout;

    public PantallaAbout(JudafaalsGreatAdventure judafaalsGreatAdventure) {

        this.jdj = judafaalsGreatAdventure;
    }

    public void CrearObjetos() {
        texto = new Texto();

    }

    @Override
    public void show() {

        crearCamara();
        batch = new SpriteBatch();
        CrearObjetos();
        crearImages();
        crearMenu();
    }

    private void crearMenu() {
        escenaAbout = new Stage(vista);

        // Creación de las texturas del botón de back
        TextureRegionDrawable trdBack = new TextureRegionDrawable(new TextureRegion(new Texture("homeNegro.png")));
        TextureRegionDrawable trdBackOnClick = new TextureRegionDrawable(new TextureRegion(new Texture("homeGris.png")));

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

        escenaAbout.addActor(backButton);
        Gdx.input.setInputProcessor(escenaAbout);

    }

    private void crearImages() {

        foto1 = new Texture("IMG_1862.png");
        foto2 = new Texture("dar.png");
        foto3 = new Texture("FotoJuan.png");
        foto4 = new Texture("bloggif_5a8615b78e32e.png");
        fondoAbout = new Texture("FondoAcercaDe.png");

    }

    private void crearCamara() {

        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO / 2, ALTO / 2, 0);
        camara.update();
        vista = new StretchViewport(ANCHO, ALTO, camara);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        //MUESTRA TEXTOS
        batch.begin();
        batch.draw(fondoAbout, 0, 0);
        batch.draw(foto1, ANCHO - ANCHO / 6, 2.1f * ALTO / 4);
        batch.draw(foto2, ANCHO - ANCHO / 6, 1.4f * ALTO / 4);
        batch.draw(foto3, ANCHO - ANCHO / 6, 0.7f * ALTO / 4);
        batch.draw(foto4, ANCHO - ANCHO / 6, 0.0f * ALTO / 4);

        texto.mostrarMensaje(batch, "Desarrolladores:", ANCHO / 2 - ANCHO / 6, 3.5f * ALTO / 4);
        texto.mostrarMensaje(batch, "Fabian Camp Mussa - ISC", ANCHO / 2 - ANCHO / 6 - 30, 2.5f * ALTO / 4);
        texto.mostrarMensaje(batch, "Darwin Chavez Salas - ISC", ANCHO / 2 - ANCHO / 6, 1.8f * ALTO / 4);
        texto.mostrarMensaje(batch, "Juan Jose Aguilar Hernandez - LAD", ANCHO / 2 - ANCHO / 6, 1.1f * ALTO / 4);
        texto.mostrarMensaje(batch, "Alfonso Alquicer Mendez - ISC", ANCHO / 2 - ANCHO / 6, 0.4f * ALTO / 4);

        batch.end();

        escenaAbout.draw();
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

        batch.dispose();
        foto1.dispose();
        foto2.dispose();
        foto3.dispose();
        foto4.dispose();
        fondoAbout.dispose();
        escenaAbout.dispose();
    }

}
