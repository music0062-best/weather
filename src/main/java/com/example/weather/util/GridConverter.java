
package com.example.weather.util;

public class GridConverter {

    // 기상청 격자 변환 상수
    private static final double RE = 6371.00877;
    private static final double GRID = 5.0;

    private static final double SLAT1 = 30.0;
    private static final double SLAT2 = 60.0;

    private static final double OLON = 126.0;
    private static final double OLAT = 38.0;

    private static final int XO = 43;
    private static final int YO = 136;

    // 변환 결과를 저장할 record
    public record Grid(int nx, int ny) {}

    // 위도(lat), 경도(lon)를 격자로 변환
    public static Grid toGrid(double lat, double lon) {

        if (!Double.isFinite(lat) || !Double.isFinite(lon)
                || lat <= -90 || lat >= 90
                || lon < -180 || lon > 180) {
            throw new IllegalArgumentException("잘못된 위경도입니다.");
        }

        // 1. 지구 반경을 격자 단위로 변환
        double re = RE / GRID;

        // 2. 각도를 라디안으로 변환
        double slat1 = Math.toRadians(SLAT1);
        double slat2 = Math.toRadians(SLAT2);
        double olon = Math.toRadians(OLON);
        double olat = Math.toRadians(OLAT);

        // 3. 람베르트 투영 계산
        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5)
                / Math.tan(Math.PI * 0.25 + slat1 * 0.5);

        sn = Math.log(Math.cos(slat1) / Math.cos(slat2))
                / Math.log(sn);

        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;

        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        // 4. 입력받은 위도를 투영 거리로 변환
        double ra = Math.tan(
                Math.PI * 0.25 + Math.toRadians(lat) * 0.5);

        ra = re * sf / Math.pow(ra, sn);

        // 5. 경도 차이 계산
        double theta = Math.toRadians(lon) - olon;

        if (theta > Math.PI) {
            theta -= 2.0 * Math.PI;
        }

        if (theta < -Math.PI) {
            theta += 2.0 * Math.PI;
        }

        theta *= sn;

        // 6. 최종 격자 좌표 계산
        int nx = (int) Math.floor(
                ra * Math.sin(theta) + XO + 0.5);

        int ny = (int) Math.floor(
                ro - ra * Math.cos(theta) + YO + 0.5);

        return new Grid(nx, ny);
    }
}
