package uz.jl.dto.quiz;

import lombok.*;
import uz.jl.dto.BaseGenericDto;
import uz.jl.entity.question.Question;
import uz.jl.enums.Category;
import uz.jl.enums.Level;

import java.util.List;

/**
 * @author Botirov Najmiddin, Mon 10:08. 31/01/2022
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizCreateDto implements BaseGenericDto {
    private int size;
    private int mark;
    private Level level;
    private Category category;
    private List<Question> questions;
    private boolean closed = false;
    private int duration;


}
