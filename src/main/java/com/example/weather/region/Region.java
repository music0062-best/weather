package com.example.weather.region;

public enum Region {
    SEOUL("서울", 60, 127);

    private final String name;
    private final int nx;
    private final int ny;

    Region(String name, int nx, int ny) {
        this.name = name;
        this.nx = nx;
        this.ny = ny;
    }

    public String getName() {
        return name;
    }

    public int getNx() {
        return nx;
    }

    public int getNy() {
        return ny;
    }
}
