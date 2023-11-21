package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.videohostingchannels.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
