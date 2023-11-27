package ro.tuc.ds2020.dtos.validators;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.validators.utils.ValidatorException;

public class DeviceValidator {

    public void validateModel(String model) throws ValidatorException {
        if (model == null || model.isEmpty()) {
            throw new ValidatorException("Model cannot be empty");
        }
    }

    public void validateType(String type) throws ValidatorException {
        if (type == null) {
            throw new ValidatorException("Type cannot be null");
        }
    }

    public void validate(DeviceDTO deviceDTO) throws ValidatorException {
        validateModel(deviceDTO.getModel());
        validateType(deviceDTO.getType().toString());
    }
}

