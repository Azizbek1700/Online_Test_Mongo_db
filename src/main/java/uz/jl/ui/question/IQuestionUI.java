package uz.jl.ui.question;

import uz.jl.dto.BaseGenericDto;
import uz.jl.dto.GenericDto;
import uz.jl.dto.answer.AnswerCreateDto;

/**
 * @author Botirov Najmiddin, Mon 00:47. 31/01/2022
 */
public interface IQuestionUI<CD extends BaseGenericDto, UD extends GenericDto> {

    AnswerCreateDto createAnswer();
    void updateAnswer();
    void getAllAnswer();
    void getAnswer();
    void deleteAnswer();
}
