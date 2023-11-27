package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.entities.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceBuilder {

    private DeviceBuilder() {

    }

    public static DeviceDTO toDTO(Device device) {
        return DeviceDTO.builder()
                .deviceId(device.getDeviceId())
                .model(device.getModel())
                .type(device.getType())
                .userId(device.getUserId())
                .build();
    }

    public static Device toEntity(DeviceDTO deviceDTO) {
        return Device.builder()
                .deviceId(deviceDTO.getDeviceId())
                .model(deviceDTO.getModel())
                .type(deviceDTO.getType())
                .userId(deviceDTO.getUserId())
                .build();
    }

    public static List<DeviceDTO> toDeviceDTOList(List<Device> devices) {
        List<DeviceDTO> deviceDTOS = new ArrayList<>();

        for (Device device : devices) {
            deviceDTOS.add(toDTO(device));
        }

        return deviceDTOS;
    }
}

