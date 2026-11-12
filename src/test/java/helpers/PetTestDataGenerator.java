package helpers;

import models.createpet.Category;
import models.createpet.Tag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetTestDataGenerator {

    private List<Category> categories = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public Category getPetCategory(String filePath) {
        ReadFileCategories(filePath);
        Random rand = new Random();
        return categories.get(rand.nextInt(categories.size()));

    }

    public Integer getPetId(Integer min, Integer max) {
        Random rand = new Random();
        return rand.nextInt(min, max);
    }

    public List<Tag> getPetTags(String filePath) {
        ReadFileTags(filePath);
        return tags;

    }


    private void ReadFileCategories(String filePath) {
        categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                categories.add(new Category(Integer.valueOf(values[0]), values[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ReadFileTags(String filePath) {
        tags = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                tags.add(new Tag(Integer.valueOf(values[0]), values[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
