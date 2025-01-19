package com.tak.gateway.api.common.utile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CoordinateConverter {

    // DMS 형식을 십진수로 변환하는 함수
    public static double parseAndConvertDMS(String dms) {
        // 방향(북위, 남위, 동경, 서경)을 제외한 숫자 부분만 추출
        String dmsValue = dms.substring(0, dms.length() - 1); // 마지막 방향 문자 제외
        char direction = dms.charAt(dms.length() - 1); // 마지막 문자는 방향

        // 숫자 부분을 분리
        String[] dmsArray = dmsValue.split("[°'\"]");

        double degrees = Double.parseDouble(dmsArray[0].trim());
        double minutes = Double.parseDouble(dmsArray[1].trim());
        double seconds = Double.parseDouble(dmsArray[2].trim());

        double decimal = degrees + (minutes / 60) + (seconds / 3600);

        // 방향에 따라 부호를 결정
        if (direction == 'S' || direction == 'W') {
            decimal = -decimal;
        }

        return decimal;
    }

    // JSON 문자열을 받아 DMS 형식으로 변환된 좌표를 반환하는 메서드
    public static String[] convertJsonToDMS(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            double longitude = jsonNode.get("x").asDouble();
            double latitude = jsonNode.get("y").asDouble();

            String latitudeDMS = convertToDMS(latitude, true);
            String longitudeDMS = convertToDMS(longitude, false);

            return new String[]{latitudeDMS, longitudeDMS};
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"Error", "Error"};
        }
    }

    // 도분초 형식으로 변환하는 메서드
    public static String convertToDMS(double decimal, boolean isLatitude) {
        String direction;
        if (isLatitude) {
            direction = decimal >= 0 ? "N" : "S";
        } else {
            direction = decimal >= 0 ? "E" : "W";
        }
        
        decimal = Math.abs(decimal);
        int degrees = (int) decimal;
        double minutesDecimal = (decimal - degrees) * 60;
        int minutes = (int) minutesDecimal;
        double seconds = (minutesDecimal - minutes) * 60;

        return String.format("%d°%d'%d\"%s", degrees, minutes, (int) seconds, direction);
    }
}
