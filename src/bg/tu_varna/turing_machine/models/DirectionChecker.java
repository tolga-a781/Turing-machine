package bg.tu_varna.turing_machine.models;

public class DirectionChecker {
    public Direction checkDirection(String direction) throws Exception{
        if(direction.toUpperCase().equals("L") || direction.toUpperCase().equals("R") || direction.toUpperCase().equals("S")){
            return Direction.valueOf(direction.toUpperCase());
        } else {
            throw new Exception("Invalid direction: " + direction);
        }
    }
}
