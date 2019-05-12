const BASE_URL = "http://localhost:8080";

export default class RestClient {
    constructor(username, password) {
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    loadAllSOUsers = () => {
        // built-in js method that knows how to make an HTTP request
        // returns a Promise
        return fetch(BASE_URL + "/users", {
            method: "GET",
            headers: {
                //"Authorization": this.authorization,
            }
        }).then(response => response.json());
    };

    createSOUser = (soUsername, soPassword) => {
        return fetch(BASE_URL + "/register", {
            method: "POST",
            body: JSON.stringify({
                soUsername: soUsername,
                soPassword: soPassword
            }),
            headers: {
                //"Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    loginSOUser = (soUsername, soPassword) => {
        return fetch(BASE_URL + "/login", {
            method: "POST",
            body: JSON.stringify({
                soUsername: soUsername,
                soPassword: soPassword
            }),
            headers: {
                "Content-type": "application/json"
            }
        });
    };

    loadAllQuestions = () => {
        return fetch(BASE_URL + "/questions", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("YOU MUST LOG IN FIRST");
                window.location.assign("#/");
            });
    };

    createQuestion = (soUserId, title, text, tags) => {
        return fetch(BASE_URL + "/questions", {
            method: "POST",
            body: JSON.stringify({
                userId: soUserId,
                title: title,
                text: text,
                tags: tags
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    filterQuestions = (tagOrTitle, value) => {
        return fetch(BASE_URL + "/questions/filter" + tagOrTitle + "=" + value, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("PROBLEMA LA FILTER");
                window.location.assign("#/");
            });
    };

    loadAllAnswers = () => {
        return fetch(BASE_URL + "/answers", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("YOU MUST LOG IN FIRST");
                window.location.assign("#/");
            });
    };

    findAnswersByQuestionId = (questionId) => {
        questionId = parseInt(questionId);
        return fetch(BASE_URL + "/answersForQuestion/" + questionId, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("YOU MUST BE LOGGED IN TO SEE THE ANSWERS");
                window.location.assign("#/");
            });
    };

    createAnswer = (questionId, soUserId, text) => {
        return fetch(BASE_URL + "/answers", {
            method: "POST",
            body: JSON.stringify({
                userId: soUserId,
                questionId: questionId,
                text: text
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    editAnswer = (answerId, newText) => {
        return fetch(BASE_URL + "/answer/" + answerId, {
            method: "PUT",
            body: JSON.stringify({
                newAnswerText: newText
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json())
            .catch(error => console.log(error))
    };

    deleteAnswer = (answerId) => {
        return fetch(BASE_URL + "/answer/" + answerId, {
            method: "DELETE",
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json())
            .catch(error => console.log(error))
    }


}