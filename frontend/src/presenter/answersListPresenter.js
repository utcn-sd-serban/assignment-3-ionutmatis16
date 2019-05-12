import sOUserModel from "../model/sOUserModel";
import answerModel from "../model/answerModel";

class AnswersListPresenter {
    onCreate = (questionId) => {
        let newAnswer = answerModel.state.newAnswer;
        answerModel.addAnswer(sOUserModel.state.loggedInSOUser, questionId, newAnswer.text)
            .then(answerModel.changeNewAnswerProperty("text", ""));
    };

    onChange = (property, value) => {
        answerModel.changeNewAnswerProperty(property, value);
    };

    onChangeNewAnswer = (answerId, value) => {
        answerModel.changeNewTextForAnswer(answerId, value);
    };

    onEditPress = (answerId) => {
        answerModel.changeEditButtonPressedForAnswer(answerId);
    };

    onEditSubmit = (answerId) => {
        answerModel.editAnswer(answerId);
        answerModel.changeEditButtonPressedForAnswer(answerId);
    };

    onDeleteAnswer = (answerId) => {
        answerModel.deleteAnswer(answerId);
    };

    onInit = (questionId) => {
        //console.log("FILTERED PRESENTER INAINTE FIND " + answerModel.state.filteredAnswers)
        answerModel.findAnswersByQuestionId(questionId);
        //console.log("FILTERED PRESENTER DUPA FIND " + answerModel.state.filteredAnswers)

    }
}

const answersListPresenter = new AnswersListPresenter();

export default answersListPresenter;