package ro.utcn.sd.mid.assign1.slackoverflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.Invoker;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.answerCommand.CreateAnswerCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.answerCommand.DeleteAnswerCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.answerCommand.EditAnswerCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.answerCommand.GetAnswersForQuestionCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.AnswerDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.EditAnswerDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.service.AnswerService;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final Invoker invoker;

    @GetMapping("/answersForQuestion/{questionId}")
    public Object answersOfQuestion(@PathVariable Integer questionId) {
        invoker.setCommand(new GetAnswersForQuestionCommand(answerService, questionId));
        return invoker.invoke();
    }

    @PostMapping("/answers")
    public Object create(@RequestBody AnswerDTO dto) {
        invoker.setCommand(new CreateAnswerCommand(answerService, dto));
        return invoker.invoke();
    }

    @DeleteMapping("/answer/{answerId}")
    public Object delete(@PathVariable Integer answerId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        invoker.setCommand(new DeleteAnswerCommand(answerService, answerId, name));
        return invoker.invoke();
    }

    @PutMapping("/answer/{answerId}")
    public Object edit(@RequestBody EditAnswerDTO dto,
                       @PathVariable Integer answerId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        invoker.setCommand(new EditAnswerCommand(answerService, answerId, name, dto.getNewAnswerText()));
        return invoker.invoke();
    }
}
