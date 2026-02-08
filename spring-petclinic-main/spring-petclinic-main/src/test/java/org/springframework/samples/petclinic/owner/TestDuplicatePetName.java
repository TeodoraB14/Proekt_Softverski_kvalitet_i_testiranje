package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestDuplicatePetName {

	@Test
	void testDuplicatePetName() {
		OwnerRepository ownerRepo = mock(OwnerRepository.class);
		PetTypeRepository petTypeRepo = mock(PetTypeRepository.class);
		PetController petController = new PetController(ownerRepo, petTypeRepo);

		Owner owner = mock(Owner.class);
		Pet pet = new Pet();
		pet.setId(2);
		pet.setName("Max");

		Pet existingPet = new Pet();
		existingPet.setId(3);

		BindingResult result = mock(BindingResult.class);
		RedirectAttributes redirectAttrs = mock(RedirectAttributes.class);

		when(owner.getPet("Max", false)).thenReturn(existingPet);
		doAnswer(invocation -> true).when(result).hasErrors();

		String view = petController.processUpdateForm(owner, pet, result, redirectAttrs);

		verify(result).rejectValue("name", "duplicate", "already exists");
		assertEquals("pets/createOrUpdatePetForm", view);
	}

}
