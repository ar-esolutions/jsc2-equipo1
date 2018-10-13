package com.esolutions.trainings.jsc2.logic;

import com.esolutions.trainings.jsc2.web.WifiSsidResponse;

import java.util.List;

public class GenerateKeyLogic {


    private String parseBinaryFloorAndRoom(int floor, int room){

        String binaryFloor = Integer.toBinaryString(floor);  // Paso el int floor a binario y lo guardo en string
        String binaryRoom = Integer.toBinaryString(room);    // Paso el int room a binario y lo guardo en string

        String concatenado = binaryFloor.concat(binaryRoom); // concateno los 2 string binaryFloor y binaryRoom
        return concatenado;
    }


    private int countZeros (String string){

        return 0;
    }

    public WifiSsidResponse concatenateSsid(int floor, int room, int ceros){
        return new WifiSsidResponse("HAND-"+ floor + "-" + room + "-"+ ceros);

    }

}
