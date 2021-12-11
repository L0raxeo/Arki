package com.sampleCompany.arki.gameEngine.entities.objects;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.Physics;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.collision.Collider;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.collision.CollisionType;
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
    protected float vertical_speed;
    protected float mass;

    public List<Collider> allColliders;
    protected boolean isSolid;

    // class
    public GameObject(String name, String unlocalizedName, float x, float y, int width, int height, boolean isSolid)
    {
        super(name, unlocalizedName, x, y, width, height);

        this.isSolid = isSolid;

        allColliders = new ArrayList<>();

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
     *
     * @return null if there is no collision
     */
    public Collider checkCollision(GameObject o)
    {
        Collider collider = null;

        for (Collider c : allColliders)
            if (c.gameObject.equals(o)) collider = c;

        if (o.equals(this))
            return null;

        if (!o.isActive())
        {
            onCollisionExit(collider);
            return null;
        }

        if (o.getCurBounds().intersects(this.getCurBounds()))
        {
            CollisionType collisionType = null;

            if (this.getY() + this.getHeight() < o.yCenter && this.getX() + this.getWidth() > o.getX() && this.getX() < o.getX() + o.getWidth()) collisionType = CollisionType.BOTTOM;
            else if (this.getY() > o.yCenter && this.getX() + this.getWidth() > o.getX() && this.getX() < o.getX() + o.getWidth()) collisionType = CollisionType.TOP;
            else if (this.getX() + this.getWidth() < o.xCenter && this.getY() + this.getHeight() > o.getY() && this.getY() < o.getY() + o.getHeight()) collisionType = CollisionType.RIGHT;
            else if (this.getX() > o.xCenter && this.getY() + this.getHeight() > o.getY() && this.getY() < o.getY() + o.getHeight()) collisionType = CollisionType.LEFT;

            if (allColliders.contains(collider))
            {
                onCollisionStay(collider);
            }
            else
            {
                onCollision(collider);
                allColliders.add(new Collider(o, collisionType));
            }

            return collider;
        }
        else if (allColliders.contains(collider))
        {
            onCollisionExit(collider);
            allColliders.remove(collider);
        }

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
        if (xSpeed == 0)
            return true;

        for (GameObject o : EntityManager.getAllGameObjects())
        {
            if (xSpeed > 0 && this.getX() + this.getWidth() == o.getX() + 1 && this.getY() < o.getY() + o.getHeight() && this.getY() + this.getWidth() > o.getY())
                return true;
            else if (xSpeed < 0 && this.getX() == (o.getX() + o.getWidth()) - 1 && this.getY() < o.getY() + o.getHeight() && this.getY() + this.getWidth() > o.getY())
                return false;

            Collider collider = this.checkCollision(o);

            if (collider != null && xSpeed > 0 && this.getX() != o.getX() - this.getWidth() - 1 && collider.type == CollisionType.RIGHT)
            {
                this.setX(o.getX() - this.getWidth() - 1);
                return false;
            }
            else if (collider != null && xSpeed < 0 && this.getX() != o.getX() + o.getWidth() - 1 && collider.type == CollisionType.LEFT)
            {
                this.setX(o.getX() + o.getWidth() - 1);
                return false;
            }
        }

        setX(super.getX() + xSpeed);
        return true;
    }

    // PROBLEM: SANDWICHED OBJECTS COLLIDE AND MOVE POSITIONS ON BOTH TOP AND BOTTOM
    /**
     * Moves object according to speed parameter on y-axis.
     * @return whether it was able to move successfully.
     */
    public boolean moveY(int ySpeed)
    {
        if (ySpeed == 0)
            return true;

        for (GameObject o : EntityManager.getAllGameObjects())
        {
            if (ySpeed > 0 && this.getY() == o.getY() - (this.getHeight() - 1) && this.getX() + this.getWidth() > o.getX() && this.getX() < o.getX() + o.getWidth())
                return false;
            else if (ySpeed < 0 && this.getY() == o.getY() + (o.getHeight() - 1) && this.getX() + this.getWidth() > o.getX() && this.getX() < o.getX() + o.getWidth())
                return false;

            Collider collider = this.checkCollision(o);

            if (collider != null && ySpeed > 0 && this.getY() != o.getY() - (this.getHeight() - 1) && collider.type == CollisionType.TOP)
            {
                this.setY(o.getY() - (this.getHeight() - 1));
                return false;
            }
            else if (collider != null && ySpeed < 0 && this.getY() != o.getY() + (o.getHeight() - 1) && collider.type == CollisionType.BOTTOM)
            {
                this.setY(o.getY() + (o.getHeight() - 1));
                return false;
            }
        }

        setY(super.getY() + ySpeed);
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
    }

    protected void fall()
    {
        this.vertical_speed += GRAVITATIONAL_ACCELERATION;

        if (this.vertical_speed > TERMINAL_VELOCITY)
            this.vertical_speed = TERMINAL_VELOCITY;

        if (!this.moveY((int) this.vertical_speed))
        {
            GRAVITATIONAL_ACCELERATION = 0;
            vertical_speed = 0;
        }
    }

    protected void addForce(float x, float y)
    {
        this.vertical_speed -= y;
    }

    protected void addForce(Vec2 force)
    {

    }

}
