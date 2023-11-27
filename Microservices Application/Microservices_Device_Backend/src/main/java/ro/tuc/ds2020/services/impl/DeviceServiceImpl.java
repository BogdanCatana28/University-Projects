package ro.tuc.ds2020.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.validators.DeviceValidator;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.services.DeviceService;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> getDeviceById(Integer id) {
        return deviceRepository.findById(id);
    }

    @Override
    public Device createDevice(DeviceDTO deviceDTO) throws ValidatorException {
        DeviceValidator validator = new DeviceValidator();
        try {
            validator.validate(deviceDTO);
        } catch (Exception e) {
            throw new ValidatorException("Validation failed: " + e.getMessage());
        }

        Integer userId = deviceDTO.getUserId();

        if (userId != null) {
            try {
                ResponseEntity<Object> userResponse = getUserByIdFromUserService(userId);

                if (userResponse.getStatusCode() != HttpStatus.OK) {
                    throw new ValidatorException("Invalid user ID");
                }
            } catch (HttpClientErrorException.BadRequest ex) {
                throw new ValidatorException("User not found");
            }
        }

        Device device = DeviceBuilder.toEntity(deviceDTO);

        return deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(Integer id, DeviceDTO updatedDeviceDTO) throws ValidatorException {
        Integer userId = updatedDeviceDTO.getUserId();

        try {
            ResponseEntity<Object> userResponse = getUserByIdFromUserService(userId);

            if (userResponse.getStatusCode() != HttpStatus.OK) {
                throw new ValidatorException("Invalid user ID");
            }
        } catch (HttpClientErrorException.BadRequest ex) {
            throw new ValidatorException("User not found");
        }

        Optional<Device> existingDeviceOptional = deviceRepository.findById(id);

        if (existingDeviceOptional.isPresent()) {
            Device existingDevice = existingDeviceOptional.get();
            existingDevice.setModel(updatedDeviceDTO.getModel());
            existingDevice.setType(updatedDeviceDTO.getType());
            existingDevice.setUserId(userId);

            return deviceRepository.save(existingDevice);
        } else {
            throw new ValidatorException("Device not found with id: " + id);
        }
    }

    public ResponseEntity<Object> getUserByIdFromUserService(Integer userId) {
        RestTemplate restTemplate = new RestTemplate();
        String userApiUrl = "http://172.30.1.1:8080/users/get/" + userId;
        return restTemplate.getForEntity(userApiUrl, Object.class);
    }

    @Override
    public void deleteDevice(Integer id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            deviceRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Device not found");
        }
    }

    @Override
    public void deleteDevicesByUserId(Integer userId) {
        List<Device> devicesToDelete = deviceRepository.findByUserId(userId);
        deviceRepository.deleteAll(devicesToDelete);
    }

    @Override
    public List<Device> getDevicesByUserId(Integer userId) {

        return deviceRepository.findByUserId(userId);
    }
}

