package tests;


import models.createpet.PetBodyModel;
import models.findpet.NotFoundPetResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testdata.BaseTestData;
import testdata.FindPetTestData;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("API PetStore: ПОлучение информации о питомце. Метод GET /pet")
public class FindPetTests extends TestBase {
    @Test
    @DisplayName("GET /pet/{petId}: Получение информации о питомце по id")
    void findExistPetTest() {
        PetBodyModel requestCreatePet = new PetBodyModel(createTestData.id
                , createTestData.category
                , createTestData.name
                , createTestData.photoUrls
                , createTestData.tags
                , createTestData.status
        );

        PetBodyModel responseCreatePet = api.pet.create(requestCreatePet);

        PetBodyModel responseGetPet = api.pet.getPetById(responseCreatePet.id());

        step("Проверяем данные о питомце соответсвуют данным из запроса на создание", () -> {
            assertThat(responseGetPet.id()).isEqualTo(responseCreatePet.id());
            assertThat(responseGetPet.category()).isEqualTo(responseGetPet.category());
            assertThat(responseGetPet.name()).isEqualTo(responseGetPet.name());
            assertThat(responseGetPet.photoUrls()).isEqualTo(responseGetPet.photoUrls());
            assertThat(responseGetPet.tags()).isEqualTo(responseGetPet.tags());
            assertThat(responseGetPet.status()).isEqualTo(responseGetPet.status());
        });
    }

    @Test
    @DisplayName("GET /pet/{petId}: Получение информации о питомце по несуществующему id")
    void findNonexistentPetTest() {
        NotFoundPetResponseModel responseGetPet = api.pet.getNonExistPetById(BaseTestData.petNonExistedId);

        step("Проверяем тело ответа", () -> {
            assertThat(responseGetPet.code()).isEqualTo(FindPetTestData.petNonExistedIdResponseCode);
            assertThat(responseGetPet.type()).isEqualTo(FindPetTestData.petNonExistedIdResponseType);
            assertThat(responseGetPet.message()).isEqualTo(FindPetTestData.petNonExistedIdResponseMessage);
        });
    }
}
