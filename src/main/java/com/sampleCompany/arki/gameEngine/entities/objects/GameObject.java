package com.sampleCompany.arki.gameEngine.entities.objects;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.Physics;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.collision.Collider;
import com.sampleCompany.arki.gameEngine.scenes.SceneManager;
import com.sampleCompany.arki.gameEngine.utils.Vec2;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.*;
import java.util.ArrayList;

/**
 * Entity subtype that is not used in GUIs, UIs, menus...
 * etc. Objects are entities that are placed within the
 * world of the scene, to be interacted with by other
 * entities, other than the direct user.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "2.1",
        releaseDate = "12/5/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public abstract class GameObject extends Entity
{

    // Gravity stuff
    protected float GRAVITATIONAL_ACCELERATION;
    protected float TERMINAL_VELOCITY;

    // Net Force (final speed)
    protected float horizontal_net_force;
    protected float vertical_net_force;

    protected ArrayList<Collider> collisions = new ArrayList<>();

    protected float mass;

    protected boolean isSolid;

    // class
    public GameObject(String name, String unlocalizedName, float x, float y, int width, int height, boolean isSolid)
    {
        super(name, unlocalizedName, x, y, width, height);

        this.isSolid = isSolid;

        if (this.getClass().isAnnotationPresent(Physics.class))
        {
            this.GRAVITATIONAL_ACCELERATION = this.getClass().getAnnotation(Physics.class).GRAVITATIONAL_ACCELERATION();
            this.TERMINAL_VELOCITY = this.getClass().getAnnotation(Physics.class).TERMINAL_VELOCITY();
            this.horizontal_net_force = 0;
            this.vertical_net_force = 0;
            this.mass = this.getClass().getAnnotation(Physics.class).mass();
        }
    }

    // Collision detection

    /**
     * Invokes appropriate collision methods.
     *
     * Only predicts when the object is destroyed
     * or once the object is ready to be moved.
     *
     * @return null if there is no collision
     */
    public Collider predictCollision(GameObject o)
    {
        if (o.equals(this))
            return null;

        Collider collider = null;
        Rectangle newBounds = new Rectangle((int) (getCurBounds().x + this.horizontal_net_force), (int) (getCurBounds().y + this.vertical_net_force), getWidth(), getHeight());

        if (newBounds.intersects(o.getCurBounds()))
        {
            //Collision type: up, down, left, right
            if (newBounds.y + newBounds.height > o.getCurBounds().y && newBounds.x + newBounds.width > o.getX() && newBounds.x < o.getX() + o.getWidth() && newBounds.y + newBounds.height < o.getCenterY())
            {
                collider = new Collider(o, Collider.CollisionType.TOP);
                System.out.println("top");
            }
            else if (newBounds.y < o.getY() + o.getHeight() && newBounds.x + newBounds.width > o.getX() && newBounds.x < o.getX() + o.getWidth() && newBounds.y > o.getCenterY())
            {
                collider = new Collider(o, Collider.CollisionType.BOTTOM);
                System.out.println("bottom");
            }
            else if (newBounds.x + newBounds.getWidth() > o.getX() && newBounds.y + newBounds.height > o.getY() && newBounds.getY() < o.getY() + o.getHeight() && newBounds.x + newBounds.getWidth() < o.getCenterX())
            {
                collider = new Collider(o, Collider.CollisionType.LEFT);
                System.out.println("left");
            }
            else if (newBounds.x < o.getX() + o.getWidth() && newBounds.y + newBounds.height > o.getY() && newBounds.getY() < o.getY() + o.getHeight() && newBounds.x > o.getCenterX())
            {
                collider = new Collider(o, Collider.CollisionType.RIGHT);
                System.out.println("right");
            }
        }

        return collider;
    }

    /**
     * Invoked when this object collides with another object.
     */
    public void onCollision(Collider collider)
    {

    }

    /**
     * Invoked when this object exits collision with another object.
     */
    public void onCollisionExit(Collider collider)
    {

    }

    /**
     * Invoked recursively during an object's collision.
     */
    public void onCollisionStay(Collider collider) {}

    // Move methods

    /**
     * Moves object according to speed parameter on x-axis.
     * @return whether it was able to move successfully.
     */
    public boolean moveX(int xSpeed)
    {
        boolean willCollide = false;

        if (this.horizontal_net_force > TERMINAL_VELOCITY)
            this.vertical_net_force = TERMINAL_VELOCITY;

        for (GameObject o : EntityManager.getAllGameObjects())
        {
            Collider collider = predictCollision(o);

            if (collider != null)
            {
                willCollide = true;
            }
        }

        if (!willCollide)
            setX(getX() + xSpeed);

        return true;
    }

    // PROBLEM: SANDWICHED OBJECTS COLLIDE AND MOVE POSITIONS ON BOTH TOP AND BOTTOM
    /**
     * Moves object according to speed parameter on y-axis.
     * @return whether it was able to move successfully.
     */
    public boolean moveY(int ySpeed)
    {
        if (this.vertical_net_force > TERMINAL_VELOCITY)
            this.vertical_net_force = TERMINAL_VELOCITY;

        for (GameObject o : EntityManager.getAllGameObjects())
        {
            Collider collider = predictCollision(o);

            if (collider != null)
            {
                return false;
            }
        }

        setY(getY() + ySpeed);

        return true;
    }

    /**
     * Moves object on both x and y-axis according to values
     * stored in the parameters.
     */
    public void move(int xSpeed, int ySpeed)
    {
        moveX(xSpeed);
        moveY(ySpeed);
    }

    /**
     * Moves object on both x and y-axis by the values stored in
     * the vector specified in parameters.
     */
    public void move(Vec2 vec2)
    {
        moveX((int) vec2.a());
        moveY((int) vec2.b());
    }

    /**
     * Moves the object on the screen according to net force of x and y.
     */
    private void applyNetForce(int xForce, int yForce)
    {
        move(xForce, yForce);
    }

    // Physics

    /**
     * Updates game object physics.
     */
    public void updatePhysics()
    {
        this.GRAVITATIONAL_ACCELERATION = (float) ((SceneManager.getCurrentScene().gravitationalConstant * mass) / Math.pow(getHeight() / 2f, 2));

        // means that the object is currently in collision
        if (SceneManager.getCurrentScene().sideView)
        {
            fall();
        }

        applyNetForce((int) horizontal_net_force, (int) vertical_net_force);
    }

    protected void fall()
    {
        this.vertical_net_force += GRAVITATIONAL_ACCELERATION;

        if (!this.moveY((int) this.vertical_net_force))
        {
            GRAVITATIONAL_ACCELERATION = 0;
        }
    }

    protected void addForce(float x, float y)
    {
        this.horizontal_net_force += x;
        this.vertical_net_force += y;
    }

    protected void addForce(Vec2 force)
    {

    }

}
