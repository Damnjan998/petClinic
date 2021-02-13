package damnjan.services.map;

import damnjan.model.Speciality;
import damnjan.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VetServiceMapTest {

    VetServiceMap vetServiceMap;

    final Long vetId = 1L;
    final Set<Speciality> specialities = new HashSet<>();
    
    @BeforeEach
    void setUp() {
        vetServiceMap = new VetServiceMap(new SpecialityServiceMap());

        vetServiceMap.save(Vet.builder().id(vetId).specialities(specialities).build());
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = vetServiceMap.findAll();

        assertEquals(1, vetSet.size());
    }

    @Test
    void deleteById() {
        vetServiceMap.deleteById(vetId);

        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void delete() {
        vetServiceMap.delete(vetServiceMap.findById(vetId));

        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        long id = 2L;

        Vet vet2 = Vet.builder().id(id).specialities(specialities).build();
        Vet savedVet = vetServiceMap.save(vet2);

        assertEquals(id, savedVet.getId());
    }

    @Test
    void saveNoId() {
        Vet savedVet = vetServiceMap.save(Vet.builder().specialities(specialities).build());

        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
    }

    @Test
    void findById() {
        Vet vet = vetServiceMap.findById(vetId);

        assertEquals(1, vet.getId());
    }
}