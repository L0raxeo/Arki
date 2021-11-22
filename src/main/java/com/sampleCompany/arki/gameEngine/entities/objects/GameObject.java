package com.sampleCompany.arki.gameEngine.entities.objects;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.scenes.SceneManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.util.ArrayList;
import java.util.HashMap;

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
        releaseDate = "11/22/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public abstract class GameObject extends Entity
{

    protected float GRAVITATIONAL_ACCELERATION;
    protected float TERMINAL_VELOCITY;
    protected float vertical_speed;
    protected float mass;

    protected ArrayList<GameObject> curCollidingObj;
    protected boolean isSolid;
    protected boolean isGrounded;

    // class
    public GameObject(String name, String unlocalizedName, float x, float y, int width, int height, boolean isSolid)
    {
        super(name, unlocalizedName, x, y, width, height);

        this.isSolid = isSolid;

        curCollidingObj = new ArrayList<>();

        if (this.getClass().isAnnotationPresent(Physics.class))
        {
            this.GRAVITATIONAL_ACCELERATION = this.getClass().getAnnotation(Physics.class).GRAVITATIONAL_ACCELERATION();
            this.TERMINAL_VELOCITY = this.getClass().getAnnotation(Physics.class).TERMINAL_VELOCITY();
            this.vertical_speed = 0;
            this.mass = this.getClass().getAnnotation(Physics.class).mass();
        }
    }

    // Collision detection

    /**
     * Invokes appropriate collision methods.
     */
    public void checkCollision()
    {
        // Iterates through all game objects in scene
        for (GameObject o : EntityManager.getAllGameObjects())
        {
            // current iteration is skipped if the object is equivalent to the class
            if (o.equals(this))
                continue;

            // Checks if the current iteration is intersecting with the class
            if (o.getCurBounds().intersects(this.getCurBounds()))
            {
                // if the object is being intersecting, that means it's colliding.
                // checks if collision has already been indexed. If it has, then it invokes on collision stay.
                // Otherwise, if the collision hasn't been indexed, that means this is the first tick it has
                // been colliding, in which case onCollision(o) is invoked.
                if (curCollidingObj.contains(o))
                {
                    onCollisionStay(o);
                }
                else
                {
                    onCollision(o);
                    curCollidingObj.add(o);
                }
            }
            else
            {
                if (curCollidingObj.contains(o))
                {
                    onCollisionExit(o);
                    curCollidingObj.remove(o);
                }
            }
        }
    }

    /**
     * Invoked when this object collides with another object.
     */
    public void onCollision(GameObject collider)
    {

    }

    /**
     * Invoked when this object exits collision with another object.
     */
    public void onCollisionExit(GameObject collider)
    {

    }

    /**
     * Invoked recursively during an object's collision.
     */
    public void onCollisionStay(GameObject collider) {}

    // Move methods

    /**
     * Moves object according to speed parameter on x-axis.
     */
    public void moveX(int xSpeed)
    {
        setX(super.getX() + xSpeed);
    }

    /**
     * Moves object according to speed parameter on y-axis.
     */
    public void moveY(int ySpeed)
    {
        setY(super.getY() + ySpeed);
    }

    /**
     * Moves object on both x and y axis according to paramters.
     */
    public void move(int xSpeed, int ySpeed)
    {
        setX(super.getX() + xSpeed);
        setY(super.getY() + ySpeed);
    }

    // Physics

    public void updatePhysics()
    {
        this.GRAVITATIONAL_ACCELERATION = (float) ((SceneManager.getCurrentScene().gravitationalConstant * mass) / Math.pow(getHeight() / 2f, 2));

        // Makes object fall if it is mid-air, and if the scene is viewed from the side.
        if (SceneManager.getCurrentScene().sideView && !isGrounded)
            fall();
    }

    protected void fall()
    {
        this.vertical_speed += GRAVITATIONAL_ACCELERATION;

        if (this.vertical_speed > TERMINAL_VELOCITY)
            this.vertical_speed = TERMINAL_VELOCITY;

        this.moveY((int) this.vertical_speed);
    }

}
