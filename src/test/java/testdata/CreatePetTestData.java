package testdata;

import models.createpet.Category;
import models.createpet.Tag;

import java.util.List;

public class CreatePetTestData extends BaseTestData {
    public String name = faker.name().firstName();
    public String pathToCategoriesFile="src/test/resources/testdata/categories.csv";
    public String pathToTagsFile="src/test/resources/testdata/tags.csv";
    public Category category = dataGenerator.getPetCategory(pathToCategoriesFile);
    public Integer id = dataGenerator.getPetId(900, 9000);
    public List<String> photoUrls = List.of("https://ichef.bbci.co.uk/ace/ws/800/cpsprodpb/43f8/live/f74a8020-b326-11f1-bc1f-3f186ca4140c.jpg.webp");
    public List<Tag> tags =dataGenerator.getPetTags(pathToTagsFile);
    public String status = "1";



}
