package pn.services;

import pn.models.dtos.GameDto;

public interface GameService {
    String addGame(String userEmail, GameDto gameDto);
}
