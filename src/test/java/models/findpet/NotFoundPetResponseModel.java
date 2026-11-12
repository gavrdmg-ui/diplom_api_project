package models.findpet;

public record NotFoundPetResponseModel(
        Integer code,
        String type,
        String message
) {
}
