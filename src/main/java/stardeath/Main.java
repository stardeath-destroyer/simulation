package stardeath;

import stardeath.rendering.Renderer;

/**
 * The main entry point of our application.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {

    Controller<Renderer> controller = new Controller<>(new Renderer());

    while (true) {
      Thread.sleep(1000);
      controller.step();
      controller.draw();
    }
  }
}
