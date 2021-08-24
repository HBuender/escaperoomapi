package de.serviceware.escaperoom.escaperoom.service;

import de.serviceware.escaperoom.escaperoom.model.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Profile("KV2")
public class KV2 extends RiddleServiceBase{

    private String riddleOne="Glücklicherweise gibt es am Dortmunder Flughafen noch die Sonderedition des heimischen  Bieres, die den Ruhrpott feiert und euch schon 2019 gut geschmeckt hat.\n" +
            "\n" +
            "Wenn ihr alles richtig kombiniert, führt euch die Zahl zum nächsten Verkehrsknotenpunkt. Bitte rundet die Ausgangswerte erst nach den gültigen mathematischen Regeln auf volle Zahlen auf oder ab.\n";

    private String riddleTwo="Am Flughafen angekommen wird euch klar, dass ihr mit dem zweiten Rätsel die Flugnummer zum nächsten Ziel herausfinden könnt.\n";
    private String riddleThree ="In Palma angekommen findet ihr den nächsten Hinweis. Zunächst mal müsst ihr den nächsten Verkehrsknotenpunkt finden. Dazu helfen euch die Puzzle Teile.\n";

    private String riddleFour ="Ihr findet vier Karten. Wenn ihr klug kombiniert, was diese Städte im Schilde führen, erhaltet ihr alle Buchstaben, um den nächsten Zielort zu finden.";

    private String riddleFive="Euch begegnen diese fünf Spieler des lokalen Fußballvereins und ihr wisst sofort mit welchem Transportmittel es weitergeht.\n";

    private String riddleSix="Wie ihr euch sicherlich schon denkt ist die nächste Station unserer Reise Prag. Aber um den Zug besteigen zu dürfen müsste ihr zunächst die richtige Zahlenkombination finden. Dabei hilft euch neben dem Wissen über das Ziel natürlich auch euer Wissen über den KV.\n" +
            "Wenn ihr die vier Zahlen hier im richtigen Format einsetzt (11.2233, 44.5555) erhaltet ihr den Zielort\n";
    private String finalText="Angekommen am Hauptbahnhof in Prag gebt ihr die Koordinaten sofort in euer Handy ein und spurtet los. " +
            "<br>Über den belebten Altstädter Ring erreicht ihr eine zwielichtige Gasse, wo euch die Neonreklame einer wohl bekannten Bar begrüßt. " +
            "<br>Doch drinnen bietet sich euch ein Bild des Grauens. " +
            "<br>Ihr erblickt die Geflüchteten an einem Tisch, auf dem diverse leere Gin-Gläser stehen. " +
            "<br>Hendrik gönnt sich gerade ein kleines Nickerchen und auch der Gott-König und Präsident ist mit sabberndem Mund am Tisch eingeschlafen. " +
            "<br>Eine leere Geldbörse liegt vor ihm und in der Hand hält er eine heiße MILF mit Honig. " +
            "<br>Als ihr die beiden gerade wecken wollt, springt plötzlich ein Angestellter der Prager Verkehrsbetriebe aus dem Schatten hervor und blockiert euren Weg. " +
            "<br>“Das macht dann 80.000 Kronen wegen Schwarzfahrens quer durch Europa”, ruft er und bricht in diabolisches Gelächter aus.  \n" +
            "<br>Schweißgebadet wacht ihr auf. \n" +
            "<br><b>Herzlichen Glückwunsch</b>!\n" +
            "<br>Ihr habt das Ziel unserer Reise gefunden. Schickt eine Whatsapp Nachricht mit dem Namen der Bar and Thilo oder Hendrik, um euch den Sieg zu sichern.\n";



    private String escapreRoomTitle="Herzlich Willkommen bei KV Scotland Yard";

    private String escapeRoomDescription="Eure Aufgabe für die nächsten <b>60 Minuten</b>: Löst die Rätsel, um von Zwischenstation über Zwischenstation zum Ziel zu kommen!\n" +
            "    <br>Die Lösung zu jedem Rätsel ist ein Code, den ihr im Feld unten eingeben und prüfen könnt.\n" +
            "    <br>Ist der Code für das jeweilige Rätsel richtig erhaltet ihr das nächste Rätsel.\n" +
            "    <br>Irgendwann hoffentlich den finalen Code, der euch zum Festkomittee führt.\n" +
            "    <br>Um die Rätsel lösen zu können erhält jeder Spieler einen Zettel mit Hinweisen (s. unten).\n" +
            "    <br>";

    private String hintTitle = "Hinweise";
    private String hintDescription = "Pro Spieler gibt es einen Hinweiszettel (bei vier Spielern muss einer zwei nehmen).\n" +
            "<br>Jeder Zettel enthält Hinweise, die euch zusammen mit dem passenden Rätsel zum richtigen Code führen.";

    private String areYouReadyTitle="Klickt hier und es geht los!";
    private String areYouReadyDescription="Habt ihr die Hinweise verteilt und die Aufgaben verstanden?";


    @Override
    public EscapeRoom initEscapeRoom() {
        Riddle riddle = new Riddle(this.riddleOne, "Rätsel 1", null);
        ImageContent image = new ImageContent("kv2/KV-Scotland-Yard.jpg",null,null);
        return new EscapeRoom(riddle,image,getHints(),getStaticTextContent());
    }

    private StaticTextContent getStaticTextContent() {
        return new StaticTextContent(escapreRoomTitle,escapeRoomDescription,hintTitle,hintDescription,areYouReadyTitle,areYouReadyDescription);
    }

    private List<Hint> getHints(){
        return Arrays.asList(new Hint("Hinweis Spieler 1","kv2/hints/TN1.pdf"),
                new Hint("Hinweis Spieler 2","kv2/hints/TN2.pdf"),
                new Hint("Hinweis Spieler 3","kv2/hints/TN3.pdf"),
                new Hint("Hinweis Spieler 4","kv2/hints/TN4.pdf"),
                new Hint("Hinweis Spieler 5","kv2/hints/TN5.pdf"));
    }


    @Override
    public SolutionProposalResult validateSolutionProposal(SolutionProposal proposal) {
        if("85356".equals(proposal.getProposal())){
            return getCorrectResult(riddleTwo,"Rätsel 2",null);
        }
        if("EW6891".equals(proposal.getProposal())){
            return getCorrectResult(riddleThree,"Rätsel 3",null);
        }
        if("Hafen".equals(proposal.getProposal())){
            return getCorrectResult(riddleFour,"Rätsel 4",null);
        }
        if("BARCA".equals(proposal.getProposal())){
            return getCorrectResult(riddleFive,"Rätsel 5",new ImageContent("kv2/Full.png",null,null));
        }
        if("train".equals(proposal.getProposal())){
            return getCorrectResult(riddleSix,"Rätsel 6",null);
        }
        if("50.0897, 14.4221".equals(proposal.getProposal())){
            return getCorrectResult(finalText,"Herzlichen Glückwunsch!",null);
        }
        return getIncorrectResult();
    }
}
