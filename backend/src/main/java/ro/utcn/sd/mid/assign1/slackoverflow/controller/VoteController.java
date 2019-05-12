package ro.utcn.sd.mid.assign1.slackoverflow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.AnswerVoteDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.QuestionVoteDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.service.VoteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @GetMapping("/questionVotes")
    public List<QuestionVoteDTO> readAllQuestionVotes() {
        return voteService.findAllQuestionVotes();
    }

    @PostMapping("/questionVotes")
    public QuestionVoteDTO createQuestionVote(@RequestBody QuestionVoteDTO dto) {
        return voteService.voteQuestion(dto);
    }

    @GetMapping("/answerVotes")
    public List<AnswerVoteDTO> readAllAnswerVotes() {
        return voteService.findAllAnswerVotes();
    }

    @PostMapping("/answerVotes")
    public AnswerVoteDTO createAnswerVote(@RequestBody AnswerVoteDTO dto) {
        return voteService.voteAnswer(dto);
    }
}
