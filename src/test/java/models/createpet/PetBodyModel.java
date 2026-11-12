package models.createpet;

import java.util.List;

public record PetBodyModel(
       Integer id,
       Category category,
       String name,
       List<String> photoUrls,
       List<Tag> tags,
       String status

) {
}
