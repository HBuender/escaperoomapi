package de.serviceware.escaperoom.escaperoom;

import de.serviceware.escaperoom.escaperoom.model.EscapeRoom;
import de.serviceware.escaperoom.escaperoom.model.SolutionProposal;
import de.serviceware.escaperoom.escaperoom.model.SolutionResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@CrossOrigin
public class EscaperoomController {

    private String riddleOne="Im Raum findet ihr eine mit einem Zahlenschloss mit einem vierstelligen Code abgeschlossene Kiste.<br> Darauf ein Zettel:<br><br><b>In diesem Jahr startet endlich die Kneipe mit deinem wunderschönen Namen!</b>";
    private String riddleTwo="Sehr gut. Ihr habt das Rätsel gelöst! In der Kiste findet ihr weitere Hinweise. Weiter gehts mit dem nächsten Rätsel!<br><br>" +
            "\n" +
            "<b>In der richtigen Reihenfolge verbinden die Kegelbilder das linke Haus in Hamburg mit dem rechten Haus in Lübeck.</b>\t\n";
    private String riddleThree ="Ihr seid dem rettenden vierstelligen Code einen Schritt näher gekommen. Aber es wartet auch schon das nächste Rätsel auf euch: Was soll euch diese Notiz nur sagen?<br><br>" +
            "<b>Ein guter Abend basiert auf dem Produkt aus Food und Drinks. Wichtig: Erst Food zusammen bringen, dann Drinks zusammen nehmen.</b>\n";
    private String riddleFour ="Wow! Ich hätte nicht gedacht, dass ihr es bis hier schafft! Aber ihr seid noch nicht am Ziel. Das nächste Rätsel wartet auf euch:<br><br>" +
            "<b>Kegler sind selten Physiker, achten also nicht auf Einheiten und verlassen sich auch manchmal auf Wikipedia! In Summe sollten euch die Bestandteile für einen guten Abend auf einer Scherenbahn den nächsten Code liefern.</b>";
    private String riddleFive="Sehr gut ihr seid einen guten Schritt weiter gekommen. Aber die Rätsel hören einfach nicht auf. Hier das nächste:<br><br>" +
            "<b>Puzzelt die Teile zusammen! Alle Ziffern < 1 und Sonderzeichen raus und ihr habt den nächsten Code.</b>";
    private String riddleSix="Sehr gut! Ihr habt es fast geschafft. Der finale Tipp: <br><br>" +
            "Der Code für das Zahlenschloss an der Tür ist das Gründungsjahr des KV - die Anzahl der Gründungsmitglieder - die Anzahl der abgeschlossenen Kegeljahre.\n";
    private String finalText="Das Schloss öffnet sich, die Tür geht auf. Gelangt in den Schankraum: Dort steht noch eine Runde frisch gezapftes Bier, aber dieses mal nicht! Ich geht daran vorbei, zielstrebig auf die Ausgangstür zu. Glück gehabt, diese ist nicht abgeschlossen! Ihr entkommt, seid in Sicherheit und müsst euch jetzt nur noch um eine neue Kneipe Gedanken machen….Was wohl Funny macht?!";
    @PostMapping("/solutionProposal")
    public @ResponseBody SolutionResult getNextRiddlePost(@RequestBody SolutionProposal proposal, @RequestHeader HttpHeaders headers) {
        System.out.println(headers);
        System.out.println(headers.getHost()+": "+proposal.getProposal());
        String result= "";
        if("2017".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleTwo,"Rätsel 2",null);
        }
        if("13301725".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleThree,"Rätsel 3   ",null);
        }
        if("1224".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleFour,"Rätsel 4   ",null);
        }
        if("210,5".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleFive,"Rätsel 5   ",null);
        }
        if("131221".equals(proposal.getProposal())){
            return new SolutionResult(true,riddleSix,"Rätsel 6   ",null);
        }
        if("1986".equals(proposal.getProposal())){
            return new SolutionResult(true,finalText,"Geschafft!!   ",null);
        }
        return new SolutionResult(false,"egal","egal",null);
    }





    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String isHealthy(@RequestHeader HttpHeaders headers){
        System.out.println(headers.getHost()+": Health Check");
        return "Up and running";
    }

    @GetMapping("/initEscapeRoom")
    @ResponseStatus(HttpStatus.OK)
    public EscapeRoom initEscapeRoom(){
        return new EscapeRoom(this.riddleOne,"Rätsel 1");
    }


}

