package tests;

import models.createpet.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("API PetStore: Добавление информации о питомце. Метод POST /pet")
public class CreatePetTest extends TestBase {

    @Test
    @DisplayName("POST /pet: Добавление информации о питомце")
    void successfulCreatePetTest() {
        PetBodyModel request = new PetBodyModel(createTestData.id,
                createTestData.category,
                createTestData.name,
                createTestData.photoUrls,
                createTestData.tags,
                createTestData.status
        );

        PetBodyModel response = api.pet.create(request);

        step("Проверяем что данные о питомце добавлены и соответствуют данным из запроса", () -> {
            assertThat(response.id()).isNotNull();
            assertThat(request.category()).isEqualTo(response.category());
            assertThat(request.name().equals(response.name()));
            assertThat(request.photoUrls().equals(response.photoUrls()));
            assertThat(request.tags().equals(response.tags()));
            assertThat(request.status().equals(response.status()));
        });
    }
}
