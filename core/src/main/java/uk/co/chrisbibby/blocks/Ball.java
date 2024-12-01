package uk.co.chrisbibby.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Ball {
  private final Color color = Color.WHITE;
  private final int size;

  private int x;
  private int y;
  private int ySpeed;
  private int xSpeed;

  public Ball(final int x, final int y, final int size, final int xSpeed, final int ySpeed) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  public void update() {
    x += xSpeed;
    y += ySpeed;

    if (x < size || x + size > Gdx.graphics.getWidth()) {
      xSpeed = -xSpeed;
    }

    if (y < size || y + size > Gdx.graphics.getHeight()) {
      ySpeed = -ySpeed;
    }

  }

  public void draw(final ShapeRenderer shape) {
    shape.setColor(color);
    shape.circle(x, y, size);
  }

  public void checkCollision(final Paddle paddle) {
    if (collidesWith(paddle)) {
      ySpeed = -ySpeed;
    }
  }

  public void checkCollision(final Block block) {
    if (collidesWith(block)) {
      ySpeed = -ySpeed;
      block.setDestroyed(true);
    }
  }

  public boolean collidesWith(final Paddle paddle) {
    return x + size >= paddle.getX() && x - size <= paddle.getX() + paddle.getWidth() && y + size >= paddle.getY() && y - size <= paddle.getY() + paddle.getHeight();
  }

  public boolean collidesWith(final Block block) {
    return x + size >= block.getX() && x - size <= block.getX() + block.getWidth() && y + size >= block.getY() && y - size <= block.getY() + block.getHeight();
  }
}
