package de.serviceware.escaperoom.escaperoom.service;

import de.serviceware.escaperoom.escaperoom.model.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Service implementation for the KV-themed escape room riddles.
 * This class handles the logic for validating solutions and managing the progression
 * through the escape room's riddles.
 */
@Service
@Profile("KV")
public class KVRiddle extends RiddleServiceBase {
    
    // Riddle texts
    private static final String RIDDLE_ONE = "Im Raum findet ihr eine mit einem Zahlenschloss mit einem vierstelligen Code abgeschlossene Kiste.<br> Darauf ein Zettel:<br><br><b>In diesem Jahr startet endlich die Kneipe mit deinem wunderschönen Namen!</b>";
    private static final String RIDDLE_TWO = "Sehr gut. Ihr habt das Rätsel gelöst! In der Kiste findet ihr weitere Hinweise. Weiter gehts mit dem nächsten Rätsel!<br><br>" +
            "\n" +
            "<b>In der richtigen Reihenfolge verbinden die Kegelbilder das linke Haus in Hamburg mit dem rechten Haus in Lübeck.</b>\t\n";
    private static final String RIDDLE_THREE = "Ihr seid dem rettenden vierstelligen Code einen Schritt näher gekommen. Aber es wartet auch schon das nächste Rätsel auf euch: Was soll euch diese Notiz nur sagen?<br><br>" +
            "<b>Ein guter Abend basiert auf dem Produkt aus Food und Drinks. Wichtig: Erst Food zusammen bringen, dann Drinks zusammen nehmen.</b>\n";
    private static final String RIDDLE_FOUR = "Wow! Ich hätte nicht gedacht, dass ihr es bis hier schafft! Aber ihr seid noch nicht am Ziel. Das nächste Rätsel wartet auf euch:<br><br>" +
            "<b>Kegler sind selten Physiker, achten also nicht auf Einheiten und verlassen sich auch manchmal auf Wikipedia! In Summe sollten euch die Bestandteile für einen guten Abend auf einer Scherenbahn den nächsten Code liefern.</b>";
    private static final String RIDDLE_FIVE = "Sehr gut ihr seid einen guten Schritt weiter gekommen. Aber die Rätsel hören einfach nicht auf. Hier das nächste:<br><br>" +
            "<b>Puzzelt die Teile zusammen! Alle Ziffern < 1 und Sonderzeichen raus und ihr habt den nächsten Code.</b>";
    private static final String RIDDLE_SIX = "Sehr gut! Ihr habt es fast geschafft. Der finale Tipp: <br><br>" +
            "Der Code für das Zahlenschloss an der Tür ist das Gründungsjahr des KV - die Anzahl der Gründungsmitglieder - die Anzahl der abgeschlossenen Kegeljahre.\n";
    private static final String FINAL_TEXT = "Das Schloss öffnet sich, die Tür geht auf. Gelangt in den Schankraum: Dort steht noch eine Runde frisch gezapftes Bier, aber dieses mal nicht! Ich geht daran vorbei, zielstrebig auf die Ausgangstür zu. Glück gehabt, diese ist nicht abgeschlossen! Ihr entkommt, seid in Sicherheit und müsst euch jetzt nur noch um eine neue Kneipe Gedanken machen….Was wohl Funny macht?!";

    // Static content
    private static final String ESCAPE_ROOM_TITLE = "Herzlich Willkommen im KV Escape Room";
    private static final String ESCAPE_ROOM_DESCRIPTION = "Eure Aufgabe für die nächsten <b>60 Minuten</b>: Löst die Rätsel, um den Code am Zahlenschloss der Tür zu knacken!\n" +
            "    <br>Die Lösung zu jedem Rätsel ist ein Code, den ihr im Feld unten eingeben und prüfen könnt.\n" +
            "    <br>Ist der Code für das jeweilige Rätsel richtig erhaltet ihr das nächste Rätsel.\n" +
            "    <br>Irgendwann hoffentlich den finalen Code um das Schloss der Tür zu öffnen und zu fliehen.\n" +
            "    <br>Um die Rätsel lösen zu können erhält jeder Spieler einen Zettel mit Hinweisen (s. unten).\n" +
            "    <br>";

    // Hint content
    private static final String HINT_TITLE = "Hinweise";
    private static final String HINT_DESCRIPTION = "Pro Spieler gibt es einen Hinweiszettel (bei drei Spielern muss einer zwei nehmen).\n" +
            "<br>Jeder Zettel enthält Hinweise, die euch zusammen mit dem passenden Rätsel zum richtigen Code führen.";

    // Ready content
    private static final String READY_TITLE = "Klickt hier und es geht los!";
    private static final String READY_DESCRIPTION = "Habt ihr die Hinweise verteilt und die Aufgaben verstanden?";

    // Solution constants
    public static final String SOLUTION_1 = "2017";
    public static final String SOLUTION_2 = "13301725";
    public static final String SOLUTION_3 = "1224";
    public static final String SOLUTION_4 = "210,5";
    public static final String SOLUTION_5 = "131221";
    public static final String SOLUTION_6 = "1986";

    private final Map<String, RiddleInfo> solutionMap;

