package uz.jl.enums;

public enum Role {
    ADMIN(3), TEACHER(2), STUDENT(1);
    private final int order;

    Role(int order) {
        this.order = order;
    }
}
