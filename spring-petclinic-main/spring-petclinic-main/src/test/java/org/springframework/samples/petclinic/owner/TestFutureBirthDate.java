package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestFutureBirthDate {

	@Test
	void testFutureBirthDate() {
		OwnerRepository ownerRepo = mock(OwnerRepository.class);
		PetTypeRepository petTypeRepo = mock(PetTypeRepository.class);
		PetController petController = new PetController(ownerRepo, petTypeRepo);

		Owner owner = mock(Owner.class);
		Pet pet = new Pet();
		pet.setId(4);
		pet.setName("Oscar");
		pet.setBirthDate(LocalDate.now().plusDays(10));

		BindingResult result = mock(BindingResult.class);
		RedirectAttributes redirectAttrs = mock(RedirectAttributes.class);

		when(owner.getPet("Oscar", false)).thenReturn(null);
		doAnswer(invocation -> true).when(result).hasErrors();

		String view = petController.processUpdateForm(owner, pet, result, redirectAttrs);

		verify(result).rejectValue("birthDate", "typeMismatch.birthDate");
		assertEquals("pets/createOrUpdatePetForm", view);
	}
}