    public KVRiddle() {
        solutionMap = new HashMap<>();
        solutionMap.put(SOLUTION_1, new RiddleInfo(RIDDLE_TWO, "Rätsel 2"));
        solutionMap.put(SOLUTION_2, new RiddleInfo(RIDDLE_THREE, "Rätsel 3"));
        solutionMap.put(SOLUTION_3, new RiddleInfo(RIDDLE_FOUR, "Rätsel 4"));
        solutionMap.put(SOLUTION_4, new RiddleInfo(RIDDLE_FIVE, "Rätsel 5"));
        solutionMap.put(SOLUTION_5, new RiddleInfo(RIDDLE_SIX, "Rätsel 6"));
        solutionMap.put(SOLUTION_6, new RiddleInfo(FINAL_TEXT, "Geschafft!!"));
    }

    /**
     * Gets the text for the first riddle.
     * @return The text of the first riddle
     */
    public static String getRiddleOne() {
        return RIDDLE_ONE;
    }

    /**
     * Gets the text for the second riddle.
     * @return The text of the second riddle
     */
    public static String getRiddleTwo() {
        return RIDDLE_TWO;
    }

    /**
     * Gets the text for the third riddle.
     * @return The text of the third riddle
     */
    public static String getRiddleThree() {
        return RIDDLE_THREE;
    }

    /**
     * Gets the text for the fourth riddle.
     * @return The text of the fourth riddle
     */
    public static String getRiddleFour() {
        return RIDDLE_FOUR;
    }

    /**
     * Gets the text for the fifth riddle.
     * @return The text of the fifth riddle
     */
    public static String getRiddleFive() {
        return RIDDLE_FIVE;
    }

    /**
     * Gets the text for the sixth riddle.
     * @return The text of the sixth riddle
     */
    public static String getRiddleSix() {
        return RIDDLE_SIX;
    }

    /**
     * Gets the final text shown after completing all riddles.
     * @return The final text
     */
    public static String getFinalText() {
        return FINAL_TEXT;
    }

    /**
     * Gets the escape room title.
     * @return The escape room title
     */
    public static String getEscapeRoomTitle() {
        return ESCAPE_ROOM_TITLE;
    }

    /**
     * Gets the escape room description.
     * @return The escape room description
     */
    public static String getEscapeRoomDescription() {
        return ESCAPE_ROOM_DESCRIPTION;
    }

    /**
     * Gets the hint title.
     * @return The hint title
     */
    public static String getHintTitle() {
        return HINT_TITLE;
    }

    /**
     * Gets the hint description.
     * @return The hint description
     */
    public static String getHintDescription() {
        return HINT_DESCRIPTION;
    }

    /**
     * Gets the ready title.
     * @return The ready title
     */
    public static String getReadyTitle() {
        return READY_TITLE;
    }

    /**
     * Gets the ready description.
     * @return The ready description
     */
    public static String getReadyDescription() {
        return READY_DESCRIPTION;
    }

    /**
     * Initializes the escape room with the first riddle and necessary content.
     * @return The initialized EscapeRoom object
     */
    @Override
    public EscapeRoom initEscapeRoom() {
        Riddle riddle = new Riddle(RIDDLE_ONE, "Rätsel 1", null);
        ImageContent image = new ImageContent("kv/KVEscapeRoom.png", null, null);
        return new EscapeRoom(riddle, image, getHints(), getStaticTextContent());
    }

    /**
     * Creates the static text content for the escape room.
     * @return StaticTextContent object containing all static text elements
     */
    private StaticTextContent getStaticTextContent() {
        return new StaticTextContent(ESCAPE_ROOM_TITLE, ESCAPE_ROOM_DESCRIPTION, 
                                   HINT_TITLE, HINT_DESCRIPTION, 
                                   READY_TITLE, READY_DESCRIPTION);
    }

    /**
     * Retrieves the list of hints for the escape room.
     * @return List of Hint objects
     */
    private List<Hint> getHints() {
        return Arrays.asList(
            new Hint("Hinweis Spieler 1", "kv/hints/KV_Escaperoom_Player1.pdf"),
            new Hint("Hinweis Spieler 2", "kv/hints/KV_Escaperoom_Player2.pdf"),
            new Hint("Hinweis Spieler 3", "kv/hints/KV_Escaperoom_Player3.pdf"),
            new Hint("Hinweis Spieler 4", "kv/hints/KV_Escaperoom_Player4.pdf")
        );
    }

    /**
     * Validates a solution proposal and returns the appropriate result.
     * @param proposal The proposed solution to validate
     * @return SolutionProposalResult containing the next riddle or incorrect result
     */
    @Override
    public SolutionProposalResult validateSolutionProposal(SolutionProposal proposal) {
        if (proposal == null || proposal.getProposal() == null) {
            return getIncorrectResult();
        }

        RiddleInfo nextRiddle = solutionMap.get(proposal.getProposal());
        if (nextRiddle != null) {
            return getCorrectResult(nextRiddle.riddleText, nextRiddle.title, null);
        }
        
        return getIncorrectResult();
    }

    /**
     * Helper class to store riddle information
     */
    private static class RiddleInfo {
        final String riddleText;
        final String title;

        RiddleInfo(String riddleText, String title) {
            this.riddleText = riddleText;
            this.title = title;
        }
    }
}
