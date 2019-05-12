package ro.utcn.sd.mid.assign1.slackoverflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.*;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.questionCommand.CreateQuestionCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.questionCommand.FilterByTagCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.questionCommand.FilterByTitleCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.commandDP.questionCommand.GetAllQuestionsCommand;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.QuestionDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.service.QuestionService;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final Invoker invoker;

    @GetMapping("/questions")
    public Object readAll() {
        invoker.setCommand(new GetAllQuestionsCommand(questionService));
        return invoker.invoke();
    }

    @PostMapping("/questions")
    public Object create(@RequestBody QuestionDTO dto) {
        invoker.setCommand(new CreateQuestionCommand(questionService,dto));
        return invoker.invoke();
    }

    @GetMapping("/questions/filterTitle={title}")
    public Object filterTitle(@PathVariable String title) {
        invoker.setCommand(new FilterByTitleCommand(questionService,title));
        return invoker.invoke();
    }

    @GetMapping("/questions/filterTag={tag}")
    public Object filterTag(@PathVariable String tag) {
        invoker.setCommand(new FilterByTagCommand(questionService,tag));
        return invoker.invoke();
    }

}
