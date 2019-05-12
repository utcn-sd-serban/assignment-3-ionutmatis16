package ro.utcn.sd.mid.assign1.slackoverflow;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.AnswerVoteDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.dto.QuestionVoteDTO;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.Answer;
import ro.utcn.sd.mid.assign1.slackoverflow.entity.Question;
import ro.utcn.sd.mid.assign1.slackoverflow.exceptions.AlreadyVotedException;
import ro.utcn.sd.mid.assign1.slackoverflow.repository.api.RepositoryFactory;
import ro.utcn.sd.mid.assign1.slackoverflow.repository.memory.InMemoryRepositoryFactory;
import ro.utcn.sd.mid.assign1.slackoverflow.service.AnswerService;
import ro.utcn.sd.mid.assign1.slackoverflow.service.VoteService;

import java.sql.Timestamp;
import java.util.ArrayList;

public class VoteServiceUnitTests {
    private static Question q1 = new Question(1, 1, "Title1", "Question 1", new Timestamp(System.currentTimeMillis()), new ArrayList<>(), 0);
    private static Answer a1 = new Answer(1, 1, 1, "Question 1", new Timestamp(System.currentTimeMillis()), 0);
    private static ApplicationEventPublisher eventPublisher = (o) -> {
    };

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createQuestionRepository().save(q1);
        factory.createAnswerRepository().save(a1);
        return factory;
    }

    @Test(expected = AlreadyVotedException.class)
    public void testUpvote() {
        RepositoryFactory factory = createMockedFactory();
        VoteService voteService = new VoteService(factory);

        voteService.voteQuestion(new QuestionVoteDTO(0, 1, 1, true));
        voteService.voteQuestion(new QuestionVoteDTO(1, 1, 1, true));
    }

    @Test
    public void testMultipleVotes() {
        RepositoryFactory factory = createMockedFactory();
        VoteService voteService = new VoteService(factory);
        AnswerService answerService = new AnswerService(factory, eventPublisher);

        voteService.voteAnswer(new AnswerVoteDTO(0, 1, 2, true));
        voteService.voteAnswer(new AnswerVoteDTO(0, 1, 2, false));
        voteService.voteAnswer(new AnswerVoteDTO(0, 1, 2, true));
        voteService.voteAnswer(new AnswerVoteDTO(0, 1, 2, false));

        Assert.assertEquals(-1, (int) answerService.findById(1).getScore());
    }
}
