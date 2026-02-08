package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OwnerServiceTest {

	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	void shouldSaveOwnerSuccessfully() {
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Street");
		owner.setCity("Skopje");
		owner.setTelephone("0712345678");

		Owner saved = ownerRepository.save(owner);

		assertNotNull(saved.getId());
		assertEquals("John", saved.getFirstName());
	}
}
