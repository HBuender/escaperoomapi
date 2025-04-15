package de.serviceware.escaperoom.escaperoom.service;

import de.serviceware.escaperoom.escaperoom.model.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;
import java.util.Arrays;
import java.util.List;

@Service
@Profile("KV")
public class KVRiddle extends RiddleServiceBase {
    public static String riddleOne="Im Raum findet ihr eine mit einem Zahlenschloss mit einem vierstelligen Code abgeschlossene Kiste.<br> Darauf ein Zettel:<br><br><b>In diesem Jahr startet endlich die Kneipe mit deinem wunderschönen Namen!</b>";
    public static String riddleTwo="Sehr gut. Ihr habt das Rätsel gelöst! In der Kiste findet ihr weitere Hinweise. Weiter gehts mit dem nächsten Rätsel!<br><br>" +
            "\n" +
            "<b>In der richtigen Reihenfolge verbinden die Kegelbilder das linke Haus in Hamburg mit dem rechten Haus in Lübeck.</b>\t\n";
    public static String riddleThree ="Ihr seid dem rettenden vierstelligen Code einen Schritt näher gekommen. Aber es wartet auch schon das nächste Rätsel auf euch: Was soll euch diese Notiz nur sagen?<br><br>" +
            "<b>Ein guter Abend basiert auf dem Produkt aus Food und Drinks. Wichtig: Erst Food zusammen bringen, dann Drinks zusammen nehmen.</b>\n";
    public static String riddleFour ="Wow! Ich hätte nicht gedacht, dass ihr es bis hier schafft! Aber ihr seid noch nicht am Ziel. Das nächste Rätsel wartet auf euch:<br><br>" +
            "<b>Kegler sind selten Physiker, achten also nicht auf Einheiten und verlassen sich auch manchmal auf Wikipedia! In Summe sollten euch die Bestandteile für einen guten Abend auf einer Scherenbahn den nächsten Code liefern.</b>";
    public static String riddleFive="Sehr gut ihr seid einen guten Schritt weiter gekommen. Aber die Rätsel hören einfach nicht auf. Hier das nächste:<br><br>" +
            "<b>Puzzelt die Teile zusammen! Alle Ziffern < 1 und Sonderzeichen raus und ihr habt den nächsten Code.</b>";
    public static String riddleSix="Sehr gut! Ihr habt es fast geschafft. Der finale Tipp: <br><br>" +
            "Der Code für das Zahlenschloss an der Tür ist das Gründungsjahr des KV - die Anzahl der Gründungsmitglieder - die Anzahl der abgeschlossenen Kegeljahre.\n";
    public static String finalText="Das Schloss öffnet sich, die Tür geht auf. Gelangt in den Schankraum: Dort steht noch eine Runde frisch gezapftes Bier, aber dieses mal nicht! Ich geht daran vorbei, zielstrebig auf die Ausgangstür zu. Glück gehabt, diese ist nicht abgeschlossen! Ihr entkommt, seid in Sicherheit und müsst euch jetzt nur noch um eine neue Kneipe Gedanken machen….Was wohl Funny macht?!";

    public static String escapreRoomTitle="Herzlich Willkommen im KV Escape Room";

    public static String escapeRoomDescription="Eure Aufgabe für die nächsten <b>60 Minuten</b>: Löst die Rätsel, um den Code am Zahlenschloss der Tür zu knacken!\n" +
            "    <br>Die Lösung zu jedem Rätsel ist ein Code, den ihr im Feld unten eingeben und prüfen könnt.\n" +
            "    <br>Ist der Code für das jeweilige Rätsel richtig erhaltet ihr das nächste Rätsel.\n" +
            "    <br>Irgendwann hoffentlich den finalen Code um das Schloss der Tür zu öffnen und zu fliehen.\n" +
            "    <br>Um die Rätsel lösen zu können erhält jeder Spieler einen Zettel mit Hinweisen (s. unten).\n" +
            "    <br>";

    public static String hintTitle = "Hinweise";
    public static String hintDescription = "Pro Spieler gibt es einen Hinweiszettel (bei drei Spielern muss einer zwei nehmen).\n" +
            "<br>Jeder Zettel enthält Hinweise, die euch zusammen mit dem passenden Rätsel zum richtigen Code führen.";

    public static String areYouReadyTitle="Klickt hier und es geht los!";
    public static String areYouReadyDescription="Habt ihr die Hinweise verteilt und die Aufgaben verstanden?";

    @Override
    public EscapeRoom initEscapeRoom() {
        Riddle riddle = new Riddle(this.riddleOne, "Rätsel 1", null);
        ImageContent image = new ImageContent("kv/KVEscapeRoom.png",null,null);
        return new EscapeRoom(riddle,image,getHints(),getStaticTextContent());
    }

    private StaticTextContent getStaticTextContent() {
        return new StaticTextContent(escapreRoomTitle,escapeRoomDescription,hintTitle,hintDescription,areYouReadyTitle,areYouReadyDescription);
    }

    private List<Hint> getHints(){
        return Arrays.asList(new Hint("Hinweis Spieler 1","kv/hints/KV_Escaperoom_Player1.pdf"),
                new Hint("Hinweis Spieler 2","kv/hints/KV_Escaperoom_Player2.pdf"),
                new Hint("Hinweis Spieler 3","kv/hints/KV_Escaperoom_Player3.pdf"),
                new Hint("Hinweis Spieler 4","kv/hints/KV_Escaperoom_Player4.pdf"));
    }

    //TODO: Refactor
    @Override
    public SolutionProposalResult validateSolutionProposal(SolutionProposal proposal) {
        if("2017".equals(proposal.getProposal())){
            return getCorrectResult(riddleTwo,"Rätsel 2",null);
        }
        if("13301725".equals(proposal.getProposal())){
            return getCorrectResult(riddleThree,"Rätsel 3",null);
        }
        if("1224".equals(proposal.getProposal())){
            return getCorrectResult(riddleFour,"Rätsel 4",null);
        }
        if("210,5".equals(proposal.getProposal())){
            return getCorrectResult(riddleFive,"Rätsel 5",null);
        }
        if("131221".equals(proposal.getProposal())){
            return getCorrectResult(riddleSix,"Rätsel 6",null);
        }
        if("1986".equals(proposal.getProposal())){
            return getCorrectResult(finalText,"Geschafft!!",null);
        }
        return getIncorrectResult();
    }

}
