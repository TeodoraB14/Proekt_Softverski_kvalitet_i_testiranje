package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestBindingResultErrors {

	@Test
	void testBindingResultErrors() {
		OwnerRepository ownerRepo = mock(OwnerRepository.class);
		PetTypeRepository petTypeRepo = mock(PetTypeRepository.class);
		PetController petController = new PetController(ownerRepo, petTypeRepo);

		Owner owner = mock(Owner.class);
		Pet pet = new Pet();
		pet.setId(5);
		pet.setName("Lucy");
		pet.setBirthDate(LocalDate.now().minusDays(2));

		BindingResult result = mock(BindingResult.class);
		RedirectAttributes redirectAttrs = mock(RedirectAttributes.class);

		when(result.hasErrors()).thenReturn(true);

		String view = petController.processUpdateForm(owner, pet, result, redirectAttrs);

		verifyNoInteractions(redirectAttrs);
		assertEquals("pets/createOrUpdatePetForm", view);
	}
}
