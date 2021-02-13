package damnjan.services.map;

import damnjan.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetServiceMapTest {

    PetServiceMap petServiceMap;

    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();

        petServiceMap.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petServiceMap.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(petId);

        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(petId));

        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        long id = 2L;

        Pet pet2 = petServiceMap.save(Pet.builder().id(id).build());
        Pet savedPet = petServiceMap.save(pet2);

        assertEquals(id, savedPet.getId());
    }

    @Test
    void saveNoId() {
        Pet savedPet = petServiceMap.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(petId);

        assertEquals(1, pet.getId());
    }
}