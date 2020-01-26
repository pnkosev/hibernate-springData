package pn.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pn.models.dtos.GameDto;
import pn.models.entities.Game;
import pn.models.entities.Role;
import pn.models.entities.User;
import pn.repositories.GameRepository;
import pn.repositories.UserRepository;

import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(String userEmail, GameDto gameDto) {
        StringBuilder sb = new StringBuilder();

        User user = this.userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return sb.append("Must be logged in in order to add games!").toString();
        }

        if (user.getRole() != Role.ADMIN) {
            return sb.append("Must be admin in order to add games!").toString();
        }

        Game game = this.modelMapper.map(gameDto, Game.class);

        this.gameRepository.saveAndFlush(game);
        Set<Game> userGames = user.getGames();
        userGames.add(game);
        user.setGames(userGames);
        this.userRepository.saveAndFlush(user);

        return sb.append(String.format("Added %s!", game.getTitle())).toString();
    }
}
