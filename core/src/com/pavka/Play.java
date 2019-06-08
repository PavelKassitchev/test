package com.pavka;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Play implements Screen, InputProcessor {

    private TiledMap map;
    private HexagonalTiledMapRenderer renderer;

    private OrthographicCamera camera;
    private Texture texture;
    private Sprite sprite;
    private SpriteBatch sb;
    MapLayer objectLayer;
    TiledMapTileLayer tileLayer;


    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        map = new TmxMapLoader().load("WarMap.tmx");
        renderer = new MyRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        Gdx.input.setInputProcessor(this);
        texture = new Texture("badlogic.jpg");
        sprite = new Sprite(texture);
        sprite.setSize(32f, 32f);
        TextureRegion tr = new TextureRegion(sprite.getTexture(), 32, 32);
        sb = new SpriteBatch();
        objectLayer = map.getLayers().get("ObjectLayer");
        TextureMapObject tmo = new TextureMapObject(sprite);
        tmo.setX(64);
        tmo.setY(64);

        objectLayer.getObjects().add(tmo);

        tileLayer = (TiledMapTileLayer)map.getLayers().get("TileLayer");
        TiledMapTileLayer.Cell cell = tileLayer.getCell(1, 1);
        TiledMapTileSet tileSet = map.getTileSets().getTileSet("WarTiles");
        String type = (String) cell.getTile().getProperties().get("type");

        System.out.println(type);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();
        /*sb.setProjectionMatrix(camera.combined);
        sprite.setSize(32f, 32f);
        sb.begin();
        sprite.draw(sb);
        sb.end();*/
        camera.update();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width / 2, height / 2, 0);
        camera.update();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,32);
        if(keycode == Input.Keys.DOWN) {
            camera.translate(0,-32);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (amount == 1)camera.zoom += 0.2;
        else camera.zoom -= 0.2;
        return true;
    }
}
