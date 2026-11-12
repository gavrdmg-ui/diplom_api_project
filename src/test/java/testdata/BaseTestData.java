package testdata;

import helpers.PetTestDataGenerator;
import net.datafaker.Faker;

public class BaseTestData {
    public  Faker faker = new Faker();
    PetTestDataGenerator dataGenerator= new PetTestDataGenerator();
    public final static Integer petNonExistedId=Integer.MAX_VALUE-1;

}
