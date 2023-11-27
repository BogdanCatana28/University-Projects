package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.services.DeviceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("get_all")
    public ResponseEntity<Object> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        if (devices.isEmpty()) {

            return ResponseEntity.badRequest().body("No devices found");
        }

        return ResponseEntity.ok(devices);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getDeviceById(@PathVariable Integer id) {
        Optional<Device> deviceOptional = deviceService.getDeviceById(id);

        return deviceOptional.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body("Device not found"));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createDevice(@RequestBody DeviceDTO deviceDTO) {
        try {
            Device createdDevice = deviceService.createDevice(deviceDTO);

            return ResponseEntity.ok(createdDevice);
        } catch (ValidatorException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateDevice(@PathVariable Integer id, @RequestBody DeviceDTO updatedDeviceDTO) {
        try {
            Device updatedDeviceResult = deviceService.updateDevice(id, updatedDeviceDTO);

            return ResponseEntity.ok(updatedDeviceResult);
        } catch (ValidatorException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable Integer id) {
        try {
            deviceService.deleteDevice(id);

            return ResponseEntity.ok("Device deleted successfully");
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body("Device not found");
        }
    }

    @DeleteMapping("/delete_by_user/{userId}")
    public ResponseEntity<String> deleteDevicesByUserId(@PathVariable Integer userId) {
        try {
            deviceService.deleteDevicesByUserId(userId);

            return ResponseEntity.ok("Devices deleted successfully");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("Error deleting devices: " + e.getMessage());
        }
    }

    @GetMapping("/get_by_user/{userId}")
    public ResponseEntity<Object> getDevicesByUserId(@PathVariable Integer userId) {
        List<Device> devices = deviceService.getDevicesByUserId(userId);
        if (devices.isEmpty()) {

            return ResponseEntity.badRequest().body("No devices found for user with ID: " + userId);
        }
        return ResponseEntity.ok(devices);
    }
}

