package tests;

import models.createpet.PetBodyModel;
import models.deletepet.DeletePetResponseModel;
import models.findpet.NotFoundPetResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testdata.BaseTestData;
import testdata.DeletePetTestData;
import testdata.FindPetTestData;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("API PetStore: Уадление информации о питомце. Метод DELETE /pet")
public class DeletePetTest extends TestBase {

    @Test
    @DisplayName("DELETE /pet/{petId}: Удаление информации о питомце")
    void deleteExistingPet() {
        PetBodyModel requestCreate = new PetBodyModel(createTestData.id
                , createTestData.category
                , createTestData.name
                , createTestData.photoUrls
                , createTestData.tags
                , createTestData.status
        );

        PetBodyModel responseCreatePet = api.pet.create(requestCreate);

        step("Проверяем код ответа и petId по которому удаляли данные", () -> {
            DeletePetResponseModel responseDelete = api.pet.deletePetById(responseCreatePet.id());
            assertThat(responseDelete.code()).isEqualTo(DeletePetTestData.deletePetResponseCode);
            assertThat(responseDelete.type()).isEqualTo(DeletePetTestData.deletePetResponseType);
            assertThat(responseDelete.message()).isEqualTo(responseCreatePet.id().toString());
        });
        step("Проверяем что данные удалены", () -> {
            NotFoundPetResponseModel responseGetPet = api.pet.getNonExistPetById(responseCreatePet.id());
            assertThat(responseGetPet.code()).isEqualTo(FindPetTestData.petNonExistedIdResponseCode);
            assertThat(responseGetPet.type()).isEqualTo(FindPetTestData.petNonExistedIdResponseType);
            assertThat(responseGetPet.message()).isEqualTo(FindPetTestData.petNonExistedIdResponseMessage);
        });
    }

    @Test
    @DisplayName("DELETE /pet/{petId}: Удаление информации о питомце по несуществующему id")
    void deleteNonExistingPet() {
        api.pet.deleteNonExistPetById(BaseTestData.petNonExistedId);
    }

    @Test
    @DisplayName("DELETE /pet/{petId}: Удаление информации о питомце c неверным типом id")
    void deleteWithInvalidIdPet() {
        DeletePetResponseModel responseDelete = api.pet.deleteWithInvalidPetIdType(DeletePetTestData.deleteInvalidIdType);

        step("Проверяем тело ответа", () -> {
            assertThat(responseDelete.code()).isEqualTo(DeletePetTestData.deletePetWithInvalidIdTypeResponseCode);
            assertThat(responseDelete.type()).isEqualTo(DeletePetTestData.deletePetResponseType);
            assertThat(responseDelete.message()).isEqualTo(DeletePetTestData.deletePetWithInvalidIdTypeResponseMessage
                    + "\"" + DeletePetTestData.deleteInvalidIdType + "\"");
        });
    }
}
