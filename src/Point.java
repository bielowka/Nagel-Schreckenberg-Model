import java.util.Random;

public class Point {

    int type = 0;
    public static Integer[] types = {0, 1, 2, 3, 5};
    Point next[] = new Point[8];
    Point prev[] = new Point[8];
    Point nextTo;
    boolean isLeft;
    boolean moved;
    int speed = 0;
    int maxSpeed = 0;


    public void move() {
        if (this.type == 1 || this.type == 2 || this.type == 3){
            if (!this.moved) {
                if (!goBack()) {
                    if (!overtake())
                        driveAhead();
                }
            }
        }
    }

    private void driveAhead() {
        if (this.speed < this.maxSpeed) this.speed = this.speed + 1;

//            Random rand = new Random();
//            int r = rand.nextInt(10);
//            if (r == 5) this.speed = this.speed - 1;

        int i = 1;
        while (i <= this.speed && this.next[i].type == 0) {
            i++;
        }

        this.speed = i - 1;

        if (this.speed > 0) {
            this.next[speed].type = this.type;
            this.type = 0;
            this.next[speed].speed = this.speed;
            this.next[speed].moved = true;
            this.next[speed].maxSpeed = this.maxSpeed;
        } else this.moved = true;
    }

    public void clicked() {
        this.type = 0;
    }

    public void clear() {
        this.type = 0;
    }

    public boolean overtake(){
        if (this.isLeft) return false;

        int i = 1;
        while (i <= this.maxSpeed && this.next[i].type == 0) {
            i++;
        }
        if (i - 1 == this.maxSpeed) return false;
        if(this.next[i].type == 0) return false; //to avoid unnecessary overtaking

        //1
        if (this.speed >= this.maxSpeed) return false;
        //2
        i = 1;
        while (i <= 7 && this.prev[i].type == 0){
            i++;
        }
        i = i - 1;
        if (this.prev[i].type != 0){
            if (i < this.prev[i].maxSpeed) return false;
        }
        //3
        i = 1;
        while (i <= 7 && this.nextTo.prev[i].type == 0){
            i++;
        }
        i = i - 1;
        if (this.nextTo.prev[i].type != 0){
            if (i < this.nextTo.prev[i].maxSpeed) return false;
        }
        //4
        i = 1;
        while (i <= this.speed && this.nextTo.next[i].type == 0){
            i++;
        }
        i = i - 1;
        if (i < this.speed) return false;

        if (this.speed == 0) return false;


        this.nextTo.next[speed-1].type = this.type;
        this.type = 0;
        this.nextTo.next[speed-1].speed = this.speed + 1;
        this.nextTo.next[speed-1].maxSpeed = this.maxSpeed;
        this.nextTo.next[speed-1].moved = true;

    return true;
    }


    public boolean goBack(){
        if (!this.isLeft) return false;

        //1
        int i = 1;
        while (i <= 7 && this.nextTo.prev[i].type == 0){
            i++;
        }
        i = i - 1;
        if (this.nextTo.prev[i].type != 0){
            if (i < this.nextTo.prev[i].maxSpeed) return false;
        }
        //2
        i = 1;
        while (i <= 7 && this.prev[i].type == 0){
            i++;
        }
        i = i - 1;
        if (this.prev[i].type != 0){
            if (i > this.prev[i].maxSpeed) return false;
        }
        //3
        i = 1;
        while (i <= this.speed && this.nextTo.next[i].type == 0){
            i++;
        }
        i = i - 1;
        if (i < this.speed) return false;

        if (this.speed == 0) return false;


        this.nextTo.next[speed-1].type = this.type;
        this.type = 0;
        this.nextTo.next[speed-1].speed = this.speed;
        this.nextTo.next[speed-1].maxSpeed = this.maxSpeed;
        this.nextTo.next[speed-1].moved = true;

        return true;
    }


}

