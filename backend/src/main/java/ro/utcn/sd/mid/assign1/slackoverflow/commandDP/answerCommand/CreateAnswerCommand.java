package ro.utcn.sd.mid.assign1.slackoverflow.commandDP.answerCommand;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.Command;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.AnswerDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.service.AnswerService;

@AllArgsConstructor
public class CreateAnswerCommand implements Command {
    private AnswerService answerService;
    private AnswerDTO answerDTO;

    @Override
    public Object execute() {
        return answerService.saveAnswer(answerDTO);
    }
}
