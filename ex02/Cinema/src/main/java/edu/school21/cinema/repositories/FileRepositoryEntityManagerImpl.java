package edu.school21.cinema.repositories;

import edu.school21.cinema.models.FileInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class FileRepositoryEntityManagerImpl implements FileRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(FileInfo fileInfo) {
		entityManager.persist(fileInfo);
	}

	@Override
	public FileInfo getFileByUuid(UUID uuid) {
		return entityManager.createQuery("SELECT file FROM FileInfo file WHERE file.uuid =: uuid", FileInfo.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
	}
}
