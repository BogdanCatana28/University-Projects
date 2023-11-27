package ro.tuc.ds2020.services;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    List<Device> getAllDevices();

    Optional<Device> getDeviceById(Integer id);

    Device createDevice(DeviceDTO deviceDTO) throws ValidatorException;

    Device updateDevice(Integer id, DeviceDTO updatedDeviceDTO) throws ValidatorException;

    void deleteDevice(Integer id);

    void deleteDevicesByUserId(Integer userId);

    List<Device> getDevicesByUserId(Integer userId);
}

