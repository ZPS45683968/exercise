package superhero;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Player {
    Set<GameCharacter> characters;
    public Player(Set<GameCharacter> characters) {
        this.characters = characters;
    }
    //An appropriately named method to add a character to the set
    public void addCharacter(GameCharacter character) {
        characters.add(character);
    }
    //A get method to retrieve the field value.
    public Set<GameCharacter> getCharacters() {
        return characters;
    }
    /*
    This method should implement the behaviour shown above in the example: given a set of powers,
this should return the set of characters required to provide all of the requested powers, or null if it is
not possible to provide the powers. If no set of characters can be found to cover all of the required
powers, this method should return null. If more than one set of characters can provide the required
powers, any valid set of characters can be returned (e.g., in the example, a level requiring Weapons
and Magic could return Raven and Robin, or Raven and Cyborg).
     */
    public Set<GameCharacter> chooseCharacters(Power... neededPowers) {
        Set<GameCharacter> result = new HashSet<>();
        Set<Power> powers = new HashSet<>(Arrays.asList(neededPowers));
        for (GameCharacter character : characters) {
            for(Power power : character.getPowers()) {
                if(powers.contains(power)) {
                    powers.remove(power);
                    result.add(character);
                }
            }
        }
        if (powers.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }
}
