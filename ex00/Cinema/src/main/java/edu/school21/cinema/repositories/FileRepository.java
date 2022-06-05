package edu.school21.cinema.repositories;

import edu.school21.cinema.models.FileInfo;

import java.util.UUID;

public interface FileRepository {
	void save(FileInfo fileInfo);
	FileInfo getFileByUuid(UUID uuid);
}
