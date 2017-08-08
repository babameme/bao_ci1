package touhou.bases;

/**
 * Created by huynq on 8/2/17.
 */
public class Vector2D {
    public float x;
    public float y;

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void addUp(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        set(other.x, other.y);
    }

//    public Vector2D clone() {
//
//    }

    public void addUp(Vector2D other) {
        addUp(other.x, other.y);
    }

    public void subtractBy(float dx, float dy) {
        this.x -= dx;
        this.y -= dy;
    }

    public void subtractBy(Vector2D other) {
        subtractBy(other.x, other.y);
    }

    public Vector2D add(float dx, float dy) {
//        Vector2D newVector = new Vector2D();
//        newVector.x = this.x + dx;
//        newVector.y = this.y + dy;
//        return newVector;
        return new Vector2D(this.x + dx, this.y + dy);
    }

    public Vector2D add(Vector2D other) {
        return add(other.x, other.y);
    }

    public Vector2D multiply(float f) {
        return new Vector2D(this.x * f, this.y * f);
    }

    public float magnitude() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D normalize() {
        float mag = magnitude();
        return new Vector2D(this.x / mag, this.y / mag);
    }

    public float dis(Vector2D other){
        return (float)Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
}
