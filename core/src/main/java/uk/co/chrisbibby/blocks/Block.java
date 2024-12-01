package uk.co.chrisbibby.blocks;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private boolean destroyed = false;

  public Block(final int x, final int y, final int width, final int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public void draw(final ShapeRenderer shape) {
    shape.rect(x, y, width, height);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public boolean isDestroyed() {
    return destroyed;
  }

  public void setDestroyed(final boolean destroyed) {
    this.destroyed = destroyed;
  }
}
