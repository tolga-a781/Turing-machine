package bg.tu_varna.turing_machine.models;

public class DirectionChecker {
    public Direction checkDirection(String direction){
        if(direction.toUpperCase().equals("L") || direction.toUpperCase().equals("R") || direction.toUpperCase().equals("S")){
            return Direction.valueOf(direction.toUpperCase());
        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }
}
