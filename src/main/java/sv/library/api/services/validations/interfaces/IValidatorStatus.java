package sv.library.api.services.validations.interfaces;

import sv.library.api.dto.status.StatusData;

public interface IValidatorStatus {
    public void validate(StatusData data);
}
