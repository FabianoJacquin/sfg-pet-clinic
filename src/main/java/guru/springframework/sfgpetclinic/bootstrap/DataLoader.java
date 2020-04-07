package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final VetService vetService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, PetTypeService petTypeService, VetService vetService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.vetService = vetService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Fabiano");
        owner1.setLastName("Jacquin");
        owner1.setAddress("21 Frazione Cerisey");
        owner1.setCity("Saint-Rhémy-En-Bosses - AOSTA");
        owner1.setTelephone("347 3423385");

        Pet fabianoPet = new Pet();
        fabianoPet.setPetType(savedDogPetType);
        fabianoPet.setOwner(owner1);
        fabianoPet.setBirthDate(LocalDate.now());
        fabianoPet.setName("Fuffy");
        owner1.getPets().add(fabianoPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Carole");
        owner2.setLastName("Proment");
        owner2.setAddress("21 Frazione Cerisey");
        owner2.setCity("Saint-Rhémy-En-Bosses - AOSTA");
        owner2.setTelephone("328 3826219");

        Pet carolePet = new Pet();
        carolePet.setPetType(savedCatPetType);
        carolePet.setOwner(owner2);
        carolePet.setBirthDate(LocalDate.now());
        carolePet.setName("Micio");
        owner2.getPets().add(carolePet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(carolePet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Andrea");
        vet1.setLastName("Rossi");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Luca");
        vet2.setLastName("Bianchi");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
