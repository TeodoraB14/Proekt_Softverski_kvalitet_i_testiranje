package org.springframework.samples.petclinic.integration;


import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OwnerRepositoryIntegrationTest {

	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	@Transactional
	void testSaveAndFindOwner() {

		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main Street");
		owner.setCity("Skopje");
		owner.setTelephone("0712345678");

		ownerRepository.save(owner);

		Owner found = ownerRepository.findById(owner.getId()).orElse(null);
		assertNotNull(found);
		assertEquals("John", found.getFirstName());
		assertEquals("Doe", found.getLastName());
		assertEquals("0712345678", found.getTelephone());
	}
}
