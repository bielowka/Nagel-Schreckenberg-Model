import java.util.Random;

public class Point {

    int type = 0;
    Point next[] = new Point[6];
    boolean moved;
    int speed = 0;


    public void move() {
        if (!this.moved && this.type == 1) {
            if (this.speed < 5) this.speed = this.speed + 1;

            Random rand = new Random();
            int r = rand.nextInt(10);
            if (r == 5) this.speed = this.speed - 1;

            int i = 1;
            while (i <= this.speed && this.next[i].type != 1){
                i++;
            }
            this.speed = i-1;

            if (this.speed > 0) {
                this.type = 0;
                this.next[speed].type = 1;
                this.next[speed].speed = this.speed;
                this.next[speed].moved = true;
            }
            else this.moved = true;
        }
    }

    public void clicked() {
        this.type = 1;
        this.speed = 1; ///
    }

    public void clear() {
        this.type = 0;
    }
}

