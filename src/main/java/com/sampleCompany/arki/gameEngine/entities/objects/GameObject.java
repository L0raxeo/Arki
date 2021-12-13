package com.sampleCompany.arki.gameEngine.entities.objects;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.Physics;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.collision.Collider;
import com.sampleCompany.arki.gameEngine.scenes.SceneManager;
import com.sampleCompany.arki.gameEngine.utils.Vec2;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.util.ArrayList;
import java.util.List;

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

    protected float GRAVITATIONAL_ACCELERATION;
    protected float TERMINAL_VELOCITY;

    // Net Force (final speed)
    protected float horizontal_net_force;
    protected float vertical_net_force;

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
     * @return null if there is no collision
     */
    public Collider predictCollision(GameObject o)
    {
        return null;
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
        ArrayList<Collider> allCollisions = new ArrayList<>();
        boolean willCollide = false;

        for (GameObject o : EntityManager.getAllGameObjects())
        {
            Collider collider = predictCollision(o);

            if (collider != null)
            {
                allCollisions.add(collider);
            }
        }

        if (willCollide)
        {
            for (Collider c : allCollisions)
            {
                if (xForce > 0)
                {

                }
                else if (xForce < 0)
                {

                }

                if (yForce > 0)
                {

                }
                else if (yForce < 0)
                {

                }
            }
        }
        else
        {
            this.setX(this.getX() + xForce);
            this.setY(this.getY() + yForce);
        }

        horizontal_net_force = 0;
        vertical_net_force = 0;
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
        this.vertical_net_force -= y;
    }

    protected void addForce(Vec2 force)
    {

    }

}
