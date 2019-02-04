package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // == constants
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == fields
    private final Game game;

    // == constructor ==

    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == init
    @PostConstruct
    public void init(){
        log.info("game = {}",game);
    }

    // == public methods
    @Override
    public String getMainMessage() {
        return "Number is between " + game.getSmallest() + " and " + game.getBiggest() + " Can you guess it ?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return "Game Won the number was"+game.getNumber();
        }
        else if(game.isGameLost()){
            return "Game Lost"+game.getNumber();
        }
        else if(!game.isValidNumber()){
            return "Invalid Number Range";
        }
        else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess ?";
        }
        else{
            String direction = "Lower";

            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }

            return direction + "! You have "+game.getRemainingGuesses()+" guess left";
        }
    }
}
