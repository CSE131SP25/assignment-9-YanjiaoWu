package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
	    segments = new LinkedList<>();
	    double startX = 0.5;
	    double startY = 0.5;
	    // 一开始就创建 5 节：头在最右，尾在最左
	    for (int i = 0; i < 5; i++) {
	        segments.add(new BodySegment(startX - i * MOVEMENT_SIZE,
	                                     startY,
	                                     SEGMENT_SIZE));
	    }
	    deltaX = 0;
	    deltaY = 0;
	}

    public void changeDirection(int direction) {
        if (direction == 1) { // up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) { // down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) { // left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4) { // right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }

    public void move() {
        BodySegment head = segments.getFirst();
        double newX = head.getX() + deltaX;
        double newY = head.getY() + deltaY;
        // Add new head segment
        segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));
        // Remove tail to keep same length
        segments.removeLast();
    }

    public void draw() {
        for (BodySegment seg : segments) {
            seg.draw();
        }
    }

    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        double dx = head.getX() - f.getX();
        double dy = head.getY() - f.getY();
        double dist = Math.hypot(dx, dy);
        if (dist < SEGMENT_SIZE + Food.FOOD_SIZE) {
            // Grow by adding an extra head segment
            segments.addFirst(new BodySegment(head.getX(), head.getY(), SEGMENT_SIZE));
            return true;
        }
        return false;
    }

    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        double x = head.getX();
        double y = head.getY();
        return x - SEGMENT_SIZE >= 0 && x + SEGMENT_SIZE <= 1
            && y - SEGMENT_SIZE >= 0 && y + SEGMENT_SIZE <= 1;
    }
}