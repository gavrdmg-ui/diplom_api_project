package api;

import io.qameta.allure.Step;
import models.createpet.*;
import models.deletepet.DeletePetResponseModel;
import models.findpet.NotFoundPetResponseModel;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static specs.createpet.CreatePetSpec.*;
import static specs.deletepet.DeletePetSpec.*;
import static specs.findpet.FindPetSpec.*;

public class PetApiClient {
    @Step("Добавляем данные о домашнем питомце")
    public PetBodyModel create(PetBodyModel createData) {
        return given()
                .spec(createRequestSpec)
                .body(createData)
                .when()
                .post("/pet")
                .then()
                .spec(successfulCreatePetResponseSpec)
                .extract()
                .as(PetBodyModel.class);
    }
    @Step("Получаем информацию о питомце по Id")
    public PetBodyModel getPetById(Integer petId) {
        return given()
                .spec(findRequestSpec)
                .when()
                .get("/pet/{id}/", petId)
                .then()
                .spec(successfulCreatePetResponseSpec)
                .extract()
                .as(PetBodyModel.class);
    }
    @Step("Получаем информацию о питомце несуществующем питомце по Id")
    public NotFoundPetResponseModel getNonExistPetById(Integer petId) {
        return given()
                .spec(findRequestSpec)
                .when()
                .get("/pet/{id}/", petId)
                .then()
                .spec(findNonExistPetResponseSpec)
                .extract()
                .as(NotFoundPetResponseModel.class);
    }
    @Step("Удаляем информацию о питомце")
    public DeletePetResponseModel deletePetById(Integer petId) {
        return given()
                .spec(deleteRequestSpec)
                .when()
                .delete("/pet/{id}/", petId)
                .then()
                .spec(successfulDeletePetResponseSpec)
                .extract()
                .as(DeletePetResponseModel.class);
    }
    @Step("Удаляем информацию о несуществующем питомце")
    public void deleteNonExistPetById(Integer petId) {
        given()
                .spec(deleteRequestSpec)
                .when()
                .delete("/pet/{id}/", petId)
                .then()
                .spec(deleteNonExistPetResponseSpec)
                .body(Matchers.emptyString());

    }
    @Step("Удаляем информацию о питомце с неверным типом параметра petId")
    public DeletePetResponseModel deleteWithInvalidPetIdType(String petId) {
        return given()
                .spec(deleteRequestSpec)
                .when()
                .delete("/pet/{id}/", petId)
                .then()
                .spec(deleteNonExistPetResponseSpec)
                .extract()
                .as(DeletePetResponseModel.class);

    }
}
