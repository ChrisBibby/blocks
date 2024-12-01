package uk.co.chrisbibby.blocks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
  private final Random r = new Random();
  private final List<Block> blocks = new ArrayList<>();

  private ShapeRenderer shape;
  private Ball ball;
  private Paddle paddle;

  @Override
  public void create() {
    super.create();
    Gdx.graphics.setWindowedMode(800, 900);
    shape = new ShapeRenderer();

    int blockWidth = 63;
    int blockHeight = 20;

    for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
      for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
        blocks.add(new Block(x, y, blockWidth, blockHeight));
      }
    }

    ball = new Ball(r.nextInt(Gdx.graphics.getWidth()) + 10, r.nextInt(Gdx.graphics.getHeight()) + 10, r.nextInt(50) + 1, r.nextInt(10) + 1, r.nextInt(10) + 1);
    paddle = new Paddle(50, 50, 100, 25);
  }

  @Override
  public void render() {
    super.render();
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    shape.begin(ShapeRenderer.ShapeType.Filled);
    ball.update();
    ball.checkCollision(paddle);
    ball.draw(shape);
    paddle.update();

    for (Block block : blocks) {
      block.draw(shape);
      ball.checkCollision(block);
    }

    for (int i = 0; i < blocks.size(); i++) {
      Block b = blocks.get(i);
      if (b.isDestroyed()) {
        blocks.remove(b);
        i--;
      }
    }

    paddle.draw(shape);
    shape.end();
  }
}
