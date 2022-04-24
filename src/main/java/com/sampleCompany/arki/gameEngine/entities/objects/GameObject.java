package com.sampleCompany.arki.gameEngine.entities.objects;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.Physics;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.collision.Collider;
import com.sampleCompany.arki.gameEngine.input.keyboard.KeyManager;
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
        version = "2.2",
        releaseDate = "12/20/2021",
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

        if (!o.isActive())
        {
            onCollisionExit(o);
            collisions.removeIf(c -> c.gameObject == o);
        }

        Collider collider = null;
        Rectangle newBounds = new Rectangle((int) (getCurBounds().x + this.horizontal_net_force), (int) (getCurBounds().y + this.vertical_net_force), getWidth(), getHeight());

        if (newBounds.intersects(o.getCurBounds()))
        {
            //Collision type: up, down, left, right
            if (this.getY() + this.height < o.getCenterY())
            {
                collider = new Collider(o, Collider.CollisionType.TOP);
            }
            else if (this.getY() > o.getCenterY())
            {
                collider = new Collider(o, Collider.CollisionType.BOTTOM);
            }
            else if (this.getX() + this.width < o.getCenterX())
            {
                collider = new Collider(o, Collider.CollisionType.LEFT);
            }
            else if (this.getX() > o.getCenterX())
            {
                collider = new Collider(o, Collider.CollisionType.RIGHT);
            }
            else
            {
                collider = new Collider(o, Collider.CollisionType.UNKNOWN);
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
    public void onCollisionExit(GameObject gameObject)
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
    public boolean moveNetForceX(int xSpeed)
    {
        if (this.horizontal_net_force > 0 && this.horizontal_net_force > TERMINAL_VELOCITY)
            this.horizontal_net_force = TERMINAL_VELOCITY;
        else if (this.horizontal_net_force < 0 && this.horizontal_net_force < -TERMINAL_VELOCITY)
            this.horizontal_net_force = -TERMINAL_VELOCITY;

        for (GameObject o : EntityManager.getAllGameObjects())
        {
            Collider collider = predictCollision(o);

            if (collider != null)
            {
                if (collider.getType() == Collider.CollisionType.LEFT || collider.getType() == Collider.CollisionType.RIGHT)
                {
                    // onCollisionStay
                    if (this.getX() == collider.gameObject.getX() - this.getWidth() + 1 ||
                            this.getX() == collider.gameObject.getX() + collider.gameObject.getWidth() - 1)
                    {
                        onCollisionStay(collider);

                        if (xSpeed > 0 && collider.getType() == Collider.CollisionType.LEFT)
                        {
                            horizontal_net_force = 0;
                        }
                        else if (xSpeed < 0 && collider.getType() == Collider.CollisionType.RIGHT)
                        {
                            horizontal_net_force = 0;
                        }
                    }
                    // onCollision
                    else if (xSpeed > 0 && collider.getType() == Collider.CollisionType.LEFT)
                    {
                        this.setX(collider.gameObject.getX() - this.getWidth() + 1);
                        horizontal_net_force = 0;
                        onCollision(collider);
                        collisions.add(collider);
                    }
                    else if (xSpeed < 0 && collider.getType() == Collider.CollisionType.RIGHT)
                    {
                        this.setX(collider.gameObject.getX() + collider.gameObject.getWidth() - 1);
                        horizontal_net_force = 0;
                        onCollision(collider);
                        collisions.add(collider);
                    }

                    return false;
                }
            }
            // onCollisionExit
            else
            {
                for (Collider c : collisions)
                {
                    if (c.gameObject == o)
                    {
                        onCollisionExit(o);
                        collisions.remove(c);
                    }
                }
            }
        }

        setX(getX() + xSpeed);

        return true;
    }

    /**
     * Moves object according to speed parameter on y-axis.
     * @return whether it was able to move successfully.
     */
    public boolean moveNetForceY(int ySpeed)
    {
        if (this.vertical_net_force > 0 && this.vertical_net_force > TERMINAL_VELOCITY)
            this.vertical_net_force = TERMINAL_VELOCITY;
        else if (this.vertical_net_force < 0 && this.vertical_net_force < -TERMINAL_VELOCITY)
            this.vertical_net_force = -TERMINAL_VELOCITY;

        for (GameObject o : EntityManager.getAllGameObjects())
        {
            Collider collider = predictCollision(o);

            if (collider != null)
            {
                if (collider.getType() == Collider.CollisionType.TOP || collider.getType() == Collider.CollisionType.BOTTOM)
                {
                    // onCollisionStay
                    if (this.getY() == collider.gameObject.getY() - this.getHeight() + 1 ||
                            this.getY() == collider.gameObject.getY() + collider.gameObject.getHeight() - 1)
                    {
                        onCollisionStay(collider);

                        if (ySpeed > 0 && collider.getType() == Collider.CollisionType.TOP)
                        {
                            vertical_net_force = 0;
                        }
                        else if (ySpeed < 0 && collider.getType() == Collider.CollisionType.BOTTOM)
                        {
                            vertical_net_force = 0;
                        }
                    }
                    // onCollision
                    else if (ySpeed > 0 && collider.getType() == Collider.CollisionType.TOP)
                    {
                        this.setY(collider.gameObject.getY() - this.getHeight() + 1);
                        vertical_net_force = 0;
                        onCollision(collider);
                        collisions.add(collider);
                    }
                    else if (ySpeed < 0 && collider.getType() == Collider.CollisionType.BOTTOM)
                    {
                        this.setY(collider.gameObject.getY() + collider.gameObject.getHeight() - 1);
                        vertical_net_force = 0;
                        onCollision(collider);
                        collisions.add(collider);
                    }

                    return false;
                }
            }
            // onCollisionExit
            else
            {
                for (Collider c : collisions)
                {
                    if (c.gameObject == o)
                    {
                        onCollisionExit(o);
                        collisions.remove(c);
                    }
                }
            }
        }

        setY(getY() + ySpeed);

        return true;
    }

    /**
     * User move method across x-axis
     *
     * @param speed at which it will move
     */
    public void moveX(int speed, char leftBtn, char rightBtn)
    {
        if (KeyManager.isHeld(leftBtn))
            horizontal_net_force = -speed;
        else if (KeyManager.isHeld(rightBtn))
            horizontal_net_force = speed;

        if (KeyManager.onRelease(leftBtn) || KeyManager.onRelease(rightBtn))
            horizontal_net_force = 0;
    }

    public void moveX(int speed, int leftKeyCode, int rightKeyCode)
    {
        if (KeyManager.isHeld(leftKeyCode))
            horizontal_net_force = -speed;
        else if (KeyManager.isHeld(rightKeyCode))
            horizontal_net_force = speed;

        if (KeyManager.onRelease(leftKeyCode) || KeyManager.onRelease(rightKeyCode))
            horizontal_net_force = 0;
    }

    public void moveY(int speed, char upBtn, char downBtn)
    {
        if (KeyManager.isHeld(upBtn))
            vertical_net_force = -speed;
        else if (KeyManager.isHeld(downBtn))
            vertical_net_force = speed;

        if (KeyManager.onRelease(downBtn) || KeyManager.onRelease(upBtn))
            vertical_net_force = 0;
    }

    public void moveY(int speed, int upKeyCode, int downKeyCode)
    {
        if (KeyManager.isHeld(upKeyCode))
            vertical_net_force = -speed;
        else if (KeyManager.isHeld(downKeyCode))
            vertical_net_force = speed;

        if (KeyManager.onRelease(downKeyCode) || KeyManager.onRelease(upKeyCode))
            vertical_net_force = 0;
    }

    /**
     * Moves object on both x and y-axis according to values
     * stored in the parameters.
     */
    public void move(int xSpeed, int ySpeed)
    {
        moveNetForceX(xSpeed);
        moveNetForceY(ySpeed);
    }

    /**
     * Moves object on both x and y-axis by the values stored in
     * the vector specified in parameters.
     */
    public void move(Vec2 vec2)
    {
        moveNetForceX((int) vec2.a());
        moveNetForceY((int) vec2.b());
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

        if (!this.moveNetForceY((int) this.vertical_net_force))
        {
            GRAVITATIONAL_ACCELERATION = 0;
        }
    }

    protected void addForce(float x, float y)
    {
        this.horizontal_net_force += x;
        this.vertical_net_force -= y;
    }

    protected void addForce(Vec2 force)
    {
        this.horizontal_net_force += force.a();
        this.vertical_net_force -= force.b();
    }

}
