package project.capstone.fick.web.dto.crack;

enum Hazard{
    LOW, MIDDLE, HIGH
}

public class CrackRequestDto {
    private String crackId;
    private String image;
    private float x;
    private float y;
    private float z;
    private float width;
    private Hazard hazard;
    private String comment;
    private boolean check;
    private String buildingId;
}
