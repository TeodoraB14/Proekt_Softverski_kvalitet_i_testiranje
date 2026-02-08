package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestNormalUpdate {

	@Test
	void testNormalUpdate() {
		OwnerRepository ownerRepo = mock(OwnerRepository.class);
		PetTypeRepository petTypeRepo = mock(PetTypeRepository.class);

		PetController petController = new PetController(ownerRepo, petTypeRepo);

		Owner owner = mock(Owner.class);
		Pet pet = new Pet();
		pet.setId(1);
		pet.setName("Buddy");
		pet.setBirthDate(LocalDate.now().minusDays(1));

		BindingResult result = mock(BindingResult.class);
		RedirectAttributes redirectAttrs = mock(RedirectAttributes.class);

		when(result.hasErrors()).thenReturn(false);
		when(owner.getPet("Buddy", false)).thenReturn(null);

		String view = petController.processUpdateForm(owner, pet, result, redirectAttrs);

		verify(owner).getPet("Buddy", false);
		verify(redirectAttrs).addFlashAttribute(eq("message"), anyString());
		assertEquals("redirect:/owners/{ownerId}", view);
	}
}
