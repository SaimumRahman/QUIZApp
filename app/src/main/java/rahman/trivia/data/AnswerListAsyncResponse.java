package rahman.trivia.data;

import java.util.ArrayList;

import rahman.trivia.model.Question;

public interface AnswerListAsyncResponse {

    void processFinished(ArrayList<Question> questions);

}
