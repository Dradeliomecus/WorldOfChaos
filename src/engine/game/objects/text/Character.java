package engine.game.objects.text;

import engine.game.components.RenderedComponent;
import engine.game.objects.GameObject;
import engine.math.Vector2f;
import engine.rendering.Mesh;
import engine.rendering.Vertex;
import engine.rendering.texture.Material;
import engine.rendering.texture.Texture;

class Character extends GameObject {

    /**
     * Creates a new Character instance.
     *
     * @param texture Texture to use
     */
    Character(final Texture texture) {
        super("Character for Text", texture.getWidth(), texture.getHeight());

        final Mesh mesh = new Mesh();
        final Vertex[] vertices = new Vertex[] {
            new Vertex(new Vector2f(0, texture.getHeight()), new Vector2f(0, 1)),
            new Vertex(new Vector2f(0, 0), new Vector2f(0, 0)),
            new Vertex(new Vector2f(texture.getWidth(), 0), new Vector2f(1, 0)),
            new Vertex(new Vector2f(texture.getWidth(), texture.getHeight()), new Vector2f(1, 1))
        };
        mesh.setVertices(vertices);

        final RenderedComponent renderedComponent = new RenderedComponent(mesh, new Material(texture), texture.getWidth(), texture.getHeight());

        this.addComponent(renderedComponent);
    }

    /**
     * Returns the Character's texture width (in pixels).
     *
     * @return Character.pixelWidth
     */
    final int getPixelWidth() {
        if(this.getComponents().size() > 1) {
            System.err.println("Error: Character has more than one component: ");
            for(int i = 0; i < this.getComponents().size(); i++) {
                System.err.println("\t- " + this.getComponents().get(i));
            }
            new Exception().printStackTrace();
            System.exit(1);
        } else if(this.getComponents().size() == 0) {
            System.err.println("Error: Character has no component.");
            new Exception().printStackTrace();
            System.exit(1);
        } else if(!(this.getComponents().get(0) instanceof RenderedComponent)) {
            System.err.println("Error: Character component is not a rendered component: ");
            System.err.println(this.getComponents().get(0));
            new Exception().printStackTrace();
            System.exit(1);
        }

        return ((RenderedComponent) this.getComponents().get(0)).getMaterial().getTexture().getWidth();
    }

}