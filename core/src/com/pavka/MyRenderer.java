package com.pavka;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyRenderer extends HexagonalTiledMapRenderer {
    public MyRenderer(TiledMap map) {
        super(map);
    }

    @Override
    public void renderObject(MapObject object) {
        if (object instanceof TextureMapObject) {
            TextureMapObject textureObj = (TextureMapObject) object;

            this.getBatch().draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY(),
                    32, 32);
        }
    }

}
