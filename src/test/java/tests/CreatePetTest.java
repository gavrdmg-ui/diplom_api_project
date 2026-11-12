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
        System.out.println(request);
        PetBodyModel response = api.pet.create(request);

        step("Проверяем что данные о питомце добавлены и соответствуют данным из запроса", () -> {
            System.out.println(request);
            System.out.println(response);

            assertThat(response.id()).isEqualTo(request.id());
            assertThat(response.category()).isEqualTo(request.category());
            assertThat(response.name()).isEqualTo(request.name());
            assertThat(response.photoUrls()).isEqualTo(request.photoUrls());
            assertThat(response.tags()).isEqualTo(request.tags());
            assertThat(response.status()).isEqualTo(request.status());
        });
    }
}
