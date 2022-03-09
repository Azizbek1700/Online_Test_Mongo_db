package uz.jl.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.jl.dto.BaseGenericDto;

/**
 * @author Botirov Najmiddin, Mon 01:00. 31/01/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreateDto implements BaseGenericDto {
    private String answer;
    private boolean right;
}
